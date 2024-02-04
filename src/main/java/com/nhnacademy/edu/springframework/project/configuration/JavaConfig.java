package com.nhnacademy.edu.springframework.project.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.nhnacademy.edu.springframework.project")
public class JavaConfig {
}
