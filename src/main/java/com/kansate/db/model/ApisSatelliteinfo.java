package com.kansate.db.model;

import java.io.Serializable;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_satelliteinfo")
public class ApisSatelliteinfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="sat_name", length=200, nullable = false)
	public String satName;

	@IQColumn(name="epoch_year", nullable = false)
	public Integer epochYear;

	@IQColumn(name="epoch_time", nullable = false)
	public String epochTime;

	@IQColumn(name="argument_of_perigee", nullable = false)
	public Double argumentOfPerigee;

	@IQColumn(name="inclination_angle", nullable = false)
	public Double inclinationAngle;

	@IQColumn(name="right_ascension_of_ascending_node", nullable = false)
	public Double rightAscensionOfAscendingNode;

	@IQColumn(name="eccentricity", nullable = false)
	public Double eccentricity;

	@IQColumn(name="mean_anomaly", nullable = false)
	public Double meanAnomaly;

	@IQColumn(name="mean_motion", nullable = false)
	public Double meanMotion;

	@IQColumn(name="mean_motion_coefficient_change", nullable = false)
	public Double meanMotionCoefficientChange;

	@IQColumn(name="is_del", nullable = false, defaultValue="0")
	public Integer isDel;

	public ApisSatelliteinfo() {
	}

	@Override
	public String toString() {
		return "ApisSatelliteinfo [id=" + id + ", satName=" + satName + ", epochYear=" + epochYear + ", epochTime="
				+ epochTime + ", argumentOfPerigee=" + argumentOfPerigee + ", inclinationAngle=" + inclinationAngle
				+ ", rightAscensionOfAscendingNode=" + rightAscensionOfAscendingNode + ", eccentricity=" + eccentricity
				+ ", meanAnomaly=" + meanAnomaly + ", meanMotion=" + meanMotion + ", meanMotionCoefficientChange="
				+ meanMotionCoefficientChange + ", isDel=" + isDel + "]";
	}
}
