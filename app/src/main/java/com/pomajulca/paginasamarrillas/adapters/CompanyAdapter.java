package com.pomajulca.paginasamarrillas.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pomajulca.paginasamarrillas.R;
import com.pomajulca.paginasamarrillas.activities.DetailCompanyActivity;
import com.pomajulca.paginasamarrillas.models.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
   private static final String TAG=CompanyAdapter.class.getSimpleName();

   private List<Company> companies;

   private AppCompatActivity activity;

   public CompanyAdapter(AppCompatActivity activity){
       this.activity=activity;
       companies = new ArrayList<>();
   }

   public void setCompanies(List<Company> companies){
       this.companies=companies;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_company,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder viewHolder, int posicion) {
       final Company company = this.companies.get(posicion);

       viewHolder.nameText.setText(company.getName());
       viewHolder.addressText.setText(company.getAddress());
       viewHolder.phoneText.setText(company.getPhone());

        Context context = viewHolder.itemView.getContext();
        int resourceid= context.getResources().getIdentifier(company.getLogo(),"drawable",context.getPackageName());

        viewHolder.pictureImage.setImageResource(resourceid);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: "+company);

                Intent intent = new Intent(activity, DetailCompanyActivity.class);
                intent.putExtra("id",company.getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

       return companies.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        private ImageView pictureImage;
        private TextView nameText;
        private TextView addressText;
        private TextView phoneText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureImage = itemView.findViewById(R.id.logo_Image);
            nameText=itemView.findViewById(R.id.name_text);
            addressText=itemView.findViewById(R.id.address_text);
            phoneText=itemView.findViewById(R.id.phone_text);
        }
    }
}
