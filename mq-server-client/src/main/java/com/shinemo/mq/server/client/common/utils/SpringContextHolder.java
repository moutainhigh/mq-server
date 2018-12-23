package com.shinemo.mq.server.client.common.utils;

import static org.apache.commons.lang.Validate.notEmpty;
import static org.apache.commons.lang.Validate.notNull;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;


public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringContextHolder.context = context;
	}

	public static Object getSpringBean(String beanName) {
		notEmpty(beanName, "bean name is required");
		return context == null ? null : context.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return context.getBean(beanName, clazz);
	}

	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	public static String[] getBeanDefinitionNames() {
		return context.getBeanDefinitionNames();
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		return context.getBeansOfType(type);
	}
	
	
	public static <V> V getBeanAndGenerateIfNotExist(String beanName, 
			Class<V> clazz, BeanDefinitionBuilder beanDefinitionBuilder) {
		notEmpty(beanName, "bean name is required");
		notNull(clazz, "clazz is required");
		if (context == null) {
			return null;
		}
		if (context.containsBean(beanName)) {
			return context.getBean(beanName, clazz);
		}
		synchronized (SpringContextHolder.class) {
			if (context.containsBean(beanName)) {
				return context.getBean(beanName, clazz);
			}
			ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
			DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext
					.getBeanFactory();
			defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
			return context.getBean(beanName, clazz);
		}
	}

	
	

}