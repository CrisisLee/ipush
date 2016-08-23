package ipush.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 一个工厂单例，用于通过名字获取bean
 * 主要用于并发环境中获取bean（spring无法向线程注入bean）
 * @author arlabsurface
 *
 */
@Component("beanFactoryUtil")
public class BeanFactoryUtil implements BeanFactoryAware {

	private static BeanFactory beanFactory = null;
	private static BeanFactoryUtil factoryUtil = null;
	
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		BeanFactoryUtil.beanFactory = beanFactory;
	}
	
	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	/**
	 * 获取该单例类的唯一实例
	 * @return 该类的实例
	 */
	public static BeanFactoryUtil getInstance() {
		if (factoryUtil == null) {
			synchronized (factoryUtil) {
				if(factoryUtil == null)
					factoryUtil = new BeanFactoryUtil();
			}
		}
		return factoryUtil;
	}
	/**
	 * 通过bean的名称获得该bean
	 * @param name bean的名称
	 * @return	bean实例
	 */
	public static Object getBean(String name) {
		return beanFactory.getBean(name);
	}

}
