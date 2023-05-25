package com.and.zmain_life.bean;


public class Pause_Stock_Simulate_Event extends  Pause_Base_Event {
    private boolean playOrPause;

    public Pause_Stock_Simulate_Event(boolean playOrPause) {

        super(playOrPause);
        this.playOrPause = playOrPause;
    }

    public Pause_Stock_Simulate_Event Copy(boolean playOrPause){
        Pause_Stock_Simulate_Event copyItem = new Pause_Stock_Simulate_Event(playOrPause);
        return copyItem;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }
}
