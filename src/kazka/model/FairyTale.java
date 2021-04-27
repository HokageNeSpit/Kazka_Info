package kazka.model;

public class FairyTale {
    int rate;
    String name;
    String description;

    public FairyTale(int rate, String name, String description) {
        this.rate = rate;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

}
