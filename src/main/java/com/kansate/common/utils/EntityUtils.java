package com.kansate.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.kansate.common.definition.DefinitionInfo;

public class EntityUtils {

	/**
	 * 指定された文字列配列から特定箇所の文字列を取得。
	 *
	 * @param lines
	 *            対象文字列の配列
	 * @param definitionInfo
	 *            取得箇所情報
	 * @return 指定された特定箇所の文字列
	 */
	public static String getItem(String[] lines, DefinitionInfo definitionInfo) {
		String line = lines[definitionInfo.getLineNum()];
		if (StringUtils.isNotEmpty(definitionInfo.getRegex())) {
			int end = StringUtils.substring(line, definitionInfo.getStart()).indexOf(definitionInfo.getRegex());
			if (end == -1) {
				end = line.length();
			}
			definitionInfo.setEnd(end);
		}

		String item = StringUtils.substring(line, definitionInfo.getStart(), definitionInfo.getEnd());
		return StringUtils.trim(item);
	}
}
