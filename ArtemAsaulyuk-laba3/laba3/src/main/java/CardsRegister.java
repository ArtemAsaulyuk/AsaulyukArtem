import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CardsRegister {
    private List<Card> cards = new ArrayList<>();
    private static String pathToLog = "src/main/logs/turnstileHistory.txt";

    public CardsRegister() {
        try {
            Files.deleteIfExists(Paths.get(pathToLog));
            Files.createFile(Paths.get(pathToLog));
        } catch (IOException ex) {
        }
    }

    public Card getCard(int id) {
        return cards.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void log(Card card, boolean isPassed) {
        File logFile = new File(pathToLog);
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            writer.println(card + ", passed=" + isPassed);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getLogs() {
        try {
            return Files.readAllLines(Paths.get(pathToLog));
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<String> getLogs(Card.Type type) {
        return getLogs().stream()
                .filter(str -> {
                    int indexOfType = str.indexOf("type=") + 5;
                    String currentType = str.substring(indexOfType, str.indexOf(",", indexOfType));

                    return currentType.equals(type.toString());
                })
                .collect(Collectors.toList());
    }

    public List<String> getLogs(Card card) {
        return getLogs().stream()
                .filter(str -> {
                    int indexOfType = str.indexOf("id=") + 3;
                    int id = -1;
                    try {
                        id = Integer.parseInt(str.substring(indexOfType, str.indexOf(",", indexOfType)));
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }

                    return id == card.getId();
                })
                .collect(Collectors.toList());
    }

    public Card createCard(int id, Card.Model model, Card.Type type) {
        switch (model) {
            case TERM:
                TermCard termCard = new TermCard(id, type);
                cards.add(termCard);
                return termCard;
            case QUANTITY:
                QuantityCard quantityCard = new QuantityCard(id, type);
                cards.add(quantityCard);
                return quantityCard;
            case CUMULATIVE:
                CumulativeCard cumulativeCard = new CumulativeCard(id, type);
                cards.add(cumulativeCard);
                return cumulativeCard;
        }

        return null;
    }

    public Card createCard(int id, Card.Model model) {
        return createCard(id, model, Card.Type.BASE);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void lockCard(int id) {
        Card card = getCard(id);
        if (card != null)
            card.setAvailable(false);
    }

    public void unlockCard(int id) {
        Card card = getCard(id);
        if (card != null)
            card.setAvailable(true);
    }

    public void lockCard(Card card) {
        if (card != null)
            card.setAvailable(false);
    }

    public void unlockCard(Card card) {
        if (card != null)
            card.setAvailable(true);
    }
}
