package com.kansate.common.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.kansate.common.definition.DefinitionInfo;

public class EntityUtilsTest {
	
	@Test
	public void testGetItem() {
		// init
		String[] lines = new String[3];;
		lines[0] = "TECHSAT 1B (GO-32)";
		lines[1] = "1 25397U 98043D   16312.09892097  .00000008  00000-0  22469-4 0  9992";
		lines[2] = "2 25397  98.5830 253.4288 0000449 348.6978  11.4191 14.23607548952198";
		
		// execute
		String actual = EntityUtils.getItem(lines, new DefinitionInfo(1, 20, 32));
		
		// verify
		assertThat(actual, is("312.09892097"));
	}

}
