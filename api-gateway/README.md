---
layout: pattern
title: API Gateway
folder: api-gateway
permalink: /patterns/api-gateway/
pumlid: JSox3SCm303HLP819FRUXg49cO542_nOyFPncUvUSszHwhbpMdyT4TCt0CDLcyIHdtGsEZLOez8vG7ek33JuueLbPvUcPM84cpeCz2S0fvI6mGjluA1_b-Tt2N5D6tNcw3y0
categories: Architectural
tags:
- Java
- Difficulty-Intermediate
- Spring
---

## Intent

对单个微服务进行聚合调用: 这就是API网关. 
用户只需要调用微服务网关API, API网关再去调用各个微服务.
实际上的微服务不需要暴漏给用户，解决了客户端为了一个功能实际上调用很多接口。

![alt text](./etc/api-gateway.png "API Gateway")

## Applicability

Use the API Gateway pattern when

* you're also using the Microservices pattern and need a single point of aggregation for your
microservice calls

## Credits

* [microservices.io - API Gateway](http://microservices.io/patterns/apigateway.html)
* [NGINX - Building Microservices: Using an API Gateway](https://www.nginx.com/blog/building-microservices-using-an-api-gateway/)
