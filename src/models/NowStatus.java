package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "now_status")

@Entity
public class NowStatus {
    @Id
    @Column(name = "now_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer now_id;

    //対象キャラクター
    @ManyToOne
    @JoinColumn(name = "chara_id", nullable = true)
    private Character characters;

    //データの年度、もしくは分類番号
    @Column(name = "now_year", nullable = false, unique = true)
    private Integer now_year;

    //キャラの分類　0.選手、監督、コーチ、オーナー、その他球団関係者　1.球団関係者以外(OB・OGなどの球界関係者、ファンなど)
    @Column(name = "chara_flag", nullable = false, unique = true)
    private Integer chara_flag;

    //キャラ分類0でのデータ
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = true)
    private Player players;

    //キャラ分類1でのデータ
    @ManyToOne
    @JoinColumn(name = "not_player_id", nullable = true)
    private Player not_players;
}
