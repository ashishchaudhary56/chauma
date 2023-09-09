package com.microservice1.approve.reject.Services;

import com.microservice1.approve.reject.Model.IPolicy;
import com.microservice1.approve.reject.Repositeries.Repository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Service
public class Service {
  @Autowired
  Repository repository;

  public String approve(int id) {
    IPolicy apr = repository.findById(id).orElse(null);

    if (apr == null) return "no policy found with this number";
    if (apr.getIsApproved().equals("pending")) {
      apr.setIsApproved("yes");
      repository.save(apr);
      return "success";
    }
    return "Can Not approve Rejected policies";
  }

  public String reject(int id) {
    IPolicy apr = repository.findById(id).orElse(null);

    if (apr == null) return "no policy found with this number";
    if (apr.getIsApproved().equals("pending")) {
      apr.setIsApproved("no");
      repository.save(apr);
      return "success";
    }
    return "can not reject approved Policies";
  }

  public String delete(int id) {
    repository.deleteById(id);

    return " policy " + id + " deleted ";
  }

  public Iterable<IPolicy> viewAll() {
    return repository.findAll();
  }
}
