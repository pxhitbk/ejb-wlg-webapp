package com.hpx.javaee.interceptor;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class JerseyRequestFilter implements ContainerRequestFilter{

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		System.out.println("filter request");
		return request;
	}

}
