package com.learn.bases.enums.enumsTest.enumsTest;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 09.05.12
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class TestEnums {
    public static Logger LOG = Logger.getLogger(TestEnums.class);
    public static void main(String[] args){
        System.out.println(Arrays.toString(Season.values()));
        System.out.println(Season.FUCK);
        System.out.println(Season.class.getSuperclass());
        
        Season season = Season.FUCK;
        System.out.println(season.ordinal());

        String name = "FUCK";
        Season season2 = Season.valueOf(name);
        System.out.println(season2);
        LOG.info("test ept");
        
        Age age = Age.YOUNG;
        age.ss = "ddd";
        System.out.println(Age.YOUNG.getSs());
    }
    enum Season{FUCK, OFF}
    
    enum Age{

        YOUNG{
            @Override
            public String getSs() {
                return "RRRRRR";
            }
        },
        OLD;

        public String getSs() {
            return ss;
        }

        String ss;

        Age(String s) {
          this.ss = s;  
        }

        Age() {
            
        }
    }
    
}
