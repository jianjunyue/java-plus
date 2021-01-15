package com.java.plus.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Data; 

@Data
public class ProcessorContext {
	
	private List<String> itemInfoList=new ArrayList<>(); // 多路召回列表 
	private Set<String> stageSet; // 每阶段并行或串行执行Node的策略 IProcessor

}
