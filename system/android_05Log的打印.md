# Java层面打印Log


## Packet中
### 打印方法
```
VERBOSE   android.util.Log.v();

DEBUG     android.util.Log.d();

INFO      android.util.Log.i();

WARN      android.util.Log.w();

ERROR     android.util.Log.e();



```

### Logcat打印最大4K的限制
```

在logger.h头文件中有以下宏定义：
http://androidxref.com/9.0.0_r3/xref/system/core/include/log/log_read.h#138


/*
 * The maximum size of the log entry payload that can be
 * written to the logger. An attempt to write more than
 * this amount will result in a truncated log entry.    超过4K  Log 将被截断
 */
#define LOGGER_ENTRY_MAX_PAYLOAD 4068
#define LOGGER_ENTRY_MAX_LEN (5 * 1024)


// 判断打印Message的大小 对它进行分段处理
public class LogUtil {
    /**
     * 截断输出日志
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (tag == null || tag.length() == 0 
                || msg == null || msg.length() == 0)
            return;
 
        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
             Log.e(tag, msg);
        }else {
            while (msg.length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志    
        }
    }
}


```

### 实际使用

#### 打印堆栈
```
RuntimeException re = new RuntimeException();
re.fillInStackTrace();
android.util.Log.i("zukgit", "RuntimeException", re);


//输出结果
12-25 11:02:32.478  7446  7446 I zukgit1 : RuntimeException
12-25 11:02:32.478  7446  7446 I zukgit1 : java.lang.RuntimeException
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.wifi.WifiEnabler.onSwitchToggled(WifiEnabler.java:239)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.SwitchBarController.onSwitchChanged(SwitchBarController.java:77)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.SwitchBar.propagateChecked(SwitchBar.java:277)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.SwitchBar.onCheckedChanged(SwitchBar.java:287)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.CompoundButton.setChecked(CompoundButton.java:171)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.Switch.setChecked(Switch.java:1083)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.settings.widget.ToggleSwitch.setChecked(ToggleSwitch.java:57)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.Switch.toggle(Switch.java:1078)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.widget.CompoundButton.performClick(CompoundButton.java:132)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.view.View.performClickInternal(View.java:6577)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.view.View.access$3100(View.java:781)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.view.View$PerformClick.run(View.java:25912)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.os.Handler.handleCallback(Handler.java:873)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.os.Handler.dispatchMessage(Handler.java:99)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.os.Looper.loop(Looper.java:193)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at android.app.ActivityThread.main(ActivityThread.java:6923)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at java.lang.reflect.Method.invoke(Native Method)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
12-25 11:02:32.478  7446  7446 I zukgit1 :      at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:870)


```

####  打印进程pid 线程tid
```
java.util.Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();
java.util.Set<Thread> threadSet = stacks.keySet();
int mProcessId = android.os.Process.myPid();

int threadNum = 1;
for (Thread key : threadSet) {
StackTraceElement[] stackTraceElements = stacks.get(key);
android.util.Log.d("zukgit", "MMMMMMMM print threadName: " + key.getName() + "ProcessID:【"+mProcessId + "】  ThreadId:【"+key.getId()+ "】 indexId: 【"+ (threadNum++)+ "】 start MMMMMMMM");
for (StackTraceElement st : stackTraceElements) {
	android.util.Log.d("zukgit", "StackTraceElement: " + st.toString());
}
android.util.Log.d("zukgit", "VVVVVVVVV print threadName: " + key.getName() + " end VVVVVVVVV");
}


```
```
Process 地址:  http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/android/os/Process.java


```


