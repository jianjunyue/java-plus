package com.java.plus.springboot;

import java.util.Arrays; 
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.java.plus.springboot.flow.iface.IWeighting;

@Service
public class ApplicationContextBean implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	public void getModuleConfiguration() {
		List<String> strategyList = Arrays.asList("itemWeight");

		List<IWeighting> strategyExecutors = buildRankStrategyList(strategyList, IWeighting.class);

		if (CollectionUtils.isNotEmpty(strategyExecutors)) {

			strategyExecutors.stream().forEach(strategy -> strategy.execute(""));
		}

	}

	@SuppressWarnings("unchecked")
	private <T> List<T> buildRankStrategyList(List<String> rankStrategyList, Class<T> clazz) {
		return rankStrategyList.stream().map(rs -> (T) applicationContext.getBean(rs))
				.filter(bean -> clazz.isAssignableFrom(bean.getClass())).collect(Collectors.toList());
	}

}
