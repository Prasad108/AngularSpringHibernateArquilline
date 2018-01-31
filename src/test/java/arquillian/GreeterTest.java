package arquillian;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
//import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.resolver.spi.MavenDependencySPI;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@RunWith(Arquillian.class)
public class GreeterTest {
	
	/*@Inject
	Greeter greeter;*/
	
	  /*@ArquillianResource
	  private URL deploymentURL;*/
	
	
	
	
	
	

   /* @Test
    public void should_create_greeting() {
        Assert.fail("Not yet implemented");
    }*/
    
	

	@Deployment
	 public static Archive createTestArchive() {
		 final String WEBAPP_SRC = "src/main/webapp";
		 File[] files =Maven.configureResolver().withMavenCentralRepo(false).loadPomFromFile("pom.xml")
			        .importRuntimeDependencies().resolve().withTransitivity().asFile();		
		 return ShrinkWrap
	    .create(WebArchive.class, "AngularSpringHibernateArquilline.war")
	    .addPackages(true, "com.app")
	    .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
	    .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/FrontController-servlet.xml"))
	    .addAsWebResource(new File(WEBAPP_SRC, "WEB-INF/jsp/addPerson.jsp"))
	    .addAsWebResource(new File(WEBAPP_SRC, "WEB-INF/jsp/showPerson.jsp"))
	    .addAsLibraries(files)
	    .addAsWebResource(new File(WEBAPP_SRC, "index.jsp"));
	  	 }
    


@Test
@RunAsClient
public void should_create_greeting() {
    
	Response  response = 	post("http://localhost:8080/AngularSpringHibernateArquilline/GetAllPersonList");
	
	/* given().
	    when().
	        get("http://localhost:8080/AngularSpringHibernateArquilline/showPerson").
	    then().
	        assertThat().
	        //spec(checkStatusCodeAndContentType).
	    and().
	        body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
	}*/
	
	//.body("fname", equalTo("Prasad"));
	
	/*JSONObject JSONResponseBody = new JSONObject(response.asString());
	 
	 //Get the desired value of a parameter
	 String result = JSONResponseBody.getString({key});
	*/
	String body = response.getBody().asString();
	
	String test=response.jsonPath().getString("fname[0]");
	System.out.println(test);
       
    
}
}