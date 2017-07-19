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

   String toImgStr(){
      String str = "";
      str = x+ "," + y + "," + filename+ "," + twinkle + "," + width
              + "," + height + "\n";
      return str;
   }

   String toImgParamStr(){
      String str = "";
      str += stayTime + "\n";
      return str;
   }

   /**
    * 获得Img在显示屏上起始显示位置的横坐标
    * @return 横坐标
    */
   public int getX() {
      return x;
   }

   /**
    * 设置Img在显示屏上起始显示位置的横坐标
    * @param x 横坐标
    */
   public void setX(int x) {
      this.x = x;
   }

   /**
    * 获得Img在显示屏上起始显示位置的纵坐标
    * @return 纵坐标
    */
   public int getY() {
      return y;
   }

   /**
    * 设置Img在显示屏上起始显示位置的纵坐标
    * @param y 纵坐标
    */
   public void setY(int y) {
      this.y = y;
   }

   /**
    * 获得图片文件的名称
    * @return 文件名称
    */
   public String getFilename() {
      return filename;
   }

   /**
    * 设置图片文件的名称
    * @param filename 文件名称
    */
   public void setFilename(String filename) {
      this.filename = filename;
   }

   /**
    * 获得图片在屏上显示的宽度 0-表示源图片的宽度
    * @return 图片宽度
    */
   public int getWidth() {
      return width;
   }

   /**
    * 设置图片在显示屏上显示的宽度 0-表示源图片的宽度
    * @param width 图片宽度
    */
   public void setWidth(int width) {
      this.width = width;
   }

   /**
    * 获得图片在屏上显示的高度 0-表示源图片的高度
    * @return 图片高度
    */
   public int getHeight() {
      return height;
   }

   /**
    * 设置图片在显示屏上显示的高度 0-表示源图片的高度
    * @param height 图片高度
    */
   public void setHeight(int height) {
      this.height = height;
   }

   /**
    * 获得图片在屏上的停留时间 单位0.1秒
    * @return 停留时间
    */
   public int getStayTime() {
      return stayTime;
   }

   /**
    * 设置图片在屏上的停留时间 单位0.1秒
    * @param stayTime 停留时间
    */
   public void setStayTime(int stayTime) {
      this.stayTime = stayTime;
   }
}