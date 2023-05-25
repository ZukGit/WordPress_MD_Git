package com.and.zmain_life.bean;


public class Pause_MP4_Common_Land_VideoEvent extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_MP4_Common_Land_VideoEvent(boolean playOrPause)
    {
        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public  Pause_MP4_Common_Land_VideoEvent Copy(boolean playOrPause){
        Pause_MP4_Common_Land_VideoEvent copyItem = new Pause_MP4_Common_Land_VideoEvent(playOrPause);
        return copyItem;
    }


    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
