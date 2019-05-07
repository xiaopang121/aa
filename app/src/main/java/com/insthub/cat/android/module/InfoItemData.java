package com.insthub.cat.android.module;

import java.io.Serializable;

/**
 * 快报ITEM
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class InfoItemData implements Serializable {

    private String id;

    private String title;

    private String mainPhoto;

    private long createTime;

    private int  status;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
}
