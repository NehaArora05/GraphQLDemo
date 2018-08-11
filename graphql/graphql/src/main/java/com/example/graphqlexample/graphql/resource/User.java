/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.graphqlexample.graphql.resource;

import com.example.graphqlexample.graphql.service.GraphQLService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sony
 */
@RequestMapping("/users")
@RestController
public class User {
    @Autowired
    GraphQLService graphQLService;

@RequestMapping(method={RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
    public ResponseEntity<Object> getAllUsers(HttpServletRequest request, @RequestBody String query) {
       
        if(query!=null && !query.isEmpty()){
            String method = request.getMethod();
            ExecutionResult execute = graphQLService.getGraphQL().execute(
                    ExecutionInput.newExecutionInput().query(query).context(method).build());
            return new ResponseEntity<>(execute, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    
    
    
}
