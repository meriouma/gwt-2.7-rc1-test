package com.projectname.project.client;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class MyBootstrapper implements Bootstrapper {
    private final PlaceManager placeManager;

    @Inject
    MyBootstrapper(PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public void onBootstrap() {
        log("Bootstrapping");

        placeManager.revealDefaultPlace();
    }

    private native void log(String message) /*-{
        console.log(message);
    }-*/;
}
