package com.and.zmain_life.bean;

public class MainPageChangeEvent {
    private int page;

    public MainPageChangeEvent(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
