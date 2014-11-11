package com.projectname.project.client.polymer.element.paper;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(PaperButtonElement.TAG)
public class PaperButtonElement extends PaperFocusableElement {
    public static final String TAG = "paper-button";

    public static PaperButtonElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected PaperButtonElement() {
    }

    public final native boolean isRaised() /*-{
        return this.raised;
    }-*/;

    public final native void setRaised(boolean raised) /*-{
        this.raised = raised;
    }-*/;

    public final native boolean isRecenteringTouch() /*-{
        return this.recenteringTouch;
    }-*/;

    public final native void setRecenteringTouch(boolean recenteringTouch) /*-{
        this.recenteringTouch = recenteringTouch;
    }-*/;

    public final native boolean isFill() /*-{
        return this.fill;
    }-*/;

    public final native void setFill(boolean fill) /*-{
        this.fill = fill;
    }-*/;
}
