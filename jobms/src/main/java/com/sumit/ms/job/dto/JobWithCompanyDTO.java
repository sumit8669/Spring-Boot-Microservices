package com.sumit.ms.job.dto;

import com.sumit.ms.job.Job;
import com.sumit.ms.job.external.Company;

public class JobWithCompanyDTO {

    private Job job;
    private   Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setId(Long id) {
    }
}
