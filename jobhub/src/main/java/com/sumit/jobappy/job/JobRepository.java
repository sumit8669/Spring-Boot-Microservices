package com.sumit.jobappy.job;

import com.sumit.jobappy.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
