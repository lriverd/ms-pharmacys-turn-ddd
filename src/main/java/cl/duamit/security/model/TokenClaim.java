package cl.duamit.security.model;

public enum TokenClaim {
    CUSTOMER_ID("customer_id"),
    USER_ID("user_id"),
    DOC_NUMBER("doc_number"),
    IS_CUSTOMER("is_customer"),
    SEGMENT("segment"),
    ENTERPRISE_ID("enterprise_id"),
    PROFILE("profile"),
    TEMP_ID("temp_id");

    private String name;

    TokenClaim(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
