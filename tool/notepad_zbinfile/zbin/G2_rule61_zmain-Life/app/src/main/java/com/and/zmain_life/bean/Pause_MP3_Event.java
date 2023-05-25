package com.and.zmain_life.bean;


public class Pause_MP3_Event  extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_MP3_Event(boolean playOrPause) {

        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public  Pause_MP3_Event Copy(boolean playOrPause){
        Pause_MP3_Event copyItem = new Pause_MP3_Event(playOrPause);
        return copyItem;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
