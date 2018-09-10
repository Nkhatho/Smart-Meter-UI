package za.co.bcx.websmartmeter.SmartMeter.clients;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JsonParseExceptionMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import za.co.bcx.websmartmeter.SmartMeter.models.Debtor;

/**
 *
 * @author Leolen
 */

@ApplicationScoped
@ManagedBean
public class DebtorServiceClient {

    private static final int ONE_MINUTE = 60000;
    private final String baseUrl;
    private Client client;

    public DebtorServiceClient(){
        this("http://172.16.220.210:8080");
        //this("http://localhost:8080");
    }
  
    public DebtorServiceClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    private WebTarget getWebTarget(String... paths) {
        String url = this.baseUrl;
        for (String path : paths) {
            url = String.format("%s/%s", url, path);
        }
        System.out.println("URL AT getWebTarget===" + url);
        WebTarget target = getClient().target(url);
        return target;
    }
    
    protected static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospectorPair pair = new AnnotationIntrospectorPair(primary, secondary);
        objectMapper.setAnnotationIntrospector(pair);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
    
    protected static ClientBuilder createClientBuilder() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        clientBuilder.property("jersey.config.disableMoxyJson.client", true);
        clientBuilder.property("jersey.config.client.disableMoxyJson", true);
        clientBuilder.register(JsonParseExceptionMapper.class);
        clientBuilder.register(JsonMappingExceptionMapper.class);
        ObjectMapper objectMapper = createObjectMapper();
        JacksonJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        jacksonJaxbJsonProvider.setMapper(objectMapper);
        clientBuilder.register(jacksonJaxbJsonProvider, MessageBodyReader.class, MessageBodyWriter.class);
        return clientBuilder;
    }

    private Client getClient() {
        if (client == null) {
            client = createClientBuilder().build();
            client.property("javax.xml.ws.client.connectionTimeout", ONE_MINUTE);
            client.property("javax.xml.ws.client.receiveTimeout", ONE_MINUTE);
        }
        System.out.println("CLIENT AT getClient()===" + client.toString());
        return client;
    }

    public Debtor addDebtor(Debtor debtor) {
        WebTarget webTarget = getWebTarget("debtors", "setdebtor");
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(debtor, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 201 || response.getStatus() != 200) {//added or
            System.out.println("RUNTIME EX AT addDebtor() ===" + response.getStatus());
            throw new RuntimeException("Failed to add debtor");
        }
        return (Debtor) response.getEntity();
    }

    public List<Debtor> getDebtorList() {
    
        WebTarget webTarget = getWebTarget("debtors");
        System.out.println("WEBTARGET AT getDebtorList()===" + webTarget.toString());
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .get();//HERE

        if(response.getStatus() != 200) {
            System.out.println("=====HAS ENTITY===" + response.hasEntity());
            System.out.println("=====THE ENTITY===" + response.getEntity());
            System.out.println("WEBTARGET AT getDebtorList()2===" + webTarget.toString() + "\n" + response.getStatus());
            throw new RuntimeException("Failed to retrieve debtors");
        }
        System.out.println("WEBTARGET AT getDebtorList()3===" + webTarget.toString() + "\n" + response.getStatus());
        Debtor[] debtors = response.readEntity(Debtor[].class);
        return new ArrayList<>(Arrays.asList(debtors));
    }
     public Debtor getDebtor(String idNumber) {
    
        WebTarget webTarget = getWebTarget("debtors", idNumber);
        System.out.println("WEBTARGET AT getDebtor()===" + webTarget.toString());
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .get();//HERE

        if(response.getStatus() != 200) {
            System.out.println("=====HAS ENTITY===" + response.hasEntity());
            System.out.println("=====THE ENTITY===" + response.getEntity());
            System.out.println("WEBTARGET AT getDebtor()2===" + webTarget.toString() + "\n" + response.getStatus());
            throw new RuntimeException("Failed to retrieve debtor");
        }
        System.out.println("WEBTARGET AT getDebtor()3===" + webTarget.toString() + "\n" + response.getStatus());
        Debtor debtor = response.readEntity(Debtor.class);
        return debtor;
    }
}
