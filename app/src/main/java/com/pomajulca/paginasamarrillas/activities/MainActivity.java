package com.pomajulca.paginasamarrillas.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pomajulca.paginasamarrillas.R;
import com.pomajulca.paginasamarrillas.adapters.CompanyAdapter;
import com.pomajulca.paginasamarrillas.models.Company;
import com.pomajulca.paginasamarrillas.repositories.CompanyRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private EditText editTextsearch;
     private Button buttonsearch;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextsearch=findViewById(R.id.search_text);
        buttonsearch=findViewById(R.id.button_search);
        findViewById(R.id.logo).setOnClickListener(this);

        recyclerView=findViewById(R.id.companylist);
        //especificar la logica de la cual estara organizada los items

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CompanyAdapter adapter=new CompanyAdapter(this);

        List<Company> companies = CompanyRepository.getCompanies();
        adapter.setCompanies(companies);
        recyclerView.setAdapter(adapter);


        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
    }

    private void buscar(){
        String category=editTextsearch.getText().toString().trim();

        if (!category.isEmpty()){
            Intent intent = new Intent(this,ResultBuscarActivity.class);

            intent.putExtra("category",category);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Ingrese una categoria",Toast.LENGTH_SHORT).show();
        }
    }


    public void onClick(View v) {

        int color;

        View contenedor = v.getRootView();

        switch (v.getId()) {
            case R.id.logo:
                color = Color.parseColor("#0F0E0E"); // negro
                break;
            default:
                color = Color.WHITE; // Blanco
        }

        contenedor.setBackgroundColor(color);
    }
}
