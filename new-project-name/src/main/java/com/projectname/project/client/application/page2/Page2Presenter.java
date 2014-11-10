package com.projectname.project.client.application.page2;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.projectname.project.client.application.ApplicationPresenter;
import com.projectname.project.client.place.NameTokens;

public class Page2Presenter extends Presenter<Page2Presenter.MyView, Page2Presenter.MyProxy> {
    interface MyView extends View {
    }

    @ProxyStandard
    @NameToken(NameTokens.PAGE2)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<Page2Presenter> {
    }

    @Inject
    Page2Presenter(EventBus eventBus,
                   MyView view,
                   MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
    }
}
