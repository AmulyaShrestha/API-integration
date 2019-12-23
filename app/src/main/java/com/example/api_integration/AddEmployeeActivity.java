package com.example.api_integration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.api_integration.Interface.EmployeeAPI;
import com.example.api_integration.Model.EmployeeCUD;
import com.example.api_integration.base_url.Base_url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etAge, etName, etSalary;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        this.etAge = findViewById(R.id.etAge);
        this.etName = findViewById(R.id.etName);
        this.etSalary = findViewById(R.id.etSalary);
        this.btnAdd = findViewById(R.id.btnAdd);

        this.btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        saveData();
    }

    private void saveData() {
        String name = etName.getText().toString();
        Float salary = Float.parseFloat(etSalary.getText().toString());
        int age = Integer.parseInt(etAge.getText().toString());

        EmployeeCUD employee = new EmployeeCUD( name, salary, age, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Void> voidCall = employeeAPI.registerEmployee(employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEmployeeActivity.this, "SUCCESSFULLY REGISTERED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEmployeeActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
