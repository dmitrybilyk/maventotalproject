package com.learn.bases.refexTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AfterMapper {
    public static void main(String[] args){
        short s = 12225;
        byte b = (byte) s;
        byte r = (byte) (b+b);
        System.out.println("length - "+ "                                   ".length());


//        0119143001111                                SpanishClient                                                         20121213PRE2012121314334000186111          00190109
//        0219143002BE31ZZZ12345                       20160420SpanishClient                                                         Main Street                                                                                                                                 ESES5000190109904010053346
//        0319143003111437269871646FC147               111437269871                       RCUR    0000500010020100201BILLLULL   Eric de builde                                                                                                                                                                                                    ES                                                                        ALU860023189150474000
//        0319143005111437269871646FC147               111437269871                       01861234ESABD123456ABDC1234ABXDef123456
//        0319143003111437269872BDEF51F3               111437269872                       RCUR    0000500010020100201DEUTESBB   Eric de builde2                                                                                                                                                                                                   LU                                                                        AES5000190109904010053346
//        0319143005111437269872BDEF51F3               111437269872                       01861234LUABD123456ABDC1234ABXDef123456
//        031914300311143726987366C9DDBD               111437269873                       RCUR    0000500010020120201DEUTESBB   Eric de builde3                                                                                                                                                                                                   ES                                                                        AES5000190109904010053346
//        04BE31ZZZ12345                       2016042000000000015000300000000030000000007
//        05BE31ZZZ12345                       00000000015000300000000030000000008
//        9900000000015000300000000030000000010


        String txt1 = "HDR1A919100S         CAP00100020001000100 12362 13259 000000                                                            ";
//        String txt1 = "HDR1A919100S         CAP00100010001000100  1235 13259 000000                                                            ";
//        String txt1 = "HDR2F0204800120                                   00                                                                    ";
//        String txt1 = "UHL1 13259000000    010000001 DAILY  001                                                                                ";
//        String txt1 = "      5404791 0U799005103394123616600000000010-----DEBITOR2-----ENDTOENDID00000002Belgacom            111                ";
 //       String txt1 = "      5404791 0U199005103394123616600000000010-----DEBITOR2-----ENDTOENDID00000002Belgacom            111                ";
//        String txt1 = "EOF1A919100S         CAP001    0001000100  1236 13259 000000 ";
//        String txt1 = "EOF2F0204800120                                   00                                                                    ";
//        String txt1 = "UTL10010011111111000000000000000000060000000                                                                            ";



//        ^31\d{6}81\d{6}E\s{25}France Telecom\s{42}France Telecom.*$
//                ^34\d{6}81\d{6}E\s{25}France Telecom\s{21}\d{21}DB FRENCH DEBTOR\s{14}DB FRENCH DEBTOR.{70}\d{6}\s{6}3\d{13}$
//                ^39\d{6}81\d{6}\s{26}France Telecom\s{42}France Telecom\s{116}\d{12}$
//        String txt1 = "0119143001111                                SpanishClient                                                         20121213PRE2012121317530400819111          00190109                                                                                                                                                                                                                                                                                                                                 ";
//        String txt2 =  "0219143002BE31ZZZ12345                       20160420SpanishClient                                                         Main Street                                                                                                                                 ESES5000190109904010053346                               ";


        Pattern pattern1 = Pattern.compile("^HDR1A\\d{6}S\\s{9}\\w{6}\\d{4}0001000100\\s{1}\\d{5}\\s\\d{5}\\s\\d{6}\\s+$");
//        Pattern pattern1 = Pattern.compile("^HDR\\dA\\d{6}S\\s{9}\\w{6}\\d{4}0001000100\\s{2}\\d{4}\\s\\d{5}\\s\\d{6}\\s+$");
//        Pattern pattern1 = Pattern.compile("^HDR2F0204800120\\s{35}\\d{2}\\s+$");
//        Pattern pattern1 = Pattern.compile("^HDR2F0204800120\\s{35}\\d{2}\\s+$");
//        Pattern pattern1 = Pattern.compile("^.{6}\\d{7}\\s\\d.{2}\\d{29}.{18}.{18}\\w{8}\\s{12}\\d{3}\\s+$");
//        Pattern pattern1 = Pattern.compile("^EOF1A\\d{6}S\\s{9}.{10}\\d{4}000100\\s{2}\\d{4}\\s\\d{5}\\s\\d{6}\\s+$");
//        Pattern pattern1 = Pattern.compile("^EOF2F0204800120\\s{35}\\d{2}\\s+$");
//        Pattern pattern1 = Pattern.compile("^UTL\\d{6}\\d{8}\\d{27}\\s+$");

//        String pattern = pattern1.toString()+pattern2.toString()+pattern3.toString()+pattern4.toString()+pattern5.toString()+pattern6.toString()+pattern7.toString()+pattern8.toString()+               pattern9.toString()+pattern10.toString();
//        Pattern resultPattern = Pattern.compile(pattern);

//        Matcher matcher = resultPattern.matcher(txt);
        Matcher matcher1 = pattern1.matcher(txt1);
//        Matcher matcher2 = pattern2.matcher(txt2);
//        Matcher matcher3 = pattern3.matcher(txt3);
//        Matcher matcher4 = pattern4.matcher(txt4);
//        Matcher matcher5 = pattern5.matcher(txt5);
//        Matcher matcher6 = pattern6.matcher(txt6);
//        Matcher matcher7 = pattern7.matcher(txt7);
//        Matcher matcher8 = pattern8.matcher(txt8);
//        Matcher matcher9 = pattern9.matcher(txt9);
//        Matcher matcher10 = pattern10.matcher(txt10);
        System.out.println(matcher1.matches());
//        System.out.println(matcher2.matches());
//        System.out.println(matcher3.matches());
//        System.out.println(matcher4.matches());
//        System.out.println(matcher5.matches());
//        System.out.println(matcher6.matches());
//        System.out.println(matcher7.matches());
//        System.out.println(matcher8.matches());
//        System.out.println(matcher9.matches());
//        System.out.println(matcher10.matches());
    }
}
