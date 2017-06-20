package com.kansate.common.definition;

public class DefinitionInfo {
	private int lineNum;

	private int start;

	private int end;

	private String regex;

	private int length;

	/**
	 * コンストラクタ(固定長文字列)
	 *
	 * @param lineNum 行番号(0始まり)
	 * @param start 開始インデックス
	 * @param end 終了インデックス
	 */
	public DefinitionInfo(int lineNum, int start, int end) {
		this.lineNum = lineNum;
		this.start = start;
		this.end = end;
	}

	/**
	 * コンストラクタ(可変長文字列)
	 * @param lineNum 行番号(0始まり)
	 * @param start 開始インデックス
	 * @param regex 最終文字(開始インデックス以降最初にこの文字が見つかった位置まで文字を取得する)
	 */
	public DefinitionInfo(int lineNum, int start, String regex) {
		this.lineNum = lineNum;
		this.start = start;
		this.regex = regex;
	}

	public int getLineNum() {
		return this.lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return this.end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRegex() {
		return this.regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
}
