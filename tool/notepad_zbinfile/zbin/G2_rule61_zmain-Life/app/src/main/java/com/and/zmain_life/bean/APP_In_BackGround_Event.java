package com.and.zmain_life.bean;


public class APP_In_BackGround_Event {
    private boolean playOrPause;

    public APP_In_BackGround_Event(boolean playOrPause) {
        this.playOrPause = playOrPause;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
