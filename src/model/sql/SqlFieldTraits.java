package model.sql;

public enum SqlFieldTraits {
    NOT_NULL("NOT NULL"),
    UNIQUE("UNIQUE"),
    AUTOINCREMENT("AUTOINCREMENT"),
    PRIMARY_KEY("PRIMARY KEY");

    private String trait;

    SqlFieldTraits(String trait) {
        this.trait = trait;
    }
}
