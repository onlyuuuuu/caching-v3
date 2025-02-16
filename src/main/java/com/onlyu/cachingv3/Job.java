package com.onlyu.cachingv3;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "jobs")
@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job implements Serializable, Comparable<Job>
{

    @Override
    public int compareTo(Job job)
    {
        if (job == null) return 1;
        return this.jobId.compareTo(job.getJobId());
    }

    @Id
    @Column(name = "job_id")
    private String jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    public final static Job EMPTY_INSTANCE = new Job("N/A", "N/A", 0, 0);

}
