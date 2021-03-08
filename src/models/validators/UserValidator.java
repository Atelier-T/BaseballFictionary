package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean nameCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String name_error = validateName(u.getUser_name());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String name_error2 = _validateName(u.getUser_name(), nameCheckFlag);
        if(!name_error2.equals("")) {
            errors.add(name_error2);
        }

        String password_error = validatePassword(u.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    private static String validateName(String user_name) {
        // 必須入力チェック
        if(user_name == null || user_name.equals("")) {
            return "ユーザ名を入力してください。";
        }

        return "";
    }

    private static String _validateName(String user_name, Boolean nameCheckFlag) {
        if(nameCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long user_name_count = (long)em.createNamedQuery("checkUserName", Long.class).setParameter("user_name", user_name).getSingleResult();
            em.close();
            if(user_name_count > 0) {
                return  user_name + "の情報は既に登録されています。";
            }
        }

        return "";
    }

 // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}
