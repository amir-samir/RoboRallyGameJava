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
        String string = "{ \"messageType\": \"HelloServer\", \"messageBody\": { \"group\": \"TolleTrolle\", \"isAI\": false, \"protocol\": \"Version 0.1\" }}";
        HelloServer m = new HelloServer("TolleTrolle",false,"Version 0.1");
        Object[] keys = new Object[3];
        keys[0] = "group";
        keys[1] = "isAI";
        keys[2] = "protocol";

        m.getMessageBody().setKeys(keys);

        String ergebnis = Adopter.javabeanToJson(m);
        System.out.println(ergebnis);
        System.out.println(string);


        //assertEquals(ergebnis.getMessageBody().getContent(), m.getMessageBody().getContent());
        //assertEquals(ergebnis.getMessageType(), m.getMessageType());
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
}
