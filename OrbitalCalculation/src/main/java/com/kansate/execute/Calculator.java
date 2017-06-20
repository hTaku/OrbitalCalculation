package com.kansate.execute;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.kansate.orbital.calculate.OrbitalCalculator;
import com.kansate.orbital.tle.element.OrbitalElementEntity;

public class Calculator extends Thread {

	/** 軌道計算の対象衛星リスト */
	private List<OrbitalElementEntity> targetList;

	public Calculator() {
	}

	/**
	 * 初期設定
	 *
	 * @param targetList
	 *            軌道計算対象の衛星情報
	 */
	public void setup(List<OrbitalElementEntity> targetList) {
		this.targetList = targetList;
	}

	/**
	 * 軌道計算を並列実行する
	 */
	@Override
	public void run() {
		Optional.ofNullable(this.targetList)
				.ifPresent(targetList -> targetList.forEach(target -> calculateAndSave(target)));
		super.run();
	}

	/**
	 * 軌道計算し保存する
	 *
	 * @param target
	 *            対象衛星情報
	 */
	private void calculateAndSave(OrbitalElementEntity target) {
		BigDecimal location = new OrbitalCalculator().execute(target);
		int gridId = getGridIdOfCelestialSphere(location);
		// Memcached.updateMemcached(gridId, target.getSatName());
	}

	/**
	 * 衛星の現在位置からグリッドIDへ変換する。
	 *
	 * @param satLocation
	 *            衛星現在位置
	 * @return 天球上のグリッドID
	 */
	private int getGridIdOfCelestialSphere(BigDecimal satLocation) {
		// TODO:
		return 1;
	}
}
