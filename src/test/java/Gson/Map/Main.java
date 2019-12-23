package Gson.Map;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String json = "{  \n" +
                "\t\"2\":{  \n" +
                "\t\t\"sessions\":[  \n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"13:00\",\n" +
                "\t\t\t\t\"price\":\"410\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"06:40\",\n" +
                "\t\t\t\t\"price\":\"340\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"16:50\",\n" +
                "\t\t\t\t\"price\":\"370\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\":\"Кинокис-L\",\n" +
                "\t\t\"locate\":\"Москва, Садовая-Спасская ул., 21, 56\",\n" +
                "\t\t\"metro\":\"Красные ворота\"\n" +
                "\t},\n" +
                "\t\"7\":{  \n" +
                "\t\t\"sessions\":[  \n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"06:35\",\n" +
                "\t\t\t\t\"price\":\"190\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"00:05\",\n" +
                "\t\t\t\t\"price\":\"410\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\":\"Кинокис-V\",\n" +
                "\t\t\"locate\":\"Павелецкая пл., 2, строение 1\",\n" +
                "\t\t\"metro\":\"Павелецкая\"\n" +
                "\t},\n" +
                "\t\"8\":{  \n" +
                "\t\t\"sessions\":[  \n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"15:10\",\n" +
                "\t\t\t\t\"price\":\"330\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\":\"Кинокис-J\",\n" +
                "\t\t\"locate\":\"ул. Пречистенка, 40/2\",\n" +
                "\t\t\"metro\":\"Кропоткинская\"\n" +
                "\t},\n" +
                "\t\"9\":{  \n" +
                "\t\t\"sessions\":[  \n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"13:00\",\n" +
                "\t\t\t\t\"price\":\"600\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"08:30\",\n" +
                "\t\t\t\t\"price\":\"300\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"04:00\",\n" +
                "\t\t\t\t\"price\":\"510\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{  \n" +
                "\t\t\t\t\"time\":\"13:15\",\n" +
                "\t\t\t\t\"price\":\"340\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\":\"Кинокис-U\",\n" +
                "\t\t\"locate\":\"Шарикоподшипниковская ул., 24\",\n" +
                "\t\t\"metro\":\"Дубровка\"\n" +
                "\t}\n" +
                "}";

        Gson g = new Gson();
        Type type = new TypeToken<Map<String, Seanse>>(){}.getType();
        Map<String, Seanse> myMap = g.fromJson(json, type);

        for( Map.Entry<String, Seanse> entry : myMap.entrySet()){
            List <Sessions> sessions = new ArrayList <>();
            sessions.add(entry.getValue().sessions.get(0));
           System.out.println(entry.getValue() + " - " + entry.getValue());

            System.out.println(sessions.get(0));

        //for (Map.Entry<String, Seanse> entry : myMap.entrySet()){
         //   System.out.println(entry.getKey());
        //}
        }
    }

}
