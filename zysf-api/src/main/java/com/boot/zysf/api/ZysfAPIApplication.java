package com.boot.zysf.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;

@CacheConfig
@EnableCaching
@SpringBootApplication
public class ZysfAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZysfAPIApplication.class, args);
		//System.out.println();
	}

}
