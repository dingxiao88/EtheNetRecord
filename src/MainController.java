/*
 * Copyright (c) 2008, 2013 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */



import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.perf.PerformanceTracker;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.paint.Color;

/**
 * Login Controller.
 */
public class MainController implements Initializable, ControlledScreen {


	//@-ϵͳʱ��
	@FXML
	private Label MK3_System_Time;


	//@-��������ָʾ��
	@FXML
	private ImageView MK3_NetLink_ImageView;


	//@-FY����
    @FXML
    private LineChart  LineChart_FY;
    @FXML
    private NumberAxis Chart_FY_AxisX;
    @FXML
    private NumberAxis Chart_FY_AxisY;

	//@-����ѡ��-1-�����
	@FXML
	private CheckBox MK3_FY_InputAngel_CheckBox;
	//@-����ѡ��-2-���ӽ�
	@FXML
	private CheckBox MK3_FY_RealAngel_CheckBox;
	//@-����ѡ��-3-����
	@FXML
	private CheckBox MK3_FY_ErrorAngel_CheckBox;
	//@-����ѡ��-4-pos_out
	@FXML
	private CheckBox MK3_FY_pos_out_CheckBox;
	//@-����ѡ��-5-pos_up
	@FXML
	private CheckBox MK3_FY_pos_up_CheckBox;
	//@-����ѡ��-6-uFwdSpd
	@FXML
	private CheckBox MK3_FY_uFwdSpd_CheckBox;
	//@-����ѡ��-7-act12
	@FXML
	private CheckBox MK3_FY_act12_CheckBox;
	//@-����ѡ��-8-Uzk
	@FXML
	private CheckBox MK3_FY_Uzk_CheckBox;
	//@-����ѡ��-9-Izk
	@FXML
	private CheckBox MK3_FY_Izk_CheckBox;
	//@-����ѡ��-10-Iu
	@FXML
	private CheckBox MK3_FY_Iu_CheckBox;
	//@-����ѡ��-11-Spe_Ref
	@FXML
	private CheckBox MK3_FY_Spe_Ref_CheckBox;
	//@-����ѡ��-12-Spe_err
	@FXML
	private CheckBox MK3_FY_Spe_err_CheckBox;
	//@-����ѡ��-13-id_err
	@FXML
	private CheckBox MK3_FY_id_err_CheckBox;
	//@-����ѡ��-14-iq_Fbk
	@FXML
	private CheckBox MK3_FY_iq_Fbk_CheckBox;
	//@-����ѡ��-15-iq_Ref
	@FXML
	private CheckBox MK3_FY_iq_Ref_CheckBox;
	//@-����ѡ��-16-iq_err
	@FXML
	private CheckBox MK3_FY_iq_err_CheckBox;



    //@-XH����
    @FXML
    private LineChart  LineChart_XH;
    @FXML
    private NumberAxis Chart_XH_AxisX;
    @FXML
    private NumberAxis Chart_XH_AxisY;

  //@-����ѡ��-1-�����
  	@FXML
  	private CheckBox MK3_XH_InputAngel_CheckBox;
  	//@-����ѡ��-2-���ӽ�
  	@FXML
  	private CheckBox MK3_XH_RealAngel_CheckBox;
  	//@-����ѡ��-3-����
  	@FXML
  	private CheckBox MK3_XH_ErrorAngel_CheckBox;
  	//@-����ѡ��-4-pos_out
  	@FXML
  	private CheckBox MK3_XH_pos_out_CheckBox;
  	//@-����ѡ��-5-pos_up
  	@FXML
  	private CheckBox MK3_XH_pos_up_CheckBox;
  	//@-����ѡ��-6-uFwdSpd
  	@FXML
  	private CheckBox MK3_XH_uFwdSpd_CheckBox;
  	//@-����ѡ��-7-act12
  	@FXML
  	private CheckBox MK3_XH_act12_CheckBox;
  	//@-����ѡ��-8-Uzk
  	@FXML
  	private CheckBox MK3_XH_Uzk_CheckBox;
  	//@-����ѡ��-9-Izk
  	@FXML
  	private CheckBox MK3_XH_Izk_CheckBox;
  	//@-����ѡ��-10-Iu
  	@FXML
  	private CheckBox MK3_XH_Iu_CheckBox;
  	//@-����ѡ��-11-Spe_Ref
  	@FXML
  	private CheckBox MK3_XH_Spe_Ref_CheckBox;
  	//@-����ѡ��-12-Spe_err
  	@FXML
  	private CheckBox MK3_XH_Spe_err_CheckBox;
  	//@-����ѡ��-13-id_err
  	@FXML
  	private CheckBox MK3_XH_id_err_CheckBox;
  	//@-����ѡ��-14-iq_Fbk
  	@FXML
  	private CheckBox MK3_XH_iq_Fbk_CheckBox;
  	//@-����ѡ��-15-iq_Ref
  	@FXML
  	private CheckBox MK3_XH_iq_Ref_CheckBox;
  	//@-����ѡ��-16-iq_err
  	@FXML
  	private CheckBox MK3_XH_iq_err_CheckBox;


	//@-���ݼ�¼����
	@FXML
	private Label MK3_FY_Save_Count;
	//@-�������ݼ�¼����
	@FXML
	private Label MK3_FY_Test_Count;

	//@-���ݼ�¼�������㰴ť
	@FXML
	private Button MK3_FY_Save_Clean;
	//@-�������ݼ�¼��ť
	@FXML
	private Button MK3_FY_Test;

	//@-FY����Ƕ�
	@FXML
	private Label MK3_FY_InputAngel;
	//@-FY���ӽǶ�
	@FXML
	private Label MK3_FY_RealAngel;
	//@-FY���Ƕ�
	@FXML
	private Label MK3_FY_ErrorAngel;



	//@-���ݼ�¼����
	@FXML
	private Label MK3_XH_Save_Count;
	//@-�������ݼ�¼����
	@FXML
	private Label MK3_XH_Test_Count;

	//@-���ݼ�¼�������㰴ť
	@FXML
	private Button MK3_XH_Save_Clean;
	//@-�������ݼ�¼��ť
	@FXML
	private Button MK3_XH_Test;

	//@-XH����Ƕ�
	@FXML
	private Label MK3_XH_InputAngel;
	//@-XH���ӽǶ�
	@FXML
	private Label MK3_XH_RealAngel;
	//@-XH���Ƕ�
	@FXML
	private Label MK3_XH_ErrorAngel;

	@FXML
	private CheckBox MK3_SaveData_CheckBox;

	@FXML
	private TextField NetCard_Name;
	//@-������ȷ����ť
	@FXML
	private Button NetCard_Name_Button;



//-------------------------------------------------------------------------------------------

	//@1-������Ӧ�ó���ӿ�
	private ScreensController myController;


    //@24-��������ʾͬ��
	public static SimpleStringProperty DisplayProperty_Main = new SimpleStringProperty();
    //@34-��������ʾ������
	private ChangeListener ChangeListen_Display;

	//@6-��ʾ���ݸ�ʽ
	private java.text.NumberFormat  formater_decimal  =  java.text.DecimalFormat.getInstance();  //��ʾС����ʽ��
	private java.text.NumberFormat  AF_formater_value  =  java.text.DecimalFormat.getInstance();  //��ʾС����ʽ��


	//-------------------------------------------------------------------------------------------------------
	//@11-����������
    private static final int MAX_DATA_POINTS = 400;
    //@12-FY������ʾʱ��������
    private int xSeriesData = 0;
    //@13-FY������ʾʱ��������Copy
    private int xSeriesData_Copy = 0;
    //@14-XH������ʾʱ��������
    private int xSeriesData1 = 0;
    //@15-XH������ʾʱ��������Copy
    private int xSeriesData1_Copy = 0;
    //@16-FY������ʾ��־
    public static  boolean FY_Curve_InputAngle_Dis =    false;
    public static  boolean FY_Curve_RealAngle_Dis =     false;
    public static  boolean FY_Curve_ErrorAngle_Dis =    true;
    public static  boolean FY_Curve_Data1_Dis =         false;
    public static  boolean FY_Curve_Data2_Dis =         false;
    public static  boolean FY_Curve_Data3_Dis =         false;
    public static  boolean FY_Curve_Data4_Dis =         false;
    public static  boolean FY_Curve_Data5_Dis =         false;

