package com.pomajulca.paginasamarrillas.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.pomajulca.paginasamarrillas.R;
import com.pomajulca.paginasamarrillas.adapters.CompanyAdapter;
import com.pomajulca.paginasamarrillas.models.Company;
import com.pomajulca.paginasamarrillas.repositories.CompanyRepository;

import java.util.List;

public class ResultBuscarActivity extends AppCompatActivity {

    private TextView resultText;
    private RecyclerView recyclerView;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_buscar);

        recyclerView=findViewById(R.id.companylist);
        resultText=findViewById(R.id.textFind);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.category=getIntent().getExtras().getString("category");
        List<Company> companies= CompanyRepository.filterCompaniesByCategory(category);

        CompanyAdapter adapter=new CompanyAdapter(this);
        adapter.setCompanies(companies);

        int count=adapter.getItemCount();
        if(count==0){
            resultText.setText("No existe la categoria"+category);

        }else {
            resultText.setText("Encontrados"+category);
            recyclerView.setAdapter(adapter);
        }
    }
}
