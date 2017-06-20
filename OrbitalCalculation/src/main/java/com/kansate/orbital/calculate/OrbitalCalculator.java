package com.kansate.orbital.calculate;

import java.math.BigDecimal;

import com.kansate.orbital.tle.element.OrbitalElementEntity;

public class OrbitalCalculator {

	public BigDecimal execute(OrbitalElementEntity target) {
		BigDecimal result = new BigDecimal(100);
		System.out.println("sat[" + target.getSatName() + "]");
		// TODO 軌道計算をおこなう
		return result;
	}

}
