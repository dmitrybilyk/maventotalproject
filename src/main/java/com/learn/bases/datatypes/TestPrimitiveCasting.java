package com.learn.bases.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 27.06.12
 * Time: 15:26
 * To change this template use File | Settings | File Templates.
 */
public class TestPrimitiveCasting {
    public static void main(String[] args){
        short s = 12225;
        byte b = (byte) s;
        byte r = (byte) (b+b);
        System.out.println("length - "+ "                                                                        ".length());


//        0119143001111                                SpanishClient                                                         20121213PRE2012121314334000186111          00190109
//        0219143002BE31ZZZ12345                       20160420SpanishClient                                                         TurkeyMain Street                                                                                                                                 ESES5000190109904010053346
//        0319143003111437269871646FC147               111437269871                       RCUR    0000500010020100201BILLLULL   Eric de builde                                                                                                                                                                                                    ES                                                                        ALU860023189150474000
//        0319143005111437269871646FC147               111437269871                       01861234ESABD123456ABDC1234ABXDef123456
//        0319143003111437269872BDEF51F3               111437269872                       RCUR    0000500010020100201DEUTESBB   Eric de builde2                                                                                                                                                                                                   LU                                                                        AES5000190109904010053346
//        0319143005111437269872BDEF51F3               111437269872                       01861234LUABD123456ABDC1234ABXDef123456
//        031914300311143726987366C9DDBD               111437269873                       RCUR    0000500010020120201DEUTESBB   Eric de builde3                                                                                                                                                                                                   ES                                                                        AES5000190109904010053346
//        04BE31ZZZ12345                       2016042000000000015000300000000030000000007
//        05BE31ZZZ12345                       00000000015000300000000030000000008
//        9900000000015000300000000030000000010


//        String txt1 = "0119143001111                                SpanishClient                                                         20121213PRE2012121314334000186111          00190109 ";
//        String txt2 =  "0219143002BE31ZZZ12345                       20160420SpanishClient                                                         TurkeyMain Street                                                                                                                                 ESES5000190109904010053346 ";
//          String txt3 =  "0319143003111437269871646FC147               111437269871                       RCUR    0000500010020100201BILLLULL   Eric de builde                                                                                                                                                                                                    ES                                                                        ALU860023189150474000 " ;
//          String txt4 = "0319143005111437269871646FC147               111437269871                       01861234ESABD123456ABDC1234ABXDef123456 " ;
//          String txt5 = "0319143003111437269872BDEF51F3               111437269872                       RCUR    0000500010020100201DEUTESBB   Eric de builde2                                                                                                                                                                                                   LU                                                                        AES5000190109904010053346 " ;
//                String txt6 = "0319143005111437269872BDEF51F3               111437269872                       01861234LUABD123456ABDC1234ABXDef123456 ";
//                String txt7 = "031914300311143726987366C9DDBD               111437269873                       RCUR    0000500010020120201DEUTESBB   Eric de builde3                                                                                                                                                                                                   ES                                                                        AES5000190109904010053346 ";
//             String txt8 =   "04BE31ZZZ12345                       2016042000000000015000300000000030000000007 ";
//             String txt9 = "05BE31ZZZ12345                       00000000015000300000000030000000008 ";
//           String txt10 =      "9900000000015000300000000030000000010 ";
//           String txt = txt1+txt2+txt3+txt4+txt5+txt6+txt7+txt8+txt9+txt10;

//        String txt1 = "0119143001111                                SpanishClient                                                         20121213PRE2012121317530400819111          00190109                                                                                                                                                                                                                                                                                                                                 ";
        String txt2 =  "0219143002BE31ZZZ12345                       20160420SpanishClient                                                         TurkeyMain Street                                                                                                                                 ESES5000190109904010053346                               ";
//          String txt3 =  "03191430031114372698731BE662EC               111437269873                       RCUR    0000500010020120201DEUTESBB   Eric de builde3                                                                                                                                                                                                   ES                                                                        AES5000190109904010053346          " ;
//          String txt4 = "0319143005111437269871CF76ECC0               111437269871                       01861234ESABD123456ABDC1234ABXDef123456                                                                                                                                                                                                                                                            " ;
//          String txt5 = "03191430031114372698716C56BC77               111437269871                       RCUR    0000500010020100201BILLLULL   Eric de builde                                                                                                                                                                                                    ES                                                                        ALU860023189150474000   " ;
//                String txt6 = "0319143005111437269872203F0DB4               111437269872                       01861234LUABD123456ABDC1234ABXDef123456                                                                                                                                                                                                                                                                                                                                                                                                                                                               ";
//                String txt7 = "03191430051114372698716C56BC77               111437269871                       01861234ESABD123456ABDC1234ABXDef123456                                                                                                                                                                                                                                                                                                                                                                                                                            ";
//             String txt8 =   "04BE31ZZZ12345                       2016042000000000015000300000000030000000007                                                                                                                                                                                                                                                                         ";
//             String txt9 = "05BE31ZZZ12345                       00000000015000300000000030000000008                                                                           \n";
//           String txt10 =      "9900000000015000300000000030000000010             ";
//           String txt = txt1+txt2+txt3+txt4+txt5+txt6+txt7+txt8+txt9+txt10;

//        Pattern pattern1 = Pattern.compile("^0\\d19143001{4}\\s{32}SpanishClient\\s{57}\\d{8}PRE\\d{22}\\s{10}\\d{8}\\s+$");
        Pattern pattern2 = Pattern.compile("^0\\d1914300\\w{13}\\s{23}\\d{8}SpanishClient\\s{57}.+\\s{129}\\w{26}\\s+$");
//        Pattern pattern3 = Pattern.compile("^0\\d1914300\\d11143726987\\w{9}\\s{15}11143726987\\d\\s{23}\\w{4}\\s{4}0{4}5000100201\\d0201\\w{8}\\s{3}.{15}\\s{195}\\w{2}\\s{72}.{25}\\s+$");
//         Pattern pattern4 = Pattern.compile("^0\\d1914300\\d11143726987\\w{9}\\s{15}11143726987[1|2]\\s{23}01861234\\w{2}ABD123456ABDC1234ABXDef123456\\s+$");     Pattern pattern5 = Pattern.compile("^0\\d1914300\\d11143726987\\w{9}\\s{15}11143726987[1|2]\\s{23}\\w{4}\\s{4}0{4}500010020100201\\w{8}\\s{3}\\w{4}\\s\\w{2}\\s\\w{7}\\s{196}\\w{2}\\s{72}\\w{21}\\s+$");
//        Pattern pattern5 = Pattern.compile("^0\\d1914300\\d11143726987\\w{9}\\s{15}11143726987\\d\\s{23}\\w{4}\\s{4}0{4}5000100201\\d0201\\w{8}\\s{3}\\w{4}\\s\\w{2}\\s\\w{6}\\s{196}\\w{2}\\s{72}\\w{21}\\s+$");
//         Pattern pattern6 = Pattern.compile("^0\\d1914300\\d11143726987\\w{9}\\s{15}11143726987[1|2]\\s{23}01861234\\w{2}ABD123456ABDC1234ABXDef123456\\s+$");
//      Pattern pattern7 = Pattern.compile("^0\\d1914300\\d11143726987\\w{9}\\s{15}11143726987\\d\\s{23}\\d{8}\\w{5}\\d{6}\\w{14}\\d{6}\\s+$");
//     Pattern pattern8 = Pattern.compile("^0\\d\\w{12}\\s{23}\\d{43}\\s+$");
//         Pattern pattern9 = Pattern.compile("^0\\d\\w{12}\\s{23}\\d{35}\\s+$");
//       Pattern pattern10 = Pattern.compile("^990{9}\\d{6}0{9}\\d0{8}\\d0.+$");
//
//        String pattern = pattern1.toString()+pattern2.toString()+pattern3.toString()+pattern4.toString()+pattern5.toString()+pattern6.toString()+pattern7.toString()+pattern8.toString()+               pattern9.toString()+pattern10.toString();
//        Pattern resultPattern = Pattern.compile(pattern);

//        Matcher matcher = resultPattern.matcher(txt);
//        Matcher matcher1 = pattern1.matcher(txt1);
        Matcher matcher2 = pattern2.matcher(txt2);
//        Matcher matcher3 = pattern3.matcher(txt3);
//        Matcher matcher4 = pattern4.matcher(txt4);
//        Matcher matcher5 = pattern5.matcher(txt5);
//        Matcher matcher6 = pattern6.matcher(txt6);
//        Matcher matcher7 = pattern7.matcher(txt7);
//        Matcher matcher8 = pattern8.matcher(txt8);
//        Matcher matcher9 = pattern9.matcher(txt9);
//        Matcher matcher10 = pattern10.matcher(txt10);
//        System.out.println(matcher1.matches());
        System.out.println(matcher2.matches());
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
