package org.truenumbers.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.truenumbers.truenumbersapi.models.TnqlResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

public class TnApiResponseHandler<T> {
    protected final ObjectMapper objectMapper = new ObjectMapper();
    protected final Class<T> targetClass;
    protected final HttpResponse<String> response;

    public TnApiResponseHandler(Class<T> targetClass, HttpResponse<String> response) {
        this.targetClass = targetClass;
        this.response = response;
    }

    public T handle() throws TnApiException, IOException {
        try {
            if (response.statusCode() >= 400) {
                throw TruenumberUtils.parseAndThrowTnApiError(response);
            }
            return objectMapper.readValue(response.body(), targetClass);
        } catch (JsonMappingException ex) {
            System.out.println(ex.getMessage());
            throw TruenumberUtils.parseAndThrowTnApiError(response);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw TruenumberUtils.parseAndThrowTnApiError(response);
        }
    }
}
