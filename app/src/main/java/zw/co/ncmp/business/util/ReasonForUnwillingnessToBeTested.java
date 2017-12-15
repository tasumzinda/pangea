package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

public enum ReasonForUnwillingnessToBeTested {

    FEELING_SCARED(1), NEED_MORE_TIME_TO_PREPARE_FOR_TEST(2), NEED_TO_CONSULT_PARTNER_OR_SIGNIFICANT_OTHER(3), DECLINED_WITH_NO_EXPLANATION(4), OTHER(5);

    private final Integer code;

    ReasonForUnwillingnessToBeTested(Integer code){
        this.code = code;
    }

    public static ReasonForUnwillingnessToBeTested get(Integer code){
        for(ReasonForUnwillingnessToBeTested item : values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Uknown parameter passed to method");
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return getName();
    }
}
