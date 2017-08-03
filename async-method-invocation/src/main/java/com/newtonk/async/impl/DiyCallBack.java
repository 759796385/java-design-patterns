package com.newtonk.async.impl;

import com.newtonk.async.abstractclass.AsyncCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2017/8/3 0003
 */
public class DiyCallBack<T> implements AsyncCallback<T> {
    private static final Logger logger = LoggerFactory.getLogger(DiyCallBack.class);

    private String name ;

    public DiyCallBack(String name){
        this.name = name;
    }

    /**
     * Complete handler which is executed when async task is completed or fails execution.
     *
     * @param taskResult the evaluated value from async task, undefined when execution fails
     * @param ex    empty value if execution succeeds, some exception if executions fails
     */
    @Override
    public void onComplete(T taskResult, Optional<Exception> ex) {
        if(ex.isPresent()){
            doFail(ex.get());
        }else{
            doSuccess(taskResult);
        }
    }

    /**
     * 可设为抽象方法或子类重写
     */
    protected void doSuccess(T value){
        logger.info(name+"task do success :" + value);
    }

    /**
     * 可设为抽象方法或子类重写
     */
    protected void doFail(Exception ex){
        logger.info(name+"task do fail :" + ex.getMessage());
    }
}
