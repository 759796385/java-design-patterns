package com.newtonk.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2017/11/8 0008
 */
@Slf4j
public class Producer {
    private String name;
    private MyQueue queue;
    private int id;

    public Producer(String name,MyQueue queue){
        this.id = id;
        this.name = name;
        this.queue = queue;
    }

    public void produce() throws Exception{
        Thread.sleep((long) (Math.random() * 1000));
        synchronized (queue.LOCK){
            while(queue.size() == queue.maxSize){
                queue.LOCK.wait();
            }
            int tempId = id++;
            Task task = new Task(tempId,"produce :"+name);
            queue.put(task);
            log.info("Producer [{}] already completed, task id is [{}]  ", name,tempId);
            queue.LOCK.notifyAll();
        }
    }
}
