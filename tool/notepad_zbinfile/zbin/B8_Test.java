import java.util.*;

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
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "one");
        map.put("2", "two");
        method5(map);
    }

    static void method1(int value) {
        java.util.Map<Thread, StackTraceElement[]> stacks1559055385575 = Thread.getAllStackTraces();
        java.util.Set<Thread> threadSet1559055385575 = stacks1559055385575.keySet();
        java.lang.management.RuntimeMXBean runtimeMXBean1559055385575 = java.lang.management.ManagementFactory.getRuntimeMXBean();
        int mProcessId1559055385575 = Integer.valueOf(runtimeMXBean1559055385575.getName().split("@")[0]).intValue();
        int threadNum1559055385575 = 1;
        for (Thread key : threadSet1559055385575) {
            StackTraceElement[] stackTraceElements = stacks1559055385575.get(key);
            System.out.println(" zukgit_B8_Test_method1 " + " \n  线程索引序号下标 【 " + (threadNum1559055385575) + " 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【" + key.getName() + "  】  进程ID-ProcessID:【 " + mProcessId1559055385575 + " 】  线程ID-ThreadId:【 " + key.getId() + " 】 ");
            for (StackTraceElement st : stackTraceElements) {
                System.out.println(st.toString());
            }
            System.out.println(" zukgit_B8_Test_method1 " + " \n  线程索引序号下标 【 " + (threadNum1559055385575++) + "  】∨∨∨∨∨∨∨∨∨∨∨∨");
        }
        RuntimeException re1559055385575 = new RuntimeException();
        re1559055385575.fillInStackTrace();
        System.out.println(" zukgit_B8_Test_method1   RuntimeException.fillInStackTrace() ");
        re1559055385575.printStackTrace();
        System.out.println(" zukgit_B8_Test_method1   " + "int value = " + value);
        System.out.println(" zukgit_B8_Test_method1   " + "this_is_indexLog_index=1");
        int randomInt = new Random().nextInt(10);
        zPrintLog(randomInt);
        System.out.println(" zukgit_B8_Test_method1   " + "this_is_indexLog_index=2");
        int intValue = 0;
        zPrintLog(intValue);
        if (randomInt < 5) {
            System.out.println(" zukgit_B8_Test_method1   " + "this_is_indexLog_index=3");
            zPrintLog(intValue);
            intValue = randomInt;
            zPrintLog(intValue);
        } else {
            System.out.println(" zukgit_B8_Test_method1   " + "this_is_indexLog_index=4");
            zPrintLog(intValue);
            intValue = -10;
            zPrintLog(intValue);
        }
        System.out.println(" zukgit_B8_Test_method1   " + "this_is_indexLog_index=5");
        System.out.println("method1   intValue=" + intValue);
    }

    static void method2(int[] value) {
        java.util.Map<Thread, StackTraceElement[]> stacks1559055385577 = Thread.getAllStackTraces();
        java.util.Set<Thread> threadSet1559055385577 = stacks1559055385577.keySet();
        java.lang.management.RuntimeMXBean runtimeMXBean1559055385577 = java.lang.management.ManagementFactory.getRuntimeMXBean();
        int mProcessId1559055385577 = Integer.valueOf(runtimeMXBean1559055385577.getName().split("@")[0]).intValue();
        int threadNum1559055385577 = 1;
        for (Thread key : threadSet1559055385577) {
            StackTraceElement[] stackTraceElements = stacks1559055385577.get(key);
            System.out.println(" zukgit_B8_Test_method2 " + " \n  线程索引序号下标 【 " + (threadNum1559055385577) + " 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【" + key.getName() + "  】  进程ID-ProcessID:【 " + mProcessId1559055385577 + " 】  线程ID-ThreadId:【 " + key.getId() + " 】 ");
            for (StackTraceElement st : stackTraceElements) {
                System.out.println(st.toString());
            }
            System.out.println(" zukgit_B8_Test_method2 " + " \n  线程索引序号下标 【 " + (threadNum1559055385577++) + "  】∨∨∨∨∨∨∨∨∨∨∨∨");
        }
        RuntimeException re1559055385577 = new RuntimeException();
        re1559055385577.fillInStackTrace();
        System.out.println(" zukgit_B8_Test_method2   RuntimeException.fillInStackTrace() ");
        re1559055385577.printStackTrace();
        for (int zindex = 0; zindex < value.length; zindex++) {
            System.out.println(" zukgit_B8_Test_method2   " + "int[] value----index = " + zindex + " : " + "  value = " + value[zindex]);
        }
        System.out.println(" zukgit_B8_Test_method2   " + "this_is_indexLog_index=1");
        System.out.println("method2");
        System.out.println(" zukgit_B8_Test_method2   " + "this_is_indexLog_index=2");
        int[] intArr = { 9, 8, 67, 65, 3, 23, 12 };
        zPrintLog(intArr);
    }

    static void method3(String[] strArr) {
        java.util.Map<Thread, StackTraceElement[]> stacks1559055385579 = Thread.getAllStackTraces();
        java.util.Set<Thread> threadSet1559055385579 = stacks1559055385579.keySet();
        java.lang.management.RuntimeMXBean runtimeMXBean1559055385579 = java.lang.management.ManagementFactory.getRuntimeMXBean();
        int mProcessId1559055385579 = Integer.valueOf(runtimeMXBean1559055385579.getName().split("@")[0]).intValue();
        int threadNum1559055385579 = 1;
        for (Thread key : threadSet1559055385579) {
            StackTraceElement[] stackTraceElements = stacks1559055385579.get(key);
            System.out.println(" zukgit_B8_Test_method3 " + " \n  线程索引序号下标 【 " + (threadNum1559055385579) + " 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【" + key.getName() + "  】  进程ID-ProcessID:【 " + mProcessId1559055385579 + " 】  线程ID-ThreadId:【 " + key.getId() + " 】 ");
            for (StackTraceElement st : stackTraceElements) {
                System.out.println(st.toString());
            }
            System.out.println(" zukgit_B8_Test_method3 " + " \n  线程索引序号下标 【 " + (threadNum1559055385579++) + "  】∨∨∨∨∨∨∨∨∨∨∨∨");
        }
        RuntimeException re1559055385579 = new RuntimeException();
        re1559055385579.fillInStackTrace();
        System.out.println(" zukgit_B8_Test_method3   RuntimeException.fillInStackTrace() ");
        re1559055385579.printStackTrace();
        if (strArr != null) {
            for (int zindex = 0; zindex < strArr.length; zindex++) {
                System.out.println(" zukgit_B8_Test_method3   " + "String[] strArr  zindex = " + zindex + "   :  value= " + strArr[zindex].toString());
            }
        } else {
            System.out.println(" zukgit_B8_Test_method3   " + "strArr is null !");
        }
        System.out.println(" zukgit_B8_Test_method3   " + "this_is_indexLog_index=1");
        System.out.println("method3");
    }

    static void method4(ArrayList<Date> dateList) {
        java.util.Map<Thread, StackTraceElement[]> stacks1559055385581 = Thread.getAllStackTraces();
        java.util.Set<Thread> threadSet1559055385581 = stacks1559055385581.keySet();
        java.lang.management.RuntimeMXBean runtimeMXBean1559055385581 = java.lang.management.ManagementFactory.getRuntimeMXBean();
        int mProcessId1559055385581 = Integer.valueOf(runtimeMXBean1559055385581.getName().split("@")[0]).intValue();
        int threadNum1559055385581 = 1;
        for (Thread key : threadSet1559055385581) {
            StackTraceElement[] stackTraceElements = stacks1559055385581.get(key);
            System.out.println(" zukgit_B8_Test_method4 " + " \n  线程索引序号下标 【 " + (threadNum1559055385581) + " 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【" + key.getName() + "  】  进程ID-ProcessID:【 " + mProcessId1559055385581 + " 】  线程ID-ThreadId:【 " + key.getId() + " 】 ");
            for (StackTraceElement st : stackTraceElements) {
                System.out.println(st.toString());
            }
            System.out.println(" zukgit_B8_Test_method4 " + " \n  线程索引序号下标 【 " + (threadNum1559055385581++) + "  】∨∨∨∨∨∨∨∨∨∨∨∨");
        }
        RuntimeException re1559055385581 = new RuntimeException();
        re1559055385581.fillInStackTrace();
        System.out.println(" zukgit_B8_Test_method4   RuntimeException.fillInStackTrace() ");
        re1559055385581.printStackTrace();
        if (dateList != null) {
            Object[] objectList = dateList.toArray();
            for (int zindex = 0; zindex < dateList.size(); zindex++) {
                System.out.println(" zukgit_B8_Test_method4   " + "ArrayList<Date> dateList  index =" + zindex + " value :" + objectList[zindex].toString());
            }
        } else {
            System.out.println(" zukgit_B8_Test_method4   " + "ArrayList<Date> dateList is null !");
        }
        System.out.println(" zukgit_B8_Test_method4   " + "this_is_indexLog_index=1");
        System.out.println("method4");
        System.out.println(" zukgit_B8_Test_method4   " + "this_is_indexLog_index=2");
        ArrayList list = new ArrayList();
        zPrintLog(list);
        System.out.println(" zukgit_B8_Test_method4   " + "this_is_indexLog_index=3");
        list.add("1");
        System.out.println(" zukgit_B8_Test_method4   " + "this_is_indexLog_index=4");
        list.add("2");
        System.out.println(" zukgit_B8_Test_method4   " + "this_is_indexLog_index=5");
        zPrintLog(list);
        list = new ArrayList();
        zPrintLog(list);
    }

    static void method5(Map<String, String> stringMap) {
        java.util.Map<Thread, StackTraceElement[]> stacks1559055385583 = Thread.getAllStackTraces();
        java.util.Set<Thread> threadSet1559055385583 = stacks1559055385583.keySet();
        java.lang.management.RuntimeMXBean runtimeMXBean1559055385583 = java.lang.management.ManagementFactory.getRuntimeMXBean();
        int mProcessId1559055385583 = Integer.valueOf(runtimeMXBean1559055385583.getName().split("@")[0]).intValue();
        int threadNum1559055385583 = 1;
        for (Thread key : threadSet1559055385583) {
            StackTraceElement[] stackTraceElements = stacks1559055385583.get(key);
            System.out.println(" zukgit_B8_Test_method5 " + " \n  线程索引序号下标 【 " + (threadNum1559055385583) + " 】∧∧∧∧∧∧∧∧∧∧∧∧ 线程名称threadName:【" + key.getName() + "  】  进程ID-ProcessID:【 " + mProcessId1559055385583 + " 】  线程ID-ThreadId:【 " + key.getId() + " 】 ");
            for (StackTraceElement st : stackTraceElements) {
                System.out.println(st.toString());
            }
            System.out.println(" zukgit_B8_Test_method5 " + " \n  线程索引序号下标 【 " + (threadNum1559055385583++) + "  】∨∨∨∨∨∨∨∨∨∨∨∨");
        }
        RuntimeException re1559055385583 = new RuntimeException();
        re1559055385583.fillInStackTrace();
        System.out.println(" zukgit_B8_Test_method5   RuntimeException.fillInStackTrace() ");
        re1559055385583.printStackTrace();
        if (stringMap != null) {
            Map.Entry<String, String> entry = null;
            java.util.Iterator iterator = stringMap.entrySet().iterator();
            int mZindex = 0;
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                System.out.println(" zukgit_B8_Test_method5   " + " stringMap    index =" + mZindex + "     entry.Key= " + entry.getKey() + " entry.Value=" + entry.getValue());
                mZindex++;
            }
        } else {
            System.out.println(" zukgit_B8_Test_method5   " + " stringMap is null !");
        }
        System.out.println(" zukgit_B8_Test_method5   " + "this_is_indexLog_index=1");
        System.out.println("method5");
        System.out.println(" zukgit_B8_Test_method5   " + "this_is_indexLog_index=2");
        Map<String, String> map = new HashMap<String, String>();
        zPrintLog(map);
        System.out.println(" zukgit_B8_Test_method5   " + "this_is_indexLog_index=3");
        map.put("a", "1");
        System.out.println(" zukgit_B8_Test_method5   " + "this_is_indexLog_index=4");
        map.put("b", "2");
        System.out.println(" zukgit_B8_Test_method5   " + "this_is_indexLog_index=5");
        zPrintLog(map);
        map = new HashMap<String, String>();
        zPrintLog(map);
    }

    public static final <T> boolean isListType(T t) {
        if (t == null) {
            return false;
        }
        String typeInfo = t.getClass().getName();
        System.out.println("isListType  typeInfo =  " + typeInfo);
        String[] zListTypeArr = { "java.util.Set", "java.util.ArrayList", "java.util.Collection", "java.util.List", "java.util.Vector", "java.util.Stack", "java.util.LinkedList", "javafx.collections.transformation.SortedList" };
        for (String curStr : zListTypeArr) {
            System.out.println("cur1:   typeInfo =  " + typeInfo + "   curStr=" + curStr);
            if (curStr.equals(typeInfo)) {
                System.out.println("cur2:   typeInfo =  " + typeInfo + "   curStr=" + curStr);
                return true;
            }
        }
        return false;
    }

    public static final <T> boolean isSetType(T t) {
        if (t == null) {
            return false;
        }
        String typeInfo = t.getClass().getName();
        System.out.println("isMapType  typeInfo =  " + typeInfo);
        String[] zSetTypeArr = { "java.util.EnumSet", "java.util.SortedSet", "java.util.concurrent.CopyOnWriteArraySet", "java.util.LinkedHashSet", "java.util.HashSet", "java.util.TreeSet", "android.util.SparseArray", "android.util.ArraySet" };
        for (String curStr : zSetTypeArr) {
            if (curStr.equals(typeInfo)) {
                return true;
            }
        }
        return false;
    }

    public static final <T> boolean iQueueType(T t) {
        if (t == null) {
            return false;
        }
        String typeInfo = t.getClass().getName();
        System.out.println("isMapType  typeInfo =  " + typeInfo);
        String[] zQueueTypeArr = { "java.util.concurrent.LinkedBlockingDeque", "java.util.Queue", "java.util.ArrayDeque", "java.util.concurrent.BlockingQueue", "java.util.concurrent.LinkedTransferQueue", "java.util.PriorityQueue", "java.util.concurrent.LinkedBlockingQueue" };
        for (String curStr : zQueueTypeArr) {
            if (curStr.equals(typeInfo)) {
                return true;
            }
        }
        return false;
    }

    public static final <T> boolean isShuZuType(T t) {
        if (t == null) {
            return false;
        }
        String typeInfo = t.getClass().getName();
        System.out.println("isMapType  typeInfo =  " + typeInfo);
        String[] zMapTypeArr = { "[Ljava.lang.String;", "[Ljava/lang/Object;", "[I", "[B", "[C", "[S", "[J", "[F", "[D" };
        if (typeInfo.startsWith("[L")) {
            return true;
        }
        for (String curStr : zMapTypeArr) {
            if (curStr.equals(typeInfo)) {
                return true;
            }
        }
        return false;
    }

    public static final <T> boolean isMapType(T t) {
        if (t == null) {
            return false;
        }
        String typeInfo = t.getClass().getName();
        System.out.println("isMapType  typeInfo =  " + typeInfo);
        String[] zMapTypeArr = { "java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.WeakHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.Hashtable", "android.util.ArrayMap" };
        for (String curStr : zMapTypeArr) {
            if (curStr.equals(typeInfo)) {
                return true;
            }
        }
        return false;
    }

    public static final <T> void zPrintLog(T t) {
        ;
        System.out.println(" 当前类型:   " + t.getClass().getName());
        if (isListType(t)) {
            System.out.println("List数据类型类型   ");
            if (t != null) {
                Object[] objectList = ((java.util.List) t).toArray();
                for (int zindex = 0; zindex < objectList.length; zindex++) {
                    System.out.println(" List   " + "ArrayList<Date> dateList  index =" + zindex + " value :" + objectList[zindex].toString());
                }
            } else {
                System.out.println(" List   " + "ArrayList<Date> dateList is null !");
            }
        } else if (isSetType(t)) {
            System.out.println("Set数据类型类型   ");
            if (t != null) {
                Object[] objectList = ((java.util.Set) t).toArray();
                for (int zindex = 0; zindex < objectList.length; zindex++) {
                    System.out.println(" Set   " + "ArrayList<Date> dateList  index =" + zindex + " value :" + objectList[zindex].toString());
                }
            } else {
                System.out.println(" Set   " + "ArrayList<Date> dateList is null !");
            }
        } else if (iQueueType(t)) {
            if (t != null) {
                Object[] objectList = ((java.util.Queue) t).toArray();
                for (int zindex = 0; zindex < objectList.length; zindex++) {
                    System.out.println(" Queue :   " + "ArrayList<Date> dateList  index =" + zindex + " value :" + objectList[zindex].toString());
                }
            } else {
                System.out.println(" Queue   " + "ArrayList<Date> dateList is null !");
            }
        } else if (isMapType(t)) {
            System.out.println("Map数据类型类型  ");
            if (t != null) {
                java.util.Map.Entry<String, String> entry = null;
                java.util.Iterator iterator = ((java.util.Map) t).entrySet().iterator();
                int mZindex = 0;
                while (iterator.hasNext()) {
                    entry = (java.util.Map.Entry<String, String>) iterator.next();
                    System.out.println(" Map   " + " stringMap    index =" + mZindex + "     entry.Key= " + entry.getKey() + " entry.Value=" + entry.getValue());
                    mZindex++;
                }
            } else {
                System.out.println(" Map   " + " t is null !");
            }
        } else if (isShuZuType(t)) {
            System.out.println("[] 数组类型格式  ");
            if (t != null) {
                String Arrtype = t.getClass().getName();
                java.util.ArrayList<Object> valueList = new java.util.ArrayList();
                int[] intArr = null;
                byte[] byteArr = null;
                char[] charArr = null;
                short[] shortArr = null;
                long[] longArr = null;
                float[] floatArr = null;
                double[] doubleArr = null;
                boolean[] booleanArr = null;
                Object[] ObjectArr = null;
                if (Arrtype.equals("[I")) {
                    intArr = (int[]) t;
                    valueList.add(java.util.Arrays.toString(intArr));
                } else if (Arrtype.equals("[J")) {
                    longArr = (long[]) t;
                    valueList.add(java.util.Arrays.toString(longArr));
                } else if (Arrtype.equals("[F")) {
                    floatArr = (float[]) t;
                    valueList.add(java.util.Arrays.toString(floatArr));
                } else if (Arrtype.equals("[D")) {
                    doubleArr = (double[]) t;
                    valueList.add(java.util.Arrays.toString(doubleArr));
                } else if (Arrtype.equals("[S")) {
                    shortArr = (short[]) t;
                    valueList.add(java.util.Arrays.toString(shortArr));
                } else if (Arrtype.equals("[C")) {
                    charArr = (char[]) t;
                    valueList.add(java.util.Arrays.toString(charArr));
                } else if (Arrtype.equals("[B")) {
                    byteArr = (byte[]) t;
                    valueList.add(java.util.Arrays.toString(byteArr));
                } else if (Arrtype.equals("[Z")) {
                    booleanArr = (boolean[]) t;
                    valueList.add(java.util.Arrays.toString(booleanArr));
                } else {
                    ObjectArr = (Object[]) t;
                    for (Object curObject : ObjectArr) {
                        valueList.add(curObject);
                    }
                }
                for (int zindex = 0; zindex < valueList.size(); zindex++) {
                    System.out.println(" shuzu[]   " + "XXXX[]   zindex = " + zindex + "   :  value= " + valueList.get(zindex).toString());
                }
            } else {
                System.out.println(" zukgit_B8_Test_method3   " + "strArr is null !");
            }
        } else {
            System.out.println("    baseObject = true   基本Object对象类型   toString()=" + t);
        }
    }
}
