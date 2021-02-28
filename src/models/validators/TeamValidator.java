package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Team;

public class TeamValidator {
    public static List<String> validate(Team t) {
        List<String> errors = new ArrayList<String>();

        String team_name_error = _validateTeam(t.getTeam_name());
        if(!team_name_error.equals("")) {
            errors.add(team_name_error);
        }

        return errors;
    }

    private static String _validateTeam(String team_name) {
        if(team_name == null || team_name.equals("")) {
            return "リーグ名を入力してください。";
            }

        return "";
    }
}
