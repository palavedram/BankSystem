package com.IronHack.BankSystem.repositories.accounts;

import com.IronHack.BankSystem.models.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking,Integer> {
}
