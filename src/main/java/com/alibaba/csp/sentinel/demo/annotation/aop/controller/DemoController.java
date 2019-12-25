/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.demo.annotation.aop.controller;

import com.alibaba.csp.sentinel.demo.annotation.aop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eric Zhao
 */
@RestController
public class DemoController {

    @Autowired
    private TestService service;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

//    private int i = 0;
    // volatile 并不能保证线程安全
//    private volatile int i = 0;

    @GetMapping("/foo")
    public String apiFoo(Long t) {
        System.out.println("当前请求是第 " + atomicInteger.incrementAndGet() + ", 当前时间是" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
//        System.out.println("当前请求是第 " + ++i );
//        System.out.println("当前时间是" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        if (t == null) {
            t = System.currentTimeMillis();
        }
//        service.test();
        return service.hello(t);
    }

    @GetMapping("/baz/{name}")
    public String apiBaz(@PathVariable("name") String name) {
        return service.helloAnother(name);
    }
}
