package com.and.zmain_life.bean;


public class ScreenOn_Off_Event  extends  Pause_Base_Event {
    private boolean playOrPause;

    public ScreenOn_Off_Event(boolean playOrPause) {

        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public  ScreenOn_Off_Event Copy(boolean playOrPause){
        ScreenOn_Off_Event copyItem = new ScreenOn_Off_Event(playOrPause);
        return copyItem;
    }
    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
