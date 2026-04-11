package org.dojo.spring.shared.models;

import jakarta.persistence.Column;

import java.time.Instant;

public abstract class Auditable {
    @Column(name = AuditableColumns.createdBy, nullable = false, updatable = false)
    private String createdBy;
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    @Column(name = AuditableColumns.createdDate, nullable = false, updatable = false)
    private Instant createdDate;
    public Instant getCreatedDate() { return createdDate; }
    public void setCreatedDate(Instant createdDate) { this.createdDate = createdDate; }

    @Column(name = AuditableColumns.lastModifiedBy, nullable = false)
    private String lastModifiedBy;
    public String getLastModifiedBy() { return lastModifiedBy; }
    public void setLastModifiedDate(Instant lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }

    @Column(name = AuditableColumns.lastModifiedDate, nullable = false)
    private Instant lastModifiedDate;
    public Instant getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedBy(String lastModifiedBy) { this.lastModifiedBy = lastModifiedBy; }
}