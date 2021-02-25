package models;

import java.sql.Timestamp;

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

@Table(name = "titles")

@NamedQueries({
    @NamedQuery(
        name = "getAllTitles",
        query = "SELECT t FROM Title AS t ORDER BY t.title_id DESC"
    ),
    @NamedQuery(
        name = "getTitlesCount",
        query = "SELECT COUNT(t) FROM Title AS t"
    )
})

@Entity
public class Title {
    @Id
    @Column(name = "title_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer title_id;

    @Column(name = "title_name", length = 255, nullable = false)
    private String title_name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @Column(name = "title_url", nullable = true)
    private String title_url;

    @Column(name = "title_count", nullable = true)
    private Integer title_count;

    //開始時点での作中年度
    @Column(name = "year", nullable = true)
    private Integer year;

    //開始時点からの作中での経過年数
    @Column(name = "elapsed_year", nullable = true)
    private Integer elapsed_year;

    @Column(name = "baseball_flag", nullable = false)
    private Integer baseball_flag;

    @Column(name = "title_information", nullable = true)
    private String title_information;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getTitle_id() {
        return title_id;
    }

    public void setTitle_id(Integer title_id) {
        this.title_id = title_id;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getTitle_url() {
        return title_url;
    }

    public void setTitle_url(String title_url) {
        this.title_url = title_url;
    }

    public Integer getTitle_count() {
        return title_count;
    }

    public void setTitle_count(Integer title_count) {
        this.title_count = title_count;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getElapsed_year() {
        return elapsed_year;
    }

    public void setElapsed_year(Integer elapsed_year) {
        this.elapsed_year = elapsed_year;
    }

    public Integer getBaseball_flag() {
        return baseball_flag;
    }

    public void setBaseball_flag(Integer baseball_flag) {
        this.baseball_flag = baseball_flag;
    }

    public String getTitle_information() {
        return title_information;
    }

    public void setTitle_information(String title_information) {
        this.title_information = title_information;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
