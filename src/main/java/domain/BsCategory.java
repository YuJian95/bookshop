package domain;

import javax.persistence.*;

@Entity
@Table(name = "bs_category", schema = "bs", catalog = "bs")
public class BsCategory implements java.io.Serializable {
    private Integer catId;
    private String catName;

    //默认无参构造方法
    public BsCategory() {
    }

    public BsCategory(Integer catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    @Id
    @Column(name = "cat_id")
    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "cat_name")
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

}
