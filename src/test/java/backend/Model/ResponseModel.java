package backend.Model;


import lombok.Data;

import java.util.List;

@Data
public class ResponseModel {
    String  token, error, email, first_name, last_name, avatar, name, job, updatedAt;//
    List<ResponseModelUserDataList> data;
    ResponseModelSupport support;
    int id, page, per_page, total, total_pages; // List users


}