    //@-FY������ʾ�Ƴ���־
    public static  boolean FY_Curve_InputAngle_Remove =    true;
    public static  boolean FY_Curve_RealAngle_Remove =     true;
    public static  boolean FY_Curve_ErrorAngle_Remove =    false;
    public static  boolean FY_Curve_Data1_Remove =         true;
    public static  boolean FY_Curve_Data2_Remove =         true;
    public static  boolean FY_Curve_Data3_Remove =         true;
    public static  boolean FY_Curve_Data4_Remove =         true;
    public static  boolean FY_Curve_Data5_Remove =         true;

    //@17-XH������ʾ��־
    public static  boolean XH_Curve_InputAngle_Dis =    false;
    public static  boolean XH_Curve_RealAngle_Dis =     false;
    public static  boolean XH_Curve_ErrorAngle_Dis =    true;
    public static  boolean XH_Curve_Data1_Dis =         false;
    public static  boolean XH_Curve_Data2_Dis =         false;
    public static  boolean XH_Curve_Data3_Dis =         false;
    public static  boolean XH_Curve_Data4_Dis =         false;
    public static  boolean XH_Curve_Data5_Dis =         false;

    //@-XH������ʾ�Ƴ���־
    public static  boolean XH_Curve_InputAngle_Remove =    true;
    public static  boolean XH_Curve_RealAngle_Remove =     true;
    public static  boolean XH_Curve_ErrorAngle_Remove =    false;
    public static  boolean XH_Curve_Data1_Remove =         true;
    public static  boolean XH_Curve_Data2_Remove =         true;
    public static  boolean XH_Curve_Data3_Remove =         true;
    public static  boolean XH_Curve_Data4_Remove =         true;
    public static  boolean XH_Curve_Data5_Remove =         true;

    //@18-���߻�׼����
    private int  FY_Curve_UpData_DataBase = 3;   //@-1:����Ƕ�   2:���ӽǶ�  3:���Ƕ�  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    private int  XH_Curve_UpData_DataBase = 3;   //@-1:����Ƕ�   2:���ӽǶ�  3:���Ƕ�  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    //@19-FY������ʾ�����
    public  int   FY_Curve_Display_Count = 1;
    public  int   FY_Curve_Display_Count_MAX = 8;
    //@20-XH������ʾ�����
    public  int   XH_Curve_Display_Count = 1;
    public  int   XH_Curve_Display_Count_MAX = 8;
    //@21-FY����ѡ��ָʾ
    public  int    FY_Curve_Index = 0; //0:��  1:����Ƕ�  2:���ӽǶ�  3:���Ƕ�  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    public  byte[] FY_Curve_Sel ={0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x00,0x00};  //0:û��ѡ��  1:ѡ��   2:ȷ��
    //@22-XH����ѡ��ָʾ
    public  int    XH_Curve_Index = 0; //0:��  1:����Ƕ�  2:���ӽǶ�  3:���Ƕ�  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    public  byte[] XH_Curve_Sel ={0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x00,0x00};  //0:û��ѡ��  1:ѡ��   2:ȷ��

    //@23-�������б�ʶ
    public static boolean Curve_run_status=false;   //true:��������  false������ֹͣ����
    //@24-����ˢ�±�ʶ
    private boolean FY_Curve_updata=true;
    private boolean XH_Curve_updata=true;
    //@25-������ʾʱ����
    private Timeline Curve_timeline;
    //@26-������ʾ������ʱ��
    private static SequentialTransition Curve_animation;

	//@27-FY�ŷ�8����������
    private LineChart.Series<Number, Number>  FY_ChartSeries_InputAngle;  //@-FY����Ƕ�
    private LineChart.Series<Number, Number>  FY_ChartSeries_RealAngle;   //@-FY���ӽǶ�
    private LineChart.Series<Number, Number>  FY_ChartSeries_ErrorAngle;  //@-FY���Ƕ�
    private LineChart.Series<Number, Number>  FY_ChartSeries_1;           //@-FYĸ�ߵ�ѹ
    private LineChart.Series<Number, Number>  FY_ChartSeries_2;           //@-FY���ת��
    private LineChart.Series<Number, Number>  FY_ChartSeries_3;			  //@-FY���ת��
    private LineChart.Series<Number, Number>  FY_ChartSeries_4;			  //@-FY�������
    private LineChart.Series<Number, Number>  FY_ChartSeries_5;			  //@-FY���ŵ�ѹ
	//@28-XH�ŷ�8����������
    private LineChart.Series<Number, Number>  XH_ChartSeries_InputAngle;  //@-XH����Ƕ�
    private LineChart.Series<Number, Number>  XH_ChartSeries_RealAngle;   //@-XH���ӽǶ�
    private LineChart.Series<Number, Number>  XH_ChartSeries_ErrorAngle;  //@-XH���Ƕ�
    private LineChart.Series<Number, Number>  XH_ChartSeries_1;           //@-XHĸ�ߵ�ѹ
    private LineChart.Series<Number, Number>  XH_ChartSeries_2;           //@-XH���ת��
    private LineChart.Series<Number, Number>  XH_ChartSeries_3;			  //@-XH���ת��
    private LineChart.Series<Number, Number>  XH_ChartSeries_4;			  //@-XH�������
    private LineChart.Series<Number, Number>  XH_ChartSeries_5;			  //@-XH���ŵ�ѹ

    //@29-FY�ŷ�8���������ݶ���
    public static List<Float> FY_Data_InputAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_RealAngle =  Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_ErrorAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_1 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_2 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_3 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_4 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_5 =          Collections.synchronizedList(new ArrayList<Float>());
    //@30-XH�ŷ�8���������ݶ���
    public static List<Float> XH_Data_InputAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_RealAngle =  Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_ErrorAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_1 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_2 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_3 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_4 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_5 =          Collections.synchronizedList(new ArrayList<Float>());


	//-------------------------------------------------------------------------------------------------------

    //@-table������ʾԴ
    public static Float data1 =  (float)(0);
    public static Float data2 =  (float)(0);
    public static Float data3 =  (float)(0);
    public static Float data4 =  (float)(0);
    public static Float data5 =  (float)(0);
    public static Float data6 =  (float)(0);
    public static Float data7 =  (float)(0);
    public static Float data8 =  (float)(0);
    public static Float data9 =  (float)(0);
    public static Float data10 = (float)(0);
    public static Float data11 = (float)(0);
    public static Float data12 = (float)(0);
    public static Float data13 = (float)(0);
    public static Float data14 = (float)(0);
    public static Float data15 = (float)(0);
    public static Float data16 = (float)(0);


	private Image Status_Square_Red;
	private Image Status_Square_Yellow;
	private Image Status_Square_Green;
	private Image Status_Square_White;

	//@-�����־
	public static boolean MK3_SaveData_Flag = true;


    /**
     * 	//@-ϵͳʱ��
		MK3_System_Time.setText(Servo_DisplayTimer.Time_Str);
	 *  ����ϵͳʱ��Ῠ�٣�������ֻ�����������Ҫ������Servo_DisplayTimer��Time_Str��ȡʱ��ı�����1s��ʱ�ӻ�ȡ������ʱ������ĳ���
	 *  20170331-ʹ��log��¼�󣬿�ʵ����ʾ���߼Ӻ�̨�洢����
     */





