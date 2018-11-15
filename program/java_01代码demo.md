


## Java内部类的创建与多线程访问变量

```

public class Test1 {
  public volatile static int count = 0;
 
     public static void inc(int threadNumber) {
 
         //这里延迟1毫秒，使得结果明显
         try {
             Thread.sleep(1);
         } catch (InterruptedException e) {
         }
         System.out.println("线程:" + threadNumber+"   count++ 之前:"+count );
         count++;
         System.out.println("线程:" + threadNumber+"   count++ 之后:"+count );
   


/**         synchronized (Test1.class) { // 这样的话 只有一个线程能访问变量 count 必须排队访问  count的值被依次增加
          count++;
          System.out.println("线程:" + threadNumber+"   count++ 之后:"+count );
   }**/




  }
 
     public static void main(String[] args) {
 
         //同时启动1000个线程，去进行i++计算，看看实际结果
 
         for (int i = 0; i < 10; i++) {
          final int x = i;
          Test1 test1 = new Test1();
          Task t = test1.new Task(x);
             new Thread();
             
//           People p = new People();Hobby h = p.new Hobby()
         }
 
         //这里每次运行的值都有可能不同,可能为1000
         System.out.println("最终运行结果:Counter.count=" + Test1.count);
     }
     


  class Task implements Runnable{
           int Threadnumber;
           public Task(int i) {
            Threadnumber = i;
   }
   @Override
   public void run() {
             Test1.inc(Threadnumber);
             System.out.println("线程"+Threadnumber+": 运行结果:Counter.count=" + Test1.count);
   
   }
};


}




```