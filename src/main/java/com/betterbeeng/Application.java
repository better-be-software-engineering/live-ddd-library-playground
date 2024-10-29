package com.betterbeeng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {Application.class})
public class Application {

  public static void main(String[] args) {
    try {
      SpringApplication springApplication = new SpringApplication(Application.class);
      springApplication.setBannerMode(Banner.Mode.OFF);
      springApplication.run(args);
    } catch (Throwable t) {
      t.printStackTrace();
      throw t;
    }
  }
}