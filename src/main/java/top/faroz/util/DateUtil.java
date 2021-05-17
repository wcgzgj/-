package top.faroz.util;

import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午10:40
 * @Version 1.0
 **/
public class DateUtil {
    public static java.util.Date sqlDate2JavaDate(java.sql.Date sqlDate) {
        if (sqlDate==null) {
            return new Date();
        }
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.sql.Date javaDate2SqlDate(java.util.Date utilDate) {
        if (utilDate==null) {
            return new java.sql.Date(new java.util.Date().getTime());
        }
        return new java.sql.Date(utilDate.getTime());
    }


}
