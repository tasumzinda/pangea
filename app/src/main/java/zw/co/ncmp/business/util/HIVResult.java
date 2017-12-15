package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

public enum HIVResult {

    NEGATIVE(1), POSITIVE(2);

    private Integer code;

    HIVResult(Integer code){
        this.code = code;
    }

    public static HIVResult get(Integer code){
        for(HIVResult item : values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown parameter");
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return getName();
    }
}
