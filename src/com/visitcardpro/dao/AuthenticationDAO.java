package com.visitcardpro.dao;

import com.visitcardpro.beans.Authentication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDAO extends DAO<Authentication> {

    private static final String SQL_CREATE = "INSERT INTO Authentication VALUES(NULL, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Authentication WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Authentication SET email = ?, hashedPassword = ?, refreshToken = ?, role = ? WHERE id = ?";

    public AuthenticationDAO(DAOFactory factory) {
        super(factory);
    }

    public void create(Authentication auth) {
        auth.setId(this.createAndGetId(SQL_CREATE, auth.getEmail(), auth.getHashedPassword(), auth.getRefreshToken(), auth.getRole()));
    }

    public void delete(Authentication auth) {
        this.delete(SQL_DELETE, auth.getId());
    }

    public void update(Authentication auth) {
        this.edit(SQL_UPDATE, auth.getEmail(), auth.getHashedPassword(), auth.getRefreshToken(), auth.getRole(), auth.getId());
    }


    @Override
    Authentication map(ResultSet resultSet) throws SQLException {
        Authentication auth = new Authentication();
        auth.setEmail( resultSet.getString( "email" ) );
        auth.setHashedPassword( resultSet.getString( "hashedPassword" ) );
        auth.setRefreshToken(resultSet.getString("accessToken"));
        auth.setRole(resultSet.getString("role"));
        return auth;
    }

}
