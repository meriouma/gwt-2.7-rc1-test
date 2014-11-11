package com.projectname.project.client.polymer;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.projectname.project.client.polymer.element.core.ToolbarElement;

public class Toolbar extends HTMLPanel {
    public Toolbar(String html) {
        super(ToolbarElement.TAG, html);
    }

    public void setHeightClass(HeightClass heightClass) {
        if (!HeightClass.NORMAL.equals(heightClass)) {
            addStyleName(heightClass.getValue());
        }
    }
}
