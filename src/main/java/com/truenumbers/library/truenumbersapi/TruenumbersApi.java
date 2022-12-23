package com.truenumbers.library.truenumbersapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truenumbers.library.truenumbersapi.models.*;
import com.truenumbers.library.truenumbersapi.models.createtruenumbers.*;
import com.truenumbers.library.utils.ParameterStringBuilder;
import com.truenumbers.library.shared.TnApiException;
import com.truenumbers.library.utils.TnApiResponseHandler;
import com.truenumbers.truenumbersapi.models.createtruenumbers.*;
import org.truenumbers.truenumbersapi.models.*;
import org.truenumbers.truenumbersapi.models.createtruenumbers.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class TruenumbersApi {

    protected String baseUrl;

    public TruenumbersApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public TnqlResponse tnql(String tnql, String numberspace, LimitOffset limitOffset) throws IOException, InterruptedException, TnApiException, URISyntaxException {
        Map queryParameters = new HashMap();
        queryParameters.put("limit", limitOffset.getLimit().toString());
        queryParameters.put("offset", limitOffset.getOffset().toString());
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("tnql", tnql);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI tnqlUrl = buildUri("/v2/numberflow/tnql?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(tnqlUrl)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(TnqlResponse.class, response).handle();
    }

    public TnqlResponse tnql(String tnql, String numberspace) throws IOException, InterruptedException, TnApiException, URISyntaxException {
        return this.tnql(tnql, numberspace, new LimitOffset());
    }

    public CreateManyTruenumbersResponse createTruenumbersFromTruespeak (String truespeak, String numberspace, CreateManyTruenumbersOptions options) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("truespeak", truespeak);
        bodyMap.put("tags", options.getTags());
        bodyMap.put("noReturn", options.getNoReturn());
        bodyMap.put("skipStore", options.getSkipStore());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(CreateManyTruenumbersResponse.class, response).handle();
    }

    public CreateManyTruenumbersResponse createTruenumbersFromTruespeak (String truespeak, String numberspace) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        return this.createTruenumbersFromTruespeak(truespeak, numberspace, buildDefaultCreateManyOptions());
    }

    public CreateManyTruenumbersResponse createTruenumbers(List<CreateTruenumberPayload> tnsToCreate, String numberspace, CreateManyTruenumbersOptions options) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("truenumbers", tnsToCreate);
        bodyMap.put("tags", options.getTags());
        bodyMap.put("noReturn", options.getNoReturn());
        bodyMap.put("skipStore", options.getSkipStore());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(CreateManyTruenumbersResponse.class, response).handle();
    }

    public CreateManyTruenumbersResponse createTruenumbers(List<CreateTruenumberPayload> tnsToCreate, String numberspace) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        return this.createTruenumbers(tnsToCreate, numberspace, buildDefaultCreateManyOptions());
    }

    public CreateTruenumberResponse createTruenumberFromTruespeak (String truespeak, String numberspace, CreateTruenumberOptions options) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("truespeak", truespeak);
        bodyMap.put("noReturn", options.getNoReturn());
        bodyMap.put("skipStore", options.getSkipStore());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new TnApiResponseHandler<>(CreateTruenumberResponse.class, response).handle();
    }

    public CreateTruenumberResponse createTruenumberFromTruespeak (String truespeak, String numberspace) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        return this.createTruenumberFromTruespeak(truespeak, numberspace, buildDefaultCreateOptions());
    }

    public CreateTruenumberResponse createTruenumber (CreateTruenumberPayload truenumberPayload, String numberspace, CreateTruenumberOptions options) throws IOException, InterruptedException, TnApiException, URISyntaxException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        List truenumbers = new ArrayList<CreateTruenumberPayload>();
        truenumbers.add(truenumberPayload);

        bodyMap.put("truenumbers", truenumbers);
        bodyMap.put("noReturn", options.getNoReturn());
        bodyMap.put("skipStore", options.getSkipStore());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new TnApiResponseHandler<>(CreateTruenumberResponse.class, response).handle();
    }

    public CreateTruenumberResponse createTruenumber (CreateTruenumberPayload truenumberPayload, String numberspace) throws IOException, InterruptedException, TnApiException, URISyntaxException {
        return this.createTruenumber(truenumberPayload, numberspace, buildDefaultCreateOptions());
    }


    public DeleteTruenumbersResponse deleteTruenumbers (String tnql, String numberspace) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("tnql", tnql);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(DeleteTruenumbersResponse.class, response).handle();
    }

    public DeleteTruenumbersResponse deleteTruenumberById (String id, String numberspace) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        URI uri = buildUri("/v2/numberflow/numbers/" + id + "?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(DeleteTruenumbersResponse.class, response).handle();
    }

    public GetTruenumberByIdResponse getTruenumberById (String id, String numberspace) throws IOException, InterruptedException, URISyntaxException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        URI uri = buildUri("/v2/numberflow/numbers/" + id + "?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(GetTruenumberByIdResponse.class, response).handle();
    }

    public TagTruenumbersResponse tagTruenumbers (String tnql, String numberspace, List<String> tags) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("tnql", tnql);
        bodyMap.put("addTags", tags);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers/tags?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(TagTruenumbersResponse.class, response).handle();
    }

    public TagTruenumbersResponse removeTagsFromTruenumbers (String tnql, String numberspace, List<String> tags) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("tnql", tnql);
        bodyMap.put("removeTags", tags);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers/tags?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(TagTruenumbersResponse.class, response).handle();
    }

    public TagTruenumbersResponse tagTruenumberById (String id, String numberspace, List<String> tags) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("addTags", tags);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers/" + id + "/tags?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(TagTruenumbersResponse.class, response).handle();
    }

    public TagTruenumbersResponse removeTagsFromTruenumberById (String id, String numberspace, List<String> tags) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Map queryParameters = new HashMap();
        queryParameters.put("numberspace", numberspace);

        Map bodyMap = new HashMap();
        bodyMap.put("removeTags", tags);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numbers/" + id + "/tags?" +
                ParameterStringBuilder.getParamsString(queryParameters));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(TagTruenumbersResponse.class, response).handle();
    }

    public CreateNumberspaceResponse createNumberspace (String numberspace) throws URISyntaxException, IOException, InterruptedException, TnApiException {
        Map bodyMap = new HashMap();
        bodyMap.put("numberspace", numberspace);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        URI uri = buildUri("/v2/numberflow/numberspace");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(CreateNumberspaceResponse.class, response).handle();
    }

    public GetNumberspacesResponse getNumberspaces () throws TnApiException, IOException, InterruptedException, URISyntaxException {
        URI uri = buildUri("/v2/numberflow/numberspace");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new TnApiResponseHandler<>(GetNumberspacesResponse.class, response).handle();
    }

    protected URI buildUri(String urlPart) throws URISyntaxException {
        return new URI(baseUrl + urlPart);
    }

    protected CreateManyTruenumbersOptions buildDefaultCreateManyOptions() {
        return CreateManyTruenumbersOptions.builder()
                .tags(new ArrayList<>())
                .noReturn(false)
                .skipStore(false)
                .build();
    }

    protected CreateTruenumberOptions buildDefaultCreateOptions() {
        return CreateTruenumberOptions.builder()
                .noReturn(false)
                .skipStore(false)
                .build();
    }
}
