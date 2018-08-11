/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.graphqlexample.graphql.service;

import com.example.graphqlexample.graphql.model.Users;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sony
 */
@Service
public class GraphQLService {
    @Value("classpath:users.graphql")
    Resource resource;
    private GraphQL graphQl;
    @Autowired
    private UserListDataFetcher allUsersDataFetcher;
    @Autowired
    private UserDataFetcher userDataFetcher;
    @Autowired
    UserRepo repo;
    @PostConstruct
    public void loadSchema() throws IOException{
        //TODO :_Remove
        saveHardcodedData();
        File schemeFile = resource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemeFile);
        RuntimeWiring writing = buildRunTimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, writing);
        graphQl = GraphQL.newGraphQL(schema).build();
        
    }

    private RuntimeWiring buildRunTimeWiring() {
       
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allUsers", allUsersDataFetcher)
                        .dataFetcher("user", userDataFetcher))
                .build();
    }
    
    /**
     * It will be removed once we save the objects through graphql
     */
    private void saveHardcodedData() {

        repo.save(new Users("1", "Neha"));
        repo.save(new Users("2", "Neha Arora"));
        
    }

    public GraphQL getGraphQL() {
        return graphQl;
    }
}
