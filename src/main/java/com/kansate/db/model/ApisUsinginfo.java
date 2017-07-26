package com.kansate.db.model;

import java.io.Serializable;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_usinginfo")
public class ApisUsinginfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="device_id", nullable = false)
	public Integer deviceId;

	@IQColumn(name="servicer_id", nullable = false)
	public Integer servicerId;

	@IQColumn(name="user_id", nullable = false)
	public Integer userId;

	public ApisUsinginfo() {
	}
}
