package com.example.api_integration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private TextView txtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Base_url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.txtList = findViewById(R.id.txtList);

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> call = employeeAPI.getAllEmployees();

        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    txtList.setText("Code: " + response.code());
                    return;
                }

                List<Employee> employeeList = response.body();
                for (Employee employee: employeeList) {
                    String data = "";
                    data += "ID" + employee.getId() + "\n";
                    data += "employee_name " + employee.getEmployee_name() + "\n";
                    data += "employee_salary" + employee.getEmployee_salary() + "\n";
                    data += "employee_age" + employee.getEmployee_age() + "\n";
                    data += "profile_image" + employee.getProfile_img() + "\n";

                    txtList.append(data);
                    txtList.append("--------------\n");
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                txtList.setText("Error: " + t.getMessage());
            }
        });
    }
}
