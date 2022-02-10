import java.util.ArrayList;

/**
 * Diese Klasse stellt den Upgrade-Shop dar.
 * In dieser Klasse werden die Upgrade-Karten, die den Spielern zum Kauf zur Verfügung stehen, verwaltet.
 *
 * @author Chen Zhaohang
 * @author Yilu Ye
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class UpgradeShop {

    private UpgradeCards[] upgradeCards;
    private boolean somebodyBoughtOne;

    /**
     * Dies ist der Konstruktor.
     * Hier wird ein neuer Upgrade-Shop erstellt.
     * @param playerCount Die Anzahl der Spieler, die aktiv am Spiel teilnehmen.
     */
    public UpgradeShop(int playerCount) {
        upgradeCards = new UpgradeCards[playerCount];
        somebodyBoughtOne = false;
    }

    /**
     * Wenn der Shop ausgetauscht werden muss, wird dies von dieser Methode erledigt.
     * @param listWithCards Eine Liste der zur Verfügung stehenden Upgrade-Karten
     * @return Die Liste mit Upgrade-Karten, nachdem Karten von ihr in den Shop übertragen wurden
     */
    public ArrayList<UpgradeCards> exchangeShop(ArrayList<UpgradeCards> listWithCards){
        for (int i = 0; i < upgradeCards.length; i++){
            upgradeCards[i] = listWithCards.remove(0);
        }
        return listWithCards;
    }

    /**
     * Nach dem Kauf einer Karte, wird diese von dieser Methode aus dem Shop entfernt.
     * @param card Die Karte, die entfernt werden soll
     * @return Ein Boolean der angibt, ob die Karte erfolgreich entfernt wurde
     */
    public boolean remove(UpgradeCards card){
        for (int i = 0; i < upgradeCards.length; i++){
            if (upgradeCards[i] != null) {
                if (upgradeCards[i].getName().equals(card.getName())) {
                    upgradeCards[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Dies ist ein Getter
     * @return Boolean der angibt, ob ein Spieler in der letzten Runde eine Karte gekauft hat
     */
    public boolean isSomebodyBoughtOne() {
        return somebodyBoughtOne;
    }

    /**
     * Dies ist ein Getter
     * @return Die Upgrade-Karten, die aktuell im Shop zur Verfüfung stehen
     */
    public UpgradeCards[] getUpgradeCards() {
        return upgradeCards;
    }

    /**
     * Dies ist ein Setter
     * @param upgradeCards Das neue Array, dass die zur Verfügung stehenden Upgrade-Karten beinhaltet
     */
    public void setUpgradeCards(UpgradeCards[] upgradeCards) {
        this.upgradeCards = upgradeCards;
    }

    /**
     * Dies ist ein Setter
     * @param somebodyBoughtOne Boolean der angibt, ob jemand eine Karte gekauft hat
     */
    public void setSomebodyBoughtOne(boolean somebodyBoughtOne) {
        this.somebodyBoughtOne = somebodyBoughtOne;
    }
}