##### 输出结果
```
// 输出结果：
zukgit  : MMMMMMMM print threadName: 【 main 】 ProcessID:【12192】 ThreadId:【2】 indexId: 【1】 start MMMMMMMM
zukgit  : StackTraceElement: com.android.settings.wifi.WifiEnabler.onSwitchToggled(WifiEnabler.java:244)
zukgit  : StackTraceElement: com.android.settings.widget.SwitchBarController.onSwitchChanged(SwitchBarController.java:77)
zukgit  : StackTraceElement: com.android.settings.widget.SwitchBar.propagateChecked(SwitchBar.java:277)
zukgit  : StackTraceElement: com.android.settings.widget.SwitchBar.onCheckedChanged(SwitchBar.java:287)
zukgit  : StackTraceElement: android.widget.CompoundButton.setChecked(CompoundButton.java:171)
zukgit  : StackTraceElement: android.widget.Switch.setChecked(Switch.java:1083)
zukgit  : StackTraceElement: com.android.settings.widget.ToggleSwitch.setChecked(ToggleSwitch.java:57)
zukgit  : StackTraceElement: android.widget.Switch.toggle(Switch.java:1078)
zukgit  : StackTraceElement: android.widget.CompoundButton.performClick(CompoundButton.java:132)
zukgit  : StackTraceElement: android.view.View.performClickInternal(View.java:6577)
zukgit  : StackTraceElement: android.view.View.access$3100(View.java:781)
zukgit  : StackTraceElement: android.view.View$PerformClick.run(View.java:25912)
zukgit  : StackTraceElement: android.os.Handler.handleCallback(Handler.java:873)
zukgit  : StackTraceElement: android.os.Handler.dispatchMessage(Handler.java:99)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:193)
zukgit  : StackTraceElement: android.app.ActivityThread.main(ActivityThread.java:6923)
zukgit  : StackTraceElement: java.lang.reflect.Method.invoke(Native Method)
zukgit  : StackTraceElement: com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
zukgit  : StackTraceElement: com.android.internal.os.ZygoteInit.main(ZygoteInit.java:870)
zukgit  : VVVVVVVVV print threadName: main end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 WifiTracker{96061c6} 】 ProcessID:【12192】 ThreadId:【600】 indexId: 【2】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName:  WifiTracker{96061c6} end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_4 】 ProcessID:【12192】 ThreadId:【550】 indexId: 【3】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_4 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_6 】 ProcessID:【12192】 ThreadId:【558】 indexId: 【4】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_6 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 SummaryLoader 】 ProcessID:【12192】 ThreadId:【589】 indexId: 【5】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName:  SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 ReferenceQueueDaemon 】 ProcessID:【12192】 ThreadId:【531】 indexId: 【6】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Daemons$ReferenceQueueDaemon.runInternal(Daemons.java:178)
zukgit  : StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  ReferenceQueueDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Signal Catcher 】 ProcessID:【12192】 ThreadId:【529】 indexId: 【7】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Signal Catcher end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 HeapTaskDaemon 】 ProcessID:【12192】 ThreadId:【534】 indexId: 【8】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  HeapTaskDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 FinalizerDaemon 】 ProcessID:【12192】 ThreadId:【532】 indexId: 【9】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Object.wait(Object.java:422)
zukgit  : StackTraceElement: java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:188)
zukgit  : StackTraceElement: java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:209)
zukgit  : StackTraceElement: java.lang.Daemons$FinalizerDaemon.runInternal(Daemons.java:232)
zukgit  : StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  FinalizerDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_8 】 ProcessID:【12192】 ThreadId:【593】 indexId: 【10】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_8 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Jit thread pool worker thread 0 】 ProcessID:【12192】 ThreadId:【528】 indexId: 【11】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Jit thread pool worker thread 0 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 SummaryLoader 】 ProcessID:【12192】 ThreadId:【573】 indexId: 【12】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName:  SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_1 】 ProcessID:【12192】 ThreadId:【535】 indexId: 【13】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_1 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 RenderThread 】 ProcessID:【12192】 ThreadId:【544】 indexId: 【14】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  RenderThread end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_2 】 ProcessID:【12192】 ThreadId:【536】 indexId: 【15】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_2 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 pool-1-thread-1 】 ProcessID:【12192】 ThreadId:【542】 indexId: 【16】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
zukgit  : StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
zukgit  : StackTraceElement: java.util.concurrent.locks.LockSupport.park(LockSupport.java:190)
zukgit  : StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2059)
zukgit  : StackTraceElement: java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1092)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  pool-1-thread-1 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_3 】 ProcessID:【12192】 ThreadId:【546】 indexId: 【17】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_3 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 AsyncTask #27 】 ProcessID:【12192】 ThreadId:【597】 indexId: 【18】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
zukgit  : StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
zukgit  : StackTraceElement: java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:230)
zukgit  : StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2101)
zukgit  : StackTraceElement: java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:467)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1091)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  AsyncTask #27 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 AsyncTask #30 】 ProcessID:【12192】 ThreadId:【602】 indexId: 【19】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
zukgit  : StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
zukgit  : StackTraceElement: java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:230)
zukgit  : StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2101)
zukgit  : StackTraceElement: java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:467)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1091)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  AsyncTask #30 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 FinalizerWatchdogDaemon 】 ProcessID:【12192】 ThreadId:【533】 indexId: 【20】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Thread.sleep(Native Method)
zukgit  : StackTraceElement: java.lang.Thread.sleep(Thread.java:373)
zukgit  : StackTraceElement: java.lang.Thread.sleep(Thread.java:314)
zukgit  : StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.sleepFor(Daemons.java:342)
zukgit  : StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.waitForFinalization(Daemons.java:364)
zukgit  : StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.runInternal(Daemons.java:281)
zukgit  : StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  FinalizerWatchdogDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 queued-work-looper 】 ProcessID:【12192】 ThreadId:【552】 indexId: 【21】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName:  queued-work-looper end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 SummaryLoader 】 ProcessID:【12192】 ThreadId:【576】 indexId: 【22】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName:  SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 AsyncTask #29 】 ProcessID:【12192】 ThreadId:【601】 indexId: 【23】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
zukgit  : StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
zukgit  : StackTraceElement: java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:230)
zukgit  : StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2101)
zukgit  : StackTraceElement: java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:467)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1091)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  AsyncTask #29 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 AsyncTask #28 】 ProcessID:【12192】 ThreadId:【598】 indexId: 【24】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
zukgit  : StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
zukgit  : StackTraceElement: java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:230)
zukgit  : StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2101)
zukgit  : StackTraceElement: java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:467)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1091)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
zukgit  : StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName:  AsyncTask #28 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_5 】 ProcessID:【12192】 ThreadId:【555】 indexId: 【25】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_5 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Binder:12192_7 】 ProcessID:【12192】 ThreadId:【561】 indexId: 【26】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Binder:12192_7 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 Profile Saver 】 ProcessID:【12192】 ThreadId:【537】 indexId: 【27】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName:  Profile Saver end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 SummaryLoader 】 ProcessID:【12192】 ThreadId:【551】 indexId: 【28】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName:  SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 ConnectivityThread 】 ProcessID:【12192】 ThreadId:【541】 indexId: 【29】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName:  ConnectivityThread end VVVVVVVVV


```

####  进程信息
```
ps  == Process Status
// 进程信息
adb shell
ps -A | grep  12192


ps -A | grep setting

USER           PID   PPID     VSZ     RSS    WCHAN                     ADDR  S   NAME
root             1     0    36868     3484   SyS_epoll+                0     S   init
root           876     1    3485264   64888  poll_schedule_timeout     0     S   zygote64
system       12192   876    3352400   98016  SyS_epoll_wait            0     S   com.android.settings


USER----(登录用户)           
PID-----(进程ID)   
PPID----(父进程ID)     
VSZ-----(使用掉的虚拟内存大小)    
RSS-----(进程使用的物理内存 包括共享内存)    
WCHAN---(进程正在睡眠的内核函数名称 该函数的名称是从/root/system.map文件中获得的)                     
ADDR----(内核进程，那么为预处理数据区的地)  
S-------(进程状态)   
NAME----(进程名称)



```
##### PS进程使用案例

