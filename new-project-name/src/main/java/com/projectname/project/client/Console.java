package com.projectname.project.client;

public class Console {
    public static native void log(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;
}
