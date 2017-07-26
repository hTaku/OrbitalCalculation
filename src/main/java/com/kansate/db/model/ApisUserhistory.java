package com.kansate.db.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_userhistory")
public class ApisUserhistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="action_time", nullable = false)
	public Timestamp actionTime;
	
	@IQColumn(name="angle", nullable = false)
	public Double angle;

	@IQColumn(name="elevation", nullable = false)
	public Double elevation;

	@IQColumn(name="kanpai_result", nullable = false)
	public Integer kanpaiResult;

	@IQColumn(name="device_id", nullable = false)
	public Integer deviceId;

	@IQColumn(name="servicer_id", nullable = false)
	public Integer servicerId;

	@IQColumn(name="user_id", nullable = false)
	public Integer userId;
	
	public ApisUserhistory() {
	}
}
