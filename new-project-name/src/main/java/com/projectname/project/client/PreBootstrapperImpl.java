package com.projectname.project.client;

import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.gwtplatform.mvp.client.PreBootstrapper;

public class PreBootstrapperImpl implements PreBootstrapper {
    @Override
    public void onPreBootstrap() {
        log("Prebootstrapping");

        MGWTSettings.ViewPort viewPort = new MGWTSettings.ViewPort();

        viewPort.setUserScaleAble(false).setMinimumScale(1.0)
                .setMinimumScale(1.0).setMaximumScale(1.0);

        MGWTSettings settings = new MGWTSettings();
        settings.setViewPort(viewPort);
        settings.setFullscreen(true);
        settings.setFixIOS71BodyBug(true);
        settings.setPreventScrolling(true);

        settings.setStatusBarStyle(MGWTSettings.StatusBarStyle.BLACK);

        MGWT.applySettings(settings);
    }

    private native void log(String message) /*-{
        console.log(message);
    }-*/;
}
