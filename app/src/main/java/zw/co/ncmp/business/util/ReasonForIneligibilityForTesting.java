package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

public enum ReasonForIneligibilityForTesting {

    DISCLOSED_BEING_ON_ART(1), DISCLOSED_BEING_HIV_POSITIVE(2), RECENTLY_TESTED_NEGATIVE_LESS_THAN_3_MONTHS_AGO(3), RECENTLY_TESTED_NEGATIVE_LESS_THAN_12_MONTHS_AGO(4), RECENTLY_TESTED_NEGATIVE_LESS_THAN_6_MONTHS_AGO(5), OTHER(6);

    private final Integer code;

    ReasonForIneligibilityForTesting(Integer code){
        this.code = code;
    }

    public static ReasonForIneligibilityForTesting get(Integer code){
        for(ReasonForIneligibilityForTesting item : values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown parameter passed to method");
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }
}
