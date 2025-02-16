package com.onlyu.cachingv3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService
{

    private final JobRepository jobRepository;

    @Override
    public Optional<Job> fetch(String id) {
        log.info("Job Service: fetching job with id [{}]", id);
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> fetchAll() {
        log.info("Job Service: fetching all jobs...");
        Iterable<Job> results = jobRepository.findAll();
        List<Job> jobs = new ArrayList<>();
        results.forEach(jobs::add);
        log.info("Job Service: fetched {} jobs.", jobs.size());
        return jobs;
    }

}
