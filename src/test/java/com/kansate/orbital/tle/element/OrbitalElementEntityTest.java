package com.kansate.orbital.tle.element;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.kansate.orbital.tle.element.OrbitalElementEntity;

public class OrbitalElementEntityTest {

	private OrbitalElementEntity target = new OrbitalElementEntity();

	@Test
	public void testParse() {
		// ----------------------
		// init
		String[] lines = new String[OrbitalElementEntity.SAT_INFO_LINE_COUNT];
		lines[0] = "SCD 1";
		lines[1] = "1 22490U 93009B   16312.21363867  .00000252  00000-0  19253-4 0  9990";
		lines[2] = "2 22490  24.9694 230.7457 0043220 151.4740   1.3972 14.44463939253474";

		// ----------------------
		// execute
		target.parse(lines);

		// ----------------------
		// verify
		assertThat(target.getSatName(), is("SCD 1"));
	}

}
