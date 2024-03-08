package com.rollcall.web.repository;

import com.rollcall.web.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByTitle(String url);
    @Query("SELECT g from Group g WHERE g.title LIKE CONCAT('%', :query, '%')")
    List<Group> searchGroups(String query);
}
