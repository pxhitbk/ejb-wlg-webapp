package com.hpx.javaee.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
	}
	
	protected <T> Response dispatch(int successStatus, IProcessResult<T> proccessor) {
		T result = null;
		try {
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
