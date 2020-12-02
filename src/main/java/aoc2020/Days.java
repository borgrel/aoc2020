package aoc2020;

import java.lang.reflect.InvocationTargetException;

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

    public String getFileName() {
        return name().toLowerCase();
    }
    public Day getDay() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String packageName = "aoc2020.attempt1.";
        String classname = name().toLowerCase().replaceFirst("d","D");
        System.out.println(classname);
        return (Day) this.getClass().getClassLoader().loadClass(packageName + classname).getConstructors()[0].newInstance();
    }
}
