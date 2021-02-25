package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Title;

public class TitleValidator {
    public static List<String> validate(Title t) {
        List<String> errors = new ArrayList<String>();

        String title_name_error = _validateTitle(t.getTitle_name());
        if(!title_name_error.equals("")) {
            errors.add(title_name_error);
        }

        return errors;
    }

    private static String _validateTitle(String title_name) {
        if(title_name == null || title_name.equals("")) {
            return "作品名を入力してください。";
            }

        return "";
    }
}
