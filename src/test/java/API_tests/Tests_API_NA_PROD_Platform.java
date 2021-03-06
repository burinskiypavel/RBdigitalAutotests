package API_tests;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Tests_API_NA_PROD_Platform {
    String bearer;

    @Test(priority = -1)
    public void test_login() throws UnirestException {
        HttpResponse <JsonNode> postResponse = Unirest.post("https://auth.rbdigital.com/v2/authenticate")
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
    public void test_magazineCheckout_return() throws UnirestException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse <JsonNode> response = Unirest.post("https://api.rbdigital.com/v2/patron-magazines/468091")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response.getBody());
        //Assert.assertEquals(response.getStatus(), 200);
        //Assert.assertNotNull(response.getBody());
        softAssert.assertNotNull(response.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response.getStatus(), 200, "ERROR - status is not 200");

        //magazine return
        HttpResponse <String> postResponse2 = Unirest.delete("https://api.rbdigital.com/v2/patron-magazines/468091")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.asJson();
                .asString();
        //.getBody();
        System.out.println(postResponse2.getBody());
        //Assert.assertEquals (postResponse2.getStatus(), 202);
        // Assert.assertNotNull(postResponse2.getBody());
        softAssert.assertNotNull(postResponse2.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(postResponse2.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertAll();
    }

    @Test
    public void test_comicCheckout_Return() throws UnirestException {
        SoftAssert softAssert = new SoftAssert();
        //checkout
        HttpResponse <JsonNode> response = Unirest.post("https://api.rbdigital.com/v1/patron-comics/456238")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response.getBody());
        //Assert.assertNotNull(response.getBody());
        //Assert.assertEquals(postResponse.getStatus(), 200);
        softAssert.assertNotNull(response.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response.getStatus(), 200, "ERROR - status is not 200");

        //return
        HttpResponse <String> deleteResponse2 = Unirest.delete("https://api.rbdigital.com/v1/patron-comics/456238")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                //.body("{\"PatronIdentifier\": \"qauser\", \"PatronSecret\" : \"password1\", \"LibraryId\": 1062, \"Source\": \"OneClick\", \"authState\": \"auth_internal\"}")
                //.asJson();
                .asString();
        //.getBody();
        System.out.println(deleteResponse2.getBody());
        //Assert.assertNotNull(deleteResponse2.getBody());
        softAssert.assertNotNull(deleteResponse2.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(deleteResponse2.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertAll();
        //Assert.assertEquals (postResponse2.getStatus(), 200);
    }

    @Test
    public void test_audiobookCheckout_Renew_ReturnCheckout() throws UnirestException {
        SoftAssert softAssert = new SoftAssert();
        //checkout
        HttpResponse <JsonNode> response = Unirest.post("https://api.rbdigital.com/v1/patron-books/checkouts/9780525633709?days=14")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response.getBody());
        //Assert.assertNotNull(postResponse.getBody());
        //Assert.assertEquals(postResponse.getStatus(), 200);
        softAssert.assertNotNull(response.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertEquals("success", response.getBody().getObject().getString("message"), "ERROR - message is not success");

        //renew
        HttpResponse <JsonNode> response2 = Unirest.put("https://api.rbdigital.com/v1/patron-books/checkouts/9780525633709?days=14")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response2.getBody());
        //Assert.assertNotNull(response2.getBody());
        //Assert.assertEquals(response2.getStatus(), 200);
        softAssert.assertNotNull(response2.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response2.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertEquals("SUCCESS", response2.getBody().getObject().getString("output"), "ERROR - output is not success");

        //delete
        HttpResponse <JsonNode> deleteResponse3 = Unirest.delete("https://api.rbdigital.com/v1/patron-books/checkouts/9780525633709")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(deleteResponse3.getBody());
        //Assert.assertNotNull(deleteResponse3.getBody());
        //Assert.assertEquals(deleteResponse3.getStatus(), 200);
        softAssert.assertNotNull(deleteResponse3.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(deleteResponse3.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertEquals("success", deleteResponse3.getBody().getObject().getString("message"), "ERROR - message is not success");
        softAssert.assertAll();
    }

    @Test
    public void test_ebookCheckout_Renew_ReturnCheckout() throws UnirestException {
        SoftAssert softAssert = new SoftAssert();
        //checkout
        HttpResponse <JsonNode> response = Unirest.post("https://api.rbdigital.com/v1/patron-books/checkouts/9780786034857?days=14")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response.getBody());
        //Assert.assertNotNull(postResponse.getBody());
        //Assert.assertEquals(postResponse.getStatus(), 200);
        softAssert.assertNotNull(response.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertEquals("success", response.getBody().getObject().getString("message"), "ERROR - message is not success");

        //renew
        HttpResponse <JsonNode> postResponse2 = Unirest.put("https://api.rbdigital.com/v1/patron-books/checkouts/9780786034857")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse2.getBody());
        //Assert.assertNotNull(postResponse2.getBody());
        //Assert.assertEquals(postResponse2.getStatus(), 200);
        softAssert.assertNotNull(postResponse2.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(postResponse2.getStatus(), 200, "ERROR - status is not 200");

        //delete
        HttpResponse <JsonNode> response3 = Unirest.delete("https://api.rbdigital.com/v1/patron-books/checkouts/9780786034857")
                .header("authorization", "bearer " + bearer + "")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response3.getBody());
        //Assert.assertNotNull(deleteResponse3.getBody());
        //Assert.assertEquals(deleteResponse3.getStatus(), 200);
        softAssert.assertNotNull(response3.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response3.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertEquals("success", response3.getBody().getObject().getString("message"), "ERROR - message is not success");
        softAssert.assertAll();
    }

    @Test
    public void test_descriptionFromDitailPage() throws UnirestException {
        String searchQueryApi = "https://api.rbdigital.com/v1/titles/9781470381806/summary";
        //JsonNode body = Unirest.get(searchQueryApi)
        HttpResponse <JsonNode> response = Unirest.get(searchQueryApi)
                .header("authorization", "bearer "+bearer+"")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response.getBody());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals("9781470381806", response.getBody().getObject().getString("isbn"));
    }

    @Test
    public void test_searchComicsByRatingPG() throws UnirestException {
        String response = Unirest.get("https://api.rbdigital.com/v1/search/comic?search-source=advanced-search&page-index=0&mediatype=ecomic&rating=pg")
                .header("authorization", "bearer " + bearer + "")
                .asString()
                .getBody();
        System.out.println(response);

        Gson g = new Gson();
        SearchRBRating request = g.fromJson(response, SearchRBRating.class);
        String actualRating = null;
        String actualMediaType = null;
        for (SearchRBRating rb : request.items) {
            actualRating = rb.item.rating;
            actualMediaType = rb.item.mediaType;
            System.out.println("rating: " + rb.item.rating);
            System.out.println("mediaType: " + rb.item.mediaType);
        }
        Assert.assertNotNull(response);
        Assert.assertEquals(actualRating, "PG");
        Assert.assertEquals(actualMediaType, "eComic");
    }

    @Test
    public void test_searchMagazineByGenre() throws UnirestException {
        String response = Unirest.get("https://api.rbdigital.com/v1/search/emagazine?search-source=advanced-search&page-index=0&mediatype=emagazine&genre=cycling")
                .header("authorization", "bearer " + bearer + "")
                .asString()
                .getBody();
        System.out.println(response);

        Gson g = new Gson();
        SearchRBGenre request = g.fromJson(response, SearchRBGenre.class);
        String actualGenre = null;
        String actualMediaType = null;
        for (SearchRBGenre rb : request.items) {
            actualGenre = rb.item.genre;
            actualMediaType = rb.item.mediaType;
            System.out.println("genre: " + rb.item.genre);
            System.out.println("mediaType: " + rb.item.mediaType);
        }
        Assert.assertNotNull(response);
        Assert.assertEquals(actualGenre, "Cycling");
        Assert.assertEquals(actualMediaType, "eMagazine");
    }

    @Test
    public void test_audiobookHold_ReturnHold() throws UnirestException {
        SoftAssert softAssert = new SoftAssert();
        //hold
        HttpResponse <JsonNode> postResponse = Unirest.post("https://api.rbdigital.com/v1/patron-books/holds/9781980035398")
                .header("authorization", "bearer " + bearer + "")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(postResponse.getBody());
        //Assert.assertNotNull(postResponse.getBody());
        //Assert.assertEquals(postResponse.getStatus(), 200);
        //Assert.assertEquals("success", postResponse.getBody().getObject().getString("message"));
        softAssert.assertNotNull(postResponse.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(postResponse.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertEquals("success", postResponse.getBody().getObject().getString("message"), "ERROR - message is not success");

        //remove hold
        HttpResponse <JsonNode> deleteResponse = Unirest.delete("https://api.rbdigital.com/v1/patron-books/holds/9781980035398")
                .header("authorization", "bearer " + bearer + "")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(deleteResponse.getBody());
        //Assert.assertNotNull(deleteResponse.getBody());
        //Assert.assertEquals(deleteResponse.getStatus(), 200);
        //Assert.assertEquals("success", deleteResponse.getBody().getObject().getString("message"));
        softAssert.assertNotNull(postResponse.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(postResponse.getStatus(), 200, "ERROR - status is not 200");
        softAssert.assertEquals("success", postResponse.getBody().getObject().getString("message"), "ERROR - message is not success");
        softAssert.assertAll();
    }

    @Test
    public void test_updateFirstNameLastNameInProfile_returnChengesBack() throws UnirestException {
        SoftAssert softAssert = new SoftAssert();
        HttpResponse <JsonNode> response = Unirest.put("https://api.rbdigital.com/v1/account")
                .header("authorization", "bearer " + bearer + "")
                .header("Content-Type", "application/json")
                .body("{ \"userName\": \"qauser\" , \"firstName\": \"qauser1\", \"lastName\": \"qauser1\", \"postalCode\": 12345, \"email\": \"smalick@recordedbooks.com\", \"termsPrivacyId\": 3, \"verifyAge\": false, \"commOptio\": 1 }")
                .asJson();
        //.asString();
        //.getBody();
        System.out.println(response.getBody());
        //Assert.assertEquals(response.getStatus(), 200);
        //Assert.assertNotNull(response.getBody());
        softAssert.assertNotNull(response.getBody(), "ERROR - response is empty");
        softAssert.assertEquals(response.getStatus(), 200, "ERROR - status is not 200");

        softAssert.assertEquals("qauser1", response.getBody().getObject().getString("firstName"), "ERROR - firstName is not correct");
        softAssert.assertEquals("qauser1", response.getBody().getObject().getString("lastName"), "ERROR - lastName is not correct");
    }
}
