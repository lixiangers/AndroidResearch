package com.lixiangers.androidresearch.feature;

/**
 * String: += 在底层会实现是 string = (new StringBuilder(String.valueOf(string))).append("min-snail").toString();
 * 会新建StringBuilder对象完成，在大亮重复的情况，会很耗性能，速度比较慢。
 *
 * StringBuilder,StringBuffer 功能是一样的。区别是StringBuffer是线程安全的，StringBuilder是线程非安全的。在速度上差不多，
 * 理论上StringBuilder会稍微快一点
 *
 *
 * 下面是运行的结果：显示String 的速度差的太多
 * The 0 [	    String : 153259	StringBuffer : 9	StringBuilder : 10	]
 The 1 [	    String : 113407	StringBuffer : 5	StringBuilder : 4	]
 The 2 [	    String : 100408	StringBuffer : 5	StringBuilder : 4	]
 The 3 [	    String : 100328	StringBuffer : 4	StringBuilder : 4	]
 The 4 [	    String : 107526	StringBuffer : 5	StringBuilder : 5	]
 The 5 [	    String : 106052	StringBuffer : 4	StringBuilder : 4	]
 */
public class StringTest {
    private final int LOOP_TIMES = 200000;
    private final String CONSTANT_STRING = "min-snail";

    public static void main(String[] args) {
        new StringTest().startup();
    }

    public void testString() {
        String string = "";
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < LOOP_TIMES; i++) {
            string += CONSTANT_STRING;
        }
        long endTime = System.currentTimeMillis();
        System.out.print("String : " + (endTime - beginTime) + "\t");
    }

    public void testStringBuffer() {
        StringBuffer buffer = new StringBuffer();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < LOOP_TIMES; i++) {
            buffer.append(CONSTANT_STRING);
        }
        buffer.toString();
        long endTime = System.currentTimeMillis();
        System.out.print("StringBuffer : " + (endTime - beginTime) + "\t");
    }

    public void testStringBuilder() {
        StringBuilder builder = new StringBuilder();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < LOOP_TIMES; i++) {
            builder.append(CONSTANT_STRING);
        }
        builder.toString();
        long endTime = System.currentTimeMillis();
        System.out.print("StringBuilder : " + (endTime - beginTime) + "\t");
    }

    public void startup() {
        for (int i = 0; i < 6; i++) {
            System.out.print("The " + i + " [\t    ");
            testString();
            testStringBuffer();
            testStringBuilder();
            System.out.println("]");
        }
    }
}
