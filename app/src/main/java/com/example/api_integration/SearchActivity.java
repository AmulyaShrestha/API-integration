package com.example.api_integration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_integration.Interface.EmployeeAPI;
import com.example.api_integration.Model.Employee;
import com.example.api_integration.Model.EmployeeCUD;
import com.example.api_integration.base_url.Base_url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txtData;
    private EditText etID, etName, etAge, etSalary;
    private Button btnSearch, btnUpdate, btnDelete;
    private EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.bindProperties();

        btnSearch.setOnClickListener(this);
    }

    private void bindProperties() {
        this.btnSearch = findViewById(R.id.btnSearch);
        this.btnUpdate = findViewById(R.id.btnUpdate);
        this.btnDelete = findViewById(R.id.btnDelete);
        this.txtData = findViewById(R.id.txtData);
        this.etID= findViewById(R.id.etID);
        this.etAge= findViewById(R.id.etAge);
        this.etName= findViewById(R.id.etName);
        this.etSalary= findViewById(R.id.etSalary);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.employeeAPI = retrofit.create(EmployeeAPI.class);

        this.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee(employeeAPI);
            }
        });

        this.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee(employeeAPI);
            }
        });
    }

    @Override
    public void onClick(View v) {
        this.loadData(employeeAPI);
    }

    private void loadData(EmployeeAPI employeeAPI) {

        Call<Employee> call = employeeAPI.getById(Integer.parseInt(etID.getText().toString()));

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (!response.isSuccessful()) {
                    txtData.setText("Code: " + response.code());
                    return;
                }

                Employee employee = response.body();
                etName.setText(employee.getEmployee_name());
                etAge.setText(String.valueOf(employee.getEmployee_age()));
                etSalary.setText(String.valueOf(employee.getEmployee_salary()));
                    /*String data = "";
                    data += "ID" + employee.getId() + "\n";
                    data += "employee_name " + employee.getEmployee_name() + "\n";
                    data += "employee_salary" + employee.getEmployee_salary() + "\n";
                    data += "employee_age" + employee.getEmployee_age() + "\n";
                    data += "profile_image" + employee.getProfile_img() + "\n";*/
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                txtData.setText("Error: " + t.getMessage());
            }
        });
    }

    private void updateEmployee(EmployeeAPI employeeAPI) {
        EmployeeCUD employeeCUD = new EmployeeCUD(this.etName.getText().toString(),
                Float.parseFloat(this.etSalary.getText().toString()),
                Integer.parseInt(this.etAge.getText().toString()), null);
        Call<Void> call = employeeAPI.updateEmployee(Integer.parseInt(this.etID.getText().toString()), employeeCUD);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SearchActivity.this, "Success updated!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Failed!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteEmployee(EmployeeAPI employeeAPI) {
        Call<Void> call = employeeAPI.deleteEmployee(Integer.parseInt(this.etID.getText().toString()));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SearchActivity.this, "Successfully deleted!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Failed!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
