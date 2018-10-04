package com.au.ymor.db.repository;

import com.au.ymor.db.model.PostalCode;
import com.au.ymor.db.model.PostalCodeHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public interface PostalCodeHistoryRepository extends CrudRepository<PostalCodeHistory,Long> {

    Optional<PostalCodeHistory> findByPostalCode1AndPostalCode2(String postalCode1,String postalCode2);
}
