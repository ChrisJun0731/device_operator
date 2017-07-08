package com.genture.device_operator; /***********************************************************************
 * Module:  Gif.java
 * Author:  Administrator
 * Purpose: Defines the Class Gif
 ***********************************************************************/

class Gif {
   private Integer x;
   private Integer y;
   private Integer width;
   private Integer height;
   private String filename;
   private Integer play_time;
   private Integer play_count;
   private Integer background_color;

   public String toGifString(){
      String gif_str = "";
      gif_str += (x== null? "": x) + "," + (y== null? "": y) + (width== null? "":width)+ ","
      + (height== null? "": height)+ "," + (filename== null? "": filename) + "," + (play_time== null? "": play_time)
              +"," + (play_count== null? "": play_count) + "," + (background_color== null? "": background_color)+ "\n";
      return gif_str;
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

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
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

   public int getBackground_color() {
      return background_color;
   }

   public void setBackground_color(int background_color) {
      this.background_color = background_color;
   }
}