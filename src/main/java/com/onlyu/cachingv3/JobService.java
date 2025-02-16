package com.onlyu.cachingv3;

import java.util.*;

public interface JobService {

    Optional<Job> fetch(String id);
    List<Job> fetchAll();

}
