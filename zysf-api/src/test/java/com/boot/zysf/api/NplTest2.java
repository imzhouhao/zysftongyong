//package com.boot.zysf.api;
//
//import com.boot.zysf.api.util.JieBa;
//import com.boot.zysf.api.util.Tagging;
//import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
//import org.deeplearning4j.models.word2vec.Word2Vec;
//import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
//import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
//import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
//import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
//import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
//import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
////"F:\项目\哈尔滨项目\Tencent_AILab_ChineseEmbedding\ChineseEmbedding.bin"
//
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//public class NplTest2 {
//    @Autowired
//    private Tagging tagging;
//
//    @Test
//    public void test1(){
//        List<String> list0 = JieBa.fenci("今天天气还行啊挺暖和的");
//        List<String> list1 = JieBa.fenci("今天不冷");
//        System.out.println("相似度为:"+
//                tagging.Similarity(
//                        tagging.AverageArgs(list0),
//                        tagging.AverageArgs(list1)
//                ));
//    }
//}
