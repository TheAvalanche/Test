package com.tele2.form;

import com.tele2.model.Person;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class OpenFormListener implements Button.ClickListener, ItemClickEvent.ItemClickListener {

    private final BeanItemContainer<Person> container;

    public OpenFormListener(BeanItemContainer<Person> container) {
        this.container = container;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        openForm(container.addBean(new Person()));
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (event.isDoubleClick() && event.getItem() != null) {
            openForm((BeanItem<Person>) event.getItem());
        }
    }

    private void openForm(BeanItem<Person> bean) {

        Window subWindow = new Window();
        subWindow.setModal(true);
        subWindow.setHeight("400px");
        subWindow.setWidth("450px");
        subWindow.setClosable(true);
        UI.getCurrent().addWindow(subWindow);

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        layout.addComponent(new PersonForm(bean, subWindow));

        subWindow.setContent(layout);

    }
}
