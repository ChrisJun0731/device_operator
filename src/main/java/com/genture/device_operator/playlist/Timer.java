package com.genture.device_operator.playlist;


import com.genture.device_operator.playlist.params.*;

/**
 * Created by zhuj@genture.com on 2017/07/06.
 */
public class Timer {
   private Integer x = 0;
   private Integer y = 0;
   private Integer width = 0;
   private Integer height = 0;
   private Integer play_time = 100;
   private Color foreground_color = Color.RED;
   private Color background_color = Color.BLACK;
   private Integer font_size = 1616;
   private Font font = Font.BLACK;
   private Fontstyle font_style = Fontstyle.NORMAL;
   private Display full_year_display = Display.SHOW;
   private DateFormat date_format = DateFormat.YEAR_MONTH_DAY;
   private Display year_display = Display.SHOW;
   private Display month_display = Display.SHOW;
   private Display day_display = Display.SHOW;
   private Display hour_display = Display.SHOW;
   private Display minute_display = Display.SHOW;
   private Display second_display = Display.SHOW;
   private Display week_display = Display.SHOW;
   private SingleLine single_line = SingleLine.YES;
   private Integer play_count = 1;

   public String toTimerString(){
      String str = "";
      str = x + "," + y + "," + width + "," + height + "," + play_time + "," + (foreground_color.ordinal()+1)
              + "," + (background_color.ordinal()+1) + "," + font_size + "," + (font.ordinal()+1) + ","
              + font_style.ordinal() + "," + full_year_display.ordinal() + "," + date_format.ordinal() + ","
              + year_display.ordinal() + "," + month_display.ordinal() + "," + day_display.ordinal() + ","
              + hour_display.ordinal() + "," + minute_display.ordinal() + "," + second_display.ordinal() + ","
              + week_display.ordinal() + "," + single_line.ordinal() + "," + play_count + "\n";
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

   public int getPlay_time() {
      return play_time;
   }

   public void setPlay_time(int play_time) {
      this.play_time = play_time;
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

   public Fontstyle getFont_style() {
      return font_style;
   }

   public void setFont_style(Fontstyle font_style) {
      this.font_style = font_style;
   }

   public Display getFull_year_display() {
      return full_year_display;
   }

   public void setFull_year_display(Display full_year_display) {
      this.full_year_display = full_year_display;
   }

   public DateFormat getDate_format() {
      return date_format;
   }

   public void setDate_format(DateFormat date_format) {
      this.date_format = date_format;
   }

   public Display getYear_display() {
      return year_display;
   }

   public void setYear_display(Display year_display) {
      this.year_display = year_display;
   }

   public Display getMonth_display() {
      return month_display;
   }

   public void setMonth_display(Display month_display) {
      this.month_display = month_display;
   }

   public Display getDay_display() {
      return day_display;
   }

   public void setDay_display(Display day_display) {
      this.day_display = day_display;
   }

   public Display getHour_display() {
      return hour_display;
   }

   public void setHour_display(Display hour_display) {
      this.hour_display = hour_display;
   }

   public Display getMinute_display() {
      return minute_display;
   }

   public void setMinute_display(Display minute_display) {
      this.minute_display = minute_display;
   }

   public Display getSecond_display() {
      return second_display;
   }

   public void setSecond_display(Display second_display) {
      this.second_display = second_display;
   }

   public Display getWeek_display() {
      return week_display;
   }

   public void setWeek_display(Display week_display) {
      this.week_display = week_display;
   }

   public SingleLine getSingle_line() {
      return single_line;
   }

   public void setSingle_line(SingleLine single_line) {
      this.single_line = single_line;
   }

   public Integer getPlay_count() {
      return play_count;
   }

   public void setPlay_count(Integer play_count) {
      this.play_count = play_count;
   }
}