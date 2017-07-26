package com.kansate.orbital.calculate.calc;


public class EccentricAnomaly {
	
	//M = E-esinE
	//Mは平均近点角。軌道要素で与えられているのは元期の平均近点角であるため、
	//平均運動の値とその変化係数を用いて計算する。
	
	//M = M0/360 + M1△t + 1/2M2△t^2
	
	public double getM0(double meanAnomaly,double meanMotion1,double meanMotion2,double dT){
		
		double meanAnomaly1 = meanAnomaly*1.0/360.0 + meanMotion1*dT + 1.0/2.0*meanMotion2*dT*dT;
		int copyMeanAnomaly = (int) meanAnomaly1;
		meanAnomaly1 = meanAnomaly1 - copyMeanAnomaly;
		meanAnomaly1 = meanAnomaly1*360.0;
		
		return meanAnomaly1;
	}
	
	
	//f(E) = M - E+esinE     f(E) = 0 を満たすEを求める
	//f'(E) = ecosE-1
	
	public double getEccentricAnomaly(double m0,double eccentricity){
		//m0の値をラジアンに変換する（M）
		double radM = m0*Math.PI/180;
		//初期値E0のラジアン
		double radE0 = radM;
		
		for(int n=0; n<3; n++){
		double fE = radM - radE0 + eccentricity*Math.sin(radE0);
		double fdE = eccentricity*Math.cos(radE0)-1;
		
		double deltaE0 = fE/fdE;
		double e1 = radE0 - deltaE0;
		
		radE0 = e1;
		}
		//Eの値を角度に戻す
		//double angleE = radE0*180/Math.PI;
		
		return radE0;
		
	}

}
