package Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
//import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.*;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GoogleMain4 {
    String token = null;

    @Test
    public void Test_GoogleAPIRequest1() throws UnirestException {

        String getApi = "https://trends.google.com/trends/api/explore?hl=en-US&tz=-120&req=%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22food%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D&tz=-120";
        String body = Unirest.get(getApi)

        //HttpResponse<String> jsonResponse = Unirest.get(getApi)

                .header("hl", "ru")
                .header("tz", "-120")
                .header("req", "%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22food%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D")

                .header("authority", "trends.google.com")
                .header("path", "/trends/api/explore?hl=ru&tz=-120&req=%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22food%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D&tz=-120")
                .header("accept", "application/json, text/plain, */*")
                .header("accept-encoding", "gzip, deflate, br")
                .header("cookie", "__utmc=10102256; _utma=10102256.398216924.1572949697.1575372718.1575448579.16; _utmz=10102256.1575448579.16.4.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); HSID=AbcEJ3E7KS0UH40gF; SSID=ADO3ynr-V6VL9EB6p; APISID=R-SniXXBhbh-ZwI6/AxjGvs-oZzBCS9HM0; SAPISID=gmZJXB4skpnDKSo6/AnDHKnLTjpFuRSeZN; SEARCH_SAMESITE=CgQIsI0B; S=billing-ui-v3=d68qgOBhigRN6WpV5jl3V8u0UucPOon1:billing-ui-v3-efe=d68qgOBhigRN6WpV5jl3V8u0UucPOon1; SID=rAdtgVwnvMyZedtqgFeh2fAg_dvo4iOW-6o0qLGGQCN2PTySwKrURuAuuLY75B1vWKZT2w.; ANID=AHWqTUnRpPZDiVYxu6nUCi54lVPZ4xzRQ5gI8U_69_VjuO6veu774Izupd65t7hg; NID=192=SUANyzFji8Ogxx5WQKv9WIKG5XX7GOAiPs2bNTP_uT2Ilc3f3SXJ2cojicho_dP8S6vi2xkDZbzOb3pyD-Yj5BkfK3t5BJO4oA3J5Fpg4MeLpC0RamVP7yXUpTqx7QI7QK7mWw6l4-F379Ii1I3ukoBdbFxZXlqB9F6zI8FkJ-gLCT9F4jgEP_N9EgFvckmHp_cHsL-lRBxvwyQZI5Z05ljm7gLKtJKgbKK12hHRst0; 1P_JAR=2019-12-4-9; SIDCC=AN0-TYvA3fWHptnsf17sAKZ463Ojs2Ph1P5GVNzqppS5uU6AdPT-hZjHHXprhGfZk-auDcrp7kgB")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                .header("x-client-data", "CI22yQEIo7bJAQjBtskBCKmdygEIqKPKAQjiqMoBCJetygEIza3KAQjLrsoBCMqvygEI97TKAQ==")
                //.body(template.render(model))
                .asString()
                //.asJson()
                .getBody();
        System.out.println(body);         // gives the full json response
        String jsonString = body;
        Gson g = new Gson();

        Wi request = g.fromJson(jsonString, Wi.class);

        for (Wi wi : request.widgets) {

            System.out.println("token:" + wi.token);
            //System.out.println("id:" + wi.id);
            if (token == null) {
                for (int i = 0; i < 1; i++) {
                    token = wi.token;
                }
            }
        }
        Assert.assertNotNull(request);
        //Assert.assertEquals("en-US", body.getObject());
    }
        @Test
        public void Test_GoogleAPIRequest2() throws UnirestException, IOException {

            String postApi2 = "https://trends.google.com/trends/api/widgetdata/multiline?tz=-120&req=%7B%22time%22:%222014-12-26+2019-12-26%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22BROAD%22,%22value%22:%22food%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D&token="+token+"&tz=-120";
            String body2 = Unirest.get(postApi2)
                    // .routeParam("hl", "ru")
                    //.header("tz", "-120")
                    //.header("req", "%7B%22time%22:%222014-12-04+2019-12-04%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22BROAD%22,%22value%22:%22food%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D")
                    //.header("token", "APP6_UEAAAAAXekxCQiy4BMDtREcWW08HCOR4bBU_MLo")

                    .header("authority", "trends.google.com")
                    .header("path", "/trends/api/widgetdata/multiline?hl=en-US&tz=-120&req=%7B%22time%22:%222014-12-04+2019-12-04%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22BROAD%22,%22value%22:%22food%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D&token=APP6_UEAAAAAXejBnEj_wMQBRwqtbkpwA_kWwA1E6M6F&tz=-120")
                    .header("accept", "application/json, text/plain, */*")
                    .header("accept-encoding", "gzip, deflate, br")
                    //.header("cookie", "__utmc=10102256; _utma=10102256.398216924.1572949697.1575372718.1575448579.16; _utmz=10102256.1575448579.16.4.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); _utmt=1; _utmb=10102256.7.9.1575448586551; HSID=AbcEJ3E7KS0UH40gF; SSID=ADO3ynr-V6VL9EB6p; APISID=R-SniXXBhbh-ZwI6/AxjGvs-oZzBCS9HM0; SAPISID=gmZJXB4skpnDKSo6/AnDHKnLTjpFuRSeZN; SEARCH_SAMESITE=CgQIsI0B; S=billing-ui-v3=d68qgOBhigRN6WpV5jl3V8u0UucPOon1:billing-ui-v3-efe=d68qgOBhigRN6WpV5jl3V8u0UucPOon1; SID=rAdtgVwnvMyZedtqgFeh2fAg_dvo4iOW-6o0qLGGQCN2PTySwKrURuAuuLY75B1vWKZT2w.; ANID=AHWqTUnRpPZDiVYxu6nUCi54lVPZ4xzRQ5gI8U_69_VjuO6veu774Izupd65t7hg; NID=192=SUANyzFji8Ogxx5WQKv9WIKG5XX7GOAiPs2bNTP_uT2Ilc3f3SXJ2cojicho_dP8S6vi2xkDZbzOb3pyD-Yj5BkfK3t5BJO4oA3J5Fpg4MeLpC0RamVP7yXUpTqx7QI7QK7mWw6l4-F379Ii1I3ukoBdbFxZXlqB9F6zI8FkJ-gLCT9F4jgEP_N9EgFvckmHp_cHsL-lRBxvwyQZI5Z05ljm7gLKtJKgbKK12hHRst0; 1P_JAR=2019-12-4-8; SIDCC=AN0-TYvU12D7ngM85RKEETkd13Mno2V_rS0nPi-L-WvrnLymrLIiUUdnyIKAY3pn10vt1uJ_qQyq")
                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                    .header("x-client-data", "CI22yQEIo7bJAQjBtskBCKmdygEIqKPKAQjiqMoBCJetygEIza3KAQjLrsoBCMqvygEI97TKAQ==")
                    //.body(template.render(model))
                    .asString()
                    //.asJson()
                    .getBody();

            System.out.println(body2);         // gives the full json response

            String jsonString2 = body2;
            System.out.println(jsonString2.length());
            int xx = jsonString2.length();
            jsonString2 = jsonString2.substring(17, xx - 1);
            System.out.println(jsonString2);

            Gson g2 = new Gson();
            Wi2 request2 = g2.fromJson(jsonString2, Wi2.class);

            List<String> valueAndTime = new ArrayList <>();

            for (Wi2 wi2 : request2.timelineData) {

                for (int i = 0; i < wi2.formattedValue.length; i++) {

                    String formValue = wi2.formattedValue[i];

                    System.out.println(wi2.formattedTime);
                    System.out.println("formattedValue :" + formValue);

                    valueAndTime.add(wi2.formattedTime);
                    valueAndTime.add("formattedValue :" + formValue);
                }

            }

            FileWriter writer = new FileWriter("C:\\demo\\ab.txt");
            for(String str: valueAndTime) {
                writer.write(str);
                writer.write("\n");

            }
            writer.close();

        }

    }


