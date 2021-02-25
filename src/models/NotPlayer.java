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

  //データの年度　「年度別データ詳細」用
    @Column(name = "data_year", nullable = true)
    private Integer data_year;

    @ManyToOne
    @JoinColumn(name = "chara_id", nullable = true)
    private Character characters;

    //0.未分類　1.トレーナー　2.打撃投手　3.チームドクター　4.通訳　5.オーナー　6.マスコット　7.その他球団職員　8.OB・OG　9.ファン　10.審判　11.コミッショナー　12.その他球界関係者　13.その他
    @Column(name = "chara_type1", nullable = false)
    private Integer chara_type1;

    //0.未分類　1.トレーナー　2.打撃投手　3.チームドクター　4.通訳　5.オーナー　6.マスコット　7.その他球団職員　8.OB・OG　9.ファン　10.審判　11.コミッショナー　12.その他球界関係者　13.その他
    @Column(name = "chara_type2", nullable = false)
    private Integer chara_type2;

    //0.未分類　1.トレーナー　2.打撃投手　3.チームドクター　4.通訳　5.オーナー　6.マスコット　7.その他球団職員　8.OB・OG　9.ファン　10.審判　11.コミッショナー　12.その他球界関係者　13.その他
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

    public Integer getData_year() {
        return data_year;
    }

    public void setData_year(Integer data_year) {
        this.data_year = data_year;
    }

    public void setNot_player_id(Integer not_player_id) {
        this.not_player_id = not_player_id;
    }

    public Character getCharacters() {
        return characters;
    }

    public void setCharacters(Character characters) {
        this.characters = characters;
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
