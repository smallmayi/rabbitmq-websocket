package com.jwis.demo.repo;

import com.jwis.demo.dao.PartDao;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PartRepo extends Neo4jRepository {

    @Query("match(n:plm) return n")
    List<PartDao> findPart();

//    @Query("match(n:plm) where n.name = :#{#partDao.name} set n.part_number = :#{#partDao.partNumber},n.part_supplier = :#{#partDao.partSupplier},n.procedure =:#{#partDao.procedure},n.profession = :#{#partDao.profession},n.type = :#{#partDao.type},n.version = :#{#partDao.version},n.via_outdiameter = :#{#partDao.viaOutdiameter},n.via_indiameter = :#{#partDao.viaIndiameter}")
    @Query("match(n:plm) where n.name = :#{#partDao.name} set n.part_number = :#{#partDao.partNumber},n.part_supplier = :#{#partDao.partSupplier},n.procedure =:#{#partDao.procedure}")
    void updatePart(PartDao partDao);

//    @Query("match (n1:plm),(n2:bop) where n1.code=xxx AND n2.code=xxx create (n1)-[r:aaa]->(n2) return r")
    @Query("match (n1:plm),(n2:bop) where n1.name=:#{#partName},n2.name=:#{#bopName} create (n1)-[r:message]->(n2) return r")
    void relationPartToBop(String partName,String bopName);

    @Query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r")
    void deleteAllNode();

    @Query("match(n:plm) where n.name = :#{#name} return n")
    PartDao findByName(String name);

    @Query("match(n:plm) where n.name =~ :#{#name} return n")
    List<PartDao> findParts(String name);
}
