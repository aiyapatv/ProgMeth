package logic.potion;

public abstract class BasePotion {
    private String name;

    public BasePotion(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name.isBlank()) {
            this.name = "Unnamed Item";
        }
    }

    public abstract void usePotion();
}
