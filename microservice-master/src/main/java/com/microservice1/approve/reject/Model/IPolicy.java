package com.microservice1.approve.reject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class IPolicy {
  @Id
  private int policy_no;

  private String isApproved = "pending";
}
