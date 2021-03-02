package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "not_players")

@Entity
public class NotPlayer {
    @Id
    @Column(name = "not_player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer not_player_id;

    @ManyToOne
    @JoinColumn(name = "now_id", nullable = true)
    private NowStatus now_status;

    //0.未分類　1.OB・OG　2.ファン　3.選手親族　4.審判　5.コミッショナー　6.その他球界関係者　7.その他一般人
    @Column(name = "chara_type1", nullable = false)
    private Integer chara_type1;

    @Column(name = "chara_type2", nullable = false)
    private Integer chara_type2;

    @Column(name = "chara_type3", nullable = false)
    private Integer chara_type3;

    //キャラ分類詳細
    @Column(name = "chara_type_detail", nullable = true)
    private String chara_type_detail;

    @Column(name = "not_player_information", nullable = true)
    private String not_player_information;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getNot_player_id() {
        return not_player_id;
    }

    public void setNot_player_id(Integer not_player_id) {
        this.not_player_id = not_player_id;
    }

    public NowStatus getNow_status() {
        return now_status;
    }

    public void setNow_status(NowStatus now_status) {
        this.now_status = now_status;
    }

    public Integer getChara_type1() {
        return chara_type1;
    }

    public void setChara_type1(Integer chara_type1) {
        this.chara_type1 = chara_type1;
    }

    public Integer getChara_type2() {
        return chara_type2;
    }

    public void setChara_type2(Integer chara_type2) {
        this.chara_type2 = chara_type2;
    }

    public Integer getChara_type3() {
        return chara_type3;
    }

    public void setChara_type3(Integer chara_type3) {
        this.chara_type3 = chara_type3;
    }

    public String getChara_type_detail() {
        return chara_type_detail;
    }

    public void setChara_type_detail(String chara_type_detail) {
        this.chara_type_detail = chara_type_detail;
    }

    public String getNot_player_information() {
        return not_player_information;
    }

    public void setNot_player_information(String not_player_information) {
        this.not_player_information = not_player_information;
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
