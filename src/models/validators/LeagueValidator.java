package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.League;

public class LeagueValidator {
    public static List<String> validate(League l) {
        List<String> errors = new ArrayList<String>();

        String league_name_error = _validateLeague(l.getLeague_name());
        if(!league_name_error.equals("")) {
            errors.add(league_name_error);
        }

        return errors;
    }

    private static String _validateLeague(String league_name) {
        if(league_name == null || league_name.equals("")) {
            return "リーグ名を入力してください。";
            }

        return "";
    }
}
