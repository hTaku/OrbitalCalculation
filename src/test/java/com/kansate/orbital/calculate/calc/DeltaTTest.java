package com.kansate.orbital.calculate.calc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.joda.time.DateTime;
import org.junit.Test;

public class DeltaTTest {

	@Test
	public void testGetDelta() {
		DeltaT deltaT = new DeltaT();
		deltaT.injectCalender(DateTime.parse("2016-01-01T00:00:00").toCalendar(Locale.JAPAN));
		assertThat(deltaT.getDelta(120.72277529), is(-120.09777529));
	}

}
