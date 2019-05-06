package com.vlad.db.repo;

import com.vlad.db.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group,Long> {
}
