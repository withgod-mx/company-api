package kz.dar.summer.intership.feign;

import kz.dar.summer.intership.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-core-api")
public interface EmployeeFeign {


    @GetMapping("/employee/healthcheck")
    public String healthCheck();


    @GetMapping("/company/{companyId}")
    public List<Employee> getEmployeeListByCompanyId(@PathVariable String companyId);



}
