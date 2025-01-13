package models;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserResponseModel {
    private UserData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserData {
        private String id;
    }
}