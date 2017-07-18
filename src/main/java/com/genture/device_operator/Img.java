package com.genture.device_operator;

/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Img {
   private Integer x;
   private Integer y;
   private String filename;
   private Integer twinkle;
   private Integer width;
   private Integer height;
   private Integer stayTime;

   public String toImgStr(){
      String str = "";
      str = (x== null? "":x)+ "," + (y== null? "": y) + "," + (filename== null? "":filename)+ "," + (twinkle== null? "": twinkle)
      + "," + (width== null? "": width) + "," + (height== null? "": height) + "\n";
      return str;
   }

   public String toImgParamStr(){
      String str = "";
      str += (stayTime== null? "": stayTime)+"\n";
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