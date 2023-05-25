package com.and.zmain_life.bean;


public class Pause_Stock_Area_Event extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_Stock_Area_Event(boolean playOrPause) {

        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public Pause_Stock_Area_Event Copy(boolean playOrPause){
        Pause_Stock_Area_Event copyItem = new Pause_Stock_Area_Event(playOrPause);
        return copyItem;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
