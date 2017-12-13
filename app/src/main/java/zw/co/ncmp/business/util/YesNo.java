package zw.co.ncmp.business.util;

public enum YesNo {

    YES(1), NO(2);

    private final Integer code;

    YesNo(Integer code){
        this.code = code;
    }

    public static YesNo get(Integer code){
        for(YesNo item : values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Uknown parameter passed to method");
    }
}
