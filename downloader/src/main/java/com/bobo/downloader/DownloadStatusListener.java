package com.bobo.downloader;

/**
 * @author bobo
 * @date 2022/12/30
 */
public interface DownloadStatusListener {

    /**
     * status 改变回调
     * @param extra
     * @param status
     */
    void onDownloadStatusChanged(Extra extra,@DownloadTask.DownloadTaskStatus int status);

}
