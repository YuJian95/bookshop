package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bs_user", schema = "bs", catalog = "bs")
public class BsUser implements java.io.Serializable {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String userRealName;
    private String userPhone;
    private String userEmail;
    private String userAddr;
    private Date userDatetime;
    private Integer userRight;

    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_pwd")
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Basic
    @Column(name = "user_realName")
    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    @Basic
    @Column(name = "user_phone")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_addr")
    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    @Basic
    @Column(name = "user_datetime")
    public Date getUserDatetime() {
        return userDatetime;
    }

    public void setUserDatetime(Date userDatetime) {
        this.userDatetime = userDatetime;
    }

    @Basic
    @Column(name = "user_right")
    public Integer getUserRight() {
        return userRight;
    }

    public void setUserRight(Integer userRight) {
        this.userRight = userRight;
    }

}
