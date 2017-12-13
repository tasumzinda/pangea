package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

public enum ReasonForHIVTest {

    KNOW_HIV_STATUS(1), DIAGNOSIS(2), ANC(3), INDEX_CASE(4);

    private final Integer code;

    ReasonForHIVTest(Integer code){
        this.code = code;
    }

    public static ReasonForHIVTest get(Integer code){
        for(ReasonForHIVTest item : values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Uknown parameter passed to method");
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
