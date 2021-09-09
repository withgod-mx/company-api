package kz.dar.summer.intership.controler;

import kz.dar.summer.intership.feign.EmployeeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    EmployeeFeign employeeFeign ;

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "company-apy working";
    }

    @GetMapping("/check/openfeign/employee")
    public String checkEmployeeFeignClient() {
        return employeeFeign.healthCheck();
    }

}
