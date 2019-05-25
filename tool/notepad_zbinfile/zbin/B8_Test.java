import java.util.ArrayList;
import java.util.Date;

public class B8_Test {

    public static void main(String[] args) {
        int int1 = 1;
        int[] intArr = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        String[] strArr = { "Z", "Y", "X", "V", "U" };
        ArrayList<Date> dateList = new ArrayList<Date>();
        long curTime = System.currentTimeMillis();
        dateList.add(new Date());
        dateList.add(new Date(curTime - 1000000000));
        dateList.add(new Date(curTime - 1500000000));
        dateList.add(new Date(curTime - 2000000000));
        method1(int1);
        method2(intArr);
        method3(strArr);
        method4(dateList);
    }

    static void method1(int value) {
        System.out.println("method1");
    }

    static void method2(int[] value) {
        System.out.println("method2");
    }

    static void method3(String[] strArr) {
        System.out.println("method3");
    }

    static void method4(ArrayList<Date> dateList) {
        System.out.println("method4");
    }
}
