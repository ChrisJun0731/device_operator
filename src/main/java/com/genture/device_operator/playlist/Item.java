package com.genture.device_operator.playlist;
/**
 * Created by zhuj@genture.com on 2017/07/06.
 */

import com.genture.device_operator.playlist.params.InOutScreenMode;
import com.genture.device_operator.playlist.params.InSpeed;
import com.genture.device_operator.playlist.params.TwinkleSpeed;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Item {
    Logger logger = Logger.getLogger(Item.class);

    private Long stayTime = 100L;
    private InOutScreenMode inScreenMode = InOutScreenMode.IMMEDIATELY;
    private InOutScreenMode outScreenMode = InOutScreenMode.IMMEDIATELY;
    private InSpeed inSpeed = InSpeed.ONE;
    private TwinkleSpeed twinkle_speed = TwinkleSpeed.ZERO;
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

    String toParamString(){
        String str = "";
        str += stayTime + "," + (inScreenMode.ordinal()+1) + ","
                + (outScreenMode.ordinal()+1) + "," + (inSpeed.ordinal()+1)
                + "," + twinkle_speed.ordinal() + "," + twinkle_count+ "," +
                play_count + "\n";
        return str;
    }

    String toItemString(){
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
                Txtext txtext = (Txtext)displayList.get(i);
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

    /**
     * 删除Item中项（图片、文本，视频）
     * @param index 项的索引
     */
    public void removeDisplay(int index){
        displayList.remove(index);
    }

    /**
     * 向Item中添加图片
     * @param img 图片
     */
    public void addImg(Img img){
        displayList.add(img);
    }

    /**
     * 向Item中添加Gif
     * @param gif Gif
     */
    public void addGif(Gif gif){
        gif_num++;
        if(gif_num >= GIF_NUM_RESTRICT){
            logger.error("每个Item上gif的数量不能超过两个！");
            return;
        }
        displayList.add(gif);
    }

    /**
     * 向Item中添加文本
     * @param txt txt对象
     */
    public void addTxt(Txt txt){
        displayList.add(txt);
    }

    /**
     * 向Item中添加视频
     * @param video video对象
     */
    public void addVideo(Video video){
        video_num++;
        if(video_num >= VIDEO_NUM_RESTRICT){
            logger.error("每个Item上的视频文件不能超过两个！");
            return;
        }
        displayList.add(video);
    }

    /**
     * 向Item中添加Txtext
     * @param txtext txtext
     */
    public void addTxtext(Txtext txtext){
        displayList.add(txtext);
    }

    /**
     * 向Item中添加时间
     * @param timer timer对象
     */
    public void addTimer(Timer timer){
        displayList.add(timer);
    }

    /**
     * 获得在屏上的停留时间 单位0.1秒
     * @return 停留时间
     */
    public long getStayTime() {
        return stayTime;
    }

    /**
     * 设置在屏上的停留时间 单位0.1秒
     * @param stayTime 停留时间
     */
    public void setStayTime(long stayTime) {
        this.stayTime = stayTime;
    }

    /**
     * 获得入屏方式
     * @return 入屏方式
     */
    public InOutScreenMode getInScreenMode() {
        return inScreenMode;
    }

    /**
     * 设置入屏方式
     * @param inScreenMode 入屏方式
     */
    public void setInScreenMode(InOutScreenMode inScreenMode) {
        this.inScreenMode = inScreenMode;
    }

    /**
     * 设获得出屏方式
     * @return 出屏方式
     */
    public InOutScreenMode getOutScreenMode() {
        return outScreenMode;
    }

    /**
     * 设置出屏方式
     * @param outScreenMode 出屏方式
     */
    public void setOutScreenMode(InOutScreenMode outScreenMode) {
        this.outScreenMode = outScreenMode;
    }

    /**
     * 获得入屏速度
     * @return 入屏速度
     */
    public InSpeed getInSpeed() {
        return inSpeed;
    }

    /**
     * 设置入屏速度
     * @param inSpeed 入屏速度
     */
    public void setInSpeed(InSpeed inSpeed) {
        this.inSpeed = inSpeed;
    }

    /**
     * 获得闪烁速度
     * @return 闪烁速度
     */
    public TwinkleSpeed getTwinkle_speed() {
        return twinkle_speed;
    }

    /**
     * 设置闪烁速度
     * @param twinkle_speed 闪烁速度
     */
    public void setTwinkle_speed(TwinkleSpeed twinkle_speed) {
        this.twinkle_speed = twinkle_speed;
    }

    /**
     * 获得闪烁次数
     * @return 闪烁次数
     */
    public Integer getTwinkle_count() {
        return twinkle_count;
    }

    /**
     * 设置闪烁次数
     * @param twinkle_count 闪烁次数
     */
    public void setTwinkle_count(Integer twinkle_count) {
        this.twinkle_count = twinkle_count;
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