package com.interstellar.transport.app;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.interstellar.transport.app.entity.DBServerUtil;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.interstellar.transport.app"})
public class Application extends SpringBootServletInitializer {

	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(Application.class);
	    }
	 
	@SuppressWarnings("static-access")
	public static void main (String[] args) {
		
		DBServerUtil.startHSQLDB();
		
		Runtime r=Runtime.getRuntime();  
		r.addShutdownHook(new Thread() {
			public void run() {
				DBServerUtil.stopHSQLDB();
				for(String fileName : new File("test.*").list()) {
					File file = new File(fileName);
					if(file.exists())
						file.delete();
				}

			}
		});
		SpringApplication.run(Application.class, args);
    }
	
	
}
