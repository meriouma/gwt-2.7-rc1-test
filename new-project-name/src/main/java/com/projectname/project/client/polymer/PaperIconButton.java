package com.projectname.project.client.polymer;

import com.google.gwt.dom.client.Element;
import com.projectname.project.client.polymer.element.paper.PaperIconButtonElement;

import static com.projectname.project.client.polymer.element.ElementHelper.getProperty;
import static com.projectname.project.client.polymer.element.ElementHelper.setProperty;

public class PaperIconButton extends PaperButton {
    private static final String ICON = "icon";
    private static final String ICON_SRC = "iconSrc";

    public PaperIconButton() {
        this(PaperIconButtonElement.create());
    }

    protected PaperIconButton(Element element) {
        super(element);
    }

    public final String getIcon() {
        return getProperty(getElement(), ICON);
    }

    public final void setIcon(String icon) {
        setSrc(null);
        setProperty(getElement(), ICON, icon);
    }

    public final String getSrc() {
        return getProperty(getElement(), ICON_SRC);
    }

    public final void setSrc(String src) {
        setProperty(getElement(), ICON_SRC, src);
    }
}
