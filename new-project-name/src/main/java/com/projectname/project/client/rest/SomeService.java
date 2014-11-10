package com.projectname.project.client.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;

@Path("somepath")
public interface SomeService {
    @GET
    RestAction<String> getSomething();
}
