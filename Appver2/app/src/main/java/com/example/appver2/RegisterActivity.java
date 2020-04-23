package com.example.appver2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.appver2.ui.login.LoginActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static com.example.appver2.R.id.checkBox1;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter adapter;

    Button registerButton;
    private String userid;
    private String userpwd;
    private String usergender;
    private String userage;
    private String userphone;
    private String useremail;
    private String useraddress;
    private String useragree;
    private String adcategoryno;
    private String living;
    private String food ;
    private String culture;
    private String fashion;
    private String beauty;
    private String pet;
    private String sport;
    private String publicad;
    private String economy;
    private AlertDialog dialog;
    private boolean validate = false;
    private TextView textView1, textView2, textView3, textView4, textView5;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6,checkBox7,checkBox8,checkBox9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }


        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText ageText = findViewById(R.id.ageText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText addressText = (EditText) findViewById(R.id.addressText);
        final EditText phone = (EditText) findViewById(R.id.Phone);
        Button registerButton = findViewById(R.id.registerButton);

        //default값 설정
        useragree = "Y";
        usergender = "F";
         living = "N";
         food = "N";
         culture = "N";
         fashion = "N";
         beauty = "N";
         pet = "N";
         sport = "N";
         publicad = "N";
         economy = "N";
        adcategoryno = "4";
// ---------------------------------------------------------------------------------------------------------
        final RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        final int genderGroupID = genderGroup.getCheckedRadioButtonId();
        RadioGroup AdGroup = (RadioGroup) findViewById(R.id.AdGroup);
        int AdGroupID = AdGroup.getCheckedRadioButtonId();
// ----------------------------------------------------------------------------------------------------------
        //(수신동의) 버튼이 눌리면 값을 바꿔주는 부분
        AdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int j) {
                RadioButton adButton = (RadioButton) findViewById(j);
                useragree = adButton.getText().toString();
                if(useragree.equals("Y")) {
                    useragree="Y";
                }else {
                    useragree="N";
                }
                Log.d("-----", useragree);
            }
        });


        //라디오버튼이 눌리면 값을 바꿔주는 부분
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);

                usergender = genderButton.getText().toString();
                if(usergender.equals("female")) {
                    usergender="F";
                }else {
                    usergender="M";
                }

                Log.d("-----", usergender);
            }
        });

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);



//        CheckBox repeatChkBx = ( CheckBox ) findViewById(R.id.checkBox1);
//        repeatChkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { if ( checkBox1.isChecked()) {
//                 living = "Y";
//                 }else{
//                 living = "N";
//
//            }
//            } });

// *******************************************************************************************
        //Register(회원가입) 버튼이 눌렸을 때
        //빈 공간 체크, 가입 완료 시 로그인 화면으로 가도록 함.
//



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = idText.getText().toString();
                String userpwd = passwordText.getText().toString();
                String userage = ageText.getText().toString();
                String userphone = phone.getText().toString();
                String useremail = emailText.getText().toString();
                String useraddress = addressText.getText().toString();

        //제출 버튼 눌렀을 때 바뀐 체크박스 값을 전달
                if(checkBox1.isChecked()){
                     living = "Y";
                }
                if(checkBox2.isChecked()){
                     food = "Y";
                }
                if(checkBox3.isChecked()){
                     culture = "Y";
                }
                if(checkBox4.isChecked()){
                     fashion = "Y";
                }
                if(checkBox5.isChecked()){
                     beauty = "Y";
                }
                if(checkBox6.isChecked()){
                     pet = "Y";
                }
                if(checkBox7.isChecked()){
                     sport = "Y";
                }
                if(checkBox8.isChecked()){
                     publicad = "Y";
                }
                if(checkBox9.isChecked()){
                     economy = "Y";
                }



                RegisterTask registerTask = new RegisterTask();
                registerTask.setURL(userid,userpwd,Integer.parseInt(userage),usergender,userphone,useremail,useraddress,useragree, Integer.parseInt(adcategoryno),living,food,fashion,culture,beauty,pet,sport,publicad,economy);
                registerTask.execute();
                //빈칸이 있을 경우 토스트메시지
                if(userid==null) {
                    Toast.makeText(getApplicationContext(), "ID를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(userpwd==null) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(userage==null) {
                    Toast.makeText(getApplicationContext(), "나이를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(userphone==null) {
                    Toast.makeText(getApplicationContext(), "전화번호를 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(useremail==null) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요!", Toast.LENGTH_LONG).show();
                }else if(useraddress==null) {
                    Toast.makeText(getApplicationContext(), "주소를 입력하세요!", Toast.LENGTH_LONG).show();
                }
//                else if(checkBox1.isChecked() == false &&
//                checkBox2.isChecked() == false &&
//                checkBox3.isChecked() == false &&
//                checkBox4.isChecked() == false &&
//                checkBox5.isChecked() == false) {
//                    Toast.makeText(getApplicationContext(),"관심분야를 하나이상 체크해주세요!",Toast.LENGTH_LONG);
//                }
                Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show();
                finish(); //다시 메인으로 돌아가는지??
//                Log.e("--------","아이디 확인x ");

//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivityForResult(intent,200);

            }
        });

// ***************************************************************************************


    }//onCreate


    public static class RegisterTask extends AsyncTask<String, Void, String> {


        String sendMsg, urlstr, receiveMsg;
        public void setURL (String id, String pwd, int age,String gender,  String phone, String email, String address,
                            String agree,int adcategoryno, String living,String food,String fashion,String culture,String beauty,String pet,String sport,String publicad,String economy) {

            urlstr = "http://70.12.226.146/oracledb/androidDB.jsp?id=" +id + "&pwd=" + pwd + "&age=" + age + "&gender=" +
                    gender + "&phone=" + phone + "&email=" + email +  "&address=" + address +"&agree=" + agree +"&adcategoryno=" + adcategoryno+"&living="
                    + living +"&food=" + food +"&fashion=" + fashion +"&culture=" + culture +"&beauty=" + beauty +"&pet=" + pet +"&sport=" + sport+ "&publicad="
                    + publicad+ "&economy=" + economy;
//            urlstr = "http://192.168.0.3/oracledb/androidDB.jsp?id=" +id + "&pwd=" + pwd + "&age=" + age + "&gender=" +
//                    gender + "&phone=" + phone + "&email=" + email + "&agree=" + agree;

        }


        @Override
        protected String doInBackground(String... strings) {

//                    sendMsg = "id="+userid+"&pwd="+userpwd+"&age="+userage+"&gender="+usergender
//                +"&phone="+userphone+"&email="+useremail+"&agree="+useragree;


            try {
                String str;
                URL url = new URL(urlstr);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송합니다.

                conn.setDoInput(true);
                conn.connect();
                /* 안드로이드 -> 서버 파라메터값 전달 */

//                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

//                osw.write(sendMsg);
//                osw.flush();
//                osw.close();

                /* 서버 -> 안드로이드 파라메터값 전달 */
                //jsp와 통신이 정상적으로 되었을 때 할 코드들입니다.
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    //jsp에서 보낸 값을 받는다.
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.d("---------","receiveMsg="+receiveMsg);
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");

                    // 통신이 실패했을 때 실패한 이유를 알기 위해 로그를 찍습니다.
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //jsp로부터 받은 리턴 값입니다.

            Log.d("---------",receiveMsg);
//            Toast.makeText(getApplicationContext() ,"회원가입완료?",Toast.LENGTH_LONG).show();
            return receiveMsg;
        }
    }


}






