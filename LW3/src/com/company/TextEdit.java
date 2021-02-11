package com.company;

import java.util.Scanner;

public class TextEdit {
    StringBuilder str;

    public String getStr() {
        return str.toString();
    }

    public void setStr(String str) {
        this.str = new StringBuilder(str);
    }

    public void insertText() {
        Scanner scan = new Scanner(System.in);
        setStr(scan.nextLine());
    }

    public String getNewStr() {
        String punctuation = ",.!?;:-";
        if (str != null && str.length() != 0) {
            StringBuilder str = new StringBuilder(getStr().trim());
            int i = 0;
            while (i < str.length()) {
                char buff = str.charAt(i);
                if ((Character.isSpaceChar(buff) && (Character.isSpaceChar(str.charAt(i-1)) || Character.isSpaceChar(str.charAt(i+1)) || punctuation.contains(""+str.charAt(i+1)))) ||
                        (!Character.isLetter(buff) && !punctuation.contains(""+buff) && !Character.isSpaceChar(buff))) {
                    str.deleteCharAt(i);
                } else i++;
            }
            return str.toString();
        } else {
            return null;
        }
    }
}
