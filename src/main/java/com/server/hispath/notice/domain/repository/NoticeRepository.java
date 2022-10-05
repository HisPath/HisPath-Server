package com.server.hispath.notice.domain.repository;

import com.server.hispath.notice.domain.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {


//    //SELECT * from student where title = ?
//    @Query("SELECT n FROM notice n WHERE n.title=?1")
//    Optional<Notice> findNoticeByTitle(String search);


}
