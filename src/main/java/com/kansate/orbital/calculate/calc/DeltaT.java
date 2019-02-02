package com.kansate.orbital.calculate.calc;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 観測時刻が元期から何日経過しているか、deltaTを求める
 */
public class DeltaT {
	
	/** カレンダー */
	private Calendar cal;
	
	/**
	 * コンストラクタ
	 */
	public DeltaT() {
		this.cal = Calendar.getInstance();
	}
	
	/**
	 * カレンダーを設定する。
	 * @param cal 設定するカレンダー
	 */
	public void injectCalender(Calendar cal) {
		this.cal = cal;
	}

	/**
	 * deltaTを求める
	 * @return deltaT
	 */
	public double getDelta(double epochTime){
		return (getDayCount() + getTime()) + ((double)-1*epochTime);
	}

	/**
	 * 年の下２桁を取得
	 * @return 年の下２桁
	 */
	@Deprecated
	@SuppressWarnings(value = { "unused" })
	private int getYear(){		
		return this.cal.get(Calendar.YEAR) - 2000;
	}

	/**
	 * １月１日からの日数
	 * @return 1月1日からの日数を取得
	 */
	private double getDayCount(){		
		return this.cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 時間を取得
	 * @return 時間を取得
	 */
	private double getTime(){
		BigDecimal hour = new BigDecimal(this.cal.get(Calendar.HOUR_OF_DAY));
		hour = hour.add(new BigDecimal(-9)) ; //JSTからUTへ変換
		hour = hour.divide(new BigDecimal(24),9,BigDecimal.ROUND_HALF_UP); //時間を日に変換

		BigDecimal minute = new BigDecimal(this.cal.get(Calendar.MINUTE));
		minute = minute.divide(new BigDecimal(1440),9,BigDecimal.ROUND_HALF_UP); //分を日に変換

		BigDecimal second = new BigDecimal(this.cal.get(Calendar.SECOND));
		second = second.divide(new BigDecimal(86400),9,BigDecimal.ROUND_HALF_UP); //秒を日に変換

		BigDecimal time = new BigDecimal(0).add(hour).add(minute).add(second);

		return time.doubleValue();
	}

	public static void main(String[] args) {
		DeltaT e = new DeltaT();
		System.out.println(e.getDelta(120.72277529));
	}
}
