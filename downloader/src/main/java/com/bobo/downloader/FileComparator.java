package com.bobo.downloader;

import java.io.File;

/**
 * @author bobo
 * @date 2022/12/30
 */
public interface FileComparator {

	int COMPARE_RESULT_SUCCESSFUL = 1;
	int COMPARE_RESULT_REDOWNLOAD_COVER = 2;
	int COMPARE_RESULT_REDOWNLOAD_RENAME = 3;

	int compare(String url, File originFile, String inputMD5, String originFileMD5);

	interface FileComparatorFactory {
		FileComparator newFileComparator();
	}
}
