package com.louis.mvp.presenter;

import com.louis.mvp.DownloadContract;
import com.louis.mvp.MainActivity;
import com.louis.mvp.engine.DownloadEngine;
import com.louis.mvp.model.ImageBean;

public class DownloaderPresenter implements DownloadContract.PV {

    private MainActivity view;
    private DownloadEngine model; //下载的模型

    public DownloaderPresenter(MainActivity view) {
        this.view = view;
        model = new DownloadEngine(this);
    }

    @Override
    public void requestDownload(ImageBean imageBean) throws Exception {
        //接收到View层的指令，去完成某个需求（可以自己完成，也可以让别人去完成）
        try {
            model.requestDownload(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void responseDownloadResult(final boolean isSuccess, final ImageBean imageBean) {
        //将完成的结果告知View层（刷新UI）
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.responseDownloadResult(isSuccess,imageBean);
            }
        });
    }
}
