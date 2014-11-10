package com.projectname.project.client.application.page1;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class Page1View extends ViewImpl implements Page1Presenter.MyView {
    interface Binder extends UiBinder<Widget, Page1View> {
    }

    @Inject
    Page1View(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
