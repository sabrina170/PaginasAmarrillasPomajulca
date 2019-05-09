package com.pomajulca.paginasamarrillas.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pomajulca.paginasamarrillas.R;
import com.pomajulca.paginasamarrillas.models.Company;
import com.pomajulca.paginasamarrillas.repositories.CompanyRepository;

public class DetailCompanyActivity extends AppCompatActivity {

    private static final String TAG = DetailCompanyActivity.class.getSimpleName();

      private static  final int CALL_PERMISSION_REQUEST=100;
      private Integer id;

    private ImageView logo;
    private TextView category;
    private TextView name;
    private TextView address;
    private TextView phone;
    private TextView email;
    private TextView webtext;
    private TextView info;

    private Intent intent;
    private Company company;

    private Button buttonPhone;
    private  Button buttonWeb;
    private  Button buttonShare;
    private Button buttonGmail;
    private  Button buttonSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_company);






        try {

            logo = findViewById(R.id.logo_Image);
            category = findViewById(R.id.category_text);
            name = findViewById(R.id.name_text);
            address = findViewById(R.id.address_text);
            phone = findViewById(R.id.phone_text);
            email = findViewById(R.id.email_text);
            webtext = findViewById(R.id.url_text);
            info = findViewById(R.id.info_text);

            this.id = getIntent().getExtras().getInt("id");

            Company company = CompanyRepository.getCompanyById(id);

            if (company != null) {
                int resourceid = getResources().getIdentifier(company.getLogo(),
                        "drawable", getPackageName()
                );

                logo.setImageResource(resourceid);
                category.setText(company.getCategory());
                name.setText(company.getName());
                address.setText(company.getAddress());
                phone.setText(company.getPhone());
                email.setText(company.getEmail());
                webtext.setText(company.getUrl());
                info.setText(company.getInfo());
            }

            buttonPhone = findViewById(R.id.phone_button);
            buttonPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llamar();
                }
            });

            buttonWeb = findViewById(R.id.web_button);
            buttonWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    web();
                }
            });

            buttonShare = findViewById(R.id.share_button);
            buttonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share();
                }
            });

            buttonSms = findViewById(R.id.sms_button);
            buttonSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sms();
                }
            });

            buttonGmail = findViewById(R.id.gmail_button);
            buttonGmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gmail();
                }
            });

        }catch (Throwable t){
            Log.e(TAG, t.toString(), t);
        }
    }
   private void gmail(){
        String email_text=email.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT,email_text);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
   }

   private void sms(){
        String sms_text=phone.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("address",sms_text);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
   }

   private void web(){
        String url=webtext.getText().toString();

        if (url.startsWith("http://")|| !url.startsWith("https://")){
            url="http://"+url;
        }

        Uri webpage=Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
   }

   private void llamar(){
       if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_REQUEST);
           return;
       }

       String phonenumber=phone.getText().toString();
       Intent intent = new Intent(Intent.ACTION_CALL);
       intent.setData(Uri.parse("tel: "+phonenumber));

       startActivity(intent);
   }

   private void share(){
        String infor=info.getText().toString();
        String namer=name.getText().toString();
        String addressr=address.getText().toString();
        String phoner=phone.getText().toString();
        String webr=webtext.getText().toString();
        String emailr=email.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                "Name: "+namer+
                "Direccion:"+addressr+
                "Telefono:"+phoner+
                "Web:"+webr+
                "Email:"+emailr+
                "Info:"+infor);
        startActivity(intent);
   }


}
