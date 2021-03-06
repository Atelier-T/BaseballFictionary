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

@Table(name = "teams")

@NamedQueries({
    @NamedQuery(
        name = "getAllTeams",
        query = "SELECT t FROM Team AS t ORDER BY t.leagues"
    ),
    @NamedQuery(
        name = "getTeamsCount",
        query = "SELECT COUNT(t) FROM Team AS t"
    ),
    @NamedQuery(
        name = "getMyAllTeams",
        query = "SELECT t FROM Team AS t WHERE t.titles = :titles ORDER BY t.leagues"
    ),
    @NamedQuery(
        name = "getMyTeamsCount",
        query = "SELECT COUNT(t) FROM Team AS t WHERE t.titles = :titles"
    )
})

@Entity
public class Team {
    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer team_id;

    @Column(name = "team_name", length = 255, nullable = false)
    private String team_name;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = true)
    private League leagues;

    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title titles;

    @Column(name = "team_information", nullable = true)
    private String team_information;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public League getLeagues() {
        return leagues;
    }

    public void setLeagues(League leagues) {
        this.leagues = leagues;
    }

    public Title getTitles() {
        return titles;
    }

    public void setTitles(Title titles) {
        this.titles = titles;
    }

    public String getTeam_information() {
        return team_information;
    }

    public void setTeam_information(String team_information) {
        this.team_information = team_information;
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
