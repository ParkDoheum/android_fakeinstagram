package com.hkit.fakeinsta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hkit.fakeinsta.ajax.APIClient;
import com.hkit.fakeinsta.ajax.APIInterface;
import com.hkit.fakeinsta.model.UserVo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private APIInterface api;
    EditText et_uid, et_upw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        api = APIClient.getClient().create(APIInterface.class);
        et_uid = findViewById(R.id.et_uid);
        et_upw = findViewById(R.id.et_upw);
    }

    //로그인
    public void clkLogin(View v) {
        String uid = et_uid.getText().toString();
        String upw = et_upw.getText().toString();
        UserVo param = new UserVo();
        param.uid = uid;
        param.upw = upw;

        Call<Integer> call = api.login(param);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int result = response.body();
                String str = "";
                switch(result) {
                    case 0:
                        str = "아이디 없음";
                        break;
                    case 1:
                        str = "로그인 성공";
                        break;
                    case 2:
                        str = "비밀번호 틀림";
                        break;
                }
                Toast.makeText(LoginActivity.this
                        , str
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(LoginActivity.this
                        , "실패"
                        , Toast.LENGTH_SHORT).show();
                Log.e("fake", t.getMessage());
            }
        });
    }

    //회원가입
    public void clkJoin(View v) {
        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
        startActivity(intent);
    }
}
