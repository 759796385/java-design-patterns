---
layout: pattern
title: Async Method Invocation
folder: async-method-invocation
permalink: /patterns/async-method-invocation/
pumlid: TSdB3SCW303GLTe1mFTkunWhk0A3_4dKxTi5UdlIUuhIoCPfuz4Zjhy03EzwIlGyqjbeQR16fJL1HjuOQF362qjZbrFBnWWsTPZeFm3wHwbCZhvQ4RqMOSXIuA1_LzDctJd75m00
categories: Concurrency
tags:
 - Java
 - Difficulty-Intermediate
 - Functional
 - Reactive
---

## Intent
异步方法调用是一种模式，在等待任务的结果时，该调用线程不会被阻塞。
这个模式让多个独立任务并行处理并通过回调或者等待检索结果，直到任务完成。 

![alt text](./etc/async-method-invocation.png "Async Method Invocation")

## Applicability
Use async method invocation pattern when

* you have multiple independent tasks that can run in parallel
* you need to improve the performance of a group of sequential tasks
* you have limited amount of processing capacity or long running tasks and the
  caller should not wait the tasks to be ready

## Real world examples

* [FutureTask](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/FutureTask.html), [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html) and [ExecutorService](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html) (Java)
* [Task-based Asynchronous Pattern](https://msdn.microsoft.com/en-us/library/hh873175.aspx) (.NET)
