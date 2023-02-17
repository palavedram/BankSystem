package com.IronHack.BankSystem.repositories.users;

import com.IronHack.BankSystem.models.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
