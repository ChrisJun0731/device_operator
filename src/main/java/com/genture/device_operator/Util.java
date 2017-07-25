package com.genture.device_operator;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by zhuj@genture.com on 2017/6/15.
 */
class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	/**
	 * 将一个按buf存储的整数转化为int
	 * @param low
	 * @param high
	 * @return
	 */
	public int buf2int(byte low, byte high){
		int low_int = (low+256)%256;
		int high_int = (high+256)%256;
		return low_int + 256*high_int;
	}

	/**
	 * 将一个为负值的byte转化为其周期内对应的正数
	 * @param num
	 * @return
	 */
	public int mapByte2Int(byte num){
		return (num+256)%256;
	}

	/**
	 * 将一个整数按高低位存储
	 * @param result
	 * @param crc
	 * @param index
	 */
	public void int2buf(int result, byte[]crc, int index){
		crc[index] = (byte)(result%256);
		crc[index+1] = (byte)(result/256);
	}

	/**
	 * 还原转义后的数据流
	 * @param data
	 * @return
	 */
	public byte[] decompile(byte[] data){
		Byte[] rec_data = new Byte[data.length];
		for(int i=0; i<rec_data.length; i++){
			rec_data[i] = data[i];
		}
		List<Byte> list = Arrays.asList(rec_data);
		List<Byte> rec_data_list = new ArrayList(list);
		for(int i=0; i<rec_data_list.size(); i++){
			if(mapByte2Int(rec_data_list.get(i))== 0xee){
				if(mapByte2Int(rec_data_list.get(i+1))== 0x0a){
					rec_data_list.set(i, (byte)0xaa);
					rec_data_list.remove(i+1);
				}
				else if(mapByte2Int(rec_data_list.get(i+1))== 0x0c){
					rec_data_list.set(i, (byte)0xcc);
					rec_data_list.remove(i+1);
				}
				else if(mapByte2Int(rec_data_list.get(i+1))== 0x0e)
				{
					rec_data_list.set(i, (byte)0xee);
					rec_data_list.remove(i+1);
				}
			}
		}
		int size = rec_data_list.size();
		Byte[] result_array = new Byte[size];
		rec_data_list.toArray(result_array);
		byte[] result = new byte[result_array.length];
		for(int i=0; i<result.length; i++){
			result[i] = result_array[i];
		}
		return result;
	}

	/**
	 * 将ip的字符串转换为byte数据
	 * @param ip
	 * @return
	 */
	public byte[] ip2byte(String ip){
		String[] array = ip.split("\\.");
		int[] middle = new int[array.length];
		for(int i=0; i<array.length; i++){
			middle[i] = Integer.parseInt(array[i]);
		}
		byte[] result = new byte[array.length];
		for(int i=0; i<array.length; i++){
			result[i] = (byte)middle[i];
		}
		return result;
	}

	/**
	 * 将byte数组转换为ip地址的字符串形式
	 * @param ip
	 * @return
	 */
	public String byte2ip(byte[] ip){
		String result = mapByte2Int(ip[0])+ "."+ mapByte2Int(ip[1])+"."+ mapByte2Int(ip[2])+ "."
				+ mapByte2Int(ip[3]);
		return result;
	}

	/**
	 * 创建临时文件，用来写入截屏图片流
	 */
	public File createTempFile(String capturePath){
		File directory = new File(capturePath);
		if(!directory.exists()){
			directory.mkdir();
		}
		String filename = "";
		Calendar now = Calendar.getInstance();
		filename = now.get(Calendar.YEAR) + "" + (now.get(Calendar.MONTH)+1) + "" + now.get(Calendar.DAY_OF_MONTH) + ""
				+ now.get(Calendar.HOUR)  + "" + now.get(Calendar.MINUTE) + "" + now.get(Calendar.SECOND)
				+ "" + now.get(Calendar.MILLISECOND);
		String suffix = ".bmp";
		File file = new File(directory,filename+suffix);
		if(!file.exists()){
			try{
				file.createNewFile();
			}
			catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return file;
	}
}
