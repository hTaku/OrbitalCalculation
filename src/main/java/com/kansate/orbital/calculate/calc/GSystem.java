package com.kansate.orbital.calculate.calc;


public class GSystem {
	
	//グリニッジ子午線の方向をx軸とする座標に変換する
	//三次元回転行列の計算   Tg = 観測時刻のグリニッジ恒星時
	
	public double getXg(double x,double y,double gst){		
		//xgの計算
		//xg = x*cosTg + y*sinTg;
		double xg = x*Math.cos(gst) + y*Math.sin(gst);
		
		return xg;
	}
	
	public double getYg(double x,double y,double gst){
		//ygの計算
		//yg = -x*sinTg + y*cosTg
		double yg = -1.0*x*Math.sin(gst) + y*Math.cos(gst);
		
		return yg;
	}
	
	public double getZg(double z){
		//zg = z
		return z;		
	}
	
	//観測点を基準にした衛星の位置と距離
	//G系地心直交座標に変換した衛星の位置　xg,xy,xz
	//観測点の位置 xmg,ymg,zmg
	//観測点から見た衛星の位置 xr,yr,zr と距離 Er
	
	//観測点の位置をビッグサイトに固定する
	double xmg = -3947.12247529274;
	double ymg = 3431.58399731201;
	double zmg = 3638.19104008208;
	
	public double getXr(double xg){
		//xr = xg - xmg
		double xr = xg - xmg;
		return xr;		
	}
	
	public double getYr(double yg){
		//yr = yg - ymg
		double yr = yg - ymg;
		return yr;
	}
	
	public double getZr(double zg){
		//zr = zg - zmg
		double zr = zg - zmg;
		return zr;
	}
	
	//現在地の緯度、経度
	//緯度 = 35°　　経度 = 139°
	double ido = 0.610865238;
	double keido = 2.42600766;
	
	
	//経度に対する座標回転(x1,y1,z1)を計算する
	
	public double getX1(double xr,double yr){
		//x1 = xr*cos(経度) + yr*sin(経度)
		double x1 = xr*Math.cos(keido) + yr*Math.sin(keido);
		return x1;
	}
	
	public double getY1(double xr,double yr){
		//y1 = xr*-sin(経度) + yr*cos(経度)
		double y1 = xr*Math.sin(keido)*-1.0 + yr*Math.cos(keido);
		return y1;
	}
	
	//z1 = zr
	
	
	//緯度に対する座標回転(x2,y2,z2)を計算する
	
	public double getX2(double x1, double zr){
		//x2 = x1*sin(緯度) - z1*cos(緯度)
		double x2 = x1*Math.sin(ido) - zr*Math.cos(ido);
		return x2;
	}
	
	//y2 = y1
	
	public double getZ2(double x1, double zr){
		//z2 = x1*cos(緯度) + z1*sin(緯度)
		double z2 = x1*Math.cos(ido) + zr*Math.sin(ido);
		return z2;
	}
	

}
