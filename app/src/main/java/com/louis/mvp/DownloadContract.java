package com.louis.mvp;


import com.louis.mvp.model.ImageBean;

//View层交互，Model交互共同的需求（契约）
public class DownloadContract {
    public interface M {
        //P层告诉M层，需要做什么事情
        void requestDownload(ImageBean imageBean)throws Exception;
    }

    public interface PV{
        //V层告诉P层，需要做什么事情
        void requestDownload(ImageBean imageBean)throws Exception;
        //P层得到M层的结果返回，再通知V层
        void responseDownloadResult(boolean isSuccess, ImageBean imageBean);
    }

}
