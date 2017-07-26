package com.kansate.orbital.calculate.calc;


public class GreenwichSideralTime {
	
	//観測時刻のグリニッジ恒星時を求めるクラス
	
/*   2017年1月1日の0時0分0秒のグリニッジ恒星時(UTC = +9 , 東経139°)
	 GST = 6h39m25.5s
	 グリニッジ恒星時を秒に直す
	   60*60*6 + 60*39 + 25.5 = 23965.5
	 1秒の回転数　= 0.0041780740
	 0.0041780740*23965.5 = 100.1296324
	 地球の一日の回転度　= 360.9856472°
	 グリニッジ恒星時の回転度
	 100.1296324/360.9856472 = 0.277378431 = θ0	
*/
	
	//観測時刻のグリニッジ恒星時に直す
	public double getGst(double epochTime,double dT){
		
		//元期からの経過日数 - 1日 = Δt
		double deltaT = epochTime + dT -1.0;
		
		//地球の一日の回転数 = 1.002737909
		//θG = θ0 + 1.002737909*deltaT
		double thetaG = 0.277378431 + 1.002737909*deltaT;
		
		//360をかけて角度に直す
		int copyThetaG = (int)thetaG;
		thetaG = thetaG - copyThetaG;
		thetaG = thetaG*360.0;
		
		//ラジアンに直す
		thetaG = thetaG*Math.PI/180.0;
		
		return thetaG;

	}
}
