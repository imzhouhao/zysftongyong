package com.boot.zysf.api.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class JieBa {

        public static List<String> RemovalOfStopWords(List<String> list) throws IOException {
            // 中文 停用词 .txt 文件路径
            String filePath = "F:\\projects\\zysf\\zysf-api\\src\\main\\resources\\停用词.txt";
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<String> stopWords = new ArrayList<>();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                stopWords.add(temp.trim());
            }
            List<String> termStringList = new ArrayList<>();
            for(int i =0;i<list.size();i++) {
                termStringList.add(list.get(i));
            }
            termStringList.removeAll(stopWords);
            return termStringList;
        }
    public  List<String>  fenci(String s){
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String s1 =  CharacterUtil.dealwithCharacter(s);
        List<String> list = segmenter.sentenceProcess(s1);
        return list;
    }
}

