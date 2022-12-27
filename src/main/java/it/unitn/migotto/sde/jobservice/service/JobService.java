package it.unitn.migotto.sde.jobservice.service;

import it.unitn.migotto.sde.jobservice.model.Job;
import it.unitn.migotto.sde.jobservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JobService {


    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public List<Job> getJobs(){
        return jobRepository.findAll();
    }

    public Optional<Job> getJob(Long id) {
        return jobRepository.findById(id);
    }


    public void postJob(Job job) {
        Optional<Job> jobOptional = jobRepository.findJobByTitle(job.getTitle());
        if (jobOptional.isPresent()){
            throw new IllegalStateException("Title already existing");
        }
        jobRepository.save(job);
    }

    public void postJobParams(String title, String description, String address, String category) {
        jobRepository.save(new Job(title, description, address, category));
    }


    public void deleteJob(Long id) {
        boolean exists = jobRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "job with id: " + id + " doesn't exist"
            );
        }
        jobRepository.deleteById(id);
    }


    public Job putJob(Job newJob, Long id){
        return jobRepository.findById(id)
                .map(job -> {
                    job.setTitle(newJob.getTitle());
                    job.setDescription(newJob.getDescription());
                    job.setAddress(newJob.getAddress());
                    job.setCategory(newJob.getCategory());
                    return jobRepository.save(job);
                }).orElseThrow();
    }


    @Transactional
    public void putJobParams(Long jobId, String newTitle, String description, String address, String payment) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new IllegalStateException(
                "job with id=" + jobId + " does not exist"
        ));

        Optional<Job> jobOptional = jobRepository.findJobByTitle(newTitle);

        if (newTitle != null && newTitle.length() > 0 && !Objects.equals(job.getTitle(), newTitle) && jobOptional.isEmpty()){
            job.setTitle(newTitle);
        }

        if (description != null){
            job.setDescription(description);
        }

        if (address != null){
            job.setAddress(address);
        }

        if (payment != null){
            job.setCategory(payment);
        }

    }







}
