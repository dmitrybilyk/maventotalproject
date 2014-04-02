package com.learn.bases.staticblocks;

public class Usualinitializingblock {
    private String value;
    private String value2;

    public Usualinitializingblock(String value) {
        this.value = value;
    }


    {setValue2("testValue2Setting in init block");}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
