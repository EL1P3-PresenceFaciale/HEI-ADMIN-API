package school.hei.haapi.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import school.hei.haapi.SentryConf;
import school.hei.haapi.endpoint.rest.api.PlacesApi;
import school.hei.haapi.endpoint.rest.api.TeachingApi;
import school.hei.haapi.endpoint.rest.client.ApiClient;
import school.hei.haapi.endpoint.rest.client.ApiException;
import school.hei.haapi.endpoint.rest.model.Course;
import school.hei.haapi.endpoint.rest.model.Place;
import school.hei.haapi.endpoint.rest.security.cognito.CognitoComponent;
import school.hei.haapi.integration.conf.AbstractContextInitializer;
import school.hei.haapi.integration.conf.TestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static school.hei.haapi.integration.conf.TestUtils.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = PlaceIT.ContextInitializer.class)
@AutoConfigureMockMvc
class PlaceIT {
  @MockBean
  private SentryConf sentryConf;
  @MockBean
  private CognitoComponent cognitoComponentMock;

  static class ContextInitializer extends AbstractContextInitializer {
    public static final int SERVER_PORT = anAvailableRandomPort();

    @Override
    public int getServerPort() {
      return SERVER_PORT;
    }
  }

  static Place place1(){
    return new Place()
            .id("place1_id")
            .location("Andavamamba")
            .city("Antananrivo") ;
  }

  static Place place2(){
    return new Place()
            .id("place2_id")
            .location("Ivandry")
            .city("Antananarivo") ;
  }

  private static ApiClient anApiClient(String token) {
    return TestUtils.anApiClient(token, PlaceIT.ContextInitializer.SERVER_PORT);
  }

  @BeforeEach
  void setUp() {
    setUpCognito(cognitoComponentMock);
  }

  @Test
  void student_read_ko() throws ApiException{
    ApiClient student1Client = anApiClient(STUDENT1_TOKEN);
    PlacesApi api = new PlacesApi(student1Client) ;
   assertThrowsForbiddenException(api::getPlaces);
  }


  @Test
  void teacher_read_ko() throws ApiException{
    ApiClient teacher1Client = anApiClient(TEACHER1_TOKEN);
    PlacesApi api = new PlacesApi(teacher1Client) ;
    assertThrowsForbiddenException(api::getPlaces);
  }

  @Test
  void manager_read_ok() throws ApiException{
    ApiClient manager1Client = anApiClient(MANAGER1_TOKEN);
    PlacesApi api = new PlacesApi(manager1Client) ;

    List<Place> places = api.getPlaces() ;

    assertTrue(places.contains(place1()));
  }
}
