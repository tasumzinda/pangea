package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

public enum ReasonForIneligibilityForTesting {

    AWAITING_DBS_RESULTS(1), DISCLOSED_BEING_ON_ART(2), DISCLOSED_BEING_HIV_POSITIVE(3), INFANT_TESTED_NEGATIVE_AND_NOT_EXPOSED(4), INFANT_NOT_EXPOSED_AND_HIV_STATUS_UNKNOWN(5), NEED_CONSENT_FROM_PARENTS(7), RECENTLY_TESTED_NEGATIVE_LESS_THAN_3_MONTHS_AGO(8), RECENTLY_TESTED_NEGATIVE_LESS_THAN_12_MONTHS_AGO(9), RECENTLY_TESTED_NEGATIVE_LESS_THAN_6_MONTHS_AGO(10), OTHER(11);

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

    @Override
    public String toString() {
        return getName();
    }
}