###### ps --help  （英文）
```
ps --help
usage: ps [-AadefLlnwZ] [-gG GROUP,] [-k FIELD,] [-o FIELD,] [-p PID,] [-t TTY,] [-uU USER,]

List processes.

Which processes to show (selections may be comma separated lists):

-A      All processes
-a      Processes with terminals that aren't session leaders
-d      All processes that aren't session leaders
-e      Same as -A
-g      Belonging to GROUPs
-G      Belonging to real GROUPs (before sgid)
-p      PIDs (--pid)
-P      Parent PIDs (--ppid)
-s      In session IDs
-t      Attached to selected TTYs
-T      Show threads
-u      Owned by USERs
-U      Owned by real USERs (before suid)

Output modifiers:

-k      Sort FIELDs in +increasing or -decreasting order (--sort)
-M      Measure field widths (expanding as necessary)
-n      Show numeric USER and GROUP
-w      Wide output (don't truncate fields)

Which FIELDs to show. (Default = -o PID,TTY,TIME,CMD)

-f      Full listing (-o USER:12=UID,PID,PPID,C,STIME,TTY,TIME,ARGS=CMD)
-l      Long listing (-o F,S,UID,PID,PPID,C,PRI,NI,ADDR,SZ,WCHAN,TTY,TIME,CMD)
-o      Output FIELDs instead of defaults, each with optional :size and =title
-O      Add FIELDS to defaults
-Z      Include LABEL

Command line -o fields:

  ARGS     CMDLINE minus initial path     CMD  Command (thread) name (stat[2])
  CMDLINE  Command line (argv[])          COMM Command filename (/proc/$PID/exe)
  COMMAND  Command file (/proc/$PID/exe)  NAME Process name (argv[0] of $PID)

Process attribute -o FIELDs:

  ADDR  Instruction pointer               BIT   Is this process 32 or 64 bits
  CPU   Which processor running on        ETIME   Elapsed time since PID start
  F     Flags (1=FORKNOEXEC 4=SUPERPRIV)  GID     Group id
  GROUP Group name                        LABEL   Security label
  MAJFL Major page faults                 MINFL   Minor page faults
  NI    Niceness (lower is faster)
  PCPU  Percentage of CPU time used       PCY     Android scheduling policy
  PGID  Process Group ID
  PID   Process ID                        PPID    Parent Process ID
  PRI   Priority (higher is faster)       PSR     Processor last executed on
  RGID  Real (before sgid) group ID       RGROUP  Real (before sgid) group name
  RSS   Resident Set Size (pages in use)  RTPRIO  Realtime priority
  RUID  Real (before suid) user ID        RUSER   Real (before suid) user name
  S     Process state:
        R (running) S (sleeping) D (device I/O) T (stopped)  t (traced)
        Z (zombie)  X (deader)   x (dead)       K (wakekill) W (waking)
  SCHED Scheduling policy (0=other, 1=fifo, 2=rr, 3=batch, 4=iso, 5=idle)
  STAT  Process state (S) plus:
        < high priority          N low priority L locked memory
        s session leader         + foreground   l multithreaded
  STIME Start time of process in hh:mm (size :19 shows yyyy-mm-dd hh:mm:ss)
  SZ    Memory Size (4k pages needed to completely swap out process)
  TCNT  Thread count                      TID     Thread ID
  TIME  CPU time consumed                 TTY     Controlling terminal
  UID   User id                           USER    User name
  VSZ   Virtual memory size (1k units)    %VSZ    VSZ as % of physical memory
  WCHAN What are we waiting in kernel for


```

