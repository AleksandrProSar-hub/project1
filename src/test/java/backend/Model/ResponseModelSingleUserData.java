package backend.Model;

import lombok.Data;

@Data
public class ResponseModelSingleUserData {
    String email, first_name, last_name, avatar;
    int id;
}
