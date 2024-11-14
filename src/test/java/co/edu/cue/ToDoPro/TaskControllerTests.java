package co.edu.cue.ToDoPro;

import co.edu.cue.ToDoPro.services.TaskService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        taskService.clearTasks(); // Reset tasks before each test
    }

    @Test
    public void testCreateTask() {
        given()
                .contentType(ContentType.URLENC)
                .formParam("title", "Ganar parcial")
                .when()
                .post("/api/tasks")
                .then()
                .statusCode(200)
                .body("title", equalTo("Ganar parcial"));
    }

    @Test
    public void testGetAllTasks() {
        taskService.createTask("Ganar parcial");

        given()
                .when()
                .get("/api/tasks")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testGetTaskById() {
        taskService.createTask("Ganar parcial");

        given()
                .when()
                .get("/api/tasks/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Ganar parcial"));
    }

    @Test
    public void testUpdateTaskTitle() {
        taskService.createTask("Ganar parcial");

        given()
                .contentType(ContentType.URLENC)
                .formParam("title", "Estudiar para parcial")
                .when()
                .put("/api/tasks/1/title")
                .then()
                .statusCode(200)
                .body("title", equalTo("Estudiar para parcial"));
    }

    @Test
    public void testUpdateTaskStatus() {
        taskService.createTask("Ganar parcial");

        given()
                .contentType(ContentType.URLENC)
                .formParam("status", "COMPLETED")
                .when()
                .put("/api/tasks/1/status")
                .then()
                .statusCode(200)
                .body("status", equalTo("COMPLETED"));
    }

    @Test
    public void testDeleteTask() {
        taskService.createTask("Ganar parcial");

        // Send DELETE request
        given()
                .when()
                .delete("/api/tasks/1")
                .then()
                .statusCode(204);

        given()
                .when()
                .get("/api/tasks/1")
                .then()
                .statusCode(404);
    }

}
