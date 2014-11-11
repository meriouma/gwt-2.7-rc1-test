package com.projectname.project.client.polymer.element.core;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(HeaderPanelElement.TAG)
public class HeaderPanelElement extends Element {
    public static final String TAG = "core-header-panel";

    public static HeaderPanelElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected HeaderPanelElement() {
    }
}
