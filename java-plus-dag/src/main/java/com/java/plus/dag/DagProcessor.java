package com.java.plus.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

public class DagProcessor {
	private final static ExecutorService executorPool = new ThreadPoolExecutor(50, 500, 5, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(1000));

	private int maxTimeout = 200;

	public void run(ProcessorContext context) {
		try {
			List<CompletableFuture> completableFutures = Lists.newArrayList();

			List<String> asyncStageSet = new ArrayList<>();
			List<String> noAsyncStageSet = new ArrayList<>();

			context.getStageSet().stream().forEach(id -> {
				boolean async = DagManager.getVertexs().get(id).isAsync();
				if (async) {
					asyncStageSet.add(id);
				} else {
					noAsyncStageSet.add(id);
				}
			});

			asyncStageSet.forEach(id -> {
				ProcessorNode node = DagManager.getVertexs().get(id);
				CompletableFuture<Void> asyncFuture = processor(node, context);
				completableFutures.add(asyncFuture);
				maxTimeout = node.getTimeout() > maxTimeout ? node.getTimeout() : maxTimeout;
			});

			noAsyncStageSet.forEach(id -> {
				ProcessorNode vertex = DagManager.getVertexs().get(id);
				vertex.getProcessor().execute(context);
			});

			maxTimeout = maxTimeout <= 0 ? 200 : maxTimeout;
			CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]))
					.get(maxTimeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private CompletableFuture<Void> processor(ProcessorNode node, ProcessorContext context) {
		CompletableFuture<Void> asyncFuture = CompletableFuture.runAsync(() -> node.getProcessor().execute(context),
				executorPool);
		return asyncFuture;
	}
}
