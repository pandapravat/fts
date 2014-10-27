package com.tcs.bbsr.ats.web.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tcs.bbsr.ats.domain.AssociateInfo;

public class AssociateValidator implements Validator{
	 private static final String EMAIL_PATTERN = 
             "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public boolean supports(Class<?> clazz) {
		return AssociateInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object arg0, Errors arg1) {
		AssociateInfo info = (AssociateInfo)arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "employeeName", "empName.reqd", "unresolved mesage");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "employeeId", "empId.reqd", "unresolved mesage");
		
		if(0 == info.getEmployeeId()) {
			arg1.rejectValue("employeeId", "empId.nonzero");
		}
		if(StringUtils.isNotEmpty(info.getEmailId()) && !Pattern.matches(EMAIL_PATTERN, info.getEmailId() )) {
			
			arg1.rejectValue("emailId", "email.invalid");
		}
	}

}
