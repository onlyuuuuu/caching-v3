package com.onlyu.cachingv3;

import com.onlyu.cachingv2.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, String>
{

}
