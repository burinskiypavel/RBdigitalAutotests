package Gateway;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.annotations.Test;

public class Tests_REST_API {

    @Test
    public void testAPIGET_DescriptionFromDitailPage() throws UnirestException {

        String searchQueryApi = "https://api.rbdigital.com/v1/titles/9781470381806/summary";

        JsonNode body = Unirest.get(searchQueryApi)
                .header("authorization", "bearer 5de50e0fa5ac8019e87abc55")
                .asJson()
                .getBody();
        System.out.println(body);         // gives the
        // full json response
        //System.out.println(body.length);  // gives the no of items
    }
}
