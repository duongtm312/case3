package dao;

import connect.Connect_MySQL;
import model.ProFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsDAO {
    ProFileDAO proFileDAO = new ProFileDAO();
    private static final String SELECT_FRIENDS_BY_ID = "select * from friends where idAcc1 =?";
    private static final String SELECT_FRIENDS_BY_ID2 = "select * from friends where idAcc2 =?";
    private static final String DELETE_FRIENDS_SQL = "delete from friends where idAcc1 = ? and idAcc2=?;";
    private static final String INSERT_FRIENDS_SQL = "insert into friends value (?,?);";

    public List<ProFile> getAllFriend(String useName) {
        List<ProFile> match = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FRIENDS_BY_ID);
            preparedStatement.setString(1, useName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idAcc2 = resultSet.getString("idAcc2");
                match.add(proFileDAO.findById(idAcc2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FRIENDS_BY_ID2);
            preparedStatement.setString(1, useName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idAcc2 = resultSet.getString("idAcc1");
                match.add(proFileDAO.findById(idAcc2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return match;
    }

    public boolean delete(String useName, String useName2) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(DELETE_FRIENDS_SQL);) {
            statement.setString(1, useName);
            statement.setString(2, useName2);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean create(String useName, String useName2) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FRIENDS_SQL);
            preparedStatement.setString(1, useName);
            preparedStatement.setString(2, useName2);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}

