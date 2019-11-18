package com.dream.city.job.thread;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池工具类
 * 
 * @author Nevyn
 *
 */
public final class ThreadPoolUtil {
	public static final String MODULE_TRANSACTION_SEND = "transaction_send";
	public static final String MODULE_MESSAGE_RESEND = "message_resend";
	private static ConcurrentHashMap<String, ExecutorService> executorMap = new ConcurrentHashMap<String, ExecutorService>();
	public static final Integer poolCount = 1;
	private ThreadPoolUtil() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				for (Entry<String, ExecutorService> entry : executorMap.entrySet()) {
					ExecutorService service = entry.getValue();
					if (service != null) {
						service.shutdown();
					}
				}

			}
		});
	}
	public static ExecutorService createExecutor(int poolSize, String module) {
		String threadKey = module + poolSize;
		ExecutorService service = executorMap.get(threadKey);
		if (service == null) {
			ExecutorService newservice = Executors.newFixedThreadPool(poolSize);
			service = executorMap.putIfAbsent(threadKey, newservice);
			if (service == null) {
				service = newservice;
			}
		}
		if (service instanceof ThreadPoolExecutor) {
			ThreadPoolExecutor exc = (ThreadPoolExecutor) service;
			exc.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		}
		return service;
	}

	public static void submit(int poolSize, String module, Runnable task) {
		ExecutorService service = createExecutor(poolSize, module);
		service.submit(task);
	}
}
