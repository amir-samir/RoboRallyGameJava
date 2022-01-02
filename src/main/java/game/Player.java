public class Player {

    int ID;
    int figur;
    String name;
    boolean ready;


    Player(int ID, String name, int figur){
        this.ID = ID;
        this.ready = false;
        this.name = name;
        this.figur = figur;
    }
}
