package com.sayan.ElectroHub.Response;

import java.util.List;
import java.util.Map;

public class MappingResponse {

    public static UniversalResponse mapUniversalResponse(String errorStatus, List<Object> response){
        UniversalResponse universalResponse = new UniversalResponse();

        universalResponse.setMessage(errorStatus);
        universalResponse.setResponse(response);
        return universalResponse;
    }
}
