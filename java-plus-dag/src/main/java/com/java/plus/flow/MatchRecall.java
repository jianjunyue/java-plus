package com.java.plus.flow;
 
import org.springframework.stereotype.Service;

import com.java.plus.dag.IProcessor;
import com.java.plus.dag.ProcessorContext;

@Service
public class MatchRecall implements IProcessor {

	@Override
	public void execute(ProcessorContext context) {
		context.getItemInfoList().add(String.valueOf(context.getItemInfoList().size()));
 
	}

}
