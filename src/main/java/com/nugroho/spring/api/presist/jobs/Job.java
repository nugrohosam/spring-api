package com.nugroho.spring.api.presist.jobs;

import java.util.Date;

public interface Job {

    public void doJob();
    public void sendJob();
    public void sendJob(Date runAt);
    public void setData(String data);

}
