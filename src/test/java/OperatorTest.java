
import com.genture.device_operator.*;
import com.genture.device_operator.playlist.*;
import com.genture.device_operator.playlist.params.*;
import org.junit.*;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017/6/13.
 */
public class OperatorTest {
	private DeviceOperator deviceOperator;
	@Before
	public void setUp(){
//		Device device = new Device("10.100.15.15", 5000);
//		Device device = new Device("192.168.0.220", 5000);
//		Device device = new Device("192.168.0.1", 5000);
//		Device device = new Device("192.168.50.225", 5000);
//		Device device = new Device("192.168.30.123",5000);
//		Device device = new Device("172.16.1.214", 5000);
		Device device = new Device("192.168.50.2", 5000);
		this.deviceOperator = new DeviceOperator(device);
	}

	@Test
	public void openScreenTest(){
		this.deviceOperator.openScreen();
	}

	@Test
	public void closeSreenTest(){
		this.deviceOperator.closeScreen();
	}

	@Test
	public void getDateAndTimeTest(){
		String data_json = deviceOperator.getDateAndTime();
		System.out.println(data_json);
	}

	@Test
	public void getScreenSizeTest(){
		String screen_size_json = deviceOperator.getScreenSize();
		System.out.println(screen_size_json);
	}

	@Test
	public void restartDeviceTest(){
		deviceOperator.restartDevice();
	}

	@Test
	public void setCloseScreenTemperatureTest(){
		deviceOperator.setCloseScreenTemperature(35);
	}

	@Test
	public void getCloseScreenTemperatureTest(){
		int temperature = deviceOperator.getCloseScreenTemperature();
		System.out.println(temperature);
	}

	@Test
	public void getEnvironmentTemperatureTest(){
		int temperature = deviceOperator.getEnvironmentTemperature();
		System.out.println(temperature);
	}

	@Test
	public void getDoorStateTest(){
		String state = deviceOperator.getDoorState();
		System.out.println(state);
	}

	@Test
	public void setVirtualConnTest(){
		deviceOperator.setVirtualConn(1,10,0);
	}

	@Test
	public void getTotalBadPointTest(){
		int bad_point_num = deviceOperator.getTotalBadPoint();
		System.out.println(bad_point_num);
	}

	@Test
	public void sendFileTest(){
//		String filePath = "C:\\Users\\Administrator\\Desktop\\新建文本文档.lst";
//		String filePath = "C:\\Users\\Administrator\\Desktop\\诺瓦交通协议标准版 V3.2.0\\金晓交通通信协议.doc";
//		String filePath = "C:\\Users\\Administrator\\Desktop\\诺瓦交通协议标准版 V3.2.0\\jar包接口测试用例.doc";
//		String filePath = "F:\\pics\\3.avi";
		String filePath = "F:\\pics\\huluwa.gif";
		deviceOperator.sendFile(filePath);
	}

	@Test
	public void setLightByHandTest(){
		//设置的值超出范围
//		deviceOperator.setLightByHand(355);

		//设置的值正常
		deviceOperator.setLightByHand(100);
	}

	//二期新增jar包功能测试

	@Test
	public void captureScreenTest(){
		deviceOperator.captureScreen();
	}

	@Test
	public void clearAllFilesTest(){
		deviceOperator.clearAllFiles();
	}

	@Test
	public void clearInvalidFiles(){
		deviceOperator.clearInvalidFiles();
	}

	@Test
	public void playAssignedListTest(){
		int assigned = 5;
		deviceOperator.playAssignedList(assigned);
	}

	@Test
	public void setBasicParamTest(){
		BasicParam basicParam = new BasicParam();
		basicParam.setIp("192.168.50.2");
//		basicParam.setSubnet_mask("255.255.255.0");
		deviceOperator.setBasicParam(basicParam);
	}

	@Test
	public void queryBasicParamTest(){
		BasicParam basicParam = new BasicParam();
		basicParam = deviceOperator.queryBasicParam();
	}

	@Test
	public void createPlaylistTest(){
		Txt txt = new Txt();
		txt.setX(0);
		txt.setY(0);
		txt.setFont(Font.KAITI);
		txt.setFont_size(1616);
		txt.setForeground_color(Color.GREEN);
		txt.setBackground_color(Color.BLACK);
		txt.setContent("文本测试,包含\\逗号等符号?这=些符号需要被转义");
		txt.setWidth(0);
		txt.setHeight(0);
		txt.setFont_style(Fontstyle.NORMAL);
		txt.setChar_space(0);
		txt.setQueue_mode(QueueMode.HORIZONTAL);

		Img img = new Img();
		img.setX(0);
		img.setY(0);
		img.setWidth(0);
		img.setHeight(0);
		img.setStayTime(10);
		img.setFilename("timg.jpg");

		Timer timer = new Timer();


		Txtext txtext = new Txtext();
		txtext.setContent("测试txtext");

		Video video = new Video();
		video.setFilename("3.avi");

		Gif gif = new Gif();
		gif.setX(0);
		gif.setY(0);
		gif.setHeight(0);
		gif.setWidth(0);
		gif.setBackground_color(Color.BLACK);
		gif.setPlay_count(3);
		gif.setPlay_time(100);
		gif.setFilename("huluwa.gif");


		Item item1 = new Item();
//		item1.setStayTime(100);
//		item1.setInScreenMode(InOutScreenMode.IMMEDIATELY);
//		item1.setOutScreenMode(InOutScreenMode.IMMEDIATELY);
//		item1.setInSpeed(InSpeed.ONE);
//		item1.setTwinkle_speed(TwinkleSpeed.ONE);
//		item1.setTwinkle_count(5);

//		item1.setPlay_count(1);

//		item1.addTxt(txt);
//		item1.addTimer(timer);
//		item1.addGif(gif);
//		item1.addImg(img);
//		item1.addVideo(video);
		item1.addTxtext(txtext);
//
//		Item item2 = new Item();
//		item2.addTxt(txt);
//		item2.addTimer(timer);
//		item2.addGif(gif);


		Playlist playlist = new Playlist();
		playlist.addItem(item1);
//		playlist.addItem(item2);

		File file = playlist.createPlayListFile();
//		System.out.println(file.getPath());
//
//		System.out.println(file.getAbsolutePath());
		deviceOperator.sendFile(file.getPath());
		String filename = file.getName();
		String num = filename.substring(4,7);
		deviceOperator.playAssignedList(Integer.parseInt(num));
		deviceOperator.disconnect();

	}

}
