

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.util.Duration;


/**
 *
 * @author Jack Ding
 * @date 2011-01-13
 */
public class Servo_DisplayTimer{

	//@1-系统计时
	private double system_tick = 0;
	//@2-1秒计时
	private double second_1s_tick = 0;
	//@3-主页面显示定时值
	private int    display_tick_count = 0;
	//@3-主页面曲线定时值
	private int    display_curve_count = 0;


    //@5-系统时间获取接口
	private static Calendar local_time;
	//@6-显示数据格式
	public static java.text.NumberFormat  formater_value  =  java.text.DecimalFormat.getInstance();  //显示小数格式化


	public static String Time_Str = new String("----");



	private int    NET_Send_TimeCount = 0;

	//@2-1秒计时
	private static double second_3s_tick = 0;
	private static boolean lock_3s = false;
	public static boolean lock_start_main = false;
	private static double second_5s_tick = 0;


	/*------使用非FX原生task方法-----------*/
	public static Task<Integer> task_dis;
    private Thread Display_Thread;


    private int tick_count=0;

    public static boolean flash_flag=false;

    public int check_net_link_time = 5;



    //@-for test
	private float FY_Angle = 0;
	private float XH_Angle = 0;
	private float Sin_Time = 0;




	/**构建时间定时器
	 *
	 * @param delayTime1
	 */
	public Servo_DisplayTimer(int delayTime){

		//@1-数据精度格式
		formater_value.setMaximumIntegerDigits(2);
		formater_value.setMinimumIntegerDigits(2);

		tick_count=1000/delayTime;

	    /*-------------使用FX原生task方法---------------------*/
	    task_dis = new Task<Integer>() {
	        @Override protected Integer call() throws Exception {
	            int iterations;

	            while (true)
	            {
	            	iterations=1;

	                if (isCancelled()) {
	                    updateMessage("Cancelled");
	                    break;
	                }

	                data_put();

	                //Block the thread for a short time, but be sure
	                //to check the InterruptedException for cancellation
	                try {
	                    Thread.sleep(delayTime);
	                } catch (InterruptedException interrupted) {
	                    if (isCancelled()) {
	                        updateMessage("Cancelled");
	                        break;
	                    }
	                }
	            }
	            return iterations;
	        }
	    };
	    Display_Thread=new Thread(task_dis);
	    Display_Thread.setName("display");
	    Display_Thread.setDaemon(true);
	    Display_Thread.setPriority(Thread.NORM_PRIORITY);    //设置优先级别8
	    Display_Thread.start();

	}


