package backend;

import backend.Model.RequestModelRegister;
import backend.Model.RequestModelUpdateUser;
import backend.Model.ResponseModel;
import backend.Model.ResponseModelSingleUser;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

public class BackendTests {

    @DisplayName("Успешная регистрация")
    @Test
    void reqresRegisterSuccessful() {
        RequestModelRegister bodyModelLombok = new RequestModelRegister();
        bodyModelLombok.setEmail("eve.holt@reqres.in");
        bodyModelLombok.setPassword("pistol");


        ResponseModel modelResponseLombok = given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .and()
                .body(bodyModelLombok)
                .when()
                .post("https://reqres.in/api/register")
                .then().log().ifStatusCodeIsEqualTo(400)
                .extract().as(ResponseModel.class);

        assertThat(modelResponseLombok.getId()).isEqualTo("4");
        assertThat(modelResponseLombok.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");

    }

    @DisplayName("Неуспешная регистрация. Не указываем пароль")
    @Test
    void reqresRegisterUnsuccessful() {
        RequestModelRegister bodyModelLombok = new RequestModelRegister();
        bodyModelLombok.setEmail("sydney@fife");

        ResponseModel modelResponseLombok = given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .and()
                .body(bodyModelLombok)
                .when()
                .post("https://reqres.in/api/register")
                .then().statusCode(400)
                .extract().as(ResponseModel.class);

        assertThat(modelResponseLombok.getError()).isEqualTo("Missing password");
    }

    @DisplayName("Успешный вход")
    @Test
    void reqresLoginSuccessful() {
        RequestModelRegister bodyModelLombok = new RequestModelRegister();
        bodyModelLombok.setEmail("eve.holt@reqres.in");
        bodyModelLombok.setPassword("cityslicka");

        ResponseModel modelResponseLombok = given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .and()
                .body(bodyModelLombok)
                .when()
                .post("https://reqres.in/api/login")
                .then().statusCode(200)
                .extract().as(ResponseModel.class);

        assertThat(modelResponseLombok.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @DisplayName("Неуспешный вход")
    @Test
    void reqresLoginUnsuccessful() {
        RequestModelRegister bodyModelLombok = new RequestModelRegister();
        bodyModelLombok.setEmail("peter@klaven");

        ResponseModel modelResponseLombok = given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .and()
                .body(bodyModelLombok)
                .when()
                .post("https://reqres.in/api/login")
                .then().statusCode(400)
                .extract().as(ResponseModel.class);

        assertThat(modelResponseLombok.getError()).isEqualTo("Missing password");
    }


    @DisplayName("Страница пользователей №2")
    @Test
    void reqresListUsers() {

        ResponseModel modelResponseLombok = given()
                .log().all()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ResponseModel.class);

        assertThat(modelResponseLombok.getPage()).isEqualTo(2);
        assertThat(modelResponseLombok.getPer_page()).isEqualTo(6);
        assertThat(modelResponseLombok.getTotal()).isEqualTo(12);
        assertThat(modelResponseLombok.getTotal_pages()).isEqualTo(2);
        // Разбираем массив
        assertThat(modelResponseLombok.getData(), hasSize(6));
        assertThat(modelResponseLombok.getData().get(0).getFirst_name(), is("Michael"));
        assertThat(modelResponseLombok.getData().get(1).getFirst_name(), is("Lindsay"));
        assertThat(modelResponseLombok.getData().get(2).getFirst_name(), is("Tobias"));
        assertThat(modelResponseLombok.getData().get(3).getFirst_name(), is("Byron"));
        assertThat(modelResponseLombok.getData().get(4).getFirst_name(), is("George"));
        assertThat(modelResponseLombok.getData().get(5).getFirst_name(), is("Rachel"));
        // Проверка поддержки
        assertThat(modelResponseLombok.getSupport().getUrl(), is("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"));
        assertThat(modelResponseLombok.getSupport().getText(), is("Tired of writing endless social media content? Let Content Caddy generate it for you."));
    }

    @DisplayName("Запрашиваем данные по пользователю №2")
    @Test
    void reqresUser2() {

        ResponseModelSingleUser modelResponseLombok = given()
                .log().all()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ResponseModelSingleUser.class);
        // data
        assertThat(modelResponseLombok.getData().getId()).isEqualTo(2);
        assertThat(modelResponseLombok.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
        assertThat(modelResponseLombok.getData().getFirst_name()).isEqualTo("Janet");
        assertThat(modelResponseLombok.getData().getLast_name()).isEqualTo("Weaver");
        assertThat(modelResponseLombok.getData().getAvatar()).isEqualTo("https://reqres.in/img/faces/2-image.jpg");
        // support
        assertThat(modelResponseLombok.getSupport().getUrl()).isEqualTo("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");
        assertThat(modelResponseLombok.getSupport().getText()).isEqualTo("Tired of writing endless social media content? Let Content Caddy generate it for you.");
    }

    @DisplayName("Запрашиваем данные по несуществующему пользователю № 23")
    @Test
    void reqresUser23() {

        ResponseModelSingleUser modelResponseLombok = given()
                .log().all()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(404)
                .extract().as(ResponseModelSingleUser.class);
    }


    @DisplayName("Обновляем пользователя №2")
    @Test
    void reqresUpdatedUser2() {
        RequestModelUpdateUser bodyModelLombok = new RequestModelUpdateUser();
        bodyModelLombok.setName("morpheus");
        bodyModelLombok.setJob("zion resident");

        ResponseModel modelResponseLombok = given()
                .log().all()
                .header("Content-type", "application/json")
                .and()
                .body(bodyModelLombok)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ResponseModel.class);

        assertThat(modelResponseLombok.getName()).isEqualTo("morpheus");
        assertThat(modelResponseLombok.getJob()).isEqualTo("zion resident");
        assertThat(modelResponseLombok.getUpdatedAt()).isNotNull();
    }
}