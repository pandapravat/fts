package com.pravatpanda.apps.ats.web.validator;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pravatpanda.apps.ats.domain.ui.NewOdcVO;

public class ODCValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return NewOdcVO.class.isAssignableFrom(clazz);
	}

	public void validate(Object arg0, Errors arg1) {

		NewOdcVO vo = (NewOdcVO) arg0;
		String odcId = vo.getOdcId();

		if(StringUtils.isBlank(odcId)) {
			arg1.rejectValue("odcId", "odcId.reqd");
		} else if(availableFloors.containsKey(odcId)) {
			arg1.rejectValue("odcId", "odcId.duplicate");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "odcName", "odcName.reqd");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "establishmentName", "est.reqd");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "city", "city.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "floorNumber", "floorNumber.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "wing", "wing.required");


		if(0 == vo.getRows()) {
			arg1.rejectValue("rows", "rows.nonzero");
		}
		if(0 == vo.getColumns()) {
			arg1.rejectValue("columns", "columns.nonzero");
		}
	}

	Map<String, String> availableFloors;

	public Map<String, String> getAvailableFloors() {
		return availableFloors;
	}

	public void setAvailableFloors(Map<String, String> availableFloors) {
		this.availableFloors = availableFloors;
	}
}