	/**显示刷新
	 *
	 */
	private void data_put()
	{
	    //@1-系统计时累加
		system_tick=system_tick+1;
		//@2-1秒计时累加
		second_1s_tick=second_1s_tick+1;

		if(ScreensFramework.Main_Falg==true)
		{
	    	//@1-程序运行在主页面
	    	if(ScreensFramework.App_Page==1)
	    	{
	    		//@-主页面显示定时器
	    		display_tick_count = display_tick_count + 1;

	    		//@-主页面曲线定时器
	    		display_curve_count = display_curve_count + 1;

	    		//@-每秒10次
	    		if(display_tick_count == tick_count/5)
	    		{
	    			display_tick_count = 0;

					if(flash_flag == false)
					flash_flag = true;
					else if(flash_flag == true)
					flash_flag = false;

					MainController.DisplayProperty_Main.setValue(""+flash_flag);

//					System.out.println("tick");
	    		}


	    		//@-每秒20次
	    		if(display_curve_count == tick_count/20)
	    		{
	    			display_curve_count = 0;

	    			if(MainController.Curve_run_status==true)
					{
						synchronized(this)
						{
							MainController.data1 = (float)Net_Main_Receive.FY_InPut_Angel;
							MainController.data2 = (float)Net_Main_Receive.FY_Real_Angel;
							MainController.data3 = (float)Net_Main_Receive.FY_Error_Angel;
							MainController.data4 = (float)Net_Main_Receive.FY_Pos_out;
							MainController.data5 = (float)Net_Main_Receive.FY_Pos_up;
							MainController.data6 = (float)Net_Main_Receive.FY_uFwdSpd;
							MainController.data7 = (float)Net_Main_Receive.FY_act12;
							MainController.data8 = (float)Net_Main_Receive.FY_Uzk;

							MainController.data9 = (float)Net_Main_Receive.XH_InPut_Angel;
							MainController.data10 = (float)Net_Main_Receive.XH_Real_Angel;
							MainController.data11 = (float)Net_Main_Receive.XH_Error_Angel;
							MainController.data12 = (float)Net_Main_Receive.XH_Pos_out;
							MainController.data13 = (float)Net_Main_Receive.XH_Pos_up;
							MainController.data14 = (float)Net_Main_Receive.XH_uFwdSpd;
							MainController.data15 = (float)Net_Main_Receive.XH_act12;
							MainController.data16 = (float)Net_Main_Receive.XH_Uzk;

							if(MainController.FY_Curve_InputAngle_Dis==true)
							MainController.FY_Data_InputAngle.add(MainController.data1);

							if(MainController.FY_Curve_RealAngle_Dis==true)
							MainController.FY_Data_RealAngle.add(MainController.data2);

							if(MainController.FY_Curve_ErrorAngle_Dis==true)
							MainController.FY_Data_ErrorAngle.add(MainController.data3);

							if(MainController.FY_Curve_Data1_Dis==true)
							MainController.FY_Data_1.add(MainController.data4);

							if(MainController.FY_Curve_Data2_Dis==true)
							MainController.FY_Data_2.add(MainController.data5);

							if(MainController.FY_Curve_Data3_Dis==true)
							MainController.FY_Data_3.add(MainController.data6);

							if(MainController.FY_Curve_Data4_Dis==true)
							MainController.FY_Data_4.add(MainController.data7);

							if(MainController.FY_Curve_Data5_Dis==true)
							MainController.FY_Data_5.add(MainController.data8);
//--------------------------------------------------------------------------------------------------------------
							if(MainController.XH_Curve_InputAngle_Dis==true)
							MainController.XH_Data_InputAngle.add(MainController.data9);

							if(MainController.XH_Curve_RealAngle_Dis==true)
							MainController.XH_Data_RealAngle.add(MainController.data10);

							if(MainController.XH_Curve_ErrorAngle_Dis==true)
							MainController.XH_Data_ErrorAngle.add(MainController.data11);

							if(MainController.XH_Curve_Data1_Dis==true)
							MainController.XH_Data_1.add(MainController.data12);

							if(MainController.XH_Curve_Data2_Dis==true)
							MainController.XH_Data_2.add(MainController.data13);

							if(MainController.XH_Curve_Data3_Dis==true)
							MainController.XH_Data_3.add(MainController.data14);

							if(MainController.XH_Curve_Data4_Dis==true)
							MainController.XH_Data_4.add(MainController.data15);

							if(MainController.XH_Curve_Data5_Dis==true)
							MainController.XH_Data_5.add(MainController.data16);

						}
				    }
	    		}


	    	}
	    	//@2-程序运行在性能检测页面
	    	else if(ScreensFramework.App_Page==2)
	    	{

	    	}

    		//@11-网络发送触发(用于组播识别)
	    	NET_Send_TimeCount = NET_Send_TimeCount+1;
	    	if(NET_Send_TimeCount == tick_count/50)
	    	{
	    		NET_Send_TimeCount = 0;
		    	if((ScreensFramework.Net_Main_Connnect.isOpen == true)&&(ScreensFramework.Net_Main_Connnect.isError == 0))
		    	{
		    		Net_Main.Send_Flag = true;
		    		ScreensFramework.Net_Main_Connnect.Thread_alive_agin();
		    		ScreensFramework.Net_Main_Connnect.Network_Send_thread.start();
		    	}

//				FY_Angle = (float)(12 * Math.sin(0.5709 * Sin_Time));
//				XH_Angle = (float)(12 * Math.sin(0.5709 * Sin_Time) - 90);
//				Sin_Time = (float) (Sin_Time + 0.02);
//
//				Net_Main_Receive.FY_InPut_Angel = FY_Angle;
//				Net_Main_Receive.XH_InPut_Angel = XH_Angle;

	    	}

		}

		//@4-1秒计时
		if(second_1s_tick==tick_count)   //1s
		{
			//@-1秒定时复位
			second_1s_tick=0;

//			if(Net_Main.NetCard_Set == true)
//			{
//	        	//@-检测网络硬件连接
//				ScreensFramework.Net_Main_Connnect.Check_Net_Link();
//			}

			//@6-刷新时间
	    	local_time = Calendar.getInstance();

			//@-系统时间
	    	Time_Str = new String(""+local_time.get(Calendar.YEAR)+"/"
					+formater_value.format(local_time.get(Calendar.MONTH)+1)+"/"
					+formater_value.format(local_time.get(Calendar.DATE))+" "
					+formater_value.format(local_time.get(Calendar.HOUR_OF_DAY))+":"
					+formater_value.format(local_time.get(Calendar.MINUTE))+":"
					+formater_value.format(local_time.get(Calendar.SECOND)));



	    	//@-网络接口检测1s
//			if(Net_Main.NetCard_Set == true)
//			{
//				Thread t1 = new Thread(new Runnable() {
//					@Override
//					public void run() {
//
//			        	//@-检测网络硬件连接
//						ScreensFramework.Net_Main_Connnect.Check_Net_Link();
//
//				  }
//				});
//				t1.setName("Check_Net_Link");
//				t1.setDaemon(true);
//				t1.start();
//			}

			//@-硬件检测5s
			second_5s_tick=second_5s_tick+1;
			if(second_5s_tick==check_net_link_time)
			{
				second_5s_tick=0;

				if(Net_Main.NetCard_Set == true)
				{
					//@-网络接口检测1s
					Thread t1 = new Thread(new Runnable() {
						@Override
						public void run() {

				        	//@-检测网络硬件连接
							ScreensFramework.Net_Main_Connnect.Check_Net_Link();

					  }
					});
					t1.setName("Check_Net_Link");
					t1.setDaemon(true);
					t1.start();

			    	//@-硬件接口检测
					HardWare_Check();
				}
			}

		}
	}



