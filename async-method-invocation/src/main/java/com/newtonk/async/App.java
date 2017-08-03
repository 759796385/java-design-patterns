package com.newtonk.async;

import com.newtonk.async.abstractclass.AsyncResult;
import com.newtonk.async.impl.DiyCallBack;
import com.newtonk.async.impl.DiyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2017/8/3 0003
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) throws Exception {

        // 构造一个异步任务执行器
        ThreadAsyncExecutor executor = new ThreadAsyncExecutor();

        // start few async tasks with varying processing times, two last with callback handlers
        AsyncResult<Integer> asyncResult1 = executor.startProcess(new DiyTask<Integer>(10, 500));
        AsyncResult<String> asyncResult2 = executor.startProcess(new DiyTask<String>("test", 300));
        AsyncResult<Long> asyncResult3 = executor.startProcess(new DiyTask<Long>(50L, 700));
        AsyncResult<Integer> asyncResult4 = executor.startProcess(new DiyTask<Integer>(20, 400), new DiyCallBack<Integer>("Callback result 4"));
        AsyncResult<String> asyncResult5 = executor.startProcess(new DiyTask<String>("callback", 600), new DiyCallBack<String>("Callback result 5"));

        // emulate processing in the current thread while async tasks are running in their own threads
        Thread.sleep(350); // Oh boy I'm working hard here
        log("Some hard work done");

        // wait for completion of the tasks
        Integer result1 = executor.endProcess(asyncResult1);
        String result2 = executor.endProcess(asyncResult2);
        Long result3 = executor.endProcess(asyncResult3);
        asyncResult4.await();
        asyncResult5.await();

        // log the results of the tasks, callbacks log immediately when complete
        log("Result 1: " + result1);
        log("Result 2: " + result2);
        log("Result 3: " + result3);
        executor.shutdown();
    }

    private static void log(String msg) {
        LOGGER.info(msg);
    }
}
