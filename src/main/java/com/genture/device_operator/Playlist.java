package com.genture.device_operator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */
class Playlist {
	private List<Item> playlist;
	private int num = 0;

	{
		this.playlist = new ArrayList();
	}

	/**
	 * 播放列表字符串
	 */
	public String toPlayListString(){
		String prefix = "[all]\n"+ "items=" + num + "\n";
		String[] items_str = new String[playlist.size()];
		for(int i=0; i<playlist.size(); i++){
			Item item = playlist.get(i);
			items_str[i] = "";
			items_str[i] += "[item]" + i + "\n";
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
	 * 创建播放列表文件
	 */
	public void createPlayListFile(){
		String playlist_str = toPlayListString();
		int i = 1;
		String filename = "playlist"+ i +".lst";
		File file = new File("E:\\temp\\playlist",filename);
		while(file.exists() && i<=100){
			i++;
			filename = "playlist"+i+".lst";
			file = new File("E:\\temp\\playlist", filename);
		}
		try{
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(playlist_str);
			bufferedWriter.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void addItem(Item item){
		playlist.add(item);
		num++;
	}

	public void removeItem(int index){
		playlist.remove(index);
		num--;
	}

	public List<Item> getList() {
		return playlist;
	}

	public void setList(List<Item> playlist) {
		this.playlist = playlist;
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
