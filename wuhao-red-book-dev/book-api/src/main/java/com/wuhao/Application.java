package com.wuhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.wuhao.mapper")
@ComponentScan(basePackages = {"com.wuhao", "org.n3r.idworker"})
//@EnableMongoRepositories
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
