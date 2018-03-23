package com.prashant.RoomDemo;

/**
 * Created by Prashant on 20/03/18.
 */

public abstract class Demo {

    protected int i = 0;

    public Demo(int i) {
        this.i = i;
    }

    protected abstract int getI();
}

