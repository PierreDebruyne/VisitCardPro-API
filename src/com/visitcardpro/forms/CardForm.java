package com.visitcardpro.forms;

import com.google.common.collect.Lists;
import com.visitcardpro.beans.Card;

import java.util.List;

public class CardForm {
    protected List<String> errors = Lists.newArrayList();
    protected String email;
    protected String phone;
    protected String firstName;
    protected String lastName;

    public List<String> getErrors() {
        return errors;
    }

    public Card generateCard() {
        Card card = new Card();
        card.setEmail(email);
        card.setFirstName(firstName);
        card.setLastName(lastName);
        card.setPhone(phone);
        return card;
    }

    public boolean isValidForm() {
        if (!Normalizer.checkEmail(getEmail()))
            errors.add("Invalid email.");
        if (!Normalizer.checkProperNoun(getFirstName()))
            errors.add("Invalid first name.");
        if (!Normalizer.checkProperNoun(getLastName()))
            errors.add("Invalid last name.");
        if (!Normalizer.ckeckPhoneNumber(getPhone()))
            errors.add("Invalid phone number.");
        if (!errors.isEmpty())
            return false;
        firstName = Normalizer.normalizeProperNoun(getFirstName());
        lastName = Normalizer.normalizeProperNoun(getLastName());
        email = Normalizer.normalizeEmail(getEmail());
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
