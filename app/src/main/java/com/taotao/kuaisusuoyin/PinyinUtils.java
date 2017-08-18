package com.taotao.kuaisusuoyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * Created by fanxintao on 2017/8/17.
 */

public class PinyinUtils {

    public static String getPinyin(String hanzi){

        StringBuilder sb=new StringBuilder();
        HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //不能对直接对多个汉字转换，只能一个一个转换
        char[] arr=hanzi.toCharArray();
        for (int i=0;i<arr.length;i++){
            if (Character.isWhitespace(arr[i])){
                continue;
            }
            try {
                String pinyinArr[]= PinyinHelper.toHanyuPinyinStringArray(arr[i],format);
                if (pinyinArr!=null){
                    sb.append(pinyinArr[0]);
                }else {
                    sb.append(arr[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //不正确的汉字
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }

}
