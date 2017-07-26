package com.kansate.orbital.calculate.calc;


public class ThreeDimensionalCoordinates {
	
	//地球中心を原点とする三次元座標を求めるクラス
	
	//人工衛星の軌道面上の座標(U,V)を求める
	public double getCoordinateU(double a,double e2,double eccentricity){
		// U = acosE - ae
		double u = a*Math.cos(e2) - a*eccentricity;
		
	return u;
	}
	
	public double getCoordinateV(double a,double e2,double eccentricity){
		// V = a√1-e^2 sinE
		double roewot = 1.0-eccentricity*eccentricity;
		
		double v = a*Math.sqrt(roewot)*Math.sin(e2);
		
	return v;
	}
	
	//近地点引数ωを求める
	public double getArgumentOfPerigee(double inclinationAngle,double argumentOfAngle,double a,double dT){
		// ω　= ω0 + 180×0.174(2-2.5sini^2)/π(a/r)^3.5*deltaT
		//ω0をラジアンに変換する
		//argumentOfAngle = argumentOfAngle*Math.PI/180.0;
		//iをラジアンに変換する
		inclinationAngle = inclinationAngle*Math.PI/180.0;
		//sini^2
		inclinationAngle = Math.sin(inclinationAngle)*Math.sin(inclinationAngle);
		//(2-2.5sini^2)
		double kakkonai = 2.0-2.5*inclinationAngle;		
		//分子の計算
		double bunnsi = 180.0*0.174*kakkonai;
		//分母のa/r
		double r = 6378.16;
		double ar = a/r;
		//分母の計算
		double bunnbo = Math.PI*Math.pow(ar, 3.5);		
		double omegaA = argumentOfAngle + bunnsi/bunnbo*dT;			
		//ラジアンで返す
		omegaA = omegaA*Math.PI/180.0;
		return omegaA;
	}
	
	//昇交点赤経Ωを求める
	public double getRightAscensionOfAscendingNode(double inclinationAngle,double rightAscensionOfAscendingNode,double a,double dT){
		// Ω = Ω0 - 180×0.174cosi/π(a/r)^3.5*deltaT
		//iをラジアンに変換する
		inclinationAngle = inclinationAngle*Math.PI/180.0;
		//cosi
		inclinationAngle = Math.cos(inclinationAngle);
		//分子の計算
		double bunnsi = 180.0*0.174*inclinationAngle;
		//分母のa/r
		double r = 6378.16;
		double ar = a/r;
		//分母の計算
		double bunnbo = Math.PI*Math.pow(ar, 3.5);		
		double omegaB = rightAscensionOfAscendingNode - bunnsi/bunnbo*dT;
		//ラジアンで返す
		omegaB = omegaB*Math.PI/180.0;
		return omegaB;
	}
	
	//三次元回転行列の計算
	
	//xの計算
	// x = (cosΩcosω - sinΩcosisinω)U - (cosΩsinω + sinΩcosicosω)V
	public double getX(double u,double v,double smallOmega,double bigOmega,double inclinationAngle){
		//iをラジアンに変換する
		inclinationAngle = inclinationAngle*Math.PI/180.0;
		//左カッコを計算する
		double left = Math.cos(bigOmega)*Math.cos(smallOmega) - Math.sin(bigOmega)*Math.cos(inclinationAngle)*Math.sin(smallOmega);
		left = left*u;
		//右カッコを計算する
		double right = Math.cos(bigOmega)*Math.sin(smallOmega) + Math.sin(bigOmega)*Math.cos(inclinationAngle)*Math.cos(smallOmega);
		right = right*v;
		
		double x = left - right;
		return x;
	}
	
	//yの計算
	// y = (sinΩcosω + cosΩcosisinω)U - (sinΩsinω - cosΩcosicosω)V
	public double getY(double u,double v,double smallOmega,double bigOmega,double inclinationAngle){
		//iをラジアンに変換する
		inclinationAngle = inclinationAngle*Math.PI/180.0;
		//左カッコを計算する
		double left = Math.sin(bigOmega)*Math.cos(smallOmega) + Math.cos(bigOmega)*Math.cos(inclinationAngle)*Math.sin(smallOmega);
		left = left*u;
		//右カッコを計算する
		double right = Math.sin(bigOmega)*Math.sin(smallOmega) - Math.cos(bigOmega)*Math.cos(inclinationAngle)*Math.cos(smallOmega);
		right = right*v;
		
		double y = left - right;
		return y;		
	}
	
	//zの計算
	// z = (sinisinω)U + (sinicosω)V
	public double getZ(double u,double v,double smallOmega,double inclinationAngle){
		//iをラジアンに変換する
		inclinationAngle = inclinationAngle*Math.PI/180.0;
		//左カッコを計算する
		double left = Math.sin(inclinationAngle)*Math.sin(smallOmega);
		left = left*u;
		//右カッコを計算する
		double right = Math.sin(inclinationAngle)*Math.cos(smallOmega);
		right = right*v;
		
		double z = left + right;
		return z;
	}
	
	
}
