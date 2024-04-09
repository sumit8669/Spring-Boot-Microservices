package com.sumit.ms.job.impl;


import com.sumit.ms.job.Job;
import com.sumit.ms.job.JobRepository;
import com.sumit.ms.job.JobService;
import com.sumit.ms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
   // private final List<Job> jobs = new ArrayList<>();
   //private Long nextId =1L;
   private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        RestTemplate restTemplate = new RestTemplate();
    Company company = restTemplate.getForObject("http://localhost:9091/company/1", Company.class);
        System.out.println("Company: "+ company.getId());
        System.out.println("Company: "+ company.getName());
    return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
       }

    @Override
    public Boolean updatedJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        } else
            return false;
    }
}



