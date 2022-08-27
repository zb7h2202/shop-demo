package com.myself.console.dao;

import com.myself.console.entity.TradeLogEntity;
import com.myself.spy.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeLogDao extends JpaRepository<TradeLogEntity,Long>, JpaSpecificationExecutor<TradeLogEntity> {

}
