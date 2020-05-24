import java.text.SimpleDateFormat;
import java.util.Date;

public class TermCard extends Card {
    private Date validity;

    public TermCard(int id) {
        super(id, Type.BASE);
        validity = new Date();
    }

    public TermCard(int id, Type type) {
        super(id, type);
        validity = new Date();
    }

    public TermCard(int id, Date validity) {
        super(id, Type.BASE);
        this.validity = validity;
    }

    public TermCard(int id, Type type, Date validity) {
        super(id, type);
        this.validity = validity;
    }

    @Override
    public boolean pass() {
        Date currentDate = new Date();
        return currentDate.before(validity) && isAvailable();
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        return "TermCard: " +
                super.toString() +
                ", validity=" + dateFormat.format(validity);
    }
}
