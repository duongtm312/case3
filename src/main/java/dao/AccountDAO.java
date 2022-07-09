package dao;

import connect.Connect_MySQL;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements CRUD<Account> {

    private static final String INSERT_ACCOUNT_SQL = "insert into account value (?,?,?);";
    private static final String SELECT_ACCOUNT_BY_ID = "select * from account where useName =?";
    private static final String SELECT_ALL_ACCOUNT = "select * from account";
    private static final String DELETE_ACCOUNT_SQL = "delete from account where useName = ?;";
    private static final String UPDATE_ACCOUNT_SQL = "update account set passWord= ? where useName =?";

    @Override
    public List<Account> getAll() {
        List<Account> account_list = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNT);) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String useName = resultSet.getString("useName");
                String passWord = resultSet.getString("passWord");
                String mail = resultSet.getString("mail");
                account_list.add(new Account(useName, passWord, mail));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account_list;
    }

    @Override
    public boolean create(Account account) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setString(1, account.getUseName());
            preparedStatement.setString(2, account.getPassWord());
            preparedStatement.setString(3, account.getMail());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(String useName, Account account) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_SQL);) {
            statement.setString(2, account.getUseName());
            statement.setString(1, account.getPassWord());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String useName) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT_SQL);) {
            statement.setString(1,useName);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Account findById(String useName) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);) {
            statement.setString(1, useName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String passWord = resultSet.getString("passWord");
                String mail = resultSet.getString("mail");
               return new Account(useName, passWord, mail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
