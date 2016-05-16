//
// Created by lx on 2016/5/13.
//

#include "helloNDK.h"
#include <string.h>
#include <android/log.h>

#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

JNIEXPORT jstring JNICALL Java_com_lixiangers_androidresearch_feature_NDKUtil_getStringFormC(
        JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "这里是来自c的string");
}

JNIEXPORT void JNICALL Java_com_lixiangers_androidresearch_feature_NDKUtil_cCallFromMethod1
        (JNIEnv *env, jobject obj) {
    //调用DataProvider对象中的helloFromJava()方法
    //获取到某个对象, 获取对象中的方法, 调用获取到的方法
    LOGI("in code");
    //NDKUtil com_lixiangers_androidresearch_feature_NDKUtil
    char *classname = "com/lixiangers/androidresearch/feature/NDKUtil";


    jclass dpclazz = (*env)->FindClass(env, classname);
    if (dpclazz == 0)
        LOGI("class not find !!!");
    else
        LOGI("class find !!!");

    //参数介绍 : 第二个参数是Class对象, 第三个参数是方法名,第四个参数是方法的签名, 获取到调用的method
    jmethodID methodID = (*env)->GetMethodID(env, dpclazz, "Method1", "()V");
    if (methodID == 0)
        LOGI("method not find !!!");
    else
        LOGI("method find !!!");

    /*
     * 调用方法 void (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
     * 参数介绍 : 后面的 ... 是可变参数, 如果该返回值void的方法有参数, 就将参数按照次序排列
     */
    LOGI("before call method");
    (*env)->CallVoidMethod(env, obj, methodID);
    LOGI("after call method");
}

JNIEXPORT void JNICALL Java_com_lixiangers_androidresearch_feature_NDKUtil_cCallFromMethod2
        (JNIEnv *env, jobject obj) {
//调用DataProvider对象中的helloFromJava()方法
    //获取到某个对象, 获取对象中的方法, 调用获取到的方法
    LOGI("in code");
    //NDKUtil com_lixiangers_androidresearch_feature_NDKUtil
    char *classname = "com/lixiangers/androidresearch/feature/NDKUtil";


    jclass dpclazz = (*env)->FindClass(env, classname);
    if (dpclazz == 0)
        LOGI("class not find !!!");
    else
        LOGI("class find !!!");

    //参数介绍 : 第二个参数是Class对象, 第三个参数是方法名,第四个参数是方法的签名, 获取到调用的method
    jmethodID methodID = (*env)->GetMethodID(env, dpclazz, "Method2", "(II)I");
    if (methodID == 0)
        LOGI("method not find !!!");
    else
        LOGI("method find !!!");

    /*
     * 调用方法 void (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
     * 参数介绍 : 后面的 ... 是可变参数, 如果该返回值void的方法有参数, 就将参数按照次序排列
     */
    LOGI("before call method");
    (*env)->CallIntMethod(env, obj, methodID, 4, 5);
    LOGI("after call method");
}

JNIEXPORT void JNICALL Java_com_lixiangers_androidresearch_feature_NDKUtil_cCallFromMethod3
        (JNIEnv *env, jobject obj) {
//调用DataProvider对象中的helloFromJava()方法
    //获取到某个对象, 获取对象中的方法, 调用获取到的方法
    LOGI("in code");
    //NDKUtil com_lixiangers_androidresearch_feature_NDKUtil
    char *classname = "com/lixiangers/androidresearch/feature/NDKUtil";


    jclass dpclazz = (*env)->FindClass(env, classname);
    if (dpclazz == 0)
        LOGI("class not find !!!");
    else
        LOGI("class find !!!");

    //参数介绍 : 第二个参数是Class对象, 第三个参数是方法名,第四个参数是方法的签名, 获取到调用的method
    jmethodID methodID = (*env)->GetMethodID(env, dpclazz, "Method3", "(Ljava/lang/String;)V");
    if (methodID == 0)
        LOGI("method not find !!!");
    else
        LOGI("method find !!!");

    /*
     * 调用方法 void (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
     * 参数介绍 : 后面的 ... 是可变参数, 如果该返回值void的方法有参数, 就将参数按照次序排列
     */
    LOGI("before call method");
    (*env)->CallVoidMethod(env, obj, methodID,
                           (*env)->NewStringUTF(env, "printString method callback success!!"));
    LOGI("after call method");
}

JNIEXPORT void JNICALL Java_com_lixiangers_androidresearch_feature_NDKUtil_cCallFromMethod4
        (JNIEnv *env, jobject obj) {
//调用DataProvider对象中的helloFromJava()方法
    //获取到某个对象, 获取对象中的方法, 调用获取到的方法
    LOGI("in code");
    //NDKUtil com_lixiangers_androidresearch_feature_NDKUtil
    char *classname = "com/lixiangers/androidresearch/feature/NDKUtil";


    jclass dpclazz = (*env)->FindClass(env, classname);
    if (dpclazz == 0)
        LOGI("class not find !!!");
    else
        LOGI("class find !!!");

    //参数介绍 : 第二个参数是Class对象, 第三个参数是方法名,第四个参数是方法的签名, 获取到调用的method
    jmethodID methodID = (*env)->GetStaticMethodID(env, dpclazz, "Method4",
                                                   "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
    if (methodID == 0)
        LOGI("method not find !!!");
    else
        LOGI("method find !!!");

    /*
     * 调用方法 void (*CallStaticVoidMethod)(JNIEnv*, jclass, jmethodID, ...);
     * jclass 前面find的class
     * 参数介绍 : 后面的 ... 是可变参数, 如果该返回值void的方法有参数, 就将参数按照次序排列
     */
    LOGI("before call method");
    (*env)->CallStaticObjectMethod(env, dpclazz, methodID,
                                 (*env)->NewStringUTF(env, "String 1"),
                                 (*env)->NewStringUTF(env, "String 2"));
    LOGI("after call method");
}