###### ps --help  （中文）
```

ps [-aAcdefHjlmNVwy][acefghLnrsSTuvxX][-C <指令名称>][-g <群组名称>]

[-G <群组识别码>][-p <进程识别码>][p <进程识别码>][-s <阶段作业>]

[-t <终端机编号>][t <终端机编号>][-u <用户识别码>][-U <用户识别码>]

[U <用户名称>][-<进程识别码>][--cols <每列字符数>]

[--columns <每列字符数>][--cumulative][--deselect][--forest]

[--headers][--help][-- info][--lines <显示列数>][--no-headers]

[--group <群组名称>][-Group <群组识别码>][--pid <进程识别码>]

[--rows <显示列数>][--sid <阶段作业>][--tty <终端机编号>]

[--user <用户名称>][--User <用户识别码>][--version]

[--width <每列字符数>]

参数说明：

　　-a  显示所有终端机下执行的进程，除了阶段作业领导者之外。
　　 a  显示现行终端机下的所有进程，包括其他用户的进程。
　　-A  显示所有进程。
　　-c  显示CLS和PRI栏位。
　　 c  列出进程时，显示每个进程真正的指令名称，而不包含路径，参数或常驻服务的标示。
　　-C<指令名称> 　指定执行指令的名称，并列出该指令的进程的状况。
　　-d 　显示所有进程，但不包括阶段作业领导者的进程。
　　-e 　此参数的效果和指定"A"参数相同。
　　 e 　列出进程时，显示每个进程所使用的环境变量。
　　-f 　显示UID,PPIP,C与STIME栏位。
　　 f 　用ASCII字符显示树状结构，表达进程间的相互关系。
　　-g<群组名称> 　此参数的效果和指定"-G"参数相同，当亦能使用阶段作业领导者的名称来指定。
　　 g 　显示现行终端机下的所有进程，包括群组领导者的进程。
　　-G<群组识别码> 　列出属于该群组的进程的状况，也可使用群组名称来指定。
　　 h 　不显示标题列。
　　-H 　显示树状结构，表示进程间的相互关系。
　　-j或j 　采用工作控制的格式显示进程状况。
　　-l或l 　采用详细的格式来显示进程状况。
　　 L 　列出栏位的相关信息。
　　-m或m 　显示所有的执行绪。
　　 n 　以数字来表示USER和WCHAN栏位。
　　-N 　显示所有的进程，除了执行ps指令终端机下的进程之外。
　　-p<进程识别码> 　指定进程识别码，并列出该进程的状况。
　 　p<进程识别码> 　此参数的效果和指定"-p"参数相同，只在列表格式方面稍有差异。
　　 r 　只列出现行终端机正在执行中的进程。
　　-s<阶段作业> 　指定阶段作业的进程识别码，并列出隶属该阶段作业的进程的状况。
　 　s 　采用进程信号的格式显示进程状况。
　　 S 　列出进程时，包括已中断的子进程资料。
　　-t<终端机编号> 　指定终端机编号，并列出属于该终端机的进程的状况。
　　 t<终端机编号> 　此参数的效果和指定"-t"参数相同，只在列表格式方面稍有差异。
　　-T 　显示现行终端机下的所有进程。
　　-u<用户识别码> 　此参数的效果和指定"-U"参数相同。
　　 u 　以用户为主的格式来显示进程状况。
　　-U<用户识别码> 　列出属于该用户的进程的状况，也可使用用户名称来指定。
　　 U<用户名称> 　列出属于该用户的进程的状况。
　　 v 　采用虚拟内存的格式显示进程状况。
　　-V或V 　显示版本信息。
　　-w或w 　采用宽阔的格式来显示进程状况。　
　 　x 　显示所有进程，不以终端机来区分。
　　 X 　采用旧式的Linux i386登陆格式显示进程状况。
　　 -y 配合参数"-l"使用时，不显示F(flag)栏位，并以RSS栏位取代ADDR栏位
　　-<进程识别码> 　此参数的效果和指定"p"参数相同。
　　--cols<每列字符数> 　设置每列的最大字符数。
　　--columns<每列字符数> 　此参数的效果和指定"--cols"参数相同。
　　--cumulative 　此参数的效果和指定"S"参数相同。
　　--deselect 　此参数的效果和指定"-N"参数相同。
　　--forest 　此参数的效果和指定"f"参数相同。
　　--headers 　重复显示标题列。
　　--help 　在线帮助。
　　--info 　显示排错信息。
　　--lines<显示列数> 设置显示画面的列数。
　　--no-headers  此参数的效果和指定"h"参数相同，只在列表格式方面稍有差异。
　　--group<群组名称> 　此参数的效果和指定"-G"参数相同。
　　--Group<群组识别码> 　此参数的效果和指定"-G"参数相同。
　　--pid<进程识别码> 　此参数的效果和指定"-p"参数相同。
　　--rows<显示列数> 　此参数的效果和指定"--lines"参数相同。
　　--sid<阶段作业> 　此参数的效果和指定"-s"参数相同。
　　--tty<终端机编号> 　此参数的效果和指定"-t"参数相同。
　　--user<用户名称> 　此参数的效果和指定"-U"参数相同。
　　--User<用户识别码> 　此参数的效果和指定"-U"参数相同。
　　--version 　此参数的效果和指定"-V"参数相同。
　　--widty<每列字符数> 　此参数的效果和指定"-cols"参数相同。 


```
###### ps -a
```

ps -a
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root          6857  6808   11800   3504 0                   0 R ps

```

###### ps -A
```
ps -A
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root             1     0   36868   3620 SyS_epoll+          0 S init
root             2     0       0      0 kthreadd            0 S [kthreadd]
root             3     2       0      0 smpboot_t+          0 S [ksoftirqd/0]
root             5     2       0      0 worker_th+          0 S [kworker/0:0H]
root             7     2       0      0 rcu_gp_kt+          0 S [rcu_preempt]
root             8     2       0      0 rcu_gp_kt+          0 S [rcu_sched]
root             9     2       0      0 rcu_gp_kt+          0 S [rcu_bh]
root            10     2       0      0 rcu_nocb_+          0 S [rcuop/0]
root            11     2       0      0 rcu_nocb_+          0 S [rcuos/0]
......
log           1087     1   25436   2628 __skb_wai+          0 S diag_mdlog
root          1107     1   19544   2572 poll_sche+          0 S qcom-system-daemon
system        1111     1 2152112   3884 SyS_epoll+          0 S cnd
system        1117     1   23276   2692 futex_wai+          0 S time_daemon

```

###### ps -d 
```
ps -d
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root             1     0   36868   3620 SyS_epoll+          0 S init
root             2     0       0      0 kthreadd            0 S [kthreadd]
root             3     2       0      0 smpboot_t+          0 S [ksoftirqd/0]
root             5     2       0      0 worker_th+          0 S [kworker/0:0H]
root             7     2       0      0 rcu_gp_kt+          0 S [rcu_preempt]
root             8     2       0      0 rcu_gp_kt+          0 S [rcu_sched]
root             9     2       0      0 rcu_gp_kt+          0 S [rcu_bh]
root            10     2       0      0 rcu_nocb_+          0 S [rcuop/0]
.....
root          7033     2       0      0 worker_th+          0 S [kworker/2:0]
root          7037     2       0      0 worker_th+          0 S [kworker/4:2]
root          7040     2       0      0 diag_sock+          0 S [kworker/u16:0]
root          7046     2       0      0 worker_th+          0 S [kworker/2:3]
root          7052  6808   11804   3772 0                   0 R ps
```


