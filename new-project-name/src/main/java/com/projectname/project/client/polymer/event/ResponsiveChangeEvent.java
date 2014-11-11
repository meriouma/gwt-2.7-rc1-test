package com.projectname.project.client.polymer.event;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.EventHandler;

public class ResponsiveChangeEvent extends DomEvent<ResponsiveChangeEvent.ResponsiveChangeHandler> {
    public interface ResponsiveChangeHandler extends EventHandler {
        void onResponsiveChange(ResponsiveChangeEvent event);
    }

    public static Type<ResponsiveChangeHandler> getType() {
        return TYPE;
    }

    private static final Type<ResponsiveChangeHandler> TYPE =
            new Type<>(PolymerEvents.RESPONSIVE_CHANGE, new ResponsiveChangeEvent());

    @Override
    public Type<ResponsiveChangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ResponsiveChangeHandler handler) {
        handler.onResponsiveChange(this);
    }
}
