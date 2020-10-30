package com.ruida.springbootdemo;

import com.ruida.springbootdemo.annotation.MyAnnotation;
import com.ruida.springbootdemo.entity.bean.TestBean;
import com.ruida.springbootdemo.config.ApplicationContextConfig;
import com.ruida.springbootdemo.service.Vehicle;
import com.ruida.springbootdemo.service.impl.TestNotNullService;
import com.ruida.springbootdemo.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootDemoApplication.class)
public class SpringBootDemoApplicationTests {

	@Resource
	UserServiceImpl userService;
	@Resource
	ApplicationContext applicationContext;
	@Resource
	TestNotNullService testNotNullService;

/*	@Test
	void contextLoads() {
	}*/

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		TestBean tb = (TestBean) context.getBean("testBean");
		System.out.println(tb);
		tb.say();

		TestBean tb2 = (TestBean) context.getBean("testBean");
		System.out.println(tb2);
	}

	@Test
	public void getUserById(){
		userService.selectUserById(1);
	}


	/**
	 * Usages:
	 *
	 * Reflections reflections = new Reflections("my.project");
	 *
	 * Set<Class<? extends SomeType>> subTypes = reflections.getSubTypesOf(SomeType.class);
	 *
	 * Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SomeAnnotation.class);
	 */
	@Test
	public void testReflections(){
		//System.out.println("---"+applicationContext);
		Reflections reflections = new Reflections();
		Set<Class<? extends Vehicle>> subTypes = reflections.getSubTypesOf(Vehicle.class);
		for(Class<?> clazz : subTypes){
			System.out.println("========="+clazz.getName());
			System.out.println("========="+clazz.getSimpleName());
			Vehicle vehicle = (Vehicle) applicationContext.getBean(StringUtils.uncapitalize(clazz.getSimpleName()));
			vehicle.start();
		}

		System.out.println("=========================getTypesAnnotatedWith=========================");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MyAnnotation.class);
		for(Class<?> clazz : annotated){
			System.out.println("========="+clazz.getSimpleName());
		}
	}

	@Test
	public void testNotNull(){

		//正常输出
		testNotNullService.show(0,"zhangsan");

		//报错
		testNotNullService.show(0,null);
	}
}
