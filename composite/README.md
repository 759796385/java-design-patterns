---
layout: pattern
title: Composite
folder: composite
permalink: /patterns/composite/
pumlid: HSf13eCm30NHgy01YFUzZGaM62LEP7-NwvTTT_EaMTLgoqFIst81Cpv4payv5LVk6U9r6CHGwkYaBHy6EztyvUsGqDEsoO2u1NMED-WTvmY5aA3-LT9xcTdR3m00
categories: Structural
tags:
 - Java
 - Gang Of Four
 - Difficulty-Intermediate
---

## Intent
将对象组合成树结构，以表示部分-整体层次结构。
组合模式可以让客户端统一处理对象和对象的组合。

![alt text](./etc/composite_1.png "Composite")

## Applicability
Use the Composite pattern when

* 您想要表示部分-整体层次的对象结构。
* 您希望客户机能够忽略对象和单个对象的组合之间的差异。客户端将统一处理复合结构中的所有对象。

## Real world examples

* [java.awt.Container](http://docs.oracle.com/javase/8/docs/api/java/awt/Container.html) and [java.awt.Component](http://docs.oracle.com/javase/8/docs/api/java/awt/Component.html)
* [Apache Wicket](https://github.com/apache/wicket) component tree, see [Component](https://github.com/apache/wicket/blob/91e154702ab1ff3481ef6cbb04c6044814b7e130/wicket-core/src/main/java/org/apache/wicket/Component.java) and [MarkupContainer](https://github.com/apache/wicket/blob/b60ec64d0b50a611a9549809c9ab216f0ffa3ae3/wicket-core/src/main/java/org/apache/wicket/MarkupContainer.java)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
