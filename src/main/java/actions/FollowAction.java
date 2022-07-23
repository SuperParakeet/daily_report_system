package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.FollowView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.EmployeeService;
import services.FollowService;

public class FollowAction extends ActionBase {
    private EmployeeService service;
    private FollowService followservice;
    @Override
    public void process() throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ
        service = new EmployeeService();
        followservice = new FollowService();
        invoke();
        service.close();
        followservice.close();
    }
    /**
     * Follow登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        System.out.println("CSRF対策 tokenのチェック直前"); //デバッグ
//        forward(ForwardConst.FW_ERR_UNKNOWN);
//        if (checkToken()) {

            //セッションからログイン中の従業員情報を取得
            System.out.println("ログイン中の従業員情報取得前"); //デバッグ
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            System.out.println("フォローされる従業員情報取得前"); //デバッグ
            EmployeeView fv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));;//フォローされる従業員

            //パラメータの値をもとにフォロー情報のインスタンスを作成する
            System.out.println("フォロー情報インスタンス前"); //デバッグ
            FollowView rv = new FollowView(
                    null,
                    ev, //ログインしている従業員を、フォローする従業員として登録する
                    fv,//フォローされる従業員を登録する。
                    0,
                    null,
                    null);

            //フォロー情報登録
            System.out.println("フォロー登録前"); //デバッグ
            List<String> errors = followservice.create(rv);
            System.out.println("エラーリスト取得（空の場合もある）"); //デバッグ
            if (errors.size() > 0) {
                //登録中にエラーがあった場合
                System.out.println("フォロー登録中にエラーがあった場合"); //デバッグ
                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.REPORT, rv);//入力された日報情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

//                //新規登録画面を再表示
//                forward(ForwardConst.FW_REP_NEW);
//                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);

            } else {
                //登録中にエラーがなかった場合
                System.out.println("フォロー登録中にエラーが無かった場合"); //デバッグ
                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
//            }
        }
//    }
    }
}
