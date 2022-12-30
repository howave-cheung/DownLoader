package com.bobo.downloader;

import java.io.File;

/**
 * @author bobo
 * @date 2022/12/30
 */
public interface DownloadSubmitter {

    /**
     * submit the download task
     *
     * @param downloadTask
     */
    boolean submit(DownloadTask downloadTask);


    File submit0(DownloadTask downloadTask) throws Exception;

}
