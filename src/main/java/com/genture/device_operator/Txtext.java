package com.genture.device_operator;
/***********************************************************************
 * Module:  Txtext.java
 * Author:  Administrator
 * Purpose: Defines the Class Txtext
 ***********************************************************************/

class Txtext {
   private Integer x;
   private Integer y;
   private Integer width;
   private Integer height;
   private Integer font;
   private Integer font_size;
   private Integer font_style;
   private Integer horizontal;
   private Integer vertical;
   private Integer line_space;
   private Integer char_space;
   private Integer foreground_color;
   private Integer background_color;
   private Integer play_se;
   private Integer se_speed;
   private Integer play_time;
   private Integer play_count;
   private String file_content;

   public String toTxtextString(){
      String str = "";
      str += (x== null? "":x)+ "," + (y== null? "": y)+ "," + (width== null? "": width)+ ","
              +(height== null? "": height) + "," + (font== null? "": font) + "," + (font_size== null? "": font_size)+
              "," + (font_style== null? "": font_style) + "," + (horizontal== null? "": horizontal)+ ","+
              (vertical== null? "": vertical)+ "," + (line_space== null? "": line_space) + ","
              + (char_space== null? "": char_space) + "," + (foreground_color== null? "": foreground_color)+ "," +
              (background_color== null? "": background_color) + "," + (play_se== null? "": play_se)+ "," +
              (se_speed== null? "": se_speed) + "," + (play_time== null? "": play_time)+ "," +
              (play_count== null? "": play_count) + "," + (file_content== null? "": file_content) + "\n";
      return str;
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getWidth() {
      return width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getFont() {
      return font;
   }

   public void setFont(int font) {
      this.font = font;
   }

   public int getFont_size() {
      return font_size;
   }

   public void setFont_size(int font_size) {
      this.font_size = font_size;
   }

   public int getFont_style() {
      return font_style;
   }

   public void setFont_style(int font_style) {
      this.font_style = font_style;
   }

   public int getHorizontal() {
      return horizontal;
   }

   public void setHorizontal(int horizontal) {
      this.horizontal = horizontal;
   }

   public int getVertical() {
      return vertical;
   }

   public void setVertical(int vertical) {
      this.vertical = vertical;
   }

   public int getChar_space() {
      return char_space;
   }

   public void setChar_space(int char_space) {
      this.char_space = char_space;
   }

   public int getLine_space() {
      return line_space;
   }

   public void setLine_space(int line_space) {
      this.line_space = line_space;
   }

   public int getForeground_color() {
      return foreground_color;
   }

   public void setForeground_color(int foreground_color) {
      this.foreground_color = foreground_color;
   }

   public int getBackground_color() {
      return background_color;
   }

   public void setBackground_color(int background_color) {
      this.background_color = background_color;
   }

   public int getPlay_se() {
      return play_se;
   }

   public void setPlay_se(int play_se) {
      this.play_se = play_se;
   }

   public int getSe_speed() {
      return se_speed;
   }

   public void setSe_speed(int se_speed) {
      this.se_speed = se_speed;
   }

   public int getPlay_time() {
      return play_time;
   }

   public void setPlay_time(int play_time) {
      this.play_time = play_time;
   }

   public int getPlay_count() {
      return play_count;
   }

   public void setPlay_count(int play_count) {
      this.play_count = play_count;
   }

   public String getFile_content() {
      return file_content;
   }

   public void setFile_content(String file_content) {
      this.file_content = file_content;
   }
}