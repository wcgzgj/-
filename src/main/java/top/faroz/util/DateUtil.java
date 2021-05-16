package top.faroz.util;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/5/16 下午10:40
 * @Version 1.0
 **/
public class DateUtil {
    public static java.util.Date sqlDate2JavaDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.sql.Date javaDate2SqlDate(java.util.Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }


}
