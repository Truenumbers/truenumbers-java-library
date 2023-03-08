package com.truenumbers.examples;

import com.truenumbers.truenumbersapi.TruenumberValueType;
import com.truenumbers.truenumbersapi.models.TaxonomyType;
import com.truenumbers.truenumbersapi.models.createtruenumbers.CreateManyTruenumbersOptions;
import com.truenumbers.truenumbersapi.models.createtruenumbers.CreateTruenumberPayload;
import com.truenumbers.truenumbersapi.Truenumber;
import com.truenumbers.truenumbersapi.TruenumbersApi;
import com.truenumbers.shared.TnApiException;
import com.truenumbers.truenumbersapi.models.createtruenumbers.CreateTruenumberValuePayload;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TnApiExamples {
    private String numberspace = "_system:numberspace/test-nard-parser";
    private TruenumbersApi tnApi = new TruenumbersApi("http://gov.truenumbers.com");

    public TnApiExamples () {

    }

    public TnApiExamples (String numberspace) {
        this();
        this.numberspace = numberspace;
    }

    public TnApiExamples(String numberspace, String tnApiBase) {
        this(numberspace);
        this.tnApi = new TruenumbersApi(tnApiBase);
    }


    public void testTnql () throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var response = tnApi.tnql("* has *", numberspace);
        System.out.println("TNQL results " + response.getTruenumbers().size());
    }

    public void testGetDistinctTaxonomyQuery () throws TnApiException, URISyntaxException, IOException, InterruptedException {
        var response = tnApi.getDistinctTaxonomyFromQuery(TaxonomyType.tags, numberspace);
        System.out.println("Taxonomy Results size " + response.getTaxonomy().size());
        System.out.println(response.getTotalTaxonomyCount());
    }

    public void testCreateTruenumbersFromTruespeak () throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var response = tnApi.createTruenumbersFromTruespeak("building has length = 3 m",
                numberspace,
                CreateManyTruenumbersOptions.builder()
                        .skipStore(true)
                        .build());
        System.out.println("Create truenumbers from truespeak length " + response.getTruenumbers().size());
    }

    public void testCreateTruenumbers() throws TnApiException, IOException, URISyntaxException, InterruptedException {
        List<CreateTruenumberPayload> payload = new ArrayList<>();
        payload.add(CreateTruenumberPayload.builder()
                .property("length")
                .subject("building")
                .value("3 m")
                .build());
        payload.add(CreateTruenumberPayload.builder()
                .property("srd_property")
                .subject("building")
                .valuePayload(CreateTruenumberValuePayload.builder().value("12345").type(TruenumberValueType.srd).build())
                .build());
        var response = tnApi.createTruenumbers(payload, numberspace,
                CreateManyTruenumbersOptions.builder()
                        .skipStore(true)
                        .build());
        System.out.println("Create truenumbers length " + response.getTruenumbers().size());
        System.out.println("Created truenumber with payload struct " + response.getTruenumbers().get(1).getValue().getType().toString());
    }

    public Truenumber testCreateTruenumber() throws TnApiException, IOException, URISyntaxException, InterruptedException {
        CreateTruenumberPayload payload = CreateTruenumberPayload.builder()
                .subject("building")
                .property("length")
                .value("3 m")
                .build();
        var response = tnApi.createTruenumber(payload, numberspace);
        return response.getTruenumber();
    }

    public void testTagTruenumberById(String id) throws TnApiException, IOException, URISyntaxException, InterruptedException {
        tnApi.tagTruenumberById(id, numberspace, List.of("testing-api"));
    }

    public Truenumber testGetTruenumberById(String id) throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var response = tnApi.getTruenumberById(id, numberspace);
        return response.getTruenumber();
    }

    public void testGetNumberspaces() throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var response = tnApi.getNumberspaces();
        System.out.println("numberspaces " + response.getNumberspaces().toString());
    }

    public void testDeleteTruenumberById (String id) throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var response = tnApi.deleteTruenumberById(id, numberspace);
        System.out.println("Deleted by id count " + response.getDeletedCount());
    }

    public void testDeleteTruenumbers (String tnql) throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var response = tnApi.deleteTruenumbers(tnql, numberspace);
        System.out.println("Deleted many count " + response.getDeletedCount());
    }
}
