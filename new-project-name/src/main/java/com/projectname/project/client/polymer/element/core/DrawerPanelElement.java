package com.projectname.project.client.polymer.element.core;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TagName;

@TagName(DrawerPanelElement.TAG)
public class DrawerPanelElement extends Element {
    public static final String TAG = "core-drawer-panel";

    public static DrawerPanelElement create() {
        return Document.get().createElement(TAG).cast();
    }

    protected DrawerPanelElement() {
    }

    public final native void togglePanel() /*-{
        this.togglePanel();
    }-*/;

    public final native void openDrawer() /*-{
        this.openDrawer();
    }-*/;

    public final native void closeDrawer() /*-{
        this.closeDrawer();
    }-*/;
}
