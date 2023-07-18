package BaseClasses;

import org.apache.commons.lang.RandomStringUtils;

public class StringGenerate {
    public static String RandomString(int n) {

        int length = n;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    } // генерация случайной строки


}
