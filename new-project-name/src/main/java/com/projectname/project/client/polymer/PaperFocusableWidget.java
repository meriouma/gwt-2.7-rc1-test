package com.projectname.project.client.polymer;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FocusWidget;

public class PaperFocusableWidget extends FocusWidget {
    protected <T extends Element> T getComponentElement() {
        return getElement().cast();
    }

    protected PaperFocusableWidget(Element element) {
        super(element);
    }
}
