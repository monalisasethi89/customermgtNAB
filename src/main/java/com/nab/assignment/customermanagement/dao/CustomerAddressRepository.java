package com.nab.assignment.customermanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nab.assignment.customermanagement.entity.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, Long> {

}
