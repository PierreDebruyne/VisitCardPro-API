package com.visitcardpro.dao;

import com.visitcardpro.beans.Card;
import com.visitcardpro.beans.PersonnalCard;
import com.visitcardpro.beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonnalCardDAO extends DAO<PersonnalCard> {
    public final static String SQL_LIST_BY_USERID =         "SELECT PersonnalCard.id, PersonnalCard.key, PersonnalCard.sharingKey, Card.id as cardId, Card.email, Card.phone, Card.firstName, Card.lastName FROM PersonnalCard INNER JOIN Card ON PersonnalCard.cardId WHERE PersonnalCard.userId = ?";
    public final static String SQL_FIND_BY_KEY_AND_USERID = "SELECT PersonnalCard.id, PersonnalCard.key, PersonnalCard.sharingKey, Card.id as cardId, Card.email, Card.phone, Card.firstName, Card.lastName FROM PersonnalCard INNER JOIN Card ON PersonnalCard.cardId WHERE PersonnalCard.key = ? AND PersonnalCard.userId = ?";
    public final static String SQL_FIND_BY_SHARINGKEY =     "SELECT PersonnalCard.id, PersonnalCard.key, PersonnalCard.sharingKey, Card.id as cardId, Card.email, Card.phone, Card.firstName, Card.lastName FROM PersonnalCard INNER JOIN Card ON PersonnalCard.cardId WHERE PersonnalCard.sharingKey = ?";
    public final static String SQL_CREATE =                 "INSERT INTO PersonnalCard VALUES(NULL, ?, ?, ?, ?)";
    public final static String SQL_DELETE =                 "DELETE FROM PersonnalCard WHERE key = ? AND userId = ?";
    private static final String SQL_UPDATE =                "UPDATE PersonnalCard INNER JOIN Card ON PersonnalCard.cardId SET card.email = ?, card.phone = ?, card.firstName = ?, card.lastName = ? WHERE PersonnalCard.key = ? AND PersonnalCard.userId = ?";

    public PersonnalCardDAO(DAOFactory factory) {
        super(factory);
    }

    public List<PersonnalCard> getByUser(User user) throws DAOException {
        return this.getList(SQL_LIST_BY_USERID, user.getId());
    }

    public PersonnalCard getByKeyAndUser(String key, User user) throws DAOException {
        return this.find(SQL_FIND_BY_KEY_AND_USERID, key, user.getId());
    }

    public PersonnalCard getByShringKey(String sharingKey) throws DAOException {
        return this.find(SQL_FIND_BY_SHARINGKEY, sharingKey);
    }

    public void createByUser(PersonnalCard pCard, User user) throws DAOException{
        daoFactory.getCardDao().create(pCard.getCard());
        pCard.setId(this.createAndGetId(SQL_CREATE, pCard.getKey(), pCard.getSharingKey(), pCard.getCard().getId(), user.getId()));
    }

    public void deleteByKeyAndUser(String key, User user) throws DAOException {
        PersonnalCard pCard = getByKeyAndUser(key, user);
        this.delete(SQL_DELETE, key, user.getId());
        daoFactory.getSharedCardDao().deleteAllByCardAndUser(pCard.getCard(), user);
        daoFactory.getCardDao().delete(pCard.getCard());
    }

    public void updateCardByUserAndKey(Card card, User user, String key) throws DAOException {
        this.edit(SQL_UPDATE, card.getEmail(), card.getPhone(), card.getFirstName(), card.getLastName(), key, user.getId());
    }

    @Override
    protected PersonnalCard map(ResultSet resultSet) throws SQLException {
        PersonnalCard personnalCard = new PersonnalCard();

        personnalCard.setId(resultSet.getLong("id"));
        personnalCard.setKey(resultSet.getString("key"));
        personnalCard.setSharingKey(resultSet.getString("sharingKey"));

        Card card = new Card();
        card.setId(resultSet.getLong("cardId"));
        card.setEmail(resultSet.getString("email"));
        card.setPhone(resultSet.getString("phone"));
        card.setFirstName(resultSet.getString("firstName"));
        card.setLastName(resultSet.getString("lastName"));
        personnalCard.setCard(card);

        return personnalCard;
    }
}
