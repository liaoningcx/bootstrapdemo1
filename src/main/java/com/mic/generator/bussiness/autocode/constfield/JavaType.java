package com.mic.generator.bussiness.autocode.constfield;

/**
 * Created by cao_x on 2020/4/21.
 */
public enum  JavaType {

    //BOOLEAN
    BOOLEAN("Boolean"),
    //文本
    STRING("String"),
    //字节流，二进制
    BYTE("Byte [] "),
    //整数
    INTEGER("Integer"),
    BIGINTEGER("BigInteger"),
    //浮点小数
    FLOAT("Float"),
    DOUBLE("Double"),
    //精确浮点小数
    BIGDECIMAL("BigDecimal"),
    //时间
    DATE("Date"),
    TIME("Time"),
    TIMESTAMP("Timestamp");

    private String key;

    private JavaType(String javatype){
        this.key = javatype;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
