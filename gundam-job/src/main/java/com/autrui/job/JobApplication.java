package com.autrui.job;

import com.autrui.util.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * @author Autrui
 * @date 2023/8/1
 * @apiNote
 */
@EnableCustomConfig
@SpringBootApplication
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(JobApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Job启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
