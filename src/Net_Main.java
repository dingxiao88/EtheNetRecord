

import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;



/** ������
 *
 * @author Jack Ding
 *
 *
 */
public class Net_Main implements Runnable{

	public  Thread Network_Send_thread=new Thread(this);

	private	InetAddress Local_inadd;                           //���������ַ
	private	InetAddress Remote_inadd;                          //Զ�������ַ
	private int Local_port;                                    //���������ַport��
	private int Remote_port;                                   //Զ�������ַport��
	private	InetAddress Multicast_inadd;                       //�鲥�����ַ
	private int Multicast_port;                                //�鲥�����ַport��
	public static DatagramSocket UDP_socket;                   //����UDP socket
	public static MulticastSocket Multicast_socket;            //�����鲥socket
    private byte[] receive_cast_buff = new byte[300];          //������ջ���128Byte
	public static DatagramPacket UDP_Packet,Multicast_Packet;  //����������ݰ�
    private byte[] receive_udp_buff = new byte[300];           //������ջ���128Byte

	public static boolean isOpen = false;                      //����򿪱�־
	public static boolean isClose = true;                      //����رձ�־
	public static int isError = 0;                             //�����־     0:û�д���  1:��ñ�����ַ����
	public static int sendmode=0;                              //���緢�ͷ�ʽ  0:�㲥   1:�鲥

    private Net_Main_Receive Net_Receive;                      //�����������

    //@1-Э���
    private DatagramPacket Send_Packet;
    //@2-���緢��Э�����ݰ� - ��Ч�ֽ�Ϊ45B ���5B�����ֳ�PCB
	public static byte[]  Send_Buff = new byte[28];
	//@3-���緢�ʹ�����־
	public static boolean  Send_Flag=false;


	//@5-ȡ�ñ���Ip��ַ
	public static InetAddress address;
	//@6-����IP����ģʽ
	public static int NetLink_Mode=1;  //1:����Ӳ��������  2:���������Ӳ�Ϊ192.168.23.18 3:���������ӵ���Ϊ192.168.23.18
	//@7-����IP����ģʽCopy
	public static int NetLink_Mode_Copy=0;
	//@7-����IP����ģʽCopy
	public static int NetLink_Mode_Copy2=0;
	//@8-����IP����ģʽˢ��
	public static boolean NetLink_Mode_Flash=false;
	//@9-����IP��ַ
	public static String Local_IP;
	//@10-�趨Ip��ַ
	//public static String Set_IP = new String("172.25.9.18");
	public static String Set_IP = new String("168.6.4.18");

	//@-FY DSP IP
	public static String WC_DSP_IP = new String("172.25.9.10");
	//@-FY DSP IP
	//public static String FY_DSP_IP = new String("172.25.9.20");
	public static String FY_DSP_IP = new String("172.25.9.20");
	//@-XH DSP IP
	public static String XH_DSP_IP = new String("172.25.9.30");
	//@-FY DSP IP
	public static String ZZ_DSP_IP = new String("172.25.9.40");


    //@-����ӿ����ñ�־
    public static boolean Net_Interface_Set = false;

    //@-���������ñ�־
    public static boolean NetCard_Set = false;
    //@-������
    public static String NetCard_Name = new String ("Realtek PCIe GBE Family Controller");



