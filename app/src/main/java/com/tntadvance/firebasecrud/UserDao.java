package com.tntadvance.firebasecrud;

/**
 * Created by thana on 6/27/2017 AD.
 */

public class UserDao {
    private String id;
    private String name;
    private String group;

    public UserDao() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
