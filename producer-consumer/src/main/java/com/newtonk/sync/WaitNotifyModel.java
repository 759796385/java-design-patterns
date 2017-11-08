package com.newtonk.sync;

/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2017/11/8 0008
 */
public class WaitNotifyModel {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(3);

        for (int i = 0; i < 1; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Producer producer = new Producer("Producer"+ finalI,myQueue);
                    try {
                        producer.produce();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Consumer consumer = new Consumer("Consumer"+ finalI,myQueue);
                    try {
                        consumer.consume();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
