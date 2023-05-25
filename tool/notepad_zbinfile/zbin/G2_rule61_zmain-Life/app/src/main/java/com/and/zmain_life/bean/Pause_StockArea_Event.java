package com.and.zmain_life.bean;


public class Pause_StockArea_Event extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_StockArea_Event(boolean playOrPause) {

        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public Pause_StockArea_Event Copy(boolean playOrPause){
        Pause_StockArea_Event copyItem = new Pause_StockArea_Event(playOrPause);
        return copyItem;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
