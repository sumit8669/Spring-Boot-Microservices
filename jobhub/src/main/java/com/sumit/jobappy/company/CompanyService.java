package com.sumit.jobappy.company;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CompanyService {

    List<Company> getAllCompanies();


    Company getCompanyById(Long id);

    void createComapy(Company company);

    Boolean deleteByCompanyId(Long id);

    Boolean updateByCompanyId(Long id, Company company);
}
