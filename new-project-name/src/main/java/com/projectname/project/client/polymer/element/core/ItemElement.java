package com.projectname.project.client.polymer.element.core;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(ItemElement.TAG)
public class ItemElement extends Element {
    public static final String TAG = "core-item";

    public static ItemElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected ItemElement() {
    }
}
