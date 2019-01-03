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
WifiEnabler.onSwitchToggled

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
#### 输出结果
```
WifiServiceImpl.setWifiEnable()

ocean:/ # logcat | grep zukgit

1660  1660 D WifiService: zukgit setWifiEnabled: true pid=1660, uid=1000, package=android
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.display】  ProcessID:【1660】 ThreadId:【30】 indexId: 【1】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.display end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【BatteryStats_wakeupReason】  ProcessID:【1660】 ThreadId:【33】 indexId: 【2】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.BatteryStatsService.nativeWaitWakeup(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.BatteryStatsService.access$100(BatteryStatsService.java:82)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.BatteryStatsService$WakeupReasonThread.waitWakeup(BatteryStatsService.java:1193)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.BatteryStatsService$WakeupReasonThread.run(BatteryStatsService.java:1178)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: BatteryStats_wakeupReason end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【system-server-init-thread3】  ProcessID:【1660】 ThreadId:【74】 indexId: 【3】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.LockSupport.park(LockSupport.java:190)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2059)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1092)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.ConcurrentUtils$1$1.run(ConcurrentUtils.java:62)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: system-server-init-thread3 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【PowerManagerService】  ProcessID:【1660】 ThreadId:【32】 indexId: 【4】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: PowerManagerService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.anim.lf】  ProcessID:【1660】 ThreadId:【73】 indexId: 【5】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.anim.lf end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.bg】  ProcessID:【1660】 ThreadId:【18】 indexId: 【6】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.bg end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【GraphicsStats-disk】  ProcessID:【1660】 ThreadId:【117】 indexId: 【7】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: GraphicsStats-disk end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SensorService】  ProcessID:【1660】 ThreadId:【79】 indexId: 【8】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SensorService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【StorageManagerService】  ProcessID:【1660】 ThreadId:【93】 indexId: 【9】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: StorageManagerService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【HeapTaskDaemon】  ProcessID:【1660】 ThreadId:【13】 indexId: 【10】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: HeapTaskDaemon end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NetworkPolicy.uid】  ProcessID:【1660】 ThreadId:【98】 indexId: 【11】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NetworkPolicy.uid end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NetworkPolicy】  ProcessID:【1660】 ThreadId:【97】 indexId: 【12】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NetworkPolicy end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【Thread-3】  ProcessID:【1660】 ThreadId:【125】 indexId: 【13】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: libcore.io.Linux.accept(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: libcore.io.BlockGuardOs.accept(BlockGuardOs.java:59)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.system.Os.accept(Os.java:41)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.NativeCrashListener.run(NativeCrashListener.java:129)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: Thread-3 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【WifiStateMachine】  ProcessID:【1660】 ThreadId: 【100】 indexId: 【14】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HwRemoteBinder.transact(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.hardware.wifi.V1_0.IWifi$Proxy.start(IWifi.java:254)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.HalDeviceManager.startWifi(HalDeviceManager.java:1166)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.HalDeviceManager.start(HalDeviceManager.java:166)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiVendorHal.startVendorHal(WifiVendorHal.java:371)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiNative.startHal(WifiNative.java:277)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiNative.setupInterfaceForClientMode(WifiNative.java:844)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.ScanOnlyModeManager$ScanOnlyModeStateMachine$IdleState.processMessage(ScanOnlyModeManager.java:194)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.StateMachine$SmHandler.processMsg(StateMachine.java:992)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.StateMachine$SmHandler.handleMessage(StateMachine.java:809)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Handler.dispatchMessage(Handler.java:106)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:193)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: WifiStateMachine end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SliceManagerService】  ProcessID:【1660】 ThreadId:【118】 indexId: 【15】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SliceManagerService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SoundPoolThread】  ProcessID:【1660】 ThreadId:【129】 indexId: 【16】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SoundPoolThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.pacmanager】  ProcessID:【1660】 ThreadId:【106】 indexId: 【17】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.pacmanager end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ConnectivityThread】  ProcessID:【1660】 ThreadId:【116】 indexId: 【18】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ConnectivityThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【AlarmManager】  ProcessID:【1660】 ThreadId:【85 】 indexId: 【19】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.AlarmManagerService.waitForAlarm(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.AlarmManagerService.access$900(AlarmManagerService.java:119)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.AlarmManagerService$AlarmThread.run(AlarmManagerService.java:3476)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: AlarmManager end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【AsyncTask #1】  ProcessID:【1660】 ThreadId:【130】 indexId: 【20】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.sleep(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.sleep(Thread.java:373)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.sleep(Thread.java:314)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.ZygoteProcess.waitForConnectionToZygote(ZygoteProcess.java:684)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.webkit.WebViewZygote.connectToZygoteIfNeededLocked(WebViewZygote.java:186)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.webkit.WebViewZygote.getProcess(WebViewZygote.java:76)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.webkit.-$$Lambda$xYTrYQCPf1HcdlWzDof3mq93ihs.run(Unknown Source:0)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: AsyncTask #1 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【HwBinder:1660_2】  ProcessID:【1660】 ThreadId:【88】 indexId: 【21】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: HwBinder:1660_2 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【system-server-init-thread4】  ProcessID:【1660】 ThreadId:【75】 indexId: 【22】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.LockSupport.park(LockSupport.java:190)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2059)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1092)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.ConcurrentUtils$1$1.run(ConcurrentUtils.java:62)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: system-server-init-thread4 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【PhotonicModulator】  ProcessID:【1660】 ThreadId:【122】 indexId: 【23】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.display.DisplayPowerState$PhotonicModulator.run(DisplayPowerState.java:459)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: PhotonicModulator end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NetworkStatsObservers】  ProcessID:【1660】 ThreadId:【131】 indexId: 【24】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NetworkStatsObservers end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ConnectivityServiceThread】  ProcessID:【1660】 ThreadId:【105】 indexId: 【25】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ConnectivityServiceThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【CpuTracker】  ProcessID:【1660】 ThreadId:【26】 indexId: 【26】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Object.java:422)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.ActivityManagerService$5.run(ActivityManagerService.java:3380)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: CpuTracker end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【CameraService_proxy】  ProcessID:【1660】 ThreadId:【119】 indexId: 【27】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: CameraService_proxy end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【FinalizerWatchdogDaemon】  ProcessID:【1660】 ThreadId:【12】 indexId: 【28】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.sleep(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.sleep(Thread.java:373)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.sleep(Thread.java:314)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.sleepFor(Daemons.java:342)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.waitForFinalization(Daemons.java:364)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$FinalizerWatchdogDaemon.runInternal(Daemons.java:281)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: FinalizerWatchdogDaemon end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NDK MediaCodec_looper】  ProcessID:【1660】 ThreadId:【133】 indexId: 【29】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NDK MediaCodec_looper end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【notification-sqlite-log】  ProcessID:【1660】 ThreadId:【110】 indexId: 【30】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: notification-sqlite-log end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.anim】  ProcessID:【1660】 ThreadId:【72 】 indexId: 【31】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.anim end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SoundPool】  ProcessID:【1660】 ThreadId:【128】 indexId: 【32】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SoundPool end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【system-server-init-thread1】  ProcessID:【1660】 ThreadId:【16】 indexId: 【33】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.LockSupport.park(LockSupport.java:190)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2059)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1092)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.ConcurrentUtils$1$1.run(ConcurrentUtils.java:62)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: system-server-init-thread1 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SettingsProvider】  ProcessID:【1660】 ThreadId: 【82】 indexId: 【34】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SettingsProvider end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【UEventObserver】  ProcessID:【1660】 ThreadId:【76】 indexId: 【35】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.UEventObserver.nativeWaitForNextEvent(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.UEventObserver.access$100(UEventObserver.java:41)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.UEventObserver$UEventThread.run(UEventObserver.java:182)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: UEventObserver end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【WifiP2pService】  ProcessID:【1660】 ThreadId:【104】 indexId: 【36】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: WifiP2pService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.io】  ProcessID:【1660】 ThreadId:【29】 indexId: 【37】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.io end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【hidl_ssvc_poll】  ProcessID:【1660】 ThreadId:【92】 indexId: 【38】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: hidl_ssvc_poll end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NetworkWatchlistService】  ProcessID:【1660】 ThreadId:【89】 indexId: 【39】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NetworkWatchlistService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【InputReader】  ProcessID:【1660】 ThreadId:【90】 indexId: 【40】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: InputReader end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【LazyTaskWriterThread】  ProcessID:【1660】 ThreadId:【25】 indexId: 【41】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.TaskPersister$LazyTaskWriterThread.processNextItem(TaskPersister.java:694)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.TaskPersister$LazyTaskWriterThread.run(TaskPersister.java:667)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: LazyTaskWriterThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NetworkStats】  ProcessID:【1660】 ThreadId:【96 】 indexId: 【42】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NetworkStats end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【FileObserver】  ProcessID:【1660】 ThreadId:【24 】 indexId: 【43】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.FileObserver$ObserverThread.observe(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.FileObserver$ObserverThread.run(FileObserver.java:86)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: FileObserver end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【FinalizerDaemon】  ProcessID:【1660】 ThreadId:【11】 indexId: 【44】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Object.java:422)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:188)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:209)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$FinalizerDaemon.runInternal(Daemons.java:232)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: FinalizerDaemon end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【PackageManager】  ProcessID:【1660】 ThreadId:【35】 indexId: 【45】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: PackageManager end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SyncHandler-0】  ProcessID:【1660】 ThreadId:【120】 indexId: 【46】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SyncHandler-0 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【Binder:1660_5】  ProcessID:【1660】 ThreadId:【126】 indexId: 【47】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: Binder:1660_5 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【Binder:1660_2】  ProcessID:【1660】 ThreadId:【15】 indexId: 【48】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: Binder:1660_2 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【DataCollection Notifier】  ProcessID:【1660】 ThreadId:【17】 indexId: 【49】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: DataCollection Notifier end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SoftApMetricsWorkerThread】  ProcessID:【1660】 ThreadId:【102】 indexId: 【50】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SoftApMetricsWorkerThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【Binder:1660_4】  ProcessID:【1660】 ThreadId:【114】 indexId: 【51】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: Binder:1660_4 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ActivityManager:kill】  ProcessID:【1660】 ThreadId:【22】 indexId: 【52】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ActivityManager:kill end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【MotorolaSettingsProvider】  ProcessID:【1660】 ThreadId:【83】 indexId: 【53】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: MotorolaSettingsProvider end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【main】  ProcessID:【1660】 ThreadId:【2】 indexId: 【54】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.LockSupport.park(LockSupport.java:190)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.FutureTask.awaitDone(FutureTask.java:450)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.FutureTask.get(FutureTask.java:192)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.ConcurrentUtils.waitForFutureNoInterrupt(ConcurrentUtils.java:83)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.pm.PackageManagerService.waitForAppDataPrepared(PackageManagerService.java:21772)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.SystemServer.lambda$startOtherServices$4(SystemServer.java:1892)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.-$$Lambda$SystemServer$s9erd2iGXiS7bbg_mQJUxyVboQM.run(Unknown Source:53)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.ActivityManagerService.systemReady(ActivityManagerService.java:15718)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.SystemServer.startOtherServices(SystemServer.java:1800)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.SystemServer.run(SystemServer.java:441)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.SystemServer.main(SystemServer.java:304)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.reflect.Method.invoke(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.os.ZygoteInit.main(ZygoteInit.java:850)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: main end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【HealthServiceRefresh】  ProcessID:【1660】 ThreadId:【77】 indexId: 【55】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: HealthServiceRefresh end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【mDnsConnector】  ProcessID:【1660】 ThreadId:【108】 indexId: 【56】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.net.LocalSocketImpl.readba_native(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.net.LocalSocketImpl.access$300(LocalSocketImpl.java:36)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.net.LocalSocketImpl$SocketInputStream.read(LocalSocketImpl.java:110)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.NativeDaemonConnector.listenToSocket(NativeDaemonConnector.java:213)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.NativeDaemonConnector.run(NativeDaemonConnector.java:139)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: mDnsConnector end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ConditionProviders.ECP】  ProcessID:【1660】 ThreadId:【111】 indexId: 【57】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ConditionProviders.ECP end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【watchdog】  ProcessID:【1660】 ThreadId:【27】 indexId: 【58】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Object.java:422)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.Watchdog.run(Watchdog.java:469)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: watchdog end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【PackageInstaller】  ProcessID:【1660】 ThreadId: 【71】 indexId: 【59】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: PackageInstaller end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ranker】  ProcessID:【1660】 ThreadId:【109】 indexId: 【60】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ranker end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【Binder:1660_3】  ProcessID:【1660】 ThreadId:【78】 indexId: 【61】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: Binder:1660_3 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【WifiScanningService】  ProcessID:【1660】 ThreadId:【103】 indexId: 【62】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: WifiScanningService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ActivityManager】  ProcessID:【1660】 ThreadId:【19】 indexId: 【63】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ActivityManager end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【EventInsertThread】  ProcessID:【1660】 ThreadId:【84】 indexId: 【64】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.LockSupport.park(LockSupport.java:190)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2059)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ArrayBlockingQueue.take(ArrayBlockingQueue.java:387)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.motorola.android.server.checkin.CheckinProvider$EventInsertThread.waitForValues(CheckinProvider.java:946)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.motorola.android.server.checkin.CheckinProvider$EventInsertThread.run(CheckinProvider.java:902)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: EventInsertThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【EthernetServiceThread】  ProcessID:【1660】 ThreadId:【121】 indexId: 【65】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: EthernetServiceThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【DeviceStorageMonitorService】  ProcessID:【1660】 ThreadId:【112】 indexId: 【66】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: DeviceStorageMonitorService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【Signal Catcher】  ProcessID:【1660】 ThreadId:【8】 indexId: 【67】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: Signal Catcher end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NetdConnector】  ProcessID:【1660】 ThreadId:【94】 indexId: 【68】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.net.LocalSocketImpl.readba_native(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.net.LocalSocketImpl.access$300(LocalSocketImpl.java:36)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.net.LocalSocketImpl$SocketInputStream.read(LocalSocketImpl.java:110)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.NativeDaemonConnector.listenToSocket(NativeDaemonConnector.java:213)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.NativeDaemonConnector.run(NativeDaemonConnector.java:139)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NetdConnector end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【AudioService】  ProcessID:【1660】 ThreadId:【113】 indexId: 【69】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Object.java:422)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.audio.AudioService$AudioHandler.onLoadSoundEffects(AudioService.java:5642)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.audio.AudioService$AudioHandler.handleMessage(AudioService.java:5823)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Handler.dispatchMessage(Handler.java:106)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:193)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.audio.AudioService$AudioSystemThread.run(AudioService.java:5464)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: AudioService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ReferenceQueueDaemon】  ProcessID:【1660】 ThreadId:【10】 indexId: 【70】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$ReferenceQueueDaemon.runInternal(Daemons.java:178)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Daemons$Daemon.run(Daemons.java:103)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ReferenceQueueDaemon end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【UsbService host thread】  ProcessID:【1660】 ThreadId:【124】 indexId: 【71】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.usb.UsbHostManager.monitorUsbHostBus(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.usb.UsbHostManager.lambda$XT3F5aQci4H6VWSBYBQQNSzpnvs(Unknown Source:0)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.usb.-$$Lambda$UsbHostManager$XT3F5aQci4H6VWSBYBQQNSzpnvs.run(Unknown Source:2)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: UsbService host thread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【HwBinder:1660_1】  ProcessID:【1660】 ThreadId:【34】 indexId: 【72】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: HwBinder:1660_1 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【TaskSnapshotPersister】  ProcessID:【1660】 ThreadId:【86】 indexId: 【73】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wm.TaskSnapshotPersister$1.run(TaskSnapshotPersister.java:245)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: TaskSnapshotPersister end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【system-server-init-thread2】  ProcessID:【1660】 ThreadId:【70】 indexId: 【74】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.BinderProxy.transactNative(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.BinderProxy.transact(Binder.java:1136)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.IInstalld$Stub$Proxy.createAppData(IInstalld.java:698)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.pm.Installer.createAppData(Installer.java:170)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.pm.PackageManagerService.prepareAppDataLeafLIF(PackageManagerService.java:23129)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.pm.PackageManagerService.prepareAppDataLIF(PackageManagerService.java:23084)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.pm.PackageManagerService.prepareAppDataAndMigrateLIF(PackageManagerService.java:23093)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.pm.PackageManagerService.lambda$new$0(PackageManagerService.java:3332)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.pm.-$$Lambda$PackageManagerService$sJ5w9GfSftnZPyv5hBDxQkxDJMU.run(Unknown Source:6)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.SystemServerInitThreadPool.lambda$submit$0(SystemServerInitThreadPool.java:64)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.-$$Lambda$SystemServerInitThreadPool$7wfLGkZF7FvYZv7xj3ghvuiJJGk.run(Unknown Source:4)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:458)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.FutureTask.run(FutureTask.java:266)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.ConcurrentUtils$1$1.run(ConcurrentUtils.java:62)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: system-server-init-thread2 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SensorEventAckReceiver】  ProcessID:【1660】 ThreadId:【80】 indexId: 【75】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SensorEventAckReceiver end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【WifiMetricsWorkerThread】  ProcessID:【1660】 ThreadId:【101】 indexId: 【76】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: WifiMetricsWorkerThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【ActivityManager:procStart】  ProcessID:【1660】 ThreadId:【21】 indexId: 【77】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: ActivityManager:procStart end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【WifiService】  ProcessID:【1660】 ThreadId:【99】 indexId: 【78】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: dalvik.system.VMStack.getThreadStackTrace(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.getStackTrace(Thread.java:1538)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.getAllStackTraces(Thread.java:1588)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiController$DeviceActiveState.enter(WifiController.java:892)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.StateMachine$SmHandler.invokeEnterMethods(StateMachine.java:1037)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.StateMachine$SmHandler.performTransitions(StateMachine.java:879)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.StateMachine$SmHandler.handleMessage(StateMachine.java:819)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Handler.dispatchMessage(Handler.java:106)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:193)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: WifiService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【NsdService】  ProcessID:【1660】 ThreadId:【107】 indexId: 【79】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: NsdService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【pool-1-thread-1】  ProcessID:【1660】 ThreadId:【31】 indexId: 【80】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:230)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2101)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1132)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:849)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1092)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1152)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: pool-1-thread-1 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【AccountManagerService】  ProcessID:【1660】 ThreadId:【81】 indexId: 【81】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: AccountManagerService end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【Binder:1660_1】  ProcessID:【1660】 ThreadId:【14】 indexId: 【82】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: Binder:1660_1 end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【InputDispatcher】  ProcessID:【1660】 ThreadId:【91】 indexId: 【83】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: InputDispatcher end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【window_tracing】  ProcessID:【1660】 ThreadId:【87】 indexId: 【84】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.parkFor$(Thread.java:2137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: sun.misc.Unsafe.park(Unsafe.java:358)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.LockSupport.park(LockSupport.java:190)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2059)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ArrayBlockingQueue.take(ArrayBlockingQueue.java:387)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wm.WindowTracing.loopOnce(WindowTracing.java:137)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wm.WindowTracing.loop(WindowTracing.java:129)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wm.-$$Lambda$8kACnZAYfDhQTXwuOd2shUPmkTE.run(Unknown Source:2)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: window_tracing end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【PackageManager】  ProcessID:【1660】 ThreadId:【36】 indexId: 【85】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: PackageManager end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SoundPoolListenerThread】  ProcessID:【1660】 ThreadId:【127】 indexId: 【86】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.audio.AudioService$SoundPoolListenerThread.run(AudioService.java:3275)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SoundPoolListenerThread end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.fg】  ProcessID:【1660】 ThreadId:【28】 indexId: 【87】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.fg end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【SyncManager】  ProcessID:【1660】 ThreadId:【123 】 indexId: 【88】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: SyncManager end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【batterystats-worker】  ProcessID:【1660】 ThreadId:【23】 indexId: 【89】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Object.wait(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.AsyncChannel$SyncMessenger.sendMessageSynchronously(AsyncChannel.java:825)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.AsyncChannel$SyncMessenger.access$100(AsyncChannel.java:739)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.AsyncChannel.sendMessageSynchronously(AsyncChannel.java:653)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.util.WifiAsyncChannel.sendMessageSynchronously(WifiAsyncChannel.java:92)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.internal.util.AsyncChannel.sendMessageSynchronously(AsyncChannel.java:666)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiStateMachine.syncGetSupportedFeatures(WifiStateMachine.java:1871)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiServiceImpl.getSupportedFeatures(WifiServiceImpl.java:1754)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiServiceImpl.reportActivityInfo(WifiServiceImpl.java:1780)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.wifi.WifiServiceImpl.requestActivityInfo(WifiServiceImpl.java:1767)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.BatteryExternalStatsWorker.updateExternalStatsLocked(BatteryExternalStatsWorker.java:411)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.BatteryExternalStatsWorker.access$900(BatteryExternalStatsWorker.java:59)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.am.BatteryExternalStatsWorker$1.run(BatteryExternalStatsWorker.java:354)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:458)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.FutureTask.run(FutureTask.java:266)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:301)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: java.lang.Thread.run(Thread.java:764)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: batterystats-worker end VVVVVVVVV
1660  2375 D zukgit_wificontroller_DeviceActiveState: MMMMMMMM SLog print threadName: 【android.ui】  ProcessID:【1660】 ThreadId:【20】 indexId: 【90】 start MMMMMMMM
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.nativePollOnce(Native Method)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.MessageQueue.next(MessageQueue.java:326)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.Looper.loop(Looper.java:160)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: android.os.HandlerThread.run(HandlerThread.java:65)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.ServiceThread.run(ServiceThread.java:44)
1660  2375 D zukgit_wificontroller_DeviceActiveState: StackTraceElement: com.android.server.UiThread.run(UiThread.java:43)
1660  2375 D zukgit_wificontroller_DeviceActiveState: VVVVVVVVV print threadName: android.ui end VVVVVVVVV

ps | grep  system_server
system    1660  762   2930028 340936            0000000000 S system_server

ps -p 1726

ps -efATM -p 1660 -o UID,PID,TID,PPID,TCNT,STIME,ETIME,TIME,F,S,PRI,NI,CPU,PCPU,C,RSS,WCHAN,SZ,VSZ,LABEL,CMD > /sdcard/log.txt

  UID   PID   TID  PPID TCNT               STIME     ELAPSED     TIME F S PRI  NI CPU %CPU C    RSS WCHAN      SZ     VSZ LABEL                          CMD            
 1000  1660  1660   740  119 2018-11-09 05:11:32       14:14 00:01:12 5 S  21  -2   1  8.4 8 260020 0      990048 3960192 u:r:system_server:s0           system_server
 1000  1660  1668   740  119 2018-11-09 05:11:32       14:14 00:00:00 1 S  19   0   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Signal Catcher
 1000  1660  1671   740  119 2018-11-09 05:11:32       14:14 00:00:00 1 S  19   0   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ADB-JDWP Connec
 1000  1660  1673   740  119 2018-11-09 05:11:32       14:14 00:00:00 1 S  15   4   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ReferenceQueueD
 1000  1660  1674   740  119 2018-11-09 05:11:32       14:14 00:00:00 1 S  15   4   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           FinalizerDaemon
 1000  1660  1675   740  119 2018-11-09 05:11:32       14:14 00:00:00 1 S  15   4   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           FinalizerWatchd
 1000  1660  1677   740  119 2018-11-09 05:11:32       14:14 00:00:01 1 S  15   4   4  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           HeapTaskDaemon
 1000  1660  1779   740  119 2018-11-09 05:11:34       14:12 00:00:00 5 S  19   0   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_1
 1000  1660  1781   740  119 2018-11-09 05:11:34       14:12 00:00:02 5 S  19   0   0  0.2 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_2
 1000  1660  1891   740  119 2018-11-09 05:11:34       14:12 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           DataCollection 
 1000  1660  1893   740  119 2018-11-09 05:11:34       14:12 00:00:08 5 S  19   0   0  1.0 1 260020 0      990048 3960192 u:r:system_server:s0           android.bg
 1000  1660  1899   740  119 2018-11-09 05:11:35       14:11 00:00:00 5 S  21  -2   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ActivityManager-procStart
 1000  1660  1901   740  119 2018-11-09 05:11:35       14:11 00:00:00 5 S  21  -2   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           android.ui
 1000  1660  1902   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S  21  -2   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ActivityManager
 1000  1660  1904   740  119 2018-11-09 05:11:35       14:11 00:00:00 5 S   9  10   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ActivityManager:kill
 1000  1660  1912   740  119 2018-11-09 05:11:35       14:11 00:00:01 1 S  19   0   0  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           batterystats-wo
 1000  1660  1939   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           FileObserver
 1000  1660  1941   740  119 2018-11-09 05:11:35       14:11 00:00:00 5 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           android.fg
 1000  1660  1942   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S  19   0   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           android.io
 1000  1660  1943   740  119 2018-11-09 05:11:35       14:11 00:00:00 5 S  22  -3   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           android.display
 1000  1660  1946   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           pool-1-thread-1
 1000  1660  1952   740  119 2018-11-09 05:11:35       14:11 00:00:06 5 S  19   0   4  0.7 1 260020 0      990048 3960192 u:r:system_server:s0           CpuTracker
 1000  1660  1955   740  119 2018-11-09 05:11:35       14:11 00:00:01 5 S  23  -4   2  0.2 0 260020 0      990048 3960192 u:r:system_server:s0           PowerManagerService
 1000  1660  1958   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S  21  -2   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           BatteryStats_wakeupReason
 1000  1660  1960   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S  19   0   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           HwBinder:1660_1
 1000  1660  1984   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S   9  10   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           PackageManager
 1000  1660  2019   740  119 2018-11-09 05:11:35       14:11 00:00:00 1 S   9  10   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           PackageManager
 1000  1660  2216   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  19   0   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           PackageInstalle
 1000  1660  2225   740  119 2018-11-09 05:11:38       14:08 00:00:01 1 S  23  -4   0  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           android.anim
 1000  1660  2226   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  23  -4   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           android.anim.lf
 1000  1660  2229   740  119 2018-11-09 05:11:38       14:08 00:00:01 5 S  19   0   3  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_3
 1000  1660  2232   740  119 2018-11-09 05:11:38       14:08 00:00:00 5 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           UEventObserver
 1000  1660  2238   740  119 2018-11-09 05:11:38       14:08 00:00:00 5 S  27  -8   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SensorEventAckReceiver
 1000  1660  2239   740  119 2018-11-09 05:11:38       14:08 00:00:00 5 S  50  -8   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SensorService
 1000  1660  2242   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  19   0   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           HealthServiceRe
 1000  1660  2254   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  21  -2   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           AccountManagerS
 1000  1660  2269   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S   9  10   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SettingsProvide
 1000  1660  2289   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S   9  10   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           MotorolaSetting
 1000  1660  2290   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  19   0   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           EventInsertThre
 1000  1660  2293   740  119 2018-11-09 05:11:38       14:08 00:00:00 5 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           AlarmManager
 1000  1660  2298   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  19   0   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           window_tracing
 1000  1660  2320   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  27  -8   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           InputDispatcher
 1000  1660  2321   740  119 2018-11-09 05:11:38       14:08 00:00:00 5 S  27  -8   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           InputReader
 1000  1660  2322   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  19   0   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           HwBinder:1660_2
 1000  1660  2324   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S   9  10   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           NetworkWatchlistService
 1000  1660  2329   740  119 2018-11-09 05:11:38       14:08 00:00:00 5 S  50   0   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           hidl_ssvc_poll
 1000  1660  2342   740  119 2018-11-09 05:11:38       14:08 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           StorageManagerService
 1000  1660  2364   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           NetdConnector
 1000  1660  2368   740  119 2018-11-09 05:11:39       14:07 00:00:00 5 S  19   0   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           NetworkStats
 1000  1660  2369   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           NetworkPolicy
 1000  1660  2370   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  21  -2   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           tworkPolicy.uid
 1000  1660  2375   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           WifiService
 1000  1660  2376   740  119 2018-11-09 05:11:39       14:07 00:00:00 5 S  19   0   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           WifiStateMachine  [wifi状态机]
 1000  1660  2406   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           WifiMetricsWorkerThread
 1000  1660  2407   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SoftApMetricsWorkerThread
 1000  1660  2415   740  119 2018-11-09 05:11:39       14:07 00:00:00 5 S  19   0   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           WifiScanningService
 1000  1660  2416   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           WifiP2pService
 1000  1660  2418   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ConnectivityServiceThread
 1000  1660  2419   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           android.pacmanager
 1000  1660  2420   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           NsdService
 1000  1660  2421   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           mDnsConnector
 1000  1660  2423   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           notification-sqlite-log
 1000  1660  2424   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ranker
 1000  1660  2425   740  119 2018-11-09 05:11:39       14:07 00:00:00 5 S   9  10   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           onProviders.ECP
 1000  1660  2426   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           DeviceStorageMonitorService
 1000  1660  2428   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           AudioService
 1000  1660  2431   740  119 2018-11-09 05:11:39       14:07 00:00:01 5 S  19   0   0  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_4
 1000  1660  2438   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           ConnectivityThread
 1000  1660  2441   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           GraphicsStats-d
 1000  1660  2443   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SliceManagerService
 1000  1660  2444   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  23  -4   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           CameraService_proxy   
 1000  1660  2452   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SyncHandler-0
 1000  1660  2456   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           EthernetService
 1000  1660  2458   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S   9  10   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           TaskSnapshotPer
 1000  1660  2459   740  119 2018-11-09 05:11:39       14:07 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           PhotonicModulat
 1000  1660  2464   740  119 2018-11-09 05:11:40       14:07 00:00:00 1 S   9  10   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           LazyTaskWriterT
 1000  1660  2468   740  119 2018-11-09 05:11:40       14:06 00:00:00 5 S   9  10   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SyncManager
 1000  1660  2469   740  119 2018-11-09 05:11:40       14:06 00:00:01 5 S  19   0   5  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_5
 1000  1660  2471   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           UsbService host
 1000  1660  2473   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   7  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Thread-3
 1000  1660  2479   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SoundPool
 1000  1660  2480   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SoundPoolThread
 1000  1660  2492   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           NetworkStatsObservers
 1000  1660  2553   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           watchdog
 1000  1660  2566   740  119 2018-11-09 05:11:40       14:06 00:00:02 5 S  19   0   6  0.3 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_6
 1000  1660  2597   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           HwBinder:1660_3
 1000  1660  2602   740  119 2018-11-09 05:11:40       14:06 00:00:03 5 S  19   0   0  0.3 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_7
 1000  1660  2633   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S  19   0   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           EmergencyAfford
 1000  1660  2643   740  119 2018-11-09 05:11:40       14:06 00:00:00 1 S   9  10   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           LocationBackgro
 1000  1660  2653   740  119 2018-11-09 05:11:41       14:06 00:00:00 1 S  19   0   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           NetworkTimeUpda
 1000  1660  2683   740  119 2018-11-09 05:11:41       14:05 00:00:00 1 S  19   0   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           HwBinder:1660_4
 1000  1660  2728   740  119 2018-11-09 05:11:41       14:05 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           BluetoothRouteM
 1000  1660  2729   740  119 2018-11-09 05:11:41       14:05 00:00:00 1 S  19   0   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           AudioPortEventH
 1000  1660  2740   740  119 2018-11-09 05:11:41       14:05 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           uteStateMachine
 1000  1660  2750   740  119 2018-11-09 05:11:41       14:05 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           CallAudioModeSt
 1000  1660  2765   740  119 2018-11-09 05:11:41       14:05 00:00:00 1 S   9  10   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           MELogger
 1000  1660  2794   740  119 2018-11-09 05:11:41       14:05 00:00:00 1 S  21  -2   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           queued-work-loo
 1000  1660  3131   740  119 2018-11-09 05:11:45       14:01 00:00:00 1 S   9  10   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           backup
 1000  1660  3148   740  119 2018-11-09 05:11:45       14:01 00:00:02 5 S  19   0   1  0.2 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_8
 1000  1660  3153   740  119 2018-11-09 05:11:45       14:01 00:00:02 5 S  19   0   6  0.2 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_9
 1000  1660  3162   740  119 2018-11-09 05:11:45       14:01 00:00:01 5 S  19   0   0  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_A
 1000  1660  3166   740  119 2018-11-09 05:11:45       14:01 00:00:01 5 S  19   0   1  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_B
 1000  1660  3427   740  119 2018-11-09 05:11:46       14:00 00:00:01 5 S  19   0   3  0.1 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_C
 1000  1660  3439   740  119 2018-11-09 05:11:46       14:00 00:00:02 5 S  19   0   3  0.2 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_D
 1000  1660  3526   740  119 2018-11-09 05:11:46       14:00 00:00:00 5 S  19   0   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           IzatProvider
 1000  1660  3543   740  119 2018-11-09 05:11:46       14:00 00:00:00 1 S  19   0   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           LBSSystemMonito
 1000  1660  3620   740  119 2018-11-09 05:11:47       13:59 00:00:02 5 S  19   0   2  0.3 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_E
 1000  1660  3720   740  119 2018-11-09 05:11:47       13:59 00:00:00 1 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SyncHandler-1
 1000  1660  3787   740  119 2018-11-09 05:11:48       13:58 00:00:00 1 S  19   0   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           SyncHandler-2
 1000  1660  4087   740  119 2018-11-09 05:11:51       13:55 00:00:00 5 S  19   0   6  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           AsyncQueryWorke
 1000  1660  4429   740  119 2018-11-09 05:11:56       13:50 00:00:00 1 S  19   0   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           PhotonicModulat
 1000  1660  4811   740  119 2018-11-09 05:12:01       13:45 00:00:00 5 S  19   0   1  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_F
 1000  1660  4869   740  119 2018-11-09 05:12:02       13:44 00:00:00 5 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_10
 1000  1660  4936   740  119 2018-11-09 05:12:03       13:43 00:00:00 5 S  19   0   2  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_11
 1000  1660  5276   740  119 2018-11-09 05:12:06       13:40 00:00:00 5 S  19   0   0  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_12
 1000  1660  5322   740  119 2018-11-09 05:12:06       13:40 00:00:02 5 S  19   0   0  0.2 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_13
 1000  1660  5826   740  119 2018-11-09 05:12:14       13:32 00:00:00 5 S  19   0   3  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_14
 1000  1660  5876   740  119 2018-11-09 05:12:15       13:31 00:00:00 1 S  19   0   5  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           Binder:1660_15
 1000  1660  6984   740  119 2018-11-09 05:13:23       12:23 00:00:00 1 S  23  -4   4  0.0 0 260020 0      990048 3960192 u:r:system_server:s0           RenderThread


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
 
