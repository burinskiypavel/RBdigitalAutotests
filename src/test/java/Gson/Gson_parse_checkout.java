package Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;

public class Gson_parse_checkout {

    //private RB.Interest .;

    @Test
    public void Test_GsonParseCheckout() {
        String json = "{\n" +
                "  \"executedAt\": \"0001-01-01T00:00:00Z\",\n" +
                "  \"pageIndex\": 0,\n" +
                "  \"pageSize\": 101,\n" +
                "  \"pageCount\": 1,\n" +
                "  \"resultSetCount\": 1,\n" +
                "  \"isSortByDesc\": true,\n" +
                "  \"resultSet\": [\n" +
                "    {\n" +
                "      \"interest\": {\n" +
                "        \"name\": \"servicecheckout\",\n" +
                "        \"id\": 449204,\n" +
                "        \"patronId\": 3036063,\n" +
                "        \"libraryId\": 2819,\n" +
                "        \"startOn\": \"2019-12-27T12:34:24Z\",\n" +
                "        \"endOn\": \"2020-01-03T12:34:24Z\",\n" +
                "        \"status\": 1,\n" +
                "        \"notes\": \"\",\n" +
                "        \"createdAt\": \"2019-12-27T12:34:24Z\",\n" +
                "        \"updatedAt\": \"2019-12-27T12:34:24Z\",\n" +
                "        \"daysRemaining\": 7\n" +
                "      },\n" +
                "      \"item\": {\n" +
                "        \"name\": \"learn-it-live\",\n" +
                "        \"title\": \"Learn It Live\",\n" +
                "        \"productType\": \"7day\",\n" +
                "        \"logoPaths\": {\n" +
                "          \"large\": \"https://dzsuudu3ba6ci.cloudfront.net/platform-services/LearnItLive_logo_large.png(43 kB)\n" +
                "https://dzsuudu3ba6ci.cloudfront.net/platform-services/LearnItLive_logo_large.png\n" +
                "\",\n" +
                "          \"small\": \"https://dzsuudu3ba6ci.cloudfront.net/platform-services/LearnItLive_logo_small.png(16 kB)\n" +
                "https://dzsuudu3ba6ci.cloudfront.net/platform-services/LearnItLive_logo_small.png\n" +
                "\",\n" +
                "          \"menu\": \"https://dzsuudu3ba6ci.cloudfront.net/platform-services/LearnItLive_menu_icon.png(3 kB)\n" +
                "https://dzsuudu3ba6ci.cloudfront.net/platform-services/LearnItLive_menu_icon.png\n" +
                "\"\n" +
                "        },\n" +
                "        \"descriptions\": {\n" +
                "          \"service\": \"Learn It Live\",\n" +
                "          \"shortDescription\": \"Wellness and Personal Development\",\n" +
                "          \"longDescription\": \"Live and on-demand classes, quick tip videos, and virtual reality wellness\"\n" +
                "        },\n" +
                "        \"url\": \"\",\n" +
                "        \"itemType\": 3\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        Gson g = new Gson();
        //Gson g = new GsonBuilder().setPrettyPrinting().create();

        RB request = g.fromJson(json, RB.class);

        for (RB rb : request.resultSet) {

            //for(int i = 0; i < request.interest.name.length(); i++) {
                System.out.println("name: " + rb.interest.name);
                System.out.println("startOn: " + rb.interest.startOn);
                System.out.println("title: " + rb.item.title);
                System.out.println("service: " + rb.item.descriptions.service);
                System.out.println("shortDescription: " + rb.item.descriptions.shortDescription);
                System.out.println("longDescription: " + rb.item.descriptions.longDescription);
           // }
            //for(RB.Interest interest : request.interest){
          //      System.out.println(interest.name);

          //  }



            //for( re3 : wi2.formattedValue) {

            //System.out.println(" formattedValue: " + wi2.formattedValue);

            //for (int i = 0; i < rb.formattedValue.length; i++){
          //     String formValue = rb.formattedValue[i];
           //     System.out.println("formattedValue : " + formValue);

           // }

            //System.out.println(" time: " + wi2.time);
            //System.out.println(" value: " + wi2.value);
            //}

        }


    }
}
