package com.asrevo.testissue.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface XtableRepository extends JpaRepository<Xtable,Long> {
}
