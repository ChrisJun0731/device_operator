package com.genture.device_operator.playlist;

/**
 * Created by Administrator on 2017/7/19.
 */
class Util {
	/**
	 * 将播放列表中的文本内容进行转义
	 * @return 转义后的字符串
	 */
	public String convertText(String text) {
		text = text.replace("\\", "\\\\");
		text = text.replace(",", "\\,");
		text = text.replace("=", "\\=");
		return text;
	}
}
