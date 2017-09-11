


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;


public class Save_Data  implements Runnable{

	public static String SaveData_FileName = "Save/SaveDb.txt";
	public static String ReadData_FileName = "Save/SaveDb.txt";

	//USB存储目录
	public static String SaveData_Filedir = "/home/pi/Rec/Save/";
	public static String SaveData_USBdir = "/media/REC";


	private static String LeaveData_FileName1 = "俯仰-指令角度.txt";
	private static String LeaveData_FileName2 = "俯仰-复示角度.txt";
	private static String LeaveData_FileName3 = "俯仰-误差角度.txt";
	private static String LeaveData_FileName4 = "俯仰-电枢电压.txt";
	private static String LeaveData_FileName5 = "俯仰-电枢电流.txt";
	private static String LeaveData_FileName6 = "俯仰-激磁电压.txt";
	private static String LeaveData_FileName7 = "俯仰-激磁电流.txt";
	private static String LeaveData_FileName8 = "俯仰-母线电压.txt";

	private static String LeaveData_FileName9  = "旋回-指令角度.txt";
	private static String LeaveData_FileName10 = "旋回-复示角度.txt";
	private static String LeaveData_FileName11 = "旋回-误差角度.txt";
	private static String LeaveData_FileName12 = "旋回-电枢电压.txt";
	private static String LeaveData_FileName13 = "旋回-电枢电流.txt";
	private static String LeaveData_FileName14 = "旋回-激磁电压.txt";
	private static String LeaveData_FileName15 = "旋回-激磁电流.txt";
	private static String LeaveData_FileName16 = "旋回-母线电压.txt";

	private static String SaveData_Format ="\t\t";
	private static String LeaveData_Format ="\t\t";

	private static String LeaveData_FileName=LeaveData_FileName1;

	private int LeaveData_Num_copy;

	private byte read_flag=0x01;  //读取数据库文件正常标志 0x00:没有读取到  0x01:读取到数据库文件

	private float read_total_Line=0;  //读取文件总行数
	private float Pro_Line_Count=0;   //正在处理数据的行数
	private float Per_Pro_Line=0;     //正在处理数据的百分比

	public static String Rec_Save_FileName;
	public static String Rec_Save_RecFileName;


	public final Object LockObject_FY = new Object();
	public final Object LockObject_XH = new Object();

	public static boolean FY_Save_Flag = false;
	public static boolean XH_Save_Flag = false;



	/**
	 *
	 */
	public void Sava_Data()
	{

	}


	//20121108-新方法是用list
	//@1-生成并保存 SaveData 对象  生成记录文件SaveDb.txt--------------------
    public void createAndSave(int which_dsp) throws IOException {

    	if(which_dsp == 1)
    	{
        	//@-old
        	List<MK3_Data_FY>  persons_fy = createSaveData_fy();
            savedata_fy(persons_fy);

//			Thread t_fy = new Thread(new Runnable() {
//				@Override
//				public void run() {
//
//					//@-线程互斥
//					synchronized (LockObject_FY)
//					{
//						//@-等待信号量
//						while (FY_Save_Flag == true)
//						{
//							try {
//								LockObject_FY.wait();
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//
//						FY_Save_Flag = true;
//			    		List<MK3_Data_FY> persons_fy = createSaveData_fy();
//			            try {
//							savedata_fy(persons_fy);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//			            FY_Save_Flag = false;
//			            LockObject_FY.notifyAll();
//					}
//				}
//			});
//			t_fy.setName("theread_fy_save");
//			t_fy.setDaemon(true);
//			t_fy.start();

    	}
        else if(which_dsp == 2)
        {
        	//@-old
        	List<MK3_Data_XH>  persons_xh = createSaveData_xh();
            savedata_xh(persons_xh);
//			Thread t_xh = new Thread(new Runnable() {
//				@Override
//				public void run() {
//
//					//@-线程互斥
//					synchronized (LockObject_XH)
//					{
//						//@-等待信号量
//						while (XH_Save_Flag == true)
//						{
//							try {
//								LockObject_XH.wait();
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//
//						XH_Save_Flag = true;
//			    		List<MK3_Data_XH> persons_xh = createSaveData_xh();
//			            try {
//							savedata_xh(persons_xh);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//			            XH_Save_Flag = false;
//			            LockObject_XH.notifyAll();
//					}
//				}
//			});
//			t_xh.setName("theread_xh_save");
//			t_xh.setDaemon(true);
//			t_xh.start();

        }

        //使用当前系统时间为文件名进行数据记录
//        if(Net_Main_Receive.Save_NewName_Flag==true)
//        {
//        	//新文件名
//        	Rec_Save_RecFileName= new String(""+Rec_DisplayTimer.Time_Str_Save);
//        	Rec_Save_FileName=new String("/home/pi/Rec/Save/"+Rec_Save_RecFileName);
//
//        	//权限关闭
//        	Net_Main_Receive.Save_NewName_Flag=false;
//        }


    }


