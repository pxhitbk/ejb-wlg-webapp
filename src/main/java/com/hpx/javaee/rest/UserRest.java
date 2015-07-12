package com.hpx.javaee.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hpx.javaee.entity.User;
import com.hpx.javaee.rest.model.LoginForm;
import com.hpx.javaee.service.UserBean;

@Stateless
@Path("/user")
public class UserRest extends BaseRest{
	@EJB
	private UserBean userBean;
	
	@GET
	@Path("index")
	public Response index() {
		return dispatchEmpty(HttpServletResponse.SC_OK, new IProcessEmpty() {

			@Override
			public void process() {
			}
		});
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(final User user) {
		return dispatch(HttpServletResponse.SC_CREATED, user, new IProcessResult<User>() {
			@Override
			public User process() {
				 return userBean.create(user.getUsername(), user.getPassword());
			}
		});
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response get() {
		User u = new User();
		u.setPassword("$$$$$$$");
		u.setUsername("august");
		return Response.status(200).entity(u).build();
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginForm form) {
		if(form.getUsername() == null) {
			return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity("Invalid name").build();
		}
		User user = new User();
		user.setUsername(form.getUsername());
		user.setPassword("**********");
		return Response.status(200).entity(user).build();
	}
}

