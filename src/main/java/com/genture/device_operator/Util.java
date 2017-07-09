package com.genture.device_operator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhuj@genture.com on 2017/6/15.
 */
class Util {
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
		List<Byte> rec_data_list = Arrays.asList(rec_data);
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
		String[] array = ip.split(".");
		byte[] result = new byte[array.length];
		for(int i=0; i<array.length; i++){
			result[i] = Byte.parseByte(array[i]);
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

}
