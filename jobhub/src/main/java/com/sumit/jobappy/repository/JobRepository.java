package com.sumit.jobappy.repository;

import com.sumit.jobappy.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
