package model;

import java.util.List;

public class Library {
    List<Post> posts;
    List<ProFile> match;
    List<ProFile> unMatch;
    List<ProFile> friends;

    public Library(List<Post> posts, List<ProFile> match, List<ProFile> unMatch, List<ProFile> friends) {
        this.posts = posts;
        this.match = match;
        this.unMatch = unMatch;
        this.friends = friends;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<ProFile> getMatch() {
        return match;
    }

    public void setMatch(List<ProFile> match) {
        this.match = match;
    }

    public List<ProFile> getUnMatch() {
        return unMatch;
    }

    public void setUnMatch(List<ProFile> unMatch) {
        this.unMatch = unMatch;
    }

    public List<ProFile> getFriends() {
        return friends;
    }

    public void setFriends(List<ProFile> friends) {
        this.friends = friends;
    }
}
