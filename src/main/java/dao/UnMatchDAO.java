package dao;

import connect.Connect_MySQL;
import model.ProFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnMatchDAO {
    ProFileDAO proFileDAO = new ProFileDAO();
    private static final String SELECT_UNMATCH_BY_ID = "select * from unmatchs where idAcc1 =?";
    private static final String DELETE_UNMATCH_SQL = "delete from unmatchs where idAcc1 = ? and idAcc2=?;";
    private static final String INSERT_UNMATCH_SQL = "insert into unmatchs value (?,?);";

    public List<ProFile> getAllUnMatch(String useName) {
        List<ProFile> match = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNMATCH_BY_ID);
            preparedStatement.setString(1, useName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idAcc2 = resultSet.getString("idAcc2");
                match.add(proFileDAO.findById(idAcc2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return match;
    }

    public boolean delete(String useName, String useName2) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(DELETE_UNMATCH_SQL);) {
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
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_UNMATCH_SQL);
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

