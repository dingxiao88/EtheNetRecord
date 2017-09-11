import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Data {

    //@-数据-点1
    public  int Data_Point1_v;
    //@-数据-点1-数据绑定
    public static IntegerProperty Data_Point1_b;
    //@-数据-点1-数据表格显示
    public static StringProperty  Data_Point1_s;

    //@-数据-点2
    public static int Data_Point2_v;
    //@-数据-点2-数据绑定
    public static IntegerProperty Data_Point2_b;
    //@-数据-点2-数据表格显示
    public static StringProperty  Data_Point2_s;

    //@-数据-点3
    public static int Data_Point3_v;
    //@-数据-点3-数据绑定
    public static IntegerProperty Data_Point3_b;
    //@-数据-点3-数据表格显示
    public static StringProperty  Data_Point3_s;

    //@-数据-点4
    public static int Data_Point4_v;
    //@-数据-点4-数据绑定
    public static IntegerProperty Data_Point4_b;
    //@-数据-点4-数据表格显示
    public static StringProperty  Data_Point4_s;


	public  Data()
	{

	}

	public void Set_Data_Point1_v(int data)
	{
		Data_Point1_v = data;
	}
	public int Get_Data_Point1_v()
	{
		return Data_Point1_v;
	}

}
