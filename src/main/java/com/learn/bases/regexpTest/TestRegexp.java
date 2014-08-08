package com.learn.bases.regexpTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//^        Matches the position at the beginning of the input string. If the RegExp object's Multiline property is set, ^ also matches the position following '\n' or '\r'.
//$        Matches the position at the end of the input string. If the RegExp object's Multiline property is set, $ also matches the position preceding '\n' or '\r'.
//*        Matches the preceding character or subexpression zero or more times. For example, zo* matches "z" and "zoo". * is equivalent to {0,}.
///+       Matches the preceding character or subexpression one or more times. For example, 'zo+' matches "zo" and "zoo", but not "z". + is equivalent to {1,}.
//?        Matches the preceding character or subexpression zero or one time. For example, "do(es)?" matches the "do" in "do" or "does". ? is equivalent to {0,1}
//{n}      n is a nonnegative integer. Matches exactly n times. For example, 'o{2}' does not match the 'o' in "Bob," but matches the two o's in "food".
//{n,}     n is a nonnegative integer. Matches at least n times. For example, 'o{2,}' does not match the "o" in "Bob" and matches all the o's in "foooood". 'o{1,}' is equivalent to 'o+'. 'o{0,}' is equivalent to 'o*'.
//{n,m}    M and n are nonnegative integers, where n <= m. Matches at least n and at most m times. For example, "o{1,3}" matches the first three o's in "fooooood". 'o{0,1}' is equivalent to 'o?'. Note that you cannot put a space between the comma and the numbers.
//?       When this character immediately follows any of the other quantifiers (*, +, ?, {n}, {n,}, {n,m}), the matching pattern is non-greedy. A non-greedy pattern matches as little of the searched string as possible
//.      Matches any single character except "\n". To match any character including the '\n', use a pattern such as '[\s\S]'.
//(pattern)   A subexpression that matches pattern and captures the match. The captured match can be retrieved from the resulting Matches collection using the $0…$9 properties. To match parentheses characters ( ), use '\(' or '\)'.
//(?:pattern)    A subexpression that matches pattern but does not capture the match, that is, it is a non-capturing match that is not stored for possible later use. This is useful for combining parts of a pattern with the "or" character (|). For example, 'industr(?:y|ies) is a more economical expression than 'industry|industries'.
//(?=pattern)   A subexpression that performs a positive lookahead search, which matches the string at any point where a string matching pattern begins. This is a non-capturing match, that is, the match is not captured for possible later use. For example 'Windows (?=95|98|NT|2000)' matches "Windows" in "Windows 2000" but not "Windows" in "Windows 3.1". Lookaheads do not consume characters, that is, after a match occurs, the search for the next match begins immediately following the last match, not after the characters that comprised the lookahead.
//(?!pattern)   A subexpression that performs a negative lookahead search, which matches the search string at any point where a string not matching pattern begins.
//x|y   Matches either x or y. For example, 'z|food' matches "z" or "food". '(z|f)ood' matches "zood" or "food".
//[xyz]  A character set. Matches any one of the enclosed characters. For example, '[abc]' matches the 'a' in "plain".
//[^xyz]  A negative character set. Matches any character not enclosed. For example, '[^abc]' matches the 'p' in "plain".
//a-z]    A range of characters. Matches any character in the specified range. For example, '[a-z]' matches any lowercase alphabetic character in the range 'a' through 'z'.
//[^a-z]  A negative range characters. Matches any character not in the specified range. For example, '[^a-z]' matches any character not in the range 'a' through 'z'.
//\b  Matches a word boundary, that is, the position between a word and a space. For example, 'er\b' matches the 'er' in "never" but not the 'er' in "verb".
//\B  Matches a nonword boundary. 'er\B' matches the 'er' in "verb" but not the 'er' in "never".
//\cx Matches the control character indicated by x. For example, \cM matches a Control-M or carriage return character. The value of x must be in the range of A-Z or a-z. If not, c is assumed to be a literal 'c' character.
//\d  Matches a digit character. Equivalent to [0-9].
//\D  Matches a nondigit character. Equivalent to [^0-9].
//\n  Matches a newline character. Equivalent to \x0a and \cJ.
//\r  Matches a carriage return character. Equivalent to \x0d and \cM.
//\s  Matches any white space character including space, tab, form-feed, and so on. Equivalent to [ \f\n\r\t\v].
//\S  Matches any non-white space character. Equivalent to [^ \f\n\r\t\v].
//\t  Matches a tab character. Equivalent to \x09 and \cI.
//\w  Matches any word character including underscore. Equivalent to '[A-Za-z0-9_]'.
//\W  Matches any nonword character. Equivalent to '[^A-Za-z0-9_]'.


/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 27.05.12
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class TestRegexp {
    public static void main(String[] args){
         Pattern manyBlanks = Pattern.compile("^\\s*y+u?do(es)?u{5,}i{3}?.(?:du|mu)[^dfs][^a-s]*dim\\Bbb\\d\\D\\s*$");
         Matcher blanks = manyBlanks.matcher("    yyyudouuuuuuiiijmulwwwdimbb6d    ");
       System.out.println(blanks.matches());

    }
}
