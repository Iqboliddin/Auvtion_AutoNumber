package model.base;

import java.util.UUID;

public abstract class BaseModel {
    protected UUID id;
    protected String name;

    public BaseModel() {
    }

    public BaseModel(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
