package com.newtonk.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2017/11/8 0008
 */
@Slf4j
public class Consumer {
    private String name;
    private MyQueue queue ;
    private int id ;


    public Consumer(String name,MyQueue queue){
        this.name = name;
        this.queue = queue;
    }

    public void consume() throws Exception{
        synchronized (queue.getLOCK()){
            while(queue.size() == 0){
                queue.LOCK.wait();
            }
            Thread.sleep(500 + (long) (Math.random() * 500));
            Task task  = queue.take();
            log.info(" Consumer [{}] has task,the task from [{}],id is {} ",name,task.getName(),task.getId());
            queue.LOCK.notifyAll();
        }

    }
}
