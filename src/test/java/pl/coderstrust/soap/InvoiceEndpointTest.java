package pl.coderstrust.soap;

import static org.junit.Assert.*;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.xml.transform.StringSource;
import pl.coderstrust.Application;
import pl.coderstrust.database.sql.InSqlDatabaseEntityManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class InvoiceEndpointTest {

  @Autowired
  private WebApplicationContext applicationContext;
  private MockWebServiceClient mockClient;

  @Test
  public void shouldValidateXsdGetInvoiceResponse() throws IOException {
    mockClient = MockWebServiceClient.createClient(applicationContext);


    String req = Files.lines(Paths.get("src//main//resources//addreq"))
        .collect(Collectors.joining("\n"));
    String res = Files.lines(Paths.get("src//main//resources//addres"))
        .collect(Collectors.joining("\n"));

    Source requestPayload = new StringSource(req);
    Source responsePayload = new StringSource(res);

    Assert.assertNotNull(applicationContext);
    mockClient.sendRequest(withPayload(requestPayload))
        .andExpect(noFault())
        .andExpect(payload(responsePayload));

  }
}
