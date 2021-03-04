package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Character_list;
import models.NowStatus;
import utils.DBUtil;

public class NowStatusValidator {
    public static List<String> validate(NowStatus n, Character_list c, Boolean now_yearCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String now_year_error = validateNow_year(n.getNow_year(), c, now_yearCheckFlag);
        if(!now_year_error.equals("")) {
            errors.add(now_year_error);
        }
        String now_year_null = _validateNow_year(n.getNow_year());
        if(!now_year_null.equals("")) {
            errors.add(now_year_null);
        }

        return errors;
    }

    private static String validateNow_year(Integer now_year, Character_list c, Boolean now_yearCheckFlag) {
        if(now_yearCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long now_year_count = (long)em.createNamedQuery("checkNow_year", Long.class).setParameter("characters", c).setParameter("now_year", now_year).getSingleResult();
            em.close();
            if(now_year_count > 0) {
                return  c.getChara_name() + "の、"  + now_year.toString() + "年度の情報は既に存在しています。";
            }
        }

        return "";
    }

    private static String _validateNow_year(Integer now_year) {
        if(now_year == null || now_year.equals("")) {
            return "年度を入力して下さい。";
            }

        return "";
    }
}

