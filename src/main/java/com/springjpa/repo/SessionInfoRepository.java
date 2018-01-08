package com.springjpa.repo;


import org.drools.persistence.info.SessionInfo;
import org.springframework.data.repository.CrudRepository;

public interface SessionInfoRepository extends CrudRepository<SessionInfo, Long>{
}
