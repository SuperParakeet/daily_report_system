package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.FollowView;
import constants.MessageConst;

public class FollowValidator {
    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(FollowView rv) {
        List<String> errors = new ArrayList<String>();

        //デリーとフラグのチェック
//        System.out.println("フォローの削除フラグ取得"); //デバッグ
//        String followError = validateTitle(rv.getDeleteFlag());
//        if (!followError.equals("")) {
//            System.out.println("フォロー削除フラグは無し"); //デバッグ
//            errors.add(followError);
//        }
//        System.out.println("エラーのリストを返す"); //デバッグ
        return errors;
    }
    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateTitle(int flag) {
        if (flag == 1 ) {
            System.out.println("フォロー削除済みです"); //デバッグ
            return MessageConst.E_NOTFOLLOW.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
}
}
