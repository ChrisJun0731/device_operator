package com.genture.device_operator.playlist;

import com.genture.device_operator.playlist.params.Color;

/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Gif {
   private Integer x = 0;
   private Integer y = 0;
   private Integer width = 0;
   private Integer height = 0;
   private String filename = "";
   private Integer play_time = 100;
   private Integer play_count = 1;
   private Color background_color = Color.BLACK;

   String toGifString(){
      String gif_str = "";
      gif_str += x + "," + y + "," + width+ "," + height + "," + filename + "," + play_time +","
              + play_count + "," + background_color.ordinal()+1 + "\n";
      return gif_str;
   }

   /**
    * 获得Gif在显示屏上起始显示位置的横坐标
    * @return 横坐标
    */
   public int getX() {
      return x;
   }

   /**
    * 设置Gif在显示屏上起始显示位置的横坐标
    * @param x 横坐标
    */
   public void setX(int x) {
      this.x = x;
   }

   /**
    * 获得Gif在显示屏上起始显示位置的纵坐标
    * @return 纵坐标
    */
   public int getY() {
      return y;
   }

   /**
    * 设置Gif在显示屏上起始显示位置的横坐标
    * @param y 纵坐标
    */
   public void setY(int y) {
      this.y = y;
   }

   /**
    * 获得Gif在显示屏上显示的宽度 0表示全屏宽度
    * @return 显示宽度
    */
   public int getWidth() {
      return width;
   }

   /**
    * 设置Gif在显示屏上显示的宽度 0表示全屏宽度
    * @param width 显示宽度
    */
   public void setWidth(int width) {
      this.width = width;
   }

   /**
    * 获得Gif在显示屏上显示的高度 0表示全屏高度
    * @return 显示高度
    */
   public int getHeight() {
      return height;
   }

   /**
    * 设置Gif在显示屏上显示的宽度 0表示全屏高度
    * @param height 显示高度
    */
   public void setHeight(int height) {
      this.height = height;
   }

   /**
    * 获得Gif的文件名称
    * @return 文件名称
    */
   public String getFilename() {
      return filename;
   }

   /**
    * 设置Gif的文件名称
    * @param filename 文件名称
    */
   public void setFilename(String filename) {
      this.filename = filename;
   }

   /**
    * 获得Gif播放时长(单位0.1秒)
    * @return 播放时长
    */
   public int getPlay_time() {
      return play_time;
   }

   /**
    * 设置Gif的播放时长(单位0.1秒)
    * @param play_time 播放时长
    */
   public void setPlay_time(int play_time) {
      this.play_time = play_time;
   }

   /**
    * 获得Gif的播放次数 范围1-255
    * @return 播放次数
    */
   public int getPlay_count() {
      return play_count;
   }

   /**
    * 设置Gif的播放次数 范围1-255
    * @param play_count 播放次数
    */
   public void setPlay_count(int play_count) {
      this.play_count = play_count;
   }

   /**
    * 获取Gif显示时的背景颜色
    * @return 背景颜色
    */
   public Color getBackground_color() {
      return background_color;
   }

   /**
    * 设置Gif显示时的背景颜色
    * @param background_color 背景颜色
    */
   public void setBackground_color(Color background_color) {
      this.background_color = background_color;
   }
}