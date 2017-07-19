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

   String toTimerString(){
      String str = "";
      str = x + "," + y + "," + width + "," + height + "," + play_time + "," + (foreground_color.ordinal()+1)
              + "," + (background_color.ordinal()+1) + "," + font_size + "," + (font.ordinal()+1) + ","
              + (font_style.name()=="LINE_THROUGH"? 8:font_style.ordinal()) + "," + full_year_display.ordinal() + ","
              + date_format.ordinal() + "," + year_display.ordinal() + "," + month_display.ordinal() + ","
              + day_display.ordinal() + "," + hour_display.ordinal() + "," + minute_display.ordinal() + ","
              + second_display.ordinal() + "," + week_display.ordinal() + "," + single_line.ordinal() + ","
              + play_count + "\n";
      return str;
   }

   /**
    * 获得时钟媒体在显示屏上起始显示位置的横坐标
    * @return 横坐标
    */
   public int getX() {
      return x;
   }

   /**
    * 设置时钟每次在显示屏上起始显示位置的横坐标
    * @param x 横坐标
    */
   public void setX(int x) {
      this.x = x;
   }

   /**
    * 获得时钟媒体在显示屏上起始显示位置的纵坐标
    * @return 纵坐标
    */
   public int getY() {
      return y;
   }

   /**
    * 设置时钟每次在显示屏上起始显示位置的纵坐标
    * @param y 纵坐标
    */
   public void setY(int y) {
      this.y = y;
   }

   /**
    * 获得时钟媒体在显示屏上的显示宽度 0表示全屏宽度
    * @return 显示宽度
    */
   public int getWidth() {
      return width;
   }

   /**
    * 设置时钟媒体在显示屏上的显示宽度 0表示全屏宽度
    * @param width 显示宽度
    */
   public void setWidth(int width) {
      this.width = width;
   }

   /**
    * 获得时钟媒体在显示屏上的显示高度 0表示全屏高度
    * @return 显示高度
    */
   public int getHeight() {
      return height;
   }

   /**
    * 设置时钟媒体在显示屏上的显示高度 0表示全屏高度
    * @param height 显示高度
    */
   public void setHeight(int height) {
      this.height = height;
   }

   /**
    * 获得一次播放时长 单位0.1秒
    * @return 播放时长
    */
   public int getPlay_time() {
      return play_time;
   }

   /**
    * 设置一次播放时长 单位0.1秒
    * @param play_time 播放时长
    */
   public void setPlay_time(int play_time) {
      this.play_time = play_time;
   }

   /**
    * 获得字体
    * @return 字体
    */
   public int getFont_size() {
      return font_size;
   }

   /**
    * 设置字体
    * @param font_size 字体
    */
   public void setFont_size(int font_size) {
      this.font_size = font_size;
   }

   /**
    * 获得前景色
    * @return 前景色
    */
   public Color getForeground_color() {
      return foreground_color;
   }

   /**
    * 设置前景色
    * @param foreground_color 前景色
    */
   public void setForeground_color(Color foreground_color) {
      this.foreground_color = foreground_color;
   }

   /**
    * 获得背景色
    * @return 背景色
    */
   public Color getBackground_color() {
      return background_color;
   }

   /**
    * 设置背景色
    * @param background_color 背景色
    */
   public void setBackground_color(Color background_color) {
      this.background_color = background_color;
   }

   /**
    * 获得字体风格
    * @return 字体风格
    */
   public Fontstyle getFont_style() {
      return font_style;
   }

   /**
    * 设置字体风格
    * @param font_style 字体风格
    */
   public void setFont_style(Fontstyle font_style) {
      this.font_style = font_style;
   }

   /**
    * 获得是否四位年显示
    * @return 四位年显示
    */
   public Display getFull_year_display() {
      return full_year_display;
   }

   /**
    * 设置是或否四位年显示
    * @param full_year_display 四位年显示
    */
   public void setFull_year_display(Display full_year_display) {
      this.full_year_display = full_year_display;
   }

   /**
    * 获得日期格式
    * @return 日期格式
    */
   public DateFormat getDate_format() {
      return date_format;
   }

   /**
    * 设置日期格式
    * @param date_format 日期格式
    */
   public void setDate_format(DateFormat date_format) {
      this.date_format = date_format;
   }

   /**
    * 获得是否显示年
    * @return 是否显示年
    */
   public Display getYear_display() {
      return year_display;
   }

   /**
    * 设置是否显示年
    * @param year_display 是否显示年
    */
   public void setYear_display(Display year_display) {
      this.year_display = year_display;
   }

   /**
    * 获得是否显示月
    * @return 是否显示月
    */
   public Display getMonth_display() {
      return month_display;
   }

   /**
    * 设置是否显示月
    * @param month_display 是否显示月
    */
   public void setMonth_display(Display month_display) {
      this.month_display = month_display;
   }

   /**
    * 获得是否显示日
    * @return 是否显示日
    */
   public Display getDay_display() {
      return day_display;
   }

   /**
    * 设置是否显示日
    * @param day_display 是否显示日
    */
   public void setDay_display(Display day_display) {
      this.day_display = day_display;
   }

   /**
    * 获得是否显示时
    * @return 是否显示时
    */
   public Display getHour_display() {
      return hour_display;
   }

   /**
    * 设置是否显示时
    * @param hour_display 是否显示时
    */
   public void setHour_display(Display hour_display) {
      this.hour_display = hour_display;
   }

   /**
    * 获得是否显示分
    * @return 是否显示分
    */
   public Display getMinute_display() {
      return minute_display;
   }

   /**
    * 设置是否显示分
    * @param minute_display 是否显示分
    */
   public void setMinute_display(Display minute_display) {
      this.minute_display = minute_display;
   }

   /**
    * 获得是否显示秒
    * @return 是否显示秒
    */
   public Display getSecond_display() {
      return second_display;
   }

   /**
    * 设置是否显示秒
    * @param second_display 是否显示秒
    */
   public void setSecond_display(Display second_display) {
      this.second_display = second_display;
   }

   /**
    * 获得是否显示周
    * @return 是否显示周
    */
   public Display getWeek_display() {
      return week_display;
   }

   /**
    * 设置是否显示周
    * @param week_display 是否显示周
    */
   public void setWeek_display(Display week_display) {
      this.week_display = week_display;
   }

   /**
    * 获得是否单行显示
    * @return 是否单行显示
    */
   public SingleLine getSingle_line() {
      return single_line;
   }

   /**
    * 设置是否单行显示
    * @param single_line 单行显示
    */
   public void setSingle_line(SingleLine single_line) {
      this.single_line = single_line;
   }

   /**
    * 获得播放次数
    * @return 播放次数
    */
   public Integer getPlay_count() {
      return play_count;
   }

   /**
    * 设置播放次数
    * @param play_count 播放次数
    */
   public void setPlay_count(Integer play_count) {
      this.play_count = play_count;
   }
}