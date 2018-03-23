package com.prashant.RoomDemo;

/**
 * Created by Prashant on 20/03/18.
 */

public class Demo2 extends Demo{

    int j = 20;
    public Demo2() {
        super(10);
    }

    public int getJ() {
        return j;
    }


    @Override
    protected int getI() {
        return j;
    }
}
