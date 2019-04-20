package model;

import javax.xml.bind.annotation.XmlElement;

public class Player {
    private String name;
    private String password;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private Player() {
        //needed for unmarshall
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj instanceof Player) {
            return this.getName().equals(((Player) obj).getName());
        }
        return false;
    }
}
