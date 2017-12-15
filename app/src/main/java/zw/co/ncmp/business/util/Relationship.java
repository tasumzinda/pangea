package zw.co.ncmp.business.util;

import zw.co.ncmp.util.StringUtils;

public enum Relationship {

    SEXUAL_PARTNERS(1), BIOLOGICAL_CHILDREN(2), OTHER(3);

    private final Integer code;

    Relationship(Integer code){
        this.code = code;
    }

    public static Relationship get(Integer code){
        for(Relationship item : values()){
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
