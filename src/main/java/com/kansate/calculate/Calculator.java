package com.kansate.calculate;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.iciql.Db;
import com.kansate.common.exception.ApplicationException;
import com.kansate.db.model.ApisSatelliteinfo;
import com.kansate.orbital.calculate.OrbitalCalculator;
import com.kansate.type.IsDel;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class Calculator {

	private static final Integer[] MEMCACHED_WEIGHT = {3};

	private static final String[] MEMCACHED_SERVERS = {"localhost:11211"};

	/** 軌道計算の対象衛星リスト */
	private List<ApisSatelliteinfo> satelliteList;
	
	private MemCachedClient memcachedClient;

	/**
	 * コンストラクタ
	 */
	public Calculator() {
		memcachedClient = new MemCachedClient();
		
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(MEMCACHED_SERVERS);
		pool.setWeights(MEMCACHED_WEIGHT);
		
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 24 * 3);	// 3日
		pool.initialize();
		
		// 一旦すべての情報を破棄する
		memcachedClient.flushAll();
	}

	/**
	 * 初期設定
	 *
	 * @param targetList
	 *            軌道計算対象の衛星情報
	 */
	public void setup() {
		Db db = Db.open("jdbc:mysql://localhost:3306/kanpai?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "kanpai", "passwd");
		ApisSatelliteinfo satelliteInfo = new ApisSatelliteinfo();
		this.satelliteList = db.from(satelliteInfo).where(satelliteInfo.isDel).is(IsDel.Anable.ordinal()).select();
		db.close();
		
		if(this.satelliteList.isEmpty()) {
			throw new ApplicationException("empty satellite info!");
		}
	}

	/**
	 * 軌道計算を並列実行する
	 */
	public void run() {
		Optional.ofNullable(this.satelliteList)
				.ifPresent(satelliteList -> satelliteList.forEach(satellite -> calculateAndSave(satellite)));
	}

	/**
	 * 軌道計算し保存する
	 *
	 * @param satellite
	 *            対象衛星情報
	 */
	private void calculateAndSave(ApisSatelliteinfo satellite) {
		OrbitalCalculator calculator = new OrbitalCalculator();
		calculator.execute(satellite);

		String gridId = getGridIdOfCelestialSphere(calculator.getAngle(), calculator.getElevation());
		@SuppressWarnings("unchecked")
		List<String> satelliteList = Optional.ofNullable((List<String>) memcachedClient.get(gridId)).orElse(Lists.newArrayList());
		satelliteList.add(satellite.satName);
		memcachedClient.set(gridId, satelliteList);
	}

	/**
	 * 衛星の現在位置からグリッドIDへ変換する。
	 *
	 * @param satLocation
	 *            衛星現在位置
	 * @return 天球上のグリッドID
	 */
	private String getGridIdOfCelestialSphere(double angle, double elevation) {
		int a = angle >= 360.0 ? (int)(angle - 360.0) : (int)angle;
		int el = elevation < 0 ? (int)(360.0 + elevation) : (int)elevation;
		return String.format("%03d%03d", a, el);
	}
}
