package com.kansate.calculate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iciql.Db;
import com.kansate.db.model.ApisSatelliteinfo;
import com.kansate.orbital.calculate.OrbitalCalculator;
import com.kansate.type.IsDel;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import mockit.Mock;
import mockit.MockUp;

public class CalculatorTest {

	private static final String[] MEMCACHE_SERVERS = { "localhost:11211" };
	private Db db = null;

	@Before
	public void setUp() {
		initDb();
	}

	@After
	public void tearDown() {
		cleanDb();
	}

	@Test
	public void testRun() {
		// init
		Calculator target = new Calculator();
		target.setup();

		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(MEMCACHE_SERVERS);
		pool.initialize();
		MemCachedClient client = new MemCachedClient();

		new MockUp<OrbitalCalculator>() {
			@Mock
			public void execute(ApisSatelliteinfo satelliteInfo) {
				assertThat(satelliteInfo.id, is(1));
				assertThat(satelliteInfo.satName, is("gosat"));
				assertThat(satelliteInfo.epochYear, is(16));
				assertThat(satelliteInfo.epochTime, is("116.52343850"));
				assertThat(satelliteInfo.argumentOfPerigee, is(125.2481));
				assertThat(satelliteInfo.inclinationAngle, is(97.8891));
				assertThat(satelliteInfo.rightAscensionOfAscendingNode, is(157.6886));
				assertThat(satelliteInfo.eccentricity, is(0.0001542));
				assertThat(satelliteInfo.meanAnomaly, is(234.8869));
				assertThat(satelliteInfo.meanMotion, is(14.62171393));
				assertThat(satelliteInfo.meanMotionCoefficientChange, is(0.00000262));
				assertThat(satelliteInfo.isDel, is(IsDel.Anable.ordinal()));
			}

			@Mock
			public double getAngle() {
				return 41.11;
			}

			@Mock
			public double getElevation() {
				return 347.22;
			}
		};

		// execute
		target.run();

		// verify
		@SuppressWarnings("unchecked")
		List<String> satList = (List<String>) client.get("041347");
		assertThat(satList.size(), is(1));
		assertThat(satList.get(0), is("gosat"));

		// final
		client.flushAll();
	}

	private void initDb() {
		db = Db.open("jdbc:mysql://localhost:3306/kanpai?useSSL=false&useUnicode=true&characterEncoding=UTF-8",
				"kanpai", "passwd");
		ApisSatelliteinfo entity = new ApisSatelliteinfo();
		entity.id = 1;
		entity.satName = "gosat";
		entity.epochYear = 16;
		entity.epochTime = "116.52343850";
		entity.argumentOfPerigee = 125.2481;
		entity.inclinationAngle = 97.8891;
		entity.rightAscensionOfAscendingNode = 157.6886;
		entity.eccentricity = 0.0001542;
		entity.meanAnomaly = 234.8869;
		entity.meanMotion = 14.62171393;
		entity.meanMotionCoefficientChange = 0.00000262;
		entity.isDel = IsDel.Anable.ordinal();

		db.insert(entity);
	}

	private void cleanDb() {
		ApisSatelliteinfo satelliteInfo = new ApisSatelliteinfo();
		List<ApisSatelliteinfo> rowList = db.from(satelliteInfo).select();
		db.deleteAll(rowList);
		db.close();
	}

}
