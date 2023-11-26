package com.example.watchrecommendation.module.user.repository;

import com.example.watchrecommendation.module.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from tb_users as tb WHERE tb.cpf = :cpf OR tb.phone = :phone OR tb.email = :email", nativeQuery = true)
    User UserAlreadyExists(String cpf, String email, String phone);

    @Query(value = "SELECT * from tb_users as tb WHERE tb.cpf = :login OR tb.phone = :login OR tb.email = :login", nativeQuery = true)
    User findByLogin(String login);
}
