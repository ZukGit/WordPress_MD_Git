# A
## acpi -> toybox

### acpi --help
```

# acpi --help
usage: acpi [-abctV]

Show status of power sources and thermal devices.

-a      show power adapters
-b      show batteries
-c      show cooling device state
-t      show temperatures
-V      show everything

```

### acpi -a
```
# acpi -a
Adapter 0: off-line
Adapter 1: on-line
Adapter 2: off-line
Adapter 3: off-line
Adapter 4: off-line


```

### acpi -b
```

# acpi -b
Battery 0: 19%

```

### acpi -c
```
# acpi -c
Cooling 0: cpu-1-1-step no state information
Cooling 1: panel0-backlight 0 of 255
Cooling 2: conn_therm no state information
Cooling 3: video-usr no state information
Cooling 4: pm6150-ibat-lvl1 no state information
Cooling 5: video-lowf no state information
Cooling 6: cpu-1-0-usr no state information
Cooling 7: thermal-cpufreq-0 0 of 9
Cooling 8: cpu-1-0-lowf no state information
Cooling 9: modem_current 0 of 3
Cooling 10: back_temp no state information
Cooling 11: cpuss-2-step no state information
Cooling 12: cxip-cdev 0 of 1
Cooling 13: xo_therm no state information
Cooling 14: wlan-usr no state information
Cooling 15: pm6150-tz no state information
Cooling 16: wlan-lowf no state information
Cooling 17: cpuss-2-usr no state information
Cooling 18: cpuss-2-lowf no state information
Cooling 19: battery no state information
Cooling 20: cpuss-0-step no state information
Cooling 21: pm6150l-vph-lvl1 no state information
Cooling 22: video-step no state information
Cooling 23: mdm-core-usr no state information
Cooling 24: cx 0 of 1
Cooling 25: mdm-core-lowf no state information
Cooling 26: cpuss-0-usr no state information
Cooling 27: cpuss-0-lowf no state information
Cooling 28: modem_pa 0 of 3
Cooling 29: bms no state information
Cooling 30: cpuss0-max-step no state information
Cooling 31: pm6150l-tz no state information
Cooling 32: display-step no state information
Cooling 33: gpu-usr no state information
Cooling 34: thermal-cpufreq-5 0 of 9
Cooling 35: q6-hvx-lowf no state information
Cooling 36: pm6150l-vph-lvl2 no state information
Cooling 37: aoss-lowf no state information
Cooling 38: adsp_vdd 0 of 1
Cooling 39: chg_therm no state information
Cooling 40: gpu-step no state information
Cooling 41: pm6150-vbat-lvl2 no state information
Cooling 42: camera-step no state information
Cooling 43: cpu-1-3-usr no state information
Cooling 44: thermal-cpufreq-3 0 of 9
Cooling 45: cpu-1-3-lowf no state information
Cooling 46: cpu-1-2-step no state information
Cooling 47: cdsp 0 of 10
Cooling 48: msm_therm no state information
Cooling 49: lmh-dcvs-00 no state information
Cooling 50: pm6150-vbat-lvl0 no state information
Cooling 51: q6-hvx-step no state information
Cooling 52: cpu-1-1-usr no state information
Cooling 53: thermal-cpufreq-1 0 of 9
Cooling 54: cpu-1-1-lowf no state information
Cooling 55: modem_skin 0 of 3
Cooling 56: cpu-1-0-step no state information
Cooling 57: battery 0 of 9
Cooling 58: sdm_therm no state information
Cooling 59: display-usr no state information
Cooling 60: pm6150-ibat-lvl0 no state information
Cooling 61: display-lowf no state information
Cooling 62: cpuss-3-usr no state information
Cooling 63: thermal-devfreq-0 0 of 4
Cooling 64: cpuss-3-lowf no state information
Cooling 65: modem_vdd 0 of 1
Cooling 66: front_temp no state information
Cooling 67: cpuss-1-step no state information
Cooling 68: mx-cdev-lvl 0 of 1
Cooling 69: camera_flash-therm no state information
Cooling 70: camera-usr no state information
Cooling 71: thermal-cpufreq-7 0 of 11
Cooling 72: camera-lowf no state information
Cooling 73: cpuss-1-usr no state information
Cooling 74: cpuss-1-lowf no state information
Cooling 75: modem_proc 0 of 3
Cooling 76: apc1-max-step no state information
Cooling 77: pm6150l-vph-lvl0 no state information
Cooling 78: q6-hvx-usr no state information
Cooling 79: thermal-cpufreq-6 0 of 11
Cooling 80: aoss-usr no state information
Cooling 81: cdsp_vdd 0 of 1
Cooling 82: quiet_therm no state information
Cooling 83: soc no state information
Cooling 84: wlan-step no state information
Cooling 85: thermal-cpufreq-4 0 of 9
Cooling 86: gpu-lowf no state information
Cooling 87: cpu-1-3-step no state information
Cooling 88: hvx 0 of 10
Cooling 89: pa_therm0 no state information
Cooling 90: lmh-dcvs-01 no state information
Cooling 91: pm6150-vbat-lvl1 no state information
Cooling 92: mdm-core-step no state information
Cooling 93: cpu-1-2-usr no state information
Cooling 94: thermal-cpufreq-2 0 of 9
Cooling 95: cpu-1-2-lowf no state information



```

### acpi -t
```
# acpi -t
Thermal 0: 36.0 degrees C
Thermal 1: -40.0 degrees C
Thermal 2: 37.2 degrees C
Thermal 3: -78.-1 degrees C
Thermal 4: 37.2 degrees C
Thermal 5: 35.6 degrees C
Thermal 6: 35.6 degrees C
Thermal 7: 3413.5 degrees C
Thermal 8: 37.2 degrees C
Thermal 9: 3609.3 degrees C
Thermal 10: 36.3 degrees C
Thermal 11: 37.0 degrees C
Thermal 12: 36.3 degrees C
Thermal 13: 37.5 degrees C
Thermal 14: 37.5 degrees C
Thermal 15: 32.0 degrees C
Thermal 16: 36.3 degrees C
Thermal 17: 0.0 degrees C
Thermal 18: 37.2 degrees C
Thermal 19: 36.0 degrees C
Thermal 20: 36.0 degrees C
Thermal 21: 36.3 degrees C
Thermal 22: 36.3 degrees C
Thermal 23: 32.0 degrees C
Thermal 24: 37.5 degrees C
Thermal 25: 37.0 degrees C
Thermal 26: 35.3 degrees C
Thermal 27: 36.0 degrees C
Thermal 28: 36.6 degrees C
Thermal 29: 0.0 degrees C
Thermal 30: 36.9 degrees C
Thermal 31: 3640.6 degrees C
Thermal 32: 36.3 degrees C
Thermal 33: 383.6 degrees C
Thermal 34: 36.0 degrees C
Thermal 35: 39.1 degrees C
Thermal 36: 39.1 degrees C
Thermal 37: 36.9 degrees C
Thermal 38: 3515.6 degrees C
Thermal 39: 75.0 degrees C
Thermal 40: 383.6 degrees C
Thermal 41: 36.3 degrees C
Thermal 42: 36.0 degrees C
Thermal 43: 36.0 degrees C
Thermal 44: 36.0 degrees C
Thermal 45: 3546.8 degrees C
Thermal 46: 35.6 degrees C
Thermal 47: -78.-1 degrees C
Thermal 48: 35.6 degrees C
Thermal 49: 37.5 degrees C
Thermal 50: 37.5 degrees C
Thermal 51: 3034.9 degrees C
Thermal 52: 36.9 degrees C
Thermal 53: 3205.8 degrees C
Thermal 54: 36.0 degrees C
Thermal 55: 36.0 degrees C
Thermal 56: 36.9 degrees C
Thermal 57: 36.9 degrees C
Thermal 58: 39.7 degrees C
Thermal 59: 0.0 degrees C
Thermal 60: 36.3 degrees C
Thermal 61: 36.9 degrees C
Thermal 62: 35.0 degrees C
Thermal 63: 2.0 degrees C
Thermal 64: 36.0 degrees C
Thermal 65: 36.3 degrees C
Thermal 66: 38.1 degrees C
Thermal 67: 3431.3 degrees C
Thermal 68: 75.0 degrees C
Thermal 69: 383.6 degrees C
Thermal 70: 36.0 degrees C
Thermal 71: 37.2 degrees C
Thermal 72: 37.2 degrees C
Thermal 73: 0.0 degrees C
Thermal 74: 32.0 degrees C
Thermal 75: 32.0 degrees C


```

### acpi -V
```
# acpi -V

Adapter 0: off-line
Battery 0: 20%
Adapter 1: on-line
Adapter 2: off-line
Adapter 3: off-line
Adapter 4: off-line
Thermal 0: 35.6 degrees C
Thermal 1: -40.0 degrees C
Thermal 2: 37.2 degrees C
Thermal 3: -78.-1 degrees C
Thermal 4: 37.2 degrees C
Thermal 5: 35.6 degrees C
Thermal 6: 35.6 degrees C
Thermal 7: 3412.3 degrees C
Thermal 8: 37.5 degrees C
Thermal 9: 3604.1 degrees C
Thermal 10: 36.0 degrees C
Thermal 11: 37.0 degrees C
Thermal 12: 36.0 degrees C
Thermal 13: 37.2 degrees C
Thermal 14: 37.2 degrees C
Thermal 15: 32.0 degrees C
Thermal 16: 36.3 degrees C
Thermal 17: 0.0 degrees C
Thermal 18: 37.2 degrees C
Thermal 19: 35.6 degrees C
Thermal 20: 35.6 degrees C
Thermal 21: 36.3 degrees C
Thermal 22: 36.3 degrees C
Thermal 23: 32.0 degrees C
Thermal 24: 37.5 degrees C
Thermal 25: 37.0 degrees C
Thermal 26: 35.3 degrees C
Thermal 27: 36.0 degrees C
Thermal 28: 36.3 degrees C
Thermal 29: 0.0 degrees C
Thermal 30: 36.9 degrees C
Thermal 31: 3635.4 degrees C
Thermal 32: 36.3 degrees C
Thermal 33: 383.6 degrees C
Thermal 34: 36.0 degrees C
Thermal 35: 39.1 degrees C
Thermal 36: 39.1 degrees C
Thermal 37: 36.9 degrees C
Thermal 38: 3515.6 degrees C
Thermal 39: 75.0 degrees C
Thermal 40: 383.6 degrees C
Thermal 41: 36.3 degrees C
Thermal 42: 36.0 degrees C
Thermal 43: 36.0 degrees C
Thermal 44: 36.0 degrees C
Thermal 45: 3541.6 degrees C
Thermal 46: 35.6 degrees C
Thermal 47: -78.-1 degrees C
Thermal 48: 35.6 degrees C
Thermal 49: 37.5 degrees C
Thermal 50: 37.5 degrees C
Thermal 51: 3034.8 degrees C
Thermal 52: 36.9 degrees C
Thermal 53: 3205.8 degrees C
Thermal 54: 36.0 degrees C
Thermal 55: 36.0 degrees C
Thermal 56: 36.9 degrees C
Thermal 57: 36.9 degrees C
Thermal 58: 39.1 degrees C
Thermal 59: 0.0 degrees C
Thermal 60: 36.3 degrees C
Thermal 61: 36.9 degrees C
Thermal 62: 35.0 degrees C
Thermal 63: 2.0 degrees C
Thermal 64: 36.0 degrees C
Thermal 65: 36.0 degrees C
Thermal 66: 37.8 degrees C
Thermal 67: 3431.3 degrees C
Thermal 68: 75.0 degrees C
Thermal 69: 383.6 degrees C
Thermal 70: 35.6 degrees C
Thermal 71: 37.2 degrees C
Thermal 72: 37.2 degrees C
Thermal 73: 0.0 degrees C
Thermal 74: 32.0 degrees C
Thermal 75: 32.0 degrees C
Cooling 0: cpu-1-1-step no state information
Cooling 1: panel0-backlight 0 of 255
Cooling 2: conn_therm no state information
Cooling 3: video-usr no state information
Cooling 4: pm6150-ibat-lvl1 no state information
Cooling 5: video-lowf no state information
Cooling 6: cpu-1-0-usr no state information
Cooling 7: thermal-cpufreq-0 0 of 9
Cooling 8: cpu-1-0-lowf no state information
Cooling 9: modem_current 0 of 3
Cooling 10: back_temp no state information
Cooling 11: cpuss-2-step no state information
Cooling 12: cxip-cdev 0 of 1
Cooling 13: xo_therm no state information
Cooling 14: wlan-usr no state information
Cooling 15: pm6150-tz no state information
Cooling 16: wlan-lowf no state information
Cooling 17: cpuss-2-usr no state information
Cooling 18: cpuss-2-lowf no state information
Cooling 19: battery no state information
Cooling 20: cpuss-0-step no state information
Cooling 21: pm6150l-vph-lvl1 no state information
Cooling 22: video-step no state information
Cooling 23: mdm-core-usr no state information
Cooling 24: cx 0 of 1
Cooling 25: mdm-core-lowf no state information
Cooling 26: cpuss-0-usr no state information
Cooling 27: cpuss-0-lowf no state information
Cooling 28: modem_pa 0 of 3
Cooling 29: bms no state information
Cooling 30: cpuss0-max-step no state information
Cooling 31: pm6150l-tz no state information
Cooling 32: display-step no state information
Cooling 33: gpu-usr no state information
Cooling 34: thermal-cpufreq-5 0 of 9
Cooling 35: q6-hvx-lowf no state information
Cooling 36: pm6150l-vph-lvl2 no state information
Cooling 37: aoss-lowf no state information
Cooling 38: adsp_vdd 0 of 1
Cooling 39: chg_therm no state information
Cooling 40: gpu-step no state information
Cooling 41: pm6150-vbat-lvl2 no state information
Cooling 42: camera-step no state information
Cooling 43: cpu-1-3-usr no state information
Cooling 44: thermal-cpufreq-3 0 of 9
Cooling 45: cpu-1-3-lowf no state information
Cooling 46: cpu-1-2-step no state information
Cooling 47: cdsp 0 of 10
Cooling 48: msm_therm no state information
Cooling 49: lmh-dcvs-00 no state information
Cooling 50: pm6150-vbat-lvl0 no state information
Cooling 51: q6-hvx-step no state information
Cooling 52: cpu-1-1-usr no state information
Cooling 53: thermal-cpufreq-1 0 of 9
Cooling 54: cpu-1-1-lowf no state information
Cooling 55: modem_skin 0 of 3
Cooling 56: cpu-1-0-step no state information
Cooling 57: battery 0 of 9
Cooling 58: sdm_therm no state information
Cooling 59: display-usr no state information
Cooling 60: pm6150-ibat-lvl0 no state information
Cooling 61: display-lowf no state information
Cooling 62: cpuss-3-usr no state information
Cooling 63: thermal-devfreq-0 0 of 4
Cooling 64: cpuss-3-lowf no state information
Cooling 65: modem_vdd 0 of 1
Cooling 66: front_temp no state information
Cooling 67: cpuss-1-step no state information
Cooling 68: mx-cdev-lvl 0 of 1
Cooling 69: camera_flash-therm no state information
Cooling 70: camera-usr no state information
Cooling 71: thermal-cpufreq-7 0 of 11
Cooling 72: camera-lowf no state information
Cooling 73: cpuss-1-usr no state information
Cooling 74: cpuss-1-lowf no state information
Cooling 75: modem_proc 0 of 3
Cooling 76: apc1-max-step no state information
Cooling 77: pm6150l-vph-lvl0 no state information
Cooling 78: q6-hvx-usr no state information
Cooling 79: thermal-cpufreq-6 0 of 11
Cooling 80: aoss-usr no state information
Cooling 81: cdsp_vdd 0 of 1
Cooling 82: quiet_therm no state information
Cooling 83: soc no state information
Cooling 84: wlan-step no state information
Cooling 85: thermal-cpufreq-4 0 of 9
Cooling 86: gpu-lowf no state information
Cooling 87: cpu-1-3-step no state information
Cooling 88: hvx 0 of 10
Cooling 89: pa_therm0 no state information
Cooling 90: lmh-dcvs-01 no state information
Cooling 91: pm6150-vbat-lvl1 no state information
Cooling 92: mdm-core-step no state information
Cooling 93: cpu-1-2-usr no state information
Cooling 94: thermal-cpufreq-2 0 of 9
Cooling 95: cpu-1-2-lowf no state information

```

## adbd [Android Debug Bridge Daemon]

### adbd --help
```
# adbd --help
Segmentation fault


```

### adbd 
```
# adbd
adbd E 06-26 14:40:14 13974 13974 adbd_auth.cpp:183] Failed to get adbd socket: Bad file descriptor
adbd E 06-26 14:40:14 13974 13974 adbd_auth.cpp:192] Failed to get adbd socket: Bad file descriptor
adbd I 06-26 14:40:14 13974 13975 usb.cpp:250] initializing functionfs
adbd I 06-26 14:40:14 13974 13975 usb.cpp:271] opening control endpoint /dev/usb-ffs/adb/ep0
adbd I 06-26 14:40:15 13974 13975 usb.cpp:250] initializing functionfs
adbd I 06-26 14:40:15 13974 13975 usb.cpp:271] opening control endpoint /dev/usb-ffs/adb/ep0
adbd I 06-26 14:40:16 13974 13975 usb.cpp:250] initializing functionfs

```


## am

