package com.kansate.collect;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iciql.Db;
import com.kansate.db.model.ApisSatelliteinfo;

public class CollectorTest {

	private Db db = null;
	private ApisSatelliteinfo satelliteInfo = new ApisSatelliteinfo();
	
	@Before
	public void setup() {
		this.db = Db.open("jdbc:mysql://localhost:3306/kanpai?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "kanpai", "passwd");
	}

	@After
	public void tearDown() {
		List<ApisSatelliteinfo> entityList = db.from(this.satelliteInfo).select();
		db.deleteAll(entityList);
		db.close();
	}

	@Test
	public void testExecute() throws SQLException {
		// init
		Collector target = new Collector();

		// execute
		target.execute("./src/test/resources/com/kansate/collect/CollectorTestData/");

		// verify
		List<ApisSatelliteinfo> actualList = db.from(satelliteInfo).select();
		System.out.println(actualList);
	}

}
