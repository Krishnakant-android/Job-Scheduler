package com.krishna.jobschedulerexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class JobSchedulerService extends JobService {
    JobTask jobTask;
    private boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(this, "Job Starts", Toast.LENGTH_SHORT).show();
        jobTask = (JobTask) new JobTask(this).execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("Jobs", "Job cancelled before completion");
        jobCancelled = true;
        //TODO cancel the running task
        if(jobTask != null){
            jobTask.cancel(true);
        }
        return true;
    }

    private class JobTask extends AsyncTask<JobParameters, Void, JobParameters> {
        private final JobService jobService;

        public JobTask(JobService jobService) {
            this.jobService = jobService;
        }

        @Override
        protected JobParameters doInBackground(JobParameters... params) {
            for (int i = 1; i <= 20; i++) {
                if (jobCancelled) {
                    break;
                }
              Log.d("Jobs"," "+i);
                SystemClock.sleep(1000);
            }

            return params[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            jobService.jobFinished(jobParameters, false);
            Toast.makeText(jobService, "Task Finished", Toast.LENGTH_SHORT).show();
        }
    }
}