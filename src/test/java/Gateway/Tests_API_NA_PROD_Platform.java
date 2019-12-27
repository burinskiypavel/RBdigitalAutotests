package Gateway;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests_API_NA_PROD_Platform {
    String bearer;

    @Test(priority=-1)
    public void test_Login() throws UnirestException {
        HttpResponse<JsonNode> postResponse = Unirest.post("https://auth.rbdigital.com/v2/authenticate")
                .header("authorization", "basic AAAC96E1-E6C8-447E-B61F-BB0CE4A24786")
                .header("content-type", "application/json")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
                //.asString();
                //.getBody();
        System.out.println(postResponse.getBody());
        bearer = postResponse.getBody().getObject().getString("bearer");
        System.out.println("bearer " + postResponse.getBody().getObject().getString("bearer"));
        Assert.assertNotNull(postResponse.getBody());
        Assert.assertEquals("Success", postResponse.getBody().getObject().getString("authStatus"));
    }

    @Test
    public void test_magazineCheckout() throws UnirestException {
        HttpResponse<JsonNode> postResponse = Unirest.post("https://api.rbdigital.com/v2/patron-magazines/447063")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
        Assert.assertEquals (postResponse.getStatus(), 200);
        Assert.assertNotNull(postResponse.getBody());
        //Assert.assertEquals("zenithUserId", postResponse.getBody().getObject().getString("2108926376017920873"));
    }

    @Test
    public void test_comicCheckout() throws UnirestException {
        HttpResponse<JsonNode> postResponse = Unirest.post("https://api.rbdigital.com/v1/patron-comics/424458")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
        Assert.assertNotNull(postResponse.getBody());
        Assert.assertEquals (postResponse.getStatus(), 200);
        //Assert.assertEquals("zenithUserId", postResponse.getBody().getObject().getString("2108926376017920873"));
    }

    @Test
    public void test_audiobookCheckout_Renew_ReturnCheckout() throws UnirestException {
        //checkout
        HttpResponse<JsonNode> postResponse = Unirest.post("https://api.rbdigital.com/v1/patron-books/checkouts/9780525633709?days=14")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
        Assert.assertNotNull(postResponse.getBody());
        Assert.assertEquals (postResponse.getStatus(), 200);

        //renew
        HttpResponse<JsonNode> postResponse2 = Unirest.put("https://api.rbdigital.com/v1/patron-books/checkouts/9780525633709?days=14")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse2.getBody());
        Assert.assertNotNull(postResponse2.getBody());
        Assert.assertEquals (postResponse2.getStatus(), 200);

        //delete
        HttpResponse<JsonNode> deleteResponse3 = Unirest.delete("https://api.rbdigital.com/v1/patron-books/checkouts/9780525633709")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
        Assert.assertNotNull(deleteResponse3.getBody());
        Assert.assertEquals (deleteResponse3.getStatus(), 200);
    }

    @Test
    public void test_ebookCheckout_Renew_ReturnCheckout() throws UnirestException {
        //checkout
        HttpResponse<JsonNode> postResponse = Unirest.post("https://api.rbdigital.com/v1/patron-books/checkouts/9781414373737?days=14")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
        Assert.assertNotNull(postResponse.getBody());
        Assert.assertEquals (postResponse.getStatus(), 200);

        //renew
        HttpResponse<JsonNode> postResponse2 = Unirest.put("https://api.rbdigital.com/v1/patron-books/checkouts/9781414373737")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse2.getBody());
        Assert.assertNotNull(postResponse2.getBody());
        Assert.assertEquals (postResponse2.getStatus(), 200);

        //delete
        HttpResponse<JsonNode> deleteResponse3 = Unirest.delete("https://api.rbdigital.com/v1/patron-books/checkouts/9781414373737")
                .header("authorization", "bearer "+bearer+"")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
        Assert.assertNotNull(deleteResponse3.getBody());
        Assert.assertEquals (deleteResponse3.getStatus(), 200);
    }

    @Test
    public void test_descriptionFromDitailPage() throws UnirestException {
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
