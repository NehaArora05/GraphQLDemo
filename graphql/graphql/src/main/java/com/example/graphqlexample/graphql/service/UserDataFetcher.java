/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.graphqlexample.graphql.service;

import com.example.graphqlexample.graphql.model.Users;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sony
 */
@Component
public class UserDataFetcher implements DataFetcher<Users>{

    @Autowired
    private UserRepo repo;
 
    @Override
    public Users get(final DataFetchingEnvironment dfe) {
        String id = dfe.getArgument("userId");
        Optional<Users> opt = repo.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }else{
            return null;
        }
      
    }
    
}
