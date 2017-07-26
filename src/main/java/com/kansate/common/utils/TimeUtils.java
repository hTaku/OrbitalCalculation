package com.kansate.common.utils;

import java.sql.Timestamp;

import org.joda.time.DateTime;

public class TimeUtils {

	/**
	 * DateTime型をTimestamp型へ変換
	 * @param datetime DateTime型
	 * @return Timestamp型
	 */
	public static Timestamp toTimestamp(DateTime datetime){
		return new Timestamp(datetime.getMillis());
	}

	/**
	 * Timestamp型をDateTime型に変換
	 * @param timestamp Timestamp型
	 * @return DateTime型
	 */
	public static DateTime toDateTime(Timestamp timestamp) {
		return new DateTime().withMillis(timestamp.getTime());
	}
}
