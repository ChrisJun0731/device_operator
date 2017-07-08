package com.genture.device_operator;

/**
 * Created by Administrator on 2017/7/5.
 */
public class Test {
	public static void main(String[] args){
		Txt txt = new Txt();
		Util util = new Util();
//		String str = util.convertToString(txt.getX(),txt.getY());
//		System.out.println(str);
		txt.setX(1);
		txt.setY(2);
		txt.setBackground_color(5);
		txt.setScroll_direction(4);
		String str = txt.toTxtString();
		System.out.println(str);
	}
}
