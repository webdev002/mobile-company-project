package pdp.uz.mobilecompanyspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.mobilecompanyspringbootproject.entity.Branches;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.BranchesDto;
import pdp.uz.mobilecompanyspringbootproject.service.BranchService;

import java.util.List;

@RestController
@RequestMapping("/api")

public class BranchController {
    @Autowired
    BranchService branchService;

    @PreAuthorize(value = "hasAnyRole('DIRECTOR','MANAGER')")
    @GetMapping("/branchAll")
    public HttpEntity<?> getBranch(){
        List<Branches> branches = branchService.getBranch();
        return ResponseEntity.ok(branches);
    }

    @PreAuthorize(value = "hasRole('DIRECTOR')")
    @PostMapping("/branch")
    public HttpEntity<?> addBranch(@RequestBody BranchesDto branchesDto){
        ApiResponse apiResponse = branchService.addBranch(branchesDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('DIRECTOR')")
    @PutMapping("/branch/{id}")
    public HttpEntity<?> editBranch(@PathVariable Integer id,@RequestBody BranchesDto branchesDto){
        ApiResponse apiResponse = branchService.editBranch(id, branchesDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:403).body(apiResponse);
    }

    @DeleteMapping("/branch/{id}")
    public HttpEntity<?> deleteBranch(@PathVariable Integer id){
        ApiResponse apiResponse = branchService.deleteBranch(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