## am -h
```
 # am -h
Activity manager (activity) commands:
  help
      Print this help text.
  start-activity [-D] [-N] [-W] [-P <FILE>] [--start-profiler <FILE>]
          [--sampling INTERVAL] [--streaming] [-R COUNT] [-S]
          [--track-allocation] [--user <USER_ID> | current] <INTENT>
      Start an Activity.  Options are:
      -D: enable debugging
      -N: enable native debugging
      -W: wait for launch to complete
      --start-profiler <FILE>: start profiler and send results to <FILE>
      --sampling INTERVAL: use sample profiling with INTERVAL microseconds
          between samples (use with --start-profiler)
      --streaming: stream the profiling output to the specified file
          (use with --start-profiler)
      -P <FILE>: like above, but profiling stops when app goes idle
      --attach-agent <agent>: attach the given agent before binding
      --attach-agent-bind <agent>: attach the given agent during binding
      -R: repeat the activity launch <COUNT> times.  Prior to each repeat,
          the top activity will be finished.
      -S: force stop the target app before starting the activity
      --track-allocation: enable tracking of object allocations
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
      --windowingMode <WINDOWING_MODE>: The windowing mode to launch the activity into.
      --activityType <ACTIVITY_TYPE>: The activity type to launch the activity as.
  start-service [--user <USER_ID> | current] <INTENT>
      Start a Service.  Options are:
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
  start-foreground-service [--user <USER_ID> | current] <INTENT>
      Start a foreground Service.  Options are:
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
  stop-service [--user <USER_ID> | current] <INTENT>
      Stop a Service.  Options are:
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
  broadcast [--user <USER_ID> | all | current] <INTENT>
      Send a broadcast Intent.  Options are:
      --user <USER_ID> | all | current: Specify which user to send to; if not
          specified then send to all users.
      --receiver-permission <PERMISSION>: Require receiver to hold permission.
      --sticky-broadcast : Send broadcast as a sticky broadcast.
  instrument [-r] [-e <NAME> <VALUE>] [-p <FILE>] [-w]
          [--user <USER_ID> | current] [--no-hidden-api-checks]
          [--no-window-animation] [--abi <ABI>] <COMPONENT>
      Start an Instrumentation.  Typically this target <COMPONENT> is in the
      form <TEST_PACKAGE>/<RUNNER_CLASS> or only <TEST_PACKAGE> if there
      is only one instrumentation.  Options are:
      -r: print raw results (otherwise decode REPORT_KEY_STREAMRESULT).  Use with
          [-e perf true] to generate raw output for performance measurements.
      -e <NAME> <VALUE>: set argument <NAME> to <VALUE>.  For test runners a
          common form is [-e <testrunner_flag> <value>[,<value>...]].
      -p <FILE>: write profiling data to <FILE>
      -m: Write output as protobuf to stdout (machine readable)
      -f <Optional PATH/TO/FILE>: Write output as protobuf to a file (machine
          readable). If path is not specified, default directory and file name will
          be used: /sdcard/instrument-logs/log-yyyyMMdd-hhmmss-SSS.instrumentation_data_proto
      -w: wait for instrumentation to finish before returning.  Required for
          test runners.
      --user <USER_ID> | current: Specify user instrumentation runs in;
          current user if not specified.
      --no-hidden-api-checks: disable restrictions on use of hidden API.
      --no-window-animation: turn off window animations while running.
      --abi <ABI>: Launch the instrumented process with the selected ABI.
          This assumes that the process supports the selected ABI.
  trace-ipc [start|stop] [--dump-file <FILE>]
      Trace IPC transactions.
      start: start tracing IPC transactions.
      stop: stop tracing IPC transactions and dump the results to file.
      --dump-file <FILE>: Specify the file the trace should be dumped to.
  profile [start|stop] [--user <USER_ID> current] [--sampling INTERVAL]
          [--streaming] <PROCESS> <FILE>
      Start and stop profiler on a process.  The given <PROCESS> argument
        may be either a process name or pid.  Options are:
      --user <USER_ID> | current: When supplying a process name,
          specify user of process to profile; uses current user if not specified.
      --sampling INTERVAL: use sample profiling with INTERVAL microseconds
          between samples
      --streaming: stream the profiling output to the specified file
  dumpheap [--user <USER_ID> current] [-n] [-g] <PROCESS> <FILE>
      Dump the heap of a process.  The given <PROCESS> argument may
        be either a process name or pid.  Options are:
      -n: dump native heap instead of managed heap
      -g: force GC before dumping the heap
      --user <USER_ID> | current: When supplying a process name,
          specify user of process to dump; uses current user if not specified.
  set-debug-app [-w] [--persistent] <PACKAGE>
      Set application <PACKAGE> to debug.  Options are:
      -w: wait for debugger when application starts
      --persistent: retain this value
  clear-debug-app
      Clear the previously set-debug-app.
  set-watch-heap <PROCESS> <MEM-LIMIT>
      Start monitoring pss size of <PROCESS>, if it is at or
      above <HEAP-LIMIT> then a heap dump is collected for the user to report.
  clear-watch-heap
      Clear the previously set-watch-heap.
  bug-report [--progress | --telephony]
      Request bug report generation; will launch a notification
        when done to select where it should be delivered. Options are:
     --progress: will launch a notification right away to show its progress.
     --telephony: will dump only telephony sections.
  force-stop [--user <USER_ID> | all | current] <PACKAGE>
      Completely stop the given application package.
  crash [--user <USER_ID>] <PACKAGE|PID>
      Induce a VM crash in the specified package or process
  kill [--user <USER_ID> | all | current] <PACKAGE>
      Kill all background processes associated with the given application.
  kill-all
      Kill all processes that are safe to kill (cached, etc).
  make-uid-idle [--user <USER_ID> | all | current] <PACKAGE>
      If the given application's uid is in the background and waiting to
      become idle (not allowing background services), do that now.
  monitor [--gdb <port>]
      Start monitoring for crashes or ANRs.
      --gdb: start gdbserv on the given port at crash/ANR
  watch-uids [--oom <uid>]
      Start watching for and reporting uid state changes.
      --oom: specify a uid for which to report detailed change messages.
  xhang [--allow-restart]
      Hang the system.
      --allow-restart: allow watchdog to perform normal system restart
  restart
      Restart the user-space system.
  idle-maintenance
      Perform idle maintenance now.
  screen-compat [on|off] <PACKAGE>
      Control screen compatibility mode of <PACKAGE>.
  package-importance <PACKAGE>
      Print current importance of <PACKAGE>.
  to-uri [INTENT]
      Print the given Intent specification as a URI.
  to-intent-uri [INTENT]
      Print the given Intent specification as an intent: URI.
  to-app-uri [INTENT]
      Print the given Intent specification as an android-app: URI.
  switch-user <USER_ID>
      Switch to put USER_ID in the foreground, starting
      execution of that user if it is currently stopped.
  get-current-user
      Returns id of the current foreground user.
  start-user <USER_ID>
      Start USER_ID in background if it is currently stopped;
      use switch-user if you want to start the user in foreground
  unlock-user <USER_ID> [TOKEN_HEX]
      Attempt to unlock the given user using the given authorization token.
  stop-user [-w] [-f] <USER_ID>
      Stop execution of USER_ID, not allowing it to run any
      code until a later explicit start or switch to it.
      -w: wait for stop-user to complete.
      -f: force stop even if there are related users that cannot be stopped.
  is-user-stopped <USER_ID>
      Returns whether <USER_ID> has been stopped or not.
  get-started-user-state <USER_ID>
      Gets the current state of the given started user.
  track-associations
      Enable association tracking.
  untrack-associations
      Disable and clear association tracking.
  get-uid-state <UID>
      Gets the process state of an app given its <UID>.
  attach-agent <PROCESS> <FILE>
    Attach an agent to the specified <PROCESS>, which may be either a process name or a PID.
  get-config [--days N] [--device] [--proto]
      Retrieve the configuration and any recent configurations of the device.
      --days: also return last N days of configurations that have been seen.
      --device: also output global device configuration info.
      --proto: return result as a proto; does not include --days info.
  supports-multiwindow
      Returns true if the device supports multiwindow.
  supports-split-screen-multi-window
      Returns true if the device supports split screen multiwindow.
  suppress-resize-config-changes <true|false>
      Suppresses configuration changes due to user resizing an activity/task.
  set-inactive [--user <USER_ID>] <PACKAGE> true|false
      Sets the inactive state of an app.
  get-inactive [--user <USER_ID>] <PACKAGE>
      Returns the inactive state of an app.
  set-standby-bucket [--user <USER_ID>] <PACKAGE> active|working_set|frequent|rare
      Puts an app in the standby bucket.
  get-standby-bucket [--user <USER_ID>] <PACKAGE>
      Returns the standby bucket of an app.
  send-trim-memory [--user <USER_ID>] <PROCESS>
          [HIDDEN|RUNNING_MODERATE|BACKGROUND|RUNNING_LOW|MODERATE|RUNNING_CRITICAL|COMPLETE]
      Send a memory trim event to a <PROCESS>.  May also supply a raw trim int level.
  display [COMMAND] [...]: sub-commands for operating on displays.
       move-stack <STACK_ID> <DISPLAY_ID>
           Move <STACK_ID> from its current display to <DISPLAY_ID>.
  stack [COMMAND] [...]: sub-commands for operating on activity stacks.
       start <DISPLAY_ID> <INTENT>
           Start a new activity on <DISPLAY_ID> using <INTENT>
       move-task <TASK_ID> <STACK_ID> [true|false]
           Move <TASK_ID> from its current stack to the top (true) or
           bottom (false) of <STACK_ID>.
       resize <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Change <STACK_ID> size and position to <LEFT,TOP,RIGHT,BOTTOM>.
       resize-animated <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Same as resize, but allow animation.
       resize-docked-stack <LEFT,TOP,RIGHT,BOTTOM> [<TASK_LEFT,TASK_TOP,TASK_RIGHT,TASK_BOTTOM>]
           Change docked stack to <LEFT,TOP,RIGHT,BOTTOM>
           and supplying temporary different task bounds indicated by
           <TASK_LEFT,TOP,RIGHT,BOTTOM>
       move-top-activity-to-pinned-stack: <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Moves the top activity from
           <STACK_ID> to the pinned stack using <LEFT,TOP,RIGHT,BOTTOM> for the
           bounds of the pinned stack.
       positiontask <TASK_ID> <STACK_ID> <POSITION>
           Place <TASK_ID> in <STACK_ID> at <POSITION>
       list
           List all of the activity stacks and their sizes.
       info <WINDOWING_MODE> <ACTIVITY_TYPE>
           Display the information about activity stack in <WINDOWING_MODE> and <ACTIVITY_TYPE>.
       remove <STACK_ID>
           Remove stack <STACK_ID>.
  task [COMMAND] [...]: sub-commands for operating on activity tasks.
       lock <TASK_ID>
           Bring <TASK_ID> to the front and don't allow other tasks to run.
       lock stop
           End the current task lock.
       resizeable <TASK_ID> [0|1|2|3]
           Change resizeable mode of <TASK_ID> to one of the following:
           0: unresizeable
           1: crop_windows
           2: resizeable
           3: resizeable_and_pipable
       resize <TASK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Makes sure <TASK_ID> is in a stack with the specified bounds.
           Forces the task to be resizeable and creates a stack if no existing stack
           has the specified bounds.
  update-appinfo <USER_ID> <PACKAGE_NAME> [<PACKAGE_NAME>...]
      Update the ApplicationInfo objects of the listed packages for <USER_ID>
      without restarting any processes.
  write
      Write all pending state to storage.

<INTENT> specifications include these flags and arguments:
    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
    [-c <CATEGORY> [-c <CATEGORY>] ...]
    [-n <COMPONENT_NAME>]
    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
    [--esn <EXTRA_KEY> ...]
    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as Integer[])
    [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as List<Integer>)
    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as Long[])
    [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as List<Long>)
    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as Float[])
    [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as List<Float>)
    [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as String[]; to embed a comma into a string,
         escape it using "\,")
    [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as List<String>; to embed a comma into a string,
         escape it using "\,")
    [-f <FLAG>]
    [--grant-read-uri-permission] [--grant-write-uri-permission]
    [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]
    [--debug-log-resolution] [--exclude-stopped-packages]
    [--include-stopped-packages]
    [--activity-brought-to-front] [--activity-clear-top]
    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
    [--activity-launched-from-history] [--activity-multiple-task]
    [--activity-no-animation] [--activity-no-history]
    [--activity-no-user-action] [--activity-previous-is-top]
    [--activity-reorder-to-front] [--activity-reset-task-if-needed]
    [--activity-single-top] [--activity-clear-task]
    [--activity-task-on-home] [--activity-match-external]
    [--receiver-registered-only] [--receiver-replace-pending]
    [--receiver-foreground] [--receiver-no-abort]
    [--receiver-include-background]
    [--selector]
    [<URI> | <PACKAGE> | <COMPONENT>]


```

## app_process -> app_process64

### None
## app_process32
### None
## app_process64
### None

## applypatch

### applypatch -h
```
# applypatch -h
usage: applypatch [-b <bonus-file>] <src-file> <tgt-file> <tgt-sha1> <tgt-size> [<src-sha1>:<patch> ...]
   or  applypatch -c <file> [<sha1> ...]
   or  applypatch -l

Filenames may be of the form
  EMMC:<partition>:<len_1>:<sha1_1>:<len_2>:<sha1_2>:...
to specify reading from or writing to an EMMC partition.

```

### applypatch -l
```
# applypatch -l
The bsdiff library used herein is:

Copyright 2003-2005 Colin Percival
All rights reserved

Redistribution and use in source and binary forms, with or without
modification, are permitted providing that the following conditions
are met:
1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

------------------

This program uses Julian R Seward's "libbzip2" library, available
from http://www.bzip.org/.

```
## appops

### appops -h
```
appops -h
AppOps service (appops) commands:
  help
    Print this help text.
  start [--user <USER_ID>] <PACKAGE | UID> <OP>
    Starts a given operation for a particular application.
  stop [--user <USER_ID>] <PACKAGE | UID> <OP>
    Stops a given operation for a particular application.
  set [--user <USER_ID>] <PACKAGE | UID> <OP> <MODE>
    Set the mode for a particular application and operation.
  get [--user <USER_ID>] <PACKAGE | UID> [<OP>]
    Return the mode for a particular application and optional operation.
  query-op [--user <USER_ID>] <OP> [<MODE>]
    Print all packages that currently have the given op in the given mode.
  reset [--user <USER_ID>] [<PACKAGE>]
    Reset the given application or all applications to default modes.
  write-settings
    Immediately write pending changes to storage.
  read-settings
    Read the last written settings, replacing current state in RAM.
  options:
    <PACKAGE> an Android package name.
    <OP>      an AppOps operation.
    <MODE>    one of allow, ignore, deny, or default
    <USER_ID> the user id under which the package is installed. If --user is not
              specified, the current user is assumed.



```


## appwidget

### appwidget -h
```
# appwidget --help
usage: adb shell appwidget [subcommand] [options]

usage: adb shell appwidget grantbind --package <PACKAGE>  [--user <USER_ID> | current]
  <PACKAGE> an Android package name.
  <USER_ID> The user id under which the package is installed.
  Example:
  # Grant the "foo.bar.baz" package to bind app widgets for the current user.
  adb shell grantbind --package foo.bar.baz --user current

usage: adb shell appwidget revokebind --package <PACKAGE> [--user <USER_ID> | current]
  <PACKAGE> an Android package name.
  <USER_ID> The user id under which the package is installed.
  Example:
  # Revoke the permisison to bind app widgets from the "foo.bar.baz" package.
  adb shell revokebind --package foo.bar.baz --user current


[ERROR] Unsupported operation: --help

```



## arping

### arping -h 
```
# arping
Usage: arping [-fqbDUAV] [-c count] [-w timeout] [-I device] [-s source] destination
  -f : quit on first reply
  -q : be quiet
  -b : keep broadcasting, don't go unicast
  -D : duplicate address detection mode
  -U : Unsolicited ARP mode, update your neighbours
  -A : ARP answer mode, update your neighbours
  -V : print version and exit
  -c count : how many packets to send
  -w timeout : how long to wait for a reply
  -I device : which ethernet device to use
  -s source : source ip address
  destination : ask for what ip address

```

### arping -V 
```
# arping -V
arping utility, iputils-s20121221


```



## atrace


### atrace --help
```
# atrace --help
usage: atrace [options] [categories...]
options include:
  -a appname      enable app-level tracing for a comma separated list of cmdlines; * is a wildcard matching any process
  -b N            use a trace buffer size of N KB
  -c              trace into a circular buffer
  -f filename     use the categories written in a file as space-separated
                    values in a line
  -k fname,...    trace the listed kernel functions
  -n              ignore signals
  -s N            sleep for N seconds before tracing [default 0]
  -t N            trace for N seconds [default 5]
  -z              compress the trace dump
  --async_start   start circular trace and return immediately
  --async_dump    dump the current contents of circular trace buffer
  --async_stop    stop tracing and dump the current contents of circular
                    trace buffer
  --stream        stream trace to stdout as it enters the trace buffer
                    Note: this can take significant CPU time, and is best
                    used for measuring things that are not affected by
                    CPU performance, like pagecache usage.
  --list_categories
                  list the available tracing categories
 -o filename      write the trace to the specified file instead of stdout.
 
```


## audioserver
### audioserver  -h
```
audioserver -h
I/O warning : failed to load external entity "/odm/etc/audio_policy_configuration.xml"
I/O warning : failed to load external entity "/vendor/etc/audio/audio_policy_configuration.xml"


```


## awk


### awk --help
```
# awk -h
usage: awk [-F fs] [-v var=value] [-f progfile | 'prog'] [file ...]


```

# B

## base64 -> toybox

### base64  --help
```
base64  --help
usage: base64 [-di] [-w COLUMNS] [FILE...]

Encode or decode in base64.

-d      decode
-i      ignore non-alphabetic characters
-w      wrap output at COLUMNS (default 76 or 0 for no wrap)

```


## basename -> toybox

### basename --help
```
basename --help 【 获取目录的最后一个文件名称 | 文件夹名称】
usage: basename string [suffix]

Return non-directory portion of a pathname removing suffix


```
## bcc