    //@2-创建要保存的 MK3_FY 对象
    private  List<MK3_Data_FY> createSaveData_fy() {

        List<MK3_Data_FY> result_fy = new ArrayList<MK3_Data_FY>();

    	result_fy.add(new MK3_Data_FY(Net_Main_Receive.receive_buff_FY));

    	//System.out.println("FY:"+(Net_Main_Receive.receive_buff_FY[0]&0xff)+"-"+(Net_Main_Receive.receive_buff_FY[1]&0xff));

    	return result_fy;
    }

    //@2-创建要保存的 MK3_XH 对象
    private  List<MK3_Data_XH> createSaveData_xh() {

        List<MK3_Data_XH> result_xh = new ArrayList<MK3_Data_XH>();

    	result_xh.add(new MK3_Data_XH(Net_Main_Receive.receive_buff_XH));

    	//System.out.println("XH:"+(Net_Main_Receive.receive_buff_XH[0]&0xff)+"-"+(Net_Main_Receive.receive_buff_XH[1]&0xff));

    	return result_xh;
    }

    //@3-保存 SaveData 对象到文件。保存格式为：
    // 1、每个 SaveData 一行
    // 2、每行依次存放数据值，用回车隔开
    private  void savedata_fy(List<MK3_Data_FY> indata) throws IOException {

        // 生成文件内容
        String data = "";
        for (MK3_Data_FY SaveData_FY : indata) {
            data += getSaveDataString_fy(SaveData_FY);
        }

//        //@-写入文件
//        // 保存文件内容
//        FileWriter writer = new FileWriter("/MK3_Save/FY_Record.txt",true);
//        //FileWriter writer = new FileWriter(Rec_Save_FileName,true);
//        writer.write(data);
//        writer.flush();
////      writer.append(data);
//        writer.close();
////      System.out.println("对象保存完毕。");

        ScreensFramework.logger_FY.info(data);

    }

    //@3-保存 SaveData 对象到文件。保存格式为：
    // 1、每个 SaveData 一行
    // 2、每行依次存放数据值，用回车隔开
    private  void savedata_xh(List<MK3_Data_XH> indata) throws IOException {

        // 生成文件内容
        String data = "";
        for (MK3_Data_XH SaveData_XH : indata) {
            data += getSaveDataString_xh(SaveData_XH);
        }

        //@-写入文件
//        // 保存文件内容
//        FileWriter writer = new FileWriter("/MK3_Save/XH_Record.txt",true);
//        //FileWriter writer = new FileWriter(Rec_Save_FileName,true);
//        writer.write(data);
//        writer.flush();
////      writer.append(data);
//        writer.close();
////      System.out.println("对象保存完毕。");

        ScreensFramework.logger_XH.info(data);

    }


