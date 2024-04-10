package com.sumit.ms.job;



import com.sumit.ms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAllJobs();

    void createJob(Job job);

    Job findJobById(Long id);

    Boolean deleteJobById(Long id);

    Boolean updatedJobById(Long id, Job job);
}
