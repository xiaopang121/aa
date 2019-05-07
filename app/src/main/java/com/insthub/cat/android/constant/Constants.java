package com.insthub.cat.android.constant;

/**
 * 常量配置
 */

public class Constants {


    /**
     * 服务器地址
     */


     public static final String SERVICE_URL_PREFEX = "http://139.196.92.19:8080/app/app/";

    public static final String SERVICE_URL_PREFEX2 = "http://139.196.92.19:8080/app/phase2/";

    public static final String SERVICE_URL_PREFEX3 = "http://139.196.92.19:8080/app/phase3/";

    public static final String IMAGE_URL_PREFEX = "http://www.qitengteng.com:8080/upload/";

    public static String NET_ERROR = "网络异常，无法访问";


    public static final  String TCP_ADDRESS="";

    public static final  int  porit=0;


    public static  final String RSA_PRIVATE="";

    /**支付宝 商户PID */
    public static final String APPID = "2018060460291625";
    /** 商户收款账号 */
    public static final String SELLER = "";
    /** 商户私钥，pkcs8格式 */
    public static final String RSA2_PRIVATE="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCXykJaTfiOQZIHMcxn0xz6kSy1FfPbBSGxMCpkfxiPRwTClom+LFocrdWPJ5C53H8XfrSilmdyb2wW7RTSYgxnlKKAUOLQfyL30lZus7+uP1Q4gJ+a84ha1PlW27mGowHpc9JHVauYPJDEZMXHOBvF8fb6l+klfH4nGuuV2ufFbBlWnPqYwEo6D0cqptU8QP3JAWPKHTVK2K+LjtOEXeE2RP0wpXX/eFppERpNqIbMP8/gsMT2vY6OtDcpuEmmDr4In8qTwwJy+LB12O+dWi3LCOLs3GneblThTgWIVdgUAhNxJ9mtmCbn3Bia9qF/w98HMUGUC1bD0/Dj31P4QwQ9AgMBAAECggEBAIsrbtrNUm9bvz/JcNhgv9kc8hi2H/dYJSQIPVYXEbWML9kZnQE4OQbCe2Pdqtw+RmiMS6ImtII/lqMOjGC4dRcjODrd7pereawutgCZ3c0tU1La23t+fi6JxnLU0q2XF1GsV8f/zGywWys2GgAmeZBr4T6xuZodIq1ICWhEZOykXqyKfRQa+RVJyXm4u0ATnjCsGYYzL0hOQoSbfan+WM9pRrGEIhqnTAXkid3TeCVt9pVwhAAhP90fgPzq1Ow0J1ILdYxsem9URygk3bYHiQHETsWnZwVmPKP4PAUpYs0JbPmRoxIR1hp8c5EQsdTvE/e0tLTIdoEMEez2nvjn0CECgYEAyOVH5U3Igbxyv0ItUHgaw1JCNGUYgwKoZQp1NboUpnd7e3tv7GMof6MmK6O+2fC/UZwgYUVrqsPnXYFboBF09YD9zx5YVDnJiHBWpHldwZLLAhwhAxvc8HD5MQvsXlDfjD8GcrY5pb8Zj1PibEp5TtEnBiDCL6Pms628FN2JmQcCgYEAwWzUEiELkECFmRrxVaAgyEzqs0DV+peLjd/2ZgphNlLwRHt1vDIIunCjh1jXAZW1ERWZ28P5peddXySuhdTGauBfudlFtHp+TedCiGBB0g4gSqLC6fXgA5CE9Kv4Jg9t8iux0+GANlPuwCmUJPXkch7k/pI2Hs1MN3sndMDre5sCgYBA8W3WgNiqqALoNTp04v6z2amxSmUsJPXPBBxz6921Grb+ah76aeuzp0g1n0+ZIKyYc0I8b7aok3/9yE2YZ0xJ+RMTm5muUKgHxVkc/mcCprOd0P4TBTTdPYgxBMu6iaZarh0lhCcKn8ZhgRqXK1Qf2Jkm7/wWboOr3iQJErzijQKBgQCTXaHZShnwRmhC9NcHwJ8ZXBi4h14/tPQbHJYyuhfEdr4dvt49ivVBbo34kYQF7hRstsNpP09VygdGdMEOr8SYKuhjxRipX2YZXmc/Ve7v5dlOY+EStUR+Er5pVxgRj1HZ3tZ2Mmknexz8hPEck8b/92/KUIUxRIKcBpVSC6WdnwKBgQCDxLM2d8Fa2p1ML98YpXEnq9lfIEQr+ASzQH/gaAfPdZ9NawFJWmIGZIIxxCDLb1Lnw8Bko33VimHhP4ERRJBkOkC7OW/PCTbu+bNc4QrVMi0Ykb+tX4ZhJ1ebB2t/mRnG/zxn7Nz0XBNT9RAaiqXRIQNmAJw4h08IvDma+Xl3TQ==";
    /** 支付宝公钥 */
    public static final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl8pCWk34jkGSBzHMZ9Mc+pEstRXz2wUhsTAqZH8Yj0cEwpaJvixaHK3VjyeQudx/F360opZncm9sFu0U0mIMZ5SigFDi0H8i99JWbrO/rj9UOICfmvOIWtT5Vtu5hqMB6XPSR1WrmDyQxGTFxzgbxfH2+pfpJXx+JxrrldrnxWwZVpz6mMBKOg9HKqbVPED9yQFjyh01Stivi47ThF3hNkT9MKV1/3haaREaTaiGzD/P4LDE9r2OjrQ3KbhJpg6+CJ/Kk8MCcviwddjvnVotywji7Nxp3m5U4U4FiFXYFAITcSfZrZgm59wYmvahf8PfBzFBlAtWw9Pw499T+EMEPQIDAQAB";

    /** 支付回调接口,需要服务器端支持 */
    public static final String NOTIFY_URL = "http://139.196.92.19:8080/app/app/pay/alipay_notify.do";





    public static final String WX_APPID="wxf1c27bf4e6631d6a";

    public static final String WX_MCHID="1505597381";

   // public static final String WX_APPSECRET="4a570f198fb0868560638f2c40a5f31c";

    public static final String WX_APPSECRET="45d4360de784c10fc661b44c69a933d3";

    public static final String WX_KEY="45d4360de784c10fc661b44c69a933d3";


    public static final String WX_NOTIFY_URL="http://118.31.40.22:8080/charger/phone/wxNotify";



}