	/**硬件接口检测
	 *
	 */
	private void HardWare_Check()
	{
		if(ScreensFramework.Main_Falg==true)
		{
			//@-自身网络接口
			Net_Interface_Check();
		}

	}


	/**自身网络接口
	 *
	 */
	private void Net_Interface_Check()
	{
		//@1-查询硬件网络连接
		if((ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy != ScreensFramework.Net_Main_Connnect.NetLink_Mode)||(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Flash == true))
		{
			//更新copy值
			ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy = ScreensFramework.Net_Main_Connnect.NetLink_Mode;

			//网络硬件没有连接
			if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy == 1)
			{
				//MainRunController.HardwareInfoProperty.set("unknow");

				//@-Noti输出信息
				ScreensFramework.Show_Noti("Error", "设备故障!");

				if(ScreensFramework.Net_Main_Connnect.isOpen == true)
				{
					ScreensFramework.Net_Main_Connnect.close();
					Net_Main.Net_Interface_Set = false;
					//MainRunController.CommunicationLEDProperty.set("error");  //网络断开
				}
			}
			//网络硬件连接，IP正确
			else if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy == 2)
			{
				//MainRunController.HardwareInfoProperty.set("ok");

				//@-Noti输出信息
				ScreensFramework.Show_Noti("Success", "设备正常!");

				check_net_link_time = 30;

				if(Net_Main.Net_Interface_Set == false)
				{
					Net_Main.Net_Interface_Set = true;
					//ScreensFramework.Net_Main_Connnect = new Net_Main(Net_Main.Set_IP,2318,Net_Main.Set_IP,2300,"230.1.2.3",2300,1);	//组播
					ScreensFramework.Net_Main_Connnect = new Net_Main(Net_Main.Set_IP,21785,Net_Main.Set_IP,21785,"224.119.219.1",21785,1);	//组播

					//System.out.println("e:"+ScreensFramework.Net_Main_Connnect.isError);

					if((ScreensFramework.Net_Main_Connnect.isOpen == true)&&(ScreensFramework.Net_Main_Connnect.isError == 0))
					{

						//MainRunController.CommunicationLEDProperty.set("warnning");  //网络已打开
					}
					else
					{
						//MainRunController.CommunicationLEDProperty.set("error");     //网络打开出错
					}

				}
			}
			//网络硬件连接，IP不正确
			else if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy == 3)
			{
				//MainRunController.HardwareInfoProperty.set("warnning");

				//@-Noti输出信息
				ScreensFramework.Show_Noti("Warning", "设备网络地址出错!");

				if(ScreensFramework.Net_Main_Connnect.isOpen == true)
				{
					ScreensFramework.Net_Main_Connnect.close();
					Net_Main.Net_Interface_Set = false;
					//MainRunController.CommunicationLEDProperty.set("error");  //网络断开
				}
			}

			//关闭状态刷新
			if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Flash == true)
			ScreensFramework.Net_Main_Connnect.NetLink_Mode_Flash = false;

		}

	}


}