    private  String getSaveDataString_fy(MK3_Data_FY InData) {


    	//System.out.println(""+InData.get_DataX_Point1(0));

      return (
    		  InData.get_DataX_Point1(0) + SaveData_Format + InData.get_DataX_Point2(0) + SaveData_Format + InData.get_DataX_Point3(0) + SaveData_Format + InData.get_DataX_Point4(0) + SaveData_Format +
    		  InData.get_DataX_Point1(1) + SaveData_Format + InData.get_DataX_Point2(1) + SaveData_Format + InData.get_DataX_Point3(1) + SaveData_Format + InData.get_DataX_Point4(1) + SaveData_Format +
    		  InData.get_DataX_Point1(2) + SaveData_Format + InData.get_DataX_Point2(2) + SaveData_Format + InData.get_DataX_Point3(2) + SaveData_Format + InData.get_DataX_Point4(2) + SaveData_Format +
    		  InData.get_DataX_Point1(3) + SaveData_Format + InData.get_DataX_Point2(3) + SaveData_Format + InData.get_DataX_Point3(3) + SaveData_Format + InData.get_DataX_Point4(3) + SaveData_Format +
    		  InData.get_DataX_Point1(4) + SaveData_Format + InData.get_DataX_Point2(4) + SaveData_Format + InData.get_DataX_Point3(4) + SaveData_Format + InData.get_DataX_Point4(4) + SaveData_Format +
    		  InData.get_DataX_Point1(5) + SaveData_Format + InData.get_DataX_Point2(5) + SaveData_Format + InData.get_DataX_Point3(5) + SaveData_Format + InData.get_DataX_Point4(5) + SaveData_Format +
    		  InData.get_DataX_Point1(6) + SaveData_Format + InData.get_DataX_Point2(6) + SaveData_Format + InData.get_DataX_Point3(6) + SaveData_Format + InData.get_DataX_Point4(6) + SaveData_Format +
    		  InData.get_DataX_Point1(7) + SaveData_Format + InData.get_DataX_Point2(7) + SaveData_Format + InData.get_DataX_Point3(7) + SaveData_Format + InData.get_DataX_Point4(7) + SaveData_Format +
    		  InData.get_DataX_Point1(8) + SaveData_Format + InData.get_DataX_Point2(8) + SaveData_Format + InData.get_DataX_Point3(8) + SaveData_Format + InData.get_DataX_Point4(8) + SaveData_Format +
    		  InData.get_DataX_Point1(9) + SaveData_Format + InData.get_DataX_Point2(9) + SaveData_Format + InData.get_DataX_Point3(9) + SaveData_Format + InData.get_DataX_Point4(9) + SaveData_Format +
    		  InData.get_DataX_Point1(10) + SaveData_Format + InData.get_DataX_Point2(10) + SaveData_Format + InData.get_DataX_Point3(10) + SaveData_Format + InData.get_DataX_Point4(10) + SaveData_Format +
    		  InData.get_DataX_Point1(11) + SaveData_Format + InData.get_DataX_Point2(11) + SaveData_Format + InData.get_DataX_Point3(11) + SaveData_Format + InData.get_DataX_Point4(11) + SaveData_Format +
    		  InData.get_DataX_Point1(12) + SaveData_Format + InData.get_DataX_Point2(12) + SaveData_Format + InData.get_DataX_Point3(12) + SaveData_Format + InData.get_DataX_Point4(12) + SaveData_Format +
    		  InData.get_DataX_Point1(13) + SaveData_Format + InData.get_DataX_Point2(13) + SaveData_Format + InData.get_DataX_Point3(13) + SaveData_Format + InData.get_DataX_Point4(13) + SaveData_Format +
    		  InData.get_DataX_Point1(14) + SaveData_Format + InData.get_DataX_Point2(14) + SaveData_Format + InData.get_DataX_Point3(14) + SaveData_Format + InData.get_DataX_Point4(14) + SaveData_Format +
    		  InData.get_DataX_Point1(15) + SaveData_Format + InData.get_DataX_Point2(15) + SaveData_Format + InData.get_DataX_Point3(15) + SaveData_Format + InData.get_DataX_Point4(15));

//        return (
//      		  InData.get_DataX_Point1(0) + SaveData_Format + InData.get_DataX_Point2(0) + SaveData_Format + InData.get_DataX_Point3(0) + SaveData_Format + InData.get_DataX_Point4(0));

    }

