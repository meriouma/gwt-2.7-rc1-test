package com.projectname.project.client.application;

import javax.inject.Inject;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.projectname.project.client.polymer.AnimatedPages;
import com.projectname.project.client.polymer.DrawerPanel;
import com.projectname.project.client.polymer.PaperIconButton;
import com.projectname.project.client.polymer.PaperItem;
import com.projectname.project.client.polymer.Toolbar;
import com.projectname.project.client.polymer.event.AnimatedPagesTransitionEndEvent;

public class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {

    private SimplePanel oldMain;

    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    Toolbar toolbar;
    @UiField
    PaperIconButton menu;
    @UiField
    DrawerPanel drawerPanel;
    @UiField
    PaperItem pageOne;
    @UiField
    PaperItem pageTwo;
    @UiField
    AnimatedPages pages;
    @UiField
    Element more;

    private SimplePanel main;
    private SimplePanel mainOld;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        mainOld = new SimplePanel();

        menu.getElement().setAttribute("core-drawer-toggle", "");

        asWidget().addDomHandler(new AnimatedPagesTransitionEndEvent.AnimatedPagesTransitionEndHandler() {
            @Override
            public void onAnimatedPagesTransitionEnd(AnimatedPagesTransitionEndEvent event) {
                oldMain.removeFromParent();
                pages.getElement().setPropertyInt("selected", 0);
            }
        }, AnimatedPagesTransitionEndEvent.getType());
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.SLOT_MAIN_CONTENT) {
            if (pages.getWidgetCount() == 0) {
                main = new SimplePanel(content.asWidget());
                pages.add(main);
            } else {
                oldMain = main;
                main = new SimplePanel(content.asWidget());
                main.setWidget(content);
                pages.add(main);
                pages.getElement().setPropertyInt("selected", 1);
            }
        }
    }

    @UiHandler("pageOne")
    void onPageOneClicked(ClickEvent e) {
        getUiHandlers().goToPageOne();
        drawerPanel.closeDrawer();
    }

    @UiHandler("pageTwo")
    void onPageTwoClicked(ClickEvent e) {
        getUiHandlers().goToPageTwo();
        drawerPanel.closeDrawer();
    }
}
