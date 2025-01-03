package com.bodyguard.auth.userpass.bodyguard_auth_username_password.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ScopeEntity {
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "permission")
    private String permission;

    public ScopeEntity() {
    }

    public ScopeEntity(Integer permissionId, String permission) {
        this.permissionId = permissionId;
        this.permission = permission;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
