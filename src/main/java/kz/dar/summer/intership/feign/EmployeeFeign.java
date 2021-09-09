package kz.dar.summer.intership.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "employee-core-api")
public interface EmployeeFeign {


    @GetMapping("/employee/healthcheck")
    public String healthCheck();



}
