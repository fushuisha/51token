package com.token51;


import com.token51.util.CommonUtils;
import com.token51.util.ConstUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages = "com")
//@EnableEurekaClient
//@ServletComponentScan
@Controller
public class ServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

    public static void main(String[] args) throws Exception {
        logger.warn(ConstUtils.SPRING_PROFILES_ACTIVE + ":{}", CommonUtils.getSpringProfilesActive());
        SpringApplication.run(ServiceApplication.class, args);
    }

    @RequestMapping(value = "/")
    public String index() {
        return "/index.html";
    }

}
