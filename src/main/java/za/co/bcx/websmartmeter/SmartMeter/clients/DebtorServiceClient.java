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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.bcx.websmartmeter.SmartMeter.models.Debtor;
import za.co.bcx.websmartmeter.SmartMeter.models.SystemDB;

/**
 *
 * @author Leolen
 */

@ApplicationScoped
@ManagedBean
public class DebtorServiceClient {

    
    @Autowired SystemDB systemDB;
    @Autowired private Debtor debtor;
    private boolean skip;
    private static final int ONE_MINUTE = 60000;
    private final String baseUrl;
    private Client client;

    /**
     * Constructor 
     */
    public DebtorServiceClient(){

        this("http://localhost:8080");
        systemDB = new SystemDB();
        debtor = new Debtor();
    }
  
    /**
     * Overloaded constructor
     * @param baseUrl URL string
     */
    public DebtorServiceClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    /**
     * Gets the web target with path
     * @param paths The web path
     * @return The web target instance
     */
    private WebTarget getWebTarget(String... paths) {
        String url = this.baseUrl;
        for (String path : paths) {
            url = String.format("%s/%s", url, path);
        }
        WebTarget target = getClient().target(url);
        return target;
    }
    
    /**
     * Creates an object mapper
     * @return The object mapper
     */
    protected static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospectorPair pair = new AnnotationIntrospectorPair(primary, secondary);
        objectMapper.setAnnotationIntrospector(pair);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
    
    /**
     * Creates a client builder instance
     * @return The client builder instance
     */
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
    
    /**
     * Creates a client instance
     * @return The client instance
     */
    private Client getClient() {
        if (client == null) {
            client = createClientBuilder().build();
            client.property("javax.xml.ws.client.connectionTimeout", ONE_MINUTE);
            client.property("javax.xml.ws.client.receiveTimeout", ONE_MINUTE);
        }
        return client;
    }

    /**
     * Saves Debtor details to the specified web target
     * @param debtor The Debtor instance details to be saved
     * @return Entity response
     */
    public Debtor setDebtorDetails(Debtor debtor) {
        WebTarget webTarget = getWebTarget("debtordetails", "save");
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(debtor, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 201 || response.getStatus() != 200) {//added or
            throw new RuntimeException("Failed to add debtor");
        }
        return (Debtor) response.getEntity();
    }

    /**
     * Retrieves a list of all debtor instances
     * @return List of debtors
     */
    public List<Debtor> getDebtorList() {
    
        WebTarget webTarget = getWebTarget("debtors", "details");
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() != 200) {
            throw new RuntimeException("Failed to retrieve debtors");
        }
        Debtor[] debtors = response.readEntity(Debtor[].class);
        
        for(Debtor debt: debtors){
            systemDB.setDebtorDetails(debt);
        }
        return systemDB.getDebtorsDetails();

    }
    
    /**
     * Retrieves a single instance of the debtor
     * @param idNumber The ID number of the debtor to be retrieved
     * @return The debtor instance
     */
    public Debtor getDebtor(String idNumber) {
    
        WebTarget webTarget = getWebTarget("debtors", idNumber);
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() != 200) {
            throw new RuntimeException("Failed to retrieve debtor");
        }
        return response.readEntity(Debtor.class);
    }
     
    /**
     * Saves debtor details
     * @return Confirmation string
     */
     public String save(){
        WebTarget webTarget = getWebTarget("debtordetails", "save");
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(debtor, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 201 || response.getStatus() != 200) {//added or
            throw new RuntimeException("Failed to add debtor");
        }
        FacesMessage msg = new FacesMessage("Debtor Saved", ((Debtor)response.getEntity()).getName() + " saved!!!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return "index";

     }
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
         
            return event.getNewStep();
        }
    }

    /**
     * @return the debtor
     */
    public Debtor getDebtor() {
        return debtor;
    }

    /**
     * @param debtor the debtor to set
     */
    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

}
