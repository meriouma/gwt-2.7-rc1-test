package com.projectname.project.client.polymer;

import com.google.gwt.dom.client.Element;
import com.projectname.project.client.polymer.element.paper.PaperButtonElement;

public class PaperButton extends PaperFocusableWidget {
    public PaperButton() {
        super(PaperButtonElement.create());
    }

    protected PaperButton(Element element) {
        super(element);
    }
}
