---
layout: pattern
title: Abstract Factory
folder: abstract-factory
permalink: /patterns/abstract-factory/
pumlid: PSZB3OD034NHLa81Czwd6sCC39gVxEUWT1_ssLmTtQLqgR5fM7sTmFGtaV6TZu8prd0r6HtQaMKqAZLk1XjT_E6qgPUZfyc0MdTgx0-8LuUn8ErFXdr98NypXxKyezKV
categories: Creational
tags:
 - Java
 - Gang Of Four
 - Difficulty-Intermediate
---

## 别称
Kit

## 目的
提供一个接口用来创建 一组 没有指定特定类型的有关系或者依赖的对象.

![alt text](./etc/abstract-factory_1.png "Abstract Factory")

## 适用性
使用抽象工厂模式的情况：

* 一个系统应该独立于它的产品是如何创建、组成和表现的
* 一个系统应该配置成多个相似的产品
* a family of related product objects is designed to be used together, and you need to enforce this constraint
* you want to provide a class library of products, and you want to reveal just their interfaces, not their implementations
* the lifetime of the dependency is conceptually shorter than the lifetime of the consumer.
*	you need a run-time value to construct a particular dependency
*	you want to decide which product to call from a family at runtime.
*	you need to supply one or more parameters only known at run-time before you can resolve a dependency.

## Use Cases:	

*	Selecting to call the appropriate implementation of FileSystemAcmeService or DatabaseAcmeService or NetworkAcmeService at runtime.
*	Unit test case writing becomes much easier

## Consequences:

*	Dependency injection in java hides the service class dependencies that can lead to runtime errors that would have been caught at compile time.




## Real world examples

* [javax.xml.parsers.DocumentBuilderFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/parsers/DocumentBuilderFactory.html)
* [javax.xml.transform.TransformerFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/transform/TransformerFactory.html#newInstance--)
* [javax.xml.xpath.XPathFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/xpath/XPathFactory.html#newInstance--)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
