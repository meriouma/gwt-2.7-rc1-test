package com.projectname.project.client.polymer.element.paper;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TagName;

@TagName(PaperIconButtonElement.TAG)
public class PaperIconButtonElement extends PaperFocusableElement {
    public static final String TAG = "paper-icon-button";

    public static PaperIconButtonElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected PaperIconButtonElement() {
    }
}
