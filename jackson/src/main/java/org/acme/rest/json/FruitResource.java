package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @GET
    public String list() throws JsonProcessingException {
        return objectMapper.writeValueAsString(fruits);
    }

    @POST
    public String add(Fruit fruit) throws JsonProcessingException {
        fruits.add(fruit);
        return objectMapper.writeValueAsString(fruits);
    }

    @DELETE
    public String delete(Fruit fruit) throws JsonProcessingException {
        fruits.remove(fruit);
        return objectMapper.writeValueAsString(fruits);
    }
}
