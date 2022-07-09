package model;

public class Account {
    private String useName;
    private String passWord;
    private String mail;

    public Account(String useName, String passWord, String mail) {
        this.useName = useName;
        this.passWord = passWord;
        this.mail = mail;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
