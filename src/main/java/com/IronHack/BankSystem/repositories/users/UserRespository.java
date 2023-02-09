package com.IronHack.BankSystem.repositories.users;

import com.IronHack.BankSystem.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User,Integer> {
}
