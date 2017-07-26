package com.kansate.db.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_userinfo")
public class ApisUserinfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="regist_time", nullable = false)
	public Timestamp registTime;

	@IQColumn(name="use_count", nullable = false)
	public Integer useCount;
	
	public ApisUserinfo() {
	}
}
