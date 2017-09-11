

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Calendar;

import javafx.application.Platform;

import javax.swing.Timer;



/**
 *
 * @author Jack Ding
 *
 */
public class Net_Main_Receive extends  Timer implements ActionListener, Runnable{

	//@1-网络接收接口、缓存
	public  Thread Network_Receive_thread=new Thread(this);      //网络接收线程
    //private static byte[] receive_buff_Udp = new byte[256];      //网络接收缓存128Byte
    public static byte[] receive_buff_Cast = new byte[128];     //网络接收缓存128Byte
    public static byte[] receive_buff_FY = new byte[128];       //网络接收缓存128Byte
    public static byte[] receive_buff_XH = new byte[128];       //网络接收缓存128Byte

    //@2-网络接口
    private int cPort_Udp;
    private int cPort_Cast;
    public  long temp;  //接收数据包计数
    private String ClientIP_Udp=null;
    private String ClientIP_Cast=null;
    private String ClientPort=null;
    private String ClientData=null;
    private int Recv_Len_Udp;   //网络接收数据包的长度
    private int Recv_Len_Cast;  //网络接收数据包的长度

	//@6-数据保存
	public static Save_Data Save_Obj = new Save_Data();

	//@-FY网络接收计数
	public static int FYDsp_NetReceive_Count = 0;
	//@-XH网络接收计数
	public static int XHDsp_NetReceive_Count = 0;


	public static float FY_InPut_Angel;
	public static float FY_Real_Angel;
	public static float FY_Error_Angel;
	public static float FY_Pos_out;
	public static float FY_Pos_up;
	public static float FY_uFwdSpd;
	public static float FY_act12;
	public static float FY_Uzk;
	public static float FY_Izk;
	public static float FY_Iu;
	public static float FY_Spd_Ref;
	public static float FY_Spd_Err;
	public static float FY_id_err;
	public static float FY_iq_Fbk;
	public static float FY_iq_Ref;
	public static float FY_iq_Err;


	public static float XH_InPut_Angel;
	public static float XH_Real_Angel;
	public static float XH_Error_Angel;
	public static float XH_Pos_out;
	public static float XH_Pos_up;
	public static float XH_uFwdSpd;
	public static float XH_act12;
	public static float XH_Uzk;
	public static float XH_Izk;
	public static float XH_Iu;
	public static float XH_Spd_Ref;
	public static float XH_Spd_Err;
	public static float XH_id_err;
	public static float XH_iq_Fbk;
	public static float XH_iq_Ref;
	public static float XH_iq_Err;








