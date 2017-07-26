package com.kansate.orbital.calculate.calc;

/**
 * 軌道長半径を求める
 */
public class SemiMajorAxis {
	
	//ケプラー第３法則から計算する　　　a^3/T^2 = GM/4π^2
	//公転周期Tは平均運動M1（一日に公転を何回するか）の逆数（ある数に掛け算した結果が１となる数。）
	//     a = (GM/4π^2M1^2)^1/3
	
	//平均運動は毎日変化している
	//観測時刻の平均運動Mmは
	//元期の平均運動M1+平均運動変化係数M2×元期からの経過日数DeltaT
	
	public double getMeanmotionM(double meanMotion1,double meanMotion2,double dT){
		return meanMotion1 + meanMotion2 *dT;
	}
	
	//万有引力定数（G）、主星の質量（M）
	//地球周回軌道の人工衛星の場合は下記
	
	double gM = 2975537000000000.00;

	// a = (GM/4π^2M1^2)^1/3
	
	double pi = Math.PI*Math.PI*4;
	
	public double getA(double mM){
		double kakkonai = gM / (pi * (mM * mM));
		double a = Math.pow(kakkonai, 1.0/3.0);
		return a;
	
	}

}
