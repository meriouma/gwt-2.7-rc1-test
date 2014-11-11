package com.projectname.project.client.polymer.element.paper;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(PaperFocusableElement.TAG)
public class PaperFocusableElement extends Element {
    public static final String TAG = "paper-focusable";

    public static PaperFocusableElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected PaperFocusableElement() {
    }

    public final native boolean isActive() /*-{
        return this.active;
    }-*/;

    public final native void setActive(boolean active) /*-{
        this.active = active;
    }-*/;

    public final native boolean isFocused() /*-{
        return this.focused;
    }-*/;

    public final native void setFocused(boolean focused) /*-{
        this.focused = focused;
    }-*/;

    public final native boolean isPressed() /*-{
        return this.pressed;
    }-*/;

    public final native void setPressed(boolean pressed) /*-{
        this.pressed = pressed;
    }-*/;

    public final native boolean isDisabled() /*-{
        return this.disabled;
    }-*/;

    public final native void setDisabled(boolean disabled) /*-{
        this.disabled = disabled;
    }-*/;

    public final native boolean isToggle() /*-{
        return this.isToggle;
    }-*/;

    public final native void setToggle(boolean isToggle) /*-{
        this.isToggle = isToggle;
    }-*/;
}
