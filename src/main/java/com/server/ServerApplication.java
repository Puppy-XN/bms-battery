package com.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口）
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/5/24 上午11:53
 */

/**
 * 这是一个 Spring Boot 的复合注解，用于标记一个主应用程序类。它包含了以下三个注解：
 * // @Configuration: 表示这是一个配置类，可以定义 Bean。
 * // @EnableAutoConfiguration: 自动配置 Spring 应用程序的类。
 * // @ComponentScan: 自动扫描指定包下的 Spring 组件。
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// 用于自动扫描 MyBatis Mapper 接口。
@MapperScan({"com.server.mapper"})
// 启用 Spring 的定时任务支持。
@EnableScheduling
// 启用 Spring 的 AOP 支持，并且将代理对象暴露给 Spring 容器，以便于其他组件可以访问代理对象。
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  项目启动成功   ლ(´ڡ`ლ)ﾞ");

    }
}
