package com.sumit.ms.job;



import com.sumit.ms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAllJobs();

    void createJob(Job job);

    JobDTO findJobById(Long id);

    Boolean deleteJobById(Long id);

    Boolean updatedJobById(Long id, Job job);
}
