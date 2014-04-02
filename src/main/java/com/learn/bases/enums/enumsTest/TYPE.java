package com.learn.bases.enums.enumsTest;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 09.05.12
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
enum Type {

    INT(true) {
        public Object parse(String string) { return Integer.valueOf(string); }

        @Override
        public String getValue() {
            return "B";
        }
    },
    INTEGER(false) {
        @Override
        public String getValue() {
            return null;
        }

        public Object parse(String string) { return Integer.valueOf(string); }
    },
    STRING(false) {
        public Object parse(String string) { return string; }

        @Override
        public String getValue() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    };

    boolean primitive;
    Type(boolean primitive) { this.primitive = primitive; }

    public boolean isPrimitive() { return primitive; }
    public abstract Object parse(String string);
    public abstract String getValue();
}
