package com.xander.mdblog.util;

import java.util.UUID;

/**
 *
 * @author 叶振东
 * @date 2018-12-17
 */
public class ShortUUIDUtil {
    private static String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };


    /**
     * 生成uuid
     * @param
     * @return java.lang.String
     * @author yezhendong
     * @date 2019/9/11
     */
    public static String getShortUUID() {
        StringBuilder stringBuilder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        /* 32 -> 8 */
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            // 16进制为基解析
            int strInteger = Integer.parseInt(str, 16);
            // 0x3E -> 字典总数 62
            stringBuilder.append(chars[strInteger % 0x3E]);
        }
        return stringBuilder.toString();
    }
}
