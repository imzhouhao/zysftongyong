package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.config.SpringContextUtil;
import com.boot.zysf.api.mapper.IndustroyTupuMapper;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.IndustrialCategory;
import com.boot.zysf.api.po.IndustroyTupu;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.boot.zysf.api.service.IndustroyTupuService;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Component

public class Tagging {
    @Autowired
    IIndustrialCategoryService iIndustrialCategoryService;
    @Autowired
    BusinessDataService businessDataService;
    @Autowired
    IndustroyTupuService industroyTupuService;
    @Autowired
    IndustroyTupuMapper industroyTupuMapper;

    private static String path = "F:\\项目\\哈尔滨项目\\Tencent_AILab_ChineseEmbedding\\ChineseEmbedding.bin";
    private static File gModel = new File(path);
    private static Word2Vec vec = WordVectorSerializer.readWord2VecModel(gModel);

//    private static Word2Vec vec = null;

    public double[] AverageArgs(List<String> list) {

        double[] vector = new double[200];
        for (int i = 0; i < list.size(); i++) {
            double[] vector1 = vec.getWordVector(list.get(i));
            for (int j = 0; j < vector.length; j++) {
                if (vector1 == null) {
                    vector[j] = vector[j] + 0;
                } else {
                    vector[j] = vector[j] + vector1[j];
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < vector.length; j++) {
                vector[j] = vector[j] / vector.length;
            }
        }
        return vector;
    }

    public double Similarity(double[] v1, double[] v2) {
        double fenmu = 0;
        double fenzi = 0;
        double fenzi0 = 0;
        double fenzi1 = 0;
        for (int i = 0; i < v1.length; i++) {
            fenmu = fenmu + v1[i] * v2[i];
            fenzi0 = fenzi0 + pow((v1[i]), 2);
            fenzi1 = fenzi1 + pow((v2[i]), 2);
        }
        fenzi = sqrt(fenzi0) * sqrt(fenzi1);
        if (fenzi1 != 0) {
            return fenmu / fenzi;
        }
        return 0;
    }
    public void getCode(){
        List<IndustroyTupu> list = industroyTupuService.list();
        for(int i=0;i<list.size();i++){
            IndustroyTupu industroyTupu = list.get(i);
            String code = industroyTupu.getCode();
            String son = industroyTupu.getName();
            if(code!=null){
                continue;
            }
            Integer id = industroyTupu.getId();
            QueryWrapper<IndustroyTupu> query = new QueryWrapper<>();
            query.eq("parent_id", id);
            List<IndustroyTupu> list1 = industroyTupuService.list(query);
            for(int j = 0;j<list1.size();j++){
                son = son.concat(list1.get(j).getName());
            }
            industroyTupu.setCode("son"+son);
            industroyTupuService.updateById(industroyTupu);
        }
        QueryWrapper<IndustrialCategory> query = new QueryWrapper<>();
        query.ne("code","last");
        List<IndustrialCategory> list2 = iIndustrialCategoryService.list(query);
        for(int i=0;i<list2.size();i++){
            IndustrialCategory industrialCategory = list2.get(i);
            String code = industrialCategory.getCode();
            String son = industrialCategory.getName();
            Integer id = industrialCategory.getId();
            QueryWrapper<IndustrialCategory> query1 = new QueryWrapper<>();
            query1.eq("parent_id", id);
            List<IndustrialCategory> list1 = iIndustrialCategoryService.list(query1);
            for(int j = 0;j<list1.size();j++){
                System.out.println((list1.get(j).getName()));
                if(list1.get(j).getCode()==null) continue;
                if(list1.get(j).getCode().contains("last")) continue;
                System.out.println((list1.get(j).getName()));
                son = son.concat(list1.get(j).getName());
            }
            System.out.println("son"+son);
            industrialCategory.setCode(son);
            iIndustrialCategoryService.updateById(industrialCategory);
        }
    }

    public  List<String> getTagsChanYe(BusinessData businessData,List<IndustroyTupu> list) {
        List<String> tags = new ArrayList<>();
        String scope = businessData.getBusinessScope();
        List<String> listScope = fenci(scope);
        double[] vector0 = AverageArgs(listScope);
        //一级产业
        String tag1 = "航空器制造";
        Integer id1 = 1;
        double max = 0;
        //遍历一级产业，用该产业的名字和儿子的名字作为信息
        for (int i = 0; i < list.size(); i++) {
            String son = list.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag1 = list.get(i).getName();
                id1 = list.get(i).getId();
            }
        }
        tags.add(tag1);
        //二级产业
        QueryWrapper<IndustroyTupu> query2 = new QueryWrapper<>();
        query2.eq("parent_id", id1);
        List<IndustroyTupu> list2 = industroyTupuService.list(query2);
        String tag2 = null;
        Integer id2 = null;
        max = 0;
        for (int i = 0; i < list2.size(); i++) {
            String son = list2.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag2 = list2.get(i).getName();
                id2 = list2.get(i).getId();
            }
        }
        tags.add(tag2);
        //三级产业
        QueryWrapper<IndustroyTupu> query3 = new QueryWrapper<>();
        query3.eq("parent_id", id2);
        List<IndustroyTupu> list3 = industroyTupuService.list(query3);
        String tag3 = "";
        Integer id3 = null;
        max = 0;
        for (int i = 0; i < list3.size(); i++) {
            String son = list3.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag3 = list3.get(i).getName();
                id3 = list3.get(i).getId();
            }
            if (tag3 == son) {
                tags.add(tag3);
                return tags;
            }
        }
        tags.add(tag3);
        //四级产业
        QueryWrapper<IndustroyTupu> query4 = new QueryWrapper<>();
        query4.eq("parent_id", id3);
        List<IndustroyTupu> list4 = industroyTupuService.list(query4);
        String tag4 = "";
        Integer id4 = null;
        max = 0;
        for (int i = 0; i < list4.size(); i++) {
            String son = list4.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag4 = list4.get(i).getName();
                id4 = list4.get(i).getId();
            }
            if (tag4 == son) {
                tags.add(tag4);
                return tags;
            }
        }
        tags.add(tag4);

