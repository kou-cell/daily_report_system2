package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
@NamedQuery(
        name = "getFollowsCount",
        query = "SELECT COUNT(f) FROM Report AS f"
    ),
@NamedQuery(
        name = "getEmployeeById",
        query = "SELECT f.id  FROM Follow AS f WHERE f.user_id = :user_id AND f.follow = :follow" //左辺のuser_idとfollowは変数
        ),
@NamedQuery(
        name = "getAllEmployee",
        query = "SELECT f  FROM Follow AS f WHERE f.user_id = :user_id AND f.follow = :follow"
        )
})
@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Employee user_id;

    @ManyToOne
    @JoinColumn(name = "follow", nullable = false)
    private Employee follow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getUser_id() {
        return user_id;
    }

    public void setUser_id(Employee user_id) {
        this.user_id = user_id;
    }

    public Employee getFollow() {
        return follow;
    }

    public void setFollow(Employee follow) {
        this.follow = follow;
    }

}