package com.example.demo.Service;

import com.example.demo.Request.EmployeeRequest;
import com.example.demo.Response.EmployeeResponse;

public interface MyService {

	EmployeeResponse save(EmployeeRequest e);

}
