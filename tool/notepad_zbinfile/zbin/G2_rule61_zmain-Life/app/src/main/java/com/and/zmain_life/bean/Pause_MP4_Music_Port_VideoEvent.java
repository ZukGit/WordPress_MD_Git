package com.and.zmain_life.bean;


public class Pause_MP4_Music_Port_VideoEvent  extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_MP4_Music_Port_VideoEvent(boolean playOrPause) {

super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public  Pause_MP4_Music_Port_VideoEvent Copy(boolean playOrPause){
        Pause_MP4_Music_Port_VideoEvent copyItem = new Pause_MP4_Music_Port_VideoEvent(playOrPause);
        return copyItem;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
