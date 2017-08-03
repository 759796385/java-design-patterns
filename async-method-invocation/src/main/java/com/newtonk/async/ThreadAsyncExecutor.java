/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.newtonk.async;


import com.newtonk.async.abstractclass.AsyncCallback;
import com.newtonk.async.abstractclass.AsyncExecutor;
import com.newtonk.async.abstractclass.AsyncResult;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Implementation of async executor that creates a new thread for every task.
 * 
 */
public class ThreadAsyncExecutor implements AsyncExecutor {

  private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


  @Override
  public <T> AsyncResult<T> startProcess(Callable<T> task) {
    return startProcess(task, null);
  }

  @Override
  public <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback) {
    CompletableResult<T> result = new CompletableResult<>(callback);
      executor.execute(() -> {
          try {
              result.setValue(task.call());
          } catch (Exception ex) {
              result.setException(ex);
          }
      });
    return result;
  }

  @Override
  public <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException {
    if (!asyncResult.isCompleted()) {
      asyncResult.await();
    }
    return asyncResult.getValue();
  }

  public void shutdown(){
      this.executor.shutdown();
  }
  /**
   * 异步结果的简单实现，让结果完成返回一个value或者抛出一个异常。一个简易版的FutureTask 和 CompletableFuture
   * Simple implementation of async result that allows completing it successfully with a value or exceptionally with an
   * exception. A really simplified version from its real life cousins FutureTask and CompletableFuture.
   *
   * @see java.util.concurrent.FutureTask
   * @see java.util.concurrent.CompletableFuture
   */
  private static class CompletableResult<T> implements AsyncResult<T> {

    static final int RUNNING = 1;
    static final int FAILED = 2;
    static final int COMPLETED = 3;

    final Object lock;
    final Optional<AsyncCallback<T>> callback;

    volatile int state = RUNNING;
    T value;
    Exception exception;

    CompletableResult(AsyncCallback<T> callback) {
      this.lock = new Object();
      this.callback = Optional.ofNullable(callback);
    }

    /**
     * 设置value 当 执行成功 并且 执行回调可以获取到. 唤醒其他等待完成的线程
     *
     * @param value
     *          value of the evaluated task
     */
    void setValue(T value) {
      this.value = value;
      this.state = COMPLETED;
      if(this.callback.isPresent()){
          AsyncCallback<T> tAsyncCallback = this.callback.get();
          tAsyncCallback.onComplete(value,Optional.<Exception>empty());
      }
//      this.callback.ifPresent(ac -> ac.onComplete(value, Optional.<Exception>empty()));
      synchronized (lock) {
        lock.notifyAll();
      }
    }

    /**
     * 设置异常 当执行失败  并且 执行回调可以获取到. 唤醒其他等待完成的线程
     *
     * @param exception
     *          exception of the failed task
     */
    void setException(Exception exception) {
      this.exception = exception;
      this.state = FAILED;
        if(this.callback.isPresent()){
            AsyncCallback<T> tAsyncCallback = this.callback.get();
            tAsyncCallback.onComplete(null,Optional.of(exception));
        }
//      this.callback.ifPresent(ac -> ac.onComplete(null, Optional.of(exception)));
      synchronized (lock) {
        lock.notifyAll();
      }
    }

    @Override
    public boolean isCompleted() {
      return state > RUNNING;
    }

    @Override
    public T getValue() throws ExecutionException {
      if (state == COMPLETED) {
        return value;
      } else if (state == FAILED) {
        throw new ExecutionException(exception);
      } else {
        throw new IllegalStateException("Execution not completed yet");
      }
    }

    @Override
    public void await() throws InterruptedException {
      synchronized (lock) {
        if (!isCompleted()) {
          lock.wait();
        }
      }
    }
  }
}
