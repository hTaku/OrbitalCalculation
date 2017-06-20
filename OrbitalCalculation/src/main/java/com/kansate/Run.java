package com.kansate;

import com.kansate.execute.ExecuteCalcuration;

public class Run {

	public static void main(String[] args) {
		ExecuteCalcuration exec = new ExecuteCalcuration();
		// TODO スレッドの個数はプロパティファイルから取得する
		exec.setup(3);
		exec.execute();
		exec.teardown();
	}

}
