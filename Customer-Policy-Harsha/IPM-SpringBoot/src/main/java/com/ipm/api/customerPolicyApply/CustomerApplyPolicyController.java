package com.ipm.api.customerPolicyApply;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipm.api.exceptions.ProjectExecption;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController

public class CustomerApplyPolicyController {
	
	private static final String IPM_SPRINGBOOT = "ipm-springBoot";
	
	@Autowired
	CustomerApplyPolicyService apservice;
	HttpStatus httpStatus;

	// Save Application
	@PostMapping("/applypolicy")

	public HttpStatus applicationSave(@RequestBody CustomerApplyPolicy cpp) {
		if (apservice.matchApplicationWithTotdalPolicy(cpp.getCustomeremail())) {

			try {

				apservice.applicatonPolicy(cpp);
				return httpStatus.CREATED;

			} catch (Exception e) {
				throw new ProjectExecption();
			}

		} else {
			throw new ProjectExecption("You can't apply  above totall Policy ");
		}

	}


	@PutMapping("/updatestatus/{id}")

	public String updateStatus(@PathVariable Long id, @RequestBody CustomerApplyPolicy cap) {

		CustomerApplyPolicy cp = apservice.updateStatus(id, cap);
		if (cp != null) {

			apservice.updateStatus(id, cap);

			return "Updated";
		} else {
			throw new ProjectExecption();
		}

	}

	
	@GetMapping("/countApprove")
	public int countApprove() {

		return apservice.countOfApprove("Approved");
	}


/***************************************************************************************************/
	@GetMapping("/countapplication")
//	@CircuitBreaker(name=IPM_SPRINGBOOT, fallbackMethod = "countApplyFallback")
	@Retry(name=IPM_SPRINGBOOT, fallbackMethod = "countApplyFallback")
	public int countApply() {
		int count=1;
		List<CustomerApplyPolicy> capp = apservice.getCustomerApplication();
		System.out.println("Retry method called " + count++ + " times at " + new Date(0) );
		return capp.size();
	}
	
	/*Fallback Method*/
	public String countApplyFallback(Exception e) {
		return "This is the Fallback Method for countApply method";
	}
/**************************************************************************************************/


	/*Harsha*/
	@GetMapping("/getcustomersByPending")
	
	public List<CustomerApplyPolicy> getCustomerByPending() {
		return apservice.showDataStatus("Pending");
	}
	
	
/********************************ASHISH*******************************************************/
	/*Asish*/
	// @GetMapping("/getcustomersByApproved")
	// public List<CustomerApplyPolicy> getCustomerByApproved() {

	// 	return apservice.showDataStatus("Approved");

	// }

//	// Delete Application Asish
//	@DeleteMapping("/deleteaplication/{id}")
//
//	public HttpStatus deleteStatus(@PathVariable("id") Long id) {
//
//		apservice.deleteCustomerApplication(id);
//
//		return HttpStatus.OK;
//
//	}
	/*Firt Part Asish*/
//	@GetMapping("/getallapplicationpolicy")
//
//	public List<CustomerApplyPolicy> showApplication() {
//
//		return apservice.getCustomerApplication();
//	}

	// History of application Harsha
//	@GetMapping("/historyofapplications/{cemail}")
//	public List<CustomerApplyPolicy> hsitory(@PathVariable("cemail") String cemail) {
//
//		return apservice.history(cemail);
//	}

	

//	// count pending
//	@GetMapping("/countPending")
//	public int countPending() {
//
//		return apservice.countOfApprove("Pending");
//	}

	/*Asish*/
//	@GetMapping("/countrejected")
//	public int countRejacted() {
//		return apservice.countOfApprove("Rejected");
//	}
	// @Get Customer by Status----
	
	/*Asish*/
//
//	@GetMapping("/getcustomersByRejected")
//	public List<CustomerApplyPolicy> getCustomerApplications() {
//
//		return apservice.showDataStatus("Rejected");
//
//	}
}
