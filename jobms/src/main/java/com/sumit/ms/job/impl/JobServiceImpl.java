package com.sumit.ms.job.impl;


import com.sumit.ms.job.Job;
import com.sumit.ms.job.JobRepository;
import com.sumit.ms.job.JobService;
import com.sumit.ms.job.dto.JobDTO;
import com.sumit.ms.job.external.Company;
import com.sumit.ms.job.external.Review;
import com.sumit.ms.job.mapper.JobMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
   /*
    private final List<Job> jobs = new ArrayList<>();
   private Long nextId =1L;
   */
   private final JobRepository jobRepository;

//   @Autowired
//   RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }

    @Override
    public List<JobDTO> findAllJobs() {
        List<Job> jobList = jobRepository.findAll();


        return jobList.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job){
            RestTemplate restTemplate = new RestTemplate();
            Company company = restTemplate.getForObject(
                    "http://localhost:9091/company/" + job.getCompanyId(),
                    Company.class);
       ResponseEntity<List<Review>> reviewResponse=restTemplate.exchange
               ("http://localhost:9093/reviews?companyId=" + job.getCompanyId(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Review>>() {
                    });

            List<Review> reviews = reviewResponse.getBody();

            JobDTO jobDTO = JobMapper
                        .mapToJobWithCompanyDto(job,company,reviews);
            jobDTO.setCompany(company);

            return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
       Job job = jobRepository.findById(id).orElse(null);
       return  convertToDto(job);
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



