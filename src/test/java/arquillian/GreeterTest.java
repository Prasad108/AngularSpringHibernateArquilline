package arquillian;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringClientConfiguration;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.app.TestClasses.Greeter;

@RunWith(Arquillian.class)
@SpringClientConfiguration("FrontController-servlet.xml")
public class GreeterTest {
	
	@Inject
	Greeter greeter;
	
	
	@Deployment
	 public static Archive createTestArchive() {
	  return ShrinkWrap
	    .create(WebArchive.class, "rest.war")
	    .addPackages(true, "com/app")
	    .addAsWebResource("src/main/webapp/WEB-INF/jsp", "addPerson.jsp")
	    .addAsWebResource("src/main/webapp/WEB-INF/jsp", "showPerson.jsp")
	    .addAsWebInfResource("src/main/webapp/WEB-INF", "web.xml")
	    .addAsWebInfResource("src/main/webapp/WEB-INF", "FrontController-servlet.xml");
	      // Deploy our test datasource
	    
	 }
	
	@Test
	public void creat2Employee(){
		 Assert.assertNotNull(greeter);
	}

/*    @Test
    public void should_create_greeting() {
        Assert.fail("Not yet implemented");
    }
    */
    


/*@Test
public void should_create_greeting() {
    Assert.assertEquals("Hello, Earthling!",
        greeter.createGreeting("Earthling"));
    greeter.greet(System.out, "Earthling");
}*/
}