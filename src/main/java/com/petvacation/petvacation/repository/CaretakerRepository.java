package com.petvacation.petvacation.repository;

import com.petvacation.petvacation.domain.CaretakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public class CaretakerRepository extends JpaRepository <CaretakerEntity, Long>, Repository CaretakerEntity, Long {
}
