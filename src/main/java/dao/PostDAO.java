package dao;

import connect.Connect_MySQL;
import model.Account;
import model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDAO implements CRUD<Post> {
    private static final String INSERT_POST_SQL = "insert into post value (?,?,?,?);";
    private static final String SELECT_ALL_POST = "select * from  post where useName =?";
    private static final String DELETE_POST_SQL = "delete from account where useName = ?;";
    private static final String UPDATE_POST_SQL = "update  post set img= ? where idPost =?";

    @Override
    public List<Post> getAll() {
        List<Post> post_list = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_POST);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String idPost = resultSet.getString("idPost");
                String useName = resultSet.getString("useName");
                String imgSrc = resultSet.getString("img");
                Date date = resultSet.getDate("time");
                post_list.add(new Post(idPost,useName, imgSrc, date));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post_list;
    }

    @Override
    public boolean create(Post post) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POST_SQL);
            preparedStatement.setString(1, post.getIdPost());
            preparedStatement.setString(2, post.getUseName());
            preparedStatement.setString(3, post.getImgSrc());
            preparedStatement.setDate(4, (java.sql.Date) post.getTime());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(String id, Post post) {
        return false;
    }

    @Override
    public boolean delete(String idPost) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement statement = connection.prepareStatement(DELETE_POST_SQL);) {
            statement.setString(1,idPost);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Post findById(String idPost) {

        return null;
    }
}
