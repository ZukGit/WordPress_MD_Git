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

http://androidxref.com/9.0.0_r3/xref/packages/apps/Settings/src/com/android/settings/wifi/WifiEnabler.java#197 
WifiEnabler.java
public boolean onSwitchToggled(boolean isChecked)



java.util.Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();
java.util.Set<Thread> threadSet = stacks.keySet();
int mProcessId = android.os.Process.myPid();

int threadNum = 1;
for (Thread key : threadSet) {
StackTraceElement[] stackTraceElements = stacks.get(key);
android.util.Log.d("zukgit", "MMMMMMMM print threadName:【 " + key.getName() + "】  ProcessID:【"+mProcessId + "】  ThreadId:【"+key.getId()+ "】 indexId: 【"+ (threadNum++)+ "】 start MMMMMMMM");
for (StackTraceElement st : stackTraceElements) {
	android.util.Log.d("zukgit", "StackTraceElement: " + st.toString());
}
android.util.Log.d("zukgit", "VVVVVVVVV print threadName: 【" + key.getName() + "】 end VVVVVVVVV");
}


```
```
Process 地址:  http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/java/android/os/Process.java


```


##### 输出结果
```
// 输出结果：
zukgit  : MMMMMMMM print threadName: Binder:6020_1ProcessID:【6020】 ThreadId:【359】 indexId: 【1】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Binder:6020_1 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: ReferenceQueueDaemonProcessID:【6020】 ThreadId:【355】 indexId: 【2】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Daemons$ReferenceQueueDaemon.runInternal(Daemons.java:178)
zukgit  : StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName: ReferenceQueueDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: FinalizerWatchdogDaemonProcessID:【6020】 ThreadId:【357】 indexId: 【3 】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.sleepUntilNeeded(Daemons.java:297)
zukgit  : StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.runInternal(Daemons.java:277)
zukgit  : StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName: FinalizerWatchdogDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: Binder:6020_3ProcessID:【6020】 ThreadId:【378】 indexId: 【4】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Binder:6020_3 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: AsyncTask #14ProcessID:【6020】 ThreadId:【424】 indexId: 【5】 start MMMMMMMM
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
zukgit  : VVVVVVVVV print threadName: AsyncTask #14 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: queued-work-looperProcessID:【6020】 ThreadId:【377】 indexId: 【6】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: queued-work-looper end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: RenderThreadProcessID:【6020】 ThreadId:【370】 indexId: 【7】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: RenderThread end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: SummaryLoaderProcessID:【6020】 ThreadId:【403】 indexId: 【8】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: Binder:6020_4ProcessID:【6020】 ThreadId:【382】 indexId: 【9】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Binder:6020_4 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: AsyncTask #13ProcessID:【6020】 ThreadId:【423】 indexId: 【10】 start MMMMMMMM
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
zukgit  : VVVVVVVVV print threadName: AsyncTask #13 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: SummaryLoaderProcessID:【6020】 ThreadId:【397】 indexId: 【11】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: FinalizerDaemonProcessID:【6020】 ThreadId:【356】 indexId: 【12】 start MMMMMMMM
zukgit  : StackTraceElement: java.lang.Object.wait(Native Method)
zukgit  : StackTraceElement: java.lang.Object.wait(Object.java:422)
zukgit  : StackTraceElement: java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:188)
zukgit  : StackTraceElement: java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:209)
zukgit  : StackTraceElement: java.lang.Daemons$FinalizerDaemon.runInternal(Daemons.java:232)
zukgit  : StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
zukgit  : StackTraceElement: java.lang.Thread.run(Thread.java:764)
zukgit  : VVVVVVVVV print threadName: FinalizerDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: Profile SaverProcessID:【6020】 ThreadId:【361】 indexId: 【13】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Profile Saver end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: SummaryLoaderProcessID:【6020】 ThreadId:【385】 indexId: 【14】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: 【 main 】 ProcessID:【6020】 ThreadId:【2】 indexId: 【15】 start MMMMMMMM
zukgit  : StackTraceElement: dalvik.system.VMStack.getThreadStackTrace(Native Method)
zukgit  : StackTraceElement: java.lang.Thread.getStackTrace(Thread.java:1538)
zukgit  : StackTraceElement: java.lang.Thread.getAllStackTraces(Thread.java:1588)
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
zukgit  : MMMMMMMM print threadName: Binder:6020_6ProcessID:【6020】 ThreadId:【420】 indexId: 【16】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Binder:6020_6 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: Binder:6020_2ProcessID:【6020】 ThreadId:【360】 indexId: 【17】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Binder:6020_2 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: WifiTracker{e32daa2}ProcessID:【6020】 ThreadId:【419】 indexId: 【18】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: WifiTracker{e32daa2} end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: SummaryLoaderProcessID:【6020】 ThreadId:【412】 indexId: 【19】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: magnifier pixel copy result handlerProcessID:【6020】 ThreadId:【402】 indexId: 【20】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: magnifier pixel copy result handler end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: HeapTaskDaemonProcessID:【6020】 ThreadId:【358】 indexId: 【21】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: HeapTaskDaemon end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: Signal CatcherProcessID:【6020】 ThreadId:【353】 indexId: 【22】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Signal Catcher end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: SummaryLoaderProcessID:【6020】 ThreadId:【417】 indexId: 【23】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: Binder:6020_5ProcessID:【6020】 ThreadId:【390】 indexId: 【24】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Binder:6020_5 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: SummaryLoaderProcessID:【6020】 ThreadId:【414】 indexId: 【25】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: SummaryLoader end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: pool-1-thread-1ProcessID:【6020】 ThreadId:【369】 indexId: 【26】 start MMMMMMMM
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
zukgit  : VVVVVVVVV print threadName: pool-1-thread-1 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: Jit thread pool worker thread 0ProcessID:【6020】 ThreadId:【352】 indexId: 【27】 start MMMMMMMM
zukgit  : VVVVVVVVV print threadName: Jit thread pool worker thread 0 end VVVVVVVVV
zukgit  : MMMMMMMM print threadName: ConnectivityThreadProcessID:【6020】 ThreadId:【363】 indexId: 【28】 start MMMMMMMM
zukgit  : StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
zukgit  : StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
zukgit  : StackTraceElement: android.os.Looper.loop(Looper.java:160)
zukgit  : StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
zukgit  : VVVVVVVVV print threadName: ConnectivityThread end VVVVVVVVV
```

##### ps -ATM -p 6020   [进程ID]
```
ps -efATM -p 6020
UID            PID   TID  PPID TCNT               STIME TTY          TIME CMD
system        6020  6020   864   27 2018-12-28 03:32:15 ?        00:00:30 com.android.settings
system        6020  6026   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6027   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6029   864   27 2018-12-28 03:32:15 ?        00:00:01 com.android.settings
system        6020  6030   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6031   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6032   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6033   864   27 2018-12-28 03:32:15 ?        00:00:01 com.android.settings
system        6020  6034   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6035   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6037   864   27 2018-12-28 03:32:15 ?        00:00:00 com.android.settings
system        6020  6057   864   27 2018-12-28 03:32:16 ?        00:00:00 com.android.settings
system        6020  6113   864   27 2018-12-28 03:32:16 ?        00:00:00 com.android.settings
system        6020  6123   864   27 2018-12-28 03:32:16 ?        00:00:14 com.android.settings
system        6020  7968   864   27 2018-12-28 03:32:54 ?        00:00:00 com.android.settings
system        6020  7969   864   27 2018-12-28 03:32:54 ?        00:00:00 com.android.settings
system        6020  8163   864   27 2018-12-28 03:33:33 ?        00:00:00 com.android.settings
system        6020  8984   864   27 2018-12-28 03:43:53 ?        00:00:00 com.android.settings
system        6020  9014   864   27 2018-12-28 03:43:55 ?        00:00:00 com.android.settings
system        6020  9234   864   27 2018-12-28 03:44:12 ?        00:00:00 com.android.settings
system        6020  9301   864   27 2018-12-28 03:44:17 ?        00:00:00 com.android.settings
system        6020  9307   864   27 2018-12-28 03:44:18 ?        00:00:00 com.android.settings
system        6020  9941   864   27 2018-12-28 03:44:35 ?        00:00:00 com.android.settings
system        6020 10008   864   27 2018-12-28 03:44:36 ?        00:00:00 com.android.settings
system        6020 10272   864   27 2018-12-28 03:44:39 ?        00:00:00 com.android.settings
system        6020 10414   864   27 2018-12-28 03:44:40 ?        00:00:00 com.android.settings
system        6020 10822   864   27 2018-12-28 03:44:44 ?        00:00:00 com.android.settings


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



### android.util.Slog 代码
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


###  Framework打印运行时Log

```
RuntimeException re = new RuntimeException();
re.fillInStackTrace();
android.util.Slog.i("zukgit", "RuntimeException", re);

```


###   Framework打印堆栈线程
```
java.util.Map<Thread, StackTraceElement[]> stacks = Thread.getAllStackTraces();
java.util.Set<Thread> threadSet = stacks.keySet();
int mProcessId = android.os.Process.myPid();

int threadNum = 1;
for (Thread key : threadSet) {
StackTraceElement[] stackTraceElements = stacks.get(key);
android.util.Slog.d("zukgit", "MMMMMMMM print threadName:【 " + key.getName() + "】  ProcessID:【"+mProcessId + "】  ThreadId:【"+key.getId()+ "】 indexId: 【"+ (threadNum++)+ "】 start MMMMMMMM");
for (StackTraceElement st : stackTraceElements) {
	android.util.Slog.d("zukgit", "StackTraceElement: " + st.toString());
}
android.util.Slog.d("zukgit", "VVVVVVVVV print threadName: 【" + key.getName() + "】 end VVVVVVVVV");
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
 
