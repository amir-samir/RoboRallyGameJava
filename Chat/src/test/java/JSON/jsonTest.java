package JSON;

import Messages.*;
import org.hamcrest.Condition;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;


public class jsonTest {

    @Test
    public void test(){
        assertEquals(true, true);
    }

    @Test
    public void testZ(){

        Message message = new HelloClient("Version 0.2");
        Object[] inhalt = message.getMessageBody().getContent();

        Object string = (Object) inhalt[0];

        assertEquals(string, "Version 0.2");

    }


    @Test
    public void GsonTestWelcomeMessage(){
        String string = "{ \"messageType\": \"Welcome\", \"messageBody\": { \"clientID\": 42}}";
        Welcome m = new Welcome(42);

        Message ergebnis = Adopter.getMessage(string);


        assertEquals(ergebnis.getMessageBody().getContent(), m.getMessageBody().getContent());
        assertEquals(ergebnis.getMessageType(), m.getMessageType());
    }
    @Test
    public void GsonTestHelloServer(){
        String string = "{ \"messageType\": \"HelloServer\", \"messageBody\": { \"group\": \"TolleTrolle\", \"isAI\": false,\"protocol\":\"Version 0.1\" }}";
        HelloServer m = new HelloServer("TolleTrolle",false,"Version 0.1");

        m.getMessageBody().setKeys(keys);

        Object[] erwartet = new Object[3];
        erwartet = m.getMessageBody().getContent();
        String erwartet1 = (String) erwartet[0];
        Boolean erwartet2 = (Boolean) erwartet[1];
        String erwartet3 = (String) erwartet[2];

        Object[] feld = new Object[3];
        feld = ergebnis.getMessageBody().getContent();
        String ergebnis1 = (String) feld[0];
        Boolean ergebnis2 = (Boolean) feld[1];
        String ergebnis3 = (String) feld[2];

        assertEquals(erwartet1, ergebnis1);
        System.out.println(erwartet1);
        assertEquals(erwartet2, ergebnis2);
        assertEquals(erwartet3, ergebnis3);
        System.out.println(erwartet3);

        String[] keys = {"group", "isAI", "protocol"};
        m.getMessageBody().setKeys(keys);
        System.out.println((String)m.getMessageBody().getContent()[0]);
        System.out.println(Adopter.javabeanToJson(m));

        HelloServer output = new HelloServer("II", false, "Version 0.2");
        String[] key = {"group", "isAI", "protocol"};
        output.getMessageBody().setKeys(key);
        String S = Adopter.javabeanToJson(output);
        System.out.println(S);
    }

    @Test
    public void GsonTestHalloClient(){
        String string = "{ \"messageType\": \"HelloClient\", \"messageBody\": { \"protocol\":\"Version 0.1\" }}";
        HelloClient m = new HelloClient("Version 0.1");

        Message ergebnis = Adopter.getMessage(string);


        assertEquals(ergebnis.getMessageBody().getContent(), m.getMessageBody().getContent());
        assertEquals(ergebnis.getMessageType(), m.getMessageType());
    }

    @Test
    public void GsonTestAlive(){
        String string = "{ \"messageType\": \"Alive\", \"messageBody\": { }}";
        Alive m = new Alive();

        String ergebnis = Adopter.javabeanToJson(m);


        //String ergebnis = Adopter.javabeanToJson(m);
        System.out.println(string);
        System.out.println(ergebnis);


 //       assertEquals(ergebnis.getMessageBody().getContent(), m.getMessageBody().getContent());
   //     assertEquals(ergebnis.getMessageType(), m.getMessageType());
    }

    @Test
    public void GsonTestPlayerValues(){
        String string = "{ \"messageType\": \"PlayerValues\", \"messageBody\": { \"availableMaps\": [\"DizzyHighway\"],  \"availableMaps\": [\"DizzyHighway\"]}}";
        PlayerValues m = new PlayerValues("Nr. 5",5);
        Message ergebnis = Adopter.getMessage(string);



        //assertEquals(ergebnis.getMessageBody().getContent(), m.getMessageBody().getContent());
        //assertEquals(ergebnis.getMessageType(), m.getMessageType());
    }
    @Test
    public void GsonTestSelectMap(){
        String string = "{ \"messageType\": \"PlayerValues\", \"messageBody\": { \"availableMaps\": \"DizzyHighway\",  \"avalableMaps\": \"DizzHighway\"}}";
        String[] map = new String[1];
        map[0] = "[DizzyHighway]";
        SelectMap m = new SelectMap(map);
        Message ergebnis = Adopter.getMessage(string);


        Object[] keys = new Object[1];
        keys[0] = "availableMaps";
        m.getMessageBody().setKeys(keys);
        String ergebnis2 = Adopter.javabeanToJson(m);
        System.out.println(ergebnis2);
    }

    @Test
    public void GsonToJson(){
        String[] map = new String[1];
        map[0] = "DizzyHighway";
        PlayerValues m = new PlayerValues("Nr. 5",5);
        Object[] keys = new Object[2];
        keys[0] = "clientID";
        keys[1] = "figure";
        m.getMessageBody().setKeys(keys);
        String ergebnis = Adopter.javabeanToJson(m);
        System.out.println(ergebnis);
    }

    @Test
    public void GsonToJsonSelectMap2(){
        String[] map = new String[1];
        map[0] = "DizzyHighway";
        String[] keys = new String[1];
        keys[0] = "availableMaps";

        SelectMap toParse = new SelectMap(map);
        toParse.getMessageBody().setKeys(keys);

        String expected = "{\"messageType\":\"SelectMap\",\"messageBody\":{\"availableMaps\":[\"DizzyHighway\"]}}";
        System.out.println("Erwartet: " + expected);
        String ergebnis = Adopter.javabeanToJson(toParse);
        System.out.println("Ergebnis: " + ergebnis);

        assertEquals(expected, ergebnis);
    }

    @Test
    public void helloClient(){
        HelloClient message = new HelloClient("Version 0.2");
        String[] key = new String[1];
        key[0] = "protocol";

        message.getMessageBody().setKeys(key);
        String toSend = Adopter.javabeanToJson(message);

        System.out.println(toSend);
    }

    @Test
    public void error1(){
        Error1 error = new Error1("Testfehler");
        String[] key = {"error"};
        error.getMessageBody().setKeys(key);
        String ergebnis = Adopter.javabeanToJson(error);
        System.out.println(ergebnis);
    }

    @Test
    public void sendChat(){
        SendChat message = new SendChat("Test", 2000);
        String[] key = {"message", "to"};
        message.getMessageBody().setKeys(key);
        String toSend = Adopter.javabeanToJson(message);
        System.out.println(toSend);
    }

    @Test
    public void alive(){
        Alive alive = new Alive();
        String ergebnis = Adopter.javabeanToJson(alive);
        System.out.println(ergebnis);
    }

    @Test
    public void PlayerValues(){
        PlayerValues playerValues = new PlayerValues("Robert", 5);
        String[] keys = {"name", "figure"};
        playerValues.getMessageBody().setKeys(keys);
        String toSend = Adopter.javabeanToJson(playerValues);
        System.out.println(toSend);
    }
}
