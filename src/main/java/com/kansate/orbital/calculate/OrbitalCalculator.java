package com.kansate.orbital.calculate;

import java.math.BigDecimal;

import com.kansate.db.model.ApisSatelliteinfo;
import com.kansate.orbital.calculate.calc.DeltaT;
import com.kansate.orbital.calculate.calc.EccentricAnomaly;
import com.kansate.orbital.calculate.calc.GSystem;
import com.kansate.orbital.calculate.calc.GreenwichSideralTime;
import com.kansate.orbital.calculate.calc.HorizonSystem;
import com.kansate.orbital.calculate.calc.SemiMajorAxis;
import com.kansate.orbital.calculate.calc.ThreeDimensionalCoordinates;

public class OrbitalCalculator {
	/** 方位角 */
	private double angle;

	/** 仰角 */
	private double elevation;

	/**
	 * 計算を実行
	 * 
	 * @param satelliteInfo 衛星情報
	 */
	public void execute(ApisSatelliteinfo satelliteInfo) {
		DeltaT deltaT = new DeltaT();
		double epochTime = new BigDecimal(satelliteInfo.epochTime).doubleValue();
		double dT = deltaT.getDelta(new BigDecimal(epochTime).doubleValue());

		SemiMajorAxis semiMajorAxis = new SemiMajorAxis();
		double mM = semiMajorAxis.getMeanmotionM(satelliteInfo.meanMotion, satelliteInfo.meanMotionCoefficientChange,
				dT);
		double a = semiMajorAxis.getA(mM);

		EccentricAnomaly eccentricAnomaly = new EccentricAnomaly();
		double m0 = eccentricAnomaly.getM0(satelliteInfo.meanAnomaly, satelliteInfo.meanMotion,
				satelliteInfo.meanMotionCoefficientChange, dT);
		double e2 = eccentricAnomaly.getEccentricAnomaly(m0, satelliteInfo.eccentricity);

		ThreeDimensionalCoordinates threeDimensionalCoordinates = new ThreeDimensionalCoordinates();
		double u = threeDimensionalCoordinates.getCoordinateU(a, e2, satelliteInfo.eccentricity);
		double v = threeDimensionalCoordinates.getCoordinateV(a, e2, satelliteInfo.eccentricity);
		double smallOmega = threeDimensionalCoordinates.getArgumentOfPerigee(satelliteInfo.inclinationAngle,
				satelliteInfo.argumentOfPerigee, a, dT);
		double bigOmega = threeDimensionalCoordinates.getRightAscensionOfAscendingNode(satelliteInfo.inclinationAngle,
				satelliteInfo.rightAscensionOfAscendingNode, a, dT);
		double x = threeDimensionalCoordinates.getX(u, v, smallOmega, bigOmega, satelliteInfo.inclinationAngle);
		double y = threeDimensionalCoordinates.getY(u, v, smallOmega, bigOmega, satelliteInfo.inclinationAngle);
		double z = threeDimensionalCoordinates.getZ(u, v, smallOmega, satelliteInfo.inclinationAngle);

		GreenwichSideralTime greenwichSideralTime = new GreenwichSideralTime();
		double gst = greenwichSideralTime.getGst(epochTime, dT);

		GSystem gSystem = new GSystem();
		double xg = gSystem.getXg(x, y, gst);
		double yg = gSystem.getYg(x, y, gst);
		double zg = gSystem.getZg(z);
		double xr = gSystem.getXr(xg);
		double yr = gSystem.getYr(yg);
		double zr = gSystem.getZr(zg);
		double x1 = gSystem.getX1(xr, yr);
		double y1 = gSystem.getY1(xr, yr);
		double x2 = gSystem.getX2(x1, zr);
		double z2 = gSystem.getZ2(x1, zr);

		HorizonSystem horizonSystem = new HorizonSystem();
		this.angle = horizonSystem.getEaz(y1, x2);
		this.elevation = horizonSystem.getEel(xr, yr, zr, z2);
	}

	/**
	 * 方位角を取得
	 * 
	 * @return 方位角
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * 仰角を取得
	 * 
	 * @return 仰角
	 * 
	 */
	public double getElevation() {
		return elevation;
	}
	
	
}
