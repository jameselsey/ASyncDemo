package com.jameselsey.demos.aysncdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

/**
 * This is a little demo app that shows how you can use the ASyncTask in Android.
 * The cancelling of ASyncTasks using the Android SDK seems a little sketchy so I've implemented my own checks
 */
public class Main extends Activity
{
    private ProgressBar progressBar;
    private boolean isCancelled;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    /**
     * Bound to the Start button
     *
     * @param v
     */
    public void clickedStart(View v)
    {
        isCancelled = false;
        new ProgressBarTask().execute();
    }

    /**
     * Bound to the Stop button
     *
     * @param v
     */
    public void clickedStop(View v)
    {
        isCancelled = true;
    }

    /**
     * Private inner class to deal with the ASyncTask
     */
    private class ProgressBarTask extends AsyncTask<Void, Integer, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            new Thread(new Runnable()
            {
                public void run()
                {
                    for (int i = 0; i < 100; i++)
                    {
                        if (!isCancelled)
                        {
                            try
                            {
                                Thread.sleep(100);
                            } catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            progressBar.setProgress(i);
                        }
                    }
                }
            }).start();
            return null;
        }
    }


}


