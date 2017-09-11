

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

	//@1-ϵͳ��ʱ
	private double system_tick = 0;
	//@2-1���ʱ
	private double second_1s_tick = 0;
	//@3-��ҳ����ʾ��ʱֵ
	private int    display_tick_count = 0;
	//@3-��ҳ�����߶�ʱֵ
	private int    display_curve_count = 0;


    //@5-ϵͳʱ���ȡ�ӿ�
	private static Calendar local_time;
	//@6-��ʾ���ݸ�ʽ
	public static java.text.NumberFormat  formater_value  =  java.text.DecimalFormat.getInstance();  //��ʾС����ʽ��


	public static String Time_Str = new String("----");



	private int    NET_Send_TimeCount = 0;

	//@2-1���ʱ
	private static double second_3s_tick = 0;
	private static boolean lock_3s = false;
	public static boolean lock_start_main = false;
	private static double second_5s_tick = 0;


	/*------ʹ�÷�FXԭ��task����-----------*/
	public static Task<Integer> task_dis;
    private Thread Display_Thread;


    private int tick_count=0;

    public static boolean flash_flag=false;

    public int check_net_link_time = 5;



    //@-for test
	private float FY_Angle = 0;
	private float XH_Angle = 0;
	private float Sin_Time = 0;




	/**����ʱ�䶨ʱ��
	 *
	 * @param delayTime1
	 */
	public Servo_DisplayTimer(int delayTime){

		//@1-���ݾ��ȸ�ʽ
		formater_value.setMaximumIntegerDigits(2);
		formater_value.setMinimumIntegerDigits(2);

		tick_count=1000/delayTime;

	    /*-------------ʹ��FXԭ��task����---------------------*/
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
	    Display_Thread.setPriority(Thread.NORM_PRIORITY);    //�������ȼ���8
	    Display_Thread.start();

	}


	/**��ʾˢ��
	 *
	 */
	private void data_put()
	{
	    //@1-ϵͳ��ʱ�ۼ�
		system_tick=system_tick+1;
		//@2-1���ʱ�ۼ�
		second_1s_tick=second_1s_tick+1;

		if(ScreensFramework.Main_Falg==true)
		{
	    	//@1-������������ҳ��
	    	if(ScreensFramework.App_Page==1)
	    	{
	    		//@-��ҳ����ʾ��ʱ��
	    		display_tick_count = display_tick_count + 1;

	    		//@-��ҳ�����߶�ʱ��
	    		display_curve_count = display_curve_count + 1;

	    		//@-ÿ��10��
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


	    		//@-ÿ��20��
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
	    	//@2-�������������ܼ��ҳ��
	    	else if(ScreensFramework.App_Page==2)
	    	{

	    	}

    		//@11-���緢�ʹ���(�����鲥ʶ��)
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

		//@4-1���ʱ
		if(second_1s_tick==tick_count)   //1s
		{
			//@-1�붨ʱ��λ
			second_1s_tick=0;

//			if(Net_Main.NetCard_Set == true)
//			{
//	        	//@-�������Ӳ������
//				ScreensFramework.Net_Main_Connnect.Check_Net_Link();
//			}

			//@6-ˢ��ʱ��
	    	local_time = Calendar.getInstance();

			//@-ϵͳʱ��
	    	Time_Str = new String(""+local_time.get(Calendar.YEAR)+"/"
					+formater_value.format(local_time.get(Calendar.MONTH)+1)+"/"
					+formater_value.format(local_time.get(Calendar.DATE))+" "
					+formater_value.format(local_time.get(Calendar.HOUR_OF_DAY))+":"
					+formater_value.format(local_time.get(Calendar.MINUTE))+":"
					+formater_value.format(local_time.get(Calendar.SECOND)));



	    	//@-����ӿڼ��1s
//			if(Net_Main.NetCard_Set == true)
//			{
//				Thread t1 = new Thread(new Runnable() {
//					@Override
//					public void run() {
//
//			        	//@-�������Ӳ������
//						ScreensFramework.Net_Main_Connnect.Check_Net_Link();
//
//				  }
//				});
//				t1.setName("Check_Net_Link");
//				t1.setDaemon(true);
//				t1.start();
//			}

			//@-Ӳ�����5s
			second_5s_tick=second_5s_tick+1;
			if(second_5s_tick==check_net_link_time)
			{
				second_5s_tick=0;

				if(Net_Main.NetCard_Set == true)
				{
					//@-����ӿڼ��1s
					Thread t1 = new Thread(new Runnable() {
						@Override
						public void run() {

				        	//@-�������Ӳ������
							ScreensFramework.Net_Main_Connnect.Check_Net_Link();

					  }
					});
					t1.setName("Check_Net_Link");
					t1.setDaemon(true);
					t1.start();

			    	//@-Ӳ���ӿڼ��
					HardWare_Check();
				}
			}

		}
	}



	/**Ӳ���ӿڼ��
	 *
	 */
	private void HardWare_Check()
	{
		if(ScreensFramework.Main_Falg==true)
		{
			//@-��������ӿ�
			Net_Interface_Check();
		}

	}


	/**��������ӿ�
	 *
	 */
	private void Net_Interface_Check()
	{
		//@1-��ѯӲ����������
		if((ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy != ScreensFramework.Net_Main_Connnect.NetLink_Mode)||(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Flash == true))
		{
			//����copyֵ
			ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy = ScreensFramework.Net_Main_Connnect.NetLink_Mode;

			//����Ӳ��û������
			if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy == 1)
			{
				//MainRunController.HardwareInfoProperty.set("unknow");

				//@-Noti�����Ϣ
				ScreensFramework.Show_Noti("Error", "�豸����!");

				if(ScreensFramework.Net_Main_Connnect.isOpen == true)
				{
					ScreensFramework.Net_Main_Connnect.close();
					Net_Main.Net_Interface_Set = false;
					//MainRunController.CommunicationLEDProperty.set("error");  //����Ͽ�
				}
			}
			//����Ӳ�����ӣ�IP��ȷ
			else if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy == 2)
			{
				//MainRunController.HardwareInfoProperty.set("ok");

				//@-Noti�����Ϣ
				ScreensFramework.Show_Noti("Success", "�豸����!");

				check_net_link_time = 30;

				if(Net_Main.Net_Interface_Set == false)
				{
					Net_Main.Net_Interface_Set = true;
					//ScreensFramework.Net_Main_Connnect = new Net_Main(Net_Main.Set_IP,2318,Net_Main.Set_IP,2300,"230.1.2.3",2300,1);	//�鲥
					ScreensFramework.Net_Main_Connnect = new Net_Main(Net_Main.Set_IP,21785,Net_Main.Set_IP,21785,"224.119.219.1",21785,1);	//�鲥

					//System.out.println("e:"+ScreensFramework.Net_Main_Connnect.isError);

					if((ScreensFramework.Net_Main_Connnect.isOpen == true)&&(ScreensFramework.Net_Main_Connnect.isError == 0))
					{

						//MainRunController.CommunicationLEDProperty.set("warnning");  //�����Ѵ�
					}
					else
					{
						//MainRunController.CommunicationLEDProperty.set("error");     //����򿪳���
					}

				}
			}
			//����Ӳ�����ӣ�IP����ȷ
			else if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy == 3)
			{
				//MainRunController.HardwareInfoProperty.set("warnning");

				//@-Noti�����Ϣ
				ScreensFramework.Show_Noti("Warning", "�豸�����ַ����!");

				if(ScreensFramework.Net_Main_Connnect.isOpen == true)
				{
					ScreensFramework.Net_Main_Connnect.close();
					Net_Main.Net_Interface_Set = false;
					//MainRunController.CommunicationLEDProperty.set("error");  //����Ͽ�
				}
			}

			//�ر�״̬ˢ��
			if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Flash == true)
			ScreensFramework.Net_Main_Connnect.NetLink_Mode_Flash = false;

		}

	}


}

