package com.projectname.project.client.polymer.element.core;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(AnimatedPagesElement.TAG)
public class AnimatedPagesElement extends Element {
    public static final String TAG = "core-animated-pages";

    public static AnimatedPagesElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected AnimatedPagesElement() {
    }
}