### bcc -help
```
 # bcc -help
USAGE: bcc [subcommand] [options] <input bitcode files>

OPTIONS:

General options:

  -C                                              - Alias for -mtriple
  -O=<char>                                       - Optimization level. [-O0, -O1, -O2, or -O3] (default: -O3)
  -aarch64-neon-syntax                            - Choose style of NEON code to emit from AArch64 backend:
    =generic                                      -   Emit generic NEON assembly
    =apple                                        -   Emit Apple-style NEON assembly
  -bclib=<bclib>                                  - Specify the bclib filename
  -bclib_relaxed=<bclib_relaxed>                  - Specify the bclib filename optimized for relaxed precision floating point maths
  -bounds-checking-single-trap                    - Use one trap block per function
  -build-checksum=<checksum>                      - Embed a checksum of this compiler invocation for cache invalidation at a later time
  -color                                          - use colored syntax highlighting (default=autodetect)
  -embedRSInfo                                    - Embed RS Info into the object file instead of generating a separate .o.info file
  -emit-llvm                                      - Emit an LLVM-IR version of the generated program
  -enable-implicit-null-checks                    - Fold null checks into faulting memory operations
  -enable-load-pre                                -
  -enable-name-compression                        - Enable name string compression
  -enable-objc-arc-opts                           - enable/disable all ARC Optimizations
  -enable-scoped-noalias                          -
  -enable-tbaa                                    -
  -exhaustive-register-search                     - Exhaustive Search for registers bypassing the depth and interference cutoffs of last chance recoloring
  -expensive-combines                             - Enable expensive instruction combines
  -fPIC                                           - Generate fully relocatable, position independent code
  -filter-print-funcs=<function names>            - Only print IR for functions whose name match this for all print-[before|after][-all] options
  -imp-null-check-page-size=<int>                 - The page size of the target in bytes
  -internalize-public-api-file=<filename>         - A file containing list of symbol names to preserve
  -internalize-public-api-list=<list>             - A list of symbol names to preserve
  -invoke=<string>                                - Invocable functions
  -join-liveintervals                             - Coalesce copies (default=true)
  -limit-float-precision=<uint>                   - Generate low-precision inline sequences for some float libcalls
  -load=<pluginfilename>                          - Load the specified plugin
  -merge=<string>                                 - Lists of kernels to merge (as source-and-slot pairs) and names for the final merged kernels
  -mtriple=<triple>                               - Specify the target triple (default: aarch64-none-linux-gnueabi)
  -no-discriminators                              - Disable generation of discriminator information.
  -o=<filename>                                   - Specify the output filename
  -output_path=<output path>                      - Specify the output path
  -plugin=<pluginfilename>                        - Load the specified vendor plugin. Use this instead of the -load option
  -print-after-all                                - Print IR after each pass
  -print-before-all                               - Print IR before each pass
  -print-machineinstrs=<pass-name>                - Print machine instrs
  -regalloc                                       - Register allocator to use
    =default                                      -   pick register allocator based on -O option
    =pbqp                                         -   PBQP register allocator
    =greedy                                       -   greedy register allocator
    =fast                                         -   fast register allocator
    =basic                                        -   basic register allocator
  -rewrite-map-file=<filename>                    - Symbol Rewrite Map
  -rng-seed=<seed>                                - Seed for the random number generator
  -rs-debug-ctx                                   - Enable build to work with a RenderScript debug context
  -rs-global-info                                 - Embed information about global variables in the code
  -rs-global-info-skip-constant                   - Skip embedding information about constant global variables in the code
  -sample-profile-check-record-coverage=<N>       - Emit a warning if less than N% of records in the input profile are matched to the IR.
  -sample-profile-check-sample-coverage=<N>       - Emit a warning if less than N% of samples in the input profile are matched to the IR.
  -sample-profile-inline-hot-threshold=<N>        - Inlined functions that account for more than N% of all samples collected in the parent function, will be inlined again.
  -sample-profile-max-propagate-iterations=<uint> - Maximum number of iterations to go through when propagating sample block/edge weights through the CFG.
  -stackmap-version=<int>                         - Specify the stackmap encoding version (default = 1)
  -static-func-full-module-prefix                 - Use full module build paths in the profile counter names for static functions.
  -stats                                          - Enable statistics output from program (available with Asserts)
  -stats-json                                     - Display statistics as json data
  -summary-file=<string>                          - The summary file to use for function importing.
  -threads=<int>                                  -
  -time-passes                                    - Time each pass, printing elapsed time for each on exit
  -verify-debug-info                              -
  -verify-dom-info                                - Verify dominator info (time consuming)
  -verify-loop-info                               - Verify loop info (time consuming)
  -verify-machine-dom-info                        - Verify machine dominator info (time consuming)
  -verify-regalloc                                - Verify during register allocation
  -verify-region-info                             - Verify region info (time consuming)
  -verify-scev                                    - Verify ScalarEvolution's backedge taken counts (slow)
  -verify-scev-maps                               - Verify no dangling value in ScalarEvolution's ExprValueMap (slow)
  -vp-counters-per-site=<number>                  - The average number of profile counters allocated per value profiling site.
  -vp-static-alloc                                - Do static counter allocation for value profiler

Generic Options:

  -help                                           - Display available options (-help-hidden for more)
  -help-list                                      - Display list of available options (-help-list-hidden for more)
  -version                                        - Display the version of this program


```


### bcc -version
```
# bcc -version
libbcc (The Android Open Source Project, http://www.android.com/):
  Default target: aarch64-none-linux-gnueabi

LLVM (http://llvm.org/):
  Version: 3.8.275480

```





## bl_notify_sys

### NONE
```
# bl_notify_sys --help
needs at least 2 args: COMMAND KEY [VALUE]

```

## blank_screen

### blank_screen
```
灭屏
blank_screen
Failed to shut off screen for type 0

```

### blank_screen --help
```

blank_screen --help

```


## blkid

### blkid --help
```
# blkid --help
blkid: invalid option -- -
blkid 1.0.0 (12-Feb-2003)
usage:  blkid [-c <file>] [-ghlLv] [-o format] [-s <tag>] [-t <token>]
    [-w <file>] [dev ...]
        -c      cache file (default: /etc/blkid.tab, /dev/null = none)
        -h      print this usage message and exit
        -g      garbage collect the blkid cache
        -s      show specified tag(s) (default show all tags)
        -t      find device with a specific token (NAME=value pair)
        -l      lookup the the first device with arguments specified by -t
        -v      print version and exit
        -w      write cache to different file (/dev/null = no write)
        dev     specify device(s) to probe (default: all devices)

```

### blkid -v 
```
blkid -v
blkid 1.0.0 (12-Feb-2003)

``` 


## blockdev -> toybox

### blockdev --help
```
# blockdev --help
usage: blockdev --OPTION... BLOCKDEV...

Call ioctl(s) on each listed block device

OPTIONs:
--setro         Set read only
--setrw         Set read write
--getro         Get read only
--getss         Get sector size
--getbsz        Get block size
--setbsz        BYTES   Set block size
--getsz         Get device size in 512-byte sectors
--getsize       Get device size in sectors (deprecated)
--getsize64     Get device size in bytes
--flushbufs     Flush buffers
--rereadpt      Reread partition table

```

## bmgr

### bmgr --help
```
# bmgr --help
Unknown command
usage: bmgr [backup|restore|list|transport|run]
       bmgr backup PACKAGE
       bmgr enable BOOL
       bmgr enabled
       bmgr list transports [-c]
       bmgr list sets
       bmgr transport WHICH|-c WHICH_COMPONENT
       bmgr restore TOKEN
       bmgr restore TOKEN PACKAGE...
       bmgr restore PACKAGE
       bmgr run
       bmgr wipe TRANSPORT PACKAGE
       bmgr fullbackup PACKAGE...
       bmgr backupnow --all|PACKAGE...
       bmgr cancel backups

The 'backup' command schedules a backup pass for the named package.
Note that the backup pass will effectively be a no-op if the package
does not actually have changed data to store.

The 'enable' command enables or disables the entire backup mechanism.
If the argument is 'true' it will be enabled, otherwise it will be
disabled.  When disabled, neither backup or restore operations will
be performed.

The 'enabled' command reports the current enabled/disabled state of
the backup mechanism.

The 'list transports' command reports the names of the backup transports
BackupManager is currently bound to. These names can be passed as arguments
to the 'transport' and 'wipe' commands.  The currently active transport
is indicated with a '*' character. If -c flag is used, all available
transport components on the device are listed. These can be used with
the component variant of 'transport' command.

The 'list sets' command reports the token and name of each restore set
available to the device via the currently active transport.

The 'transport' command designates the named transport as the currently
active one.  This setting is persistent across reboots. If -c flag is
specified, the following string is treated as a component name.

The 'restore' command when given just a restore token initiates a full-system
restore operation from the currently active transport.  It will deliver
the restore set designated by the TOKEN argument to each application
that had contributed data to that restore set.

The 'restore' command when given a token and one or more package names
initiates a restore operation of just those given packages from the restore
set designated by the TOKEN argument.  It is effectively the same as the
'restore' operation supplying only a token, but applies a filter to the
set of applications to be restored.

The 'restore' command when given just a package name intiates a restore of
just that one package according to the restore set selection algorithm
used by the RestoreSession.restorePackage() method.

The 'run' command causes any scheduled backup operation to be initiated
immediately, without the usual waiting period for batching together
data changes.

The 'wipe' command causes all backed-up data for the given package to be
erased from the given transport's storage.  The next backup operation
that the given application performs will rewrite its entire data set.
Transport names to use here are those reported by 'list transports'.

The 'fullbackup' command induces a full-data stream backup for one or more
packages.  The data is sent via the currently active transport.

The 'backupnow' command runs an immediate backup for one or more packages.
    --all flag runs backup for all eligible packages.
For each package it will run key/value or full data backup
depending on the package's manifest declarations.
The data is sent via the currently active transport.
The 'cancel backups' command cancels all running backups.


```


## bootanimation

### bootanimation -h
```
查看开机动画 【 Ctrl + C 】 恢复

```
## bootctl

### bootctl -h
```
# bootctl -h
bootctl - command-line wrapper for the boot HAL.

Usage:
  bootctl COMMAND

Commands:
  bootctl hal-info                       - Show info about boot_control HAL used.
  bootctl get-number-slots               - Prints number of slots.
  bootctl get-current-slot               - Prints currently running SLOT.
  bootctl mark-boot-successful           - Mark current slot as GOOD.
  bootctl set-active-boot-slot SLOT      - On next boot, load and execute SLOT.
  bootctl set-slot-as-unbootable SLOT    - Mark SLOT as invalid.
  bootctl is-slot-bootable SLOT          - Returns 0 only if SLOT is bootable.
  bootctl is-slot-marked-successful SLOT - Returns 0 only if SLOT is marked GOOD.
  bootctl get-suffix SLOT                - Prints suffix for SLOT.

SLOT parameter is the zero-based slot-number.



```

### bootctl hal-info
```
# bootctl hal-info
HAL Version: android.hardware.boot@1.0::IBootControl

```

### bootctl get-number-slots
```
# bootctl get-number-slots
2

```
### bootctl get-current-slot
```
# bootctl get-current-slot
0

```

###  bootctl get-suffix SLOT  
```
# bootctl get-suffix SLOT
_a

```

## bootstat

### bootstat --help
```
# bootstat --help
Usage: bootstat [options]
options include:
  -h, --help              Show this help
  -l, --log               Log all metrics to logstorage
  -p, --print             Dump the boot event records to the console
  -r, --record            Record the timestamp of a named boot event
  --value                 Optional value to associate with the boot event
  --record_boot_complete  Record metrics related to the time for the device boot
  --record_boot_reason    Record the reason why the device booted
  --record_time_since_factory_reset Record the time since the device was reset

```

### bootstat -l
```
# bootstat -p
Boot events:
------------
last_boot_time_utc      1561529731
build_date      1558432565
factory_reset_boot_complete_no_encryption       61
factory_reset_boot_complete     61
ro.boottime.init        4112
ro.boottime.init.selinux        164
ro.boottime.init.cold_boot_wait 0
boottime.bootloader.total       0
absolute_boot_time      103
boot_reason     4
system_boot_reason      4
factory_reset_current_time      1561529731
factory_reset   1556435156
time_since_last_boot    164444
ota_boot_complete_no_encryption 143
ota_boot_complete       143
factory_reset_record_value      1556435156
time_since_factory_reset        5094575
boot_complete_no_encryption     103
boot_complete   103


```

## bpfloader

### None

## bt_logger

### None


## btsnoop
### None

## bu

### bu -h
```
# bu -h
 backup [-f FILE] [-apk|-noapk] [-obb|-noobb] [-shared|-noshared] [-all]
        [-system|-nosystem] [-keyvalue|-nokeyvalue] [PACKAGE...]
     write an archive of the device's data to FILE [default=backup.adb]
     package list optional if -all/-shared are supplied
     -apk/-noapk: do/don't back up .apk files (default -noapk)
     -obb/-noobb: do/don't back up .obb files (default -noobb)
     -shared|-noshared: do/don't back up shared storage (default -noshared)
     -all: back up all installed applications
     -system|-nosystem: include system apps in -all (default -system)
     -keyvalue|-nokeyvalue: include apps that perform key/value backups.
         (default -nokeyvalue)
 restore FILE             restore device contents from FILE


```

### bu 
```
bu
Killed


```

## bugreport


### bugreport
```
# bugreport
=============================================================================
WARNING: flat bugreports are deprecated, use adb bugreport <zip_file> instead
=============================================================================

```

### None

## bugreportz


### bugreportz -h
```
 # bugreportz --help
bugreportz: invalid option -- -
usage: bugreportz [-h | -v]
  -h: to display this help message
  -p: display progress
  -v: to display the version
  or no arguments to generate a zipped bugreport


```

### bugreportz -v
```
# bugreportz -v
1.1

```


## bunzip2 -> bzip2

### bunzip2 -h
```
# bunzip2 -h
bzip2, a block-sorting file compressor.  Version 1.0.6, 6-Sept-2010.

   usage: bunzip2 [flags and input files in any order]

   -h --help           print this message
   -d --decompress     force decompression
   -z --compress       force compression
   -k --keep           keep (don't delete) input files
   -f --force          overwrite existing output files
   -t --test           test compressed file integrity
   -c --stdout         output to standard out
   -q --quiet          suppress noncritical error messages
   -v --verbose        be verbose (a 2nd -v gives more)
   -L --license        display software version & license
   -V --version        display software version & license
   -s --small          use less memory (at most 2500k)
   -1 .. -9            set block size to 100k .. 900k
   --fast              alias for -1
   --best              alias for -9

   If invoked as `bzip2', default action is to compress.
              as `bunzip2',  default action is to decompress.
              as `bzcat', default action is to decompress to stdout.

   If no file names are given, bzip2 compresses or decompresses
   from standard input to standard output.  You can combine
   short flags, so `-v -4' means the same as -v4 or -4v, &c.

```

## busybox
```
busybox 是一个工具包集合,内包含很多实用工具

```

### busybox  --help
```
# busybox --help
BusyBox v1.19.4 (2012-04-01 20:57:05 CST) multi-call binary.
Copyright (C) 1998-2011 Erik Andersen, Rob Landley, Denys Vlasenko
and others. Licensed under GPLv2.
See source distribution for full notice.

Usage: busybox [function] [arguments]...
   or: busybox --list[-full]
   or: function [arguments]...

        BusyBox is a multi-call binary that combines many common Unix
        utilities into a single executable.  Most people will create a
        link to busybox for each function they wish to use and BusyBox
        will act like whatever it was invoked as.

Currently defined functions:
        [, [[, acpid, add-shell, addgroup, adduser, adjtimex, arp, arping, ash, awk, base64, basename, beep, blkid, blockdev,
        bootchartd, brctl, bunzip2, bzcat, bzip2, cal, cat, catv, chat, chattr, chgrp, chmod, chown, chpasswd, chpst, chroot, chrt,
        chvt, cksum, clear, cmp, comm, cp, cpio, crond, crontab, cryptpw, cttyhack, cut, date, dc, dd, deallocvt, delgroup, deluser,
        depmod, devmem, df, dhcprelay, diff, dirname, dmesg, dnsd, dnsdomainname, dos2unix, du, dumpkmap, dumpleases, echo, ed, egrep,
        eject, env, envdir, envuidgid, ether-wake, expand, expr, fakeidentd, false, fbset, fbsplash, fdflush, fdformat, fdisk,
        fgconsole, fgrep, find, findfs, flock, fold, free, freeramdisk, fsck, fsck.minix, fsync, ftpd, ftpget, ftpput, fuser, getopt,
        getty, grep, groups, gunzip, gzip, halt, hd, hdparm, head, hexdump, hostid, hostname, httpd, hush, hwclock, id, ifconfig,
        ifdown, ifenslave, ifplugd, ifup, inetd, init, insmod, install, ionice, iostat, ip, ipaddr, ipcalc, ipcrm, ipcs, iplink,
        iproute, iprule, iptunnel, kbd_mode, kill, killall, killall5, klogd, last, less, linux32, linux64, linuxrc, ln, loadfont,
        loadkmap, logger, login, logname, logread, losetup, lpd, lpq, lpr, ls, lsattr, lsmod, lspci, lsusb, lzcat, lzma, lzop, lzopcat,
        makedevs, makemime, man, md5sum, mdev, mesg, microcom, mkdir, mkdosfs, mke2fs, mkfifo, mkfs.ext2, mkfs.minix, mkfs.vfat, mknod,
        mkpasswd, mkswap, mktemp, modinfo, modprobe, more, mount, mountpoint, mpstat, mt, mv, nameif, nbd-client, nc, netstat, nice,
        nmeter, nohup, nslookup, ntpd, od, openvt, passwd, patch, pgrep, pidof, ping, ping6, pipe_progress, pivot_root, pkill, pmap,
        popmaildir, poweroff, powertop, printenv, printf, ps, pscan, pstree, pwd, pwdx, raidautorun, rdate, rdev, readahead, readlink,
        readprofile, realpath, reboot, reformime, remove-shell, renice, reset, resize, rev, rm, rmdir, rmmod, route, rpm, rpm2cpio,
        rtcwake, run-parts, runlevel, runsv, runsvdir, rx, script, scriptreplay, sed, sendmail, seq, setarch, setconsole, setfont,
        setkeycodes, setlogcons, setserial, setsid, setuidgid, sh, sha1sum, sha256sum, sha512sum, showkey, slattach, sleep, smemcap,
        softlimit, sort, split, start-stop-daemon, stat, strings, stty, su, sulogin, sum, sv, svlogd, swapoff, swapon, switch_root,
        sync, sysctl, syslogd, tac, tail, tar, tcpsvd, tee, telnet, telnetd, test, tftp, tftpd, time, timeout, top, touch, tr,
        traceroute, traceroute6, true, tty, ttysize, tunctl, udhcpc, udhcpd, udpsvd, umount, uname, unexpand, uniq, unix2dos, unlzma,
        unlzop, unxz, unzip, uptime, users, usleep, uudecode, uuencode, vconfig, vi, vlock, volname, wall, watch, watchdog, wc, wget,
        which, who, whoami, whois, xargs, xz, xzcat, yes, zcat, zcip


```



