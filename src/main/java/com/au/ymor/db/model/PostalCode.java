package com.au.ymor.db.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostalCode {

    @Id
    private long id;

    private String postalCode;

    private double latitude;

    private double longitude;
}
