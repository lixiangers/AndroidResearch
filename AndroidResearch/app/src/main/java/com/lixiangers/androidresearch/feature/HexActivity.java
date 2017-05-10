package com.lixiangers.androidresearch.feature;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiangers.androidresearch.R;

import java.util.Arrays;

public class HexActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        findViewById(R.id.bt_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] buf = {0x02, 0x01, 0x03, 0xE8, 0x00, 0x02, 0x02, 0x03, 0xE8, 0x00, 0x02};
                String test = String.valueOf(buf);
                char[] chars = test.toCharArray();
                byte funcationCode = (byte) chars[0];
                char[] contentChar = Arrays.copyOfRange(chars, 1, chars.length);
                Log.d("", "");
                //高，低 8位转为int
                char[] testChar = {0x03, 0xe8};
                int hight = testChar[0];
                int low = testChar[1];
                int sum = (hight << 8) + low;
                Log.d("sum", "sum:" + sum);

                //int 转换为高，低 char
                int value = -1000;
                int lowValue = value & 0xff;  // 低8位
                int highValue = (value >> 8) & 0xff; // 高8位
                Log.d("sum", "lowValue:" + lowValue + " highValue:" + highValue);

                int[] crc = crc(new int[]{0xaa, 0x5, 0x0});
                Log.d("", "");
            }
        });
    }


    public int[] crc(int[] data) {
        int[] temdata = new int[data.length + 2];
        //unsigned char alen = *aStr – 2;   //CRC16只计算前两部分
        int xda, xdapoly;
        int i, j, xdabit;
        xda = 0xFFFF;
        xdapoly = 0xA001; // (X**16 + X**15 + X**2 + 1)
        for (i = 0; i < data.length; i++) {
            xda ^= data[i];
            for (j = 0; j < 8; j++) {
                xdabit = (int) (xda & 0x01);
                xda >>= 1;
                if (xdabit == 1)
                    xda ^= xdapoly;
            }
        }
        System.arraycopy(data, 0, temdata, 0, data.length);
        temdata[temdata.length - 2] = (int) (xda & 0xFF);
        temdata[temdata.length - 1] = (int) (xda >> 8);
        return temdata;
    }
}