    //@-for test
	private float FY_Angle = 0;
	private float XH_Angle = 0;
	private float Sin_Time = 0;
	private float MK3_Device_AngleSend_Switch = (float)(16384.0/180.0);
	private int temp;




/**
 *
 * @param local_add_ip        ������ʾ��������ip��ַ
 * @param local_add_port      ���뱾������������port��
 * @param Remote_add_ip       ����Զ������ip��ַ
 * @param Remote_add_port     ����Զ������Port��
 * @param Multicast_add_ip    �����鲥ip��ַ
 * @param Multicast_add_port  �����鲥port��
 * @param Send_Mode           ���뷢�ͷ�ʽ �㲥�����鲥   0 or 1
 */
	public Net_Main(String local_add_ip,int local_add_port,String Remote_add_ip,int Remote_add_port,String Multicast_add_ip,int Multicast_add_port,int Send_Mode){

		Network_Send_thread.setPriority(9);

		sendmode=Send_Mode;  //�洢���ͷ�ʽ  �㲥���鲥
		Multicast_port=Multicast_add_port;
		Remote_port=Remote_add_port;
		Local_port=local_add_port;


		//---------�����㲥-----------------------
		try {
			Local_inadd=InetAddress.getByName(local_add_ip);   //for windows
			//Local_inadd=SWJ_NewLogin_Ctl.address_use.getByName(local_add_ip);  //for liunx

		} catch (UnknownHostException e) {
		}

		//��ȡ�ƶ�Զ�˵�ַ----------------------
		try {
			 Remote_inadd=InetAddress.getByName(Remote_add_ip);  //for windows
			//Remote_inadd=SWJ_NewLogin_Ctl.address_use.getByName(Remote_add_ip);  //for liunx
		} catch (UnknownHostException e1) {
		}

		if(Send_Mode==0)  //�㲥����
		{
			try {
				UDP_socket=new DatagramSocket(Local_port,Local_inadd);
			} catch (SocketException e) {
			}

			UDP_Packet = new DatagramPacket(receive_udp_buff,receive_udp_buff.length,Local_inadd,Local_port);  //����UDP���ݽ��հ�
			//--------------end----------------

			//---------------------------------�㲥------------------------------------
			//��ȡ�ƶ�������ַ----------------------
			try {
				Local_inadd=InetAddress.getByName(local_add_ip);  //for windows
				//Local_inadd=SWJ_NewLogin_Ctl.address_use.getByName(local_add_ip);  //for liunx

			} catch (UnknownHostException e) {
				isError=1;  //����ƶ�IP��ַ����
			}

			//��ȡ�ƶ�Զ�˵�ַ----------------------
			try {
				 Remote_inadd=InetAddress.getByName(Remote_add_ip);  //for windows
				 //Remote_inadd=SWJ_NewLogin_Ctl.address_use.getByName(Remote_add_ip);  //for liunx
			} catch (UnknownHostException e1) {
				isError=2;  //����ƶ�IP��ַ����
			}

			//����UDP����Socket---------------------
			if(isError==0)  //����ƶ�������ַû�д���
			{
				try {
					UDP_socket=new DatagramSocket(local_add_port,Local_inadd);
					UDP_socket.setSoTimeout(1000);

				} catch (SocketException e) {
					isError=2;  //����socket����
				}

			}
			UDP_Packet = new DatagramPacket(receive_udp_buff,receive_udp_buff.length,Local_inadd,Local_port);  //����UDP���ݽ��հ�

		}

		else if (Send_Mode==1)  //�鲥����
		{
			//---------------------------------�鲥------------------------------------
			//��ȡ�ƶ��鲥IP��ַ----------------------
			try {
				Multicast_inadd = InetAddress.getByName(Multicast_add_ip);
			} catch (UnknownHostException e1) {
				isError=3;  //����ƶ��鲥IP��ַ����
			}


			//�����鲥����socket-------------------------
			try {
				Multicast_socket = new MulticastSocket(Multicast_port); //�����鲥Socket
				Multicast_socket.joinGroup(Multicast_inadd);  //�����鲥��ַIP
				Multicast_socket.setSoTimeout(100);  //���ý��ճ�ʱʱ��
			} catch (IOException e1) {
				isError=4;  //�����鲥socket����
				e1.printStackTrace();
			}
			Multicast_Packet = new DatagramPacket(receive_cast_buff,receive_cast_buff.length,Local_inadd,Local_port);  //�����鲥���ݽ��հ�


		    //��������Э�����ݰ�
			Send_Packet=new DatagramPacket(Send_Buff,Send_Buff.length, Multicast_inadd,Multicast_port);


		}
		//-----------------------------------------------

		if(isError==0)  //ȷ�������紴��������û�д�����
		{
			isOpen=true;
			isClose=false;

			if(Send_Mode==0)   //�㲥
			{
				//��ӡ��Ϣ��������
			}
			else if(Send_Mode==1)  //�鲥
			{
				//��ӡ��Ϣ��������
			}
		}
		else
		{
			//��ӡ��Ϣ��������
		}

		Net_Receive =new Net_Main_Receive(50);  //�鲥�������20/s  --���ղ��ö�ʱ�������� 0.2s
		Net_Receive.receive_on_off(true);
	}