	/**�������ʼ��
	 *
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    	//@-���ݾ��ȸ�ʽ - С��
    	formater_decimal.setMaximumFractionDigits(3);
    	formater_decimal.setMinimumFractionDigits(3);

    	Status_Square_Red = new Image(MainController.class.getResourceAsStream("statusbar_message_light_red.png"));
    	Status_Square_Yellow = new Image(MainController.class.getResourceAsStream("statusbar_message_light_orange.png"));
    	Status_Square_Green = new Image(MainController.class.getResourceAsStream("statusbar_message_light_green.png"));
    	Status_Square_White = new Image(MainController.class.getResourceAsStream("statusbar_message_light_white.png"));

    	Curve_Parameter_Init();

    	//@6-��ʾͬ��
    	DisplayProperty_Main.addListener(ChangeListen_Display = new ChangeListener(){
			@Override
			public void changed(ObservableValue arg0, Object oldval, Object newval) {

				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
					// TODO Auto-generated method stub
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							 //################################ 1-���涥����Ϣ��ʾ ##########################################
							 //@-ϵͳʱ��
							 MK3_System_Time.setText(Servo_DisplayTimer.Time_Str);

							 //@-��������ָʾ
							 if(ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy2 != ScreensFramework.Net_Main_Connnect.NetLink_Mode)
							 {
								 ScreensFramework.Net_Main_Connnect.NetLink_Mode_Copy2 = ScreensFramework.Net_Main_Connnect.NetLink_Mode;
								 if(ScreensFramework.Net_Main_Connnect.NetLink_Mode == 1)
								 {
									 MK3_NetLink_ImageView.setImage(Status_Square_Red);
								 }
								 else if(ScreensFramework.Net_Main_Connnect.NetLink_Mode == 2)
								 {
									 MK3_NetLink_ImageView.setImage(Status_Square_Green);
								 }
								 else if(ScreensFramework.Net_Main_Connnect.NetLink_Mode == 3)
								 {
									 MK3_NetLink_ImageView.setImage(Status_Square_Yellow);
								 }
							 }

							 MK3_FY_Save_Count.setText("��¼��������:"+Net_Main_Receive.FYDsp_NetReceive_Count);
							 MK3_XH_Save_Count.setText("��¼��������:"+Net_Main_Receive.XHDsp_NetReceive_Count);

							 //@-FY�Ƕ�
							 MK3_FY_InputAngel.setText("����:"+formater_decimal.format(Net_Main_Receive.FY_InPut_Angel));
							 MK3_FY_RealAngel.setText("����:"+formater_decimal.format(Net_Main_Receive.FY_Real_Angel));
							 MK3_FY_ErrorAngel.setText("���:"+formater_decimal.format(Net_Main_Receive.FY_Error_Angel));

							 //@-XH�Ƕ�
							 MK3_XH_InputAngel.setText("����:"+formater_decimal.format(Net_Main_Receive.XH_InPut_Angel));
							 MK3_XH_RealAngel.setText("����:"+formater_decimal.format(Net_Main_Receive.XH_Real_Angel));
							 MK3_XH_ErrorAngel.setText("���:"+formater_decimal.format(Net_Main_Receive.XH_Error_Angel));

						}
					});
				  }
				});
				t1.setName("MainDisplayUpdate");
				t1.setDaemon(true);
				t1.start();
			}
    	});

    	/**����������£���ʱ1min��FY&XH����14037(�����ļ�д�����)
    	 * ����������£���ʱ1min��FY&XH����15083(�����ļ�д�����)
    	 *
    	 * log4j,����������£���ʱ1min��FY����30345
    	 * log4j,����������£���ʱ1min��FY����30355
    	 *
    	 */
    	//@7-�������߹���
    	prepareTimeline();

    }


    /**�������ݳ�ʼ��
    *
    */
   private void Curve_Parameter_Init()
   {
	    //@-FY���߻�������
 		LineChart_FY.setCreateSymbols(false);
 		LineChart_FY.setAnimated(false);
 		LineChart_FY.setLegendVisible(true);
 		LineChart_FY.setCache(true);
 		LineChart_FY.setCacheShape(true);
 		LineChart_FY.cacheProperty();
 		LineChart_FY.cacheHintProperty();
 		LineChart_FY.setCacheHint(CacheHint.QUALITY);
 		//Rec_AxisY.setTickLabelFill(Color.RED);
 		Chart_FY_AxisY.setAutoRanging(true);
 		Chart_FY_AxisX.setTickLabelFill(Color.CHOCOLATE);
 		Chart_FY_AxisY.setTickLabelFill(Color.CHOCOLATE);

 		//@-DX


 		//@-FY�������ݳ�ʼ��
 		FY_ChartSeries_InputAngle = new LineChart.Series<Number, Number>();
 		FY_ChartSeries_RealAngle =  new LineChart.Series<Number, Number>();
 		FY_ChartSeries_ErrorAngle = new LineChart.Series<Number, Number>();
 		FY_ChartSeries_1 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_2 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_3 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_4 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_5 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_InputAngle.setName("�����");
 		FY_ChartSeries_RealAngle.setName ("���ӽ�");
 		FY_ChartSeries_ErrorAngle.setName("����");
 		FY_ChartSeries_1.setName("pos_out");
 		FY_ChartSeries_2.setName("pos_up");
 		FY_ChartSeries_3.setName("uFwdSpd");
 		FY_ChartSeries_4.setName("act12");
 		FY_ChartSeries_5.setName("Uzk");
 		//@-�����ݼ�������-DX
 		LineChart_FY.getData().addAll(FY_ChartSeries_ErrorAngle);

 		//@-XH���߻�������
 		LineChart_XH.setCreateSymbols(false);
 		LineChart_XH.setAnimated(false);
 		LineChart_XH.setLegendVisible(true);
 		LineChart_XH.setCache(true);
 		LineChart_XH.setCacheShape(true);
 		LineChart_XH.cacheProperty();
 		LineChart_XH.cacheHintProperty();
 		LineChart_XH.setCacheHint(CacheHint.QUALITY);
 		//Rec_AxisY1.setTickLabelFill(Color.BLUE);
 		Chart_XH_AxisY.setAutoRanging(true);
 		//Rec_AxisY1.setTickLabelFill(Color.GREEN);
 		Chart_XH_AxisX.setTickLabelFill(Color.CHOCOLATE);
 		Chart_XH_AxisY.setTickLabelFill(Color.CHOCOLATE);
 		//@-XH�������ݳ�ʼ��
 		XH_ChartSeries_InputAngle = new LineChart.Series<Number, Number>();
 		XH_ChartSeries_RealAngle =  new LineChart.Series<Number, Number>();
 		XH_ChartSeries_ErrorAngle = new LineChart.Series<Number, Number>();
 		XH_ChartSeries_1 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_2 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_3 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_4 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_5 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_InputAngle.setName("�����");
 		XH_ChartSeries_RealAngle.setName ("���ӽ�");
 		XH_ChartSeries_ErrorAngle.setName("����");
 		XH_ChartSeries_1.setName("pos_out");
 		XH_ChartSeries_2.setName("pos_up");
 		XH_ChartSeries_3.setName("uFwdSpd");
 		XH_ChartSeries_4.setName("act12");
 		XH_ChartSeries_5.setName("Uzk");
 		//@-�����ݼ�������-DX
 		LineChart_XH.getData().addAll(XH_ChartSeries_ErrorAngle);
   }


   /**���߸��¶�ʱʱ����
   *
   */
  private void prepareTimeline() {

      //@1-����ʱ����
  	Curve_timeline = new Timeline();

      //@2-����ʱ����Ϊ����ѭ��
  	Curve_timeline.setCycleCount(Animation.INDEFINITE);

      //@3-ʱ�������Ӷ�ʱ�¼�
  	Curve_timeline.getKeyFrames().add(
              new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {
                  @Override public void handle(ActionEvent actionEvent) {

                  	//@4-����ƽ̨����߳�
						Platform.runLater(new Runnable() {
							@Override
							public void run() {

								//@5-���������������
					        	addDataToSeries();
							}
						});
                  }
              })
      );

      //@6-���¼���������������ʱ������
      Curve_animation = new SequentialTransition();
      Curve_animation.getChildren().addAll(Curve_timeline);

      //@7-������������״̬
      curve_run_set(true);

  }


	  /**����״̬����
	  *
	  * @param flag
	  */
	 public static void curve_run_set(boolean flag)
	 {
	 	if(flag==true)
	 	{
	 		Curve_run_status=true;
	 		Curve_animation.play();
	 	}
	 	else if(flag==false)
	 	{
	 		Curve_run_status=false;
	 		Curve_animation.stop();

	 		FY_Data_InputAngle.clear();
	 		FY_Data_RealAngle.clear();
	 		FY_Data_ErrorAngle.clear();
	 		FY_Data_1.clear();
	 		FY_Data_2.clear();
	 		FY_Data_3.clear();
	 		FY_Data_4.clear();
	 		FY_Data_5.clear();

	 		XH_Data_InputAngle.clear();
	 		XH_Data_RealAngle.clear();
	 		XH_Data_ErrorAngle.clear();
	 		XH_Data_1.clear();
	 		XH_Data_2.clear();
	 		XH_Data_3.clear();
	 		XH_Data_4.clear();
	 		XH_Data_5.clear();
	 	}
	 }


	  /**���������������
	  *
	  */
	 private void addDataToSeries() {

	    	//@1-ͬ�����߳�
	    	synchronized(this)
	    	{
	    		//@2-��׼����Դ-����Ƕ�
	    		if(FY_Curve_InputAngle_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_InputAngle)
	        		{
	    	    	    if(FY_Data_InputAngle.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_InputAngle.remove(0);
	    	    	    	FY_ChartSeries_InputAngle.getData().add(new LineChart.Data(xSeriesData++, data1));
	    	    	    	FY_Curve_UpData_DataBase = 1;
	    	    	    }
	        		}
	    		}
	    		//@3-��׼����Դ-���ӽǶ�
	    		else if(FY_Curve_RealAngle_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_RealAngle)
	        		{
	    	    	    if(FY_Data_RealAngle.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_RealAngle.remove(0);
	    	    	    	FY_ChartSeries_RealAngle.getData().add(new LineChart.Data(xSeriesData++, data2));
	    	    	    	FY_Curve_UpData_DataBase = 2;
	    	    	    }
	        		}
	    		}
	    		//@4-��׼����Դ-���Ƕ�
	    		else if(FY_Curve_ErrorAngle_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_ErrorAngle)
	        		{
	    	    	    if(FY_Data_ErrorAngle.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_ErrorAngle.remove(0);
	    	    	    	FY_ChartSeries_ErrorAngle.getData().add(new LineChart.Data(xSeriesData++, data3));
	    	    	    	FY_Curve_UpData_DataBase = 3;
	    	    	    }
	        		}
	    		}
	    		//@5-��׼����Դ-ĸ�ߵ�ѹ
	    		else if(FY_Curve_Data1_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_1)
	        		{
	    	    	    if(FY_Data_1.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_1.remove(0);
	    	    	    	FY_ChartSeries_1.getData().add(new LineChart.Data(xSeriesData++, data4));
	    	    	    	FY_Curve_UpData_DataBase = 4;
	    	    	    }
	        		}
	    		}
	    		//@6-��׼����Դ-���ת��
	    		else if(FY_Curve_Data2_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_2)
	        		{
	    	    	    if(FY_Data_2.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_2.remove(0);
	    	    	    	FY_ChartSeries_2.getData().add(new LineChart.Data(xSeriesData++, data5));
	    	    	    	FY_Curve_UpData_DataBase = 5;
	    	    	    }
	        		}
	    		}
	       		//@7-��׼����Դ-���ת��
	    		else if(FY_Curve_Data3_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_3)
	        		{
	    	    	    if(FY_Data_3.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_3.remove(0);
	    	    	    	FY_ChartSeries_3.getData().add(new LineChart.Data(xSeriesData++, data6));
	    	    	    	FY_Curve_UpData_DataBase = 6;
	    	    	    }
	        		}
	    		}
	       		//@8-��׼����Դ-�������
	    		else if(FY_Curve_Data4_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_4)
	        		{
	    	    	    if(FY_Data_4.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_4.remove(0);
	    	    	    	FY_ChartSeries_4.getData().add(new LineChart.Data(xSeriesData++, data7));
	    	    	    	FY_Curve_UpData_DataBase = 7;
	    	    	    }
	        		}
	    		}
	       		//@9-��׼����Դ-���ŵ�ѹ
	    		else if(FY_Curve_Data5_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (FY_Data_5)
	        		{
	    	    	    if(FY_Data_5.isEmpty()==false)
	    	    	    {
	    	    	    	FY_Data_5.remove(0);
	    	    	    	FY_ChartSeries_5.getData().add(new LineChart.Data(xSeriesData++, data8));
	    	    	    	FY_Curve_UpData_DataBase = 8;
	    	    	    }
	        		}
	    		}
	//--------------------------------------------------------------------------------------------------------------
	    		//@10-��׼����Դ-����Ƕ�
	    		if(XH_Curve_InputAngle_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_InputAngle)
	        		{
	    	    	    if(XH_Data_InputAngle.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_InputAngle.remove(0);
	    	    	    	XH_ChartSeries_InputAngle.getData().add(new LineChart.Data(xSeriesData1++, data9));
	    	    	    	XH_Curve_UpData_DataBase = 1;
	    	    	    }
	        		}
	    		}
	    		//@11-��׼����Դ-���ӽǶ�
	    		else if(XH_Curve_RealAngle_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_RealAngle)
	        		{
	    	    	    if(XH_Data_RealAngle.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_RealAngle.remove(0);
	    	    	    	XH_ChartSeries_RealAngle.getData().add(new LineChart.Data(xSeriesData1++, data10));
	    	    	    	XH_Curve_UpData_DataBase = 2;
	    	    	    }
	        		}
	    		}
	    		//@12-��׼����Դ-���Ƕ�
	    		else if(XH_Curve_ErrorAngle_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_ErrorAngle)
	        		{
	    	    	    if(XH_Data_ErrorAngle.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_ErrorAngle.remove(0);
	    	    	    	XH_ChartSeries_ErrorAngle.getData().add(new LineChart.Data(xSeriesData1++, data11));
	    	    	    	XH_Curve_UpData_DataBase = 3;
	    	    	    }
	        		}
	    		}
	    		//@13-��׼����Դ-ĸ�ߵ�ѹ
	    		else if(XH_Curve_Data1_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_1)
	        		{
	    	    	    if(XH_Data_1.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_1.remove(0);
	    	    	    	XH_ChartSeries_1.getData().add(new LineChart.Data(xSeriesData1++, data12));
	    	    	    	XH_Curve_UpData_DataBase = 4;
	    	    	    }
	        		}
	    		}
	    		//@14-��׼����Դ-���ת��
	    		else if(XH_Curve_Data2_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_2)
	        		{
	    	    	    if(XH_Data_2.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_2.remove(0);
	    	    	    	XH_ChartSeries_2.getData().add(new LineChart.Data(xSeriesData1++, data13));
	    	    	    	XH_Curve_UpData_DataBase = 5;
	    	    	    }
	        		}
	    		}
	       		//@15-��׼����Դ-���ת��
	    		else if(XH_Curve_Data3_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_3)
	        		{
	    	    	    if(XH_Data_3.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_3.remove(0);
	    	    	    	XH_ChartSeries_3.getData().add(new LineChart.Data(xSeriesData1++, data14));
	    	    	    	XH_Curve_UpData_DataBase = 6;
	    	    	    }
	        		}
	    		}
	       		//@16-��׼����Դ-�������
	    		else if(XH_Curve_Data4_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_4)
	        		{
	    	    	    if(XH_Data_4.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_4.remove(0);
	    	    	    	XH_ChartSeries_4.getData().add(new LineChart.Data(xSeriesData1++, data15));
	    	    	    	XH_Curve_UpData_DataBase = 7;
	    	    	    }
	        		}
	    		}
	       		//@17-��׼����Դ-���ŵ�ѹ
	    		else if(XH_Curve_Data5_Dis==true)
	    		{
	        		//@-ͬ������Դ
	        		synchronized (XH_Data_5)
	        		{
	    	    	    if(XH_Data_5.isEmpty()==false)
	    	    	    {
	    	    	    	XH_Data_5.remove(0);
	    	    	    	XH_ChartSeries_5.getData().add(new LineChart.Data(xSeriesData1++, data16));
	    	    	    	XH_Curve_UpData_DataBase = 8;
	    	    	    }
	        		}
	    		}
	//--------------------------------------------------------------------------------------------------------------
	    		//@18-FY��׼����Դ�Ƿ����
		    	if(xSeriesData_Copy!=xSeriesData)
		    	{
		    		xSeriesData_Copy=xSeriesData;
		    		FY_Curve_updata=true;
		    	}
		    	else
		    	{
		    		FY_Curve_updata=false;
		    	}
	    		//@19-XH��׼����Դ�Ƿ����
		    	if(xSeriesData1_Copy!=xSeriesData1)
		    	{
		    		xSeriesData1_Copy=xSeriesData1;
		    		XH_Curve_updata=true;
		    	}
		    	else
		    	{
		    		XH_Curve_updata=false;
		    	}
	//--------------------------------------------------------------------------------------------------------------
		    	//@20-FY��׼����Դ����
		    	if(FY_Curve_updata==true)
		    	{
		    		//@21-FY���ݻ�׼Դ������Ƕ�
	    		    if(FY_Curve_UpData_DataBase!=1)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_InputAngle_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_InputAngle)
	    	        		{
	    	    	    	    if(FY_Data_InputAngle.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_InputAngle.remove(0);
	    	    	    	    	FY_ChartSeries_InputAngle.getData().add(new LineChart.Data(xSeriesData, data1));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }
		    		//@22-FY���ݻ�׼Դ�Ǹ��ӽǶ�
	    		    if(FY_Curve_UpData_DataBase!=2)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_RealAngle_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_RealAngle)
	    	        		{
	    	    	    	    if(FY_Data_RealAngle.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_RealAngle.remove(0);
	    	    	    	    	FY_ChartSeries_RealAngle.getData().add(new LineChart.Data(xSeriesData, data2));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }
		    		//@23-FY���ݻ�׼Դ�����Ƕ�
	    		    if(FY_Curve_UpData_DataBase!=3)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_ErrorAngle_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_ErrorAngle)
	    	        		{
	    	    	    	    if(FY_Data_ErrorAngle.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_ErrorAngle.remove(0);
	    	    	    	    	FY_ChartSeries_ErrorAngle.getData().add(new LineChart.Data(xSeriesData, data3));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }
		    		//@24-FY���ݻ�׼Դ��ĸ�ߵ�ѹ
	    		    if(FY_Curve_UpData_DataBase!=4)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_Data1_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_1)
	    	        		{
	    	    	    	    if(FY_Data_1.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_1.remove(0);
	    	    	    	    	FY_ChartSeries_1.getData().add(new LineChart.Data(xSeriesData, data4));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }
		    		//@25-FY���ݻ�׼Դ�ǵ��ת��
	    		    if(FY_Curve_UpData_DataBase!=5)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_Data2_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_2)
	    	        		{
	    	    	    	    if(FY_Data_2.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_2.remove(0);
	    	    	    	    	FY_ChartSeries_2.getData().add(new LineChart.Data(xSeriesData, data5));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }
		    		//@26-FY���ݻ�׼Դ�ǵ��ת��
	    		    if(FY_Curve_UpData_DataBase!=6)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_Data3_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_3)
	    	        		{
	    	    	    	    if(FY_Data_3.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_3.remove(0);
	    	    	    	    	FY_ChartSeries_3.getData().add(new LineChart.Data(xSeriesData, data6));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }
	    		    //@27-FY���ݻ�׼Դ�ǵ������
	    		    if(FY_Curve_UpData_DataBase!=7)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_Data4_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_4)
	    	        		{
	    	    	    	    if(FY_Data_4.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_4.remove(0);
	    	    	    	    	FY_ChartSeries_4.getData().add(new LineChart.Data(xSeriesData, data7));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }
	    		    //@28-FY���ݻ�׼Դ�����ŵ�ѹ
	    		    if(FY_Curve_UpData_DataBase!=8)
	    		    {
	    	    		//@-�Ƿ���ʾ
	    	    		if(FY_Curve_Data5_Dis==true)
	    	    		{
	    	        		//@-ͬ������Դ
	    	        		synchronized (FY_Data_5)
	    	        		{
	    	    	    	    if(FY_Data_5.isEmpty()==false)
	    	    	    	    {
	    	    	    	    	FY_Data_5.remove(0);
	    	    	    	    	FY_ChartSeries_5.getData().add(new LineChart.Data(xSeriesData, data8));
	    	    	    	    }
	    	        		}
	    	    		}
	    		    }


		   		    //@29-���߳��������ʾ��-����Ƕ�
			        if (FY_ChartSeries_InputAngle.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_InputAngle_Dis==true)
			    		{
			    			FY_ChartSeries_InputAngle.getData().remove(0);
			    		}
			        }
		   		    //@30-���߳��������ʾ��-���ӽǶ�
			        if (FY_ChartSeries_RealAngle.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_RealAngle_Dis==true)
			    		{
			    			FY_ChartSeries_RealAngle.getData().remove(0);
			    		}
			        }
		   		    //@31-���߳��������ʾ��-���Ƕ�
			        if (FY_ChartSeries_ErrorAngle.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_ErrorAngle_Dis==true)
			    		{
			    			FY_ChartSeries_ErrorAngle.getData().remove(0);
			    		}
			        }
		   		    //@32-���߳��������ʾ��-ĸ�ߵ�ѹ
			        if (FY_ChartSeries_1.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data1_Dis==true)
			    		{
			    			FY_ChartSeries_1.getData().remove(0);
			    		}
			        }
		   		    //@33-���߳��������ʾ��-���ת��
			        if (FY_ChartSeries_2.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data2_Dis==true)
			    		{
			    			FY_ChartSeries_2.getData().remove(0);
			    		}
			        }
		   		    //@34-���߳��������ʾ��-���ת��
			        if (FY_ChartSeries_3.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data3_Dis==true)
			    		{
			    			FY_ChartSeries_3.getData().remove(0);
			    		}
			        }
		   		    //@35-���߳��������ʾ��-�������
			        if (FY_ChartSeries_4.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data4_Dis==true)
			    		{
			    			FY_ChartSeries_4.getData().remove(0);
			    		}
			        }
		   		    //@36-���߳��������ʾ��-���ŵ�ѹ
			        if (FY_ChartSeries_5.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data5_Dis==true)
			    		{
			    			FY_ChartSeries_5.getData().remove(0);
			    		}
			        }

		        //@37-���߸��±߽���ʾ
		        Chart_FY_AxisX.setLowerBound(xSeriesData-MAX_DATA_POINTS);
		        Chart_FY_AxisX.setUpperBound(xSeriesData-1);
	        }
	//--------------------------------------------------------------------------------------------------------------
	    	//@20-XH��׼����Դ����
	    	if(XH_Curve_updata==true)
	    	{
	    		//@21-XH���ݻ�׼Դ������Ƕ�
			    if(XH_Curve_UpData_DataBase!=1)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_InputAngle_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_InputAngle)
		        		{
		    	    	    if(XH_Data_InputAngle.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_InputAngle.remove(0);
		    	    	    	XH_ChartSeries_InputAngle.getData().add(new LineChart.Data(xSeriesData, data9));
		    	    	    }
		        		}
		    		}
			    }
	    		//@22-XH���ݻ�׼Դ�Ǹ��ӽǶ�
			    if(XH_Curve_UpData_DataBase!=2)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_RealAngle_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_RealAngle)
		        		{
		    	    	    if(XH_Data_RealAngle.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_RealAngle.remove(0);
		    	    	    	XH_ChartSeries_RealAngle.getData().add(new LineChart.Data(xSeriesData, data10));
		    	    	    }
		        		}
		    		}
			    }
	    		//@23-XH���ݻ�׼Դ�����Ƕ�
			    if(XH_Curve_UpData_DataBase!=3)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_ErrorAngle_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_ErrorAngle)
		        		{
		    	    	    if(XH_Data_ErrorAngle.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_ErrorAngle.remove(0);
		    	    	    	XH_ChartSeries_ErrorAngle.getData().add(new LineChart.Data(xSeriesData, data11));
		    	    	    }
		        		}
		    		}
			    }
	    		//@24-XH���ݻ�׼Դ��ĸ�ߵ�ѹ
			    if(XH_Curve_UpData_DataBase!=4)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_Data1_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_1)
		        		{
		    	    	    if(XH_Data_1.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_1.remove(0);
		    	    	    	XH_ChartSeries_1.getData().add(new LineChart.Data(xSeriesData, data12));
		    	    	    }
		        		}
		    		}
			    }
	    		//@25-XH���ݻ�׼Դ�ǵ��ת��
			    if(XH_Curve_UpData_DataBase!=5)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_Data2_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_2)
		        		{
		    	    	    if(XH_Data_2.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_2.remove(0);
		    	    	    	XH_ChartSeries_2.getData().add(new LineChart.Data(xSeriesData, data13));
		    	    	    }
		        		}
		    		}
			    }
	    		//@26-XH���ݻ�׼Դ�ǵ��ת��
			    if(XH_Curve_UpData_DataBase!=6)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_Data3_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_3)
		        		{
		    	    	    if(XH_Data_3.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_3.remove(0);
		    	    	    	XH_ChartSeries_3.getData().add(new LineChart.Data(xSeriesData, data14));
		    	    	    }
		        		}
		    		}
			    }
			    //@27-XH���ݻ�׼Դ�ǵ������
			    if(XH_Curve_UpData_DataBase!=7)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_Data4_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_4)
		        		{
		    	    	    if(XH_Data_4.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_4.remove(0);
		    	    	    	XH_ChartSeries_4.getData().add(new LineChart.Data(xSeriesData, data15));
		    	    	    }
		        		}
		    		}
			    }
			    //@28-XH���ݻ�׼Դ�����ŵ�ѹ
			    if(XH_Curve_UpData_DataBase!=8)
			    {
		    		//@-�Ƿ���ʾ
		    		if(XH_Curve_Data5_Dis==true)
		    		{
		        		//@-ͬ������Դ
		        		synchronized (XH_Data_5)
		        		{
		    	    	    if(XH_Data_5.isEmpty()==false)
		    	    	    {
		    	    	    	XH_Data_5.remove(0);
		    	    	    	XH_ChartSeries_5.getData().add(new LineChart.Data(xSeriesData, data16));
		    	    	    }
		        		}
		    		}
			    }


	   		    //@29-���߳��������ʾ��-����Ƕ�
		        if (XH_ChartSeries_InputAngle.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_InputAngle_Dis==true)
		    		{
		    			XH_ChartSeries_InputAngle.getData().remove(0);
		    		}
		        }
	   		    //@30-���߳��������ʾ��-���ӽǶ�
		        if (XH_ChartSeries_RealAngle.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_RealAngle_Dis==true)
		    		{
		    			XH_ChartSeries_RealAngle.getData().remove(0);
		    		}
		        }
	   		    //@31-���߳��������ʾ��-���Ƕ�
		        if (XH_ChartSeries_ErrorAngle.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_ErrorAngle_Dis==true)
		    		{
		    			XH_ChartSeries_ErrorAngle.getData().remove(0);
		    		}
		        }
	   		    //@32-���߳��������ʾ��-ĸ�ߵ�ѹ
		        if (XH_ChartSeries_1.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data1_Dis==true)
		    		{
		    			XH_ChartSeries_1.getData().remove(0);
		    		}
		        }
	   		    //@33-���߳��������ʾ��-���ת��
		        if (XH_ChartSeries_2.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data2_Dis==true)
		    		{
		    			XH_ChartSeries_2.getData().remove(0);
		    		}
		        }
	   		    //@34-���߳��������ʾ��-���ת��
		        if (XH_ChartSeries_3.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data3_Dis==true)
		    		{
		    			XH_ChartSeries_3.getData().remove(0);
		    		}
		        }
	   		    //@35-���߳��������ʾ��-�������
		        if (XH_ChartSeries_4.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data4_Dis==true)
		    		{
		    			XH_ChartSeries_4.getData().remove(0);
		    		}
		        }
	   		    //@36-���߳��������ʾ��-���ŵ�ѹ
		        if (XH_ChartSeries_5.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data5_Dis==true)
		    		{
		    			XH_ChartSeries_5.getData().remove(0);
		    		}
		        }

		        //@37-���߸��±߽���ʾ
		        Chart_XH_AxisX.setLowerBound(xSeriesData-MAX_DATA_POINTS);
		        Chart_XH_AxisX.setUpperBound(xSeriesData-1);
	    	}

	    }

	  }



    /**����������
    *
    * @param event
    */
    @FXML
    public void Button_Pro(ActionEvent event)
    {
    	//@-���㰴ť
    	if((event.getSource() == MK3_FY_Save_Clean))
    	{
    		Net_Main_Receive.FYDsp_NetReceive_Count = 0;
    		Net_Main_Receive.XHDsp_NetReceive_Count = 0;
    	}

    	//@-���԰�ť
    	if((event.getSource() == MK3_FY_Test))
    	{
    		MK3_FY_Test_Count.setText("��¼������������:"+Net_Main_Receive.FYDsp_NetReceive_Count);
    		MK3_XH_Test_Count.setText("��¼������������:"+Net_Main_Receive.XHDsp_NetReceive_Count);
    	}

    	//@-���㰴ť
    	if((event.getSource() == MK3_XH_Save_Clean))
    	{
    		Net_Main_Receive.FYDsp_NetReceive_Count = 0;
    		Net_Main_Receive.XHDsp_NetReceive_Count = 0;
    	}
    	//@-���԰�ť
    	if((event.getSource() == MK3_XH_Test))
    	{
    		MK3_FY_Test_Count.setText("��¼������������:"+Net_Main_Receive.FYDsp_NetReceive_Count);
    		MK3_XH_Test_Count.setText("��¼������������:"+Net_Main_Receive.XHDsp_NetReceive_Count);
    	}

    	//@-������ť
    	if((event.getSource() == NetCard_Name_Button))
    	{
    		Net_Main.NetCard_Set = true;
    		Net_Main.NetCard_Name = new String(NetCard_Name.getText());
    	}

    	//@-��������
    	if((event.getSource() == MK3_SaveData_CheckBox))
    	{
    		 if(MK3_SaveData_CheckBox.isSelected() == true)
    		 {
    			 MK3_SaveData_Flag = true;
    		 }
    		 else if(MK3_SaveData_CheckBox.isSelected() == false)
    		 {
    			 MK3_SaveData_Flag = false;
    		 }
    		 //System.out.println("flag:"+MK3_SaveData_Flag);
    	}

    	//@-FY����ѡ��-�����
    	if((event.getSource()==MK3_FY_InputAngel_CheckBox))
    	{
    		Curve_Display_Pro(1,1);
    	}
    	//@-FY����ѡ��-���ӽ�
    	else if((event.getSource()==MK3_FY_RealAngel_CheckBox))
    	{
    		Curve_Display_Pro(1,2);
    	}
    	//@-FY����ѡ��-����
    	else if((event.getSource()==MK3_FY_ErrorAngel_CheckBox))
    	{
    		Curve_Display_Pro(1,3);
    	}
    	//@-FY����ѡ��-pos_out
    	else if((event.getSource()==MK3_FY_pos_out_CheckBox))
    	{
    		Curve_Display_Pro(1,4);
    	}
    	//@-FY����ѡ��-pos_up
    	else if((event.getSource()==MK3_FY_pos_up_CheckBox))
    	{
    		Curve_Display_Pro(1,5);
    	}
    	//@-FY����ѡ��-uFwdSpd
    	else if((event.getSource()==MK3_FY_uFwdSpd_CheckBox))
    	{
    		Curve_Display_Pro(1,6);
    	}
    	//@-FY����ѡ��-act12
    	else if((event.getSource()==MK3_FY_act12_CheckBox))
    	{
    		Curve_Display_Pro(1,7);
    	}
    	//@-FY����ѡ��-Uzk
    	else if((event.getSource()==MK3_FY_Uzk_CheckBox))
    	{
    		Curve_Display_Pro(1,8);
    	}

     	//@-XH����ѡ��-�����
    	if((event.getSource()==MK3_XH_InputAngel_CheckBox))
    	{
    		Curve_Display_Pro(2,1);
    	}
    	//@-XH����ѡ��-���ӽ�
    	else if((event.getSource()==MK3_XH_RealAngel_CheckBox))
    	{
    		Curve_Display_Pro(2,2);
    	}
    	//@-XH����ѡ��-����
    	else if((event.getSource()==MK3_XH_ErrorAngel_CheckBox))
    	{
    		Curve_Display_Pro(2,3);
    	}
    	//@-XH����ѡ��-pos_out
    	else if((event.getSource()==MK3_XH_pos_out_CheckBox))
    	{
    		Curve_Display_Pro(2,4);
    	}
    	//@-XH����ѡ��-pos_up
    	else if((event.getSource()==MK3_XH_pos_up_CheckBox))
    	{
    		Curve_Display_Pro(2,5);
    	}
    	//@-XH����ѡ��-uFwdSpd
    	else if((event.getSource()==MK3_XH_uFwdSpd_CheckBox))
    	{
    		Curve_Display_Pro(2,6);
    	}
    	//@-XH����ѡ��-act12
    	else if((event.getSource()==MK3_XH_act12_CheckBox))
    	{
    		Curve_Display_Pro(2,7);
    	}
    	//@-XH����ѡ��-Uzk
    	else if((event.getSource()==MK3_XH_Uzk_CheckBox))
    	{
    		Curve_Display_Pro(2,8);
    	}


    }



    /**����������ʾѡ����
    *
    * @param Display_Num
    */
   private void Curve_Display_Pro(int Mode,int Display_Num)
   {
	    //@-FY
	    if(Mode == 1)
	    {
		   	switch(Display_Num)
		   	{
		   	    //@1-�����
		   		case 1:
		   			   if(MK3_FY_InputAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-��ʾС�������ʾ��
		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
		   				   {
			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
			   				   FY_Curve_InputAngle_Dis = true;
			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
			   				   if(FY_Curve_InputAngle_Remove==true)
			   				   {
			   					   FY_Curve_InputAngle_Remove=false;
			   					   LineChart_FY.getData().add(FY_ChartSeries_InputAngle);
			   				   }
		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_InputAngel_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
		   			   }
		   			   else if(MK3_FY_InputAngel_CheckBox.isSelected()==false)
		   			   {
		   				   if(FY_Curve_Display_Check()==false)
		   				   {
		   					   MK3_FY_InputAngel_CheckBox.setSelected(true);
		   					   FY_Curve_InputAngle_Dis=true;
		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
		       				   if(FY_Curve_InputAngle_Remove==true)
		       				   {
		       					   FY_Curve_InputAngle_Remove=false;
		       					   LineChart_FY.getData().add(FY_ChartSeries_InputAngle);
		       				   }
		   				   }
		   				   else if(FY_Curve_Display_Check()==true)
		   				   {
		   					   MK3_FY_InputAngel_CheckBox.setSelected(false);
		   					   FY_Curve_InputAngle_Dis=false;
		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
		   					   //@�Ƴ�������
		   					   FY_Curve_InputAngle_Remove=true;
		   					   LineChart_FY.getData().remove(FY_ChartSeries_InputAngle);
		   				   }
		   			   }
		   			   break;

		   	    //@2-���ӽ�
		   		case 2:
		   			   if(MK3_FY_RealAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-��ʾС�������ʾ��
		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
		   				   {
			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
			   				   FY_Curve_RealAngle_Dis = true;
			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
			   				   if(FY_Curve_RealAngle_Remove==true)
			   				   {
			   					   FY_Curve_RealAngle_Remove=false;
			   					   LineChart_FY.getData().add(FY_ChartSeries_RealAngle);
			   				   }
		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_RealAngel_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
		   			   }
		   			   else if(MK3_FY_RealAngel_CheckBox.isSelected()==false)
		   			   {
		   				   if(FY_Curve_Display_Check()==false)
		   				   {
		   					   MK3_FY_RealAngel_CheckBox.setSelected(true);
		   					   FY_Curve_RealAngle_Dis=true;
		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
		       				   if(FY_Curve_RealAngle_Remove==true)
		       				   {
		       					   FY_Curve_RealAngle_Remove=false;
		       					   LineChart_FY.getData().add(FY_ChartSeries_RealAngle);
		       				   }
		   				   }
		   				   else if(FY_Curve_Display_Check()==true)
		   				   {
		   					   MK3_FY_RealAngel_CheckBox.setSelected(false);
		   					   FY_Curve_RealAngle_Dis=false;
		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
		   					   //@�Ƴ�������
		   					   FY_Curve_RealAngle_Remove=true;
		   					   LineChart_FY.getData().remove(FY_ChartSeries_RealAngle);
		   				   }
		   			   }
		   			   break;

	   	   	    //@3-����
   		   		case 3:
   		   			   if(MK3_FY_ErrorAngel_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_ErrorAngle_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(FY_Curve_ErrorAngle_Remove==true)
   			   				   {
   			   					   FY_Curve_ErrorAngle_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_ErrorAngle);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_ErrorAngel_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
   		   			   }
   		   			   else if(MK3_FY_ErrorAngel_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(FY_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_FY_ErrorAngel_CheckBox.setSelected(true);
   		   					   FY_Curve_ErrorAngle_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(FY_Curve_ErrorAngle_Remove==true)
   		       				   {
   		       					   FY_Curve_ErrorAngle_Remove=false;
   		       					   LineChart_FY.getData().add(FY_ChartSeries_ErrorAngle);
   		       				   }
   		   				   }
   		   				   else if(FY_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_FY_ErrorAngel_CheckBox.setSelected(false);
   		   					   FY_Curve_ErrorAngle_Dis=false;
   		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   FY_Curve_ErrorAngle_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_ErrorAngle);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@4-pos_out
   		   		case 4:
   		   			   if(MK3_FY_pos_out_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data1_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(FY_Curve_Data1_Remove==true)
   			   				   {
   			   					   FY_Curve_Data1_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_1);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_pos_out_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
   		   			   }
   		   			   else if(MK3_FY_pos_out_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(FY_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_FY_pos_out_CheckBox.setSelected(true);
   		   					   FY_Curve_Data1_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(FY_Curve_Data1_Remove==true)
   		       				   {
   		       					   FY_Curve_Data1_Remove=false;
   		       					   LineChart_FY.getData().add(FY_ChartSeries_1);
   		       				   }
   		   				   }
   		   				   else if(FY_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_FY_pos_out_CheckBox.setSelected(false);
   		   					   FY_Curve_Data1_Dis=false;
   		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   FY_Curve_Data1_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_1);
   		   				   }
   		   			   }
   		   			   break;

   	  		   	//@5-pos_up
   		   		case 5:
   		   			   if(MK3_FY_pos_up_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data2_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(FY_Curve_Data2_Remove==true)
   			   				   {
   			   					   FY_Curve_Data2_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_2);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_pos_up_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_FY_pos_up_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(FY_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_FY_pos_up_CheckBox.setSelected(true);
   		   					   FY_Curve_Data2_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(FY_Curve_Data2_Remove==true)
   		       				   {
   		       					   FY_Curve_Data2_Remove=false;
   		       					   LineChart_FY.getData().add(FY_ChartSeries_2);
   		       				   }
   		   				   }
   		   				   else if(FY_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_FY_pos_up_CheckBox.setSelected(false);
   		   					   FY_Curve_Data2_Dis=false;
   		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   FY_Curve_Data2_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_2);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@6-uFwdSpd
   		   		case 6:
   		   			   if(MK3_FY_uFwdSpd_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data3_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(FY_Curve_Data3_Remove==true)
   			   				   {
   			   					   FY_Curve_Data3_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_3);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_uFwdSpd_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_FY_uFwdSpd_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(FY_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_FY_uFwdSpd_CheckBox.setSelected(true);
   		   					   FY_Curve_Data3_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(FY_Curve_Data3_Remove==true)
   		       				   {
   		       					   FY_Curve_Data3_Remove=false;
   		       					   LineChart_FY.getData().add(FY_ChartSeries_3);
   		       				   }
   		   				   }
   		   				   else if(FY_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_FY_uFwdSpd_CheckBox.setSelected(false);
   		   					   FY_Curve_Data3_Dis=false;
   		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   FY_Curve_Data3_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_3);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@7-act12
   		   		case 7:
   		   			   if(MK3_FY_act12_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data4_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(FY_Curve_Data4_Remove==true)
   			   				   {
   			   					   FY_Curve_Data4_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_4);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_act12_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_FY_act12_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(FY_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_FY_act12_CheckBox.setSelected(true);
   		   					   FY_Curve_Data4_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(FY_Curve_Data4_Remove==true)
   		       				   {
   		       					   FY_Curve_Data4_Remove=false;
   		       					   LineChart_FY.getData().add(FY_ChartSeries_4);
   		       				   }
   		   				   }
   		   				   else if(FY_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_FY_act12_CheckBox.setSelected(false);
   		   					   FY_Curve_Data4_Dis=false;
   		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   FY_Curve_Data4_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_4);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@8-Uzk
   		   		case 8:
   		   			   if(MK3_FY_Uzk_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data5_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(FY_Curve_Data5_Remove==true)
   			   				   {
   			   					   FY_Curve_Data5_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_5);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_FY_Uzk_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_FY_Uzk_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(FY_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_FY_Uzk_CheckBox.setSelected(true);
   		   					   FY_Curve_Data5_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(FY_Curve_Data5_Remove==true)
   		       				   {
   		       					   FY_Curve_Data5_Remove=false;
   		       					   LineChart_FY.getData().add(FY_ChartSeries_5);
   		       				   }
   		   				   }
   		   				   else if(FY_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_FY_Uzk_CheckBox.setSelected(false);
   		   					   FY_Curve_Data5_Dis=false;
   		   					   FY_Curve_Display_Count = FY_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   FY_Curve_Data5_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_5);
   		   				   }
   		   			   }
   		   			   break;

		   	     default: break;
		   	}
	    }

	    //@-XH
	    if(Mode == 2)
	    {
		   	switch(Display_Num)
		   	{
		   	    //@1-�����
		   		case 1:
		   			   if(MK3_XH_InputAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-��ʾС�������ʾ��
		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
		   				   {
			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
			   				   XH_Curve_InputAngle_Dis = true;
			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
			   				   if(XH_Curve_InputAngle_Remove==true)
			   				   {
			   					   XH_Curve_InputAngle_Remove=false;
			   					   LineChart_XH.getData().add(XH_ChartSeries_InputAngle);
			   				   }
		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_InputAngel_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
		   			   }
		   			   else if(MK3_XH_InputAngel_CheckBox.isSelected()==false)
		   			   {
		   				   if(XH_Curve_Display_Check()==false)
		   				   {
		   					   MK3_XH_InputAngel_CheckBox.setSelected(true);
		   					   XH_Curve_InputAngle_Dis=true;
		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
		       				   if(XH_Curve_InputAngle_Remove==true)
		       				   {
		       					   XH_Curve_InputAngle_Remove=false;
		       					   LineChart_XH.getData().add(XH_ChartSeries_InputAngle);
		       				   }
		   				   }
		   				   else if(XH_Curve_Display_Check()==true)
		   				   {
		   					   MK3_XH_InputAngel_CheckBox.setSelected(false);
		   					   XH_Curve_InputAngle_Dis=false;
		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
		   					   //@�Ƴ�������
		   					   XH_Curve_InputAngle_Remove=true;
		   					   LineChart_XH.getData().remove(XH_ChartSeries_InputAngle);
		   				   }
		   			   }
		   			   break;

		   	    //@2-���ӽ�
		   		case 2:
		   			   if(MK3_XH_RealAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-��ʾС�������ʾ��
		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
		   				   {
			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
			   				   XH_Curve_RealAngle_Dis = true;
			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
			   				   if(XH_Curve_RealAngle_Remove==true)
			   				   {
			   					   XH_Curve_RealAngle_Remove=false;
			   					   LineChart_XH.getData().add(XH_ChartSeries_RealAngle);
			   				   }
		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_RealAngel_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
		   			   }
		   			   else if(MK3_XH_RealAngel_CheckBox.isSelected()==false)
		   			   {
		   				   if(XH_Curve_Display_Check()==false)
		   				   {
		   					   MK3_XH_RealAngel_CheckBox.setSelected(true);
		   					   XH_Curve_RealAngle_Dis=true;
		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
		       				   if(XH_Curve_RealAngle_Remove==true)
		       				   {
		       					   XH_Curve_RealAngle_Remove=false;
		       					   LineChart_XH.getData().add(XH_ChartSeries_RealAngle);
		       				   }
		   				   }
		   				   else if(XH_Curve_Display_Check()==true)
		   				   {
		   					   MK3_XH_RealAngel_CheckBox.setSelected(false);
		   					   XH_Curve_RealAngle_Dis=false;
		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
		   					   //@�Ƴ�������
		   					   XH_Curve_RealAngle_Remove=true;
		   					   LineChart_XH.getData().remove(XH_ChartSeries_RealAngle);
		   				   }
		   			   }
		   			   break;

	   	   	    //@3-����
   		   		case 3:
   		   			   if(MK3_XH_ErrorAngel_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_ErrorAngle_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(XH_Curve_ErrorAngle_Remove==true)
   			   				   {
   			   					   XH_Curve_ErrorAngle_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_ErrorAngle);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_ErrorAngel_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
   		   			   }
   		   			   else if(MK3_XH_ErrorAngel_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(XH_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_XH_ErrorAngel_CheckBox.setSelected(true);
   		   					   XH_Curve_ErrorAngle_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(XH_Curve_ErrorAngle_Remove==true)
   		       				   {
   		       					   XH_Curve_ErrorAngle_Remove=false;
   		       					   LineChart_XH.getData().add(XH_ChartSeries_ErrorAngle);
   		       				   }
   		   				   }
   		   				   else if(XH_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_XH_ErrorAngel_CheckBox.setSelected(false);
   		   					   XH_Curve_ErrorAngle_Dis=false;
   		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   XH_Curve_ErrorAngle_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_ErrorAngle);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@4-pos_out
   		   		case 4:
   		   			   if(MK3_XH_pos_out_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data1_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(XH_Curve_Data1_Remove==true)
   			   				   {
   			   					   XH_Curve_Data1_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_1);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_pos_out_CheckBox.setSelected(false);
		   					   //System.out.println("out max");
		   				   }
   		   			   }
   		   			   else if(MK3_XH_pos_out_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(XH_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_XH_pos_out_CheckBox.setSelected(true);
   		   					   XH_Curve_Data1_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(XH_Curve_Data1_Remove==true)
   		       				   {
   		       					   XH_Curve_Data1_Remove=false;
   		       					   LineChart_XH.getData().add(XH_ChartSeries_1);
   		       				   }
   		   				   }
   		   				   else if(XH_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_XH_pos_out_CheckBox.setSelected(false);
   		   					   XH_Curve_Data1_Dis=false;
   		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   XH_Curve_Data1_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_1);
   		   				   }
   		   			   }
   		   			   break;

   	  		   	//@5-pos_up
   		   		case 5:
   		   			   if(MK3_XH_pos_up_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data2_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(XH_Curve_Data2_Remove==true)
   			   				   {
   			   					   XH_Curve_Data2_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_2);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_pos_up_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_XH_pos_up_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(XH_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_XH_pos_up_CheckBox.setSelected(true);
   		   					   XH_Curve_Data2_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(XH_Curve_Data2_Remove==true)
   		       				   {
   		       					   XH_Curve_Data2_Remove=false;
   		       					   LineChart_XH.getData().add(XH_ChartSeries_2);
   		       				   }
   		   				   }
   		   				   else if(XH_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_XH_pos_up_CheckBox.setSelected(false);
   		   					   XH_Curve_Data2_Dis=false;
   		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   XH_Curve_Data2_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_2);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@6-uFwdSpd
   		   		case 6:
   		   			   if(MK3_XH_uFwdSpd_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data3_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(XH_Curve_Data3_Remove==true)
   			   				   {
   			   					   XH_Curve_Data3_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_3);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_uFwdSpd_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_XH_uFwdSpd_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(XH_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_XH_uFwdSpd_CheckBox.setSelected(true);
   		   					   XH_Curve_Data3_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(XH_Curve_Data3_Remove==true)
   		       				   {
   		       					   XH_Curve_Data3_Remove=false;
   		       					   LineChart_XH.getData().add(XH_ChartSeries_3);
   		       				   }
   		   				   }
   		   				   else if(XH_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_XH_uFwdSpd_CheckBox.setSelected(false);
   		   					   XH_Curve_Data3_Dis=false;
   		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   XH_Curve_Data3_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_3);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@7-act12
   		   		case 7:
   		   			   if(MK3_XH_act12_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data4_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(XH_Curve_Data4_Remove==true)
   			   				   {
   			   					   XH_Curve_Data4_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_4);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_act12_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_XH_act12_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(XH_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_XH_act12_CheckBox.setSelected(true);
   		   					   XH_Curve_Data4_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(XH_Curve_Data4_Remove==true)
   		       				   {
   		       					   XH_Curve_Data4_Remove=false;
   		       					   LineChart_XH.getData().add(XH_ChartSeries_4);
   		       				   }
   		   				   }
   		   				   else if(XH_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_XH_act12_CheckBox.setSelected(false);
   		   					   XH_Curve_Data4_Dis=false;
   		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   XH_Curve_Data4_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_4);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@8-Uzk
   		   		case 8:
   		   			   if(MK3_XH_Uzk_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-��ʾС�������ʾ��
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data5_Dis = true;
   			   				   //@-�����Ƴ���ʶ�ж��Ƿ����
   			   				   if(XH_Curve_Data5_Remove==true)
   			   				   {
   			   					   XH_Curve_Data5_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_5);
   			   				   }
   		   				   }
		   				   //@-������ʾ-������ʾ���ֵ
		   				   else
		   				   {
		   					   MK3_XH_Uzk_CheckBox.setSelected(false);
		   					   //System.up.println("up max");
		   				   }
   		   			   }
   		   			   else if(MK3_XH_Uzk_CheckBox.isSelected()==false)
   		   			   {
   		   				   if(XH_Curve_Display_Check()==false)
   		   				   {
   		   					   MK3_XH_Uzk_CheckBox.setSelected(true);
   		   					   XH_Curve_Data5_Dis=true;
   		       				   //@-�����Ƴ���ʶ�ж��Ƿ����
   		       				   if(XH_Curve_Data5_Remove==true)
   		       				   {
   		       					   XH_Curve_Data5_Remove=false;
   		       					   LineChart_XH.getData().add(XH_ChartSeries_5);
   		       				   }
   		   				   }
   		   				   else if(XH_Curve_Display_Check()==true)
   		   				   {
   		   					   MK3_XH_Uzk_CheckBox.setSelected(false);
   		   					   XH_Curve_Data5_Dis=false;
   		   					   XH_Curve_Display_Count = XH_Curve_Display_Count - 1;
   		   					   //@�Ƴ�������
   		   					   XH_Curve_Data5_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_5);
   		   				   }
   		   			   }
   		   			   break;

		   	     default: break;
		   	}
	    }
   }


	   /**���������ʾ����
	   *
	   * @return
	   */
	  private boolean FY_Curve_Display_Check()
	  {
	  	//�������һ��������ʾ
	  	if(FY_Curve_Display_Count>1)
	  	{
	  		return true;
	  	}
	  	else
	  	{
	  		return false;
	  	}
	  }

	   /**���������ʾ����
	   *
	   * @return
	   */
	  private boolean XH_Curve_Display_Check()
	  {
	  	//�������һ��������ʾ
	  	if(XH_Curve_Display_Count>1)
	  	{
	  		return true;
	  	}
	  	else
	  	{
	  		return false;
	  	}
	  }


    /**
     *
     */
	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		myController = screenPage;
	}

}
