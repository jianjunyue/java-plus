package com.java.plus.dag;
  
public class Test {
  
	public static void main(String[] args) {  
		 
		String processorName = "com.java.plus.flow.MatchRecall";
		String processorName2 = "com.java.plus.flow.ItemFilter";

		boolean async1 = true;
		boolean async2 = true;
		int timeout = 10;
		int timeout2 = 200;

		ProcessorNode a = new ProcessorNode("a", "111-a", async1, timeout, processorName);
		ProcessorNode b = new ProcessorNode("b", "222-b", async1, timeout, processorName);
		ProcessorNode c = new ProcessorNode("c", "333-c", async1, timeout, processorName2);
		ProcessorNode d = new ProcessorNode("d", "444-d", async1, timeout, processorName);
		ProcessorNode e = new ProcessorNode("e", "555-e", async2, timeout, processorName2);
		ProcessorNode f = new ProcessorNode("f", "666-f", async2, timeout2, processorName); 
		DagManager.addEdge(a, b);
		DagManager.addEdge(a, c);
		DagManager.addEdge(a, d);
		DagManager.addEdge(b, f);
		DagManager.addEdge(c, e);
		DagManager.addEdge(d, e);
		DagManager.addEdge(e, f);

		System.out.println(DagManager.toDagString());
		ProcessorContext context = new ProcessorContext();
		try {
			DagEngine dagEngine = new DagEngine();
			dagEngine.execute(context);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("-------------Test-------------");
		context.getItemInfoList().stream().forEach(action -> {

			System.out.println("-------------" + action + "-------------");
		});
		System.out.println(context.getItemInfoList().size());
	}

}
