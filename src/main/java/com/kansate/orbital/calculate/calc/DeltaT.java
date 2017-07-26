package com.kansate.orbital.calculate.calc;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 観測時刻が元期から何日経過しているか、deltaTを求める
 */

public class DeltaT {
	
	//観測時刻が元期から何日経過しているか、deltaTを求める
	
	//年の下２桁を取得
	public int getYear(){
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		year = year - 2000;
		
		return year;
	}
	
	//１月１日から数えて何日目か
	public double getDayCount(){
		
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_YEAR);
	}
	
	//時間を取得
	public double getTime(){
		
		Calendar c = Calendar.getInstance();
		BigDecimal hour = new BigDecimal(c.get(Calendar.HOUR_OF_DAY));
		hour = hour.add(new BigDecimal(-9)) ; //JSTからUTへ変換
		hour = hour.divide(new BigDecimal(24),9,BigDecimal.ROUND_HALF_UP); //時間を日に変換
		
		BigDecimal minute = new BigDecimal(c.get(Calendar.MINUTE));
		minute = minute.divide(new BigDecimal(1440),9,BigDecimal.ROUND_HALF_UP); //分を日に変換
		
		BigDecimal second = new BigDecimal(c.get(Calendar.SECOND));
		second = second.divide(new BigDecimal(86400),9,BigDecimal.ROUND_HALF_UP); //秒を日に変換
		
		BigDecimal time = new BigDecimal(0);
		time = time.add(hour);
		time = time.add(minute);
		time = time.add(second);
		
		return time.doubleValue();
		
	}
	
	//日にちと時間を合わせる
	public double getUt(){
		return getDayCount() + getTime();
	}
	
	//deltaTを求める
	public double getDelta(double epochTime){
		return getUt() + ((double)-1*epochTime);
		
	}
	
	public static void main(String[] args) {
		DeltaT e = new DeltaT();
		System.out.println(e.getDelta(120.72277529));
	}
	
	
}
