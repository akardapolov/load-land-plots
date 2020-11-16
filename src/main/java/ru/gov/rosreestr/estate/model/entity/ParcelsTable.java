package ru.gov.rosreestr.estate.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class ParcelsTable {
  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 200)
  private String name;

  @Column(length = 2000)
  private String category;

  @Column(length = 4000)
  private String utilization;
}
