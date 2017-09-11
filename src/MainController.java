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


	//@-系统时间
	@FXML
	private Label MK3_System_Time;


	//@-网络链接指示灯
	@FXML
	private ImageView MK3_NetLink_ImageView;


	//@-FY曲线
    @FXML
    private LineChart  LineChart_FY;
    @FXML
    private NumberAxis Chart_FY_AxisX;
    @FXML
    private NumberAxis Chart_FY_AxisY;

	//@-曲线选择-1-输入角
	@FXML
	private CheckBox MK3_FY_InputAngel_CheckBox;
	//@-曲线选择-2-复视角
	@FXML
	private CheckBox MK3_FY_RealAngel_CheckBox;
	//@-曲线选择-3-误差角
	@FXML
	private CheckBox MK3_FY_ErrorAngel_CheckBox;
	//@-曲线选择-4-pos_out
	@FXML
	private CheckBox MK3_FY_pos_out_CheckBox;
	//@-曲线选择-5-pos_up
	@FXML
	private CheckBox MK3_FY_pos_up_CheckBox;
	//@-曲线选择-6-uFwdSpd
	@FXML
	private CheckBox MK3_FY_uFwdSpd_CheckBox;
	//@-曲线选择-7-act12
	@FXML
	private CheckBox MK3_FY_act12_CheckBox;
	//@-曲线选择-8-Uzk
	@FXML
	private CheckBox MK3_FY_Uzk_CheckBox;
	//@-曲线选择-9-Izk
	@FXML
	private CheckBox MK3_FY_Izk_CheckBox;
	//@-曲线选择-10-Iu
	@FXML
	private CheckBox MK3_FY_Iu_CheckBox;
	//@-曲线选择-11-Spe_Ref
	@FXML
	private CheckBox MK3_FY_Spe_Ref_CheckBox;
	//@-曲线选择-12-Spe_err
	@FXML
	private CheckBox MK3_FY_Spe_err_CheckBox;
	//@-曲线选择-13-id_err
	@FXML
	private CheckBox MK3_FY_id_err_CheckBox;
	//@-曲线选择-14-iq_Fbk
	@FXML
	private CheckBox MK3_FY_iq_Fbk_CheckBox;
	//@-曲线选择-15-iq_Ref
	@FXML
	private CheckBox MK3_FY_iq_Ref_CheckBox;
	//@-曲线选择-16-iq_err
	@FXML
	private CheckBox MK3_FY_iq_err_CheckBox;



    //@-XH曲线
    @FXML
    private LineChart  LineChart_XH;
    @FXML
    private NumberAxis Chart_XH_AxisX;
    @FXML
    private NumberAxis Chart_XH_AxisY;

  //@-曲线选择-1-输入角
  	@FXML
  	private CheckBox MK3_XH_InputAngel_CheckBox;
  	//@-曲线选择-2-复视角
  	@FXML
  	private CheckBox MK3_XH_RealAngel_CheckBox;
  	//@-曲线选择-3-误差角
  	@FXML
  	private CheckBox MK3_XH_ErrorAngel_CheckBox;
  	//@-曲线选择-4-pos_out
  	@FXML
  	private CheckBox MK3_XH_pos_out_CheckBox;
  	//@-曲线选择-5-pos_up
  	@FXML
  	private CheckBox MK3_XH_pos_up_CheckBox;
  	//@-曲线选择-6-uFwdSpd
  	@FXML
  	private CheckBox MK3_XH_uFwdSpd_CheckBox;
  	//@-曲线选择-7-act12
  	@FXML
  	private CheckBox MK3_XH_act12_CheckBox;
  	//@-曲线选择-8-Uzk
  	@FXML
  	private CheckBox MK3_XH_Uzk_CheckBox;
  	//@-曲线选择-9-Izk
  	@FXML
  	private CheckBox MK3_XH_Izk_CheckBox;
  	//@-曲线选择-10-Iu
  	@FXML
  	private CheckBox MK3_XH_Iu_CheckBox;
  	//@-曲线选择-11-Spe_Ref
  	@FXML
  	private CheckBox MK3_XH_Spe_Ref_CheckBox;
  	//@-曲线选择-12-Spe_err
  	@FXML
  	private CheckBox MK3_XH_Spe_err_CheckBox;
  	//@-曲线选择-13-id_err
  	@FXML
  	private CheckBox MK3_XH_id_err_CheckBox;
  	//@-曲线选择-14-iq_Fbk
  	@FXML
  	private CheckBox MK3_XH_iq_Fbk_CheckBox;
  	//@-曲线选择-15-iq_Ref
  	@FXML
  	private CheckBox MK3_XH_iq_Ref_CheckBox;
  	//@-曲线选择-16-iq_err
  	@FXML
  	private CheckBox MK3_XH_iq_err_CheckBox;


	//@-数据记录条数
	@FXML
	private Label MK3_FY_Save_Count;
	//@-测试数据记录条数
	@FXML
	private Label MK3_FY_Test_Count;

	//@-数据记录计数清零按钮
	@FXML
	private Button MK3_FY_Save_Clean;
	//@-测试数据记录按钮
	@FXML
	private Button MK3_FY_Test;

	//@-FY输入角度
	@FXML
	private Label MK3_FY_InputAngel;
	//@-FY复视角度
	@FXML
	private Label MK3_FY_RealAngel;
	//@-FY误差角度
	@FXML
	private Label MK3_FY_ErrorAngel;



	//@-数据记录条数
	@FXML
	private Label MK3_XH_Save_Count;
	//@-测试数据记录条数
	@FXML
	private Label MK3_XH_Test_Count;

	//@-数据记录计数清零按钮
	@FXML
	private Button MK3_XH_Save_Clean;
	//@-测试数据记录按钮
	@FXML
	private Button MK3_XH_Test;

	//@-XH输入角度
	@FXML
	private Label MK3_XH_InputAngel;
	//@-XH复视角度
	@FXML
	private Label MK3_XH_RealAngel;
	//@-XH误差角度
	@FXML
	private Label MK3_XH_ErrorAngel;

	@FXML
	private CheckBox MK3_SaveData_CheckBox;

	@FXML
	private TextField NetCard_Name;
	//@-网卡名确定按钮
	@FXML
	private Button NetCard_Name_Button;



