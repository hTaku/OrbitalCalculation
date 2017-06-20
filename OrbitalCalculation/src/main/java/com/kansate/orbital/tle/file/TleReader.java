/**
 *
 */
package com.kansate.orbital.tle.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.kansate.common.exception.ApplicationException;
import com.kansate.orbital.tle.element.OrbitalElementEntity;

/**
 * TLEファイルを読み込む
 */
public class TleReader {

	private Map<String, OrbitalElementEntity> satMap;

	private Iterator<OrbitalElementEntity> satIte;

	/**
	 * コンストラクタ
	 */
	public TleReader() {
		this.satMap = new HashMap<String, OrbitalElementEntity>();
		this.satIte = this.satMap.values().iterator();
	}

	/**
	 * TLEファイル読み込み
	 *
	 * @param filePath
	 *            ファイルパス
	 */
	public void load(String filePath) {
		LineIterator lineIte = null;
		try {
			lineIte = FileUtils.lineIterator(new File(filePath), "UTF-8");
			String[] lines = new String[OrbitalElementEntity.SAT_INFO_LINE_COUNT];
			int lineCount = 0;
			while (lineIte.hasNext()) {
				lines[lineCount] = lineIte.nextLine();
				++lineCount;
				if (lineCount == OrbitalElementEntity.SAT_INFO_LINE_COUNT) {
					OrbitalElementEntity element = new OrbitalElementEntity();
					element.parse(lines);
					this.satMap.put(element.getSatName(), element);
					lineCount = 0;
				}
			}
			this.satIte = this.satMap.values().iterator();
		} catch (IOException e) {
			throw new ApplicationException(e);
		} finally {
			LineIterator.closeQuietly(lineIte);
		}
	}

	/**
	 * 次の衛星情報はあるか
	 *
	 * @return 次の衛星情報が存在する場合はtrue、存在しない場合はfalse
	 */
	public boolean hasNext() {
		return satIte.hasNext();
	}

	/**
	 * 次の衛星情報を取得する。
	 *
	 * @return 次の衛星情報
	 */
	public OrbitalElementEntity next() {
		return satIte.next();
	}

	/**
	 * TLEに登録されている衛星数を取得する。
	 *
	 * @return TLEに登録されている衛星数
	 */
	public int getSatCount() {
		if (MapUtils.isEmpty(this.satMap)) {
			throw new ApplicationException("TLEデータが読み込まれていません。");
		}
		return this.satMap.size();
	}
}
