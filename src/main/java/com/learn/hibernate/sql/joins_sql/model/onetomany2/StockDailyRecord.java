package com.learn.hibernate.sql.joins_sql.model.onetomany2;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "stock_daily_record", uniqueConstraints = @UniqueConstraint(columnNames = "DATE"))
public class StockDailyRecord implements java.io.Serializable {

    private Integer recordId;
    private Stock stock;
    private Float priceOpen;
    private Float priceClose;
    private Float priceChange;
    private Long volume;
    private Date date;

    public StockDailyRecord() {
    }

    public StockDailyRecord(Stock stock, Date date) {
        this.stock = stock;
        this.date = date;
    }

    public StockDailyRecord(Stock stock, Float priceOpen, Float priceClose,
                            Float priceChange, Long volume, Date date) {
        this.stock = stock;
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        this.priceChange = priceChange;
        this.volume = volume;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "RECORD_ID", unique = true, nullable = false)
    public Integer getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_ID", nullable = false)
    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Column(name = "PRICE_OPEN", precision = 6)
    public Float getPriceOpen() {
        return this.priceOpen;
    }

    public void setPriceOpen(Float priceOpen) {
        this.priceOpen = priceOpen;
    }

    @Column(name = "PRICE_CLOSE", precision = 6)
    public Float getPriceClose() {
        return this.priceClose;
    }

    public void setPriceClose(Float priceClose) {
        this.priceClose = priceClose;
    }

    @Column(name = "PRICE_CHANGE", precision = 6)
    public Float getPriceChange() {
        return this.priceChange;
    }

    public void setPriceChange(Float priceChange) {
        this.priceChange = priceChange;
    }

    @Column(name = "VOLUME")
    public Long getVolume() {
        return this.volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", unique = true, nullable = false, length = 10)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}