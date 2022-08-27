package com.myself.spy.dao;

import com.myself.spy.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeDao extends JpaRepository<TradeEntity,Long>, JpaSpecificationExecutor<TradeEntity> {

}
