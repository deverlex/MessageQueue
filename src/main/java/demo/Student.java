package demo;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private Integer old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }
}
