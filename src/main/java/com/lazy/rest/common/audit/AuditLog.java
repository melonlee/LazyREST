package com.lazy.rest.common.audit;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "audit_logs")
public class AuditLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String path;

    @Column(length = 1000)
    private String params;

    @Column(length = 1000)
    private String result;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private Long duration;

    @CreationTimestamp
    private LocalDateTime createdAt;
} 