package com.projectname.project.client.rest;

import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RestModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new RestDispatchAsyncModule.Builder().build());

        bindConstant().annotatedWith(RestApplicationPath.class).to("/rest");
    }
}
