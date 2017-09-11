/*
 * MK3随动控制系统-人机模块主程序入口
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 */


import java.io.File;
import java.util.Locale;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;



/**
 *
 * @author DX
 */

//@DX1-软件优化标识


public class ScreensFramework extends Application {

	//@1-主程序stage
    public static Stage MK3_Stage;
    //@2-主程序scene
    public static Scene MK3_Scene;

	//@3-主程序根root
    public static Group root = new Group();
	//@4-页面更改切换login->Main
	public static SimpleStringProperty PageChange = new SimpleStringProperty();
	//@5-主程序显示定时器
	private static Servo_DisplayTimer Time_Displsy;
	//@6-网络连接对象
	public static Net_Main Net_Main_Connnect;
	//@7-主页面运行标志
	public static boolean Main_Falg=true;

	//@8-页面位置指示
	public static int  App_Page=0;    //0:login  1:main  2:performance test  3:help

	//@9-界面主调度器
    private ScreensController mainContainer;

    //@10-界面常量
    public static String screen1ID = "main";
    public static String screen1File = "EthenetRecord.fxml";
    public static String screen2ID = "main";
    public static String screen2File = "Servo_Handle_Main_Copy_New.fxml";
    public static String screen3ID = "performance_test";
    public static String screen3File = "Performance_Test.fxml";
    public static String screen4ID = "checkdata";
    public static String screen4File = "CheckData.fxml";
    public static String screen5ID = "set";
    public static String screen5File = "Set.fxml";
    public static String screen6ID = "help";
    public static String screen6File = "Help.fxml";


    //@12-数据存储路径常量
	public static String MAIN_SaveData_Path = new String("D://MK3_Save");
    //@13-数据文件存储路径
	public static File MAIN_FileSave_Path;            //数据存储路径
	//@14-存储文件文件对象列表
	public static File[] MAIN_SaveFileList;
	//@15-记录文件存储数量
	public static int MAIN_SaveFile_Num;

	//@16-提示窗
    private static Notification.Notifier Main_Noti;
    private static Notification Noti_Targe;

	//@17-窗口尺寸
    private Rectangle2D bounds;

    //@-自定义键盘键值
    public static KeyCode Key_Code;
    //@-自定义键盘层
    public static int Main_Key_Level=0;
    //@-键盘触发计数
    public static int Key_Count=0;

    //@17-log日志-20160804
    public static Logger logger_FY = Logger.getLogger("Log_FY");
    public static Logger logger_XH = Logger.getLogger("Log_XH");


    @Override
    public void start(Stage primaryStage) {

    	//@1-初始化Stage
    	MK3_Stage = new Stage(StageStyle.DECORATED);
    	MK3_Stage.setTitle("MK3以太网数据记录器");

    	//@2-挂载页面控制器
        mainContainer = new ScreensController();

        mainContainer.loadScreen(screen1ID, screen1File);
//        mainContainer.loadScreen(screen2ID, screen2File);
//        mainContainer.loadScreen(screen3ID, screen3File);
//        mainContainer.loadScreen(screen4ID, screen4File);
//        mainContainer.loadScreen(screen5ID, screen5File);
//        mainContainer.loadScreen(screen6ID, screen6File);


        //@3-页面更改挂载更改监听器
    	PageChange.addListener(new ChangeListener<Object>(){
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object newval) {
				// TODO Auto-generated method stub

				//@-切换到主界面
				if(newval.toString().equals(new String("main")))
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							if(Main_Falg==false)
							Main_Falg=true;

//							if(MainController.Page1_Run_Flag==true)
//							MainController.curve_run_set(true);

							//@-切换到主界面
							mainContainer.setScreen(screen2ID);
							App_Page=1;

					        MK3_Stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - 1280 / 2);
					        MK3_Stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - 720 / 2);
							MK3_Stage.setWidth(800);
							MK3_Stage.setHeight(480);

						}
					});
				}
				//@-切换到性能检测
				else if(newval.toString().equals(new String("performance_test")))
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

//							if(CurveController.Page2_Run_Flag==true)
//							CurveController.curve_run_set(true);

							mainContainer.setScreen(screen3ID);
							App_Page=2;

//							MK3_Stage.setWidth(1280);
//							MK3_Stage.setHeight(720);
//					        MK3_Stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - 1280 / 2);
//					        MK3_Stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - 720 / 2);
						}
					});
				}
				//@-切换到帮助页面
				else if(newval.toString().equals(new String("help")))
				{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							//@-切换到帮助界面
							mainContainer.setScreen(screen6ID);
							App_Page=5;
						}
					});
				}
			}});

    	//@3-初始化加载主页面
        mainContainer.setScreen(screen1ID);
        App_Page=1;

		//@DX2-Log开始
		ScreensFramework.logger_FY.info("---------------Start----------------");
		ScreensFramework.logger_XH.info("---------------Start----------------");

        //@5-开机检测存储数据数量
        Check_File_Num();

        //@6-scene加载页面
        root = new Group();
        root.getChildren().addAll(mainContainer);
        MK3_Scene = new Scene(root);

        //@8-stage加载
	    MK3_Stage.setScene(MK3_Scene);
	    bounds = Screen.getPrimary().getBounds();
	    MK3_Stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - 1280 / 2);
	    MK3_Stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - 720 / 2);


	    //@9-stage显示
	    MK3_Stage.show();

		//@10-启动全局定时器-50ms
	    Time_Displsy = new Servo_DisplayTimer(10);
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**提示信息显示
     *
     * @param Show_Mode
     * @param Show_Info
     */
    public static void Show_Noti(String Show_Mode, String Show_Info)
    {
    	String Noti_Title=null;
    	Image  Noti_Image=null;

    	//@1-Noti标号
    	switch(Show_Mode)
    	{
    		case "Info":
	        		Noti_Title = new String("Info");
	        		Noti_Image = new Image(ScreensFramework.class.getResourceAsStream("info.png"));
	        		break;
    		case "Error":
	        		Noti_Title = new String("Error");
	        		Noti_Image = new Image(ScreensFramework.class.getResourceAsStream("error.png"));
	        		break;
    		case "Warning":
	        		Noti_Title = new String("Warning");
	        		Noti_Image = new Image(ScreensFramework.class.getResourceAsStream("warning.png"));
	        		break;
    		case "Success":
	        		Noti_Title = new String("Success");
	        		Noti_Image = new Image(ScreensFramework.class.getResourceAsStream("success.png"));
	        		break;

	        default:break;

    	}

    	//@3-建立Noti
    	Noti_Targe =  new Notification(Noti_Title, Show_Info, Noti_Image);

    	//@4-显示Noti
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Main_Noti = Notification.Notifier.INSTANCE;
				Main_Noti.notify(Noti_Targe);
			}
		});
    }



    /**检测存储数据文件数量
     *
     */
    public static void Check_File_Num()
    {
    	MAIN_FileSave_Path = new File(MAIN_SaveData_Path);
    	if(MAIN_FileSave_Path.isDirectory())
    	{
		   	 //获取文件列表
	    	 MAIN_SaveFileList = MAIN_FileSave_Path.listFiles();
		   	 //获取文件数量
	    	 MAIN_SaveFile_Num=MAIN_SaveFileList.length;

	    	 //System.out.println("ok"+MAIN_SaveFile_Num);
    	}
    }


	/**程序退出
	 *
	 */
	public static void cleanAndQuit() {
		Time_Displsy.task_dis.cancel();
		if(Net_Main_Connnect.isOpen==true)
		{
			Net_Main_Connnect.close();
		}
		MK3_Stage.close();
		Platform.exit();
		System.exit(0);
	}
}
