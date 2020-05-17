package com.louis.mvp.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.louis.mvp.DownloadContract;
import com.louis.mvp.model.ImageBean;
import com.louis.mvp.presenter.DownloaderPresenter;
import com.louis.mvp.utils.Constant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadEngine implements DownloadContract.M {

private DownloaderPresenter mDownloaderPresenter;

    public DownloadEngine(DownloaderPresenter downloaderPresenter) {
        this.mDownloaderPresenter = downloaderPresenter;
    }

    @Override
    public void requestDownload(ImageBean imageBean) throws Exception {
        //P层让我做这个需求
        new Thread(new Downloader(imageBean)).start();
    }

    final class Downloader implements Runnable {

        private final ImageBean mImageBean;

        public Downloader(ImageBean imageBean) {
            mImageBean = imageBean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(mImageBean.getRequestPath());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");

                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode ==HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUI(Constant.SUCCESS, bitmap);
                } else {
                    showUI(Constant.ERROR,null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void showUI(int resultCode, Bitmap bitmap) {
            mImageBean.setBitmap(bitmap);
            mDownloaderPresenter.responseDownloadResult(resultCode == Constant.SUCCESS,mImageBean);
        }
    }
}
