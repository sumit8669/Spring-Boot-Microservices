package com.sumit.jobappy.job;

import com.sumit.jobappy.job.Job;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();

    void createJob(Job job);

    Job findJobById(Long id);

    Boolean deleteJobById(Long id);

    Boolean updatedJobById(Long id, Job job);
}
