---
layout: pattern
title: Business Delegate
folder: business-delegate
permalink: /patterns/business-delegate/
pumlid: POl13SCm3CHMQGU8zUysgYCuBcJ5a4x9-l6_Fu84tzsgvYxf-Zg06HyYvxkqZYE_6UBrD8YXr7DGrxmPxFJZYxTTeZVR9WFY5ZGu5j2wkad4wYgD8IIe_xQaZp9pw0C0
categories: Business Tier
tags:
 - Java
 - Difficulty-Intermediate
---

## Intent
业务委托模式添加了一层抽象层在表现层和业务层。通过使用设计模式获得层级的松耦合并且
封装了关于服务定位，对象关联，并与应用程序的业务对象进行交互。

![alt text](./etc/business-delegate.png "Business Delegate")

## Applicability
Use the Business Delegate pattern when

* you want loose coupling between presentation and business tiers
* 你想要在表现层和业务层获得松耦合结构。
* you want to orchestrate calls to multiple business services
* 您想要协调对多个业务服务的调用
* you want to encapsulate service lookups and service calls
* 您希望封装服务查找和服务调用。

## Credits

* [J2EE Design Patterns](http://www.amazon.com/J2EE-Design-Patterns-William-Crawford/dp/0596004273/ref=sr_1_2)
