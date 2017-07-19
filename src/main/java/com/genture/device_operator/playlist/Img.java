package com.genture.device_operator.playlist;

/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Img {
   private Integer x = 0;
   private Integer y = 0;
   private String filename = "";
   private Integer twinkle = 0;
   private Integer width = 0;
   private Integer height = 0;
   private Integer stayTime = 100;

   public String toImgStr(){
      String str = "";
      str = x+ "," + y + "," + filename+ "," + twinkle + "," + width
              + "," + height + "\n";
      return str;
   }

   public String toImgParamStr(){
      String str = "";
      str += stayTime + "\n";
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

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public int getTwinkle() {
      return twinkle;
   }

   public void setTwinkle(int twinkle) {
      this.twinkle = twinkle;
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

   public int getStayTime() {
      return stayTime;
   }

   public void setStayTime(int stayTime) {
      this.stayTime = stayTime;
   }
}