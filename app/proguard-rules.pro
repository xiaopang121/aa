# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/macbook/Documents/DevelopTools/tools/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


#指定代码的压缩级别
-optimizationpasses 5

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

# 是否使用大小写混合
-dontusemixedcaseclassnames

#预校验
-dontpreverify

#混淆时是否记录日志
-verbose

#忽略警告，避免打包时某些警告出现
-ignorewarning

# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*

#记录生成的日志数据,gradle build时在本项目根目录输出
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt

-keepattributes Signature

#-----------------------------6.默认保留区-----------------------

# 保持哪些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService




#如果有引用v4、v7包可以添加下面这行
-keep public class * extends android.support.*


# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}


## 保持枚举 enum 类不被混淆
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 # 保持 Parcelable 不被混淆
 -keep class * implements android.os.Parcelable {
     public static final android.os.Parcelable$Creator *;
 }
#---------------------------7.webview-----------------------

-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}


-dontwarn com.insthub.ship.android.module.**
-keep class com.insthub.ship.android.module.**{*;}
#-----------------------------end-------------------------------


-dontwarn org.bouncycastle.**
-keep class org.bouncycastle.** { *;}

#-libraryjars libs/pushservice-5.9.0.59.jar
-dontwarn com.baidu.**
-keep class com.baidu.** { *;}


-dontwarn org.apache.commons.codec.**
-keep class org.apache.commons.codec.** { *;}



-dontwarn com.mob.**
-keep class com.mob.** { *;}


-dontwarn com.fuiou.mobile.**
-keep class com.fuiou.mobile.** { *;}


-dontwarn com.lidroid.xutils.**
-keep class com.lidroid.xutils.** { *;}


#Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.* { *;}
-dontwarn  com.google.gson.**


-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*


-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn Android.webkit.WebViewClient

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}



# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }



# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Retrolambda
-dontwarn java.lang.invoke.*

# RxJava RxAndroid
-dontwarn sun.misc.**
-dontwarn rx.**
-keep class rx.** { *; }
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}


# picasso
-dontwarn com.squareup.picasso.**
-keep class com.squareup.picasso.** { *; }


# glide
-dontwarn com.bumptech.glide.**
-keep class  com.bumptech.glide.** { *; }



-dontwarn org.xmlpull.v1.XmlPullParser
-dontwarn org.xmlpull.v1.XmlSerializer
-keep class org.xmlpull.v1.* {*;}

-dontwarn com.common.**
-keep class  com.common.** { *; }

-dontwarn  com.insthub.ship.android.mvp.**
-keep class  com.insthub.ship.android.mvp.** { *; }


-keep class cn.sharesdk.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*


-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class com.insthub.jld.android.R$*{
public static final int *;
}


-dontwarn com.netease.**
-keep class com.netease.** {*;}
#如果你使用全文检索插件，需要加入
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}

