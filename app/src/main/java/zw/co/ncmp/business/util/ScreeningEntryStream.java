package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

/**
 * @uthor Tasu Muzinda
 */
public enum ScreeningEntryStream {

    OPD(1), FHS(2), OTHER_OPD(3);

    private final Integer code;

    ScreeningEntryStream(Integer code){
        this.code = code;
    }

    public static ScreeningEntryStream get(Integer code){
        for(ScreeningEntryStream item : values()){
            if(item.code.equals(code))
                return item;
        }
        throw new IllegalArgumentException("Parameter provided to method is unknown");
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return getName();
    }
}