###### ps -g   [进程组名称]
```
【进程组名称】
【     ps -g wifi   】 
【     ps -g bluetooth   】 
【     ps -g nfc   】 
【     ps -g gps   】 
【     ps -g camera   】 
【     ps -g media   】 
【     ps -g audio   】 
【     ps -g system   】 
【     ps -g radio   】 
【     ps -g root   】 
【     ps -g drm   】 
【     ps -g log   】 
【     ps -g keystore   】 
【     ps -g vendor_pwric   】 

ps -g wifi
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
wifi           971     1   23040   6252 binder_th+          0 S android.hardware.wifi@1.0-service
wifi          1208     1   19180   4112 SyS_epoll+          0 S wificond
wifi          4690     1 2144292  13076 poll_sche+          0 S wpa_supplicant



ps -g bluetooth
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
bluetooth      952     1   19612   3108 binder_th+          0 S android.hardware.bluetooth@1.0-service-qti

ps -g nfc
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system         980     1   13348   2716 binder_th+          0 S vendor.qti.esepowermanager@1.0-service


ps -g gps
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
gps           1228     1   15444   2432 futex_wai+          0 S mlid
gps           1231     1   15700   2944 futex_wai+          0 S loc_launcher
gps           1273  1231 2133448   3868 futex_wai+          0 S lowi-server
gps           1274  1231   29100   3540 __skb_wai+          0 S xtra-daemon



ps -g camera
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
camera         974     1   20832   2504 binder_th+          0 S motorola.hardware.camera.imgtuner@1.0-service
mediacodec    1210     1   91548   4088 binder_th+          0 S media.codec


ps -g media
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
media          959     1   19576   2588 binder_th+          0 S android.hardware.drm@1.1-service.clearkey
media          960     1   22700   2716 binder_th+          0 S android.hardware.drm@1.1-service.widevine
media         1140     1   15388   2452 poll_sche+          0 S adsprpcd
media         1199     1 2155828   4644 binder_th+          0 S media.metrics



ps -g audio
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
cameraserver   953     1  104456    932 binder_th+          0 S android.hardware.camera.provider@2.4-service
audioserver    973     1   41288   2972 binder_th+          0 S motorola.hardware.audio@2.0-service
audioserver   1010     1   76768   4780 binder_th+          0 S audioserver
cameraserver  1164     1   34552   1992 binder_th+          0 S cameraserver
media         1202     1  205548   2920 binder_th+          0 S mediaserver





ps -g system
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system         785     1   11616   2732 binder_th+          0 S servicemanager
system         786     1 2127152   4092 binder_th+          0 S hwservicemanager
system         787     1   12328   2444 binder_th+          0 S vndservicemanager
system         807     1   19248   2332 do_wait             0 S qseecomd
system         810   807   32052   1728 sigsuspend          0 S qseecomd
system         824     1   14820   2580 binder_th+          0 S android.hardware.gatekeeper@1.0-service-qti
system         825     1   16656   2656 binder_th+          0 S android.hardware.keymaster@4.0-service-qti
system         948     1  104392   2812 binder_th+          0 S android.hardware.sensors@1.0-service
system         949     1   12820   3016 binder_th+          0 S android.hidl.allocator@1.0-service
system         951     1   20116   2984 binder_th+          0 S motorola.hardware.tcmd@1.0-service
system         956     1   18732   2880 binder_th+          0 S android.hardware.configstore@1.1-service
system         964     1   14184   3008 SyS_epoll+          0 S android.hardware.health@2.0-service
system         965     1   14216   2932 binder_th+          0 S android.hardware.light@2.0-service
system         966     1   14216   2928 binder_th+          0 S android.hardware.memtrack@1.0-service
system         967     1   15948   2992 binder_th+          0 S android.hardware.power@1.0-service
system         968     1   14212   2672 binder_th+          0 S android.hardware.thermal@1.0-service
system         970     1   14216   2912 binder_th+          0 S android.hardware.vibrator@1.0-service



ps -g radio
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system        1127     1   15740   2612 poll_sche+          0 S imsqmidaemon
system        1131     1   31456   2788 sigsuspend          0 S ims_rtp_daemon
system        1137     1   19444   2836 binder_th+          0 S imsrcsd
radio         1215     1    4872    664 __skb_wai+          0 S ssgqmigd
radio         1477     1  145240   6648 binder_th+          0 S qcrild
radio         1484     1   14204   2648 poll_sche+          0 S ipacm-diag
radio         1490     1   22160   2828 futex_wai+          0 S ipacm
radio         1499     1   23204   2668 poll_sche+          0 S qti
radio         1507     1  124668   3064 __skb_wai+          0 S netmgrd
radio         1516     1   15412   2444 futex_wai+          0 S port-bridge
radio         1729  1477   29760   3024 poll_sche+          0 S qmi_motext_hook
radio         2553   876 3238152  56256 SyS_epoll+          0 S com.qualcomm.qti.telephonyservice
radio         2587   876 3315716  66996 SyS_epoll+          0 S com.android.phone
radio         2805   876 3236688  59280 SyS_epoll+          0 S com.qualcomm.qcrilmsgtunnel
radio         4708   876 3234316  59836 SyS_epoll+          0 S com.qualcomm.qti.modemtestmode


ps -g drm
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
drm           1182     1   24552   1836 binder_th+          0 S drmserver


ps -g log
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system        1072     1   11324   2500 poll_sche+          0 S subsystem_ramdump
log           1078     1   15128   3436 SyS_epoll+          0 S aplogd
log           1087     1   25436   2628 __skb_wai+          0 S diag_mdlog



ps -g keystore
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
keystore      1188     1 2135868   4196 binder_th+          0 S keystore


ps -g vendor_pwric
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
vendor_pwric  2994     1   12556   2916 poll_sche+          0 S batt_health

```

###### ps -p   [进程id]
```
ps -p 12192        // 查看当前进程id 的信息
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system       12192   876 3371888 160860 SyS_epoll+          0 S com.android.settings


```

###### ps -P   [父进程id]
```
 ps -P  876        //  查看以 [ 876(父进程) ] 为父进程的进程信息

USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
system        1799   876 3992396 193192 SyS_epoll+          0 S system_server
u0_a150       2349   876 3427332 119036 SyS_epoll+          0 S com.google.android.inputmethod.latin
u0_a30        2361   876 3433612 174948 SyS_epoll+          0 S com.android.systemui
system        2526   876 3248248  64016 SyS_epoll+          0 S .dataservices
radio         2553   876 3238152  56256 SyS_epoll+          0 S com.qualcomm.qti.telephonyservice
log           2565   876 3236188  53156 SyS_epoll+          0 S com.motorola.android.nativedropboxagent
radio         2587   876 3315716  66996 SyS_epoll+          0 S com.android.phone
u0_a39        2722   876 3234436  63140 SyS_epoll+          0 S com.google.android.ext.services
radio         2805   876 3236688  59280 SyS_epoll+          0 S com.qualcomm.qcrilmsgtunnel
u0_a80        2906   876 3367656 118512 SyS_epoll+          0 S com.google.android.gms.persist
```

