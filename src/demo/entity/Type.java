package demo.entity;

/**
 * Created by hhh on 2017/4/8.
 */
public enum Type {
    String, Numeric, Date;

    public static Type toType (String typeStr) {
        if("String".equals(typeStr)) return Type.String;
        if("Numeric".equals(typeStr)) return Type.Numeric;
        if("Date".equals(typeStr)) return Type.Date;

        return Type.String;
    }
}
