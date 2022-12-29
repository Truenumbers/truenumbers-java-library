package org.truenumbers.triggerapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.truenumbers.triggerapi.models.CreateTriggerPayload;
import org.truenumbers.triggerapi.models.GetTriggerDefinitionsResponse;
import org.truenumbers.triggerapi.models.TriggerDefinitionResponse;
import org.truenumbers.triggerapi.models.TriggerStatus;
import org.truenumbers.utils.ParameterStringBuilder;
import org.truenumbers.utils.TnApiException;
import org.truenumbers.utils.TnApiResponseHandler;
import org.truenumbers.utils.TruenumberUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class TriggerApi {

    protected String baseUri;

    public TriggerApi(String baseUri) {
        this.baseUri = baseUri;
    }

    public TriggerDefinitionResponse createTriggerDefinition (CreateTriggerPayload createTriggerPayload) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map bodyMap = new HashMap();
        bodyMap.put("numberspace", createTriggerPayload.getNumberspace());
        bodyMap.put("name", createTriggerPayload.getName());
        bodyMap.put("description", createTriggerPayload.getDescription());
        bodyMap.put("destinations", createTriggerPayload.getDestinations());
        bodyMap.put("executeOn", createTriggerPayload.getExecuteOn());
        bodyMap.put("tnql", createTriggerPayload.getTnql());
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v1/trigger-definition");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response " + response.body());
        return new TnApiResponseHandler<>(TriggerDefinitionResponse.class, response).handle();
    }

    public TriggerDefinitionResponse getTriggerDefinitionById (String id) throws IOException, InterruptedException, TnApiException, URISyntaxException {
        URI uri = buildUri("/v1/trigger-definitions/" + id);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(TriggerDefinitionResponse.class, response).handle();
    }

    public GetTriggerDefinitionsResponse getTriggerDefinitions (TriggerStatus status, String numberspace, String name) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();

        if (numberspace != null && !numberspace.isEmpty()) {
            queryParameters.put("numberspace", numberspace);
        }

        if (status != null) {
            queryParameters.put("status", status.toString());
        }

        if (name != null) {
            queryParameters.put("name", name);
        }

        URI uri = buildUri("/v1/trigger-definitions?" + ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(GetTriggerDefinitionsResponse.class, response).handle();
    }

    // method get trigger by name
    public GetTriggerDefinitionsResponse getTriggerDefinitions (String numberspace, String name) throws TnApiException, IOException, URISyntaxException, InterruptedException {
        return getTriggerDefinitions(TriggerStatus.ACTIVE, numberspace,  name);
    }

    public GetTriggerDefinitionsResponse getTriggerDefinitions (TriggerStatus status, String numberspace) throws TnApiException, IOException, URISyntaxException, InterruptedException {
        return getTriggerDefinitions(status, numberspace, null);
    }

    public GetTriggerDefinitionsResponse getTriggerDefinitions (String numberspace) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        return getTriggerDefinitions(TriggerStatus.ACTIVE, numberspace);
    }

    public GetTriggerDefinitionsResponse getTriggerDefinitions () throws IOException, URISyntaxException, InterruptedException, TnApiException {
        return getTriggerDefinitions(null);
    }

    public void deactivateTriggerDefinition (String id) throws URISyntaxException, IOException, InterruptedException, TnApiException {
        URI uri = buildUri("/v1/trigger-definitions/" + id);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400) {
            throw TruenumberUtils.parseAndThrowTnApiError(response);
        }
    }

    public void refreshTriggerDefinitionById (String id) throws URISyntaxException, IOException, InterruptedException, TnApiException {
        URI uri = buildUri("/v1/trigger-definitions/" + id + "/refresh");
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400) {
            throw TruenumberUtils.parseAndThrowTnApiError(response);
        }
    }

    protected URI buildUri(String uriPart) throws URISyntaxException {
        return new URI(baseUri + uriPart);
    }
}
