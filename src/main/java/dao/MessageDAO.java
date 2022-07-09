package dao;

import connect.Connect_MySQL;
import model.Message;
import model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDAO {
    List<Message> messages = new ArrayList<>();
    private static final String INSERT_MESSAGE_SQL = "insert into message value (?,?,?,?,?);";
    private static final String SELECT_MESSAGE_POST = "select * from  message where idAcc1 =? and idAcc2 =?";

    public List<Message> getAll(String idAcc1,String idAcc2) {
        List<Message> messageList = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()){
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGE_POST);
             preparedStatement.setString(1, idAcc1);
            preparedStatement.setString(2, idAcc2);
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idSender = resultSet.getString("idsender");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("timesend");
                messageList.add(new Message(idAcc1,idSender, content, date));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messageList;
    }
    public boolean create(Message message,String idAcc2) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL);
            preparedStatement.setString(1, message.getUseName());
            preparedStatement.setString(2, idAcc2);
            preparedStatement.setString(3, message.getIdSender());
            preparedStatement.setString(4, message.getContent());
            preparedStatement.setDate(5, (java.sql.Date) message.getTime());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
