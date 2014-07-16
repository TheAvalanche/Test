package com.tele2;

import com.tele2.form.OpenFormListener;
import com.tele2.model.Person;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    private BeanItemContainer<Person> container;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.tele2.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        container = new BeanItemContainer<>(Person.class);
        Table table = new Table("People");
        table.setContainerDataSource(container);
        table.setSelectable(true);
        table.setImmediate(true);
        table.addItemClickListener(new OpenFormListener(container));

        Button addButton = new Button("Add Person", new OpenFormListener(container));

        layout.addComponent(addButton);
        layout.addComponent(table);

    }

}
