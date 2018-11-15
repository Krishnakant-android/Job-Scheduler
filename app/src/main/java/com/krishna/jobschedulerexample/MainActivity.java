package com.krishna.jobschedulerexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID = 0x34;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JobScheduler jobScheduler =(JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        final JobInfo job = new JobInfo.Builder(JOB_ID, new ComponentName(this, JobSchedulerService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .setPersisted(true)
                .build();

        jobScheduler.schedule(job);

    }


}
