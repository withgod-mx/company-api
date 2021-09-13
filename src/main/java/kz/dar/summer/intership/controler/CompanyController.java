package kz.dar.summer.intership.controler;

import kz.dar.summer.intership.feign.EmployeeFeign;
import kz.dar.summer.intership.model.CompanyResponse;
import kz.dar.summer.intership.repository.CompanyEntity;
import kz.dar.summer.intership.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    EmployeeFeign employeeFeign ;

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "company-apy working";
    }

    @GetMapping("/check/openfeign/employee")
    public String checkEmployeeFeignClient() {
        return employeeFeign.healthCheck();
    }



    @GetMapping("/all")
    public ResponseEntity<List<CompanyResponse>> getCompanyList() {
        Iterable<CompanyEntity> companyEntities = companyRepository.findAll();

        List<CompanyResponse> companyRepositories = StreamSupport.stream(companyEntities.spliterator(), false)
                .map(company -> mappingCompany(company)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(companyRepositories);
    }

    private CompanyResponse mappingCompany(CompanyEntity company) {
        CompanyResponse companyResponse = new CompanyResponse();

        companyResponse.setCompanyId(company.getCompanyId());
        companyResponse.setName(company.getName());

        companyResponse.setEmployees(employeeFeign.getEmployeeListByCompanyId(company.getCompanyId()));

        return companyResponse;
    }


}
