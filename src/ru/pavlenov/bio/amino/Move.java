package ru.pavlenov.bio.amino;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 21.12.13 20:23
 */
public enum Move {
    DOWN {
        public String toString() { return "↓"; }
    },
    RIGHT {
        public String toString() { return "→"; }
    },
    MATCH {
        public String toString() { return "⇘"; }
    },
    MIS {
        public String toString() { return "↘"; }
    },
    NULL {
        public String toString() { return "."; }
    }
}
