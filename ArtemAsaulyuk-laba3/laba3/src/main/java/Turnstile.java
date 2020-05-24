public class Turnstile {
    private CardsRegister register = new CardsRegister();

    public void passTurnstile(Card card) {
        if (register.getCards().contains(card)) {
            if (card.getType() == Card.Type.PRIVILEGE) {
                warn();
            }

            boolean isPassed = card.pass();
            register.log(card, isPassed);
        }
    }

    public void warn() {
        System.out.println("Контролер повинен перевірити документи");
    }
}
