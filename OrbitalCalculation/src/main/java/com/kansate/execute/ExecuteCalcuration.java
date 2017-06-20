/**
 *
 */
package com.kansate.execute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;
import com.kansate.orbital.tle.element.OrbitalElementEntity;
import com.kansate.orbital.tle.file.TleReader;

/**
 *
 */
public class ExecuteCalcuration {

	private int threadCount = 2;

	private ExecutorService executorService;

	private Calculator[] calculators;

	public ExecuteCalcuration() {
	}

	public void setup(int threadCount) {
		this.threadCount = threadCount;
		this.executorService = Executors.newFixedThreadPool(this.threadCount);

		List<List<OrbitalElementEntity>> orbitalElementEntityListList = getOrbitalElementEntity(this.threadCount);

		this.calculators = new Calculator[this.threadCount];
		for (int i = 0; i < this.threadCount; i++) {
			this.calculators[i] = new Calculator();
			this.calculators[i].setup(orbitalElementEntityListList.get(i));
		}
	}

	public void execute() {
		Arrays.stream(this.calculators).forEach(calculator -> this.executorService.execute(calculator));
	}

	public void teardown() {
		if (!this.executorService.isShutdown()) {
			this.executorService.shutdown();
		}
	}
	private List<List<OrbitalElementEntity>> getOrbitalElementEntity(int threadCount) {
		List<List<OrbitalElementEntity>> resultListList = Lists.newArrayList();
		for(int i = 0; i < threadCount; i++) {
			ArrayList<OrbitalElementEntity> resultList = Lists.newArrayList();
			resultListList.add(resultList);
		}
		resultListList.forEach(resultList -> resultList = Lists.newArrayList());

		TleReader reader = new TleReader();
		// TODO: ファイル名取得メソッドは後ほど作成
		reader.load("./src/test/resources/com/wooperworld/orbital/tle/file/TleReaderTestData/EarthResources.txt");
		int i = 0;
		while(reader.hasNext()){
			OrbitalElementEntity entity = reader.next();
			List<OrbitalElementEntity> resultList = resultListList.get(i);
			resultList.add(entity);
			resultListList.set(i, resultList);
			++i;
			if(i == resultListList.size()) {
				i = 0;
			}
		}
		return resultListList;
	}

}
