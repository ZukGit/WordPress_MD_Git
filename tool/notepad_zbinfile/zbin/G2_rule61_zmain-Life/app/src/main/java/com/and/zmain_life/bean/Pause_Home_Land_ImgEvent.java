package com.and.zmain_life.bean;


public class Pause_Home_Land_ImgEvent extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_Home_Land_ImgEvent(boolean playOrPause) {
        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public  Pause_Home_Land_ImgEvent Copy(boolean playOrPause){
        Pause_Home_Land_ImgEvent copyItem = new Pause_Home_Land_ImgEvent(playOrPause);
        return copyItem;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