	/** ����ر�
	 *
	 */
	public void close(){

//		if(isError!=2&&sendmode==0)
//	    UDP_socket.close();

		if(isError!=4&&sendmode==1)
		{
			Net_Receive.shutdown();
			Net_Receive=null;
		}

		isError=0;     //�����־��λ
		isOpen=false;  //�ı��־״̬
		isClose=true;  //�ı��־״̬
	}
	/**
	 *
	 */
	public void Thread_alive_agin()
	{
		Network_Send_thread=new Thread(this);
		Network_Send_thread.setName("Net-Send");
		Network_Send_thread.setDaemon(true);
		Network_Send_thread.setPriority(3);    //�������ȼ���3
	}

    /** ���緢��
     *
     *
     */
	@Override
	public void run() {

		//�������緢��
		if(isOpen==true)  //�����Ѵ�
		{
				//@-�ֳ�PI3���ͱ��Ľṹ(����)
				//@-[�˻��ֳ�(50Byte)]  ��50Byte

//			    //@1-����˻��������ư�����
//				ScreensFramework.PI3_Handle.PI3_Send_Pro();
//				//@-����˻��������ư����������ͻ�����
//				for(int i=0;i<ScreensFramework.PI3_Handle.PI3_SendData_Size;i++)
//				{
//					Send_Buff[i] = ScreensFramework.PI3_Handle.PI3_NetSend_Data[i];
//				}

//				FY_Angle = (float)(12 * Math.sin(0.5709 * Sin_Time));
//				XH_Angle = (float)(12 * Math.sin(0.5709 * Sin_Time) - 90);
				FY_Angle = (float)(12 * Math.sin(((2*3.1415926)/11) * Sin_Time));
				XH_Angle = (float)(12 * Math.sin(((2*3.1415926)/11) * Sin_Time) - 90);
				Sin_Time = (float) (Sin_Time + 0.02);

				//@-FY�Ƕ�
				temp = (int)(FY_Angle*MK3_Device_AngleSend_Switch);
				Send_Buff[0] = (byte)((temp&0xff00)>>8);
				Send_Buff[1] = (byte)(temp&0x00ff);
				Net_Main_Receive.FY_InPut_Angel = FY_Angle;

				//@-XH�Ƕ�
				temp = (int)(XH_Angle*MK3_Device_AngleSend_Switch);
				Send_Buff[2] = (byte)((temp&0xff00)>>8);
				Send_Buff[3] = (byte)(temp&0x00ff);
				Net_Main_Receive.XH_InPut_Angel = XH_Angle;

			    //@2-���緢��
				Send_Packet=new DatagramPacket(Send_Buff,Send_Buff.length, Multicast_inadd,Multicast_port);
				try {
					Multicast_socket.send(Send_Packet);  //������������
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//����������־
				Send_Flag=false;
		}
	}




	/**�����������
	 *�ú���Ƶ�����ý�����GUI�ٶȱ���������������Խ��Խ����
	 *
	 */
	public static void Check_Net_Link()
	{
		Enumeration<NetworkInterface> interfaces = null;
		NetworkInterface networkInterface = null;
		boolean NetworkInterface_Find_Flag = false;


		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//@-��������ӿ�
        while (interfaces.hasMoreElements())
        {
            networkInterface = interfaces.nextElement();

            if(networkInterface.getDisplayName().equals(NetCard_Name))
            {
            	NetworkInterface_Find_Flag = true;
            	break;
            }
        }

        //@-�ҵ���������
        if(NetworkInterface_Find_Flag == true)
        {
	    	try {
	    		//����Ӳ������
				if(networkInterface.isUp()==true)
				{
					//System.out.println("isup");

		            for (InterfaceAddress i : networkInterface.getInterfaceAddresses())
		            {

		            	String ip = i.getAddress().getHostAddress();

		            	//System.out.println("ip:" + ip.length());
		            	if(ip.length()<15)
		            	{
		            		//System.out.println("ip:" + ip);
			                if(ip.equals(Set_IP))
			                {
								Local_IP=new String(i.getAddress().getHostAddress());
								NetLink_Mode=2;
			                }
			                else
			                {
								Local_IP=new String(i.getAddress().getHostAddress());
								NetLink_Mode=3;
			                	continue;
			                }
		            	}
		            }
				}
				//����Ӳ��δ����
				else if(networkInterface.isUp()==false)
				{
					//System.out.println("out");
					NetLink_Mode=1;
				}

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }



        // �����ļ�����
//      FileWriter writer;
//		try {
//			writer = new FileWriter("/MK3_Save/NetCard.txt",true);
//          //FileWriter writer = new FileWriter(Rec_Save_FileName,true);
//          writer.write(data2);
//          writer.flush();
////        writer.append(data);
//          writer.close();
////        System.out.println("���󱣴���ϡ�");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}



	/**�����������
	 *�ú���Ƶ�����ý�����GUI�ٶȱ���������������Խ��Խ����
	 *
	 */
	public static void Check_Net_Link_Backup()
	{
		Enumeration<NetworkInterface> interfaces = null;

        String data1 = "";
        String data2 = "";

        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;


		try {
			interfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        while (interfaces.hasMoreElements())
        {
            NetworkInterface networkInterface = interfaces.nextElement();
            //System.out.println("Network Interface Name : [" + networkInterface.getDisplayName() + "]");

            //ScreensFramework.Debug_String = new String("Network Interface Name : [" + networkInterface.getDisplayName() + "]\n");

            data1 =  new String("Network Interface Name : [" + networkInterface.getDisplayName() + "]\n");


            //@-���ݾ�������Ӳ�����ý����޸�
            //if(networkInterface.getDisplayName().equals("eth0"))
            //if(networkInterface.getDisplayName().equals("Software Loopback Interface 1"))
            //Realtek PCIe GBE Family Controller
            if(networkInterface.getDisplayName().equals(NetCard_Name))
            {
            	flag1 = true;

            	try {
            		//����Ӳ������
					if(networkInterface.isUp()==true)
					{
						//System.out.println("isup");

						flag2 = true;

			            for (InterfaceAddress i : networkInterface.getInterfaceAddresses())
			            {

			            	flag3 = true;

			            	String ip = i.getAddress().getHostAddress();

			            	//System.out.println("ip:" + ip.length());
			            	if(ip.length()<15)
			            	{
			            		//System.out.println("ip:" + ip);
				                if(ip.equals(Set_IP))
				                {
									Local_IP=new String(i.getAddress().getHostAddress());
									NetLink_Mode=2;
				                }
				                else
				                {
									Local_IP=new String(i.getAddress().getHostAddress());
									NetLink_Mode=3;
				                	continue;
				                }
			            	}
			            }
					}
					//����Ӳ��δ����
					else if(networkInterface.isUp()==false)
					{
						//System.out.println("out");
						NetLink_Mode=1;
					}

				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
            //�ػ���ַ
            else if(networkInterface.getDisplayName().equals("lo"))
            {
            	continue;
            }


            data2 = new String(""+data1+"flag1:"+flag1+"  flag2:"+flag2+"  flag3:"+flag3+"\n");


            // �����ļ�����
//            FileWriter writer;
//			try {
//				writer = new FileWriter("/MK3_Save/NetCard.txt",true);
//	            //FileWriter writer = new FileWriter(Rec_Save_FileName,true);
//	            writer.write(data2);
//	            writer.flush();
////	          writer.append(data);
//	            writer.close();
////	          System.out.println("���󱣴���ϡ�");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}



            //System.out.println("ip:" + Local_IP);
        }
	}


}
