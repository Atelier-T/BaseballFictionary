package models.validators;

import java.util.ArrayList;
import java.util.List;

public class Tool1Validator {
    public static List<String> validate(String create_year) {
        List<String> errors = new ArrayList<String>();

        String create_year_error = _validateCreateYear(create_year);
        if(!create_year_error.equals("")) {
            errors.add(create_year_error);
        }

        return errors;
    }

    private static String _validateCreateYear(String create_year) {
        if(create_year == null || create_year.equals("")) {
            return "作成する年度を入力してください。";
            }

        return "";
    }
}
