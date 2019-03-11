package domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bs_order", schema = "bs", catalog = "bs")
public class BsOrder implements java.io.Serializable {
    private Integer ordId;
    private BsUser user;
    private Integer userId;
    private Date ordDatetime;
    private Integer ordState;

    private Set bsDetailses = new HashSet(0);

    public BsOrder() {
    }

    public BsOrder(Integer ordId, BsUser user, Integer userId, Date ordDatetime, Integer ordState, Set bsDetailses) {
        this.ordId = ordId;
        this.user = user;
        this.userId = userId;
        this.ordDatetime = ordDatetime;
        this.ordState = ordState;
        this.bsDetailses = bsDetailses;
    }

    @Id
    @Column(name = "ord_id")
    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BsUser getUser() {
        return user;
    }

    public void setUser(BsUser user) {
        this.user = user;
    }

    @Basic
    @Column(name = "ord_datetime")
    public Date getOrdDatetime() {
        return ordDatetime;
    }

    public void setOrdDatetime(Date ordDatetime) {
        this.ordDatetime = ordDatetime;
    }

    @Basic
    @Column(name = "ord_state")
    public Integer getOrdState() {
        return ordState;
    }

    public void setOrdState(Integer ordState) {
        this.ordState = ordState;
    }

    public Set getBsDetailses() {
        return bsDetailses;
    }

    public void setBsDetailses(Set bsDetailses) {
        this.bsDetailses = bsDetailses;
    }

    @Override
    public String toString() {
        return "BsOrder{" +
                "ordId=" + ordId +
                ", user=" + user +
                ", userId=" + userId +
                ", ordDatetime=" + ordDatetime +
                ", ordState=" + ordState +
                ", bsDetailses=" + bsDetailses +
                '}';
    }
}
