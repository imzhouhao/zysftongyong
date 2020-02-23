package com.boot.zysf.api.mapper;

import com.boot.zysf.api.po.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IndustrialCategoryMapperTest {

	@Autowired
	private IndustrialCategoryMapper industrialCategoryMapper;

	@Test
	public void test1(){
		List<TreeNode> byParentId = industrialCategoryMapper.findByParentId(4796);
		System.out.println(byParentId);
	}

}
