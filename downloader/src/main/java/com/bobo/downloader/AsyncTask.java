package com.bobo.downloader;

import android.os.Looper;
import com.queue.library.DispatchThread;

/**
 * @author bobo
 * @date 2022/12/30
 */
public class AsyncTask {


    private static final DispatchThread MAIN_QUEUE = new DispatchThread(Looper.getMainLooper());

    protected void publishProgress(final Integer... values) {
        MAIN_QUEUE.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(values);
            }
        });
    }


    protected void onProgressUpdate(Integer... values) {

    }

}
