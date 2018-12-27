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
 
