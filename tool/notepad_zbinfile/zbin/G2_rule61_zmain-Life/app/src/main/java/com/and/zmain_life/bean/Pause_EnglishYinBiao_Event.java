package com.and.zmain_life.bean;


public class Pause_EnglishYinBiao_Event extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_EnglishYinBiao_Event(boolean playOrPause)
    {
        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public Pause_EnglishYinBiao_Event Copy(boolean playOrPause){
        Pause_EnglishYinBiao_Event copyItem = new Pause_EnglishYinBiao_Event(playOrPause);
        return copyItem;
    }


    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
