package com.kansate.orbital.calculate.calc;


public class HorizonSystem {
	//方位角、仰角を求めるクラス
	
	public double getEaz(double y1,double x2){
		//方位角(南が0で時計回りに2πまで)
		//Eaz = arctan(-y2/x2)
		double eaz = -1.0*y1/x2;
		eaz = Math.atan(eaz);
		
		//度 = rad*180/π
		eaz = eaz*180.0/Math.PI;
		
		if(x2 >= 0){
			return eaz;
		}else{
			eaz = 360.0 + eaz;
			return eaz;
		}		
	}
	
	public double getEel(double xr,double yr,double zr,double z2){
		//仰角(地平線が0で天頂のπ/2まで)
		//E1 = z2/Er
		//Er = (xr^2 + yr^2 + zr^2)^0.5
		double er = xr*xr + yr*yr + zr*zr;
		er = Math.pow(er, 0.5);
		
		double e1 = z2/er;
		
		//Eel = arctan(E1/(-E1*E1 + 1)^0.5)
		double eel = -1.0*e1*e1 + 1;
		eel = Math.pow(eel, 0.5);
		eel = e1/eel;
		eel = Math.atan(eel);
		
		//度 = rad*180/π
		eel = eel*180.0/Math.PI;
		
		return eel;
		
	}
	

}
