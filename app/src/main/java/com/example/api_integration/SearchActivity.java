package com.example.api_integration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.api_integration.Interface.EmployeeAPI;
import com.example.api_integration.Model.Employee;
import com.example.api_integration.base_url.Base_url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txtData;
    private EditText etID;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.bindProperties();

        btnSearch.setOnClickListener(this);
    }

    private void bindProperties() {
        this.btnSearch = findViewById(R.id.btnSearch);
        this.txtData = findViewById(R.id.txtData);
        this.etID= findViewById(R.id.etID);
    }

    @Override
    public void onClick(View v) {
        this.loadData();
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Employee> call = employeeAPI.getById(Integer.parseInt(etID.getText().toString()));

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (!response.isSuccessful()) {
                    txtData.setText("Code: " + response.code());
                    return;
                }

                Employee employee = response.body();
                    String data = "";
                    data += "ID" + employee.getId() + "\n";
                    data += "employee_name " + employee.getEmployee_name() + "\n";
                    data += "employee_salary" + employee.getEmployee_salary() + "\n";
                    data += "employee_age" + employee.getEmployee_age() + "\n";
                    data += "profile_image" + employee.getProfile_img() + "\n";

                    txtData.setText(data);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                txtData.setText("Error: " + t.getMessage());
            }
        });
    }
}
