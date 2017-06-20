/**
 *
 */
package com.kansate.orbital.tle.file;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.kansate.orbital.tle.element.OrbitalElementEntity;

import junit.framework.TestCase;

/**
 * @author owner
 *
 */
public class TleReaderTest extends TestCase {

	private TleReader target = new TleReader();

	/**
	 * {@link com.kansate.orbital.tle.file.TleReader#Load(java.lang.String)} のためのテスト・メソッド。
	 */
	public void testLoad() {
		// ---------------------
		// init

		// ---------------------
		// execute
		target.load("./src/test/resources/com/kansate/orbital/tle/file/TleReaderTestData/EarthResources.txt");

		// ---------------------
		// verify
		assertTrue(target.hasNext());

		{
			OrbitalElementEntity actual = target.next();
			assertThat(actual.getSatName(), is("SCD"));
		}
		{
			OrbitalElementEntity actual = target.next();
			assertThat(actual.getSatName(), is("TECHSAT"));
		}
	}

}
