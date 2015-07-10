package com.hpx.javaee.rest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Response;

import com.hpx.javaee.util.ApplicationErrorCode;
import com.hpx.javaee.util.ApplicationException;
import com.hpx.javaee.util.ServiceError;

public class BaseRest{
	
	private static final Map<ApplicationErrorCode, Integer> ERROR_CONVERSION = new HashMap<ApplicationErrorCode, Integer>();
	static {
		ERROR_CONVERSION.put(ApplicationErrorCode.UNHANDED_ERROR, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ERROR_CONVERSION.put(ApplicationErrorCode.INVALID_NAME_TYPE, HttpServletResponse.SC_BAD_GATEWAY);
		ERROR_CONVERSION.put(ApplicationErrorCode.USER_EXISTED, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ERROR_CONVERSION.put(ApplicationErrorCode.INVALID_INPUT, HttpServletResponse.SC_BAD_REQUEST);
	}
	
	protected <T, F> Response dispatch(int successStatus, F form, IProcessResult<T> proccessor) {
		T result = null;
		try {
			validate(form);
			result = proccessor.process();
		} catch (ApplicationException appException) {
			return error(appException);
		}
		catch (Exception otherException) {
			if (otherException.getCause() instanceof ApplicationException) {
				return error((ApplicationException) otherException.getCause());
			}
			return unhandlerError(otherException);
		}
		return Response.status(successStatus).entity(result).build();
	}

	protected Response dispatchEmpty(int successStatus, IProcessEmpty proccessor) {
		try {
			proccessor.process();
		} catch (ApplicationException appException) {
			return error(appException);
		}
		catch (Exception otherException) {
			if (otherException.getCause() instanceof ApplicationException) {
				return error((ApplicationException) otherException.getCause());
			}
			return unhandlerError(otherException);
		}
		return Response.status(successStatus).build();
	}
	
	private <F> void validate(F form) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<F>> constraintViolations = validatorFactory.getValidator().validate(form);
		StringBuilder message = new StringBuilder();
		Iterator<ConstraintViolation<F>> iter = constraintViolations.iterator();
		for (int i = 0; i < constraintViolations.size(); i++) {
			ConstraintViolation<F> violation = iter.next();
			message.append(violation.getMessage());
			if (iter.hasNext()) message.append("/");
		}
		if (message.length() > 0) {
			throw new ApplicationException(ApplicationErrorCode.INVALID_INPUT, message.toString());
		}
	}
	
	protected Response error(ApplicationException e) {
		Integer httpCode = ERROR_CONVERSION.get(e.getCode());
		if(httpCode == null) 
			return unhandlerError(e);
		ServiceError error = new ServiceError(e.getCode().toString(), e.getMessage());
		return Response.status(httpCode).entity(error).build();
	}
	
	protected Response unhandlerError(Exception e) {
		return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).
				entity(new ServiceError(ApplicationErrorCode.UNHANDED_ERROR.toString(), e.getMessage())).build();
	}
}
