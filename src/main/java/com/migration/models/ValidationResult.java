package com.migration.models;

public class ValidationResult {

    private String customerId;
    private String field;
    private String sourceValue;
    private String targetValue;
    private String status;

    public ValidationResult(
            String customerId,
            String field,
            String sourceValue,
            String targetValue,
            String status) {

        this.customerId = customerId;
        this.field = field;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getField() {
        return field;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public String getStatus() {
        return status;
    }
}