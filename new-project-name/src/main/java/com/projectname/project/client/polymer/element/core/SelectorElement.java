package com.projectname.project.client.polymer.element.core;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(SelectorElement.TAG)
public class SelectorElement extends Element {
    public static final String TAG = "core-selector";

    public static SelectorElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected SelectorElement() {
    }
}
