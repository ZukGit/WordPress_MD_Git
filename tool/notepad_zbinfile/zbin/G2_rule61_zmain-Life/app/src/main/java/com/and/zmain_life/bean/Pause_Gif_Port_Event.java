package com.and.zmain_life.bean;


public class Pause_Gif_Port_Event extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_Gif_Port_Event(boolean playOrPause) {
        super(playOrPause);
        this.playOrPause = playOrPause;
    }


    public  Pause_Gif_Port_Event Copy(boolean playOrPause){
        Pause_Gif_Port_Event copyItem = new Pause_Gif_Port_Event(playOrPause);
        return copyItem;
    }


    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
