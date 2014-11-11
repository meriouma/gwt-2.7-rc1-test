package com.projectname.project.client.polymer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasConstrainedValue;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SimpleKeyProvider;
import com.projectname.project.client.polymer.element.core.SelectorElement;

import static com.projectname.project.client.polymer.element.ElementHelper.getProperty;
import static com.projectname.project.client.polymer.element.ElementHelper.setProperty;

public class Selector<T> extends FocusWidget implements HasConstrainedValue<T> {
    public interface Renderer<T> {
        Element render(T object);
    }

    protected ProvidesKey<T> keyProvider;
    protected Renderer<T> renderer;

    protected List<T> values = new ArrayList<T>();
    protected Map<Object, Integer> valueKeyToIndex = new HashMap<>();

    private T value;

    private boolean valueChangeHandlerInitialized;

    public Selector() {
        this(new Renderer<T>() {
            @Override
            public Element render(T object) {
                Element divElement = DOM.createDiv();

                String label = (object == null ? "" : object.toString());
                divElement.setAttribute("label", label);
                divElement.setInnerHTML(label);

                return divElement;
            }
        }, new SimpleKeyProvider<T>());
    }

    public Selector(Renderer<T> renderer,
                    ProvidesKey<T> keyProvider) {
        this(SelectorElement.create(), renderer, keyProvider);
    }

    public Selector(Element element,
                    Renderer<T> renderer,
                    ProvidesKey<T> keyProvider) {
        super(element);

        this.keyProvider = keyProvider;
        this.renderer = renderer;
    }

    protected void ensureDomEventHandlers() {
        if (!valueChangeHandlerInitialized) {
            addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    ValueChangeEvent.fire(Selector.this, getValue());
                }
            });

            valueChangeHandlerInitialized = true;
        }
    }

    public T getSelected() {
        return getValue();
    }

    public void setSelected(T selected) {
        setValue(selected, true);
    }

    public boolean isMulti() {
        return getProperty(getElement(), "multi");
    }

    public void setMulti(boolean status) {
        setProperty(getElement(), "multi", status);
    }

    public String getValueAttribute() {
        return getProperty(getElement(), "valueattr");
    }

    public void setValueAttribute(String attr) {
        setProperty(getElement(), "valueattr", attr);
    }

    public String getSelectedClass() {
        return getProperty(getElement(), "selectedClass");
    }

    public void setSelectedClass(String selectedClass) {
        setProperty(getElement(), "selectedClass", selectedClass);
    }

    public String getSelectedProperty() {
        return getProperty(getElement(), "selectedProperty");
    }

    public void setSelectedProperty(String property) {
        setProperty(getElement(), "selectedProperty", property);
    }

    public String getSelectedAttribute() {
        return getProperty(getElement(), "selectedAttribute");
    }

    public void setSelectedAttribute(String attribute) {
        setProperty(getElement(), "selectedAttribute", attribute);
    }

    public int getSelectedIndex() {
        return values.indexOf(getValue());
    }

    public void setSelectedIndex(int index) {
        T selected = values.get(index);
        setSelected(selected);
    }

    public Element getTarget() {
        return getProperty(getElement(), "target");
    }

    public void setTarget(Element target) {
        setProperty(getElement(), "target", target);
    }

    public String getItemSelector() {
        return getProperty(getElement(), "itemSelector");
    }

    public void setItemSelector(String selector) {
        setProperty(getElement(), "itemSelector", selector);
    }

    public String getActivateEvent() {
        return getProperty(getElement(), "activateEvent");
    }

    public void setActivateEvent(String event) {
        setProperty(getElement(), "activateEvent", event);
    }

    public boolean isNoTap() {
        return getProperty(getElement(), "notap");
    }

    public void setNoTap(boolean status) {
        setProperty(getElement(), "notap", status);
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        setValue(value, false);
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        if (value == this.value
                || (this.value != null && this.value.equals(value))) {
            return;
        }

        T before = this.value;
        this.value = value;

        if (fireEvents) {
            ValueChangeEvent.fireIfNotEqual(this, before, value);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        ensureDomEventHandlers();

        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public void setAcceptableValues(Collection<T> newValues) {
        values.clear();
        valueKeyToIndex.clear();

        clearItems();

        for (T nextNewValue : newValues) {
            addValue(nextNewValue);
        }
    }

    protected void addValue(T value) {
        Object key = keyProvider.getKey(value);
        if (valueKeyToIndex.containsKey(key)) {
            throw new IllegalArgumentException("Duplicate value: " + value);
        }

        valueKeyToIndex.put(key, values.size());
        values.add(value);

        getElement().appendChild(renderer.render(value));
    }

    protected void clearItems() {
        NodeList<Node> nodes = getElement().getChildNodes();
        for (int i = 0, length = nodes.getLength(); i < length; i++) {
            nodes.getItem(i).removeFromParent();
        }
    }
}
