package com.genture.device_operator;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhuj@genture.com on 2017/6/15.
 */
class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	/**
	 * ��һ����buf�洢������ת��Ϊint
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
	 * ��һ��Ϊ��ֵ��byteת��Ϊ�������ڶ�Ӧ������
	 * @param num
	 * @return
	 */
	public int mapByte2Int(byte num){
		return (num+256)%256;
	}

	/**
	 * ��һ���������ߵ�λ�洢
	 * @param result
	 * @param crc
	 * @param index
	 */
	public void int2buf(int result, byte[]crc, int index){
		crc[index] = (byte)(result%256);
		crc[index+1] = (byte)(result/256);
	}

	/**
	 * ��ԭת����������
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
	 * ��ip���ַ���ת��Ϊbyte����
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
	 * ��byte����ת��Ϊip��ַ���ַ�����ʽ
	 * @param ip
	 * @return
	 */
	public String byte2ip(byte[] ip){
		String result = mapByte2Int(ip[0])+ "."+ mapByte2Int(ip[1])+"."+ mapByte2Int(ip[2])+ "."
				+ mapByte2Int(ip[3]);
		return result;
	}

	/**
	 * ������ʱ�ļ�������д�����ͼƬ��
	 */
	public File createTempFile(){
		String path = "E:/capture";
		File directory = new File(path);
		if(!directory.exists()){
			directory.mkdir();
		}
		String filename = "";
		filename = Calendar.YEAR + "" + Calendar.MONTH + "" + Calendar.DAY_OF_MONTH + ""
				+ Calendar.HOUR  + "" + Calendar.MINUTE + "" + Calendar.SECOND
				+ "" + Calendar.MILLISECOND;
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
