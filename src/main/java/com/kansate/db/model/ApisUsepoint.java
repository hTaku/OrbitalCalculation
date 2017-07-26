package com.kansate.db.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_usepoint")
public class ApisUsepoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="pointLon", nullable = false)
	public Double pointLon;

	@IQColumn(name="point_lat", nullable = false)
	public Double pointLat;

	@IQColumn(name="start_time", nullable = false)
	public Timestamp startTime;

	@IQColumn(name="end_time", nullable = false)
	public Timestamp endTime;

	@IQColumn(name="servicer_id", nullable = false)
	public Integer servicerId;

	public ApisUsepoint() {
	}

}
