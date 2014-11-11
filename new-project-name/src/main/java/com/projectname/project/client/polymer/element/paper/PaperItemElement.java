package com.projectname.project.client.polymer.element.paper;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TagName;

@TagName(PaperItemElement.TAG)
public class PaperItemElement extends PaperFocusableElement {
    public static final String TAG = "paper-item";

    public static PaperItemElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected PaperItemElement() {
    }
}
