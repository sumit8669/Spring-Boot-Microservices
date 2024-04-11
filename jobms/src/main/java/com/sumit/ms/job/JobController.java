package com.sumit.ms.job;

import com.sumit.ms.job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created Successfully",HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findJobs(){
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id){
             Job job = jobService.findJobById(id);
             if(job != null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
            Boolean deleted = jobService.deleteJobById(id);
                if (deleted)
            return new ResponseEntity<>("Job deleted Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Error occurred!!",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job){
           Boolean updated = jobService.updatedJobById(id,job);
            if (updated)
                return new ResponseEntity<>("Job updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Job not Found",HttpStatus.BAD_REQUEST);
    }

}
