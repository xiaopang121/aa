package com.insthub.cat.android.event;

/**
 *
 * 打印机活动
 * Created by linux on 2017/8/11.
 */

public class ActionEvent extends BaseEvent {


    public String title,content;


    public ActionEvent(String title ,String content)
    {
        this.title = title;
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
