package com.insthub.cat.android.event;

/**
 *
 *
 * Created by linux on 2017/7/25.
 */

public class WXShareEvent extends BaseEvent{

    public int  state;


    public WXShareEvent(int  state )
    {
        this.state = state;
    }

}
