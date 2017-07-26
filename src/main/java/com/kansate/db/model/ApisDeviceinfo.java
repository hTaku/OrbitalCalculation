package com.kansate.db.model;

import java.io.Serializable;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_deviceinfo")
public class ApisDeviceinfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public ApisDeviceinfo() {
	}

	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="name", length = 200, nullable = false)
	public String name;

	@IQColumn(name="detail", length = 1000, nullable = false)
	public String detail;

	@IQColumn(name="type", length = 10, nullable = false)
	public String type;

	@IQColumn(name="status", length = 10, nullable = false)
	public String status;
}
