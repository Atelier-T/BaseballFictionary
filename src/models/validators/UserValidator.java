package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserValidator {
    public static List<String> validate(User u, Boolean nameDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String name_error = validateName(u.getUser_name(), nameDuplicateCheckFlag);
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = validatePassword(u.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    private static String validateName(String user_name, Boolean nameDuplicateCheckFlag) {
        // 必須入力チェック
        if(user_name == null || user_name.equals("")) {
            return "ユーザ名を入力してください。";
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
