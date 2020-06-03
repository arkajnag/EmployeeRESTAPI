package com.employee.Utilities;

import java.io.File;
import java.util.Map;
import java.util.function.Predicate;
import com.employee.ExceptionHandler.NetworkException;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface WebServiceUtils {

	public static Response getRequest(String endPointURI, Map<String,String> header) throws NetworkException {
		try {
				RequestSpecification reqSpec = RestAssured.given().relaxedHTTPSValidation();
				reqSpec.headers(header);
				return reqSpec.request(Method.GET, endPointURI);
		}
		catch(Exception e) {
			throw new NetworkException("Exception in :"+Thread.currentThread().getStackTrace()[1].getMethodName()+" and Exception details:"+e.getMessage());
		}
	}

	public static Response deleteRequest(String endPointURI, Map<String,String> header) throws NetworkException {
		try {
				RequestSpecification reqSpec = RestAssured.given().relaxedHTTPSValidation();
				reqSpec.headers(header);
				return reqSpec.request(Method.DELETE, endPointURI);
		}catch(Exception e) {
			throw new NetworkException("Exception in :"+Thread.currentThread().getStackTrace()[1].getMethodName()+" and Exception details:"+e.getMessage());
		}
	}
	
	public static Response postRequest(String endPointURI, Map<String,String> header, String jsonString) throws NetworkException {
		try {
				RequestSpecification reqSpec = RestAssured.given().relaxedHTTPSValidation();
				reqSpec.headers(header);
				reqSpec.body(jsonString);
				return reqSpec.request(Method.POST, endPointURI);
		}catch(Exception e) {
			throw new NetworkException("Exception in :"+Thread.currentThread().getStackTrace()[1].getMethodName()+" and Exception details:"+e.getMessage());
		}
	}
	
	public static Response postRequest(String endPointURI, Map<String,String> header, File jsonFile) throws NetworkException {
		try {
				RequestSpecification reqSpec = RestAssured.given().relaxedHTTPSValidation();
				reqSpec.headers(header);
				reqSpec.body(jsonFile);
				return reqSpec.request(Method.POST, endPointURI);
		}catch(Exception e) {
			throw new NetworkException("Exception in :"+Thread.currentThread().getStackTrace()[1].getMethodName()+" and Exception details:"+e.getMessage());
		}
	}
	
	public static Response putRequest(String endPointURI, Map<String,String> header, String jsonString) throws NetworkException {
		try {
				RequestSpecification reqSpec = RestAssured.given().relaxedHTTPSValidation();
				reqSpec.headers(header);
				reqSpec.body(jsonString);
				return reqSpec.request(Method.PUT, endPointURI);
		}catch(Exception e) {
			throw new NetworkException("Exception in :"+Thread.currentThread().getStackTrace()[1].getMethodName()+" and Exception details:"+e.getMessage());
		}
	}
	
	public static Response patchRequest(String endPointURI, Map<String,String> header, String jsonString) throws NetworkException {
		try {
			RequestSpecification reqSpec = RestAssured.given().relaxedHTTPSValidation();
			reqSpec.headers(header);
			reqSpec.body(jsonString);
			return reqSpec.request(Method.PATCH, endPointURI);
		}catch(Exception e) {
			throw new NetworkException("Exception in :"+Thread.currentThread().getStackTrace()[1].getMethodName()+" and Exception details:"+e.getMessage());
		}
	}
	
	public static Predicate<Response> predHttpResponse = t-> (t.getStatusCode()==200)?true:false;
}





