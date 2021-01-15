package com.java.plus.dag;

import java.util.HashMap; 
import java.util.Map;
import java.util.Set;
 
import com.google.common.collect.Sets;
  
public class DagManager {
	
	private static Map<String, ProcessorNode> vertexMap = new HashMap<String, ProcessorNode>();
	private static Map<String, Set<String>> inDegree = new HashMap<String, Set<String>>();
	private static Map<String, Set<String>> outDegree = new HashMap<String, Set<String>>();
	
	public static Map<String,ProcessorNode> getVertexs(){
		return vertexMap;
	}	

	public static Map<String, Set<String>> getInDegree(){
		return inDegree;
	}
	
	public static Map<String, Set<String>> getOutDegree(){
		return outDegree;
	}


	public static boolean addEdge(ProcessorNode from_Vertex, ProcessorNode to_Vertex) {
		if (hasPath(to_Vertex, from_Vertex)) {
			return false;
		}
		addVertex(from_Vertex);
		addVertex(to_Vertex);
		inPut(from_Vertex, to_Vertex);
		outPut(from_Vertex, to_Vertex);

		vertexMap.put(from_Vertex.getId(), from_Vertex);
		vertexMap.put(to_Vertex.getId(), to_Vertex);
		return true;
	}
	 
	private static boolean hasPath(Object start, Object end) {
		if (start == end) {
			return true;
		}
		Set<String> children = outDegree.get(start);
		if (children != null) {
			for(String id : children) {
				if (hasPath(id, end)) {
					return true;
				}
			} 
		}
		return false;
	}

	public static void inPut(ProcessorNode from_Vertex, ProcessorNode to_Vertex) {
		Set<String> values = inDegree.get(to_Vertex.getId());
		if (values == null) {
			values = Sets.newLinkedHashSet();
		}
		values.add(from_Vertex.getId());
		inDegree.put(to_Vertex.getId(), values);
	}

	public static void outPut(ProcessorNode from_Vertex, ProcessorNode to_Vertex) {
		Set<String> values = outDegree.get(from_Vertex.getId());
		if (values == null) {
			values = Sets.newLinkedHashSet();
		}
		values.add(to_Vertex.getId());
		outDegree.put(from_Vertex.getId(), values);
	}
	 
	public static String toDagString() {
		return "OutDegree: " + outDegree.toString() + " InDegree: " + inDegree.toString();
	}
	
	public static void addVertex(ProcessorNode vertex) {
		if (!inDegree.containsKey(vertex.getId())) {
			inDegree.put(vertex.getId(), Sets.newLinkedHashSet());
		}
		if (!outDegree.containsKey(vertex.getId())) {
			outDegree.put(vertex.getId(), Sets.newLinkedHashSet());
		}
		vertexMap.put(vertex.getId(), vertex);
	}
 
}
