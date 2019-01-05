package com.visitcardpro.dao;

import com.visitcardpro.beans.Card;
import com.visitcardpro.beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CardDAO extends DAO<Card> {

    public final static String SQL_CREATE = "INSERT INTO Card VALUES(NULL, ?, ?, ?, ?)";
    public final static String SQL_DELETE = "DELETE FROM Card WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Card SET email = ?, phone = ?, firstName = ?, lastName = ? WHERE id = ?";

    public CardDAO(DAOFactory factory) {
        super(factory);
    }

    public void create(Card card) throws DAOException{
        card.setId(this.createAndGetId(SQL_CREATE, card.getEmail(), card.getPhone(), card.getFirstName(), card.getLastName()));
    }

    public void delete(Card card) throws DAOException {
        this.delete(SQL_DELETE, card.getId());
    }

    public void update(Card card) throws DAOException {
        this.edit(SQL_UPDATE, card.getEmail(), card.getPhone(), card.getFirstName(), card.getLastName(), card.getId());
    }

    @Override
    protected Card map(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getLong("id"));
        card.setEmail(resultSet.getString("email"));
        card.setPhone(resultSet.getString("phone"));
        card.setFirstName(resultSet.getString("firstName"));
        card.setLastName(resultSet.getString("lastName"));
        return card;
    }


}
