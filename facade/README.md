---
layout: pattern
title: Facade
folder: facade
permalink: /patterns/facade/
pumlid: BSP15eCm20N0gxG7CEoz3ILKqvTW7dpq-hhehERTJ7fMJU-l7PYn4ZbVPMlOyvEXBeT13KMEGQtdnM2d7v-yL8sssJ8PKBUWmV64lYnSbHJoRqaVPUReDm00
categories: Structural
tags:
 - Java
 - Gang Of Four
 - Difficulty-Beginner
---

## Intent
为子系统的一组接口提供一个统一的接口

门面模式定义了一个高层级的接口保证子系统容易易用

![alt text](./etc/facade_1.png "Facade")

## Applicability
使用门面模式在

* 为复杂的子系统提供一个简单的接口. 子系统在演化过程中往往变得更加复杂. 大多数模式在应用时，会导致更多小功能的类. 这使得子系统更加可重用，更易于定制, 但对于不需要自定义的使用者来说，使用它也变得更加困难。门面模式可以提供对大多数客户端来说够用的子系统的简单默认视图。 只有需要更多可定制性的调用者才需要注意外观模式内部。
* 客户端和抽象的实现类之间存在许多依赖关系。 引入一个门面模式来将子系统与客户端和其他子系统解耦，从而保证子系统的独立性和可移植性。
* 你想要对子系统进行分层。使用facade来定义每个子系统级别的入口点。 如果子系统有依赖，那么您可以通过使它们仅通过它们的门面接口进行通信来简化它们之间的依赖关系。

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
