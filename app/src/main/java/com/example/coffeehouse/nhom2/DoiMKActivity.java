package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.unti.Server;

import java.util.HashMap;
import java.util.Map;

public class DoiMKActivity extends AppCompatActivity {
    private Toolbar toolbarDMK;
    private TextInputLayout layoutMKHT;
    private EditText edtMKHT;
    private TextInputLayout layoutMKL1;
    private EditText edtMKL1;
    private TextInputLayout layoutMKL2;
    private EditText edtMKL2;
    private Button btnDMK;
    private RelativeLayout menu_progressbarDMK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mk);
        AnhXa();
        GetThongTin();

    }



    private void GetThongTin() {
        btnDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MKHT = edtMKHT.getText().toString().trim();
                String MKL1 = edtMKL1.getText().toString().trim();
                final String MKL2 = edtMKL2.getText().toString().trim();
                if (!MKHT.equals(MainActivity.password)){
                    edtMKHT.setError("Mật khẩu không đúng");
                }else if (MKL1.equals("")){
                    edtMKL1.setError("Yêu cầu nhập dữ liệu");
                }else if (MKL1.equals(MKL2)){

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandoimatkhau, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(DoiMKActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("idtk",MainActivity.ID);
                            hashMap.put("matkhau",MKL2);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    edtMKL2.setError("Mật khẩu không trùng");
                }
            }
        });
    }

    private void AnhXa() {

        edtMKHT = (EditText) findViewById(R.id.edt_MKHT);

        edtMKL1 = (EditText) findViewById(R.id.edt_MKL1);

        edtMKL2 = (EditText) findViewById(R.id.edt_MKL2);
        btnDMK = (Button) findViewById(R.id.btn_DMK);

    }

    public void btnHome(View view) {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}
