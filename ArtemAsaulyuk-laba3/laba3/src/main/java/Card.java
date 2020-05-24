import java.util.Objects;

public abstract class Card {
    private int id;
    private Type type;
    private boolean available = true;

    Card(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    public abstract boolean pass();

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;

        return id == card.id &&
                available == card.available &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, available);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", type=" + type +
                ", available=" + available;
    }

    public enum Type {
        BASE,
        PRIVILEGE
    }

    public enum Model {
        QUANTITY,
        TERM,
        CUMULATIVE
    }
}
