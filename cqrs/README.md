---
layout: pattern
title: CQRS
folder: cqrs
permalink: /patterns/cqrs/
pumlid: 7SPR4a0m3030gt00pR_RH6I8QQFouFgC_TfHb6gkd5Q7FQBx363ub4rYpoMTZKuDrYXqDX37HIuuyCPfPPTDfuuHREhGqBy0NUR0GNzAMYizMtq1
categories: Architectural
tags:
 - Java
 - Difficulty-Intermediate
---

## Intent
CQRS命令查询职责分离模式——将查询区与命令（增删改）区分开。.

![alt text](./etc/cqrs.png "CQRS")

## Applicability
Use the CQRS pattern when

* 您需要独立地扩展查询和命令。
* 您希望使用不同的数据模型来查询和命令。在处理复杂的域时有用。
* 您希望使用像事件包或基于任务的UI这样的架构。
* 主要用来扩展读写分离
## Credits

* [Greg Young - CQRS, Task Based UIs, Event Sourcing agh!](http://codebetter.com/gregyoung/2010/02/16/cqrs-task-based-uis-event-sourcing-agh/)
* [Martin Fowler - CQRS](https://martinfowler.com/bliki/CQRS.html)
* [Oliver Wolf - CQRS for Great Good](https://www.youtube.com/watch?v=Ge53swja9Dw)
