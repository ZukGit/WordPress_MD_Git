package com.and.zmain_life.bean;


public abstract class Pause_Base_Event {
    private boolean playOrPause;

    public Pause_Base_Event(boolean playOrPause) {
        this.playOrPause = playOrPause;
    }

    public abstract Pause_Base_Event Copy(boolean playOrPause);

    public abstract  boolean isPlayOrPause();
}
