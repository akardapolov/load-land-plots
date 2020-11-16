package ru.gov.rosreestr.estate.model.repository;

import ru.gov.rosreestr.estate.model.entity.ParcelsTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelsTableRepository extends JpaRepository<ParcelsTable, Long> {
}
