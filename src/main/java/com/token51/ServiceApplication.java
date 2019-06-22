package com.token51;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages = "com")
//@EnableEurekaClient
//@ServletComponentScan
@Controller
public class ServiceApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceApplication.class, args);
    }


    @RequestMapping(value = "/")
    public String index() {
        return "/index.html";
    }

}
