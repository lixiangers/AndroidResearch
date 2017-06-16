package com.example;

import java.nio.ByteBuffer;

/**
 * 这里测试用来验证使用Heap Memory 和 Direct Memory的效率
 * Direct Memory不在java 虚拟机中，而是直接使用物理内存（出现outOfMemoryError概率比较小）
 *
 * 运行结果：
 * alloc directByteBuffer for 100000 times spends 61424ms
 directByteBuffer process 100000 times spends 36ms
 alloc nonDirectByteBuffer for 100000 times spends 11313ms
 nonDirectByteBuffer process 100000 times spends 18ms
 */
public class DirectByteBufferTest {
    public static void main(String[] args) {
        int count = 100000;
        int cap = 1024 * 1024;
        DirectByteBufferTest bufferTest = new DirectByteBufferTest();
        bufferTest.testDirectBuf(count, cap);
        bufferTest.testNonDirectBuf(count, cap);
    }

    private void testDirectBuf(int count, int cap) {
        long st;
        long ed;
        ByteBuffer byteBuf = null;
        st = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            byteBuf = allocDirectByteBuffer(cap);

        }
        ed = System.currentTimeMillis();
        System.out.println("alloc directByteBuffer for " + count
                + " times spends " + (ed - st) + "ms");

        st = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            processBuf(byteBuf);
        }
        ed = System.currentTimeMillis();
        System.out.println("directByteBuffer process " + count
                + " times spends " + (ed - st) + "ms");
    }

    private ByteBuffer testNonDirectBuf(int count, int cap) {
        long st = System.currentTimeMillis();
        ByteBuffer byteBuf = null;
        for (int i = 0; i < count; i++) {
            byteBuf = allocNonDirectByteBuffer(cap);
        }
        long ed = System.currentTimeMillis();
        System.out.println("alloc nonDirectByteBuffer for " + count
                + " times spends " + (ed - st) + "ms");
        st = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            processBuf(byteBuf);

        }
        ed = System.currentTimeMillis();
        System.out.println("nonDirectByteBuffer process " + count
                + " times spends " + (ed - st) + "ms");
        return byteBuf;
    }

    private ByteBuffer allocNonDirectByteBuffer(int cap) {
        ByteBuffer byteBuf = ByteBuffer.allocate(cap);
        return byteBuf;
    }

    private ByteBuffer allocDirectByteBuffer(int cap) {
        ByteBuffer directBuf = ByteBuffer.allocateDirect(cap);
        return directBuf;
    }

    private void processBuf(ByteBuffer buf) {
        byte[] bytes = "assfasf".getBytes();
        buf.put(bytes);
        for (int i = 0; i < bytes.length; i++) {
            byte b = buf.get(i);
            byte[] bytes2 = new byte[]{b};
            // System.out.print(new String(bytes2));
        }
        // System.out.println();
        // System.out.println(buf.capacity());
    }
}
