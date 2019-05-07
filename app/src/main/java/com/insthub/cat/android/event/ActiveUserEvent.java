package com.insthub.cat.android.event;

/**
 *
 *
 * Created by linux on 2017/7/25.
 */

public class ActiveUserEvent extends BaseEvent{

    public String head_img,content;


    public ActiveUserEvent(String head_img ,String content)
    {
        this.head_img = head_img;
        this.content = content;
    }


    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