    private  String getSaveDataString_xh(MK3_Data_XH InData) {


        return (
      		  InData.get_DataX_Point1(0) + SaveData_Format + InData.get_DataX_Point2(0) + SaveData_Format + InData.get_DataX_Point3(0) + SaveData_Format + InData.get_DataX_Point4(0) + SaveData_Format +
      		  InData.get_DataX_Point1(1) + SaveData_Format + InData.get_DataX_Point2(1) + SaveData_Format + InData.get_DataX_Point3(1) + SaveData_Format + InData.get_DataX_Point4(1) + SaveData_Format +
      		  InData.get_DataX_Point1(2) + SaveData_Format + InData.get_DataX_Point2(2) + SaveData_Format + InData.get_DataX_Point3(2) + SaveData_Format + InData.get_DataX_Point4(2) + SaveData_Format +
      		  InData.get_DataX_Point1(3) + SaveData_Format + InData.get_DataX_Point2(3) + SaveData_Format + InData.get_DataX_Point3(3) + SaveData_Format + InData.get_DataX_Point4(3) + SaveData_Format +
      		  InData.get_DataX_Point1(4) + SaveData_Format + InData.get_DataX_Point2(4) + SaveData_Format + InData.get_DataX_Point3(4) + SaveData_Format + InData.get_DataX_Point4(4) + SaveData_Format +
      		  InData.get_DataX_Point1(5) + SaveData_Format + InData.get_DataX_Point2(5) + SaveData_Format + InData.get_DataX_Point3(5) + SaveData_Format + InData.get_DataX_Point4(5) + SaveData_Format +
      		  InData.get_DataX_Point1(6) + SaveData_Format + InData.get_DataX_Point2(6) + SaveData_Format + InData.get_DataX_Point3(6) + SaveData_Format + InData.get_DataX_Point4(6) + SaveData_Format +
      		  InData.get_DataX_Point1(7) + SaveData_Format + InData.get_DataX_Point2(7) + SaveData_Format + InData.get_DataX_Point3(7) + SaveData_Format + InData.get_DataX_Point4(7) + SaveData_Format +
      		  InData.get_DataX_Point1(8) + SaveData_Format + InData.get_DataX_Point2(8) + SaveData_Format + InData.get_DataX_Point3(8) + SaveData_Format + InData.get_DataX_Point4(8) + SaveData_Format +
      		  InData.get_DataX_Point1(9) + SaveData_Format + InData.get_DataX_Point2(9) + SaveData_Format + InData.get_DataX_Point3(9) + SaveData_Format + InData.get_DataX_Point4(9) + SaveData_Format +
      		  InData.get_DataX_Point1(10) + SaveData_Format + InData.get_DataX_Point2(10) + SaveData_Format + InData.get_DataX_Point3(10) + SaveData_Format + InData.get_DataX_Point4(10) + SaveData_Format +
      		  InData.get_DataX_Point1(11) + SaveData_Format + InData.get_DataX_Point2(11) + SaveData_Format + InData.get_DataX_Point3(11) + SaveData_Format + InData.get_DataX_Point4(11) + SaveData_Format +
      		  InData.get_DataX_Point1(12) + SaveData_Format + InData.get_DataX_Point2(12) + SaveData_Format + InData.get_DataX_Point3(12) + SaveData_Format + InData.get_DataX_Point4(12) + SaveData_Format +
      		  InData.get_DataX_Point1(13) + SaveData_Format + InData.get_DataX_Point2(13) + SaveData_Format + InData.get_DataX_Point3(13) + SaveData_Format + InData.get_DataX_Point4(13) + SaveData_Format +
      		  InData.get_DataX_Point1(14) + SaveData_Format + InData.get_DataX_Point2(14) + SaveData_Format + InData.get_DataX_Point3(14) + SaveData_Format + InData.get_DataX_Point4(14) + SaveData_Format +
      		  InData.get_DataX_Point1(15) + SaveData_Format + InData.get_DataX_Point2(15) + SaveData_Format + InData.get_DataX_Point3(15) + SaveData_Format + InData.get_DataX_Point4(15));

//          return (
//        		  InData.get_DataX_Point1(0) + SaveData_Format + InData.get_DataX_Point2(0) + SaveData_Format + InData.get_DataX_Point3(0) + SaveData_Format + InData.get_DataX_Point4(0));

      }
    //----------Save End----------------------


