package com.spring.exam.sys.validatior;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(arg0);
		// return Message.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
	}
}
