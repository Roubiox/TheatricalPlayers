import java.util.UUID;

public class Acheteur {
    private String name;
    private final UUID id;
    private float credits;

    public Acheteur(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.credits = 0;
    }

    public String getName() {
        return name;
    }

    public float getCredits() {
        return credits;
    }

    public void updateCredits(Performance performance) {
        this.credits += performance.getCredit();
    }

    public void updateCredits(Integer credits) {
        this.credits += credits;
    }

    public UUID getId() {
        return id;
    }

    public Boolean reduction() {
        if (getCredits() >= 150) {
            updateCredits(-150);
            return true;
        }
        return false;
    }
}
