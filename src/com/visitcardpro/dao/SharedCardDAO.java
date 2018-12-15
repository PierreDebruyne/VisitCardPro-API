package com.visitcardpro.dao;

import com.visitcardpro.beans.Card;
import com.visitcardpro.beans.PersonnalCard;
import com.visitcardpro.beans.SharedCard;
import com.visitcardpro.beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SharedCardDAO extends DAO<SharedCard> {
    public final static String SQL_LIST_BY_USERID = "SELECT * FROM PersonnalCard WHERE userId = ?";
    public final static String SQL_FIND_BY_KEY_AND_USERID = "SELECT * FROM PersonnalCard WHERE cardKey = ? AND userId = ?";
    public final static String SQL_CREATE = "INSERT INTO PersonnalCard VALUES(NULL, ?, ?, ?, ?, ?, ?)";
    public final static String SQL_DELETE = "DELETE FROM PersonnalCard WHERE cardKey = ? AND userId = ?";
    private static final String SQL_UPDATE = "UPDATE PersonnalCard SET email = ?, phone = ?, firstName = ?, lastName = ? WHERE cardKey = ? AND userId = ?";

    public SharedCardDAO(DAOFactory factory) {
        super(factory);
    }

    public SharedCard getCardByKeyAndUser(String key, User user) throws DAOException{
        return this.find(SQL_FIND_BY_KEY_AND_USERID, key, user.getId());
    }

    public SharedCard createByUser(String sharingKey, User user) throws DAOException{
       // card.setId(this.createAndGetId(SQL_CREATE, card.getCard().getKey(), user.getId(), card.getCard().getEmail(), card.getCard().getPhone(), card.getCard().getFirstName(), card.getCard().getLastName()));
        return null;
    }

    public void deleteByKeyAndUser(String key, User user) throws DAOException {
        this.delete(SQL_DELETE, key, user.getId());
    }

    public List<SharedCard> getByUser(User user) throws DAOException {
        return this.getList(SQL_LIST_BY_USERID, user.getId());
    }

    public void updateCardByUser(SharedCard card, User user) throws DAOException {
        this.edit(SQL_UPDATE, card.getCard().getEmail(), card.getCard().getPhone(), card.getCard().getFirstName(), card.getCard().getLastName(), card.getCard().getKey(), user.getId());
    }

    @Override
    SharedCard map(ResultSet resultSet) throws SQLException {
        SharedCard sharedCard = new SharedCard();
        Card card = new Card();
        card.setId(resultSet.getLong("cardId"));
        card.setKey(resultSet.getString("cardKey"));
        card.setEmail(resultSet.getString("email"));
        card.setPhone(resultSet.getString("phone"));
        card.setFirstName(resultSet.getString("firstName"));
        card.setLastName(resultSet.getString("lastName"));
        sharedCard.setCard(card);
        sharedCard.setId(resultSet.getLong("id"));
        return sharedCard;
    }
}
