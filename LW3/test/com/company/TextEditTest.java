package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextEditTest {
    TextEdit textEdit;

    @BeforeEach
    void setUp() {
        textEdit = new TextEdit();
    }

    @Test
    void testGetNewStr1() {
        textEdit.setStr("f4hjsf 2hf   4rhf     dhef4ghsif4543b sf");
        String expected = "fhjsf hf rhf dhefghsifb sf";
        assertEquals(expected, textEdit.getNewStr(), "not correct");
    }

    @Test
    void testGetNewStr2() {
        textEdit.setStr("     8r4sfh83 fh8 f fhff f f    8f3h  23r xf  2 233 fbdt   ");
        String expected = "rsfh fh f fhff f f fh r xf fbdt";
        assertEquals(expected, textEdit.getNewStr(), "not correct");
    }

    @Test
    void testGetNewStr3() {
        textEdit.setStr("Michael simply had no idea what was about to come and even though Greg could prevent it from happening, he opted \\ to let it happen. It was qui4534te ironic, rea//lly? It was some\\\\thing Greg had said  he  would never   wish upon anyone a   million times   , yet here he was knowingly le45514tting it happen to o3ne of4434 hi1s be45st  452  friends!  ");
        String expected = "Michael simply had no idea what was about to come and even though Greg could prevent it from happening, he opted to let it happen. It was quite ironic, really? It was something Greg had said he would never wish upon anyone a million times, yet here he was knowingly letting it happen to one of his best friends!";
        assertEquals(expected, textEdit.getNewStr(), "not correct");
    }

    @Test
    void testGetNewStr4() {
        textEdit.setStr("Просто какой-то, тек344ст!     2Нич4его45  , такого*?");
        String expected = "Просто какой-то, текст! Ничего, такого?";
        assertEquals(expected, textEdit.getNewStr(), "not correct");
    }
}