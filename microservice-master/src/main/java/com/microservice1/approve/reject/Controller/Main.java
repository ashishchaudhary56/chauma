package com.microservice1.approve.reject.Controller;

import com.microservice1.approve.reject.Model.IPolicy;
import com.microservice1.approve.reject.Services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class Main {
  @Autowired
  Service service;

  @DeleteMapping("/deleteById/{id}")
  public String delete(@PathVariable int id) {
    return service.delete(id);
  }

  @GetMapping("/MarkStatus/{str}/{id}")
  public String mark(@PathVariable String str, @PathVariable int id) {
    if (str.equals("approve")) return service.approve(id); else return service.reject(id);
  }

  @GetMapping("/viewAll")
  public Iterable<IPolicy> viewAll() {
    return service.viewAll();
  }
}
