# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.kakao.** { *; }
-keep class net.daum.** { *; }
-keepattributes Signature
-keepclassmembers class * {
  public static <fields>;
  public *;
}
-dontwarn android.support.v4.**,org.slf4j.**,com.google.android.gms.**

-keepattributes Signature
-keepattributes Annotation
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

-keep class android.support.v4.** #support 라이브러리 난독화 방지
-keep class android.support.v7.** #support 라이브러리 난독화 방지
-keep class oauth.signpost.** {*;}
-keep class ch.boye.** {*;}
-keep class opcxml.** {*;}
-keep class org.json.** {*;}
-keep class com.google.** {*;}
-keep class com.project.dungji.network.** {*;}

-keepattributes EnclosingMethod
-keepattributes InnerClasses

-dontwarn org.apache.**

# Retrofit does reflection on generic parameters and InnerClass is required to use Signature.

-keepattributes Signature, InnerClasses



# Retain service method parameters when optimizing.

-keepclassmembers,allowshrinking,allowobfuscation interface * {

    @retrofit2.http.* <methods>;

}



# Ignore annotation used for build tooling.

-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement



# Ignore JSR 305 annotations for embedding nullability information.

-dontwarn javax.annotation.**



# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.

-dontwarn kotlin.Unit



# Top-level functions that can only be used by Kotlin.

-dontwarn retrofit2.-KotlinExtensions

##---------------Begin: proguard configuration for Gson  ----------

# Gson uses generic type information stored in a class file when working with fields. Proguard

# removes such information by default, so configure it to keep all of it.

-keepattributes Signature



# For using GSON @Expose annotation

-keepattributes *Annotation*



# Gson specific classes

-dontwarn sun.misc.**

#-keep class com.google.gson.stream.** { *; }



# Application classes that will be serialized/deserialized over Gson

-keep class com.google.gson.examples.android.model.** { *; }



# Prevent proguard from stripping interface information from TypeAdapterFactory,

# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)

-keep class * implements com.google.gson.TypeAdapterFactory

-keep class * implements com.google.gson.JsonSerializer

-keep class * implements com.google.gson.JsonDeserializer



##---------------End: proguard configuration for Gson  ----------

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
