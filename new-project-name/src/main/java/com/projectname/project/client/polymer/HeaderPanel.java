package com.projectname.project.client.polymer;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.projectname.project.client.polymer.element.core.HeaderPanelElement;

public class HeaderPanel extends HTMLPanel {
    public HeaderPanel(String html) {
        super(HeaderPanelElement.TAG, html);
    }
}
