package models;

import java.time.LocalDateTime;

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

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * フォローデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_FOL)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_FOL_GET_ALL,
            query = JpaConst.Q_FOL_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOL_COUNT,
            query = JpaConst.Q_FOL_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOL_GET_ALL_MINE,
            query = JpaConst.Q_FOL_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_FOL_COUNT_ALL_MINE,
            query = JpaConst.Q_FOL_COUNT_ALL_MINE_DEF)
})
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Follow {
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.FOL_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * フォローする従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_EMP, nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_EMP_FOL, nullable = false)
    private Employee employeeFollow;

    /**
     * 削除されたフォローかどうか（登録済み：0、削除済み：1）
     */
    @Column(name = JpaConst.FOL_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.REP_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.REP_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
}
