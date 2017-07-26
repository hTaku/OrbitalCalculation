package com.kansate;

import org.apache.commons.lang.ArrayUtils;

import com.kansate.calculate.Calculator;
import com.kansate.collect.Collector;

public class Run {

	public static void main(String[] args) {
		// collectモード
		if (ArrayUtils.contains(args, "-collect")) {
			if (ArrayUtils.getLength(args) == 2) {
				String dirPath = args[1];
				Collector exec = new Collector();
				exec.execute(dirPath);
			} else {
				System.out.println("Illegal argument error.");
				System.out.println("java -jar OrbitalCalculation.jar -collect [TLE DirPath]");
			}
		} else if (ArrayUtils.contains(args, "-calc")) {
			Calculator calc = new Calculator();
			calc.setup();
			calc.run();
		}
	}

}
