package com.taotao.kuaisusuoyin;

/**
 * Created by fanxintao on 2017/8/17.
 */

public class Person {

    private String name;  //姓名
    private String pinyin;   //拼音
    private String headerWord;        //拼音首字母


    public Person(String name) {
        this.name = name;
        this.pinyin = PinyinUtils.getPinyin(name);
        this.headerWord = pinyin.substring(0,1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getHeaderWord() {
        return headerWord;
    }

    public void setHeaderWord(String headerWord) {
        this.headerWord = headerWord;
    }
}
