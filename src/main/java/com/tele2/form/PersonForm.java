package com.tele2.form;

import com.tele2.model.Occupation;
import com.tele2.model.Person;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

import java.util.Arrays;

public class PersonForm extends FormLayout {

    @PropertyId("name")
    private TextField nameField = new TextField("Name");

    @PropertyId("surname")
    private TextField surnameField = new TextField("Surname");

    @PropertyId("age")
    private Slider ageField = new Slider("Age", 1, 99);

    @PropertyId("occupation")
    private ComboBox occupationField = new ComboBox("Occupation", Arrays.asList(Occupation.values()));


    public PersonForm(BeanItem<Person> bean) {

        FieldGroup binder = new FieldGroup(bean);
        binder.bindMemberFields(this);

        addComponent(nameField);
        addComponent(surnameField);
        addComponent(ageField);
        addComponent(occupationField);

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
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
        }
    }
}
