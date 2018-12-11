package com.visitcardpro.dao;

import com.visitcardpro.beans.Authentication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDAO extends DAO<Authentication> {

    private static final String SQL_CREATE = "INSERT INTO Authentication VALUES(NULL, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Authentication WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE Authentication SET email = ?, hashedPassword = ?, refreshToken = ?, role = ?, resetPasswordToken = ? WHERE id = ?";
    private static final String SQL_UPDATE_RESETPASSWORDTOKEN_BY_EMAIL = "UPDATE Authentication SET resetPasswordToken = ? WHERE email = ?";
    private static final String SQL_UPDATE_HASHEDPASSWORD_BY_RESETPASSWORDTOKEN = "UPDATE Authentication SET hashedPassword = ?, resetPasswordToken = NULL WHERE resetPasswordToken = ?";

    public AuthenticationDAO(DAOFactory factory) {
        super(factory);
    }

    public void create(Authentication auth) {
        auth.setId(this.createAndGetId(SQL_CREATE, auth.getEmail(), auth.getHashedPassword(), auth.getRefreshToken(), auth.getRole(), null));
    }

    public void delete(Authentication auth) {
        this.delete(SQL_DELETE, auth.getId());
    }

    public void update(Authentication auth) {
        this.edit(SQL_UPDATE_BY_ID, auth.getEmail(), auth.getHashedPassword(), auth.getRefreshToken(), auth.getRole(), auth.getResetPasswordToken(), auth.getId());
    }

    public void updateResetPasswordTokenByEmail(String email, String token) throws DAOException {
        this.edit(SQL_UPDATE_RESETPASSWORDTOKEN_BY_EMAIL, token, email);
    }

    public void updatePasswordByResetPasswordToken(String resetPasswordToken, String hashedPassword) {
        this.edit(SQL_UPDATE_HASHEDPASSWORD_BY_RESETPASSWORDTOKEN, hashedPassword, resetPasswordToken);
    }


    @Override
    Authentication map(ResultSet resultSet) throws SQLException {
        Authentication auth = new Authentication();
        auth.setEmail( resultSet.getString( "email" ) );
        auth.setHashedPassword( resultSet.getString( "hashedPassword" ) );
        auth.setRefreshToken(resultSet.getString("accessToken"));
        auth.setRole(resultSet.getString("role"));
        auth.setResetPasswordToken(resultSet.getString("resetPasswordToken"));
        return auth;
    }

}
