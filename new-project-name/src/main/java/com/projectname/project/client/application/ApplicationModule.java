package com.projectname.project.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.projectname.project.client.application.page1.Page1Module;
import com.projectname.project.client.application.page2.Page2Module;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new Page1Module());
        install(new Page2Module());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
