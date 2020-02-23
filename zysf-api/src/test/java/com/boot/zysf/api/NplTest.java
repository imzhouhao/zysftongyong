//package com.boot.zysf.api;
//
//import com.boot.zysf.api.util.JieBa;
//import com.boot.zysf.api.util.JsonUtil;
//import com.boot.zysf.api.util.Tagging;
//import org.deeplearning4j.models.embeddings.WeightLookupTable;
//import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
//import org.deeplearning4j.models.word2vec.Word2Vec;
//import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
//import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
//import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
//import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
//import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
//import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
//import org.junit.Test;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.bytedeco.javacpp.Loader;
//import org.nd4j.nativeblas.Nd4jCpu;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.FilteredClassLoader;
//import org.springframework.core.io.ClassPathResource;
////"F:\项目\哈尔滨项目\Tencent_AILab_ChineseEmbedding\ChineseEmbedding.bin"
//import java.io.*;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.logging.Logger;
//
//public class NplTest {
//    //private static Logger logger = LoggerFactory.getLogger(NplTest.class);
//    String path = "F:\\项目\\哈尔滨项目\\Tencent_AILab_ChineseEmbedding\\ChineseEmbedding.bin";
//    File gModel = new File(path);
//    Word2Vec vec = WordVectorSerializer.readWord2VecModel(gModel);
////    WeightLookupTable weightLookupTable = vec.lookupTable();
////    Iterator<INDArray> vectors = weightLookupTable.vectors();
////    INDArray wordVectorMatrix = vec.getWordVectorMatrix("制造业");
////    double[] wordVector = vec.getWordVector("制造业");
//    @Test
//    public void test1(){
//        Tagging tagging = new Tagging();
//        List<String> list0 = JieBa.fenci("今天天气还行啊，挺暖和的");
//        List<String> list1 = JieBa.fenci("今天不冷");
//        System.out.println(tagging.Similarity(tagging.AverageArgs(list0),tagging.AverageArgs(list1)));
//    }
//    @Test
//    public  void test2(){
//        double cossim = vec.similarity("老鼠","耗子");
//        System.out.println(cossim);
//    }
//    @Test
//    public  void test3() throws IOException {
//        List<String> fenci = JieBa.fenci("电力设备和器材的生产、销售、代理;销售:建筑材料、电子产品、通讯设备、化工产品(不含危险化学品)、摩托车、汽车配件、仪器仪表、动力机械设备;电力技术咨询;环保工程的代理。(依法须经批准的项目,经相关部门批准后方可开展经营活动)");
//        System.out.println(JieBa.RemovalOfStopWords(fenci));
//    }
//}
