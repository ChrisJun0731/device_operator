package com.genture.device_operator;
/**
 * Created by zhuj@genture.com on 2017/07/06.
 */

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Item {
    Logger logger = Logger.getLogger(Item.class);

    private Long stayTime = 100L;
    private Integer inStyle = 1;
    private Integer outStyle = 1;
    private Integer inSpeed = 1;
    private Integer twinkle_speed = 0;
    private Integer twinkle_count =5;
    private Integer play_count = 1;
    private int gif_num;
    private int video_num;
    private int GIF_NUM_RESTRICT = 2;
    private int VIDEO_NUM_RESTRICT = 2;
    private List displayList;

    {
        this.displayList = new ArrayList();
    }

    public String toParamString(){
        String str = "";
        str += (stayTime== null? "": stayTime) + "," + (inStyle== null? "": inStyle) + "," +
                (outStyle== null? "": outStyle) + "," + (inSpeed== null? "": inSpeed) + "," +
                (twinkle_speed== null? "": twinkle_speed) + "," + (twinkle_count== null? "": twinkle_count)+ "," +
                (play_count== null? "": play_count) + "\n";
        return str;
    }

    public String toItemString(){
        String[] item_str = new String[displayList.size()];
        int gif_num = 0;
        int txt_num = 0;
        int video_num = 0;
        int img_num = 0;
        int txtext_num = 0;
        int timer_num = 0;
        for(int i=0; i<displayList.size(); i++){
            if(displayList.get(i) instanceof Txt){
                Txt txt = (Txt)displayList.get(i);
                txt_num++;
                item_str[i] = "txt" + txt_num + "=" + txt.toTxtString() + "txtparam" + txt_num + "=" +
                        txt.toTxtParamString();
            }
            else if(displayList.get(i) instanceof Gif){
                Gif gif = (Gif)displayList.get(i);
                gif_num++;
                item_str[i] = "gif"+ gif_num + "=" + gif.toGifString();
            }
            else if(displayList.get(i) instanceof Img){
                Img img = (Img)displayList.get(i);
                img_num++;
                item_str[i] = "img" + img_num + "=" + img.toImgStr() + "imgparam" + img_num + "=" +
                        img.toImgParamStr();
            }
            else if(displayList.get(i) instanceof Timer){
                Timer timer = (Timer)displayList.get(i);
                timer_num++;
                item_str[i] = "time" + timer_num + "=" + timer.toTimerString();
            }
            else if(displayList.get(i) instanceof Video){
                Video video = (Video)displayList.get(i);
                video_num++;
                item_str[i] = "video" + video_num + "=" + video.toVideoString();
            }
            else if(displayList.get(i) instanceof Txtext){
                Txtext txtext = new Txtext();
                txtext_num++;
                item_str[i] = "txtext" + txtext_num + "=" + txtext.toTxtextString();
            }
        }
        String item_string = "";
        for(int i=0; i<displayList.size(); i++){
            item_string += item_str[i];
        }
        return item_string;
    }

    public void removeDisplay(int index){
        displayList.remove(index);
    }

    public void addImg(Img img){
        displayList.add(img);
    }

    public void addGif(Gif gif){
        gif_num++;
        if(gif_num >= GIF_NUM_RESTRICT){
            logger.error("ÿ��item���ϴ���gif���ܳ���2����");
            return;
        }
        displayList.add(gif);
    }

    public void addTxt(Txt txt){
        displayList.add(txt);
    }

    public void addVideo(Video video){
        video_num++;
        if(video_num >= VIDEO_NUM_RESTRICT){
            logger.error("ÿ��item�ϴ���gif���ܳ���2����");
            return;
        }
        displayList.add(video);
    }

    public void addTxtext(Txtext txtext){
        displayList.add(txtext);
    }

    public void addTimer(Timer timer){
        displayList.add(timer);
    }

    public long getStayTime() {
        return stayTime;
    }

    public void setStayTime(long stayTime) {
        this.stayTime = stayTime;
    }

    public int getInStyle() {
        return inStyle;
    }

    public void setInStyle(int inStyle) {
        this.inStyle = inStyle;
    }

    public int getOutStyle() {
        return outStyle;
    }

    public void setOutStyle(int outStyle) {
        this.outStyle = outStyle;
    }

    public int getInSpeed() {
        return inSpeed;
    }

    public void setInSpeed(int inSpeed) {
        this.inSpeed = inSpeed;
    }

    public Integer getTwinkle_speed() {
        return twinkle_speed;
    }

    public void setTwinkle_speed(Integer twinkle_speed) {
        this.twinkle_speed = twinkle_speed;
    }

    public Integer getTwinkle_count() {
        return twinkle_count;
    }

    public void setTwinkle_count(Integer twinkle_count) {
        this.twinkle_count = twinkle_count;
    }

    public Integer getPlay_count() {
        return play_count;
    }

    public void setPlay_count(Integer play_count) {
        this.play_count = play_count;
    }

}