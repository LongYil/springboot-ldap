package com.xiaozhuge.springbootldap.dao;


import com.xiaozhuge.springbootldap.domain.NormalUser;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface PersonRepository extends CrudRepository<NormalUser, Name> {

}