###### ps -AT     ▲
```
 ps -AT     //  显示所有进程  并且同时 显示

USER           PID   TID  PPID     VSZ    RSS WCHAN            ADDR S CMD
root             1     1     0   36868   3620 SyS_epoll+          0 S init
root             2     2     0       0      0 kthreadd            0 S kthreadd
root             3     3     2       0      0 smpboot_t+          0 S ksoftirqd/0
root             5     5     2       0      0 worker_th+          0 S kworker/0:0H
root             7     7     2       0      0 rcu_gp_kt+          0 S rcu_preempt
root             8     8     2       0      0 rcu_gp_kt+          0 S rcu_sched
..........
system       12192 12192   876 3371888 159964 SyS_epoll+          0 S ndroid.settings
system       12192 12197   876 3371888 159964 futex_wai+          0 S Jit thread pool
system       12192 12198   876 3371888 159964 do_sigtim+          0 S Signal Catcher
system       12192 12199   876 3371888 159964 poll_sche+          0 S ADB-JDWP Connec
system       12192 12200   876 3371888 159964 futex_wai+          0 S ReferenceQueueD
system       12192 12201   876 3371888 159964 futex_wai+          0 S FinalizerDaemon
system       12192 12202   876 3371888 159964 futex_wai+          0 S FinalizerWatchd
system       12192 12203   876 3371888 159964 futex_wai+          0 S HeapTaskDaemon
system       12192 12204   876 3371888 159964 binder_th+          0 S Binder:12192_1
system       12192 12205   876 3371888 159964 binder_th+          0 S Binder:12192_2
system       12192 12206   876 3371888 159964 futex_wai+          0 S Profile Saver
system       12192 12210   876 3371888 159964 SyS_epoll+          0 S ConnectivityThr
system       12192 12211   876 3371888 159964 futex_wai+          0 S pool-1-thread-1
system       12192 12216   876 3371888 159964 SyS_epoll+          0 S RenderThread
system       12192 12232   876 3371888 159964 binder_th+          0 S Binder:12192_3
system       12192 12292   876 3371888 159964 binder_th+          0 S Binder:12192_4
system       12192 12293   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 12298   876 3371888 159964 SyS_epoll+          0 S queued-work-loo
system       12192 12453   876 3371888 159964 binder_th+          0 S Binder:12192_5
system       12192 12742   876 3371888 159964 binder_th+          0 S Binder:12192_6
system       12192 12967   876 3371888 159964 binder_th+          0 S Binder:12192_7
system       12192 24077   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 24089   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 24295   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 24705   876 3371888 159964 binder_th+          0 S Binder:12192_8
system       12192 29260   876 3371888 159964 binder_th+          0 S Binder:12192_9
system       12192 29617   876 3371888 159964 SyS_epoll+          0 S magnifier pixel
system       12192 29750   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192 30131   876 3371888 159964 binder_th+          0 S Binder:12192_A
system       12192 31628   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6351   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6393   876 3371888 159964 binder_th+          0 S Binder:12192_B
system       12192  6397   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6451   876 3371888 159964 SyS_epoll+          0 S SummaryLoader
system       12192  6467   876 3371888 159964 SyS_epoll+          0 S SummaryLoader



```

###### ps -u   [wifi 用户名称]
```    
ps -u  wifi                    //  查看属于wifi用户的进程 
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
wifi           971     1   23040   6252 binder_th+          0 S android.hardware.wifi@1.0-service
wifi          1208     1   19180   4112 SyS_epoll+          0 S wificond
wifi          4690     1 2144292  13076 poll_sche+          0 S wpa_supplicant

ps -u u0_a80
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
u0_a80        2906   876 3367656 119664 SyS_epoll+          0 S com.google.android.gms.persistent
u0_a80        3175   876 3509964 109836 SyS_epoll+          0 S com.google.android.gms
u0_a80       15642   876 3252304  59736 SyS_epoll+          0 S com.google.process.gservices



ps -u u0_a36
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
u0_a36        6207   876 3244532  71152 SyS_epoll+          0 S com.google.android.apps.turbo


```

###### ps  -n
```
ps  -n    // 以数值的方式显示 USER列表  

ps  -n
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
0             6808  6702    9532   3236 sigsuspend          0 S sh
0             8734  6808   11800   3772 0                   0 R ps


ps  -a
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
root          8740  6808   11800   3616 0                   0 R ps




```



###### ps  -AM
```
 ps  -AM     // M 的作用在于使用较宽的宽度去显示


对比如下:  标题位置较宽
ps -A
USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
u0_a80       15642   876 3252304  60992 SyS_epoll+          0 S com.google.process.gservices

ps -AM
USER             PID  PPID     VSZ    RSS WCHAN                             ADDR S NAME  
u0_a80       15642   876 3252304  60980 SyS_epoll+                          0    S com.google.process.gservices
```

