package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //select m from Member m where m.name = ? 이렇게 JPQL 작성됨(by뒤에 나오는 걸로(AND, OR 도 가능)
    @Override
    Optional<Member> findByName(String name);
}
