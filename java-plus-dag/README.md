# DAG:https://github.com/smartxing/algorithm/tree/master/src/main/java/com/algorithm/xlb/algorithm/dag


1、Node节点设计
String id;
boolean async;
int timeout;
IProcessor processor; ---->	void execute(ProcessorContext context);

2、Node节点管理DagManager
Map<String, ProcessorNode> vertexMap；所有节点情况
Map<String, Set<String>> inDegree；节点分别对应的父节点
Map<String, Set<String>> outDegree；节点分别对应的子节点

3、DAG执行调度引擎DagEngine
（1）遍历所有节点的父节点数量（inDegree）
（2）父节点数量为0的节点，即为入口节点
（3）执行入口节点IProcessor 逻辑
（4）遍历入口节点的子节点，入口节点的子节点的父节点数量都减1（入口节点已经执行了，它的子节点中待执行父节点数量就减少了1）
（5）获取新父节点数量为0的节点，递归执行（2）
