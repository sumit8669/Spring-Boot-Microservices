package com.sumit.jobappy.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumit.jobappy.job.Job;
import com.sumit.jobappy.reviews.Review;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

    public Company() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
