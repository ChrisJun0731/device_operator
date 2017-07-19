package com.genture.device_operator.playlist;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */
public class Playlist {
	private static Logger logger = Logger.getLogger(Playlist.class);

	private List<Item> playlist;
	private int num;

	{
		this.playlist = new ArrayList();
	}

	/**
	 * 获得播放列表的字符串
	 * @return 播放列表的字符串
	 */
	String toPlayListString(){
		String prefix = "[all]\n"+ "items=" + this.num + "\n";
		String[] items_str = new String[playlist.size()];
		for(int i=0; i<playlist.size(); i++){
			Item item = playlist.get(i);
			items_str[i] = "";
			items_str[i] += "[item" + (i+1) + "]\n";
			items_str[i] += "param=" + item.toParamString();
			items_str[i] += item.toItemString();
		}
		String playlist_str = "";
		playlist_str += prefix;
		for(int i=0; i< items_str.length; i++){
			playlist_str += items_str[i];
		}
		return playlist_str;
	}

	/**
	 * 创建播放列表文件 默认路径E:\temp\playlist
	 * @return 播放列表文件
	 */
	public File createPlayListFile(){
		String playlist_str = toPlayListString();
		int i = 1;
		String path = "E:\\temp\\playlist";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String filename = "play00"+ i +".lst";
		File file = new File(path ,filename);
		while(file.exists() && i<=100){
			i++;
			if(i>1 & i<10)
				filename = "play00"+ i + ".lst";
			else if(i<100)
				filename = "play0" + i + ".lst";
			else
				filename = "play" + i + ".lst";
			file = new File(path, filename);
		}
		try{
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(playlist_str);
			bufferedWriter.flush();
			logger.info("创建播放列表"+filename+"成功!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 向播放列表中添加Item
	 * @param item item
	 */
	public void addItem(Item item){
		this.playlist.add(item);
		this.num++;
	}

	/**
	 * 从播放列表中移除Item
	 * @param index item索引
	 */
	public void removeItem(int index){
		this.playlist.remove(index);
		this.num--;
	}

	int getNum() {
		return num;
	}

	void setNum(int num) {
		this.num = num;
	}
}
