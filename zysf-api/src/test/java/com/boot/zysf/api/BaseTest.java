package com.boot.zysf.api;

import com.boot.zysf.api.Runner.TagRunner;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.Model.CommonModel;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.IndustroyTupuService;
import com.boot.zysf.api.util.Tagging;
import com.boot.zysf.api.util.ThreadTag1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	BusinessDataService businessDataService;
	@Autowired
	IndustroyTupuService industroyTupuService;
	@Autowired
	TagRunner tagRunner;
     @Test
	 public void test3(){
		 String empName = "深圳市亚风快运股份有限公司";
		 String url1 = "http://47.92.240.36/academic/api/v1/s?containsVerify=true&content="+empName+"&num=10&page=0&type=4";
		 CommonModel forObject1 = restTemplate.getForObject(url1, CommonModel.class);
		 Object data = forObject1.getData();
		 List list = (List) data;
		 System.out.println(list);
		 if(list!=null&&list.size()>0) {
			 Map map = (Map) list.get(0);
			 System.out.println(map);
			 String id =  map.get("id").toString();

		 }

	 }
	 @Test
	public void test4() {
	 }

}
