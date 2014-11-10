package com.projectname.project.client.application.page1;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class Page1Module extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(Page1Presenter.class, Page1Presenter.MyView.class, Page1View.class,
                Page1Presenter.MyProxy.class);
    }
}
