package model;

import java.util.List;

public class ProFile {
    private String idProfile;
    private String name;
    private String sex;
    private int age;
    private String address;
    private String imgSrc;

    public ProFile(String idProfile, String name, String sex, int age, String address, String imgSrc) {
        this.idProfile = idProfile;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.imgSrc = imgSrc;
    }

    public String getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(String idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