    /**
     *
     * @param arg0
     * @param arg1
     */
	public Net_Main_Receive(int interval) {

		super(interval,  null);
	}


	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		//设置UDP Socket超时
		try {
			Net_Main.UDP_socket.setSoTimeout(100);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	/**
	 *
	 */
	public void shutdown()
	{
		Network_Receive_thread.interrupt();
	}


	/** 接收开关
	 *
	 */
	public void receive_on_off(boolean on_off){

		if(on_off==true)
		{
		 Network_Receive_thread=new Thread(this);  //网络接收线程
		 //Network_Receive_thread.setDaemon(true);   //设置为后台线程
		 Network_Receive_thread.setName("Net-Receive");
		 Network_Receive_thread.setPriority(7);    //设置优先级别6
		 Network_Receive_thread.start();
		}
		else
		 Network_Receive_thread=null;
	}

	/**
	 *
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Net_Main.sendmode==0)  //点播
		{
			if(Net_Main.isOpen==true)  //网络连接正常
			{
				while(Net_Main.isClose==false)  //只要没有要求关闭，一直接收
				{


						/**
						 *   add Action
						 */

				}
			}
		}
		else if(Net_Main.sendmode==1)  //组播
		{
			while(Net_Main.isOpen==true)  //网络连接正常
			{
				//@1-组播接收
				try{

					Net_Main.Multicast_socket.receive(Net_Main.Multicast_Packet);

					ClientIP_Cast=Net_Main.Multicast_Packet.getAddress().getHostAddress();
					cPort_Cast=Net_Main.Multicast_Packet.getPort();
					Recv_Len_Cast=Net_Main.Multicast_Packet.getLength();
					receive_buff_Cast=Net_Main.Multicast_Packet.getData();

					//System.out.println("Cast:"+ClientIP_Cast);

					//@2-FY DSP
					if(ClientIP_Cast.equals(Net_Main.FY_DSP_IP))
					{
						//System.out.println("Handheld");
						if(cPort_Cast==23201)
						{
							if(Recv_Len_Cast==128)  //协议接收数据长度
							{
								//System.out.println("ddd");

								//receive_buff_Cast[0]

								//@-接收计数
								FYDsp_NetReceive_Count = FYDsp_NetReceive_Count + 1;

								//@-复制数据
								for(int i=0;i<128;i++)
								receive_buff_FY[i] = receive_buff_Cast[i];

								//System.out.println(""+(receive_buff_FY[16]&0xff)+"-"+(receive_buff_FY[17]&0xff));

								//@1-FY输入角度
								int temp1 = (int)(receive_buff_FY[1]&0xff);
								int temp2 = (int)(receive_buff_FY[0]&0xff);
								double temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_InPut_Angel = (float)((temp3/1024.0)*10.0);

								//@2-FY复视角度
								temp1 = (int)(receive_buff_FY[9]&0xff);
								temp2 = (int)(receive_buff_FY[8]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Real_Angel = (float)((temp3/1024.0)*10.0);

								//@3-FY误差角度
								temp1 = (int)(receive_buff_FY[17]&0xff);
								temp2 = (int)(receive_buff_FY[16]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Error_Angel = (float)((temp3/1024.0)/10.0);


								//@4-FY-Pos_Out
								temp1 = (int)(receive_buff_FY[25]&0xff);
								temp2 = (int)(receive_buff_FY[24]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Pos_out = (float)((temp3/1024.0));

								//@5-FY-Pos_up
								temp1 = (int)(receive_buff_FY[33]&0xff);
								temp2 = (int)(receive_buff_FY[32]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Pos_up = (float)((temp3/1024.0));

								//@6-FY-FY_uFwdSpd
								temp1 = (int)(receive_buff_FY[41]&0xff);
								temp2 = (int)(receive_buff_FY[40]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_uFwdSpd = (float)((temp3/1024.0));

								//@7-FY-FY_act12
								temp1 = (int)(receive_buff_FY[49]&0xff);
								temp2 = (int)(receive_buff_FY[48]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_act12 = (float)((temp3/1024.0));

								//@8-FY-FY_Uzk
								temp1 = (int)(receive_buff_FY[57]&0xff);
								temp2 = (int)(receive_buff_FY[56]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Uzk = (float)((temp3/1024.0)*100.0);

								//@9-FY-FY_Izk
								temp1 = (int)(receive_buff_FY[65]&0xff);
								temp2 = (int)(receive_buff_FY[64]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Izk = (float)((temp3/1024.0)*10.0);

								//@10-FY-FY_Iu
								temp1 = (int)(receive_buff_FY[73]&0xff);
								temp2 = (int)(receive_buff_FY[72]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Iu = (float)((temp3/1024.0)*10.0);

								//@11-FY-FY_Spd_Ref
								temp1 = (int)(receive_buff_FY[81]&0xff);
								temp2 = (int)(receive_buff_FY[80]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Spd_Ref = (float)((temp3/1024.0));

								//@12-FY-FY_Spd_Err
								temp1 = (int)(receive_buff_FY[89]&0xff);
								temp2 = (int)(receive_buff_FY[88]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_Spd_Err = (float)((temp3/1024.0));

								//@13-FY-FY_id_err
								temp1 = (int)(receive_buff_FY[97]&0xff);
								temp2 = (int)(receive_buff_FY[96]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_id_err = (float)((temp3/1024.0));

								//@14-FY-FY_iq_Fbk
								temp1 = (int)(receive_buff_FY[105]&0xff);
								temp2 = (int)(receive_buff_FY[104]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_iq_Fbk = (float)((temp3/1024.0));

								//@15-FY-FY_iq_Ref
								temp1 = (int)(receive_buff_FY[113]&0xff);
								temp2 = (int)(receive_buff_FY[112]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_iq_Ref = (float)((temp3/1024.0));

								//@16-FY-FY_iq_Err
								temp1 = (int)(receive_buff_FY[121]&0xff);
								temp2 = (int)(receive_buff_FY[120]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								FY_iq_Err = (float)((temp3/1024.0));


								if(MainController.MK3_SaveData_Flag == true)
								{
									//开启数据记录
								    try {
										Save_Obj.createAndSave(1);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}


							}
						}
					}
					//@2-XH DSP
					else if(ClientIP_Cast.equals(Net_Main.XH_DSP_IP))
					{
						//System.out.println("Handheld");
						if(cPort_Cast==23301)
						{
							if(Recv_Len_Cast==128)  //协议接收数据长度
							{
								//@-接收计数
								XHDsp_NetReceive_Count = XHDsp_NetReceive_Count + 1;

								//@-复制数据
								for(int i=0;i<128;i++)
								receive_buff_XH[i] = receive_buff_Cast[i];

								//@-XH输入角度
								int temp1 = (int)(receive_buff_XH[1]&0xff);
								int temp2 = (int)(receive_buff_XH[0]&0xff);
								double temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_InPut_Angel = (float)((temp3/1024.0)*10.0);


								//@-XH复视角度
								temp1 = (int)(receive_buff_XH[9]&0xff);
								temp2 = (int)(receive_buff_XH[8]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Real_Angel = (float)((temp3/1024.0)*10.0);


								//@-XH误差角度
								temp1 = (int)(receive_buff_XH[17]&0xff);
								temp2 = (int)(receive_buff_XH[16]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Error_Angel = (float)((temp3/1024.0)/10.0);


								//@4-XH-Pos_Out
								temp1 = (int)(receive_buff_XH[25]&0xff);
								temp2 = (int)(receive_buff_XH[24]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Pos_out = (float)((temp3/1024.0));

								//@5-XH-Pos_up
								temp1 = (int)(receive_buff_XH[33]&0xff);
								temp2 = (int)(receive_buff_XH[32]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Pos_up = (float)((temp3/1024.0));

								//@6-XH-XH_uFwdSpd
								temp1 = (int)(receive_buff_XH[41]&0xff);
								temp2 = (int)(receive_buff_XH[40]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_uFwdSpd = (float)((temp3/1024.0));

								//@7-XH-XH_act12
								temp1 = (int)(receive_buff_XH[49]&0xff);
								temp2 = (int)(receive_buff_XH[48]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_act12 = (float)((temp3/1024.0));

								//@8-XH-XH_Uzk
								temp1 = (int)(receive_buff_XH[57]&0xff);
								temp2 = (int)(receive_buff_XH[56]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Uzk = (float)((temp3/1024.0)*100.0);

								//@9-XH-XH_Izk
								temp1 = (int)(receive_buff_XH[65]&0xff);
								temp2 = (int)(receive_buff_XH[64]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Izk = (float)((temp3/1024.0)*10.0);

								//@10-XH-XH_Iu
								temp1 = (int)(receive_buff_XH[73]&0xff);
								temp2 = (int)(receive_buff_XH[72]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Iu = (float)((temp3/1024.0)*10.0);

								//@11-XH-XH_Spd_Ref
								temp1 = (int)(receive_buff_XH[81]&0xff);
								temp2 = (int)(receive_buff_XH[80]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Spd_Ref = (float)((temp3/1024.0));

								//@12-XH-XH_Spd_Err
								temp1 = (int)(receive_buff_XH[89]&0xff);
								temp2 = (int)(receive_buff_XH[88]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_Spd_Err = (float)((temp3/1024.0));

								//@13-XH-XH_id_err
								temp1 = (int)(receive_buff_XH[97]&0xff);
								temp2 = (int)(receive_buff_XH[96]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_id_err = (float)((temp3/1024.0));

								//@14-XH-XH_iq_Fbk
								temp1 = (int)(receive_buff_XH[105]&0xff);
								temp2 = (int)(receive_buff_XH[104]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_iq_Fbk = (float)((temp3/1024.0));

								//@15-XH-XH_iq_Ref
								temp1 = (int)(receive_buff_XH[113]&0xff);
								temp2 = (int)(receive_buff_XH[112]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_iq_Ref = (float)((temp3/1024.0));

								//@16-XH-XH_iq_Err
								temp1 = (int)(receive_buff_XH[121]&0xff);
								temp2 = (int)(receive_buff_XH[120]&0xff);
								temp3 = (int)((temp1*256)+temp2);
								if(temp1>=128)
								{
									temp3 = (temp3 - 65535) - 1;
								}
								XH_iq_Err = (float)((temp3/1024.0));


								if(MainController.MK3_SaveData_Flag == true)
								{
									//开启数据记录
								    try {
										Save_Obj.createAndSave(2);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							}
						}
					}

					//System.out.println("receive ok!"+ClientIP+cPort+"/n");

				} catch (IOException e) {

				}

				if(Thread.currentThread().isInterrupted())
				{
					break;
				}
			}
		}
	}


}

