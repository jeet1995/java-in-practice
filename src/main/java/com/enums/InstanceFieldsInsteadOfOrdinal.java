package com.enums;

public class InstanceFieldsInsteadOfOrdinal {

    public enum MusicianCount {
        // always use instance fields ("musicianCount")
        // as ordinality can be broken by adding new enum types
        SOLO(1), DUET(2), TRIPLET(3);

        private final int musicianCount;

        MusicianCount(int musicianCount) {
            this.musicianCount = musicianCount;
        }

        public int getMusicianCount() {
            return musicianCount;
        }
    }
}
