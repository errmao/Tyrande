package org.tyrande.common.utils;

import org.tyrande.common.constant.NormalConstants;

/**
 * 字符串驼峰转换工具类
 *
 * @author Tyrande
 */
public class StringChangeUtil {

    /**
     * 下划线转驼峰
     *
     * @param underlineName 带有下划线的名字
     * @return 驼峰字符串
     */
    public static String underlineToHump(String underlineName) {
        //截取下划线分成数组
        char[] charArray = underlineName.toCharArray();
        boolean underlineBefore = false;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                buffer.append(charArray[i] -= 32);
                underlineBefore = false;
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * 驼峰转 下划线
     *
     * @param humpName 驼峰命名
     * @return 带下滑线的String
     */
    public static String humpToUnderline(String humpName) {
        char[] charArray = humpName.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append(NormalConstants.UNDER_LINE).append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }


    /**
     * 将首字母和带 _ 后第一个字母 转换成大写
     */
    public static String upperTable(String str) {
        StringBuffer sbf = new StringBuffer();
        if (str.contains(NormalConstants.UNDER_LINE)) {
            String[] split = str.split(NormalConstants.UNDER_LINE);
            for (int i = 0, index = split.length; i < index; i++) {
                String upperTable = upperTable(split[i]);
                sbf.append(upperTable);
            }
        } else {
            char[] ch = str.toCharArray();
            if (ch[0] >= NormalConstants.A_CHAR && ch[0] <= NormalConstants.Z_CHAR) {
                ch[0] = (char) (ch[0] - 32);
            }
            sbf.append(ch);
        }
        return sbf.toString();
    }
}
