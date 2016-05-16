//
// Created by lx on 2016/5/13.
//

#include "helloNDK.h"
#include <string.h>

JNIEXPORT jstring JNICALL Java_com_lixiangers_androidresearch_feature_NDKUtil_getStringFormC(JNIEnv* env, jobject obj) {
        return  (*env)->NewStringUTF(env,"这里是来自c的string");
}


