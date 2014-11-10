package com.projectname.project.client.gin;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.projectname.project.client.application.ApplicationModule;
import com.projectname.project.client.place.NameTokens;
import com.projectname.project.client.rest.RestModule;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder().build());
        install(new RestModule());
        install(new ApplicationModule());

        // DefaultPlaceManager Places
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.PAGE1);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.PAGE1);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.PAGE2);
    }
}
