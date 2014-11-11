package com.projectname.project.client.polymer;

import com.projectname.project.client.polymer.element.paper.PaperItemElement;

public class PaperItem extends PaperIconButton {
    public PaperItem() {
        super(PaperItemElement.create());
    }

    public void setLabel(String label) {
        getElement().setAttribute("label", label);
    }
}
