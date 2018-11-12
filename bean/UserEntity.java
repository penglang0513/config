package com.penglang.config.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserEntity implements Serializable
{


    private String userid;

    private String username;

    private String age;

    private String address;

    private String pwd;

    private String userstate;

    private String mobilephone;

    private String email;

    private Timestamp createtime;

    private Timestamp updatetime;




    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }



    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", pwd='" + pwd + '\'' +
                ", userstate='" + userstate + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", email='" + email + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
