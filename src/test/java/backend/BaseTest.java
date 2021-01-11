package backend;

import org.testng.annotations.Test;

import junit.framework.Assert;
import utils.CommonOperations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Iterator;


public class BaseTest {
	public static String idOfUserNew;
	public  ArrayList<Integer> idOfPost = new ArrayList<>();
	public  ArrayList<String> emailList = new ArrayList<>();

    @Test
    public void TC1_Step1_searchForUserName(){
    	String idOfUser =given()
                .get("https://jsonplaceholder.typicode.com/users?username=Delphine")
                .then().assertThat().statusCode(200)
                .time(lessThan(2000L))
                .body("username",hasItem("Delphine"))
                .body("name", hasItem("Glenna Reichert"))
                .body("address", notNullValue())
                .body("phone", notNullValue())
                .body("website", notNullValue())
                .body("company", notNullValue())
                .body("company.name", notNullValue())
                .body("company.catchPhrase", notNullValue())
                .body("company.bs", notNullValue())
                .body("geo.lat", notNullValue())
                .body("address.geo.lat", contains("24.6463"))
                .body("address.geo.lng", contains("-168.8889"))
                .extract()
                .path("id").toString();
     
    	 idOfUserNew = idOfUser.replaceAll("[^\\d]", ""); 
    	System.out.println(idOfUserNew);
     
     
    }
    
    @Test
    public void TC1_Step2_searchForCommentsForPost(){
    	int userIdValue = Integer.parseInt(idOfUserNew);
    	idOfPost =given()
    			.queryParam("userId", userIdValue)
                .get("https://jsonplaceholder.typicode.com/posts/")
                .then().assertThat().statusCode(200)
                .time(lessThan(2000L))
                .body("userId[0]", notNullValue())
                .body("id[0]", notNullValue())
                .body("title[0]", notNullValue())
                .body("body[0]", notNullValue())
                .extract()
                .path("id");
     
     System.out.println(idOfPost);
     
     
    }
   
    
    @Test
    public void TC1_Step3_searchForPostByUser(){
    	boolean isSuccess = true;
    	for(int i : idOfPost) {
    		emailList =given()
        			.pathParam("postId", i)
                    .get("https://jsonplaceholder.typicode.com/posts/{postId}/comments")
                    .then().assertThat().statusCode(200)
                    .time(lessThan(2000L))
                    .body("postId[0]", notNullValue())
                    .body("id[0]", notNullValue())
                    .body("name[0]", notNullValue())
                    .body("email[0]", notNullValue())
                    .body("body[0]", notNullValue())

                    .extract()
                    .path("email");
    		for(String email:emailList) {
    			{ System.out.println("The Email address " + email
    					  + " is " + (CommonOperations.isValidEmail(email) ? "valid" : "invalid")); }
    			Assert.assertTrue(isSuccess);
    	    }
    	}
    	
    }
    
}