//        //5级产业
//        QueryWrapper<IndustroyTupu> query5 = new QueryWrapper<>();
//        query5.eq("parent_id", id4);
//        List<IndustroyTupu> list5 = industroyTupuService.list(query5);
//        String tag5 = "";
//        Integer id5 = null;
//        max = 0;
//        for (int i = 0; i < list5.size(); i++) {
//            String son = list5.get(i).getCode();
//            double[] vector1 = AverageArgs(JieBa.fenci(son));
//            double similarity = Similarity(vector0, vector1);
//            if (max < similarity) {
//                max = similarity;
//                tag5 = list5.get(i).getName();
//                id5 = list5.get(i).getId();
//            }
//            if (tag5 == son) {
//                tags.add(tag5);
//                return tags;
//            }
//        }
//        tags.add(tag5);

        return tags;
    }

    public List<String> getTagsHangYe(BusinessData businessData,List<IndustrialCategory> list) {
        List<String> tags = new ArrayList<>();
        String scope = businessData.getBusinessScope();
        List<String> listScope = fenci(scope);
        double[] vector0 = AverageArgs(listScope);
        //一级产业
        String tag1 = "装备制造业";
        Integer id1 = 4796;
        double max = 0;
        //遍历一级产业，用该产业的名字和儿子的名字作为信息
        for (int i = 0; i < list.size(); i++) {
            String son = list.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag1 = list.get(i).getName();
                id1 = list.get(i).getId();
            }
        }
        tags.add(tag1);
        //二级产业
        QueryWrapper<IndustrialCategory> query2 = new QueryWrapper<>();
        query2.eq("parent_id", id1);
        List<IndustrialCategory> list2 = iIndustrialCategoryService.list(query2);
        String tag2 = null;
        Integer id2 = null;
        max = 0;
        for (int i = 0; i < list2.size(); i++) {
            String son = list2.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag2 = list2.get(i).getName();
                id2 = list2.get(i).getId();
            }
        }
        tags.add(tag2);
        //三级产业
        QueryWrapper<IndustrialCategory> query3 = new QueryWrapper<>();
        query3.eq("parent_id", id2);
        List<IndustrialCategory> list3 = iIndustrialCategoryService.list(query3);
        String tag3 = "";
        Integer id3 = null;
        max = 0;
        for (int i = 0; i < list3.size(); i++) {
            String son = list3.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag3 = list3.get(i).getName();
                id3 = list3.get(i).getId();
            }
            if (tag3 == son) {
                tags.add(tag3);
                return tags;
            }
        }
        tags.add(tag3);
        //四级产业
        QueryWrapper<IndustrialCategory> query4 = new QueryWrapper<>();
        query4.eq("parent_id", id3);
        List<IndustrialCategory> list4 = iIndustrialCategoryService.list(query4);
        String tag4 = "";
        Integer id4 = null;
        max = 0;
        for (int i = 0; i < list4.size(); i++) {
            String son = list4.get(i).getCode();
            double[] vector1 = AverageArgs(fenci(son));
            double similarity = Similarity(vector0, vector1);
            if (max < similarity) {
                max = similarity;
                tag4 = list4.get(i).getName();
                id4 = list4.get(i).getId();
            }
            if (tag4 == son) {
                tags.add(tag4);
                return tags;
            }
        }
        tags.add(tag4);

