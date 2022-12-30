package com.bobo.downloadersimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import com.bobo.downloader.DownloadImpl;
import com.bobo.downloader.DownloadListenerAdapter;
import com.bobo.downloader.Extra;
import java.io.File;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText urlText = findViewById(R.id.et_url);
        Button button = findViewById(R.id.btn_down_load);

        button.setOnClickListener(view -> {
            String url = urlText.getText().toString().trim();

            if (TextUtils.isEmpty(url)) {
                return;
            }

            int permission = ActivityCompat.checkSelfPermission(this,"android.permission.WRITE_EXTERNAL_STORAGE");

            if (permission != PackageManager.PERMISSION_GRANTED){ // 判断是否有读写权限
                DownloadImpl.getInstance(this)
                        .url(url)
                        .target(new File(getExternalCacheDir() + "/download/" + "photos221230.jpg"), this.getPackageName() + ".DownloadFileProvider")//自定义路径需指定目录和authority(FileContentProvide),需要相对应匹配才能启动通知，和自动打开文件
                        .setUniquePath(false)//是否唯一路径
                        .setForceDownload(true)//不管网络类型
                        .setRetry(4)//下载异常，自动重试,最多重试4次
                        .setBlockMaxTime(60000L) //以8KB位单位，默认60s ，如果60s内无法从网络流中读满8KB数据，则抛出异常 。
                        .setConnectTimeOut(10000L)//连接10超时
//                .addHeader("xx", "cookie")//添加请求头
                        .setDownloadTimeOut(Long.MAX_VALUE)//下载最大时长
                        .setOpenBreakPointDownload(true)//打开断点续传
                        .setParallelDownload(true)//打开多线程下载
//                .autoOpenWithMD5("93d1695d87df5a0c0002058afc0361f1")//校验md5通过后自动打开该文件,校验失败会回调异常
                        .quickProgress()//快速连续回调进度，默认1.2s回调一次
                        .enqueue(new DownloadListenerAdapter() {

                            @MainThread //加上该注解，自动回调到主线程
                            @Override
                            public void onProgress(String url, long downloaded, long length, long usedTime) {
                                super.onProgress(url, downloaded, length, usedTime);
                            }

                            @Override
                            public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
                                return super.onResult(throwable, path, url, extra);
                            }
                        });
            }else {
                ActivityCompat.requestPermissions(this,new String[]{"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"},0);
            }

        });

    }
}