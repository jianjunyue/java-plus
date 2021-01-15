package com.java.plus.dag;

import java.util.HashSet; 
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors; 
import org.apache.commons.collections4.CollectionUtils; 

public class DagEngine {
	private ProcessorContext context;

	public void execute(ProcessorContext context) {
		this.context = context;
		Map<String, Integer> inDegreeIntMap = getIntegerMap(DagManager.getInDegree());
		// 入度为0的节点
		Set<String> sources = computeRootEdgeVertices(DagManager.getInDegree());
		if(sources.size()==0) {
			return;
		}
		execute_(sources, inDegreeIntMap, DagManager.getOutDegree());
	}

	private Map<String, Integer> getIntegerMap(Map<String, Set<String>> inDegree) {
		Set<String> set = inDegree.keySet();
		// 入度表
		return set.stream().collect(Collectors.toMap(k -> k, k -> inDegree.get(k).size()));
	}

	// 入度为0的节点
	private Set<String> computeRootEdgeVertices(Map<String, Set<String>> inDegree) {
		Set<String> candidates = inDegree.keySet();
		Set<String> roots = new HashSet<>();
		for(String id :candidates) {
			if (inDegree.get(id).isEmpty()) {
				roots.add(id);
			}
		}
		// roots跟节点应该只有一个值
		return roots;
	}

	private void execute_(Set<String> set, Map<String, Integer> inDegreeIntMap, Map<String, Set<String>> outDegree) {
		exec(set);
		Set<String> nextSet =new HashSet<>();
		set.forEach(o -> {
			outDegree.get(o).forEach(so -> {
				inDegreeIntMap.put(so, inDegreeIntMap.get(so) - 1);
				if (inDegreeIntMap.get(so) == 0) {
					nextSet.add(so);
				}
			});
		});
		if (CollectionUtils.isNotEmpty(nextSet)) {
			execute_(nextSet, inDegreeIntMap, outDegree);
		}
	}

	private void exec(Set<String> stageSet) {
		context.setStageSet(stageSet);
		DagProcessor dagProcessor = new DagProcessor();
		dagProcessor.run(context);
	}
}
