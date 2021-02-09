package cl.duamit.security.model;

import cl.duamit.shared.entitie.Rut;
import cl.duamit.pharmacy.type.EnterpriseSegmentType;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
public class Token {

    private Rut customerId;
    private Rut userId;
    private String userName;
    private boolean isCustomer;
    private EnterpriseSegmentType segment;
    private String ciSerialnumber;
    private Integer tempId;

    public String getCiSerialnumber() {
        return ciSerialnumber;
    }

    public void setCiSerialnumber(String ciSerialnumber) {
        this.ciSerialnumber = ciSerialnumber;
    }

    public Rut getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Rut customerId) {
        this.customerId = customerId;
    }

    public Rut getUserId() {
        return userId;
    }

    public void setUserId(Rut userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    public EnterpriseSegmentType getSegment() {
        return segment;
    }

    public void setSegment(EnterpriseSegmentType segment) {
        this.segment = segment;
    }

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }
}
