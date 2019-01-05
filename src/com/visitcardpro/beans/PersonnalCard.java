package com.visitcardpro.beans;

public class PersonnalCard {
    protected long id;
    protected String key;
    protected String sharingKey;

    protected Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getSharingKey() {
        return sharingKey;
    }

    public void setSharingKey(String sharingKey) {
        this.sharingKey = sharingKey;
    }
}
