package com.tele2.form;

import com.tele2.model.Occupation;
import com.tele2.model.Person;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

import java.util.Arrays;

public class PersonForm extends FormLayout {

    private final Window window;

    @PropertyId("name")
    private TextField nameField;

    @PropertyId("surname")
    private TextField surnameField;

    @PropertyId("age")
    private Slider ageField;

    @PropertyId("occupation")
    private ComboBox occupationField;


    public PersonForm(BeanItem<Person> bean, Window window) {
        this.window = window;

        nameField = new TextField("Name");
        nameField.setNullRepresentation("");
        addComponent(nameField);

        surnameField = new TextField("Surname");
        surnameField.setNullRepresentation("");
        addComponent(surnameField);

        ageField = new Slider("Age", 1, 99);
        ageField.setWidth("100%");
        addComponent(ageField);

        occupationField = new ComboBox("Occupation", Arrays.asList(Occupation.values()));
        addComponent(occupationField);

        FieldGroup binder = new FieldGroup(bean);
        binder.bindMemberFields(this);

        Button saveButton = new Button("Save", new SaveGood(binder));

        addComponent(saveButton);
    }

    private class SaveGood implements Button.ClickListener {

        private final FieldGroup binder;

        private SaveGood(FieldGroup binder) {
            this.binder = binder;
        }

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            try {
                binder.commit();
                window.close();
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
        }
    }
}
