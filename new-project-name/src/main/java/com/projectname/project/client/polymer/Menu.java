package com.projectname.project.client.polymer;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.projectname.project.client.polymer.element.core.MenuElement;

public class Menu extends HTMLPanel {
    public Menu(String html) {
        super(MenuElement.TAG, html);
    }
}
