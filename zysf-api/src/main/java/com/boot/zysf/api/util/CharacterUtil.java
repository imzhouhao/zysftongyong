package com.boot.zysf.api.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
@Slf4j
public class CharacterUtil {
    public String dealwithCharacter(String s) {
        if (s != null) {
            return s.replaceAll("[:：，；。、,.`;()（）!？！?]", "");
        }
        return "";
    }
    public String deleteChareacter(String s){
        if(s!=null){
            return s.replaceAll("[:：，；。、,.`;]", "*");
        }
        return "";
    }
    public List<String> readBycommon(String s) {
        if (s != null) {
            String[] split = s.split("，");
            List<String> list = Arrays.asList(split);
            return list;
        }
        List<String> list = new ArrayList<String>();
        list.add("");
        return list;
    }
    public void getPosition(String add){

    }

    /*public static void main(String[] args) {
        String s = dealwithCharacter("钢结构：多、高层建筑钢结构，桥梁用钢铁结构，塔桅钢结构，网架、网壳结构、管结构，模板、脚手架、坑道支撑用钢铁制支柱，钢制装配结构件，门式钢架轻型厂房结构，拱型波纹钢屋盖结构，城市建设钢制品，重型工业厂房结构，锅炉钢结构，钢铁井架及上层结构，钢铁可调或伸缩支柱，钢铁管状立柱，钢铁可伸缩格子平顶梁，钢铁制水闸门，钢铁制凸堤、栈桥，" +
                "钢铁制船用桅杆、舷梯，钢铁制道口拦路杆，特种构筑物，住宅钢结构，其他钢结构；");
        System.out.println(readBycommon(s));
    }*/
}
