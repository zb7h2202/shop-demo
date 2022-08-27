package com.myself.spy.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "t_trade")
@Data
public class TradeEntity implements Serializable {

    private static final long serialVersionUID = 92389236732922971L;

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cId;

    @Column(name = "c_name")
    private String cName;

    @Column(name = "c_account")
    private String cAccount;

    @Column(name = "c_saving")
    private BigDecimal cSaving;

    @Column(name = "c_expend")
    private BigDecimal cExpend;

    @Column(name = "c_income")
    private BigDecimal cIncome;




    @Override
    public int hashCode() {
        return Objects.hash(cId, cName, cAccount, cSaving, cExpend, cIncome);
    }
}
