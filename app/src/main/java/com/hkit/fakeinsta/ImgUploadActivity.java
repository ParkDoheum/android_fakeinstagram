package com.hkit.fakeinsta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hkit.fakeinsta.ajax.APIClient;
import com.hkit.fakeinsta.ajax.APIInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImgUploadActivity extends AppCompatActivity {
    final int REQ_CODE_SELECT_IMAGE = 1001;
    String imgPath = null;
    APIInterface api;
    ImageView imageview;
    EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_upload);
        init();
    }

    public void init() {
        imageview = findViewById(R.id.imageview);
        et_content = findViewById(R.id.et_content);

        api = APIClient.getClient().create(APIInterface.class);
    }

    //이미지 선택
    public void selectImg(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
    }

    //이미지 업로드
    public void clkUpload(View v) {
        if(imgPath == null || "".equals(imgPath)) {
            Util.showToast(ImgUploadActivity.this, R.string.imgupload_need_img);
            return;
        }

        File file = new File(imgPath);
        RequestBody fileReqBody = RequestBody.Companion.create(file, MediaType.parse("image/*"));
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);

        RequestBody write_uid = RequestBody.Companion.create("1", okhttp3.MultipartBody.FORM);

        Call call  = api.uploadImg(part, write_uid);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Util.showToast(ImgUploadActivity.this, "이미지 업로드 성공");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Util.showToast(ImgUploadActivity.this, "이미지 업로드 실패");

                Log.e("fake", t.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE_SELECT_IMAGE) {
            if(resultCode == Activity.RESULT_OK) {
                imgPath = Util.getFilePath(ImgUploadActivity.this, data);
            }
        }
    }
}


