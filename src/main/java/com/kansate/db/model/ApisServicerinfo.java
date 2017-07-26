package com.kansate.db.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_servicerinfo")
public class ApisServicerinfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="name", nullable = false)
	public String name;

	@IQColumn(name="regist_time", length=200, nullable = false)
	public Timestamp registTime;

	@IQColumn(name="is_del", nullable = false, defaultValue="0")
	public Integer isDel;
	
	public ApisServicerinfo() {
	}
}
