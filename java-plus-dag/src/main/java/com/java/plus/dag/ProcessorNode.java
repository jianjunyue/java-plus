package com.java.plus.dag;
    
import lombok.Data;

@Data
public class ProcessorNode {

	private String id;
	private String name;
	private String processorName;
	private IProcessor processor;  
	private boolean async;
	private int timeout;//该processor执行超时时间，单位毫秒
	
	public ProcessorNode(String id,String name,boolean async,int timeout,String processorName) {
		this.id=id;
		this.name=name;
		this.processorName=processorName;
		this.async=async;
		this.timeout=timeout;

		setProcessor(this.processorName);
	}
	
	private void setProcessor(String processorName) {
		this.processorName = processorName;
		try {
			processor = reflectClass(processorName, IProcessor.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private <T> T reflectClass(String strategyClassName, Class<T> clazz) {
		try { 
			Class<?> classAvailable = ClassLoader.getSystemClassLoader().loadClass(strategyClassName);
			if (classAvailable != null) {
				T c = (T) Class.forName(strategyClassName).newInstance();
				return c;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
}
