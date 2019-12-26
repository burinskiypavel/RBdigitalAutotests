package Gateway;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests_REST_API {

    @Test
    public void test_Login() throws UnirestException {

        //JSONObject exampleJson = new JSONObject().put("LibraryId", 1062).put("PatronIdentifier", "qauser").put("PatronSecret", "password1").put("Source", "OneClick").put("authState", "auth_internal");

        HttpResponse<JsonNode> postResponse = Unirest.post("https://auth.rbdigital.com/v2/authenticat")

                .body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
                //.asString();
                //.getBody();
        System.out.println(postResponse.getBody());
    }

    @Test
    public void test_Logout() throws UnirestException {

        //JSONObject exampleJson = new JSONObject().put("LibraryId", 1062).put("PatronIdentifier", "qauser").put("PatronSecret", "password1").put("Source", "OneClick").put("authState", "auth_internal");

        HttpResponse<JsonNode> postResponse = Unirest.post("https://api.rbdigital.com/site/logout/")

                .body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
    }

    @Test
    public void test_DescriptionFromDitailPage() throws UnirestException {
        String searchQueryApi = "https://api.rbdigital.com/v1/titles/9781470381806/summary";
        //JsonNode body = Unirest.get(searchQueryApi)
        HttpResponse<JsonNode> response = Unirest.get(searchQueryApi)
                .header("authorization", "bearer 5de50e0fa5ac8019e87abc55")
                .asJson();
                //.asString();
                //.getBody();
        System.out.println(response.getBody());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals("9781470381806", response.getBody().getObject().getString("isbn"));
    }
}
