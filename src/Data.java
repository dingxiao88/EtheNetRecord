import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Data {

    //@-����-��1
    public  int Data_Point1_v;
    //@-����-��1-���ݰ�
    public static IntegerProperty Data_Point1_b;
    //@-����-��1-���ݱ����ʾ
    public static StringProperty  Data_Point1_s;

    //@-����-��2
    public static int Data_Point2_v;
    //@-����-��2-���ݰ�
    public static IntegerProperty Data_Point2_b;
    //@-����-��2-���ݱ����ʾ
    public static StringProperty  Data_Point2_s;

    //@-����-��3
    public static int Data_Point3_v;
    //@-����-��3-���ݰ�
    public static IntegerProperty Data_Point3_b;
    //@-����-��3-���ݱ����ʾ
    public static StringProperty  Data_Point3_s;

    //@-����-��4
    public static int Data_Point4_v;
    //@-����-��4-���ݰ�
    public static IntegerProperty Data_Point4_b;
    //@-����-��4-���ݱ����ʾ
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
