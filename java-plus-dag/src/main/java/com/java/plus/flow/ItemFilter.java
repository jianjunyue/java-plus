package com.java.plus.flow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.plus.dag.IProcessor;
import com.java.plus.dag.ProcessorContext;

@Service
public class ItemFilter implements IProcessor {

	@Override
	public void execute(ProcessorContext context) {
		List<String> itemInfoList = new ArrayList<>(); 
		
		for (int i = 0; i < context.getItemInfoList().size(); i++) {
			if (i % 2 == 0) {
				itemInfoList.add("emp");
			} else {

				itemInfoList.add(context.getItemInfoList().get(i));
			}
		}
		context.setItemInfoList(itemInfoList);

	}

}
