package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

public enum ClientServices {

    OPD(1), IN_PATIENT(2), OI_ART(3), TB(4), STI(5), PMTCT(6), FHS(7), ANC(8), PNC(9), OTHER(10);

    private final Integer code;

    ClientServices(Integer code){
        this.code = code;
    }

    public static ClientServices get(Integer code){
        for(ClientServices item : values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown argument passed to method");
    }

    public String getName(){
        return StringUtils.toCamelCase3(super.name());
    }

    @Override
    public String toString() {
        return getName();
    }
}
