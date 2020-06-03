package com.employee.Utilities;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface CommonUtils {

	public static <T> List<T> verifyExistenceOfData(List<T> genericList,Function<? super T,Object> getValidateData,Object dataToValidate) {
		try {
			return genericList.stream().filter(t->getValidateData.apply(t).equals(dataToValidate)).collect(Collectors.toList());
		}catch(Exception e) {
			throw new RuntimeException("Error in: "+Thread.currentThread().getStackTrace()[2].getMethodName());
		}
	}
	
	public static BinaryOperator<Integer> randomValue = (minVal,maxVal)-> (int) ((Math.random()*maxVal)-(minVal+1));

}