### busybox --list
```
parker:/ # busybox --list
[
[[
acpid
add-shell
addgroup
adduser
adjtimex
arp
arping
ash
awk
base64
basename
beep
blkid
blockdev
bootchartd
brctl
bunzip2
bzcat
bzip2
cal
cat
catv
chat
chattr
chgrp
chmod
chown
chpasswd
chpst
chroot
chrt
chvt
cksum
clear
cmp
comm
cp
cpio
crond
crontab
cryptpw
cttyhack
cut
date
dc
dd
deallocvt
delgroup
deluser
depmod
devmem
df
dhcprelay
diff
dirname
dmesg
dnsd
dnsdomainname
dos2unix
du
dumpkmap
dumpleases
echo
ed
egrep
eject
env
envdir
envuidgid
ether-wake
expand
expr
fakeidentd
false
fbset
fbsplash
fdflush
fdformat
fdisk
fgconsole
fgrep
find
findfs
flock
fold
free
freeramdisk
fsck
fsck.minix
fsync
ftpd
ftpget
ftpput
fuser
getopt
getty
grep
groups
gunzip
gzip
halt
hd
hdparm
head
hexdump
hostid
hostname
httpd
hush
hwclock
id
ifconfig
ifdown
ifenslave
ifplugd
ifup
inetd
init
insmod
install
ionice
iostat
ip
ipaddr
ipcalc
ipcrm
ipcs
iplink
iproute
iprule
iptunnel
kbd_mode
kill
killall
killall5
klogd
last
less
linux32
linux64
linuxrc
ln
loadfont
loadkmap
logger
login
logname
logread
losetup
lpd
lpq
lpr
ls
lsattr
lsmod
lspci
lsusb
lzcat
lzma
lzop
lzopcat
makedevs
makemime
man
md5sum
mdev
mesg
microcom
mkdir
mkdosfs
mke2fs
mkfifo
mkfs.ext2
mkfs.minix
mkfs.vfat
mknod
mkpasswd
mkswap
mktemp
modinfo
modprobe
more
mount
mountpoint
mpstat
mt
mv
nameif
nbd-client
nc
netstat
nice
nmeter
nohup
nslookup
ntpd
od
openvt
passwd
patch
pgrep
pidof
ping
ping6
pipe_progress
pivot_root
pkill
pmap
popmaildir
poweroff
powertop
printenv
printf
ps
pscan
pstree
pwd
pwdx
raidautorun
rdate
rdev
readahead
readlink
readprofile
realpath
reboot
reformime
remove-shell
renice
reset
resize
rev
rm
rmdir
rmmod
route
rpm
rpm2cpio
rtcwake
run-parts
runlevel
runsv
runsvdir
rx
script
scriptreplay
sed
sendmail
seq
setarch
setconsole
setfont
setkeycodes
setlogcons
setserial
setsid
setuidgid
sh
sha1sum
sha256sum
sha512sum
showkey
slattach
sleep
smemcap
softlimit
sort
split
start-stop-daemon
stat
strings
stty
su
sulogin
sum
sv
svlogd
swapoff
swapon
switch_root
sync
sysctl
syslogd
tac
tail
tar
tcpsvd
tee
telnet
telnetd
test
tftp
tftpd
time
timeout
top
touch
tr
traceroute
traceroute6
true
tty
ttysize
tunctl
udhcpc
udhcpd
udpsvd
umount
uname
unexpand
uniq
unix2dos
unlzma
unlzop
unxz
unzip
uptime
users
usleep
uudecode
uuencode
vconfig
vi
vlock
volname
wall
watch
watchdog
wc
wget
which
who
whoami
whois
xargs
xz
xzcat
yes
zcat
zcip

```

## bzcat -> bzip2

### bzcat -h
```
# bzcat -h
bzip2, a block-sorting file compressor.  Version 1.0.6, 6-Sept-2010.

   usage: bzcat [flags and input files in any order]

   -h --help           print this message
   -d --decompress     force decompression
   -z --compress       force compression
   -k --keep           keep (don't delete) input files
   -f --force          overwrite existing output files
   -t --test           test compressed file integrity
   -c --stdout         output to standard out
   -q --quiet          suppress noncritical error messages
   -v --verbose        be verbose (a 2nd -v gives more)
   -L --license        display software version & license
   -V --version        display software version & license
   -s --small          use less memory (at most 2500k)
   -1 .. -9            set block size to 100k .. 900k
   --fast              alias for -1
   --best              alias for -9

   If invoked as `bzip2', default action is to compress.
              as `bunzip2',  default action is to decompress.
              as `bzcat', default action is to decompress to stdout.

   If no file names are given, bzip2 compresses or decompresses
   from standard input to standard output.  You can combine
   short flags, so `-v -4' means the same as -v4 or -4v, &c.


```



## bzip2
### bzip2 -h
```
# bzip2 -h
bzip2, a block-sorting file compressor.  Version 1.0.6, 6-Sept-2010.

   usage: bzip2 [flags and input files in any order]

   -h --help           print this message
   -d --decompress     force decompression
   -z --compress       force compression
   -k --keep           keep (don't delete) input files
   -f --force          overwrite existing output files
   -t --test           test compressed file integrity
   -c --stdout         output to standard out
   -q --quiet          suppress noncritical error messages
   -v --verbose        be verbose (a 2nd -v gives more)
   -L --license        display software version & license
   -V --version        display software version & license
   -s --small          use less memory (at most 2500k)
   -1 .. -9            set block size to 100k .. 900k
   --fast              alias for -1
   --best              alias for -9

   If invoked as `bzip2', default action is to compress.
              as `bunzip2',  default action is to decompress.
              as `bzcat', default action is to decompress to stdout.

   If no file names are given, bzip2 compresses or decompresses
   from standard input to standard output.  You can combine
   short flags, so `-v -4' means the same as -v4 or -4v, &c.

```

# C

## cachemon

### cachemon -h 
```
# cachemon -h
Usage: cachemon [OPTION]...
  --ofs        Include file offset breakdowns in output
  --uid        Include UID breakdowns in output
  --show-prog  Show progress dots every 500 messages

```

### cachemon --show-prog
```
....................................  显示500个点?

```


## cachenow

### cachenow
```
显示缓存文件?
.......
4 KB  /data/media/0/Android/data/com.UCMobile/cache/uil-images/1413581179
4 KB  /data/data/com.android.vending/databases/xternal_referrer_status.db-shm
4 KB  /data/data/com.android.vending/databases/library.db-shm
4 KB  /data/data/com.android.vending/databases/notification_cache-shm
4 KB  /data/media/0/Android/data/com.ss.android.article.news/cache/prefs/local_prefs.json
4 KB  /data/data/com.android.vending/databases/verify_apps.db
4 KB  /data/media/0/wandoujia/.config/.udid
4 KB  /data/media/0/.system/gifnoc3.ini
4 KB  /data/media/0/.UTSystemConfig/Global/Alvin2.xml
4 KB  /data/media/0/.DataStorage/ContextData.xml
4 KB  /data/media/0/.com.taobao.dp/dd7893586a493dc3
4 KB  /data/.nosetupcheck
4 KB  /data/.layout_version
4 KB  /data/cachemon.txt
4 KB  /system/lib/libpackagelistparser.so


```
###  None
## cal -> toybox

### cal
```
# cal
     June 2019
Su Mo Tu We Th Fr Sa
                   1
 2  3  4  5  6  7  8
 9 10 11 12 13 14 15
16 17 18 19 20 21 22
23 24 25 26 27 28 29
30

```

### cal --help
```
# cal --help
usage: cal [[month] year]

Print a calendar.

With one argument, prints all months of the specified year.
With two arguments, prints calendar for month and year.


```

### cal 2018
```
# cal 2018
    January 2018         February 2018           March 2018
Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa
    1  2  3  4  5  6               1  2  3               1  2  3
 7  8  9 10 11 12 13   4  5  6  7  8  9 10   4  5  6  7  8  9 10
14 15 16 17 18 19 20  11 12 13 14 15 16 17  11 12 13 14 15 16 17
21 22 23 24 25 26 27  18 19 20 21 22 23 24  18 19 20 21 22 23 24
28 29 30 31           25 26 27 28           25 26 27 28 29 30 31

     April 2018             May 2018             June 2018
Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa
 1  2  3  4  5  6  7         1  2  3  4  5                  1  2
 8  9 10 11 12 13 14   6  7  8  9 10 11 12   3  4  5  6  7  8  9
15 16 17 18 19 20 21  13 14 15 16 17 18 19  10 11 12 13 14 15 16
22 23 24 25 26 27 28  20 21 22 23 24 25 26  17 18 19 20 21 22 23
29 30                 27 28 29 30 31        24 25 26 27 28 29 30

     July 2018            August 2018          September 2018
Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa
 1  2  3  4  5  6  7            1  2  3  4                     1
 8  9 10 11 12 13 14   5  6  7  8  9 10 11   2  3  4  5  6  7  8
15 16 17 18 19 20 21  12 13 14 15 16 17 18   9 10 11 12 13 14 15
22 23 24 25 26 27 28  19 20 21 22 23 24 25  16 17 18 19 20 21 22
29 30 31              26 27 28 29 30 31     23 24 25 26 27 28 29
                                            30
    October 2018         November 2018         December 2018
Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa
    1  2  3  4  5  6               1  2  3                     1
 7  8  9 10 11 12 13   4  5  6  7  8  9 10   2  3  4  5  6  7  8
14 15 16 17 18 19 20  11 12 13 14 15 16 17   9 10 11 12 13 14 15
21 22 23 24 25 26 27  18 19 20 21 22 23 24  16 17 18 19 20 21 22
28 29 30 31           25 26 27 28 29 30     23 24 25 26 27 28 29
                                            30 31



```

### cal 8 2008 
```
2008年8月的月历
# cal 8 2008
    August 2008
Su Mo Tu We Th Fr Sa
                1  2
 3  4  5  6  7  8  9
10 11 12 13 14 15 16
17 18 19 20 21 22 23
24 25 26 27 28 29 30
31

```


## cameraserver
### none 

## cat -> toybox
### cat --help 
```
# cat --help
usage: cat [-etuv] [file...]
// 把文件打印到输出文件中
Copy (concatenate) files to stdout.  If no files listed, copy from stdin.
Filename "-" is a synonym for stdin.

-e      Mark each newline with $
-t      Show tabs as ^I
-u      Copy one byte at a time (slow)
-v      Display nonprinting characters as escape sequences with M-x for
        high ascii characters (>127), and ^x for other nonprinting chars


```

## chcon -> toybox


### chcon --help
```
# chcon --help
usage: chcon [-hRv] CONTEXT FILE...

Change the SELinux security context of listed file[s].

-h change symlinks instead of what they point to
-R recurse into subdirectories
-v verbose output


```


## chgrp -> toybox

### chgrp --help
```
# chgrp --help
usage: chgrp/chown [-RHLP] [-fvh] group file...
更改文件的所属群组
Change group of one or more files.

-f      suppress most error messages.
-h      change symlinks instead of what they point to
-R      recurse into subdirectories (implies -h)
-H      with -R change target of symlink, follow command line symlinks
-L      with -R change target of symlink, follow all symlinks
-P      with -R change symlink, do not follow symlinks (default)
-v      verbose output


```
## chmod -> toybox


### chmod --help
```
# chmod --help
usage: chmod [-L] [-R] MODE FILE...

Change mode of listed file[s] (recursively with -R).

MODE can be (comma-separated) stanzas: [ugoa][+-=][rwxstXugo]

Stanzas are applied in order: For each category (u = user,
g = group, o = other, a = all three, if none specified default is a),
set (+), clear (-), or copy (=), r = read, w = write, x = execute.
s = u+s = suid, g+s = sgid, o+s = sticky. (+t is an alias for o+s).
suid/sgid: execute as the user/group who owns the file.
sticky: can't delete files you don't own out of this directory
X = x for directories or if any category already has x set.

Or MODE can be an octal value up to 7777        ug uuugggooo    top +
bit 1 = o+x, bit 1<<8 = u+w, 1<<11 = g+1        sstrwxrwxrwx    bottom

Examples:
chmod u+w file - allow owner of "file" to write to it.
chmod 744 file - user can read/write/execute, everyone else read only

Does not follow symlinks by default (can be overridden with -L).



```

### chmod 777 ./file
```
// 对当前文件 更改为权限 777  任何user 可读可写可执行

```

## chown -> toybox

```
# chown --help
usage: chgrp/chown [-RHLP] [-fvh] group file...
更改文件所属 user
Change group of one or more files.

-f      suppress most error messages.
-h      change symlinks instead of what they point to
-R      recurse into subdirectories (implies -h)
-H      with -R change target of symlink, follow command line symlinks
-L      with -R change target of symlink, follow all symlinks
-P      with -R change symlink, do not follow symlinks (default)
-v      verbose output



```


## chroot -> toybox

###  chroot --help
```
# chroot --help
usage: chroot NEWPATH [commandline...]
运行命令在新的 root 目录下  如果没有输入命令  运行  /bin/sh  Shell
Run command within a new root directory. If no command, run /bin/sh.


```


## chrt -> toybox

### chrt --help
```
# chrt --help
usage: chrt [-Rmofrbi] {-p PID [PRIORITY] | [PRIORITY COMMAND...]}
设置进程的优先级 以及 策略？
Get/set a process' real-time scheduling policy and priority.

-p      Set/query given pid (instead of running COMMAND)
-R      Set SCHED_RESET_ON_FORK
-m      Show min/max priorities available

Set policy (default -r):

  -o  SCHED_OTHER    -f  SCHED_FIFO    -r  SCHED_RR
  -b  SCHED_BATCH    -i  SCHED_IDLE


```

 
## cksum -> toybox

### cksum  --help
```
# cksum --help
usage: cksum [-IPLN] [file...]
CRC的全称是循环冗余校验
For each file, output crc32 checksum value, length and name of file.
If no files listed, copy from stdin.  Filename "-" is a synonym for stdin.

-H      Hexadecimal checksum (defaults to decimal)
-L      Little endian (defaults to big endian)
-P      Pre-inversion
-I      Skip post-inversion
-N      Do not include length in CRC calculation (or output)


```

### None


## clatd

###  clatd -h
```
# clatd -h
android-clat arguments:
-i [uplink interface]
-p [plat prefix]
-n [NetId]
-m [socket mark]

```

### None



## clear -> toybox

### clear --help
```
clear --help   清屏
Clear the screen.

```

### None

## cmd

### cmd -l
```
# cmd -l     // 查看当前机器上运行的服务

parker:/sdcard/Pictures # cmd -l
Currently running services:
  DockObserver
  SurfaceFlinger
  accessibility
  account
  activity
  alarm
  android.os.UpdateEngineService
  android.security.keystore
  android.service.gatekeeper.IGateKeeperService
  appops
  appwidget
  audio
  autofill
  backup
  battery
  batteryproperties
  batterystats
  binder_calls_stats
  bluetooth_manager
  carrier_config
  clipboard
  cneservice
  com.qualcomm.location.izat.IzatService
  commontime_management
  companiondevice
  connectivity
  connmetrics
  consumer_ir
  content
  contexthub
  country_detector
  cpuinfo
  crossprofileapps
  dbinfo
  device_identifiers
  device_policy
  deviceidle
  devicestoragemonitor
  diskstats
  display
  dreams
  drm.drmManager
  dropbox
  ethernet
  extphone
  fingerprint
  gfxinfo
  gpu
  graphicsstats
  hardware_properties
  imms
  incident
  input
  input_method
  installd
  iphonesubinfo
  ipsec
  isms
  isub
  jobscheduler
  launcherapps
  location
  lock_settings
  media.camera.proxy
  media.drm
  media.extractor
  media.extractor.update
  media.metrics
  media.player
  media.resource_manager
  media_projection
  media_resource_monitor
  media_router
  media_session
  meminfo
  midi
  moto_pers_data_block
  motsettings
  mount
  netd
  netd_listener
  netpolicy
  netstats
  network_management
  network_score
  network_time_update_service
  network_watchlist
  nfc
  notification
  oem_lock
  otadexopt
  overlay
  package
  package_native
  panel
  permission
  persistent_data_block
  phone
  pinner
  power
  print
  processinfo
  procstats
  qti.ims.ext
  recovery
  restrictions
  scheduling_policy
  search
  sec_key_att_app_id_provider
  secure_element
  sensorservice
  serial
  servicediscovery
  settings
  shortcut
  simphonebook
  sip
  slice
  soundtrigger
  stats
  statscompanion
  statusbar
  storaged
  storaged_pri
  storagestats
  system_update
  telecom
  telephony.registry
  textclassification
  textservices
  thermalservice
  timezone
  trust
  uimode
  updatelock
  usagestats
  usb
  user
  vendor.perfservice
  vibrator
  voiceinteraction
  vold
  vrmanager
  wallpaper
  webviewupdate
  wifi
  wificond
  wifip2p
  wifiscanner
  window

```

### cmd accessibility
#### cmd accessibility -h
```
Accessibility service (accessibility) commands:
  help
    Print this help text.
  set-bind-instant-service-allowed [--user <USER_ID>] true|false 
    Set whether binding to services provided by instant apps is allowed.
  get-bind-instant-service-allowed [--user <USER_ID>]
    Get whether binding to services provided by instant apps is allowed.


```

### cmd account
#### cmd account -h
```
Account manager service commands:
  help
    Print this help text.
  set-bind-instant-service-allowed [--user <USER_ID>] true|false 
    Set whether binding to services provided by instant apps is allowed.
  get-bind-instant-service-allowed [--user <USER_ID>]
    Get whether binding to services provided by instant apps is allowed.


```

