package com.projectname.project.client.polymer.event;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.EventHandler;

public class AnimatedPagesTransitionEndEvent
        extends DomEvent<AnimatedPagesTransitionEndEvent.AnimatedPagesTransitionEndHandler> {
    public interface AnimatedPagesTransitionEndHandler extends EventHandler {
        void onAnimatedPagesTransitionEnd(AnimatedPagesTransitionEndEvent event);
    }

    public static Type<AnimatedPagesTransitionEndHandler> getType() {
        return TYPE;
    }

    private static final Type<AnimatedPagesTransitionEndHandler> TYPE =
            new Type<>(PolymerEvents.ANIMATED_PAGES_TRANSITION_END, new AnimatedPagesTransitionEndEvent());

    @Override
    public Type<AnimatedPagesTransitionEndHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AnimatedPagesTransitionEndHandler handler) {
        handler.onAnimatedPagesTransitionEnd(this);
    }
}
