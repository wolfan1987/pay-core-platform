package org.andrew.commons.mqoper.rkt.scheduler;

public abstract class JobCaller {

    private Job job;

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public abstract void call() throws Exception;
}
