package com.kansate.orbital.tle.element;

import java.math.BigDecimal;

import org.apache.commons.lang.ArrayUtils;

import com.kansate.common.exception.ApplicationException;
import com.kansate.common.utils.EntityUtils;

/**
 * 軌道要素
 *
 */
public class OrbitalElementEntity {
	/** 1衛星分の行数 */
	public static int SAT_INFO_LINE_COUNT = 3;

	/** 元期(年)*/
	private int epochYear;

	/** 元期(ET)*/
	private String epochTime;

	/** 近地点引数(ω) */
	private BigDecimal argumentOfPerigee;

	/** 軌道傾斜角(i) */
	private BigDecimal inclinationAngle;

	/** 昇交点赤経(Ω) */
	private BigDecimal rightAscensionOfAscendingNode;

	/** 離心率(e) */
	private BigDecimal eccentricity;

	/** 平均近点角(M0) */
	private BigDecimal meanAnomaly;

	/** 平均運動(M1) */
	private BigDecimal meanMotion;

	/** 平均運動変化係数(M2) */
	private BigDecimal meanMotionCoefficientChange;

	/** 衛星名 */
	private String satName;

	/**
	 * 元期(年)を取得する。
	 *
	 * @return 元期(年)
	 */
	public int getEpochYear() {
		return epochYear;
	}
	/**
	 * 元期(ET)を取得する。
	 *
	 * @return 元期(ET)
	 */
	public String getEpochTime() {
		return epochTime;
	}

	/**
	 * 近地点引数(ω)を取得する。
	 *
	 * @return 近地点引数(ω)
	 */
	public BigDecimal getArgumentOfPerigee() {
		return argumentOfPerigee;
	}

	/**
	 * 軌道傾斜角(i)を取得する。
	 *
	 * @return 軌道傾斜角(i)
	 */
	public BigDecimal getInclinationAngle() {
		return inclinationAngle;
	}

	/**
	 * 昇交点赤経(Ω)を取得する。
	 *
	 * @return 昇交点赤経(Ω)
	 */
	public BigDecimal getRightAscensionOfAscendingNode() {
		return rightAscensionOfAscendingNode;
	}

	/**
	 * 離心率(e)を取得する。
	 *
	 * @return 離心率(e)
	 */
	public BigDecimal getEccentricity() {
		return eccentricity;
	}

	/**
	 * 平均近点角(M0)を取得する。
	 *
	 * @return 平均近点角(M0)
	 */
	public BigDecimal getMeanAnomaly() {
		return meanAnomaly;
	}

	/**
	 * 平均運動(M1)を取得する。
	 *
	 * @return 平均運動(M1)
	 */
	public BigDecimal getMeanMotion() {
		return meanMotion;
	}

	/**
	 * 平均運動変化係数(M2)を取得する。
	 *
	 * @return 平均運動変化係数(M2)
	 */
	public BigDecimal getMeanMotionCoefficientChange() {
		return meanMotionCoefficientChange;
	}

	/**
	 * 衛星名を取得する。
	 *
	 * @return 衛星名
	 */
	public String getSatName() {
		return satName;
	}

	/**
	 * TLE 1衛星分の情報を解析する。
	 *
	 * @param lines 1衛星分のTLE情報
	 */
	public void parse(String[] lines) {
		verify(lines);

		this.setSatName(EntityUtils.getItem(lines, OrbitalElementDefinition.SAT_NAME));
		this.setEpochYear(Integer.valueOf(EntityUtils.getItem(lines, OrbitalElementDefinition.EPOCH_YEAR)));
		this.setEpochTime(EntityUtils.getItem(lines, OrbitalElementDefinition.EPOCH_TIME));
		this.setArgumentOfPerigee(new BigDecimal(EntityUtils.getItem(lines, OrbitalElementDefinition.ARGUMENT_OF_PERIGEE)));
		this.setInclinationAngle(new BigDecimal(EntityUtils.getItem(lines, OrbitalElementDefinition.INCLINATION_ANGLE)));
		this.setRightAscensionOfAscendingNode(new BigDecimal(EntityUtils.getItem(lines, OrbitalElementDefinition.RIGHT_ASCENSION_OF_ASCENDING_NODE)));
		this.setEccentricity(new BigDecimal("0." + EntityUtils.getItem(lines, OrbitalElementDefinition.ECCENTRICITY)));
		this.setMeanAnomaly(new BigDecimal(EntityUtils.getItem(lines, OrbitalElementDefinition.MEAN_ANOMALY)));
		this.setMeanMotion(new BigDecimal(EntityUtils.getItem(lines, OrbitalElementDefinition.MEAN_MOTION)));
		this.setMeanMotionCoefficientChange(new BigDecimal("0" + EntityUtils.getItem(lines, OrbitalElementDefinition.MEAN_MOTION_COEFFICIENT_CHANGE)));
	}

	/**
	 * TLE 1衛星分の情報を検証する。
	 *
	 * @param lines 1衛星分のTLE情報
	 */
	public void verify(String[] lines) {
		if(ArrayUtils.getLength(lines) != SAT_INFO_LINE_COUNT) {
			throw new ApplicationException("TLEの1衛星の行数は" + SAT_INFO_LINE_COUNT + "です。");
		}
	}

	private void setSatName(String satName) {
		this.satName = satName;
	}

	private void setEpochYear(int epochYear) {
		this.epochYear = epochYear;
	}

	private void setEpochTime(String epochTime) {
		this.epochTime = epochTime;
	}

	private void setArgumentOfPerigee(BigDecimal argumentOfPerigee) {
		this.argumentOfPerigee = argumentOfPerigee;
	}

	private void setInclinationAngle(BigDecimal inclinationAngle) {
		this.inclinationAngle = inclinationAngle;
	}

	private void setRightAscensionOfAscendingNode(BigDecimal rightAscensionOfAscendingNode) {
		this.rightAscensionOfAscendingNode = rightAscensionOfAscendingNode;
	}

	private void setEccentricity(BigDecimal eccentricity) {
		this.eccentricity = eccentricity;
	}

	private void setMeanAnomaly(BigDecimal meanAnomaly) {
		this.meanAnomaly = meanAnomaly;
	}

	private void setMeanMotion(BigDecimal meanMotion) {
		this.meanMotion = meanMotion;
	}

	private void setMeanMotionCoefficientChange(BigDecimal meanMotionCoefficientChange) {
		this.meanMotionCoefficientChange = meanMotionCoefficientChange;
	}
}
