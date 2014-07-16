package com.tele2;

import com.tele2.form.PersonForm;
import com.tele2.model.Person;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    private Window subWindow;
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

        Button addButton = new Button("Add Person", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                openForm(container.addBean(new Person()));
            }
        });

        layout.addComponent(addButton);
        layout.addComponent(table);

    }

    private void openForm(BeanItem<Person> bean) {

        subWindow = new Window();
        subWindow.setModal(true);
        subWindow.setHeight("800px");
        subWindow.setWidth("450px");
        subWindow.setClosable(true);
        UI.getCurrent().addWindow(subWindow);

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        layout.addComponent(new PersonForm(bean));

        subWindow.setContent(layout);
        subWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent closeEvent) {

            }
        });
    }

    public void refreshView() {
        this.removeWindow(subWindow);

    }

}
