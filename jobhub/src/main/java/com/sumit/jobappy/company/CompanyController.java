package com.sumit.jobappy.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jobhub/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createComapy(company);
        return new ResponseEntity<>("Company is created Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/all-companies")
    public ResponseEntity<List<Company>> getAllCompanies(){
           List<Company> allCompanies = companyService.getAllCompanies();
        return new ResponseEntity(allCompanies ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if (company != null)
            return new ResponseEntity<>(company,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedByCompanyId(@PathVariable Long id){
        Boolean deleted =  companyService.deleteByCompanyId(id);
        if (deleted)
            return new ResponseEntity<>("Company is deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>("Company not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedByCompanyId(@PathVariable Long id, @RequestBody Company company){
            Boolean updated = companyService.updateByCompanyId(id,company);
                if (updated)
                return new ResponseEntity<>("Company updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Error occurred!!!",HttpStatus.BAD_REQUEST);
    }

}
