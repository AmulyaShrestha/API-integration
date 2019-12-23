package com.example.api_integration.Interface;

import com.example.api_integration.Model.Employee;
import com.example.api_integration.Model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employee/{id}")
    Call<Employee> getById(@Path("id") int id);

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

//    @PUT("")
}
