---
layout: pattern
title: Chain of responsibility
folder: chain
permalink: /patterns/chain/
pumlid: 9SR13SCm20NGLTe1OkxTXX0KKzd4Wa-pVYlrdTxJN4OTMZ4U7LZv8Wg-ssdejLTgoELGHvDhaesw6HpqvWzlXwQTlYq6D3nfSlv2qjcS5F9VgvXjrHnV
categories: Behavioral
tags:
 - Java
 - Gang Of Four
 - Difficulty-Intermediate
---

## Intent
通过给予多个对象一个处理请求的机会，避免将请求的发送方与接收方耦合。链接接收对象并沿链传递请求，直到对象处理它。
![alt text](./etc/chain_1.png "Chain of Responsibility")

## Applicability
Use Chain of Responsibility when

* 多个对象可以处理请求。, 并且处理器没有优先级. 处理程序自动确定。
* 您想要向其中一个对象发出请求，而无需显式指定接收器。
* 可以动态指定处理请求的对象集。

## Real world examples

* [java.util.logging.Logger#log()](http://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html#log%28java.util.logging.Level,%20java.lang.String%29)
* [Apache Commons Chain](https://commons.apache.org/proper/commons-chain/index.html)
* [javax.servlet.Filter#doFilter()](http://docs.oracle.com/javaee/7/api/javax/servlet/Filter.html#doFilter-javax.servlet.ServletRequest-javax.servlet.ServletResponse-javax.servlet.FilterChain-)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
