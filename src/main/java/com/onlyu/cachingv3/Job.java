package com.onlyu.cachingv3;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @Column(name="job_id")
    private String jobId;

    @Column(name="job_title")
    private String jobTitle;

    @Column(name="min_salary")
    private int minSalary;

    @Column(name="max_salary")
    private int maxSalary;

    public final static Job EMPTY_INSTANCE = new Job("N/A", "N/A", 0, 0);

}