### cmd activity
#### cmd activity -h
```
Activity manager (activity) commands:
  help
      Print this help text.
  start-activity [-D] [-N] [-W] [-P <FILE>] [--start-profiler <FILE>]
          [--sampling INTERVAL] [--streaming] [-R COUNT] [-S]
          [--track-allocation] [--user <USER_ID> | current] <INTENT>
      Start an Activity.  Options are:
      -D: enable debugging
      -N: enable native debugging
      -W: wait for launch to complete
      --start-profiler <FILE>: start profiler and send results to <FILE>
      --sampling INTERVAL: use sample profiling with INTERVAL microseconds
          between samples (use with --start-profiler)
      --streaming: stream the profiling output to the specified file
          (use with --start-profiler)
      -P <FILE>: like above, but profiling stops when app goes idle
      --attach-agent <agent>: attach the given agent before binding
      --attach-agent-bind <agent>: attach the given agent during binding
      -R: repeat the activity launch <COUNT> times.  Prior to each repeat,
          the top activity will be finished.
      -S: force stop the target app before starting the activity
      --track-allocation: enable tracking of object allocations
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
      --windowingMode <WINDOWING_MODE>: The windowing mode to launch the activity into.
      --activityType <ACTIVITY_TYPE>: The activity type to launch the activity as.
  start-service [--user <USER_ID> | current] <INTENT>
      Start a Service.  Options are:
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
  start-foreground-service [--user <USER_ID> | current] <INTENT>
      Start a foreground Service.  Options are:
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
  stop-service [--user <USER_ID> | current] <INTENT>
      Stop a Service.  Options are:
      --user <USER_ID> | current: Specify which user to run as; if not
          specified then run as the current user.
  broadcast [--user <USER_ID> | all | current] <INTENT>
      Send a broadcast Intent.  Options are:
      --user <USER_ID> | all | current: Specify which user to send to; if not
          specified then send to all users.
      --receiver-permission <PERMISSION>: Require receiver to hold permission.
      --sticky-broadcast : Send broadcast as a sticky broadcast.
  instrument [-r] [-e <NAME> <VALUE>] [-p <FILE>] [-w]
          [--user <USER_ID> | current] [--no-hidden-api-checks]
          [--no-window-animation] [--abi <ABI>] <COMPONENT>
      Start an Instrumentation.  Typically this target <COMPONENT> is in the
      form <TEST_PACKAGE>/<RUNNER_CLASS> or only <TEST_PACKAGE> if there
      is only one instrumentation.  Options are:
      -r: print raw results (otherwise decode REPORT_KEY_STREAMRESULT).  Use with
          [-e perf true] to generate raw output for performance measurements.
      -e <NAME> <VALUE>: set argument <NAME> to <VALUE>.  For test runners a
          common form is [-e <testrunner_flag> <value>[,<value>...]].
      -p <FILE>: write profiling data to <FILE>
      -m: Write output as protobuf to stdout (machine readable)
      -f <Optional PATH/TO/FILE>: Write output as protobuf to a file (machine
          readable). If path is not specified, default directory and file name will
          be used: /sdcard/instrument-logs/log-yyyyMMdd-hhmmss-SSS.instrumentation_data_proto
      -w: wait for instrumentation to finish before returning.  Required for
          test runners.
      --user <USER_ID> | current: Specify user instrumentation runs in;
          current user if not specified.
      --no-hidden-api-checks: disable restrictions on use of hidden API.
      --no-window-animation: turn off window animations while running.
      --abi <ABI>: Launch the instrumented process with the selected ABI.
          This assumes that the process supports the selected ABI.
  trace-ipc [start|stop] [--dump-file <FILE>]
      Trace IPC transactions.
      start: start tracing IPC transactions.
      stop: stop tracing IPC transactions and dump the results to file.
      --dump-file <FILE>: Specify the file the trace should be dumped to.
  profile [start|stop] [--user <USER_ID> current] [--sampling INTERVAL]
          [--streaming] <PROCESS> <FILE>
      Start and stop profiler on a process.  The given <PROCESS> argument
        may be either a process name or pid.  Options are:
      --user <USER_ID> | current: When supplying a process name,
          specify user of process to profile; uses current user if not specified.
      --sampling INTERVAL: use sample profiling with INTERVAL microseconds
          between samples
      --streaming: stream the profiling output to the specified file
  dumpheap [--user <USER_ID> current] [-n] [-g] <PROCESS> <FILE>
      Dump the heap of a process.  The given <PROCESS> argument may
        be either a process name or pid.  Options are:
      -n: dump native heap instead of managed heap
      -g: force GC before dumping the heap
      --user <USER_ID> | current: When supplying a process name,
          specify user of process to dump; uses current user if not specified.
  set-debug-app [-w] [--persistent] <PACKAGE>
      Set application <PACKAGE> to debug.  Options are:
      -w: wait for debugger when application starts
      --persistent: retain this value
  clear-debug-app
      Clear the previously set-debug-app.
  set-watch-heap <PROCESS> <MEM-LIMIT>
      Start monitoring pss size of <PROCESS>, if it is at or
      above <HEAP-LIMIT> then a heap dump is collected for the user to report.
  clear-watch-heap
      Clear the previously set-watch-heap.
  bug-report [--progress | --telephony]
      Request bug report generation; will launch a notification
        when done to select where it should be delivered. Options are:
     --progress: will launch a notification right away to show its progress.
     --telephony: will dump only telephony sections.
  force-stop [--user <USER_ID> | all | current] <PACKAGE>
      Completely stop the given application package.
  crash [--user <USER_ID>] <PACKAGE|PID>
      Induce a VM crash in the specified package or process
  kill [--user <USER_ID> | all | current] <PACKAGE>
      Kill all background processes associated with the given application.
  kill-all
      Kill all processes that are safe to kill (cached, etc).
  make-uid-idle [--user <USER_ID> | all | current] <PACKAGE>
      If the given application's uid is in the background and waiting to
      become idle (not allowing background services), do that now.
  monitor [--gdb <port>]
      Start monitoring for crashes or ANRs.
      --gdb: start gdbserv on the given port at crash/ANR
  watch-uids [--oom <uid>]
      Start watching for and reporting uid state changes.
      --oom: specify a uid for which to report detailed change messages.
  hang [--allow-restart]
      Hang the system.
      --allow-restart: allow watchdog to perform normal system restart
  restart
      Restart the user-space system.
  idle-maintenance
      Perform idle maintenance now.
  screen-compat [on|off] <PACKAGE>
      Control screen compatibility mode of <PACKAGE>.
  package-importance <PACKAGE>
      Print current importance of <PACKAGE>.
  to-uri [INTENT]
      Print the given Intent specification as a URI.
  to-intent-uri [INTENT]
      Print the given Intent specification as an intent: URI.
  to-app-uri [INTENT]
      Print the given Intent specification as an android-app: URI.
  switch-user <USER_ID>
      Switch to put USER_ID in the foreground, starting
      execution of that user if it is currently stopped.
  get-current-user
      Returns id of the current foreground user.
  start-user <USER_ID>
      Start USER_ID in background if it is currently stopped;
      use switch-user if you want to start the user in foreground
  unlock-user <USER_ID> [TOKEN_HEX]
      Attempt to unlock the given user using the given authorization token.
  stop-user [-w] [-f] <USER_ID>
      Stop execution of USER_ID, not allowing it to run any
      code until a later explicit start or switch to it.
      -w: wait for stop-user to complete.
      -f: force stop even if there are related users that cannot be stopped.
  is-user-stopped <USER_ID>
      Returns whether <USER_ID> has been stopped or not.
  get-started-user-state <USER_ID>
      Gets the current state of the given started user.
  track-associations
      Enable association tracking.
  untrack-associations
      Disable and clear association tracking.
  get-uid-state <UID>
      Gets the process state of an app given its <UID>.
  attach-agent <PROCESS> <FILE>
    Attach an agent to the specified <PROCESS>, which may be either a process name or a PID.
  get-config [--days N] [--device] [--proto]
      Retrieve the configuration and any recent configurations of the device.
      --days: also return last N days of configurations that have been seen.
      --device: also output global device configuration info.
      --proto: return result as a proto; does not include --days info.
  supports-multiwindow
      Returns true if the device supports multiwindow.
  supports-split-screen-multi-window
      Returns true if the device supports split screen multiwindow.
  suppress-resize-config-changes <true|false>
      Suppresses configuration changes due to user resizing an activity/task.
  set-inactive [--user <USER_ID>] <PACKAGE> true|false
      Sets the inactive state of an app.
  get-inactive [--user <USER_ID>] <PACKAGE>
      Returns the inactive state of an app.
  set-standby-bucket [--user <USER_ID>] <PACKAGE> active|working_set|frequent|rare
      Puts an app in the standby bucket.
  get-standby-bucket [--user <USER_ID>] <PACKAGE>
      Returns the standby bucket of an app.
  send-trim-memory [--user <USER_ID>] <PROCESS>
          [HIDDEN|RUNNING_MODERATE|BACKGROUND|RUNNING_LOW|MODERATE|RUNNING_CRITICAL|COMPLETE]
      Send a memory trim event to a <PROCESS>.  May also supply a raw trim int level.
  display [COMMAND] [...]: sub-commands for operating on displays.
       move-stack <STACK_ID> <DISPLAY_ID>
           Move <STACK_ID> from its current display to <DISPLAY_ID>.
  stack [COMMAND] [...]: sub-commands for operating on activity stacks.
       start <DISPLAY_ID> <INTENT>
           Start a new activity on <DISPLAY_ID> using <INTENT>
       move-task <TASK_ID> <STACK_ID> [true|false]
           Move <TASK_ID> from its current stack to the top (true) or
           bottom (false) of <STACK_ID>.
       resize <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Change <STACK_ID> size and position to <LEFT,TOP,RIGHT,BOTTOM>.
       resize-animated <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Same as resize, but allow animation.
       resize-docked-stack <LEFT,TOP,RIGHT,BOTTOM> [<TASK_LEFT,TASK_TOP,TASK_RIGHT,TASK_BOTTOM>]
           Change docked stack to <LEFT,TOP,RIGHT,BOTTOM>
           and supplying temporary different task bounds indicated by
           <TASK_LEFT,TOP,RIGHT,BOTTOM>
       move-top-activity-to-pinned-stack: <STACK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Moves the top activity from
           <STACK_ID> to the pinned stack using <LEFT,TOP,RIGHT,BOTTOM> for the
           bounds of the pinned stack.
       positiontask <TASK_ID> <STACK_ID> <POSITION>
           Place <TASK_ID> in <STACK_ID> at <POSITION>
       list
           List all of the activity stacks and their sizes.
       info <WINDOWING_MODE> <ACTIVITY_TYPE>
           Display the information about activity stack in <WINDOWING_MODE> and <ACTIVITY_TYPE>.
       remove <STACK_ID>
           Remove stack <STACK_ID>.
  task [COMMAND] [...]: sub-commands for operating on activity tasks.
       lock <TASK_ID>
           Bring <TASK_ID> to the front and don't allow other tasks to run.
       lock stop
           End the current task lock.
       resizeable <TASK_ID> [0|1|2|3]
           Change resizeable mode of <TASK_ID> to one of the following:
           0: unresizeable
           1: crop_windows
           2: resizeable
           3: resizeable_and_pipable
       resize <TASK_ID> <LEFT,TOP,RIGHT,BOTTOM>
           Makes sure <TASK_ID> is in a stack with the specified bounds.
           Forces the task to be resizeable and creates a stack if no existing stack
           has the specified bounds.
  update-appinfo <USER_ID> <PACKAGE_NAME> [<PACKAGE_NAME>...]
      Update the ApplicationInfo objects of the listed packages for <USER_ID>
      without restarting any processes.
  write
      Write all pending state to storage.

<INTENT> specifications include these flags and arguments:
    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
    [-c <CATEGORY> [-c <CATEGORY>] ...]
    [-n <COMPONENT_NAME>]
    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
    [--esn <EXTRA_KEY> ...]
    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as Integer[])
    [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as List<Integer>)
    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as Long[])
    [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as List<Long>)
    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as Float[])
    [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as List<Float>)
    [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as String[]; to embed a comma into a string,
         escape it using "\,")
    [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as List<String>; to embed a comma into a string,
         escape it using "\,")
    [-f <FLAG>]
    [--grant-read-uri-permission] [--grant-write-uri-permission]
    [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]
    [--debug-log-resolution] [--exclude-stopped-packages]
    [--include-stopped-packages]
    [--activity-brought-to-front] [--activity-clear-top]
    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
    [--activity-launched-from-history] [--activity-multiple-task]
    [--activity-no-animation] [--activity-no-history]
    [--activity-no-user-action] [--activity-previous-is-top]
    [--activity-reorder-to-front] [--activity-reset-task-if-needed]
    [--activity-single-top] [--activity-clear-task]
    [--activity-task-on-home] [--activity-match-external]
    [--receiver-registered-only] [--receiver-replace-pending]
    [--receiver-foreground] [--receiver-no-abort]
    [--receiver-include-background]
    [--selector]
    [<URI> | <PACKAGE> | <COMPONENT>]


```


### cmd alarm
#### cmd alarm -h
```
Alarm manager service (alarm) commands:
  help
    Print this help text.
  set-time TIME
    Set the system clock time to TIME where TIME is milliseconds
    since the Epoch.
  set-timezone TZ
    Set the system timezone to TZ where TZ is an Olson id.


```


### cmd appops
#### cmd appops -h
```
AppOps service (appops) commands:
  help
    Print this help text.
  start [--user <USER_ID>] <PACKAGE | UID> <OP> 
    Starts a given operation for a particular application.
  stop [--user <USER_ID>] <PACKAGE | UID> <OP> 
    Stops a given operation for a particular application.
  set [--user <USER_ID>] <PACKAGE | UID> <OP> <MODE>
    Set the mode for a particular application and operation.
  get [--user <USER_ID>] <PACKAGE | UID> [<OP>]
    Return the mode for a particular application and optional operation.
  query-op [--user <USER_ID>] <OP> [<MODE>]
    Print all packages that currently have the given op in the given mode.
  reset [--user <USER_ID>] [<PACKAGE>]
    Reset the given application or all applications to default modes.
  write-settings
    Immediately write pending changes to storage.
  read-settings
    Read the last written settings, replacing current state in RAM.
  options:
    <PACKAGE> an Android package name.
    <OP>      an AppOps operation.
    <MODE>    one of allow, ignore, deny, or default
    <USER_ID> the user id under which the package is installed. If --user is not
              specified, the current user is assumed.


```

### cmd xxxx
#### cmd xxxx -h
```


```

### cmd autofill
#### cmd autofill -h
```
AutoFill Service (autofill) commands:
  help
    Prints this help text.

  get log_level 
    Gets the Autofill log level (off | debug | verbose).

  get max_partitions
    Gets the maximum number of partitions per session.

  get max_visible_datasets
    Gets the maximum number of visible datasets in the UI.

  get full_screen_mode
    Gets the Fill UI full screen mode

  get fc_score [--algorithm ALGORITHM] value1 value2
    Gets the field classification score for 2 fields.

  get bind-instant-service-allowed
    Gets whether binding to services provided by instant apps is allowed

  set log_level [off | debug | verbose]
    Sets the Autofill log level.

  set max_partitions number
    Sets the maximum number of partitions per session.

  set max_visible_datasets number
    Sets the maximum number of visible datasets in the UI.

  set full_screen_mode [true | false | default]
    Sets the Fill UI full screen mode

  set bind-instant-service-allowed [true | false]
    Sets whether binding to services provided by instant apps is allowed

  list sessions [--user USER_ID]
    Lists all pending sessions.

  destroy sessions [--user USER_ID]
    Destroys all pending sessions.

  reset
    Resets all pending sessions and cached service connections.



```

### cmd battery
#### cmd battery -h
```
Battery service (battery) commands:
  help
    Print this help text.
  set [-f] [ac|usb|wireless|status|level|temp|present|invalid] <value>
    Force a battery property value, freezing battery state.
    -f: force a battery change broadcast be sent, prints new sequence.
  unplug [-f]
    Force battery unplugged, freezing battery state.
    -f: force a battery change broadcast be sent, prints new sequence.
  reset [-f]
    Unfreeze battery state, returning to current hardware values.
    -f: force a battery change broadcast be sent, prints new sequence.


```

### cmd connectivity
#### cmd connectivity -h
```
Connectivity service commands:
  help
    Print this help text.
  airplane-mode [enable|disable]
    Turn airplane mode on or off.
  airplane-mode
    Get airplane mode.


```


### cmd content
#### cmd content -h
```
Content service commands:
  help
    Print this help text.

  reset-today-stats
    Reset 1-day sync stats.



```


### cmd deviceidle
#### cmd deviceidle -h
```
Device idle controller (deviceidle) commands:
  help
    Print this help text.
  step [light|deep]
    Immediately step to next state, without waiting for alarm.
  force-idle [light|deep]
    Force directly into idle mode, regardless of other device state.
  force-inactive
    Force to be inactive, ready to freely step idle states.
  unforce
    Resume normal functioning after force-idle or force-inactive.
  get [light|deep|force|screen|charging|network]
    Retrieve the current given state.
  disable [light|deep|all]
    Completely disable device idle mode.
  enable [light|deep|all]
    Re-enable device idle mode after it had previously been disabled.
  enabled [light|deep|all]
    Print 1 if device idle mode is currently enabled, else 0.
  whitelist
    Print currently whitelisted apps.
  whitelist [package ...]
    Add (prefix with +) or remove (prefix with -) packages.
  sys-whitelist [package ...|reset]
    Prefix the package with '-' to remove it from the system whitelist or '+' to put it back in the system whitelist.
    Note that only packages that were earlier removed from the system whitelist can be added back.
    reset will reset the whitelist to the original state
    Prints the system whitelist if no arguments are specified
  except-idle-whitelist [package ...|reset]
    Prefix the package with '+' to add it to whitelist or '=' to check if it is already whitelisted
    [reset] will reset the whitelist to it's original state
    Note that unlike <whitelist> cmd, changes made using this won't be persisted across boots
  tempwhitelist
    Print packages that are temporarily whitelisted.
  tempwhitelist [-u USER] [-d DURATION] [-r] [package]
    Temporarily place package in whitelist for DURATION milliseconds.
    If no DURATION is specified, 10 seconds is used
    If [-r] option is used, then the package is removed from temp whitelist and any [-d] is ignored
  motion
    Simulate a motion event to bring the device out of deep doze


```
#### cmd deviceidle force-idle deep
```
Now forced in to deep idle mode

```

