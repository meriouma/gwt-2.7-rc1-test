package com.projectname.project.client.polymer;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.projectname.project.client.polymer.element.ElementHelper;
import com.projectname.project.client.polymer.element.core.DrawerPanelElement;

public class DrawerPanel extends HTMLPanel {
    public DrawerPanel(String html) {
        super(DrawerPanelElement.TAG, html);

        Scheduler.get().scheduleEntry(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                DOM.getChild(getElement(), 0).setAttribute("drawer", "");
                DOM.getChild(getElement(), 1).setAttribute("main", "");
            }
        });
    }

    public void togglePanel() {
        getComponentElement().togglePanel();
    }

    public void openDrawer() {
        getComponentElement().openDrawer();
    }

    public void closeDrawer() {
        getComponentElement().closeDrawer();
    }

    public void setResponsiveWidth(String responsiveWidth) {
        ElementHelper.setProperty(getElement(), "responsiveWidth", responsiveWidth);
    }

    private DrawerPanelElement getComponentElement() {
        return getElement().cast();
    }
}
