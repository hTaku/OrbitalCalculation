package com.kansate.orbital.tle.element;

import com.kansate.common.definition.DefinitionInfo;

class OrbitalElementDefinition {
	/** 衛星名 */
	public static DefinitionInfo SAT_NAME = new DefinitionInfo(0, 0, "\n");

	/** 元期(年) */
	public static DefinitionInfo EPOCH_YEAR = new DefinitionInfo(1, 18, 20);

	/** 元期(ET) */
	public static DefinitionInfo EPOCH_TIME = new DefinitionInfo(1, 20, 33);

	/** 近地点引数(ω) */
	public static DefinitionInfo ARGUMENT_OF_PERIGEE = new DefinitionInfo(2, 34, 42);

	/** 軌道傾斜角(i) */
	public static DefinitionInfo INCLINATION_ANGLE = new DefinitionInfo(2, 8, 16);

	/** 昇交点赤経(Ω) */
	public static DefinitionInfo RIGHT_ASCENSION_OF_ASCENDING_NODE = new DefinitionInfo(2, 17, 25);

	/** 離心率(e) */
	public static DefinitionInfo ECCENTRICITY = new DefinitionInfo(2, 26, 33);

	/** 平均近点角(M0) */
	public static DefinitionInfo MEAN_ANOMALY = new DefinitionInfo(2, 43, 51);

	/** 平均運動(M1) */
	public static DefinitionInfo MEAN_MOTION = new DefinitionInfo(2, 52, 63);

	/** 平均運動変化係数(M2) */
	public static DefinitionInfo MEAN_MOTION_COEFFICIENT_CHANGE = new DefinitionInfo(1, 33, 43);
}
