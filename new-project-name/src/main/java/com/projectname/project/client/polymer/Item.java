package com.projectname.project.client.polymer;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.projectname.project.client.polymer.element.core.ItemElement;

public class Item extends HTMLPanel {
    public Item(String html) {
        super(ItemElement.TAG, html);
    }

    public void setLabel(String label) {
        getElement().setAttribute("label", label);
    }
}
