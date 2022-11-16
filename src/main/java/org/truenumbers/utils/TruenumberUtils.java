package org.truenumbers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class TruenumberUtils {
    public static TnApiException parseAndThrowTnApiError(HttpResponse<String> response) throws IOException, TnApiException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> errorMap = objectMapper.readValue(response.body(), HashMap.class);
        throw new TnApiException(errorMap.get("message"), errorMap.get("code"), errorMap.get("app"));
    }
}
