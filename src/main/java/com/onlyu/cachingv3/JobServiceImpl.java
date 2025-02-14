package com.onlyu.cachingv3;

import com.onlyu.cachingv2.Job;
import com.onlyu.cachingv2.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService
{

    private final JobRepository jobRepository;

    @Override
    public Optional<Job> fetch(String id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> fetchAll() {
        Iterable<Job> results = jobRepository.findAll();
        List<Job> jobs = new ArrayList<>();
        results.forEach(jobs::add);
        return jobs;
    }

}
