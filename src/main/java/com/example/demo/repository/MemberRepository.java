package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// spring data jpa의 다양한 기본기능을 쓰기 위해서는 JpaRepository를 상속받아야한다.
// 상속받을때, entity명과 해당 entity의 pk를 옵션으로 줘야한다.
public interface MemberRepository extends JpaRepository<Member, Long> {
}
