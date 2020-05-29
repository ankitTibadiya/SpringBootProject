package com.arthur.blogP;

import com.arthur.blogP.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class BlogP {

	public static void main(String[] args) {
		SpringApplication.run(BlogP.class, args);
	}

}
