package com.jwis.demo.repo;

import com.jwis.demo.dao.BopDao;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BopRepo extends Neo4jRepository {

    @Query("match(n:bop) where n.name = :#{#name} return n")
    BopDao findByName(String name);

    @Query("match(n:bop) set n.part_number = :#{#bopDao.partNumber},n.part_supplier = :#{#bopDao.partSupplier},n.procedure =:#{#bopDao.procedure}")
    void updateBop(BopDao bopDao);
}
