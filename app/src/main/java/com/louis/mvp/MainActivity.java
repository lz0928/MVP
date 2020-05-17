package com.louis.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.louis.mvp.model.ImageBean;
import com.louis.mvp.presenter.DownloaderPresenter;
import com.louis.mvp.utils.Constant;

//MVC中Activity是C层，MVP中Activity是V层
public class MainActivity extends AppCompatActivity implements DownloadContract.PV{

    private ImageView mImageView;
    private DownloaderPresenter mDownloaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.iv);
        mDownloaderPresenter = new DownloaderPresenter(this);
    }

    //点击事件
    public void down(View view) {
        ImageBean imageBean = new ImageBean();
        imageBean.setRequestPath(Constant.IMAGE_PATH);
        try {
            requestDownload(imageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestDownload(ImageBean imageBean) throws Exception {
        if (mDownloaderPresenter != null) {
            mDownloaderPresenter.requestDownload(imageBean);
        }
    }

    @Override
    public void responseDownloadResult(boolean isSuccess, ImageBean imageBean) {
        Toast.makeText(this,isSuccess ? "下载成功" : "下载失败",Toast.LENGTH_SHORT).show();
        if (isSuccess && imageBean.getBitmap() != null) {
            mImageView.setImageBitmap(imageBean.getBitmap());
        }
    }
}
