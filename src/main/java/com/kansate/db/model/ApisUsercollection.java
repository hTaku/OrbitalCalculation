package com.kansate.db.model;

import java.io.Serializable;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "apis_usercollection")
public class ApisUsercollection implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IQColumn(primaryKey = true, nullable = false)
	public Integer id;

	@IQColumn(name="sat_id", nullable = false)
	public Integer satId;

	@IQColumn(name="user_id", nullable = false)
	public Integer userId;

	@IQColumn(name="user_history_id", nullable = false)
	public Integer userHistoryId;

	public ApisUsercollection() {
	}
}
