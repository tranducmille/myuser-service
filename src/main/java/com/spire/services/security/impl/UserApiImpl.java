package com.spire.services.security.impl;

import com.spire.generated.jaxrs.model.User;
import com.spire.generated.security.jaxrs.api.UsersApi;

import java.util.List;
import com.spire.generated.security.jaxrs.api.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = UsersApi.class)
public class UserApiImpl implements UsersApi {

	@Activate
	public void activate() throws Exception {
		System.out.println("Activating....UsersApi");
	}

	@Override
	public Response createUser(User user, SecurityContext securityContext, HttpServletRequest request ) throws NotFoundException {
		UserManager.getInstance().addUser(user);
		List<User> users = UserManager.getInstance().getUsers();
		return Response.ok().entity(users).build();
	}

	@Override
	public Response deleteUser(String userId, SecurityContext securityContext, HttpServletRequest request )throws NotFoundException {
		UserManager.getInstance().deleteUserById(Integer.valueOf(userId));
		List<User> users = UserManager.getInstance().getUsers();
		return Response.ok().entity(users).build();
	}

	@Override
	public Response getUsers(SecurityContext securityContext, HttpServletRequest request) throws NotFoundException {
		List<User> users = UserManager.getInstance().getUsers();
		return Response.ok().entity(users).build();
	}

	@Override
	public Response updateUser(User user, SecurityContext securityContext, HttpServletRequest request) throws NotFoundException {
		UserManager.getInstance().updateUser(user);
		List<User> users = UserManager.getInstance().getUsers();
		return Response.ok().entity(users).build();
	}
}
