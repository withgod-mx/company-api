package kz.dar.summer.intership.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompanyResponse {

    private String companyId;

    private String name;

    private List<Employee> employees;

}
