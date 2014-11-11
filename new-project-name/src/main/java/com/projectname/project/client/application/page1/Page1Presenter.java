package com.projectname.project.client.application.page1;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.projectname.project.client.application.ApplicationPresenter;
import com.projectname.project.client.place.NameTokens;
import com.projectname.project.client.rest.SomeService;

public class Page1Presenter extends Presenter<Page1Presenter.MyView, Page1Presenter.MyProxy> {
    interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.PAGE1)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<Page1Presenter> {
    }

    private final RestDispatchAsync dispatcher;
    private final SomeService someService;

    @Inject
    Page1Presenter(EventBus eventBus,
                   MyView view,
                   MyProxy proxy,
                   RestDispatchAsync dispatcher,
                   SomeService someService) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);

        this.dispatcher = dispatcher;
        this.someService = someService;
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        dispatcher.execute(someService.getSomething(), new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
            }

            @Override
            public void onSuccess(String s) {
            }
        });
    }
}
