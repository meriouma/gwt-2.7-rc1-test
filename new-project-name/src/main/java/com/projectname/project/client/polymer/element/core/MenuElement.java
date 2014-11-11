package com.projectname.project.client.polymer.element.core;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(MenuElement.TAG)
public class MenuElement extends Element {
    public static final String TAG = "core-menu";

    public static MenuElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected MenuElement() {
    }
}
