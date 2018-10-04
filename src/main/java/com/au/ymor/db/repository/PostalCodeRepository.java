package com.au.ymor.db.repository;

import com.au.ymor.db.model.PostalCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public interface PostalCodeRepository  extends CrudRepository<PostalCode,Long> {

    Optional<PostalCode> findByPostalCode(String postalCode);
}
