package com.yarikonen.web4.Repositories;

import com.yarikonen.web4.Data.Dot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DotRepository extends JpaRepository<Dot,Long> {
    List<Dot> findFirst10ById(Long id);
    void deleteAllByUserName(String username);
}

