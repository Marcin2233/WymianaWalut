package com.kwiatkowski.WymianaWalut.repository;

import com.kwiatkowski.WymianaWalut.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByPesel(String pesel);
}
