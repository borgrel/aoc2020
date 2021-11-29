package aoctools;

public enum Days {
    DAY01,
    DAY02,
    DAY03,
    DAY04,
    DAY05,
    DAY06,
    DAY07,
    DAY08,
    DAY09,
    DAY10,
    DAY11,
    DAY12,
    DAY13,
    DAY14,
    DAY15,
    DAY16,
    DAY17,
    DAY18,
    DAY19,
    DAY20,
    DAY21,
    DAY22,
    DAY23,
    DAY24,
    DAY25,
    OUTPUT;

    private static final String PACKAGE_NAME = "aoc2020.attempt1";
    public String getFileName() {
        return name().toLowerCase();
    }
    public String getClassName() {
        return PACKAGE_NAME + "." +
                name().toLowerCase().replaceFirst("d","D");
    }

    public static Days dayFromInt(int value) {
        return Days.values()[value-1];
    }
}
