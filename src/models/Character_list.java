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

@Table(name = "characters")

@NamedQueries({
    @NamedQuery(
        name = "getMyAllCharacters",
        query = "SELECT c FROM Character_list AS c WHERE c.titles = :titles ORDER BY c.chara_name_read"
    ),
    @NamedQuery(
        name = "getMyCharactersCount",
        query = "SELECT COUNT(c) FROM Character_list AS c WHERE c.titles = :titles"
    ),
    @NamedQuery(
        name = "getMyAllCharactersForReaders",
        query = "SELECT c FROM Character_list AS c WHERE c.titles = :titles and c.appearance_flag = 1 ORDER BY c.chara_name_read"
    ),
    @NamedQuery(
        name = "getMyCharactersCountForReaders",
        query = "SELECT COUNT(c) FROM Character_list AS c WHERE c.titles = :titles and c.appearance_flag = 1"
    ),
    @NamedQuery(
        name = "getMyAllCharactersPlayers",
        query = "SELECT c FROM Character_list AS c WHERE c.titles = :titles and c.now_status.chara_flag = 0"
    ),
    @NamedQuery(
        name = "getMyAllCharactersNotPlayers",
        query = "SELECT c FROM Character_list AS c WHERE c.titles = :titles and c.now_status.chara_flag = 1"
    )
})

@Entity
public class Character_list {
    @Id
    @Column(name = "chara_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chara_id;

    @Column(name = "chara_name", length = 255, nullable = false)
    private String chara_name;

    //読み方
    @Column(name = "chara_name_read", length = 255, nullable = false)
    private String chara_name_read;

    //作品情報
    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title titles;

    //現在の選手or選手以外としての情報　nullならtitleのyearなどを参考に自動分類
    @ManyToOne
    @JoinColumn(name = "now_status", nullable = true)
    private NowStatus now_status;

    //原典
    @Column(name = "original", length = 255, nullable = true)
    private String original;

    //モデル
    @Column(name = "chara_model", length = 255, nullable = true)
    private String chara_model;

    //ストーリー上での重要度　0.未分類　1.主役級　2.準主役級　3.レギュラー級　4.準レギュラー級　5.モブ
    @Column(name = "chara_priority", nullable = false)
    private Integer chara_priority;

    //誕生年度
    @Column(name = "birth_year", nullable = true)
    private Integer birth_year;

    //出身地
    @Column(name = "birth_place", length = 255, nullable = true)
    private String birth_place;

    //初登場話
    @Column(name = "appearance", nullable = true)
    private Integer appearance;

    //0.未登場　1.登場
    @Column(name = "appearance_flag", nullable = false)
    private Integer appearance_flag;

    @Column(name = "chara_information", nullable = true)
    private String chara_information;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;


    public Integer getChara_id() {
        return chara_id;
    }

    public void setChara_id(Integer chara_id) {
        this.chara_id = chara_id;
    }

    public String getChara_name() {
        return chara_name;
    }

    public void setChara_name(String chara_name) {
        this.chara_name = chara_name;
    }

    public String getChara_name_read() {
        return chara_name_read;
    }

    public void setChara_name_read(String chara_name_read) {
        this.chara_name_read = chara_name_read;
    }

    public Title getTitles() {
        return titles;
    }

    public void setTitles(Title titles) {
        this.titles = titles;
    }

    public NowStatus getNow_status() {
        return now_status;
    }

    public void setNow_status(NowStatus now_status) {
        this.now_status = now_status;
    }

    public String getOriginal() {
        return original;
    }

    public String getChara_model() {
        return chara_model;
    }

    public void setChara_model(String chara_model) {
        this.chara_model = chara_model;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Integer getChara_priority() {
        return chara_priority;
    }

    public void setChara_priority(Integer chara_priority) {
        this.chara_priority = chara_priority;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public Integer getAppearance() {
        return appearance;
    }

    public void setAppearance(Integer appearance) {
        this.appearance = appearance;
    }

    public Integer getAppearance_flag() {
        return appearance_flag;
    }

    public void setAppearance_flag(Integer appearance_flag) {
        this.appearance_flag = appearance_flag;
    }

    public String getChara_information() {
        return chara_information;
    }

    public void setChara_information(String chara_information) {
        this.chara_information = chara_information;
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
