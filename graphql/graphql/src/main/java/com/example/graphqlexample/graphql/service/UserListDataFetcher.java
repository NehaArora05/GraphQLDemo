/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.graphqlexample.graphql.service;

import com.example.graphqlexample.graphql.model.Users;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sony 
 * This class is going to interact with the repository for finding all the users existing in the database
 */
@Component
public class UserListDataFetcher implements DataFetcher<List<Users>> {

    @Autowired
    private UserRepo repo;

    @Override
    public List<Users> get(final DataFetchingEnvironment dfe) {
        return (List<Users>) repo.findAll();
    }

}
