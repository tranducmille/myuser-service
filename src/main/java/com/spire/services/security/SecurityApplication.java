package com.spire.services.security;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.spire.generated.security.jaxrs.api.UsersApi;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;


@Component(immediate = true, service = Application.class, property = { "jaxrs.application=true" })
@ApplicationPath("/security/api/v1")
public class SecurityApplication extends Application  {

	private UsersApi usersApi;
	
	@Reference
	private void setUserApi(UsersApi usersApi) {
		this.usersApi = usersApi;
	}
	
	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {

		// Clear list for modify action
		_singletons.clear();

		// add the automated Jackson marshaller for JSON
		_singletons.add(new JacksonJsonProvider());

		_singletons.add(usersApi);
	}

	public Set<Object> getSingletons() {
		return _singletons;
	}

	private Set<Object> _singletons = new HashSet<Object>();
}