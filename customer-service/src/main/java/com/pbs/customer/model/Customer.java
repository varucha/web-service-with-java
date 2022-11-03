/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbs.customer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Victor Arucha - varucha@hightech-corp.com - PBS
 */
@Getter
@Setter
public class Customer {
    
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    
}