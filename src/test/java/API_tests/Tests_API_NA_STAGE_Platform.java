package API_tests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Tests_API_NA_STAGE_Platform {
    String bearer;

    @Test(priority = -1)
    public void test_login() throws UnirestException {
        HttpResponse<JsonNode> postResponse = Unirest.post("https://auth.rbdigitalstage.com/v2/authenticate")
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

    @Test(priority = 0)
    public void test_registration() throws UnirestException {
        SoftAssert softAssert = new SoftAssert();
        //magazine checkout
        HttpResponse <JsonNode> response = Unirest.post("https://api.rbdigitalstage.com/v1/register/")
                .header("authorization", "bearer " + "2c60a549-c04e-4480-98d4-a1d0657ece54" + "")
                //.header("authorization", "basic AAAC96E1-E6C8-447E-B61F-BB0CE4A24786")
                .header("content-type", "application/json")
                .body("{\"FirstName\": \"mar20d\", \"LastName\" : \"mar20c\", \"Email\": \"mar20deee@gmail.com\", \"PostalCode\": 123456, \"location\": \"prod_test_N\", \"commOption\": 1, \"AccessCode\" : \"pointbreak\", \"LibraryCard\": \"\", \"UserName\": \"mar20deee\", \"Password\": \"mar20deee\", \"termsPrivacyId\": 3}")
                //.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response.getBody());
        //Assert.assertNotNull(postResponse.getBody());
        //Assert.assertEquals (postResponse.getStatus(), 202);
        softAssert.assertNotNull(response.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response.getStatus(), 200, "Checkout ERROR - status is not 200");
    }
}
