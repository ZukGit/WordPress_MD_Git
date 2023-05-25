package com.and.zmain_life.bean;


public class Pause_StockCommon_Event extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_StockCommon_Event(boolean playOrPause) {

        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public Pause_StockCommon_Event Copy(boolean playOrPause){
        Pause_StockCommon_Event copyItem = new Pause_StockCommon_Event(playOrPause);
        return copyItem;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
