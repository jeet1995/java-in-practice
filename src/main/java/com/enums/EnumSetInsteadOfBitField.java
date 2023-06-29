package com.enums;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetInsteadOfBitField {
    public enum Styles {
        BOLD, ITALIC, CALIBRI, ARIAL;

        public Set<Styles> getCalibriFontWithItalic() {
            return EnumSet.of(Styles.ITALIC, Styles.CALIBRI);
        }
    }
}
