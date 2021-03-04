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

@Table(name = "now_status")

@NamedQueries({
    @NamedQuery(
        name = "getAllNowStatus",
        query = "SELECT n FROM NowStatus AS n ORDER BY n.now_id DESC"
    ),
    @NamedQuery(
        name = "getNowStatusCount",
        query = "SELECT COUNT(n) FROM NowStatus AS n"
    ),
    @NamedQuery(
        name = "getMyAllNowStatus",
        query = "SELECT n FROM NowStatus AS n WHERE n.characters.titles = :titles ORDER BY n.now_id DESC"
    ),
    @NamedQuery(
        name = "getMyNowStatusCount",
        query = "SELECT COUNT(n) FROM NowStatus AS n WHERE n.characters.titles = :titles"
    ),
    @NamedQuery(
        name = "getCharactersAllNowStatus",
        query = "SELECT n FROM NowStatus AS n WHERE n.characters = :characters ORDER BY n.now_id DESC"
    ),
    @NamedQuery(
        name = "checkNow_year",
        query = "SELECT COUNT(n) FROM NowStatus AS n WHERE n.characters = :characters and n.now_year = :now_year"
    )
})

@Entity
public class NowStatus {
    @Id
    @Column(name = "now_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer now_id;

    //対象キャラクター
    @ManyToOne
    @JoinColumn(name = "chara_id", nullable = false)
    private Character characters;

    //データの年度
    @Column(name = "now_year", nullable = false, unique = true)
    private Integer now_year;

    //人物分類　0.選手、監督、コーチ、オーナー、その他球団関係者　1.球団関係者以外(OB・OGなどの球界関係者、ファンなど)
    @Column(name = "chara_flag", nullable = false)
    private Integer chara_flag;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = true)
    private Player players;

    @ManyToOne
    @JoinColumn(name = "not_player_id", nullable = true)
    private NotPlayer not_players;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getNow_id() {
        return now_id;
    }

    public void setNow_id(Integer now_id) {
        this.now_id = now_id;
    }

    public Character getCharacters() {
        return characters;
    }

    public void setCharacters(Character characters) {
        this.characters = characters;
    }

    public Integer getNow_year() {
        return now_year;
    }

    public void setNow_year(Integer now_year) {
        this.now_year = now_year;
    }

    public Integer getChara_flag() {
        return chara_flag;
    }

    public void setChara_flag(Integer chara_flag) {
        this.chara_flag = chara_flag;
    }

    public Player getPlayers() {
        return players;
    }

    public void setPlayers(Player players) {
        this.players = players;
    }

    public NotPlayer getNot_players() {
        return not_players;
    }

    public void setNot_players(NotPlayer not_players) {
        this.not_players = not_players;
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