#### cmd deviceidle force-idle light
```
Now forced in to light idle mode

```

#### cmd deviceidle force-inactive
```
Light state: OVERRIDE, deep state: IDLE


```
#### cmd deviceidle  unforce
```
Light state: ACTIVE, deep state: ACTIVE
Light state: OVERRIDE, deep state: IDLE
Light state: ACTIVE, deep state: ACTIVE

```

#### cmd deviceidle  get light 
```
ACTIVE

```

#### cmd deviceidle  get  deep
```
ACTIVE

```

#### cmd deviceidle  get  force
```
false

```


#### cmd deviceidle  get  screen
```
false

```

#### cmd deviceidle  get  charging
```
true

```

#### cmd deviceidle  get  network
```
true

```

#### cmd deviceidle  disable  light
```
Light idle mode disabled

```
#### cmd deviceidle  disable  deep
```
Deep idle mode disabled

```
#### cmd deviceidle  disable  all
```
null

```

#### cmd deviceidle  enable  light
```
Light idle mode enable

```
#### cmd deviceidle  enable  deep
```
Deep idle mode enable

```
#### cmd deviceidle  enable  all
```
null

```

#### cmd deviceidle  enabled  light
```
1

```
#### cmd deviceidle  enabled  deep
```
1

```
#### cmd deviceidle  enabled  all
```
1

```
  
#### cmd deviceidle whitelist
```
白名单
system-excidle,com.android.providers.downloads,10036
system-excidle,com.google.android.gms,10039
system-excidle,com.google.android.ims,10070
```
#### cmd deviceidle tempwhitelist

#### cmd deviceidle motion
```
Light state: ACTIVE, deep state: ACTIVE

```

### cmd devicestoragemonitor
#### cmd devicestoragemonitor -h
```
Device storage monitor service (devicestoragemonitor) commands:
  help
    Print this help text.
  force-low [-f]
    Force storage to be low, freezing storage state.
    -f: force a storage change broadcast be sent, prints new sequence.
  force-not-low [-f]
    Force storage to not be low, freezing storage state.
    -f: force a storage change broadcast be sent, prints new sequence.
  reset [-f]
    Unfreeze storage state, returning to current real values.
    -f: force a storage change broadcast be sent, prints new sequence.


```

### cmd display
#### cmd display -h
```
Display manager commands:
  help
    Print this help text.

  set-brightness BRIGHTNESS
    Sets the current brightness to BRIGHTNESS (a number between 0 and 1).
  reset-brightness-configuration
    Reset the brightness to its default configuration.

<INTENT> specifications include these flags and arguments:
    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
    [-c <CATEGORY> [-c <CATEGORY>] ...]
    [-n <COMPONENT_NAME>]
    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
    [--esn <EXTRA_KEY> ...]
    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as Integer[])
    [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as List<Integer>)
    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as Long[])
    [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as List<Long>)
    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as Float[])
    [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as List<Float>)
    [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as String[]; to embed a comma into a string,
         escape it using "\,")
    [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as List<String>; to embed a comma into a string,
         escape it using "\,")
    [-f <FLAG>]
    [--grant-read-uri-permission] [--grant-write-uri-permission]
    [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]
    [--debug-log-resolution] [--exclude-stopped-packages]
    [--include-stopped-packages]
    [--activity-brought-to-front] [--activity-clear-top]
    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
    [--activity-launched-from-history] [--activity-multiple-task]
    [--activity-no-animation] [--activity-no-history]
    [--activity-no-user-action] [--activity-previous-is-top]
    [--activity-reorder-to-front] [--activity-reset-task-if-needed]
    [--activity-single-top] [--activity-clear-task]
    [--activity-task-on-home] [--activity-match-external]
    [--receiver-registered-only] [--receiver-replace-pending]
    [--receiver-foreground] [--receiver-no-abort]
    [--receiver-include-background]
    [--selector]
    [<URI> | <PACKAGE> | <COMPONENT>]


```
####  cmd display set-brightness  0
```
亮度最小

```
####  cmd display set-brightness  1
```
亮度最大

```

### cmd input_method
#### cmd input_method -h
```
InputMethodManagerService commands:
  help
    Prints this help text.
  dump [options]
    Synonym of dumpsys.
  ime <command> [options]
    Manipulate IMEs.  Run "ime help" for details.
  set-bind-instant-service-allowed true|false 
    Set whether binding to services provided by instant apps is allowed.


```

### cmd jobscheduler
#### cmd jobscheduler -h
```

Job scheduler (jobscheduler) commands:
  help
    Print this help text.
  run [-f | --force] [-u | --user USER_ID] PACKAGE JOB_ID
    Trigger immediate execution of a specific scheduled job.
    Options:
      -f or --force: run the job even if technical constraints such as
         connectivity are not currently met
      -u or --user: specify which user's job is to be run; the default is
         the primary or system user
  timeout [-u | --user USER_ID] [PACKAGE] [JOB_ID]
    Trigger immediate timeout of currently executing jobs, as if their.
    execution timeout had expired.
    Options:
      -u or --user: specify which user's job is to be run; the default is
         all users
  cancel [-u | --user USER_ID] PACKAGE [JOB_ID]
    Cancel a scheduled job.  If a job ID is not supplied, all jobs scheduled
    by that package will be canceled.  USE WITH CAUTION.
    Options:
      -u or --user: specify which user's job is to be run; the default is
         the primary or system user
  heartbeat [num]
    With no argument, prints the current standby heartbeat.  With a positive
    argument, advances the standby heartbeat by that number.
  monitor-battery [on|off]
    Control monitoring of all battery changes.  Off by default.  Turning
    on makes get-battery-seq useful.
  get-battery-seq
    Return the last battery update sequence number that was received.
  get-battery-charging
    Return whether the battery is currently considered to be charging.
  get-battery-not-low
    Return whether the battery is currently considered to not be low.
  get-storage-seq
    Return the last storage update sequence number that was received.
  get-storage-not-low
    Return whether storage is currently considered to not be low.
  get-job-state [-u | --user USER_ID] PACKAGE JOB_ID
    Return the current state of a job, may be any combination of:
      pending: currently on the pending list, waiting to be active
      active: job is actively running
      user-stopped: job can't run because its user is stopped
      backing-up: job can't run because app is currently backing up its data
      no-component: job can't run because its component is not available
      ready: job is ready to run (all constraints satisfied or bypassed)
      waiting: if nothing else above is printed, job not ready to run
    Options:
      -u or --user: specify which user's job is to be run; the default is
         the primary or system user
  trigger-dock-state [idle|active]
    Trigger wireless charging dock state.  Active by default.




```



### cmd motsettings
#### cmd motsettings -h
```
Motorola Settings provider (motsettings) commands:
  help
      Print this help text.
  get [--user <USER_ID> | current] NAMESPACE KEY
      Retrieve the current value of KEY.
  put [--user <USER_ID> | current] NAMESPACE KEY VALUE [TAG] [default]
      Change the contents of KEY to VALUE.
      TAG to associate with the setting.
      {default} to set as the default, case-insensitive only for global/secure namespace
  delete NAMESPACE KEY
      Delete the entry for KEY.
  reset [--user <USER_ID> | current] NAMESPACE {PACKAGE_NAME | RESET_MODE}
      Reset the global/secure table for a package with mode.
      RESET_MODE is one of {untrusted_defaults, untrusted_clear, trusted_defaults}, case-insensitive
  list NAMESPACE
      Print all defined keys.
      NAMESPACE is one of {system, secure, global}, case-insensitive


```

### cmd netpolicy
#### cmd netpolicy -h
```

Network policy manager (netpolicy) commands:
  help
    Print this help text.

  add restrict-background-whitelist UID
    Adds a UID to the whitelist for restrict background usage.
  add restrict-background-blacklist UID
    Adds a UID to the blacklist for restrict background usage.
  get restrict-background
    Gets the global restrict background usage status.
  list wifi-networks [true|false]
    Lists all saved wifi networks and whether they are metered or not.
    If a boolean argument is passed, filters just the metered (or unmetered)
    networks.
  list restrict-background-whitelist
    Lists UIDs that are whitelisted for restrict background usage.
  list restrict-background-blacklist
    Lists UIDs that are blacklisted for restrict background usage.
  remove restrict-background-whitelist UID
    Removes a UID from the whitelist for restrict background usage.
  remove restrict-background-blacklist UID
    Removes a UID from the blacklist for restrict background usage.
  set metered-network ID [undefined|true|false]
    Toggles whether the given wi-fi network is metered.
  set restrict-background BOOLEAN
    Sets the global restrict background usage status.
  set sub-plan-owner subId [packageName]
    Sets the data plan owner package for subId.



```



### cmd network_watchlist
#### cmd network_watchlist -h
```
Network watchlist manager commands:
  help
    Print this help text.
  set-test-config your_watchlist_config.xml
    Set network watchlist test config file.
  force-generate-report
    Force generate watchlist test report.


```



### cmd notification
#### cmd notification -h
```
help
allow_listener COMPONENT [user_id]
disallow_listener COMPONENT [user_id]
allow_assistant COMPONENT
remove_assistant COMPONENT
allow_dnd PACKAGE
disallow_dnd PACKAGE
suspend_package PACKAGE
unsuspend_package PACKAGE



```


### cmd otadexopt
#### cmd otadexopt -h
```
OTA Dexopt (ota) commands:
  help
    Print this help text.

  prepare
    Prepare an OTA dexopt pass, collecting all packages.
  done
    Replies whether the OTA is complete or not.
  step
    OTA dexopt the next package.
  next
    Get parameters for OTA dexopt of the next package.
  cleanup
    Clean up internal states. Ends an OTA session.


```
#### cmd otadexopt prepare
 

### cmd overlay
#### cmd overlay -h
```
# cmd overlay -h
Overlay manager (overlay) commands:
  help
    Print this help text.
  dump [--verbose] [--user USER_ID] [PACKAGE [PACKAGE [...]]]
    Print debugging information about the overlay manager.
  list [--user USER_ID] [PACKAGE [PACKAGE [...]]]
    Print information about target and overlay packages.
    Overlay packages are printed in priority order. With optional
    parameters PACKAGEs, limit output to the specified packages
    but include more information about each package.
  enable [--user USER_ID] PACKAGE
    Enable overlay package PACKAGE.
  disable [--user USER_ID] PACKAGE
    Disable overlay package PACKAGE.
  enable-exclusive [--user USER_ID] [--category] PACKAGE
    Enable overlay package PACKAGE and disable all other overlays for
    its target package. If the --category option is given, only disables
    other overlays in the same category.
  set-priority [--user USER_ID] PACKAGE PARENT|lowest|highest
    Change the priority of the overlay PACKAGE to be just higher than
    the priority of PACKAGE_PARENT If PARENT is the special keyword
    'lowest', change priority of PACKAGE to the lowest priority.
    If PARENT is the special keyword 'highest', change priority of
    PACKAGE to the highest priority.

```

### cmd package
#### cmd package -h
```
# cmd package -h
Package manager (package) commands:
  help
    Print this help text.

  path [--user USER_ID] PACKAGE
    Print the path to the .apk of the given PACKAGE.

  dump PACKAGE
    Print various system state associated with the given PACKAGE.

  list features
    Prints all features of the system.

  has-feature FEATURE_NAME [version]
    Prints true and returns exit status 0 when system has a FEATURE_NAME,
    otherwise prints false and returns exit status 1

  list instrumentation [-f] [TARGET-PACKAGE]
    Prints all test packages; optionally only those targeting TARGET-PACKAGE
    Options:
      -f: dump the name of the .apk file containing the test package

  list libraries
    Prints all system libraries.

  list packages [-f] [-d] [-e] [-s] [-3] [-i] [-l] [-u] [-U]
      [--uid UID] [--user USER_ID] [FILTER]
    Prints all packages; optionally only those whose name contains
    the text in FILTER.  Options are:
      -f: see their associated file
      -d: filter to only show disabled packages
      -e: filter to only show enabled packages
      -s: filter to only show system packages
      -3: filter to only show third party packages
      -i: see the installer for the packages
      -l: ignored (used for compatibility with older releases)
      -U: also show the package UID
      -u: also include uninstalled packages
      --uid UID: filter to only show packages with the given UID
      --user USER_ID: only list packages belonging to the given user

  list permission-groups
    Prints all known permission groups.

  list permissions [-g] [-f] [-d] [-u] [GROUP]
    Prints all known permissions; optionally only those in GROUP.  Options are:
      -g: organize by group
      -f: print all information
      -s: short summary
      -d: only list dangerous permissions
      -u: list only the permissions users will see

  resolve-activity [--brief] [--components] [--user USER_ID] INTENT
    Prints the activity that resolves to the given INTENT.

  query-activities [--brief] [--components] [--user USER_ID] INTENT
    Prints all activities that can handle the given INTENT.

  query-services [--brief] [--components] [--user USER_ID] INTENT
    Prints all services that can handle the given INTENT.

  query-receivers [--brief] [--components] [--user USER_ID] INTENT
    Prints all broadcast receivers that can handle the given INTENT.

  install [-lrtsfdg] [-i PACKAGE] [--user USER_ID|all|current]
       [-p INHERIT_PACKAGE] [--install-location 0/1/2]
       [--originating-uri URI] [---referrer URI]
       [--abi ABI_NAME] [--force-sdk]
       [--preload] [--instantapp] [--full] [--dont-kill]
       [--force-uuid internal|UUID] [--pkg PACKAGE] [-S BYTES] [PATH|-]
    Install an application.  Must provide the apk data to install, either as a
    file path or '-' to read from stdin.  Options are:
      -l: forward lock application
      -R: disallow replacement of existing application
      -t: allow test packages
      -i: specify package name of installer owning the app
      -s: install application on sdcard
      -f: install application on internal flash
      -d: allow version code downgrade (debuggable packages only)
      -p: partial application install (new split on top of existing pkg)
      -g: grant all runtime permissions
      -S: size in bytes of package, required for stdin
      --user: install under the given user.
      --dont-kill: installing a new feature split, don't kill running app
      --originating-uri: set URI where app was downloaded from
      --referrer: set URI that instigated the install of the app
      --pkg: specify expected package name of app being installed
      --abi: override the default ABI of the platform
      --instantapp: cause the app to be installed as an ephemeral install app
      --full: cause the app to be installed as a non-ephemeral full app
      --install-location: force the install location:
          0=auto, 1=internal only, 2=prefer external
      --force-uuid: force install on to disk volume with given UUID
      --force-sdk: allow install even when existing app targets platform
          codename but new one targets a final API level

  install-create [-lrtsfdg] [-i PACKAGE] [--user USER_ID|all|current]
       [-p INHERIT_PACKAGE] [--install-location 0/1/2]
       [--originating-uri URI] [---referrer URI]
       [--abi ABI_NAME] [--force-sdk]
       [--preload] [--instantapp] [--full] [--dont-kill]
       [--force-uuid internal|UUID] [--pkg PACKAGE] [-S BYTES]
    Like "install", but starts an install session.  Use "install-write"
    to push data into the session, and "install-commit" to finish.

  install-write [-S BYTES] SESSION_ID SPLIT_NAME [PATH|-]
    Write an apk into the given install session.  If the path is '-', data
    will be read from stdin.  Options are:
      -S: size in bytes of package, required for stdin

  install-commit SESSION_ID
    Commit the given active install session, installing the app.

  install-abandon SESSION_ID
    Delete the given active install session.

  set-install-location LOCATION
    Changes the default install location.  NOTE this is only intended for debugging;
    using this can cause applications to break and other undersireable behavior.
    LOCATION is one of:
    0 [auto]: Let system decide the best location
    1 [internal]: Install on internal device storage
    2 [external]: Install on external media

  get-install-location
    Returns the current install location: 0, 1 or 2 as per set-install-location.

  move-package PACKAGE [internal|UUID]

  move-primary-storage [internal|UUID]

  pm uninstall [-k] [--user USER_ID] [--versionCode VERSION_CODE] PACKAGE [SPLIT]
    Remove the given package name from the system.  May remove an entire app
    if no SPLIT name is specified, otherwise will remove only the split of the
    given app.  Options are:
      -k: keep the data and cache directories around after package removal.
      --user: remove the app from the given user.
      --versionCode: only uninstall if the app has the given version code.

  clear [--user USER_ID] PACKAGE
    Deletes all data associated with a package.

  enable [--user USER_ID] PACKAGE_OR_COMPONENT
  disable [--user USER_ID] PACKAGE_OR_COMPONENT
  disable-user [--user USER_ID] PACKAGE_OR_COMPONENT
  disable-until-used [--user USER_ID] PACKAGE_OR_COMPONENT
  default-state [--user USER_ID] PACKAGE_OR_COMPONENT
    These commands change the enabled state of a given package or
    component (written as "package/class").

  hide [--user USER_ID] PACKAGE_OR_COMPONENT
  unhide [--user USER_ID] PACKAGE_OR_COMPONENT

  suspend [--user USER_ID] TARGET-PACKAGE
    Suspends the specified package (as user).

  unsuspend [--user USER_ID] TARGET-PACKAGE
    Unsuspends the specified package (as user).

  grant [--user USER_ID] PACKAGE PERMISSION
  revoke [--user USER_ID] PACKAGE PERMISSION
    These commands either grant or revoke permissions to apps.  The permissions
    must be declared as used in the app's manifest, be runtime permissions
    (protection level dangerous), and the app targeting SDK greater than Lollipop MR1.

  reset-permissions
    Revert all runtime permissions to their default state.

  set-permission-enforced PERMISSION [true|false]

  get-privapp-permissions TARGET-PACKAGE
    Prints all privileged permissions for a package.

  get-privapp-deny-permissions TARGET-PACKAGE
    Prints all privileged permissions that are denied for a package.

  get-oem-permissions TARGET-PACKAGE
    Prints all OEM permissions for a package.

  set-app-link [--user USER_ID] PACKAGE {always|ask|never|undefined}
  get-app-link [--user USER_ID] PACKAGE

  trim-caches DESIRED_FREE_SPACE [internal|UUID]
    Trim cache files to reach the given free space.

  create-user [--profileOf USER_ID] [--managed] [--restricted] [--ephemeral]
      [--guest] USER_NAME
    Create a new user with the given USER_NAME, printing the new user identifier
    of the user.

  remove-user USER_ID
    Remove the user with the given USER_IDENTIFIER, deleting all data
    associated with that user

  set-user-restriction [--user USER_ID] RESTRICTION VALUE

  get-max-users

  get-max-running-users

  compile [-m MODE | -r REASON] [-f] [-c] [--split SPLIT_NAME]
          [--reset] [--check-prof (true | false)] (-a | TARGET-PACKAGE)
    Trigger compilation of TARGET-PACKAGE or all packages if "-a".  Options are:
      -a: compile all packages
      -c: clear profile data before compiling
      -f: force compilation even if not needed
      -m: select compilation mode
          MODE is one of the dex2oat compiler filters:
            assume-verified
            extract
            verify
            quicken
            space-profile
            space
            speed-profile
            speed
            everything
      -r: select compilation reason
          REASON is one of:
            first-boot
            boot
            install
            bg-dexopt
            ab-ota
            inactive
            shared
      --reset: restore package to its post-install state
      --check-prof (true | false): look at profiles when doing dexopt?
      --secondary-dex: compile app secondary dex files
      --split SPLIT: compile only the given split name

  force-dex-opt PACKAGE
    Force immediate execution of dex opt for the given PACKAGE.

  bg-dexopt-job
    Execute the background optimizations immediately.
    Note that the command only runs the background optimizer logic. It may
    overlap with the actual job but the job scheduler will not be able to
    cancel it. It will also run even if the device is not in the idle
    maintenance mode.

  reconcile-secondary-dex-files TARGET-PACKAGE
    Reconciles the package secondary dex files with the generated oat files.

  dump-profiles TARGET-PACKAGE
    Dumps method/class profile files to
    /data/misc/profman/TARGET-PACKAGE.txt

  snapshot-profile TARGET-PACKAGE [--code-path path]
    Take a snapshot of the package profiles to
    /data/misc/profman/TARGET-PACKAGE[-code-path].prof
    If TARGET-PACKAGE=android it will take a snapshot of the boot image

  set-home-activity [--user USER_ID] TARGET-COMPONENT
    Set the default home activity (aka launcher).

  set-installer PACKAGE INSTALLER
    Set installer package name

  get-instantapp-resolver
    Return the name of the component that is the current instant app installer.

  set-harmful-app-warning [--user <USER_ID>] <PACKAGE> [<WARNING>]
    Mark the app as harmful with the given warning message.

  get-harmful-app-warning [--user <USER_ID>] <PACKAGE>
    Return the harmful app warning message for the given app, if present

  uninstall-system-updates
    Remove updates to all system applications and fall back to their /system version.

<INTENT> specifications include these flags and arguments:
    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
    [-c <CATEGORY> [-c <CATEGORY>] ...]
    [-n <COMPONENT_NAME>]
    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
    [--esn <EXTRA_KEY> ...]
    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as Integer[])
    [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as List<Integer>)
    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as Long[])
    [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as List<Long>)
    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as Float[])
    [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as List<Float>)
    [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as String[]; to embed a comma into a string,
         escape it using "\,")
    [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as List<String>; to embed a comma into a string,
         escape it using "\,")
    [-f <FLAG>]
    [--grant-read-uri-permission] [--grant-write-uri-permission]
    [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]
    [--debug-log-resolution] [--exclude-stopped-packages]
    [--include-stopped-packages]
    [--activity-brought-to-front] [--activity-clear-top]
    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
    [--activity-launched-from-history] [--activity-multiple-task]
    [--activity-no-animation] [--activity-no-history]
    [--activity-no-user-action] [--activity-previous-is-top]
    [--activity-reorder-to-front] [--activity-reset-task-if-needed]
    [--activity-single-top] [--activity-clear-task]
    [--activity-task-on-home] [--activity-match-external]
    [--receiver-registered-only] [--receiver-replace-pending]
    [--receiver-foreground] [--receiver-no-abort]
    [--receiver-include-background]
    [--selector]
    [<URI> | <PACKAGE> | <COMPONENT>]


```


