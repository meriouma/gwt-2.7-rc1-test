package com.projectname.project.client.polymer.element;

import com.google.gwt.dom.client.Element;

public class ElementHelper {
    public static <T> void setProperty(Element element, String property, T value) {
        setPropertyImpl(element, property, value);
    }

    public static <T> T getProperty(Object element, String property) {
        return getPropertyImpl(element, property);
    }

    private static native void setPropertyImpl(Element element, String property, Object value) /*-{
        element[property] = value;
    }-*/;

    private static native <T> T getPropertyImpl(Object element, String property) /*-{
        return element[property];
    }-*/;
}
