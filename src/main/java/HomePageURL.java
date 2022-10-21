import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class HomePageURL {
    protected static RequestSpecification getBaseSpeciafications() {
        return new RequestSpecBuilder()
                .setContentType(JSON) // тип данных в body
                .setBaseUri(BaseConfigurations.BASE_URL)
                .build();

    }
}