### cmd phone 

####  cmd phone -h
```
 cmd phone -h
Telephony Commands:
  help
    Print this help text.
  ims
    IMS Commands.
IMS Commands:
  ims set-ims-service [-s SLOT_ID] (-c | -d) PACKAGE_NAME
    Sets the ImsService defined in PACKAGE_NAME to to be the bound
    ImsService. Options are:
      -s: the slot ID that the ImsService should be bound for. If no option
          is specified, it will choose the default voice SIM slot.
      -c: Override the ImsService defined in the carrier configuration.
      -d: Override the ImsService defined in the device overlay.
  ims get-ims-service [-s SLOT_ID] [-c | -d]
    Gets the package name of the currently defined ImsService.
    Options are:
      -s: The SIM slot ID for the registered ImsService. If no option
          is specified, it will choose the default voice SIM slot.
      -c: The ImsService defined as the carrier configured ImsService.
      -c: The ImsService defined as the device default ImsService.
  ims enable [-s SLOT_ID]
    enables IMS for the SIM slot specified, or for the default voice SIM slot
    if none is specified.
  ims disable [-s SLOT_ID]
    disables IMS for the SIM slot specified, or for the default voice SIM
    slot if none is specified.


```
#### cmd phone  ims get-ims-service -s 0 -c -d
```
org.codeaurora.ims
```
#### cmd phone ims enable ims enable 
#### cmd phone ims enable ims disable 


### cmd power

#### cmd power -h
```
# cmd print
Print service commands:
  help
    Print this help text.
  set-bind-instant-service-allowed [--user <USER_ID>] true|false
    Set whether binding to print services provided by instant apps is allowed.
  get-bind-instant-service-allowed [--user <USER_ID>]
    Get whether binding to print services provided by instant apps is allowed.
255|parker:/ # cmd power
Power manager (power) commands:
  help
    Print this help text.

  set-mode MODE
    sets the power mode of the device to MODE.
    1 turns low power mode on and 0 turns low power mode off.

<INTENT> specifications include these flags and arguments:
    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
    [-c <CATEGORY> [-c <CATEGORY>] ...]
    [-n <COMPONENT_NAME>]
    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
    [--esn <EXTRA_KEY> ...]
    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as Integer[])
    [--eial <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
        (mutiple extras passed as List<Integer>)
    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as Long[])
    [--elal <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
        (mutiple extras passed as List<Long>)
    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as Float[])
    [--efal <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
        (mutiple extras passed as List<Float>)
    [--esa <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as String[]; to embed a comma into a string,
         escape it using "\,")
    [--esal <EXTRA_KEY> <EXTRA_STRING_VALUE>[,<EXTRA_STRING_VALUE...]]
        (mutiple extras passed as List<String>; to embed a comma into a string,
         escape it using "\,")
    [-f <FLAG>]
    [--grant-read-uri-permission] [--grant-write-uri-permission]
    [--grant-persistable-uri-permission] [--grant-prefix-uri-permission]
    [--debug-log-resolution] [--exclude-stopped-packages]
    [--include-stopped-packages]
    [--activity-brought-to-front] [--activity-clear-top]
    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
    [--activity-launched-from-history] [--activity-multiple-task]
    [--activity-no-animation] [--activity-no-history]
    [--activity-no-user-action] [--activity-previous-is-top]
    [--activity-reorder-to-front] [--activity-reset-task-if-needed]
    [--activity-single-top] [--activity-clear-task]
    [--activity-task-on-home] [--activity-match-external]
    [--receiver-registered-only] [--receiver-replace-pending]
    [--receiver-foreground] [--receiver-no-abort]
    [--receiver-include-background]
    [--selector]
    [<URI> | <PACKAGE> | <COMPONENT>]

```

### cmd print

#### cmd print
```

Print service commands:
  help
    Print this help text.
  set-bind-instant-service-allowed [--user <USER_ID>] true|false
    Set whether binding to print services provided by instant apps is allowed.
  get-bind-instant-service-allowed [--user <USER_ID>]
    Get whether binding to print services provided by instant apps is allowed.

```


### cmd  qti.ims.ext

#### cmd  sensorservice -h
```
# cmd  sensorservice -h
Sensor service commands:
  get-uid-state <PACKAGE> gets the uid state
  set-uid-state <PACKAGE> <active|idle> overrides the uid state
  reset-uid-state <PACKAGE> clears the uid state override
  help print this message

```
#### cmd  sensorservice  get-uid-state  com.android.settings
```
active

```


### cmd shortcut
#### cmd shortcut -h
```
# cmd shortcut -h
Usage: cmd shortcut COMMAND [options ...]

cmd shortcut reset-throttling [--user USER_ID]
    Reset throttling for all packages and users

cmd shortcut reset-all-throttling
    Reset the throttling state for all users

cmd shortcut override-config CONFIG
    Override the configuration for testing (will last until reboot)

cmd shortcut reset-config
    Reset the configuration set with "update-config"

cmd shortcut clear-default-launcher [--user USER_ID]
    Clear the cached default launcher

cmd shortcut get-default-launcher [--user USER_ID]
    Show the default launcher

cmd shortcut unload-user [--user USER_ID]
    Unload a user from the memory
    (This should not affect any observable behavior)

cmd shortcut clear-shortcuts [--user USER_ID] PACKAGE
    Remove all shortcuts from a package, including pinned shortcuts


```

#### cmd shortcut reset-throttling
```
success

```


####  cmd shortcut reset-config
```
success

```


#### cmd shortcut get-default-launcher
```
xxxxx.launch3  //   获取默认的launch桌面

```


### cmd slice

#### cmd slice
```
# cmd slice
Status bar commands:
  help
    Print this help text.

  get-permissions <authority>
    List the pkgs that have permission to an authority.


```
#### cmd slice  get-permissions 
```
Exception occurred while executing:
java.lang.IllegalArgumentException: Argument expected after "get-permissions"
        at android.os.ShellCommand.getNextArgRequired(ShellCommand.java:321)
        at com.android.server.slice.SliceShellCommand.onCommand(SliceShellCommand.java:47)
        at android.os.ShellCommand.exec(ShellCommand.java:103)
        at com.android.server.slice.SliceManagerService.onShellCommand(SliceManagerService.java:337)
        at android.os.Binder.shellCommand(Binder.java:643)
        at android.os.Binder.onTransact(Binder.java:541)
        at android.app.slice.ISliceManager$Stub.onTransact(ISliceManager.java:224)
        at android.os.Binder.execTransact(Binder.java:746)


```

### cmd stats


```
# cmd stats
usage: adb shell cmd stats print-stats-log [tag_required] [timestamp_nsec_optional]


usage: adb shell cmd stats meminfo

  Prints the malloc debug information. You need to run the following first:
   # adb shell stop
   # adb shell setprop libc.debug.malloc.program statsd
   # adb shell setprop libc.debug.malloc.options backtrace
   # adb shell start


usage: adb shell cmd stats print-uid-map [PKG]

  Prints the UID, app name, version mapping.
  PKG           Optional package name to print the uids of the package


usage: adb shell cmd stats pull-source [int]

  Prints the output of a pulled metrics source (int indicates source)


usage: adb shell cmd stats write-to-disk

  Flushes all data on memory to disk.


usage: adb shell cmd stats log-app-breadcrumb [UID] LABEL STATE
  Writes an AppBreadcrumbReported event to the statslog buffer.
  UID           The uid to use. It is only possible to pass a UID
                parameter on eng builds. If UID is omitted the calling
                uid is used.
  LABEL         Integer in [0, 15], as per atoms.proto.
  STATE         Integer in [0, 3], as per atoms.proto.


usage: adb shell cmd stats config remove [UID] [NAME]
usage: adb shell cmd stats config update [UID] NAME

  Adds, updates or removes a configuration. The proto should be in
  wire-encoded protobuf format and passed via stdin. If no UID and name is
  provided, then all configs will be removed from memory and disk.

  UID           The uid to use. It is only possible to pass the UID
                parameter on eng builds. If UID is omitted the calling
                uid is used.
  NAME          The per-uid name to use


              *Note: If both UID and NAME are omitted then all configs will

                     be removed from memory and disk!

usage: adb shell cmd stats dump-report [UID] NAME [--proto]
  Dump all metric data for a configuration.
  UID           The uid of the configuration. It is only possible to pass
                the UID parameter on eng builds. If UID is omitted the
                calling uid is used.
  NAME          The name of the configuration
  --proto       Print proto binary.


usage: adb shell cmd stats send-broadcast [UID] NAME
  Send a broadcast that triggers the subscriber to fetch metrics.
  UID           The uid of the configuration. It is only possible to pass
                the UID parameter on eng builds. If UID is omitted the
                calling uid is used.
  NAME          The name of the configuration


usage: adb shell cmd stats print-stats
  Prints some basic stats.
  --proto       Print proto binary instead of string format.


usage: adb shell cmd stats clear-puller-cache
  Clear cached puller data.

usage: adb shell cmd stats print-logs
      Only works on eng build



```
继续点
#### cmd stats print-stats-log



### cmd statusbar
```
# cmd statusbar
Status bar commands:
  help
    Print this help text.

  expand-notifications
    Open the notifications panel.

  expand-settings
    Open the notifications panel and expand quick settings if present.

  collapse
    Collapse the notifications and settings panel.

  add-tile COMPONENT
    Add a TileService of the specified component

  remove-tile COMPONENT
    Remove a TileService of the specified component

  click-tile COMPONENT
    Click on a TileService of the specified component

  check-support
    Check if this device supports QS + APIs

  get-status-icons
    Print the list of status bar icons and the order they appear in


```

####  cmd statusbar expand-notifications
```
自动打开下拉通知栏


```

####  cmd statusbar expand-settings
```
展开通知栏 下拉图标


```

####  cmd statusbar collapse
```
收起 通知栏


```

####  cmd statusbar add-tile COMPONENT
```
手动触发空指针吗  ......

```

####  cmd statusbar click-tile COMPONENT
```
手动触发空指针吗  ......

```


####  cmd statusbar check-support
```
#  cmd statusbar check-support
true


```


####  cmd statusbar get-status-icons
```
#  cmd statusbar get-status-icons
查看图标的集合
alarm_clock
rotate
headset
data_saver
ime
sync_failing
sync_active
nfc
tty
speakerphone
cdma_eri
data_connection
phone_evdo_signal
phone_signal
secure
managed_profile
cast
vpn
bluetooth
location
mute
volume
zen
ims
vowifi
ethernet
wifi
hotspot
mobile
airplane
battery
clock

``` 



### cmd uimode
```
cmd uimode
UiModeManager service (uimode) commands:
  help
    Print this help text.
  night [yes|no|auto]
    Set or read night mode.



```
#### cmd uimode night 
#### cmd uimode night yes
#### cmd uimode night no
#### cmd uimode night auto



### cmd user
```
# cmd user
User manager (user) commands:
  help
    Print this help text.

  list
    Prints all users on the system.


```

#### cmd user list
```
cmd user list
Users:
        UserInfo{0:机主:13} running



```
### cmd vibrator
```
# cmd vibrator
Vibrator commands:
  help
    Prints this help text.

  vibrate duration [description]
    Vibrates for duration milliseconds; ignored when device is on DND
    (Do Not Disturb) mode.

```

#### cmd vibrator vibrate  200
```
震动 0.2 秒

java.lang.IllegalArgumentException: maximum duration is 200
        at com.android.server.VibratorService$VibratorShellCommand.runVibrate(VibratorService.java:1333)
        at com.android.server.VibratorService$VibratorShellCommand.onCommand(VibratorService.java:1308)
        at android.os.ShellCommand.exec(ShellCommand.java:103)
        at com.android.server.VibratorService.onShellCommand(VibratorService.java:1292)
        at android.os.Binder.shellCommand(Binder.java:643)
        at android.os.Binder.onTransact(Binder.java:541)
        at android.os.IVibratorService$Stub.onTransact(IVibratorService.java:110)
        at android.os.Binder.execTransact(Binder.java:746)
		


```


### cmd window
```
# cmd window
Window manager (window) commands:
  help
      Print this help text.
  size [reset|WxH|WdpxHdp]
    Return or override display size.
    width and height in pixels unless suffixed with 'dp'.
  density [reset|DENSITY]
    Return or override display density.
  overscan [reset|LEFT,TOP,RIGHT,BOTTOM]
    Set overscan area for display.
  scaling [off|auto]
    Set display scaling mode.
  dismiss-keyguard
    Dismiss the keyguard, prompting user for auth if necessary.
  tracing (start | stop)
    Start or stop window tracing.


```
#### cmd window size
```
# cmd window size
Physical size: 1080x2340

```

#### cmd window density
```
Physical density: 400


```
#### cmd window scaling off
#### cmd window scaling auto

#### cmd window  tracing start
```
# cmd window  tracing start
Start tracing to /data/misc/wmtrace/wm_trace.pb.
```
#### cmd window  tracing stop
```
# cmd window  tracing stop
Stop tracing to /data/misc/wmtrace/wm_trace.pb. Waiting for traces to flush.
Trace written to /data/misc/wmtrace/wm_trace.pb.


```


### cmd wifi
```
# cmd wifi
Wi-Fi (wifi) commands:
  help
    Print this help text.
  set-ipreach-disconnect enabled|disabled
    Sets whether CMD_IP_REACHABILITY_LOST events should trigger disconnects. 【设置CMD_IP_REACHABILITY_LOST 是否引起断连】
  get-ipreach-disconnect
    Gets setting of CMD_IP_REACHABILITY_LOST events triggering disconnects.
  set-poll-rssi-interval-msecs <int>
    Sets the interval between RSSI polls to <int> milliseconds.
  get-poll-rssi-interval-msecs
    Gets current interval between RSSI polls, in milliseconds.


```

#### cmd wifi set-ipreach-disconnect enabled  
#### cmd wifi set-ipreach-disconnect disabled
#### cmd wifi get-ipreach-disconnect
```
IPREACH_DISCONNECT state is false
IPREACH_DISCONNECT state is true
```
#### cmd wifi set-poll-rssi-interval-msecs 2000  
```
设置时间间隔 2秒 2000毫秒

```

