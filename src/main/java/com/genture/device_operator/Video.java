package com.genture.device_operator;
/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Video {
   private Integer x;
   private Integer y;
   private Integer width;
   private Integer height;
   private String filename;
   private Integer play_count;

   public String toVideoString(){
      String str = "";
      str += (x== null? "": x) + "," + (y== null? "": y) + "," + (width== null? "": width) + "," +
              (height== null? "": height) + "," + (filename== null? "": filename) + "," +
              (play_count== null? "": play_count)+ "\n";
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

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public int getPlay_count() {
      return play_count;
   }

   public void setPlay_count(int play_count) {
      this.play_count = play_count;
   }
}