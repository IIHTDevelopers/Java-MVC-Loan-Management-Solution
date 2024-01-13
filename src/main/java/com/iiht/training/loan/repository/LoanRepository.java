package com.iiht.training.loan.repository;

import com.iiht.training.loan.entity.Loan;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(value = "select * from loan l where l.first_name like %:keyword% or l.email like %:keyword%", nativeQuery = true)
    Page<Loan> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Loan> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE loan l set l.active = :active where l.id = :id",
            nativeQuery = true)
    void updateLoanStatus(@Param("active") boolean active, @Param("id") Long id);

}
