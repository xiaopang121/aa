package com.insthub.cat.android.module;

import java.io.Serializable;

/**
 * User:macbook
 * DATE:2017/12/11 21:35
 * Desc:${DESC}
 */

public class UserInfoData extends BaseData {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        public String token;
       public String session;
       public String id;
       public String loginName;
       public String birthday;
       public String  sex;
       public String phone;
       public String loginPassword;
       public long  registedDate;
       public long  lastAccessedTime;
       public String idCardNo;
       public String email;
       public int userType;
       public int status;
       public String openId;
       public String industry;
       public String dep;
       public String trade;
       public String code;
       public String  position; //职位

        private String im_code;
       public int deduction; //抵用券

       public int recom_count; //推荐的用户总数

       public int coupon ;//优惠券

       public int integral;//积分
        public int random_num;


        public float money;


       public String user_name;

       public String share_image;
       // 服务保证金使用的是
       public float service_money;

        // 技术服务费
        public float save_money;

        //技术服务费有效期至时间
        public String service_fee_time;

        //是否诚信认证 0：未认证 1：已认证
        public int auth;

       // 保证金 0:未缴纳 1:已缴纳
        public int is_save;
        //服务费缴纳 0:未缴纳 1:已缴纳
        public int service_fee;

       public float balance;//余额

        private String kaipu_words;


        private int has_store;

        public String getIm_code() {
            return im_code;
        }

        public void setIm_code(String im_code) {
            this.im_code = im_code;
        }

        public int getHas_store() {
            return has_store;
        }

        public void setHas_store(int has_store) {
            this.has_store = has_store;
        }

        public int getRandom_num() {
            return random_num;
        }

        public void setRandom_num(int random_num) {
            this.random_num = random_num;
        }

        public String getKaipu_words() {
            return kaipu_words;
        }

        public void setKaipu_words(String kaipu_words) {
            this.kaipu_words = kaipu_words;
        }

        public int getAuth() {
            return auth;
        }

        public void setAuth(int auth) {
            this.auth = auth;
        }

        public int getIs_save() {
            return is_save;
        }

        public void setIs_save(int is_save) {
            this.is_save = is_save;
        }

        public int getService_fee() {
            return service_fee;
        }

        public void setService_fee(int service_fee) {
            this.service_fee = service_fee;
        }

        public float getService_money() {
            return service_money;
        }

        public void setService_money(float service_money) {
            this.service_money = service_money;
        }

        public float getSave_money() {
            return save_money;
        }

        public void setSave_money(float save_money) {
            this.save_money = save_money;
        }

        public String getService_fee_time() {
            return service_fee_time;
        }

        public void setService_fee_time(String service_fee_time) {
            this.service_fee_time = service_fee_time;
        }

        public float getBalance() {
            return balance;
        }

        public void setBalance(float balance) {
            this.balance = balance;
        }

        public float getMoney() {
            return money;
        }

        public void setMoney(float money) {
            this.money = money;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getDeduction() {
            return deduction;
        }

        public void setDeduction(int deduction) {
            this.deduction = deduction;
        }

        public int getRecom_count() {
            return recom_count;
        }

        public void setRecom_count(int recom_count) {
            this.recom_count = recom_count;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String head_image;

        public String getHead_image() {
            return head_image;
        }

        public void setHead_image(String head_image) {
            this.head_image = head_image;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSession() {
            return session;
        }

        public void setSession(String session) {
            this.session = session;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }


        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLoginPassword() {
            return loginPassword;
        }

        public void setLoginPassword(String loginPassword) {
            this.loginPassword = loginPassword;
        }

        public long getRegistedDate() {
            return registedDate;
        }

        public void setRegistedDate(long registedDate) {
            this.registedDate = registedDate;
        }

        public long getLastAccessedTime() {
            return lastAccessedTime;
        }

        public void setLastAccessedTime(long lastAccessedTime) {
            this.lastAccessedTime = lastAccessedTime;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getDep() {
            return dep;
        }

        public void setDep(String dep) {
            this.dep = dep;
        }

        public String getTrade() {
            return trade;
        }

        public void setTrade(String trade) {
            this.trade = trade;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }


        public String getShare_image() {
            return share_image;
        }

        public void setShare_image(String share_image) {
            this.share_image = share_image;
        }
    }
}
