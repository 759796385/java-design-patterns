package com.newtonk.async.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2017/8/3 0003
 */
public class DiyTask<T> implements Callable<T> {
    private static final Logger logger = LoggerFactory.getLogger(DiyTask.class);

    private T value;
    private int time;

    public DiyTask(T value ,int time){
        this.value = value;
        this.time = time;
    }
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public T call() throws Exception {
        Thread.sleep(time);
        logger.info("Task completed with: " + value);
        return value;
    }
}
