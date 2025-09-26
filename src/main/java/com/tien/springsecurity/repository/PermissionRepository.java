package com.tien.springsecurity.repository;


import com.tien.springsecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
