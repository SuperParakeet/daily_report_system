package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.FollowConverter;
import actions.views.FollowView;
import models.validators.FollowValidator;

public class FollowService extends ServiceBase {


    /**
     * フォローの登録内容を元にデータを1件作成し、フォローテーブルに登録する
     * @param rv フォローの登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(FollowView rv) {
        System.out.println("FollowValidator呼び出し"); //デバッグ
        List<String> errors = FollowValidator.validate(rv);
        if (errors.size() == 0) {
            System.out.println("現在時刻取得）"); //デバッグ
            LocalDateTime ldt = LocalDateTime.now();
            rv.setCreatedAt(ldt);
            rv.setUpdatedAt(ldt);
            createInternal(rv);
        }
            //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
            return errors;
        }
    /**
     * フォローデータを1件登録する
     * @param rv フォローデータ
     */
        private void createInternal(FollowView rv) {
            System.out.println("DBに登録処理）"); //デバッグ
            em.getTransaction().begin();
            em.persist(FollowConverter.toModel(rv));
            em.getTransaction().commit();               //コミット

        }
}
