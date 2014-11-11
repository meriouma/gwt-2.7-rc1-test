package com.projectname.project.client.polymer;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.projectname.project.client.polymer.element.core.AnimatedPagesElement;

public class AnimatedPages extends HTMLPanel {
    public AnimatedPages(String html) {
        super(AnimatedPagesElement.TAG, html);
    }

    public void setTransitions(String transitions) {
        getElement().setAttribute("transitions", transitions);
    }
}
