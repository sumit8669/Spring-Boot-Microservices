package com.sumit.jobappy.company.impl;

import com.sumit.jobappy.company.Company;
import com.sumit.jobappy.company.CompanyRepository;
import com.sumit.jobappy.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

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
    public void createComapy(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Boolean deleteByCompanyId(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        }catch (Exception e) {
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
            companyToUpdate.setJobs(updatedCompany.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
      } else
        return false;
    }
}
