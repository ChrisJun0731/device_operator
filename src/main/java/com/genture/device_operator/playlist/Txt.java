package com.genture.device_operator.playlist;

import com.genture.device_operator.playlist.params.Color;
import com.genture.device_operator.playlist.params.Font;
import com.genture.device_operator.playlist.params.Fontstyle;
import com.genture.device_operator.playlist.params.QueueMode;

/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Txt{
   private Integer x = 0;
   private Integer y = 0;
   private Font font = Font.BLACK;
   private Integer font_size = 1616;
   private Color foreground_color = Color.RED;
   private Color background_color = Color.BLACK;
   private Integer twinkle = 0;
   private String content = "";
   private Integer width = 0;
   private Integer height = 0;
   private Fontstyle font_style = Fontstyle.NORMAL;
   private Integer char_space = 0;
   private QueueMode queue_mode = QueueMode.HORIZONTAL;

   private Util util = new Util();

   String toTxtString(){
      String txt = "";
      txt += x + "," + y + "," + (font.ordinal()+1) + "," + font_size + "," + (foreground_color.ordinal()+1)
              + "," + (background_color.ordinal()+1) + "," + twinkle + "," + util.convertText(content)
              + "," + width + "," + height + ","
              + (font_style.name()=="LINE_THROUGH"?8: font_style.ordinal()) + "\n";
      return txt;
   }

   String toTxtParamString(){
      String str = char_space + "," + queue_mode.ordinal() + "\n";
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

   public int getFont_size() {
      return font_size;
   }

   public void setFont_size(int font_size) {
      this.font_size = font_size;
   }

   public Color getForeground_color() {
      return foreground_color;
   }

   public void setForeground_color(Color foreground_color) {
      this.foreground_color = foreground_color;
   }

   public Color getBackground_color() {
      return background_color;
   }

   public void setBackground_color(Color background_color) {
      this.background_color = background_color;
   }

   /**
    * 获得显示的字符内容
    * @return 字符内容
    */
   public String getContent() {
      return content;
   }

   /**
    * 设置要显示的字符内容
    * @param content
    */
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

   public Font getFont() {
      return font;
   }

   public void setFont(Font font) {
      this.font = font;
   }

   public Fontstyle getFont_style() {
      return font_style;
   }

   public void setFont_style(Fontstyle font_style) {
      this.font_style = font_style;
   }

   /**
    * 获得字符间距
    * @return 字符间距 范围-1000到1000
    */
   public int getChar_space() {
      return char_space;
   }

   /**
    * 设置字符间距 范围-1000到1000
    * @param char_space 字符间距
    */
   public void setChar_space(int char_space) {
      this.char_space = char_space;
   }

   /**
    * 获得字符的排列方式
    * @return 排列方式
    */
   public QueueMode getQueue_mode() {
      return queue_mode;
   }

   /**
    * 设置字符的排列方式
    * @param queue_mode 排列方式
    */
   public void setQueue_mode(QueueMode queue_mode) {
      this.queue_mode = queue_mode;
   }
}