/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pbs.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Victor Arucha - varucha@hightech-corp.com - PBS
 */
@Getter
@Setter
@AllArgsConstructor
public class ResponseEntity {
    private int stateHttp;
    private String message;
}