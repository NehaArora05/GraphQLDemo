/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.graphqlexample.graphql.service;


import com.example.graphqlexample.graphql.model.Users;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Sony
 */
public interface UserRepo extends CrudRepository<Users, String>{
    
}
