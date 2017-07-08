package com.genture.device_operator;
/***********************************************************************
 * Module:  Timer.java
 * Author:  Administrator
 * Purpose: Defines the Class Timer
 ***********************************************************************/

class Timer {
   private Integer x;
   private Integer y;
   private Integer width;
   private Integer height;
   private Integer play_time;
   private Integer foreground_color;
   private Integer background_color;
   private Integer font_size;
   private Integer font;
   private Integer font_style;
   private Integer full_year;
   private Integer date_format;
   private Integer year;
   private Integer month;
   private Integer day;
   private Integer hour;
   private Integer minute;
   private Integer second;
   private Integer week;
   private Integer single_line;
   private Integer play_count;

   public String toTimerString(){
      String str = "";
      str = (x== null? "": x) + "," + (y== null? "": y) + "," + (width==null? "": width) + "," +
              (height== null? "": height) + "," + (play_time== null? "": play_time) + "," +
              (foreground_color== null? "": foreground_color) + "," + (background_color== null? "": background_color) +
              "," + (font_size== null? "": font_size) + "," + (font== null? "": font) + "," +
              (font_style== null? "": font_style) + "," + (full_year== null? "": full_year) + "," +
              (date_format== null? "": date_format) + "," + (year== null? "": year) + "," +
              (month== null? "": month)+ "," + (day== null? "": day) + "," + (hour== null? "": hour) +
              "," + (minute== null? "": minute)+ "," + (second== null? "": second)+ "," + (week== null? "": week)+
              "," + (single_line== null? "": single_line) + "," + (play_count== null? "": play_count) + "\n";
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

   public int getForeground_color() {
      return foreground_color;
   }

   public void setForeground_color(int foreground_color) {
      this.foreground_color = foreground_color;
   }

   public int getBackground_color() {
      return background_color;
   }

   public void setBackground_color(int background_color) {
      this.background_color = background_color;
   }

   public int getFont_size() {
      return font_size;
   }

   public void setFont_size(int font_size) {
      this.font_size = font_size;
   }

   public int getFont() {
      return font;
   }

   public void setFont(int font) {
      this.font = font;
   }

   public int getFont_style() {
      return font_style;
   }

   public void setFont_style(int font_style) {
      this.font_style = font_style;
   }

   public int getFull_year() {
      return full_year;
   }

   public void setFull_year(int full_year) {
      this.full_year = full_year;
   }

   public Integer getDate_format() {
      return date_format;
   }

   public void setDate_format(Integer date_format) {
      this.date_format = date_format;
   }

   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public int getMonth() {
      return month;
   }

   public void setMonth(int month) {
      this.month = month;
   }

   public int getDay() {
      return day;
   }

   public void setDay(int day) {
      this.day = day;
   }

   public int getHour() {
      return hour;
   }

   public void setHour(int hour) {
      this.hour = hour;
   }

   public int getMinute() {
      return minute;
   }

   public void setMinute(int minute) {
      this.minute = minute;
   }

   public int getSecond() {
      return second;
   }

   public void setSecond(int second) {
      this.second = second;
   }

   public int getWeek() {
      return week;
   }

   public void setWeek(int week) {
      this.week = week;
   }

   public Integer getSingle_line() {
      return single_line;
   }

   public void setSingle_line(Integer single_line) {
      this.single_line = single_line;
   }

   public int getPlay_count() {
      return play_count;
   }

   public void setPlay_count(int play_count) {
      this.play_count = play_count;
   }
}