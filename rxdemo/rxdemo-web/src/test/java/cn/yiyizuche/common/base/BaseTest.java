package cn.yiyizuche.common.base;

import java.util.Random;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")

public class BaseTest {
	
	public int random(int min, int max) {
		// 随机一个数
		int number = new Random().nextInt(max) % (max - min + 1) + min;
		return number;
	}
	

}