//        //5级产业
//        QueryWrapper<IndustroyTupu> query5 = new QueryWrapper<>();
//        query5.eq("parent_id", id4);
//        List<IndustroyTupu> list5 = industroyTupuService.list(query5);
//        String tag5 = "";
//        Integer id5 = null;
//        max = 0;
//        for (int i = 0; i < list5.size(); i++) {
//            String son = list5.get(i).getCode();
//            double[] vector1 = AverageArgs(JieBa.fenci(son));
//            double similarity = Similarity(vector0, vector1);
//            if (max < similarity) {
//                max = similarity;
//                tag5 = list5.get(i).getName();
//                id5 = list5.get(i).getId();
//            }
//            if (tag5 == son) {
//                tags.add(tag5);
//                return tags;
//            }
//        }
//        tags.add(tag5);

        return tags;
    }
    public String hello(String s){
        return s;
    }
    public   List<String>  fenci(String s){
       JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        String s1 =  CharacterUtil.dealwithCharacter(s);
        List<String> list = jiebaSegmenter.sentenceProcess(s1);
        return list;
    }



//    }
//    public List<String> getTag(BusinessData businessData) {
//        //String scope = businessData.getBusinessScope();
//        //JieBa jieBa = new JieBa();
//        List<String> tags = new ArrayList<>();
//        List<String> listScope = JieBa.fenci(businessData.getIndustory());
//        double[] vector0 = AverageArgs(listScope);
//        //一级产业
//        QueryWrapper<IndustrialCategory> query1 = new QueryWrapper<>();
//        query1.eq("parent_id", -1);
//        //query1.select(IndustrialCategory.NAME,IndustrialCategory.CODE);
////        query.select(IndustrialCategory.NAME);
//        List<IndustrialCategory> list = iIndustrialCategoryService.list(query1);
//        //List<String> indus = list.stream().map().collect(Collectors.toList());
//        String tag1 = null;
//        Integer id1 = null;
//        double max = 0;
//        for (int i = 0; i < list.size(); i++) {
//            double[] vector1 = AverageArgs(JieBa.fenci(list.get(i).getCode()));
//            double similarity = Similarity(vector0, vector1);
//            if (max < similarity) {
//                max = similarity;
//                tag1 = list.get(i).getName();
//                id1 = list.get(i).getId();
//            }
//        }
//        max = 0;
//        if (tag1 == null) {
//            for (int i = 0; i < list.size(); i++) {
//                double[] vector1 = AverageArgs(JieBa.fenci(list.get(i).getCode()));
//                double[] vector5 = AverageArgs(JieBa.fenci(list.get(i).getName()));
//                double similarity = Similarity(vector5, vector1);
//                if (max < similarity) {
//                    max = similarity;
//                    tag1 = list.get(i).getName();
//                    id1 = list.get(i).getId();
//                }
//            }
//        }
//        tags.add(tag1);
//        double[] vector2 = AverageArgs(JieBa.fenci(businessData.getBusinessScope()));
//        //二级产业
//        QueryWrapper<IndustrialCategory> query2 = new QueryWrapper<>();
//        query2.eq("parent_id", id1);
//        //query.select(IndustrialCategory.NAME,IndustrialCategory.CODE);
////        query.select(IndustrialCategory.NAME);
//        List<IndustrialCategory> list2 = iIndustrialCategoryService.list(query2);
//        //List<String> indus = list.stream().map(IndustrialCategory::getName).collect(Collectors.toList());
//        String tag2 = null;
//        Integer id2 = null;
//        max = 0;
//        for (int i = 0; i < list2.size(); i++) {
//            QueryWrapper<IndustrialCategory> temp = new QueryWrapper<>();
//            temp.eq("parent_id", list2.get(i).getId());
//            List<IndustrialCategory> getson = iIndustrialCategoryService.list(temp);
//            String son = list2.get(i).getName();
//            for (int j = 0; j < getson.size(); j++) {
//                son = son.concat(getson.get(j).getName());
//                double[] vector1 = AverageArgs(JieBa.fenci(son));
//                double similarity = Similarity(vector2, vector1);
//                if (max < similarity) {
//                    max = similarity;
//                    tag2 = list2.get(i).getName();
//                    id2 = list2.get(i).getId();
//                }
//            }
//        }
//        tags.add(tag2);
//
//
//        //三级产业
//        QueryWrapper<IndustrialCategory> query3 = new QueryWrapper<>();
//        query3.eq("parent_id", id2);
//        //query.select(IndustrialCategory.NAME,IndustrialCategory.CODE);
////        query.select(IndustrialCategory.NAME);
//        List<IndustrialCategory> list3 = iIndustrialCategoryService.list(query3);
//        //List<String> indus = list.stream().map(IndustrialCategory::getName).collect(Collectors.toList());
//        String tag3 = null;
//        Integer id3 = null;
//        max = 0;
//        for (int i = 0; i < list3.size(); i++) {
//            QueryWrapper<IndustrialCategory> temp = new QueryWrapper<>();
//            temp.eq("parent_id", list3.get(i).getId());
//            List<IndustrialCategory> getson = iIndustrialCategoryService.list(temp);
//            String son = list3.get(i).getName();
//            for (int j = 0; j < getson.size(); j++) {
//                son = son.concat(getson.get(j).getName());
//                double[] vector1 = AverageArgs(JieBa.fenci(son));
//                double similarity = Similarity(vector2, vector1);
//                if (max < similarity) {
//                    max = similarity;
//                    tag3 = list3.get(i).getName();
//                    id3 = list3.get(i).getId();
//                }
//            }
//        }
//            tags.add(tag3);
//            //四级产业
//
//            QueryWrapper<IndustrialCategory> query4 = new QueryWrapper<>();
//            query4.eq("parent_id", id3);
//            //query.select(IndustrialCategory.NAME,IndustrialCategory.CODE);
////        query.select(IndustrialCategory.NAME);
//            List<IndustrialCategory> list4 = iIndustrialCategoryService.list(query4);
//            //List<String> indus = list.stream().map(IndustrialCategory::getName).collect(Collectors.toList());
//            String tag4 = null;
//            Integer id4 = null;
//            max = 0;
//
////            QueryWrapper<IndustrialCategory> temp = new QueryWrapper<>();
////            temp.eq("parent_id",list3.get(i).getId());
////            List<IndustrialCategory> getson = iIndustrialCategoryService.list(temp);
////            String son = list3.get(i).getName();
////            for(int j = 0;j<getson.size();j++){
////               son= son.concat(getson.get(j).getName());
////            }
//            Map<Double, String> map = new TreeMap<Double, String>();
//            for (int i = 0; i < list4.size(); i++) {
//                double[] vector1 = AverageArgs(JieBa.fenci(list4.get(i).getName()));
//                double similarity = Similarity(vector1, vector2);
//                map.put(similarity, list4.get(i).getName());
//
//            }
//            Set<Double> doubles = map.keySet();
//            List t = new ArrayList(doubles);
//            if (t.size() < 4) {
//                for (int j = t.size() - 1; j > -1; j--) {
//                    if (map.containsKey(t.get(j))) {
//                        tags.add(map.get(t.get(j)));
//                    }
//                }
//            } else {
//                for (int j = t.size() - 1; j > t.size() - 4; j--) {
//                    if (map.containsKey(t.get(j))) {
//                        tags.add(map.get(t.get(j)));
//                    }
//                }
//            }
//            return tags;
//        }
////
}
