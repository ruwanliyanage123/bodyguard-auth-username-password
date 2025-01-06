package com.bodyguard.auth.userpass.bodyguard_auth_username_password.repository;

import com.bodyguard.auth.userpass.bodyguard_auth_username_password.entity.ScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScopeRepository extends JpaRepository<ScopeEntity, Integer> {
    /**
     * Find scopes
     *
     * @param roleId role id
     * @return list of scopes
     */
    @Query(value = "select permission_id, permission from permission p inner join role_permission rp on p.id = rp.permission_id inner join role r on rp.role_id = r.id where role_id =:roleId;", nativeQuery = true)
    public List<ScopeEntity> findScopes(@Param("roleId") Integer roleId);
}