    //分离指定数据并生成文件-Record.txt--------------------
    //@1-读取并显示 RecordData 对象  LeaveData_Num：0~15
    public void readAndShow(int LeaveData_Num) throws IOException {

    	LeaveData_Num_copy=LeaveData_Num;

    	//初始化
		read_total_Line=0;  //读取文件总行数
		Pro_Line_Count=0;   //正在处理数据的行数
		Per_Pro_Line=0;     //正在处理数据的百分比

    	Thread pro_thread=new Thread(this);  //网络接收线程
    	pro_thread=new Thread(this);  //创建数据处理线程
    	pro_thread.setDaemon(true);   //设置线程为后台线程
    	pro_thread.start();

//        List<RecordData> readData = readSaveData(LeaveData_Num);
//        writedata(readData,LeaveData_Num);
    }

    //@2-从文件中读取 RecordData 对象  Num：0~15
    private List<RecordData> readSaveData(int Num){
        List<RecordData> result = new ArrayList<RecordData>();

        BufferedReader reader = null;
        String line;

		try {
			reader = new BufferedReader(new FileReader(SaveData_FileName));
			read_flag=0x01;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			read_flag=0x00;
		}

		if(read_flag==0x01)
		{
	        try {
				while ((line = reader.readLine()) != null) {
				    result.add(getRecordDataFromString(line,Num));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//20121208-读取文件总行数
			try {
				read_total_Line=total_line_count(SaveData_FileName);
				  //数据分离过程信息输出
				  //SaveData_Leave.Leave_Pro_Put(read_total_Line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
        return result;
    }

    //@3-通过一行文件内容生成一个 RecordData 对象
    private RecordData getRecordDataFromString(String line,int Num) {
        String[] parts = line.split(SaveData_Format);  // 获取被分隔的三个部分

        //分离数据0~15
        if((Num==0)||(Num==8))
        return new RecordData(Double.parseDouble(parts[Num]));
        else
        return new RecordData(Integer.parseInt(parts[Num]));
    }

    //@4-保存 RecordData 对象到文件。保存格式为：
    private  void writedata(List<RecordData> Redata,int Num) throws IOException {

    	 //定时器
    	 Timer timer1 = new Timer();
    	 TimerTask timerTask1;
         //生成文件内容
         String data = "";

        //@1-生产定时器任务-20121208
 		timerTask1 = new TimerTask(){
			@Override
			public void run() {
			    //数据分离过程信息输出
				Per_Pro_Line=((Pro_Line_Count/read_total_Line)*100);
			    //SaveData_Leave.Leave_Pro_Put(Per_Pro_Line);
			}
		};
		timer1.schedule(timerTask1,100,50);

		//@2-开始处理数据
        for (RecordData RecordData1 : Redata) {
            data += getSaveDataString(RecordData1,Num) + "\n";
            Pro_Line_Count++;
        }

        //@3-销毁定时器-20121208
		if(timerTask1 != null){
			timerTask1.cancel();
		}
		if(timer1 != null){
			timer1.cancel();
			timer1.purge();
		}

	 	//初始化
		read_total_Line=0;  //读取文件总行数
		Pro_Line_Count=0;   //正在处理数据的行数
		Per_Pro_Line=0;     //正在处理数据的百分比

        //@4-保存文件内容
        switch(Num)
        {
        	//FY
        	case 0: LeaveData_FileName=LeaveData_FileName1;
        			break;
        	case 1: LeaveData_FileName=LeaveData_FileName2;
					break;
        	case 2: LeaveData_FileName=LeaveData_FileName3;
					break;
        	case 3: LeaveData_FileName=LeaveData_FileName4;
					break;
        	case 4: LeaveData_FileName=LeaveData_FileName5;
					break;
        	case 5: LeaveData_FileName=LeaveData_FileName6;
					break;
        	case 6: LeaveData_FileName=LeaveData_FileName7;
					break;
        	case 7: LeaveData_FileName=LeaveData_FileName8;
					break;

		    //XH
        	case 8: LeaveData_FileName=LeaveData_FileName9;
        			break;
        	case 9: LeaveData_FileName=LeaveData_FileName10;
					break;
        	case 10: LeaveData_FileName=LeaveData_FileName11;
					break;
        	case 11: LeaveData_FileName=LeaveData_FileName12;
					break;
        	case 12: LeaveData_FileName=LeaveData_FileName13;
					break;
        	case 13: LeaveData_FileName=LeaveData_FileName14;
					break;
        	case 14: LeaveData_FileName=LeaveData_FileName15;
					break;
        	case 15: LeaveData_FileName=LeaveData_FileName16;
					break;

        	default:break;
        }

        FileWriter writer = new FileWriter(LeaveData_FileName,true);
        writer.write(data);
        writer.close();
    }

    //@5-写入Record.tx文件中的格式
    private  String getSaveDataString(RecordData InData,int Num) {

    	//Double
    	if((Num==0)||(Num==8))
        return (
        		//写入数据的格式
        		InData.get_ReData_Double()+LeaveData_Format
        );
    	//int
    	else
        return (
        		//写入数据的格式
        		InData.get_ReData_Int()+LeaveData_Format
        );
    }

    //分离数据对象
    class RecordData {

    	private Double ReData_Double;
    	private int ReData_Int;

    	RecordData(Double data)
    	{
    		this.ReData_Double=data;
    	}

    	RecordData(int data)
    	{
    		this.ReData_Int=data;
    	}

    	//double
        public double get_ReData_Double() {
            return ReData_Double;
        }
        public void set_FY_ReData_Double(Double InputData) {
            this.ReData_Double = InputData;
        }
    	//int
        public int get_ReData_Int() {
            return ReData_Int;
        }
        public void set_FY_ReData_Int(int InputData) {
            this.ReData_Int = InputData;
        }

    }

    //分离数据-End-----------------



    // 要持久化的类


    /**
     * 采用BufferedInputStream方式读取文件行数
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public int total_line_count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        while ((readChars = is.read(c)) != -1) {
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n')
                    ++count;
            }
        }
        is.close();
        return count;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
        List<RecordData> readData;
		try {
			readData = readSaveData(LeaveData_Num_copy);

			if(read_flag==0x01)
			{
				writedata(readData,LeaveData_Num_copy);
				//数据分离结束
				//SaveData_Leave.Leave_Pro_OK();
			}
			//else
				//SaveData_Leave.Leave_Pro_Error();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**for linux javafx
	 *
	 */
	public static int Save_DataTo_USB(String inputname,String outputname)
	{

		FileOutputStream output=null;

		new File(outputname).mkdirs();
		File[] file = new File(inputname).listFiles();
		for(int i=0;i<file.length;i++)
		{
			if(file[i].isFile())
			{
				file[i].toString();
				FileInputStream input;
				try {
					input = new FileInputStream(file[i]);


				File outtest = new File(outputname);
				if(!outtest.exists())
				{
					outtest.mkdir();
				}

			    output = new FileOutputStream(outputname+"/"+file[i].getName().toString());
				}
				 catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					return 2;
				}



				byte[] b =new byte[1024*5];
				int len;
				try {
					while((len=input.read(b))!=-1)
					{
						output.write(b, 0, len);
					}

					output.flush();
					output.close();
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return 2;
				}

			}
			else if(file[i].isDirectory())
			{
				Save_DataTo_USB(file[i].toString(),outputname+"//"+file[i].getName());
			}
		}

		return 1;

	}

}
