package com.microservice1.approve.reject.Repositeries;

import com.microservice1.approve.reject.Model.IPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<IPolicy, Integer> {}
