package com.projectname.project.client.polymer.event;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.EventHandler;

public class MediaChangeEvent extends DomEvent<MediaChangeEvent.MediaChangeHandler> {
    public interface MediaChangeHandler extends EventHandler {
        void onMediaChange(MediaChangeEvent event);
    }

    public static Type<MediaChangeHandler> getType() {
        return TYPE;
    }

    private static final Type<MediaChangeHandler> TYPE =
            new Type<>(PolymerEvents.MEDIA_CHANGE, new MediaChangeEvent());

    public boolean matches() {
        return matches(getNativeEvent());
    }

    @Override
    public Type<MediaChangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(MediaChangeHandler handler) {
        handler.onMediaChange(this);
    }

    private native boolean matches(NativeEvent nativeEvent) /*-{
        return nativeEvent.detail.matches;
    }-*/;
}
