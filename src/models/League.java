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

@Table(name = "leagues")

@NamedQueries({
    @NamedQuery(
        name = "getAllLeagues",
        query = "SELECT l FROM League AS l ORDER BY l.titles DESC"
    ),
    @NamedQuery(
        name = "getLeaguesCount",
        query = "SELECT COUNT(l) FROM League AS l"
    ),
    @NamedQuery(
        name = "getMyAllLeagues",
        query = "SELECT l FROM League AS l WHERE l.titles = :titles ORDER BY l.league_id DESC"
    ),
    @NamedQuery(
        name = "getMyLeaguesCount",
        query = "SELECT COUNT(l) FROM League AS l WHERE l.titles = :titles"
    ),
    @NamedQuery(
        name = "LeagueClassSearch",
        query = "SELECT l FROM League AS l WHERE l.league_id = :league_id"
    )
})

@Entity
public class League {
    @Id
    @Column(name = "league_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer league_id;

    @Column(name = "league_name", length = 255, nullable = false)
    private String league_name;

    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title titles;

    @Column(name = "country_flag", nullable = false)
    private Integer country_flag;

    @Column(name = "league_information", nullable = true)
    private String league_information;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Integer league_id) {
        this.league_id = league_id;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public Title getTitles() {
        return titles;
    }

    public void setTitles(Title titles) {
        this.titles = titles;
    }

    public Integer getCountry_flag() {
        return country_flag;
    }

    public void setCountry_flag(Integer country_flag) {
        this.country_flag = country_flag;
    }

    public String getLeague_information() {
        return league_information;
    }

    public void setLeague_information(String league_information) {
        this.league_information = league_information;
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
