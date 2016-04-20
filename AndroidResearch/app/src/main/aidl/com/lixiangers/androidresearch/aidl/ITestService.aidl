// ITestService.aidl
package com.lixiangers.androidresearch.aidl;
import com.lixiangers.androidresearch.aidl.Person;

// Declare any non-default types here with import statements

interface ITestService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    Person getPerson();
}