###### ps  -ATMf
```
ps  -ATMf               // f 参数用于 设置表头为  UID,PID,PPID,C,STIME,TTY,TIME,CMD
 

UID              PID   TID  PPID TCNT               STIME TTY          TIME CMD
root               1     1     0    1 2018-12-25 10:59:44 ?        00:00:07 init
root               2     2     0    1 2018-12-25 10:59:44 ?        00:00:01 [kthreadd]
root               3     3     2    1 2018-12-25 10:59:44 ?        00:00:30 [ksoftirqd/0]
root               5     5     2    1 2018-12-25 10:59:44 ?        00:00:00 [kworker/0:0H]
root               7     7     2    1 2018-12-25 10:59:44 ?        00:04:24 [rcu_preempt]
root               8     8     2    1 2018-12-25 10:59:44 ?        00:00:23 [rcu_sched]
root               9     9     2    1 2018-12-25 10:59:44 ?        00:00:00 [rcu_bh]
root              10    10     2    1 2018-12-25 10:59:44 ?        00:01:31 [rcuop/0]
root              11    11     2    1 2018-12-25 10:59:44 ?        00:00:02 [rcuos/0]
........
system         12192 12192   876   36 2018-12-25 11:52:00 ?        00:01:16 com.android.settings
system         12192 12197   876   36 2018-12-25 11:52:00 ?        00:00:02 com.android.settings
system         12192 12198   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12199   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12200   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12201   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12202   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12203   876   36 2018-12-25 11:52:00 ?        00:00:03 com.android.settings
system         12192 12204   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12205   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12206   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12210   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12211   876   36 2018-12-25 11:52:00 ?        00:00:00 com.android.settings
system         12192 12216   876   36 2018-12-25 11:52:01 ?        00:00:39 com.android.settings
system         12192 12232   876   36 2018-12-25 11:52:01 ?        00:00:00 com.android.settings
system         12192 12292   876   36 2018-12-25 11:52:19 ?        00:00:00 com.android.settings
system         12192 12293   876   36 2018-12-25 11:52:19 ?        00:00:00 com.android.settings
system         12192 12298   876   36 2018-12-25 11:52:20 ?        00:00:00 com.android.settings
system         12192 12453   876   36 2018-12-25 11:52:59 ?        00:00:00 com.android.settings
system         12192 12742   876   36 2018-12-25 11:54:40 ?        00:00:00 com.android.settings
system         12192 12967   876   36 2018-12-25 11:56:31 ?        00:00:00 com.android.settings
system         12192 24077   876   36 2018-12-26 08:29:32 ?        00:00:00 com.android.settings
system         12192 24089   876   36 2018-12-26 08:29:35 ?        00:00:00 com.android.settings
system         12192 24295   876   36 2018-12-26 08:31:53 ?        00:00:00 com.android.settings
system         12192 24705   876   36 2018-12-26 08:33:45 ?        00:00:00 com.android.settings
system         12192 29260   876   36 2018-12-26 10:29:21 ?        00:00:00 com.android.settings
system         12192 29617   876   36 2018-12-26 10:30:29 ?        00:00:00 com.android.settings
system         12192 29750   876   36 2018-12-26 10:30:36 ?        00:00:00 com.android.settings
system         12192 30131   876   36 2018-12-26 10:31:10 ?        00:00:00 com.android.settings
system         12192 31628   876   36 2018-12-26 11:56:12 ?        00:00:00 com.android.settings
system         12192  6351   876   36 2018-12-27 07:26:42 ?        00:00:00 com.android.settings
system         12192  6393   876   36 2018-12-27 07:26:48 ?        00:00:00 com.android.settings
system         12192  6397   876   36 2018-12-27 07:26:48 ?        00:00:00 com.android.settings
system         12192  6451   876   36 2018-12-27 07:27:20 ?        00:00:00 com.android.settings
system         12192 12908   876   36 2018-12-27 10:01:19 ?        00:00:00 com.android.settings
system         12192 13938   876   36 2018-12-27 10:12:09 ?        00:00:00 com.android.settings
```


###### ps  -ATMlf
```
ps  -ATMl       //  l 参数用于设置表头为 F,S,UID,PID,PPID,C,PRI,NI,ADDR,SZ,WCHAN,TTY,TIME,CMD
F S   UID   PID   TID  PPID  C PRI  NI BIT     SZ WCHAN                           TTY          TIME CMD
4 S     0     1     1     0  0  19   0  64   9217 SyS_epoll_wait                  ?        00:00:07 init
1 S     0     2     2     0  0  19   0   -      0 kthreadd                        ?        00:00:01 kthreadd
.........
5 S  1000 12192 12192   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:01:16 ndroid.settings
1 S  1000 12192 12197   876  0  10   9  64 843411 futex_wait_queue_me             ?        00:00:02 Jit thread pool
1 S  1000 12192 12198   876  0  19   0  64 843411 do_sigtimedwait                 ?        00:00:00 Signal Catcher
1 S  1000 12192 12199   876  0  19   0  64 843411 poll_schedule_timeout           ?        00:00:00 ADB-JDWP Connec
1 S  1000 12192 12200   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:00 ReferenceQueueD
1 S  1000 12192 12201   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:00 FinalizerDaemon
1 S  1000 12192 12202   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:00 FinalizerWatchd
1 S  1000 12192 12203   876  0  15   4  64 843411 futex_wait_queue_me             ?        00:00:03 HeapTaskDaemon
1 S  1000 12192 12204   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_1
1 S  1000 12192 12205   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_2
1 S  1000 12192 12206   876  0  10   9  64 843411 futex_wait_queue_me             ?        00:00:00 Profile Saver
1 S  1000 12192 12210   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:00:00 ConnectivityThr
1 S  1000 12192 12211   876  0  19   0  64 843411 futex_wait_queue_me             ?        00:00:00 pool-1-thread-1
1 S  1000 12192 12216   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:00:39 RenderThread
1 S  1000 12192 12232   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_3
1 S  1000 12192 12292   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_4
1 S  1000 12192 12293   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 12298   876  0  21  -2  64 843411 SyS_epoll_wait                  ?        00:00:00 queued-work-loo
1 S  1000 12192 12453   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_5
1 S  1000 12192 12742   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_6
1 S  1000 12192 12967   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_7
1 S  1000 12192 24077   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 24089   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 24295   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 24705   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_8
1 S  1000 12192 29260   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_9
1 S  1000 12192 29617   876  0  19   0  64 843411 SyS_epoll_wait                  ?        00:00:00 magnifier pixel
1 S  1000 12192 29750   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 30131   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_A
1 S  1000 12192 31628   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192  6351   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192  6393   876  0  19   0  64 843411 binder_thread_read              ?        00:00:00 Binder:12192_B
1 S  1000 12192  6397   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192  6451   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 12908   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader
1 S  1000 12192 13938   876  0   9  10  64 843411 SyS_epoll_wait                  ?        00:00:00 SummaryLoader

```

