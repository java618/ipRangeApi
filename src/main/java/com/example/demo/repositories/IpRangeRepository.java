package com.example.demo.repositories;

import com.example.demo.entities.IpRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IpRangeRepository extends JpaRepository<IpRangeEntity,Long> {

//    Optional<IpRangeEntity> findIpRangeEntityByLowerRangeLessThanAndUpperRangeGreaterThan(long ipRange);

}
