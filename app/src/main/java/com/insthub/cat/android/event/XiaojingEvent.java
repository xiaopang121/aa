package com.insthub.cat.android.event;

import com.common.android.fdao.orm.Column;
import com.common.android.fdao.orm.Id;
import com.common.android.fdao.orm.Table;

/**
 *
 * 小鲸消息事件
 * Created by linux on 2017/7/25.
 */
@Table(name = "table_xiaojing_msg3")
public class XiaojingEvent extends BaseEvent{


    @Id
    @Column(name = "_id" )
    public int _id;


    @Column(name = "uid" )
    public String uid;


    @Column(name = "phone" )
    public  String phone;

    @Column(name = "content" )
    public String  content;


    @Column(name = "state" )
    public int   state;

    @Column(name ="keywords")
    public String keywords;


    @Column(name ="im_code")
    public String im_code;


    @Column(name ="service_name")
    public String service_name;


    @Column(name ="details")
    public String details;

    @Column(name ="image")
    public String image;

    public XiaojingEvent()
    {}


    public XiaojingEvent(String urd,String paramPhone,String paramCantent,String paramkey,String imcode,String service_name,String details,String image )
    {
             this.uid = urd;
             this.phone = paramPhone;
             this.content = paramCantent;
             this.keywords =paramkey;
             this.im_code = imcode;
             state=1;
             this.service_name = service_name;
             this.details = details;
             this.image = image;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIm_code() {
        return im_code;
    }

    public void setIm_code(String im_code) {
        this.im_code = im_code;
    }
}
