package com.sumit.jobappy.service;

import com.sumit.jobappy.model.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId =1L;

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job findJobById(Long id) {
        for (Job job :jobs){
            if(job.getId().equals(id))
                return job;
        }
        return null;
    }

    @Override
    public Boolean deleteJobById(Long id) {
        Iterator<Job> jobIterator = jobs.iterator();
        while (jobIterator.hasNext()){
            Job job = jobIterator.next();
            if (job.getId().equals(id)){
                jobIterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updatedJobById(Long id, Job updatedJob) {
        for (Job job : jobs){
            if (job.getId().equals(id)){
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobs;
    }


}
