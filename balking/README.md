---
layout: pattern
title: Balking
folder: balking
permalink: /patterns/balking/
categories: Concurrency
tags:
 - Java
 - Difficulty-Beginner
---

## Intent
阻行模式用来在执行一段确定的代码时，阻止一个对象运行当它处于不完整或者不正确的状态。


![alt text](./etc/balking.png "Balking")

## Applicability
使用阻行模式当：

*you want to invoke an action on an object only when it is in a particular state
* 你想要执行对象上的一个操作，当他处于特殊的状态。
* 对象通常状态易变

## Related patterns
* Guarded Suspendion Pattern
* Double Checked Locking Pattern