package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Character_list;

public class CharacterValidator {
    public static List<String> validate(Character_list c) {
        List<String> errors = new ArrayList<String>();

        String chara_name_error = _validateCharaName(c.getChara_name());
        if(!chara_name_error.equals("")) {
            errors.add(chara_name_error);
        }

        String chara_name_read_error = _validateCharaNameRead(c.getChara_name_read());
        if(!chara_name_read_error.equals("")) {
            errors.add(chara_name_read_error);
        }

        return errors;
    }

    private static String _validateCharaName(String chara_name) {
        if(chara_name == null || chara_name.equals("")) {
            return "登場人物名を入力してください。";
        }

        return "";
    }

    private static String _validateCharaNameRead(String chara_name_read) {
        if(chara_name_read == null || chara_name_read.equals("")) {
            return "登場人物名の読み方を入力してください。";
        }

        return "";
    }
}
