package com.visitcardpro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.visitcardpro.beans.Authentication;
import com.visitcardpro.beans.User;

public class UserDAO extends DAO<User> {
	private static final String SQL_SELECT_BY_ID = "SELECT User.id, Authentication.email, Authentication.hashedPassword, Authentication.refreshToken, Authentication.role FROM User INNER JOIN Authentication ON User.authId = Authentication.id WHERE id = ?";
	private static final String SQL_SELECT_BY_EMAIL = "SELECT User.id, Authentication.email, Authentication.hashedPassword, Authentication.refreshToken, Authentication.role FROM User INNER JOIN Authentication ON User.authId = Authentication.id WHERE Authentication.email = ?";
	private static final String SQL_SELECT_BY_REFRESH_TOKEN = "SELECT User.id, Authentication.email, Authentication.hashedPassword, Authentication.refreshToken, Authentication.role FROM User INNER JOIN Authentication ON User.authId = Authentication.id WHERE Authentication.refreshToken = ?";
	private static final String SQL_SELECT_BY_ROLE = "SELECT User.id, Authentication.email, Authentication.hashedPassword, Authentication.refreshToken, Authentication.role FROM User INNER JOIN Authentication ON User.authId = Authentication.id WHERE Authentication.role = ?";
	private static final String SQL_CREATE       = 	"INSERT INTO User VALUES(NULL, ?)";
	private static final String SQL_DELETE       = "DELETE FROM User WHERE id = ?";

	public UserDAO(DAOFactory factory) {
		super(factory);
	}

	public User findById( long id ) throws DAOException {
		return this.find(SQL_SELECT_BY_ID, id);
	}

	public User findById(String email) throws DAOException {
		return this.find(SQL_SELECT_BY_EMAIL, email);
	}

	public User findByRefreshToken(String token) throws DAOException {
		return this.find(SQL_SELECT_BY_REFRESH_TOKEN, token);
	}

	public void create(User user) throws DAOException {
		if (user.getAuth() == null) {
			throw new DAOException("Le bean Authentication est vide.");
		}
		daoFactory.getAuthenticationDao().create(user.getAuth());
		user.setId(this.createAndGetId(SQL_CREATE, user.getAuth().getId()));
	}

	public List<User> getListByRole(String role) throws DAOException {
		return this.getList(SQL_SELECT_BY_ROLE, role);
	}

	public void delete(User user) throws DAOException {
		this.delete(SQL_DELETE, user.getId());
		daoFactory.getAuthenticationDao().delete(user.getAuth());
	}

	
	protected User map( ResultSet resultSet ) throws SQLException {
	    User user = new User();
	    user.setId( resultSet.getLong( "id" ) );
	    user.setAuth(new Authentication());
	    user.getAuth().setEmail( resultSet.getString( "email" ) );
	    user.getAuth().setHashedPassword( resultSet.getString( "hashedPassword" ) );
	    user.getAuth().setRefreshToken(resultSet.getString("refreshToken"));
	    user.getAuth().setRole(resultSet.getString("role"));
	    return user;
	}
}
