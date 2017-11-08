package com.newtonk.sync;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2017/11/8 0008
 */
@Getter
public class MyQueue {
    private  Queue<Task> queue = new LinkedList<>();
    public final Object LOCK = new Object();
    public  int maxSize;
    public MyQueue(int maxSize){
        this.maxSize = maxSize;
    }

    public  void put(Task task){
        queue.add(task);
    }

    public Task take(){
        return queue.poll();
    }

    public int size(){
        return queue.size();
    }

}
