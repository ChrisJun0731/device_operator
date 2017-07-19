package com.genture.device_operator.playlist;

import com.genture.device_operator.playlist.params.*;

/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Txtext {
   private Integer x = 0;
   private Integer y = 0;
   private Integer width = 0;
   private Integer height = 0;
   private Font font = Font.BLACK;
   private Integer font_size;
   private Fontstyle font_style = Fontstyle.NORMAL;
   private Align horizontal_align = Align.LEFT;
   private Align vertical_align = Align.LEFT;
   private Integer line_space = 1;
   private Integer char_space = 0;
   private Color foreground_color = Color.RED;
   private Color background_color = Color.BLACK;
   private SpecialEffect specialEffect = SpecialEffect.NONE;
   private SpecialEffectSpeed specialEffectSpeed = SpecialEffectSpeed.NORMAL;
   private Integer play_time = 100;
   private Integer play_count = 1;
   private String content = "";

   private Util util = new Util();

   public String toTxtextString(){
      String str = "";
      str += x+ "," + y+ "," + width+ "," + height + "," + font + "," + font_size + ","
              + font_style.ordinal() + "," + horizontal_align.ordinal() + ","+ vertical_align.ordinal()
              + "," + line_space + "," + char_space + "," + (foreground_color.ordinal()+1) + "," +
              (background_color.ordinal()+1) + "," + (specialEffect.name()=="Random"?255: specialEffect.ordinal())
              + "," + specialEffectSpeed.ordinal() + "," + play_time + "," + play_count + ","
              + util.convertText(content) + "\n";
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

   public int getFont_size() {
      return font_size;
   }

   public void setFont_size(int font_size) {
      this.font_size = font_size;
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

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
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

   public Align getHorizontal_align() {
      return horizontal_align;
   }

   public void setHorizontal_align(Align horizontal_align) {
      this.horizontal_align = horizontal_align;
   }

   public Align getVertical_align() {
      return vertical_align;
   }

   public void setVertical_align(Align vertical_align) {
      this.vertical_align = vertical_align;
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

   public SpecialEffect getSpecialEffect() {
      return specialEffect;
   }

   public void setSpecialEffect(SpecialEffect specialEffect) {
      this.specialEffect = specialEffect;
   }

   public SpecialEffectSpeed getSpecialEffectSpeed() {
      return specialEffectSpeed;
   }

   public void setSpecialEffectSpeed(SpecialEffectSpeed specialEffectSpeed) {
      this.specialEffectSpeed = specialEffectSpeed;
   }
}