###### ps  -ATM -o  [表头项]
```
ps  -ATM -o ARGS                // -o 参数标识只显示的表头项
ps  -ATM -o CMD 
ps  -ATM -o CMDLINE 
ps  -ATM -o COMM 
ps  -ATM -o COMMAND 
ps  -ATM -o NAME 


ps  -ATM -o ARGS 
ARGS
init
[kthreadd]
[ksoftirqd/0]
[kworker/0:0H]
[rcu_preempt]
[rcu_sched]

ps  -ATM -o CMD 
CMD
init
kthreadd
ksoftirqd/0
kworker/0:0H
rcu_preempt
rcu_sched



```



###### PS命令表头项
```
USER----(登录用户)           
PID-----(进程ID)   
PPID----(父进程ID)     
VSZ-----(使用掉的虚拟内存大小)    
RSS-----(进程使用的物理内存 包括共享内存)    
WCHAN---(进程正在睡眠的内核函数名称 该函数的名称是从/root/system.map文件中获得的)                     
ADDR----(内核进程，那么为预处理数据区的地)  
S-------(进程状态)   
NAME----(进程名称)
TID----
TCNT---
TTY----
TIME----
CMD----
F----
S----
UID---
C----
PRI----
NI----
BIT---
```


### android.util.Log代码
```
地址: http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/android/util/Log.java#272

package android.util;
public final class Log {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

   public static int v(String tag, String msg) {
        return println_native(LOG_ID_MAIN, VERBOSE, tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        return printlns(LOG_ID_MAIN, VERBOSE, tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        return println_native(LOG_ID_MAIN, DEBUG, tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return printlns(LOG_ID_MAIN, DEBUG, tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        return println_native(LOG_ID_MAIN, INFO, tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return printlns(LOG_ID_MAIN, INFO, tag, msg, tr);
    }


    public static int w(String tag, String msg) {
        return println_native(LOG_ID_MAIN, WARN, tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return printlns(LOG_ID_MAIN, WARN, tag, msg, tr);
    }

    public static int w(String tag, Throwable tr) {
        return printlns(LOG_ID_MAIN, WARN, tag, "", tr);
    }


    public static int e(String tag, String msg) {
        return println_native(LOG_ID_MAIN, ERROR, tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return printlns(LOG_ID_MAIN, ERROR, tag, msg, tr);
    }


```

## Framework中

### 打印方法
```
VERBOSE     android.util.Slog.v();

DEBUG       android.util.Slog.d();

INFO        android.util.Slog.i();

WARN        android.util.Slog.w();

ERROR       android.util.Slog.e();



```

### android.util.Slog代码
```
地址： http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/android/util/Slog.java

package android.util;

/**
 * @hide
 */
public final class Slog {

    private Slog() {
    }

    public static int v(String tag, String msg) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.VERBOSE, tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.VERBOSE, tag,
                msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int d(String tag, String msg) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.DEBUG, tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.DEBUG, tag,
                msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int i(String tag, String msg) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.INFO, tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.INFO, tag,
                msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int w(String tag, String msg) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.WARN, tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.WARN, tag,
                msg + '\n' + Log.getStackTraceString(tr));
    }

    public static int w(String tag, Throwable tr) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.WARN, tag, Log.getStackTraceString(tr));
    }

    public static int e(String tag, String msg) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.ERROR, tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return Log.println_native(Log.LOG_ID_SYSTEM, Log.ERROR, tag,
                msg + '\n' + Log.getStackTraceString(tr));
    }

    /**
     * Like {@link Log#wtf(String, String)}, but will never cause the caller to crash, and
     * will always be handled asynchronously.  Primarily for use by coding running within
     * the system process.
     */
    public static int wtf(String tag, String msg) {
        return Log.wtf(Log.LOG_ID_SYSTEM, tag, msg, null, false, true);
    }

    /**
     * Like {@link #wtf(String, String)}, but does not output anything to the log.
     */
    public static void wtfQuiet(String tag, String msg) {
        Log.wtfQuiet(Log.LOG_ID_SYSTEM, tag, msg, true);
    }

    /**
     * Like {@link Log#wtfStack(String, String)}, but will never cause the caller to crash, and
     * will always be handled asynchronously.  Primarily for use by coding running within
     * the system process.
     */
    public static int wtfStack(String tag, String msg) {
        return Log.wtf(Log.LOG_ID_SYSTEM, tag, msg, null, true, true);
    }

    /**
     * Like {@link Log#wtf(String, Throwable)}, but will never cause the caller to crash,
     * and will always be handled asynchronously.  Primarily for use by coding running within
     * the system process.
     */
    public static int wtf(String tag, Throwable tr) {
        return Log.wtf(Log.LOG_ID_SYSTEM, tag, tr.getMessage(), tr, false, true);
    }

    /**
     * Like {@link Log#wtf(String, String, Throwable)}, but will never cause the caller to crash,
     * and will always be handled asynchronously.  Primarily for use by coding running within
     * the system process.
     */
    public static int wtf(String tag, String msg, Throwable tr) {
        return Log.wtf(Log.LOG_ID_SYSTEM, tag, msg, tr, false, true);
    }

    public static int println(int priority, String tag, String msg) {
        return Log.println_native(Log.LOG_ID_SYSTEM, priority, tag, msg);
    }
}



```
# C(Hal Jni) 层面打印Log

```
头文件：  #include <utils/Log.h>

方法：          
VERBOSE   LOGV()；
DEBUG     LOGD();
INFO      LOGI();
WARN      LOGW();
ERROR     LOGE();

例子：   LOGD("%s, %d",  s, n) ;
```
 
