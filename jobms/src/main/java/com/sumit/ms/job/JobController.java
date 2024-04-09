package com.sumit.ms.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        service.createJob(job);
        return new ResponseEntity<>("Job created Successfully",HttpStatus.CREATED);
    }


    @GetMapping("/all-jobs")
    public ResponseEntity<List<Job>> findAllJobs(){
            List<Job> jobs = service.findAllJobs();
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id){
             Job job = service.findJobById(id);
             if(job != null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
            Boolean deleted = service.deleteJobById(id);
                if (deleted)
            return new ResponseEntity<>("Job deleted Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Error occurred!!",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job){
           Boolean updated = service.updatedJobById(id,job);
            if (updated)
                return new ResponseEntity<>("Job updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Job not Found",HttpStatus.BAD_REQUEST);
    }

}