//-------------------------------------------------------------------------------------------

	//@1-传递主应用程序接口
	private ScreensController myController;


    //@24-主界面显示同步
	public static SimpleStringProperty DisplayProperty_Main = new SimpleStringProperty();
    //@34-主界面显示监听器
	private ChangeListener ChangeListen_Display;

	//@6-显示数据格式
	private java.text.NumberFormat  formater_decimal  =  java.text.DecimalFormat.getInstance();  //显示小数格式化
	private java.text.NumberFormat  AF_formater_value  =  java.text.DecimalFormat.getInstance();  //显示小数格式化


	//-------------------------------------------------------------------------------------------------------
	//@11-曲线最大点数
    private static final int MAX_DATA_POINTS = 400;
    //@12-FY曲线显示时间轴坐标
    private int xSeriesData = 0;
    //@13-FY曲线显示时间轴坐标Copy
    private int xSeriesData_Copy = 0;
    //@14-XH曲线显示时间轴坐标
    private int xSeriesData1 = 0;
    //@15-XH曲线显示时间轴坐标Copy
    private int xSeriesData1_Copy = 0;
    //@16-FY曲线显示标志
    public static  boolean FY_Curve_InputAngle_Dis =    false;
    public static  boolean FY_Curve_RealAngle_Dis =     false;
    public static  boolean FY_Curve_ErrorAngle_Dis =    true;
    public static  boolean FY_Curve_Data1_Dis =         false;
    public static  boolean FY_Curve_Data2_Dis =         false;
    public static  boolean FY_Curve_Data3_Dis =         false;
    public static  boolean FY_Curve_Data4_Dis =         false;
    public static  boolean FY_Curve_Data5_Dis =         false;

    //@-FY曲线显示移除标志
    public static  boolean FY_Curve_InputAngle_Remove =    true;
    public static  boolean FY_Curve_RealAngle_Remove =     true;
    public static  boolean FY_Curve_ErrorAngle_Remove =    false;
    public static  boolean FY_Curve_Data1_Remove =         true;
    public static  boolean FY_Curve_Data2_Remove =         true;
    public static  boolean FY_Curve_Data3_Remove =         true;
    public static  boolean FY_Curve_Data4_Remove =         true;
    public static  boolean FY_Curve_Data5_Remove =         true;

    //@17-XH曲线显示标志
    public static  boolean XH_Curve_InputAngle_Dis =    false;
    public static  boolean XH_Curve_RealAngle_Dis =     false;
    public static  boolean XH_Curve_ErrorAngle_Dis =    true;
    public static  boolean XH_Curve_Data1_Dis =         false;
    public static  boolean XH_Curve_Data2_Dis =         false;
    public static  boolean XH_Curve_Data3_Dis =         false;
    public static  boolean XH_Curve_Data4_Dis =         false;
    public static  boolean XH_Curve_Data5_Dis =         false;

    //@-XH曲线显示移除标志
    public static  boolean XH_Curve_InputAngle_Remove =    true;
    public static  boolean XH_Curve_RealAngle_Remove =     true;
    public static  boolean XH_Curve_ErrorAngle_Remove =    false;
    public static  boolean XH_Curve_Data1_Remove =         true;
    public static  boolean XH_Curve_Data2_Remove =         true;
    public static  boolean XH_Curve_Data3_Remove =         true;
    public static  boolean XH_Curve_Data4_Remove =         true;
    public static  boolean XH_Curve_Data5_Remove =         true;

    //@18-曲线基准数据
    private int  FY_Curve_UpData_DataBase = 3;   //@-1:输入角度   2:复视角度  3:误差角度  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    private int  XH_Curve_UpData_DataBase = 3;   //@-1:输入角度   2:复视角度  3:误差角度  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    //@19-FY曲线显示最大数
    public  int   FY_Curve_Display_Count = 1;
    public  int   FY_Curve_Display_Count_MAX = 8;
    //@20-XH曲线显示最大数
    public  int   XH_Curve_Display_Count = 1;
    public  int   XH_Curve_Display_Count_MAX = 8;
    //@21-FY曲线选择指示
    public  int    FY_Curve_Index = 0; //0:无  1:输入角度  2:复视角度  3:误差角度  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    public  byte[] FY_Curve_Sel ={0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x00,0x00};  //0:没有选择  1:选择   2:确认
    //@22-XH曲线选择指示
    public  int    XH_Curve_Index = 0; //0:无  1:输入角度  2:复视角度  3:误差角度  4:pos_out  5:uFwdSpd  6:act12 7:Uzk  8:Izk
    public  byte[] XH_Curve_Sel ={0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x00,0x00};  //0:没有选择  1:选择   2:确认

    //@23-曲线运行标识
    public static boolean Curve_run_status=false;   //true:曲线运行  false：曲线停止运行
    //@24-曲线刷新标识
    private boolean FY_Curve_updata=true;
    private boolean XH_Curve_updata=true;
    //@25-曲线显示时间线
    private Timeline Curve_timeline;
    //@26-曲线显示动画定时器
    private static SequentialTransition Curve_animation;

	//@27-FY伺服8条数据曲线
    private LineChart.Series<Number, Number>  FY_ChartSeries_InputAngle;  //@-FY输入角度
    private LineChart.Series<Number, Number>  FY_ChartSeries_RealAngle;   //@-FY复视角度
    private LineChart.Series<Number, Number>  FY_ChartSeries_ErrorAngle;  //@-FY误差角度
    private LineChart.Series<Number, Number>  FY_ChartSeries_1;           //@-FY母线电压
    private LineChart.Series<Number, Number>  FY_ChartSeries_2;           //@-FY电机转速
    private LineChart.Series<Number, Number>  FY_ChartSeries_3;			  //@-FY电机转矩
    private LineChart.Series<Number, Number>  FY_ChartSeries_4;			  //@-FY电机功率
    private LineChart.Series<Number, Number>  FY_ChartSeries_5;			  //@-FY弱磁电压
	//@28-XH伺服8条数据曲线
    private LineChart.Series<Number, Number>  XH_ChartSeries_InputAngle;  //@-XH输入角度
    private LineChart.Series<Number, Number>  XH_ChartSeries_RealAngle;   //@-XH复视角度
    private LineChart.Series<Number, Number>  XH_ChartSeries_ErrorAngle;  //@-XH误差角度
    private LineChart.Series<Number, Number>  XH_ChartSeries_1;           //@-XH母线电压
    private LineChart.Series<Number, Number>  XH_ChartSeries_2;           //@-XH电机转速
    private LineChart.Series<Number, Number>  XH_ChartSeries_3;			  //@-XH电机转矩
    private LineChart.Series<Number, Number>  XH_ChartSeries_4;			  //@-XH电机功率
    private LineChart.Series<Number, Number>  XH_ChartSeries_5;			  //@-XH弱磁电压

    //@29-FY伺服8条曲线数据队列
    public static List<Float> FY_Data_InputAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_RealAngle =  Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_ErrorAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_1 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_2 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_3 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_4 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> FY_Data_5 =          Collections.synchronizedList(new ArrayList<Float>());
    //@30-XH伺服8条曲线数据队列
    public static List<Float> XH_Data_InputAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_RealAngle =  Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_ErrorAngle = Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_1 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_2 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_3 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_4 =          Collections.synchronizedList(new ArrayList<Float>());
    public static List<Float> XH_Data_5 =          Collections.synchronizedList(new ArrayList<Float>());


	//-------------------------------------------------------------------------------------------------------

    //@-table数据显示源
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

	//@-保存标志
	public static boolean MK3_SaveData_Flag = true;


    /**
     * 	//@-系统时间
		MK3_System_Time.setText(Servo_DisplayTimer.Time_Str);
	 *  其中系统时间会卡顿，即会出现会跳跳现象，主要是由于Servo_DisplayTimer中Time_Str获取时间的变量在1s定时钟获取，导致时间跳秒的出现
	 *  20170331-使用log记录后，可实现显示曲线加后台存储数据
     */





	/**主界面初始化
	 *
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    	//@-数据精度格式 - 小数
    	formater_decimal.setMaximumFractionDigits(3);
    	formater_decimal.setMinimumFractionDigits(3);

    	Status_Square_Red = new Image(MainController.class.getResourceAsStream("statusbar_message_light_red.png"));
    	Status_Square_Yellow = new Image(MainController.class.getResourceAsStream("statusbar_message_light_orange.png"));
    	Status_Square_Green = new Image(MainController.class.getResourceAsStream("statusbar_message_light_green.png"));
    	Status_Square_White = new Image(MainController.class.getResourceAsStream("statusbar_message_light_white.png"));

    	Curve_Parameter_Init();

    	//@6-显示同步
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

							 //################################ 1-界面顶部信息显示 ##########################################
							 //@-系统时间
							 MK3_System_Time.setText(Servo_DisplayTimer.Time_Str);

							 //@-网络链接指示
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

							 MK3_FY_Save_Count.setText("记录数据条数:"+Net_Main_Receive.FYDsp_NetReceive_Count);
							 MK3_XH_Save_Count.setText("记录数据条数:"+Net_Main_Receive.XHDsp_NetReceive_Count);

							 //@-FY角度
							 MK3_FY_InputAngel.setText("输入:"+formater_decimal.format(Net_Main_Receive.FY_InPut_Angel));
							 MK3_FY_RealAngel.setText("复视:"+formater_decimal.format(Net_Main_Receive.FY_Real_Angel));
							 MK3_FY_ErrorAngel.setText("误差:"+formater_decimal.format(Net_Main_Receive.FY_Error_Angel));

							 //@-XH角度
							 MK3_XH_InputAngel.setText("输入:"+formater_decimal.format(Net_Main_Receive.XH_InPut_Angel));
							 MK3_XH_RealAngel.setText("复视:"+formater_decimal.format(Net_Main_Receive.XH_Real_Angel));
							 MK3_XH_ErrorAngel.setText("误差:"+formater_decimal.format(Net_Main_Receive.XH_Error_Angel));

						}
					});
				  }
				});
				t1.setName("MainDisplayUpdate");
				t1.setDaemon(true);
				t1.start();
			}
    	});

    	/**开曲线情况下，计时1min，FY&XH计数14037(常规文件写入操作)
    	 * 关曲线情况下，计时1min，FY&XH计数15083(常规文件写入操作)
    	 *
    	 * log4j,开曲线情况下，计时1min，FY计数30345
    	 * log4j,关曲线情况下，计时1min，FY计数30355
    	 *
    	 */
    	//@7-启动曲线功能
    	prepareTimeline();

    }


    /**曲线数据初始化
    *
    */
   private void Curve_Parameter_Init()
   {
	    //@-FY曲线基本配置
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


 		//@-FY曲线数据初始化
 		FY_ChartSeries_InputAngle = new LineChart.Series<Number, Number>();
 		FY_ChartSeries_RealAngle =  new LineChart.Series<Number, Number>();
 		FY_ChartSeries_ErrorAngle = new LineChart.Series<Number, Number>();
 		FY_ChartSeries_1 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_2 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_3 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_4 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_5 =          new LineChart.Series<Number, Number>();
 		FY_ChartSeries_InputAngle.setName("输入角");
 		FY_ChartSeries_RealAngle.setName ("复视角");
 		FY_ChartSeries_ErrorAngle.setName("误差角");
 		FY_ChartSeries_1.setName("pos_out");
 		FY_ChartSeries_2.setName("pos_up");
 		FY_ChartSeries_3.setName("uFwdSpd");
 		FY_ChartSeries_4.setName("act12");
 		FY_ChartSeries_5.setName("Uzk");
 		//@-将数据加入曲线-DX
 		LineChart_FY.getData().addAll(FY_ChartSeries_ErrorAngle);

 		//@-XH曲线基本配置
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
 		//@-XH曲线数据初始化
 		XH_ChartSeries_InputAngle = new LineChart.Series<Number, Number>();
 		XH_ChartSeries_RealAngle =  new LineChart.Series<Number, Number>();
 		XH_ChartSeries_ErrorAngle = new LineChart.Series<Number, Number>();
 		XH_ChartSeries_1 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_2 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_3 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_4 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_5 =          new LineChart.Series<Number, Number>();
 		XH_ChartSeries_InputAngle.setName("输入角");
 		XH_ChartSeries_RealAngle.setName ("复视角");
 		XH_ChartSeries_ErrorAngle.setName("误差角");
 		XH_ChartSeries_1.setName("pos_out");
 		XH_ChartSeries_2.setName("pos_up");
 		XH_ChartSeries_3.setName("uFwdSpd");
 		XH_ChartSeries_4.setName("act12");
 		XH_ChartSeries_5.setName("Uzk");
 		//@-将数据加入曲线-DX
 		LineChart_XH.getData().addAll(XH_ChartSeries_ErrorAngle);
   }


   /**曲线更新定时时间线
   *
   */
  private void prepareTimeline() {

      //@1-创建时间线
  	Curve_timeline = new Timeline();

      //@2-设置时间线为无限循环
  	Curve_timeline.setCycleCount(Animation.INDEFINITE);

      //@3-时间线增加定时事件
  	Curve_timeline.getKeyFrames().add(
              new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {
                  @Override public void handle(ActionEvent actionEvent) {

                  	//@4-建立平台输出线程
						Platform.runLater(new Runnable() {
							@Override
							public void run() {

								//@5-添加新数据至曲线
					        	addDataToSeries();
							}
						});
                  }
              })
      );

      //@6-将事件线连接至动画定时器启动
      Curve_animation = new SequentialTransition();
      Curve_animation.getChildren().addAll(Curve_timeline);

      //@7-设置曲线运行状态
      curve_run_set(true);

  }


	  /**曲线状态设置
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


	  /**向曲线添加新数据
	  *
	  */
	 private void addDataToSeries() {

	    	//@1-同步多线程
	    	synchronized(this)
	    	{
	    		//@2-基准数据源-输入角度
	    		if(FY_Curve_InputAngle_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@3-基准数据源-复视角度
	    		else if(FY_Curve_RealAngle_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@4-基准数据源-误差角度
	    		else if(FY_Curve_ErrorAngle_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@5-基准数据源-母线电压
	    		else if(FY_Curve_Data1_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@6-基准数据源-电机转速
	    		else if(FY_Curve_Data2_Dis==true)
	    		{
	        		//@-同步数据源
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
	       		//@7-基准数据源-电机转矩
	    		else if(FY_Curve_Data3_Dis==true)
	    		{
	        		//@-同步数据源
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
	       		//@8-基准数据源-电机功率
	    		else if(FY_Curve_Data4_Dis==true)
	    		{
	        		//@-同步数据源
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
	       		//@9-基准数据源-弱磁电压
	    		else if(FY_Curve_Data5_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@10-基准数据源-输入角度
	    		if(XH_Curve_InputAngle_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@11-基准数据源-复视角度
	    		else if(XH_Curve_RealAngle_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@12-基准数据源-误差角度
	    		else if(XH_Curve_ErrorAngle_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@13-基准数据源-母线电压
	    		else if(XH_Curve_Data1_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@14-基准数据源-电机转速
	    		else if(XH_Curve_Data2_Dis==true)
	    		{
	        		//@-同步数据源
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
	       		//@15-基准数据源-电机转矩
	    		else if(XH_Curve_Data3_Dis==true)
	    		{
	        		//@-同步数据源
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
	       		//@16-基准数据源-电机功率
	    		else if(XH_Curve_Data4_Dis==true)
	    		{
	        		//@-同步数据源
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
	       		//@17-基准数据源-弱磁电压
	    		else if(XH_Curve_Data5_Dis==true)
	    		{
	        		//@-同步数据源
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
	    		//@18-FY基准数据源是否更新
		    	if(xSeriesData_Copy!=xSeriesData)
		    	{
		    		xSeriesData_Copy=xSeriesData;
		    		FY_Curve_updata=true;
		    	}
		    	else
		    	{
		    		FY_Curve_updata=false;
		    	}
	    		//@19-XH基准数据源是否更新
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
		    	//@20-FY基准数据源更新
		    	if(FY_Curve_updata==true)
		    	{
		    		//@21-FY数据基准源非输入角度
	    		    if(FY_Curve_UpData_DataBase!=1)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_InputAngle_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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
		    		//@22-FY数据基准源非复视角度
	    		    if(FY_Curve_UpData_DataBase!=2)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_RealAngle_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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
		    		//@23-FY数据基准源非误差角度
	    		    if(FY_Curve_UpData_DataBase!=3)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_ErrorAngle_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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
		    		//@24-FY数据基准源非母线电压
	    		    if(FY_Curve_UpData_DataBase!=4)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_Data1_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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
		    		//@25-FY数据基准源非电机转速
	    		    if(FY_Curve_UpData_DataBase!=5)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_Data2_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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
		    		//@26-FY数据基准源非电机转矩
	    		    if(FY_Curve_UpData_DataBase!=6)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_Data3_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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
	    		    //@27-FY数据基准源非电机功率
	    		    if(FY_Curve_UpData_DataBase!=7)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_Data4_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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
	    		    //@28-FY数据基准源非弱磁电压
	    		    if(FY_Curve_UpData_DataBase!=8)
	    		    {
	    	    		//@-是否显示
	    	    		if(FY_Curve_Data5_Dis==true)
	    	    		{
	    	        		//@-同步数据源
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


		   		    //@29-曲线超出最大显示数-输入角度
			        if (FY_ChartSeries_InputAngle.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_InputAngle_Dis==true)
			    		{
			    			FY_ChartSeries_InputAngle.getData().remove(0);
			    		}
			        }
		   		    //@30-曲线超出最大显示数-复视角度
			        if (FY_ChartSeries_RealAngle.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_RealAngle_Dis==true)
			    		{
			    			FY_ChartSeries_RealAngle.getData().remove(0);
			    		}
			        }
		   		    //@31-曲线超出最大显示数-误差角度
			        if (FY_ChartSeries_ErrorAngle.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_ErrorAngle_Dis==true)
			    		{
			    			FY_ChartSeries_ErrorAngle.getData().remove(0);
			    		}
			        }
		   		    //@32-曲线超出最大显示数-母线电压
			        if (FY_ChartSeries_1.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data1_Dis==true)
			    		{
			    			FY_ChartSeries_1.getData().remove(0);
			    		}
			        }
		   		    //@33-曲线超出最大显示数-电机转速
			        if (FY_ChartSeries_2.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data2_Dis==true)
			    		{
			    			FY_ChartSeries_2.getData().remove(0);
			    		}
			        }
		   		    //@34-曲线超出最大显示数-电机转矩
			        if (FY_ChartSeries_3.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data3_Dis==true)
			    		{
			    			FY_ChartSeries_3.getData().remove(0);
			    		}
			        }
		   		    //@35-曲线超出最大显示数-电机功率
			        if (FY_ChartSeries_4.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data4_Dis==true)
			    		{
			    			FY_ChartSeries_4.getData().remove(0);
			    		}
			        }
		   		    //@36-曲线超出最大显示数-弱磁电压
			        if (FY_ChartSeries_5.getData().size() > MAX_DATA_POINTS)
			        {
			    		if(FY_Curve_Data5_Dis==true)
			    		{
			    			FY_ChartSeries_5.getData().remove(0);
			    		}
			        }

		        //@37-曲线更新边界显示
		        Chart_FY_AxisX.setLowerBound(xSeriesData-MAX_DATA_POINTS);
		        Chart_FY_AxisX.setUpperBound(xSeriesData-1);
	        }
	//--------------------------------------------------------------------------------------------------------------
	    	//@20-XH基准数据源更新
	    	if(XH_Curve_updata==true)
	    	{
	    		//@21-XH数据基准源非输入角度
			    if(XH_Curve_UpData_DataBase!=1)
			    {
		    		//@-是否显示
		    		if(XH_Curve_InputAngle_Dis==true)
		    		{
		        		//@-同步数据源
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
	    		//@22-XH数据基准源非复视角度
			    if(XH_Curve_UpData_DataBase!=2)
			    {
		    		//@-是否显示
		    		if(XH_Curve_RealAngle_Dis==true)
		    		{
		        		//@-同步数据源
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
	    		//@23-XH数据基准源非误差角度
			    if(XH_Curve_UpData_DataBase!=3)
			    {
		    		//@-是否显示
		    		if(XH_Curve_ErrorAngle_Dis==true)
		    		{
		        		//@-同步数据源
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
	    		//@24-XH数据基准源非母线电压
			    if(XH_Curve_UpData_DataBase!=4)
			    {
		    		//@-是否显示
		    		if(XH_Curve_Data1_Dis==true)
		    		{
		        		//@-同步数据源
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
	    		//@25-XH数据基准源非电机转速
			    if(XH_Curve_UpData_DataBase!=5)
			    {
		    		//@-是否显示
		    		if(XH_Curve_Data2_Dis==true)
		    		{
		        		//@-同步数据源
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
	    		//@26-XH数据基准源非电机转矩
			    if(XH_Curve_UpData_DataBase!=6)
			    {
		    		//@-是否显示
		    		if(XH_Curve_Data3_Dis==true)
		    		{
		        		//@-同步数据源
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
			    //@27-XH数据基准源非电机功率
			    if(XH_Curve_UpData_DataBase!=7)
			    {
		    		//@-是否显示
		    		if(XH_Curve_Data4_Dis==true)
		    		{
		        		//@-同步数据源
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
			    //@28-XH数据基准源非弱磁电压
			    if(XH_Curve_UpData_DataBase!=8)
			    {
		    		//@-是否显示
		    		if(XH_Curve_Data5_Dis==true)
		    		{
		        		//@-同步数据源
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


	   		    //@29-曲线超出最大显示数-输入角度
		        if (XH_ChartSeries_InputAngle.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_InputAngle_Dis==true)
		    		{
		    			XH_ChartSeries_InputAngle.getData().remove(0);
		    		}
		        }
	   		    //@30-曲线超出最大显示数-复视角度
		        if (XH_ChartSeries_RealAngle.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_RealAngle_Dis==true)
		    		{
		    			XH_ChartSeries_RealAngle.getData().remove(0);
		    		}
		        }
	   		    //@31-曲线超出最大显示数-误差角度
		        if (XH_ChartSeries_ErrorAngle.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_ErrorAngle_Dis==true)
		    		{
		    			XH_ChartSeries_ErrorAngle.getData().remove(0);
		    		}
		        }
	   		    //@32-曲线超出最大显示数-母线电压
		        if (XH_ChartSeries_1.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data1_Dis==true)
		    		{
		    			XH_ChartSeries_1.getData().remove(0);
		    		}
		        }
	   		    //@33-曲线超出最大显示数-电机转速
		        if (XH_ChartSeries_2.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data2_Dis==true)
		    		{
		    			XH_ChartSeries_2.getData().remove(0);
		    		}
		        }
	   		    //@34-曲线超出最大显示数-电机转矩
		        if (XH_ChartSeries_3.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data3_Dis==true)
		    		{
		    			XH_ChartSeries_3.getData().remove(0);
		    		}
		        }
	   		    //@35-曲线超出最大显示数-电机功率
		        if (XH_ChartSeries_4.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data4_Dis==true)
		    		{
		    			XH_ChartSeries_4.getData().remove(0);
		    		}
		        }
	   		    //@36-曲线超出最大显示数-弱磁电压
		        if (XH_ChartSeries_5.getData().size() > MAX_DATA_POINTS)
		        {
		    		if(XH_Curve_Data5_Dis==true)
		    		{
		    			XH_ChartSeries_5.getData().remove(0);
		    		}
		        }

		        //@37-曲线更新边界显示
		        Chart_XH_AxisX.setLowerBound(xSeriesData-MAX_DATA_POINTS);
		        Chart_XH_AxisX.setUpperBound(xSeriesData-1);
	    	}

	    }

	  }



    /**按键监听器
    *
    * @param event
    */
    @FXML
    public void Button_Pro(ActionEvent event)
    {
    	//@-清零按钮
    	if((event.getSource() == MK3_FY_Save_Clean))
    	{
    		Net_Main_Receive.FYDsp_NetReceive_Count = 0;
    		Net_Main_Receive.XHDsp_NetReceive_Count = 0;
    	}

    	//@-测试按钮
    	if((event.getSource() == MK3_FY_Test))
    	{
    		MK3_FY_Test_Count.setText("记录测试数据条数:"+Net_Main_Receive.FYDsp_NetReceive_Count);
    		MK3_XH_Test_Count.setText("记录测试数据条数:"+Net_Main_Receive.XHDsp_NetReceive_Count);
    	}

    	//@-清零按钮
    	if((event.getSource() == MK3_XH_Save_Clean))
    	{
    		Net_Main_Receive.FYDsp_NetReceive_Count = 0;
    		Net_Main_Receive.XHDsp_NetReceive_Count = 0;
    	}
    	//@-测试按钮
    	if((event.getSource() == MK3_XH_Test))
    	{
    		MK3_FY_Test_Count.setText("记录测试数据条数:"+Net_Main_Receive.FYDsp_NetReceive_Count);
    		MK3_XH_Test_Count.setText("记录测试数据条数:"+Net_Main_Receive.XHDsp_NetReceive_Count);
    	}

    	//@-网卡按钮
    	if((event.getSource() == NetCard_Name_Button))
    	{
    		Net_Main.NetCard_Set = true;
    		Net_Main.NetCard_Name = new String(NetCard_Name.getText());
    	}

    	//@-保存数据
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

    	//@-FY曲线选择-输入角
    	if((event.getSource()==MK3_FY_InputAngel_CheckBox))
    	{
    		Curve_Display_Pro(1,1);
    	}
    	//@-FY曲线选择-复视角
    	else if((event.getSource()==MK3_FY_RealAngel_CheckBox))
    	{
    		Curve_Display_Pro(1,2);
    	}
    	//@-FY曲线选择-误差角
    	else if((event.getSource()==MK3_FY_ErrorAngel_CheckBox))
    	{
    		Curve_Display_Pro(1,3);
    	}
    	//@-FY曲线选择-pos_out
    	else if((event.getSource()==MK3_FY_pos_out_CheckBox))
    	{
    		Curve_Display_Pro(1,4);
    	}
    	//@-FY曲线选择-pos_up
    	else if((event.getSource()==MK3_FY_pos_up_CheckBox))
    	{
    		Curve_Display_Pro(1,5);
    	}
    	//@-FY曲线选择-uFwdSpd
    	else if((event.getSource()==MK3_FY_uFwdSpd_CheckBox))
    	{
    		Curve_Display_Pro(1,6);
    	}
    	//@-FY曲线选择-act12
    	else if((event.getSource()==MK3_FY_act12_CheckBox))
    	{
    		Curve_Display_Pro(1,7);
    	}
    	//@-FY曲线选择-Uzk
    	else if((event.getSource()==MK3_FY_Uzk_CheckBox))
    	{
    		Curve_Display_Pro(1,8);
    	}

     	//@-XH曲线选择-输入角
    	if((event.getSource()==MK3_XH_InputAngel_CheckBox))
    	{
    		Curve_Display_Pro(2,1);
    	}
    	//@-XH曲线选择-复视角
    	else if((event.getSource()==MK3_XH_RealAngel_CheckBox))
    	{
    		Curve_Display_Pro(2,2);
    	}
    	//@-XH曲线选择-误差角
    	else if((event.getSource()==MK3_XH_ErrorAngel_CheckBox))
    	{
    		Curve_Display_Pro(2,3);
    	}
    	//@-XH曲线选择-pos_out
    	else if((event.getSource()==MK3_XH_pos_out_CheckBox))
    	{
    		Curve_Display_Pro(2,4);
    	}
    	//@-XH曲线选择-pos_up
    	else if((event.getSource()==MK3_XH_pos_up_CheckBox))
    	{
    		Curve_Display_Pro(2,5);
    	}
    	//@-XH曲线选择-uFwdSpd
    	else if((event.getSource()==MK3_XH_uFwdSpd_CheckBox))
    	{
    		Curve_Display_Pro(2,6);
    	}
    	//@-XH曲线选择-act12
    	else if((event.getSource()==MK3_XH_act12_CheckBox))
    	{
    		Curve_Display_Pro(2,7);
    	}
    	//@-XH曲线选择-Uzk
    	else if((event.getSource()==MK3_XH_Uzk_CheckBox))
    	{
    		Curve_Display_Pro(2,8);
    	}


    }



    /**各条曲线显示选择处理
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
		   	    //@1-输入角
		   		case 1:
		   			   if(MK3_FY_InputAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-显示小于最大显示数
		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
		   				   {
			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
			   				   FY_Curve_InputAngle_Dis = true;
			   				   //@-根据移除标识判断是否加入
			   				   if(FY_Curve_InputAngle_Remove==true)
			   				   {
			   					   FY_Curve_InputAngle_Remove=false;
			   					   LineChart_FY.getData().add(FY_ChartSeries_InputAngle);
			   				   }
		   				   }
		   				   //@-增加提示-超过显示最大值
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
		       				   //@-根据移除标识判断是否加入
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
		   					   //@移除该曲线
		   					   FY_Curve_InputAngle_Remove=true;
		   					   LineChart_FY.getData().remove(FY_ChartSeries_InputAngle);
		   				   }
		   			   }
		   			   break;

		   	    //@2-复视角
		   		case 2:
		   			   if(MK3_FY_RealAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-显示小于最大显示数
		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
		   				   {
			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
			   				   FY_Curve_RealAngle_Dis = true;
			   				   //@-根据移除标识判断是否加入
			   				   if(FY_Curve_RealAngle_Remove==true)
			   				   {
			   					   FY_Curve_RealAngle_Remove=false;
			   					   LineChart_FY.getData().add(FY_ChartSeries_RealAngle);
			   				   }
		   				   }
		   				   //@-增加提示-超过显示最大值
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
		       				   //@-根据移除标识判断是否加入
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
		   					   //@移除该曲线
		   					   FY_Curve_RealAngle_Remove=true;
		   					   LineChart_FY.getData().remove(FY_ChartSeries_RealAngle);
		   				   }
		   			   }
		   			   break;

	   	   	    //@3-误差角
   		   		case 3:
   		   			   if(MK3_FY_ErrorAngel_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_ErrorAngle_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(FY_Curve_ErrorAngle_Remove==true)
   			   				   {
   			   					   FY_Curve_ErrorAngle_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_ErrorAngle);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   FY_Curve_ErrorAngle_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_ErrorAngle);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@4-pos_out
   		   		case 4:
   		   			   if(MK3_FY_pos_out_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data1_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(FY_Curve_Data1_Remove==true)
   			   				   {
   			   					   FY_Curve_Data1_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_1);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   FY_Curve_Data1_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_1);
   		   				   }
   		   			   }
   		   			   break;

   	  		   	//@5-pos_up
   		   		case 5:
   		   			   if(MK3_FY_pos_up_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data2_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(FY_Curve_Data2_Remove==true)
   			   				   {
   			   					   FY_Curve_Data2_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_2);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   FY_Curve_Data2_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_2);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@6-uFwdSpd
   		   		case 6:
   		   			   if(MK3_FY_uFwdSpd_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data3_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(FY_Curve_Data3_Remove==true)
   			   				   {
   			   					   FY_Curve_Data3_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_3);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   FY_Curve_Data3_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_3);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@7-act12
   		   		case 7:
   		   			   if(MK3_FY_act12_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data4_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(FY_Curve_Data4_Remove==true)
   			   				   {
   			   					   FY_Curve_Data4_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_4);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   FY_Curve_Data4_Remove=true;
   		   					   LineChart_FY.getData().remove(FY_ChartSeries_4);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@8-Uzk
   		   		case 8:
   		   			   if(MK3_FY_Uzk_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(FY_Curve_Display_Count < FY_Curve_Display_Count_MAX)
   		   				   {
   			   				   FY_Curve_Display_Count = FY_Curve_Display_Count + 1;
   			   				   FY_Curve_Data5_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(FY_Curve_Data5_Remove==true)
   			   				   {
   			   					   FY_Curve_Data5_Remove=false;
   			   					   LineChart_FY.getData().add(FY_ChartSeries_5);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
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
		   	    //@1-输入角
		   		case 1:
		   			   if(MK3_XH_InputAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-显示小于最大显示数
		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
		   				   {
			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
			   				   XH_Curve_InputAngle_Dis = true;
			   				   //@-根据移除标识判断是否加入
			   				   if(XH_Curve_InputAngle_Remove==true)
			   				   {
			   					   XH_Curve_InputAngle_Remove=false;
			   					   LineChart_XH.getData().add(XH_ChartSeries_InputAngle);
			   				   }
		   				   }
		   				   //@-增加提示-超过显示最大值
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
		       				   //@-根据移除标识判断是否加入
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
		   					   //@移除该曲线
		   					   XH_Curve_InputAngle_Remove=true;
		   					   LineChart_XH.getData().remove(XH_ChartSeries_InputAngle);
		   				   }
		   			   }
		   			   break;

		   	    //@2-复视角
		   		case 2:
		   			   if(MK3_XH_RealAngel_CheckBox.isSelected()==true)
		   			   {
		   				   //@-显示小于最大显示数
		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
		   				   {
			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
			   				   XH_Curve_RealAngle_Dis = true;
			   				   //@-根据移除标识判断是否加入
			   				   if(XH_Curve_RealAngle_Remove==true)
			   				   {
			   					   XH_Curve_RealAngle_Remove=false;
			   					   LineChart_XH.getData().add(XH_ChartSeries_RealAngle);
			   				   }
		   				   }
		   				   //@-增加提示-超过显示最大值
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
		       				   //@-根据移除标识判断是否加入
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
		   					   //@移除该曲线
		   					   XH_Curve_RealAngle_Remove=true;
		   					   LineChart_XH.getData().remove(XH_ChartSeries_RealAngle);
		   				   }
		   			   }
		   			   break;

	   	   	    //@3-误差角
   		   		case 3:
   		   			   if(MK3_XH_ErrorAngel_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_ErrorAngle_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(XH_Curve_ErrorAngle_Remove==true)
   			   				   {
   			   					   XH_Curve_ErrorAngle_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_ErrorAngle);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   XH_Curve_ErrorAngle_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_ErrorAngle);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@4-pos_out
   		   		case 4:
   		   			   if(MK3_XH_pos_out_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data1_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(XH_Curve_Data1_Remove==true)
   			   				   {
   			   					   XH_Curve_Data1_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_1);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   XH_Curve_Data1_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_1);
   		   				   }
   		   			   }
   		   			   break;

   	  		   	//@5-pos_up
   		   		case 5:
   		   			   if(MK3_XH_pos_up_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data2_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(XH_Curve_Data2_Remove==true)
   			   				   {
   			   					   XH_Curve_Data2_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_2);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   XH_Curve_Data2_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_2);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@6-uFwdSpd
   		   		case 6:
   		   			   if(MK3_XH_uFwdSpd_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data3_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(XH_Curve_Data3_Remove==true)
   			   				   {
   			   					   XH_Curve_Data3_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_3);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   XH_Curve_Data3_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_3);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@7-act12
   		   		case 7:
   		   			   if(MK3_XH_act12_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data4_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(XH_Curve_Data4_Remove==true)
   			   				   {
   			   					   XH_Curve_Data4_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_4);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   XH_Curve_Data4_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_4);
   		   				   }
   		   			   }
   		   			   break;

   		   		//@8-Uzk
   		   		case 8:
   		   			   if(MK3_XH_Uzk_CheckBox.isSelected()==true)
   		   			   {
   		   				   //@-显示小于最大显示数
   		   				   if(XH_Curve_Display_Count < XH_Curve_Display_Count_MAX)
   		   				   {
   			   				   XH_Curve_Display_Count = XH_Curve_Display_Count + 1;
   			   				   XH_Curve_Data5_Dis = true;
   			   				   //@-根据移除标识判断是否加入
   			   				   if(XH_Curve_Data5_Remove==true)
   			   				   {
   			   					   XH_Curve_Data5_Remove=false;
   			   					   LineChart_XH.getData().add(XH_ChartSeries_5);
   			   				   }
   		   				   }
		   				   //@-增加提示-超过显示最大值
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
   		       				   //@-根据移除标识判断是否加入
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
   		   					   //@移除该曲线
   		   					   XH_Curve_Data5_Remove=true;
   		   					   LineChart_XH.getData().remove(XH_ChartSeries_5);
   		   				   }
   		   			   }
   		   			   break;

		   	     default: break;
		   	}
	    }
   }


	   /**检查曲线显示条数
	   *
	   * @return
	   */
	  private boolean FY_Curve_Display_Check()
	  {
	  	//必须存在一条曲线显示
	  	if(FY_Curve_Display_Count>1)
	  	{
	  		return true;
	  	}
	  	else
	  	{
	  		return false;
	  	}
	  }

	   /**检查曲线显示条数
	   *
	   * @return
	   */
	  private boolean XH_Curve_Display_Check()
	  {
	  	//必须存在一条曲线显示
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
