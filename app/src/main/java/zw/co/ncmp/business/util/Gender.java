package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

/**
 * Created by Tasu Muzinda
 */
public enum Gender {

    MALE(1), FEMALE(2), OTHER(3);

    private final Integer code;

    Gender(Integer code){
        this.code = code;
    }

    public static Gender get(Integer code){
        for(Gender item : values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown parameter passed to method");
    }

    private static final Gender[] VALUES = {Gender.MALE, Gender.FEMALE};

    public static Gender[] getItems(){
       return VALUES.clone();
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
