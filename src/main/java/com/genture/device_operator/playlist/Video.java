package com.genture.device_operator.playlist;
/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Video {
   private Integer x = 0;
   private Integer y = 0;
   private Integer width = 0;
   private Integer height = 0;
   private String filename = "";
   private Integer play_count = 1;

   String toVideoString(){
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

   /**
    * 获得视频文件的名称
    * @return 文件名称
    */
   public String getFilename() {
      return filename;
   }

   /**
    * 设置视频文件的名称
    * @param filename 文件名称
    */
   public void setFilename(String filename) {
      this.filename = filename;
   }

   /**
    * 获得视频播放次数
    * @return
    */
   public int getPlay_count() {
      return play_count;
   }

   /**
    * 设置视频播放次数
    * @param play_count
    */
   public void setPlay_count(int play_count) {
      this.play_count = play_count;
   }
}