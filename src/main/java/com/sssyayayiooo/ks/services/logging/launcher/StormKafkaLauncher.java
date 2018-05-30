package com.sssyayayiooo.ks.services.logging.launcher;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Leezer
 * @version 1.0
 **/
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.sssyayayiooo.ks.services.logging")
@PropertySource(value = { "classpath:application.properties","classpath:storm.properties"})
public class StormKafkaLauncher  extends SpringBootServletInitializer {

	/**
	 * 设置 安全线程launcher实例
	 **/
	private volatile static StormKafkaLauncher stormKafkaLauncher;
	/**
	 * 设置上下文
	 **/
	private ApplicationContext context;


	public static void main(String[] args) {
		SpringApplicationBuilder application = new SpringApplicationBuilder(StormKafkaLauncher.class);
		application.run(args);
		StormKafkaLauncher s = new StormKafkaLauncher();
		s.setApplicationContext(application.context());
		setStormLauncher(s);
	}

	private static void setStormLauncher(StormKafkaLauncher stormKafkaLauncher) {
		StormKafkaLauncher.stormKafkaLauncher = stormKafkaLauncher;
	}
	public static StormKafkaLauncher getStormLauncher() {
		return stormKafkaLauncher;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StormKafkaLauncher.class);
	}


	/**
	 * 获取上下文
	 *
	 * @return the application context
	 */
	public ApplicationContext getApplicationContext() {
		return context;
	}

	/**
	 * 设置上下文.
	 *
	 * @param appContext 上下文
	 */
	private void setApplicationContext(ApplicationContext appContext) {
		this.context = appContext;
	}

	/**
	 * 通过自定义name获取 实例 Bean.
	 *
	 * @param name the name
	 * @return the bean
	 */
	public Object getBean(String name) {
		return context.getBean(name);
	}

	/**
	 * 通过class获取Bean.
	 *
	 * @param <T>   the type parameter
	 * @param clazz the clazz
	 * @return the bean
	 */
	public <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	/**
	 * 通过name,以及Clazz返回指定的Bean
	 *
	 * @param <T>   the type parameter
	 * @param name  the name
	 * @param clazz the clazz
	 * @return the bean
	 */
	public <T> T getBean(String name, Class<T> clazz) {
		return context.getBean(name, clazz);
	}

}
