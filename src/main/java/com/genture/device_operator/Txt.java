package com.genture.device_operator;
/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Txt {
   private Integer x = 0;
   private Integer y = 0;
   private Integer font = 1;
   private Integer font_size = 1616;
   private Integer foreground_color = 1;
   private Integer background_color = 8;
   private Integer twinkle = 0;
   private String content = "";
   private Integer width = 0;
   private Integer height = 0;
   private Integer font_style = 0;
   private Integer char_space = 0;
   private Integer queue_mode = 0;

   public String toTxtString(){
      String txt = "";
      txt += (x== null? "": x) + "," + (y== null? "": y) + "," + (font== null? "": font) + "," + (font_size==null? "": font_size)
      + "," + (foreground_color== null? "": foreground_color) + "," + (background_color== null? "": background_color) + ","
              + (twinkle== null? "": twinkle) + "," + (content== null? "": content) + "," + (width== null? "": width)+ "," +
              (height== null? "": height) + "," + (font_style==null? "": font_style) + "\n";
      return txt;
   }

   public String toTxtParamString(){
      String str = (char_space== null? "": char_space) + "," + (queue_mode== null? "": queue_mode)+ "\n";
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

   public Integer getFont() {
      return font;
   }

   public void setFont(Integer font) {
      this.font = font;
   }

   public int getFont_size() {
      return font_size;
   }

   public void setFont_size(int font_size) {
      this.font_size = font_size;
   }

   public Integer getForeground_color() {
      return foreground_color;
   }

   public void setForeground_color(Integer foreground_color) {
      this.foreground_color = foreground_color;
   }

   public int getBackground_color() {
      return background_color;
   }

   public void setBackground_color(int background_color) {
      this.background_color = background_color;
   }

   public int getTwinkle() {
      return twinkle;
   }

   public void setTwinkle(int twinkle) {
      this.twinkle = twinkle;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
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

   public int getFont_style() {
      return font_style;
   }

   public void setFont_style(int font_style) {
      this.font_style = font_style;
   }

   public int getChar_space() {
      return char_space;
   }

   public void setChar_space(int char_space) {
      this.char_space = char_space;
   }

   public int getQueue_mode() {
      return queue_mode;
   }

   public void setQueue_mode(int queue_mode) {
      this.queue_mode = queue_mode;
   }

}