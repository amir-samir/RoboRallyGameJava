public class Player {

    int ID;
    int figur;
    String name = "";
    boolean ready = false;


    Player(int ID, String name, int figur){
        this.ID = ID;
        this.ready = false;
        this.name = "";
        this.name = name;
        this.figur = figur;
    }
}
