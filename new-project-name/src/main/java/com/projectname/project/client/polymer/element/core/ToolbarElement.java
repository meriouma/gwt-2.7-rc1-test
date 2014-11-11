package com.projectname.project.client.polymer.element.core;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(ToolbarElement.TAG)
public class ToolbarElement extends Element {
    public static final String TAG = "core-toolbar";

    public static ToolbarElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected ToolbarElement() {
    }
}
