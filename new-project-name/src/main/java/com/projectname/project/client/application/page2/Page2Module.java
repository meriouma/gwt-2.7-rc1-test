package com.projectname.project.client.application.page2;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class Page2Module extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(Page2Presenter.class, Page2Presenter.MyView.class, Page2View.class,
                Page2Presenter.MyProxy.class);
    }
}
