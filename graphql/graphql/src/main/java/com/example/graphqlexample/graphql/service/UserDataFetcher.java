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
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sony
 */
@Component
public class UserDataFetcher implements DataFetcher<Users> {

    @Autowired
    private UserRepo repo;

    @Override
    public Users get(final DataFetchingEnvironment dfe) {

        String context = dfe.getContext();
        if (RequestMethod.GET.equals(context)) {
            return this.executeGet(dfe);
        } else if (RequestMethod.POST.equals(context)) {
            return this.executePost(dfe);
        } else if (RequestMethod.PUT.equals(context)) {
            return this.executePut(dfe);
        }
        return null;
    }

    private Users executeGet(final DataFetchingEnvironment dfe) {
        String id = dfe.getArgument("userId");

        Optional<Users> opt = repo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    private Users executePost(DataFetchingEnvironment dfe) {
        //Alternatively fetch all arguments from map- and construct object from POJO
        String name = dfe.getArgument("userName");

        Users user = new Users(name);
        return repo.save(user);
        
    }

    private Users executePut(DataFetchingEnvironment dfe) {
        //Alternatively fetch all arguments from map- and construct object from POJO
        String id = dfe.getArgument("userId");
        String name = dfe.getArgument("userName");
        Users user = new Users(id,name);
        return repo.save(user);
        
    }
}
