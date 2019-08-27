package com.hkit.fakeinsta;

import androidx.appcompat.app.AppCompatActivity;

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

public class JoinActivity extends AppCompatActivity {
    private EditText et_uid, et_upw, et_upw_re, et_nick_nm;
    private APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        init();
    }

    private void init() {
        et_uid = findViewById(R.id.et_uid);
        et_upw = findViewById(R.id.et_upw);
        et_upw_re = findViewById(R.id.et_upw_re);
        et_nick_nm = findViewById(R.id.et_nick_nm);

        api = APIClient.getClient().create(APIInterface.class);
    }

    public void clkJoin(View v) {
        String uid = et_uid.getText().toString();
        String upw = et_upw.getText().toString();
        String upwRe = et_upw_re.getText().toString();
        String nick_nm = et_nick_nm.getText().toString();

        if(!upw.equals(upwRe)) {
            Toast.makeText(JoinActivity.this
                    , R.string.join_check_pw
                    , Toast.LENGTH_SHORT).show();
            return;
        }

        UserVo vo = new UserVo();
        vo.uid = uid;
        vo.upw = upw;
        vo.nick_nm = nick_nm;

        Call<Integer> call = api.join(vo);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(JoinActivity.this
                        , "회원가입 완료"
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(JoinActivity.this
                        , "회원가입 실패"
                        , Toast.LENGTH_SHORT).show();

                Log.e("fake", t.getMessage());
            }
        });
    }

}
