package com.au.ymor.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostalCodeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String postalCode1;

    private String postalCode2;


}
