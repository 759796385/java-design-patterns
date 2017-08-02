---
layout: pattern
title: Aggregator Microservices
folder: aggregator-microservices
permalink: /patterns/aggregator-microservices/
pumlid: JOov3SCm301NIGQGs7iRXYPa1g8ayB7NjuiKwGvtmBrbKC-Tq_hhY5Y-0HXUjKaS-Kbdepc2HrIQ2jBpma23BvvOTdPfeooCO1iEYlu0O6l63MDQKI6Rp-CKOWSE-ey_NzEqhjH-0m00
categories: Architectural
tags:
- Java
- Spring
---

## Intent

用户调用单个聚合器, 聚合器会去调用每个相关的微服务然后将结果汇聚,将业务逻辑应用于它，并进一步发布作为REST端点.
更多的聚合器变化: 
- 代理 微服务设计模式:根据不同的业务需要调用微服务. 
- 链式 微服务设计模式: 微服务之间也会有连续的链式调用依赖.

![alt text](./etc/aggregator-microservice.png "Aggregator Microservice")

## Applicability

Use the Aggregator Microservices pattern when you need a unified API for various microservices, regardless the client device.

## Credits

* [Microservice Design Patterns](http://blog.arungupta.me/microservice-design-patterns/)
