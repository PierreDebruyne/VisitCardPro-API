package com.visitcardpro.dao;

import com.visitcardpro.beans.Card;
import com.visitcardpro.beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CardDAO extends DAO<Card> {

    public final static String SQL_FIND_BY_KEY_AND_USERID = "SELECT * FROM Card WHERE card.key = ? AND card.userId = ?";
    public final static String SQL_CREATE = "INSERT INTO Card VALUES(NULL, ?, ?, ?, ?, ?, ?)";
    public final static String SQL_DELETE = "DELETE FROM Card WHERE card.key = ? AND card.userId = ?";
    public final static String SQL_LIST_BY_USERID = "SELECT * FROM Card WHERE card.userId = ?";

    public CardDAO(DAOFactory factory) {
        super(factory);
    }


    public Card getCardByKeyAndUser(String key, User user) throws DAOException{
        return this.find(SQL_FIND_BY_KEY_AND_USERID, key, user.getId());
    }

    public void createCardByUser(Card card, User user) throws DAOException{
        card.setId(this.createAndGetId(SQL_CREATE, card.getKey(), user.getId(), card.getEmail(), card.getPhone(), card.getFirstName(), card.getLastName()));
    }

    public void deleteCardByKeyAndUser(String key, User user) throws DAOException {
        this.delete(SQL_DELETE, key, user.getId());
    }

    public List<Card> getCardsByUser(User user) throws DAOException {
        return this.getList(SQL_LIST_BY_USERID, user.getId());
    }



    @Override
    Card map(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getLong("id"));
        card.setKey(resultSet.getString("key"));
        card.setEmail(resultSet.getString("email"));
        card.setPhone(resultSet.getString("phone"));
        card.setFirstName(resultSet.getString("firstName"));
        card.setLastName(resultSet.getString("lastName"));
        return card;
    }


}