#### cmd wifi get-poll-rssi-interval-msecs
```
cmd wifi get-poll-rssi-interval-msecs
WifiStateMachine.mPollRssiIntervalMsecs = 3000

```


### cmd settings

```
cmd settings
Settings provider (settings) commands:
  help
      Print this help text.
  get [--user <USER_ID> | current] NAMESPACE KEY
      Retrieve the current value of KEY.
  put [--user <USER_ID> | current] NAMESPACE KEY VALUE [TAG] [default]
      Change the contents of KEY to VALUE.
      TAG to associate with the setting.
      {default} to set as the default, case-insensitive only for global/secure namespace
  delete NAMESPACE KEY
      Delete the entry for KEY.
  reset [--user <USER_ID> | current] NAMESPACE {PACKAGE_NAME | RESET_MODE}
      Reset the global/secure table for a package with mode.
      RESET_MODE is one of {untrusted_defaults, untrusted_clear, trusted_defaults}, case-insensitive
  list NAMESPACE
      Print all defined keys.
      NAMESPACE is one of {system, secure, global}, case-insensitive

```


#### cmd settings reset global untrusted_defaults
#### cmd settings reset global untrusted_clear
#### cmd settings reset global trusted_defaults
#### cmd settings reset secure untrusted_defaults
#### cmd settings reset secure untrusted_clear
#### cmd settings reset secure trusted_defaults

#### cmd settings delete global airplane_mode_on [删除项]
#### cmd settings get global airplane_mode_on
#### cmd settings put global airplane_mode_on 1 
####  cmd settings list global
```

#  cmd settings list global
Phenotype_flags=
adb_enabled=1
add_users_when_locked=0
airplane_mode_on=0
airplane_mode_radios=cell,bluetooth,wifi,nfc,wimax
airplane_mode_toggleable_radios=bluetooth,wifi,nfc
assisted_gps_enabled=1
audio_safe_volume_state=3
auto_time=1
auto_time_zone=1
ble_scan_always_enabled=1
bluetooth_disabled_profiles=0
bluetooth_on=0
boot_count=27
call_auto_retry=0
car_dock_sound=/system/media/audio/ui/Dock.ogg
car_undock_sound=/system/media/audio/ui/Undock.ogg
cdma_cell_broadcast_sms=1
charging_vibration_enabled=1
database_creation_buildid=PPH29.21
debug_app=null
default_install_location=0
default_restrict_background_data=0
desk_dock_sound=/system/media/audio/ui/Dock.ogg
desk_undock_sound=/system/media/audio/ui/Undock.ogg
development_settings_enabled=1
device_name=Moto Z
device_provisioned=1
device_provisioning_mobile_data=0
dock_audio_media_enabled=1
dock_sounds_enabled=0
dock_sounds_enabled_when_accessbility=0
dropbox_max_files=1000
dropbox_quota_kb=5242880
emergency_affordance_needed=0
emergency_tone=0
heads_up_notifications_enabled=1
hs20_saved_state=1
location_global_kill_switch=0
lock_sound=/system/media/audio/ui/Lock.ogg
low_battery_sound=/system/media/audio/ui/LowBattery.ogg
low_battery_sound_timeout=0
low_power=0
max_sound_trigger_detection_service_ops_per_day=1000
mobile_data=1
mobile_data_always_on=0
mode_ringer=2
netstats_enabled=1
network_recommendations_enabled=0
network_recommendations_package=com.google.android.gms
network_watchlist_last_report_time=1561478400000
package_verifier_enable=1
package_verifier_user_consent=1
power_sounds_enabled=1
preferred_network_mode=10,10
set_install_location=0
show_zen_settings_suggestion=1
sound_trigger_detection_service_op_timeout=15000
stay_on_while_plugged_in=0
subscription_mode=0
sys_storage_full_threshold_bytes=2097152
sysui_demo_allowed=null
tether_offload_disabled=0
theater_mode_on=0
transition_animation_scale=1.0
trusted_sound=/system/media/audio/ui/Trusted.ogg
unlock_sound=/system/media/audio/ui/Unlock.ogg
upload_apk_enable=1
usb_mass_storage_enabled=1
wait_for_debugger=0
webview_provider=com.android.chrome
wifi_country_code=cn
wifi_display_on=0
wifi_max_dhcp_retry_count=9
wifi_networks_available_notification_on=1
wifi_on=1
wifi_saved_state=0
wifi_scan_always_enabled=1
wifi_sleep_policy=2
wifi_verbose_logging_enabled=1
wifi_wakeup_enabled=0
window_animation_scale=1.0
wireless_charging_started_sound=/system/media/audio/ui/ChargingStarted.ogg
zen_duration=0
zen_mode=0
zen_mode_config_etag=1049832873
zen_mode_ringer_level=2
zen_settings_suggestion_viewed=0
zen_settings_updated=1



```


####  cmd settings list system
```
获取 设置APK system域名下的设置项

accelerometer_rotation=0
alarm_alert=content://media/internal/audio/media/18
alarm_alert_set=1
dim_screen=1
dtmf_tone=1
dtmf_tone_type=0
font_scale=1.0
haptic_feedback_enabled=1
hearing_aid=0
hide_rotation_lock_toggle_for_accessibility=0
lockscreen_sounds_enabled=1
mode_ringer_streams_affected=422
mute_streams_affected=47
notification_light_pulse=1
notification_sound=content://media/internal/audio/media/60
notification_sound_set=1
pointer_speed=0
ringtone=content://media/internal/audio/media/30
ringtone_2=content://media/internal/audio/media/30
ringtone_2_set=1
ringtone_set=1
screen_auto_brightness_adj=0.0
screen_brightness=67
screen_brightness_for_vr=86
screen_brightness_mode=1
screen_off_timeout=300000
sound_effects_enabled=0
status_bar_show_battery_percent=0
system_locales=zh-Hans-CN,pt-BR
tty_mode=0
user_rotation=0
vibrate_when_ringing=0
volume_alarm=6
volume_alarm_speaker=2
volume_bluetooth_sco=7
volume_music=5
volume_music_speaker=8
volume_notification=5
volume_ring=5
volume_ring_earpiece=0
volume_ring_speaker=1
volume_system=7
volume_voice=4
volume_voice_earpiece=1

```


####  cmd settings list secure
```
# cmd settings list secure
accessibility_display_inversion_enabled=null
accessibility_display_magnification_enabled=0
accessibility_display_magnification_scale=2.0
accessibility_enabled=0
allowed_geolocation_origins=http://www.google.com http://www.google.co.uk
android_id=6f961f1f7e0074ac
autofill_service=com.google.android.gms/.autofill.service.AutofillService
backup_enabled=null
backup_transport=com.google.android.gms/.backup.BackupTransportService
bluetooth_address=90:73:5A:C2:91:1D
bluetooth_name=cocacola Z
camera_double_tap_power_gesture_disabled=1
clock_seconds=null
com.google.android.gms.backup/dogfood_secondary_key=null
default_input_method=com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME
double_tap_to_wake=1
doze_enabled=2
enabled_accessibility_services=
enabled_input_methods=com.google.android.googlequicksearchbox/com.google.android.voicesearch.ime.VoiceInputMethodService:com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME

enabled_notification_assistant=com.google.android.ext.services/android.ext.services.notification.Assistant
enabled_notification_listeners=com.cocacolarola.cocacoladisplay/com.cocacolarola.cocacoladisplay.notification.DisplayNotifListenerService:com.cocacolarola.launcher3/com.android.launcher3.notification.NotificationListener:com.google.android.apps.restore/com.google.android.apps.pixelmigrate.component.NotificationConsolidatorService
enabled_notification_policy_access_packages=com.cocacola.camera2:com.android.camera2:com.google.android.apps.wellbeing:com.cocacola.arselfie:com.cocacola.cameraone
hush_gesture_used=0
icon_blacklist=null
immersive_mode_confirmations=confirmed
input_methods_subtype_history=com.google.android.inputmethod.latin/com.android.inputmethod.latin.LatinIME;1891618174:com.baidu.input/.ImeService;1891618174
install_non_market_apps=1
keyguard_slice_uri=null
last_setup_shown=eclair_1
location_changer=1
location_providers_allowed=
lock_screen_allow_private_notifications=1
lock_screen_owner_info_enabled=0
lock_screen_show_notifications=1
lockscreen.disabled=0
long_press_timeout=400
manual_ringer_toggle_count=0
mock_location=0
mount_play_not_snd=1
mount_ums_autostart=0
mount_ums_notify_enabled=1
mount_ums_prompt=1
multi_press_timeout=300
nfc_payment_default_component=com.cocacola.VirtualUiccPayment/com.cocacola.VirtualUiccPayment.VirtualUiccPaymentService
notification_badging=1
overview_nav_bar_gesture=null
package_verifier_state=1
qs_auto_tiles=hotspot
qs_show_brightness=null
screensaver_activate_on_dock=1
screensaver_activate_on_sleep=0
screensaver_components=com.google.android.deskclock/com.android.deskclock.Screensaver
screensaver_default_component=com.google.android.deskclock/com.android.deskclock.Screensaver
screensaver_enabled=1
selected_input_method_subtype=-1
selected_spell_checker=com.google.android.inputmethod.latin/com.android.inputmethod.latin.spellcheck.AndroidSpellCheckerService
selected_spell_checker_subtype=0
show_ime_with_hard_keyboard=0
sleep_timeout=-1
sms_default_application=com.google.android.apps.messaging
snoozed_schedule_condition_provider=
speak_password=1
sync_parent_sounds=0
systemui.google.opa_enabled=0
sysui_do_not_disturb=null
sysui_keyguard_left=null
sysui_keyguard_right=null
sysui_nav_bar=null
sysui_nav_bar_left=null
sysui_nav_bar_right=null
sysui_qqs_count=null
sysui_qs_fancy_anim=null
sysui_qs_move_whole_rows=null
sysui_qs_tiles=wifi,bt,dnd,flashlight,rotation,battery,cell,airplane,night,custom(com.cocacola.ccc.ota/.env.SystemUpdateQSTile),cast,hotspot,custom(com.cocacola.help/com.cocacola.feedback.quicksettings.FeedBackTileService)
sysui_rounded_content_padding=null
sysui_tuner_version=4
sysui_volume_down_silent=null
sysui_volume_up_silent=null
touch_exploration_enabled=0
touch_exploration_granted_accessibility_services=
trust_agents_initialized=1
unknown_sources_default_reversed=1
user_setup_complete=1
user_setup_personalization_state=0
voice_interaction_service=com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService
voice_recognition_service=com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService
volume_hush_gesture=1
wake_gesture_enabled=1


```


## cmp -> toybox
## comm -> toybox
## consumer
## content
## coredump
## cp -> toybox
## cpio -> toybox
## cppreopts.sh
## cqa_send
## crash_dump32
## crash_dump64
## csdump
## curl
## cut -> toybox

# D
## dalvikvm -> dalvikvm64
## dalvikvm32
## dalvikvm64
## date -> toybox
## dd -> toolbox
## debuggerd
## dex2oat
## dex2oatd
## dexdiag
## dexdump
## dexlist
## dexoptanalyzer
## dexoptanalyzerd
## df -> toybox
## diff -> toybox
## dirname -> toybox
## dmesg -> toybox
## dnsmasq
## dos2unix -> toybox
## dpm
## dpmd
## drmserver
## dropboxhelper
## du -> toybox
## dumpres
## dumpstate
## dumpsys
## dun-server

# E

## e2fsck
## e2fsdroid
## echo -> toybox
## egrep -> grep
## env -> toybox
## expand -> toybox
## expr -> toybox

# F

## fallocate -> toybox
## false -> toybox
## fgrep -> grep
## file -> toybox
## find -> toybox
## flock -> toybox
## fmt -> toybox
## free -> toybox
## fsck.exfat
## fsck.f2fs
## fsck_msdos

# G

## gatekeeperd
## gdbserver
## gdbserver64
## getenforce -> toybox
## getevent -> toolbox
## getfattr -> toolbox
## getprop -> toolbox
## grep
## groups -> toybox
## gunzip -> toybox
## gzip -> toybox

# H

## head -> toybox
## healthd
## hid
## host_manager_11ad
## hostname -> toybox
## hw
## hwclock -> toybox
## hwservicemanager

# I

## id -> toybox
## idmap
## ifconfig -> toybox
## ime
## imscmd
## incident
## incident_helper
## incidentd
## init.mmi.carrier.sh
## init.qti.khung.sh
## inotifyd -> toybox
## input
## insmod -> toybox
## installd
## invoke_test_client
## ionice -> toybox
## iorenice -> toybox
## iotop
## ip
## ip-wrapper-1.0 -> netutils-wrapper-1.0
## ip6tables
## ip6tables-restore -> ip6tables
## ip6tables-save -> ip6tables
## ip6tables-wrapper-1.0 -> netutils-wrappe
## ipInfo
## iptables
## iptables-restore -> iptables
## iptables-save -> iptables
## iptables-wrapper-1.0 -> netutils-wrapper
## iw

# K

## keystore
## keystore_cli_v2
## kill -> toybox
## killall -> toybox
## kpitrace

# L

## ld.mc
## linker
## linker64
## linker_asan -> linker
## linker_asan64 -> linker64
## lmkd
## ln -> toybox
## load_policy -> toybox
## locksettings
## log -> toybox
## logcat
## logcatd
## logd
## logname -> toybox
## logpersist.cat -> logpersist.start
## logpersist.start
## logpersist.stop -> logpersist.start
## logwrapper
## losetup -> toybox
## ls -> toybox
## lshal
## lsmod -> toybox
## lsof -> toybox
## lspci -> toybox
## lsusb -> toybox

# M

## make_f2fs
## md5sum -> toybox
## mdnsd
## media
## mediadrmserver
## mediaextractor
## mediametrics
## mediaserver
## memory_replay32
## memory_replay64
## memtest
## microcom -> toybox
## mkdir -> toybox
## mke2fs
## mkfifo -> toybox
## mkfs.exfat
## mkfs.ext2 -> mke2fs
## mkfs.ext3 -> mke2fs
## mkfs.ext4 -> mke2fs
## mknod -> toybox
## mkswap -> toybox
## mktemp -> toybox
## mmi
## mmi_diag
## modinfo -> toybox
## modprobe -> toybox
## monkey
## more -> toybox
## motsettings
## mount -> toybox
## mountpoint -> toybox
## move_elabel_data.sh
## move_time_data.sh
## move_wifi_data.sh
## mtpd
## mv -> toybox
## mxt-app

# N

## ndc
## ndc-wrapper-1.0 -> netutils-wrapper-1.0
## netd
## netstat -> toybox
## netutils-wrapper-1.0
## newfs_msdos -> toolbox
## nice -> toybox
## nl -> toybox
## nohup -> toybox

# O
## oatdump
## od -> toybox
## otapreopt
## otapreopt_chroot
## otapreopt_script
## otapreopt_slot

# P

##  paste -> toybox
##  patch -> toybox
##  patchoat
##  patchoatd
##  perfetto
##  perfprofd
##  perfservice
##  pgrep -> toybox
##  pidof -> toybox
##  ping
##  ping6
##  pkill -> toybox
##  pm
##  pmap -> toybox
##  pppd
##  preopt2cachename
##  printenv -> toybox
##  printf -> toybox
##  profman
##  profmand
##  ps -> toybox
##  pwd -> toybox

# R

## racoon
## readlink -> toybox
## realpath -> toybox
## reboot
## recovery-persist
## recovery-refresh
## renice -> toybox
## requestsync
## resize2fs
## restorecon -> toybox
## rm -> toybox
## rmdir -> toybox
## rmmod -> toybox
## rr
## rtspclient
## rtspserver
## run-as
## runcon -> toybox

# S

## schedtest
## screencap
## screenrecord
## sdcard
## secdiscard
## secilc
## sed -> toybox
## sendevent -> toybox
## sendevent2 -> toolbox
## sensorservice
## seq -> toybox
## service
## servicemanager
## setenforce -> toybox
## setfattr -> toolbox
## setprop -> toybox
## setsid -> toybox
## settings
## setup_shutdown.sh
## sg_write_buffer
## sgdisk
## sh
## sha1sum -> toybox
## sha224sum -> toybox
## sha256sum -> toybox
## sha384sum -> toybox
## sha512sum -> toybox
## simg2img
## sleep -> toybox
## sload_f2fs
## sm
## sort -> toybox
## split -> toybox
## sqlite3
## ss
## start -> toybox
## stat -> toybox
## statsd
## stop -> toybox
## storaged
## strace
## strings -> toybox
## stty -> toybox
## surfaceflinger
## svc
## swapoff -> toybox
## swapon -> toybox
## sync -> toybox
## sysctl -> toybox

# T

## tac -> toybox
## tail -> toybox
## tar -> toybox
## taskset -> toybox
## tc
## tc-wrapper-1.0 -> netutils-wrapper-1.0
## tcmd_system
## tee -> toybox
## telecom
## thermalserviced
## time -> toybox
## timedexec
## timeout -> toybox
## tinycap
## tinypcminfo
## tombstoned
## toolbox
## top -> toybox
## touch -> toybox
## touchrpt
## toybox
## tr -> toybox
## traced
## traced_probes
## tracepath
## tracepath6
## traceroute6
## true -> toybox
## truncate -> toybox
## tty -> toybox
## tune2fs
## tzdatacheck

# U

## ufs_ffu_start.sh
## ufs_wd
## uiautomator
## ulimit -> toybox
## umount -> toybox
## uname -> toybox
## uncrypt
## uniq -> toybox
## unix2dos -> toybox
## update_engine
## update_engine_client
## update_verifier
## uptime -> toybox
## usbd
## usleep -> toybox
## uudecode -> toybox
## uuencode -> toybox

# V

## vdc
## vmstat -> toybox
## vold
## vold_prepare_subdirs
## vr

# W
## wait_for_keymaster
## wc -> toybox
## which -> toybox
## whoami -> toybox
## wificond
## wigig_logcollector
## wigig_remoteserver
## wigig_wiburn
## wm

# X

## xargs -> toybox
## xxd -> toybox

# Y

## yes -> toybox

# Z
## zcat -> toybox