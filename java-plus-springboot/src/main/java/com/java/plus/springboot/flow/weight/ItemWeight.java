package com.java.plus.springboot.flow.weight;

import org.springframework.stereotype.Service;

import com.java.plus.springboot.flow.iface.IWeighting;

@Service
public class ItemWeight implements IWeighting {

	@Override
	public void execute(String rankContext) {
		 System.out.println("-------------------ItemWeight----------------------");
		
	}

}
