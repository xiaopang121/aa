package com.insthub.cat.android.module;

import java.io.Serializable;

/**
 * User:macbook
 * DATE:2018/11/3 14:00
 * Desc:${DESC}
 */
public class AnnexBean implements Serializable {

    private String annex_type;
    private String file_name;
    private String url;

    public String getAnnex_type() {
        return annex_type;
    }

    public void setAnnex_type(String annex_type) {
        this.annex_type = annex_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
