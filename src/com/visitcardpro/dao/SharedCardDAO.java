package com.visitcardpro.dao;

import com.visitcardpro.beans.Card;
import com.visitcardpro.beans.PersonnalCard;
import com.visitcardpro.beans.SharedCard;
import com.visitcardpro.beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SharedCardDAO extends DAO<SharedCard> {
    public final static String SQL_LIST_BY_USERID =         "SELECT SharedCard.id, SharedCard.key, Card.id as cardId, Card.email, Card.phone, Card.firstName, Card.lastName FROM SharedCard INNER JOIN Card ON SharedCard.cardId WHERE SharedCard.userId = ?";
    public final static String SQL_FIND_BY_KEY_AND_USERID = "SELECT SharedCard.id, SharedCard.key, Card.id as cardId, Card.email, Card.phone, Card.firstName, Card.lastName FROM SharedCard INNER JOIN Card ON SharedCard.cardId WHERE SharedCard.key = ? AND SharedCard.userId = ?";
    public final static String SQL_CREATE =                 "INSERT INTO SharedCard VALUES(NULL, ?, ?, ?)";
    public final static String SQL_DELETE =                 "DELETE FROM SharedCard WHERE key = ? AND userId = ?";
    public final static String SQL_DELETE_BY_CARDID_AND_USERID =       "DELETE FROM SharedCard WHERE cardId = ? AND userId = ?";

    public SharedCardDAO(DAOFactory factory) {
        super(factory);
    }

    public List<SharedCard> getByUser(User user) throws DAOException {
        return this.getList(SQL_LIST_BY_USERID, user.getId());
    }

    public SharedCard getByKeyAndUser(String key, User user) throws DAOException{
        return this.find(SQL_FIND_BY_KEY_AND_USERID, key, user.getId());
    }

    public SharedCard createByUserAndSharingKey(SharedCard sCard, User user, String sharingKey) throws DAOException{

        PersonnalCard pCard = daoFactory.getPersonnalCardDao().getByShringKey(sharingKey);
        sCard.setCard(pCard.getCard());
        sCard.setId(this.createAndGetId(SQL_CREATE, sCard.getKey(), sCard.getCard().getId(), user.getId()));
        return sCard;
    }

    public void deleteByKeyAndUser(String key, User user) throws DAOException {
        this.delete(SQL_DELETE, key, user.getId());
    }

    public void deleteAllByCardAndUser(Card card, User user) {
        this.delete(SQL_DELETE_BY_CARDID_AND_USERID, card.getId(), user.getId());
    }


    @Override
    protected SharedCard map(ResultSet resultSet) throws SQLException {
        SharedCard sharedCard = new SharedCard();
        sharedCard.setId(resultSet.getLong("id"));
        sharedCard.setKey(resultSet.getString("key"));

        Card card = new Card();
        card.setId(resultSet.getLong("cardId"));
        card.setEmail(resultSet.getString("email"));
        card.setPhone(resultSet.getString("phone"));
        card.setFirstName(resultSet.getString("firstName"));
        card.setLastName(resultSet.getString("lastName"));
        sharedCard.setCard(card);

        return sharedCard;
    }
}
