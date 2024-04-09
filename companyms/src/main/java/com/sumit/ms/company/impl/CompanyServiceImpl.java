package com.sumit.ms.company.impl;


import com.sumit.ms.company.Company;
import com.sumit.ms.company.CompanyRepository;
import com.sumit.ms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

   private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Boolean deleteByCompanyId(Long id) {
        if (companyRepository.existsById(id)){
             companyRepository.deleteById(id);
             return true;
            } else {
            return false;
        }
    }

    @Override
    public Boolean updateByCompanyId(Long id, Company updatedCompany) {
      Optional <Company> companyOptional = companyRepository.findById(id);
      if (companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setName(updatedCompany.getName());
            companyToUpdate.setDescription(updatedCompany.getDescription());
            companyRepository.save(companyToUpdate);
            return true;
      } else
        return false;
    }
}
