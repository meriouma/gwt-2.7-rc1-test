package com.projectname.project.client.application.page2;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class Page2View extends ViewImpl implements Page2Presenter.MyView {
    interface Binder extends UiBinder<Widget, Page2View> {
    }

    @Inject
    Page2View(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
