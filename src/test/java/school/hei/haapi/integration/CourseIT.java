package school.hei.haapi.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import school.hei.haapi.SentryConf;
import school.hei.haapi.endpoint.rest.api.PayingApi;
import school.hei.haapi.endpoint.rest.api.TeachingApi;
import school.hei.haapi.endpoint.rest.client.ApiClient;
import school.hei.haapi.endpoint.rest.client.ApiException;
import school.hei.haapi.endpoint.rest.model.Course;
import school.hei.haapi.endpoint.rest.model.CreateFee;
import school.hei.haapi.endpoint.rest.model.Fee;
import school.hei.haapi.endpoint.rest.security.cognito.CognitoComponent;
import school.hei.haapi.integration.conf.AbstractContextInitializer;
import school.hei.haapi.integration.conf.TestUtils;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static school.hei.haapi.integration.conf.TestUtils.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = CourseIT.ContextInitializer.class)
@AutoConfigureMockMvc
class CourseIT {
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

  static Course course1(){
    return new Course()
            .id("course1_id")
            .ref("PROG1")
            .name("Algorithmique")
            .credits(6)
            .totalHours(60) ;
  }

  static Course course2(){
    return new Course()
            .id("course2_id")
            .ref("SYS1")
            .name("Composants de l''ordinateur")
            .credits(6)
            .totalHours(60) ;
  }
  static Course course3(){
    return new Course()
            .id(null)
            .ref("SYS2")
            .name("Composants de l''ordinateur")
            .credits(6)
            .totalHours(60) ;
  }

  private static ApiClient anApiClient(String token) {
    return TestUtils.anApiClient(token, CourseIT.ContextInitializer.SERVER_PORT);
  }

  @BeforeEach
  void setUp() {
    setUpCognito(cognitoComponentMock);
  }


  @Test
  void student_read_ok() throws ApiException{
    ApiClient student1Client = anApiClient(STUDENT1_TOKEN);
    TeachingApi api = new TeachingApi(student1Client) ;

    List<Course> courses = api.getCourses() ;

    assertTrue(courses.contains(course1()));
  }

  @Test
  void teacher_read_ok() throws ApiException{
    ApiClient teacher1CLient = anApiClient(TEACHER1_TOKEN);
    TeachingApi api = new TeachingApi(teacher1CLient) ;

    List<Course> courses = api.getCourses() ;

    assertTrue(courses.contains(course1()));
  }

  @Test
  void manager_read_ok() throws ApiException{
    ApiClient teacher1Client = anApiClient(MANAGER1_TOKEN);
    TeachingApi api = new TeachingApi(teacher1Client) ;

    List<Course> courses = api.getCourses() ;

    assertTrue(courses.contains(course1()));
    assertTrue(courses.contains(course1()));
  }


  @Test
  void student_write_or_update_ko() throws ApiException{
    ApiClient student1Client = anApiClient(STUDENT1_TOKEN);
    TeachingApi api = new TeachingApi(student1Client) ;

    assertThrowsForbiddenException(() -> api.createOrUpdateCourses(course1()));
  }


  @Test
  void teacher_write_or_update_ko() throws ApiException{
    ApiClient teacher1Client = anApiClient(TEACHER1_TOKEN) ;
    TeachingApi api = new TeachingApi(teacher1Client) ;

    assertThrowsForbiddenException(() -> api.createOrUpdateCourses(course1()));
  }

  @Test
  void manager_write_or_update_ok() throws ApiException{
    ApiClient manager1Client = anApiClient(MANAGER1_TOKEN);
    TeachingApi api = new TeachingApi(manager1Client) ;
    Course toUpdate = api.createOrUpdateCourses(course3());
    toUpdate.setRef("EL1 P1");

    Course updated = api.createOrUpdateCourses(toUpdate);

    assertEquals(toUpdate , updated);
  }
}
