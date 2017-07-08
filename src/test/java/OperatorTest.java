
import com.genture.device_operator.Device;
import com.genture.device_operator.DeviceOperator;
import org.junit.*;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/13.
 */
public class OperatorTest {
	private DeviceOperator deviceOperator;
//	private DataFrame dataFrame;

	@Before
	public void setUp(){
//		genture.com.com.genture.device_operator.Device device = new genture.com.com.genture.device_operator.Device("192.168.30.123", 5000);
		Device device = new Device("10.100.15.15", 5000);
		this.deviceOperator = new DeviceOperator(device);
//		this.dataFrame = new DataFrame();
	}

	@Test
	public void openScreenTest(){
		this.deviceOperator.openScreen();
	}

	@Test
	public void closeScreenTest(){
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
		String filePath = "C:\\Users\\Administrator\\Desktop\\诺瓦交通协议标准版 V3.2.0\\金晓交通通信协议.doc";
//		String filePath = "C:\\Users\\Administrator\\Desktop\\诺瓦交通协议标准版 V3.2.0\\jar包接口测试用例.doc";
		deviceOperator.sendFile(filePath);
	}

	@Test
	public void setLightByHandTest(){
		//设置的值超出范围
//		deviceOperator.setLightByHand(355);

		//设置的值正常
		deviceOperator.setLightByHand(100);
	}


	@Test
	public void captureScreenTest(){
		deviceOperator.captureScreen();
	}

	@Test
	public void convertByteDataTest(){
		byte[] data= {(byte)0x23, (byte)0xaa, (byte)0x11, (byte)0xcc, (byte)0xee, (byte)0x35};
//		byte[] convertData = dataFrame.convertByteData(data);
	}

	@Test
	public void createDataFrameTest(){
		byte[] data= {(byte)0x23, (byte)0xaa, (byte)0x11, (byte)0xcc, (byte)0xee, (byte)0x35};
//		byte[] data_frame = dataFrame.createDataFrame(0x13, data);
	}
}
