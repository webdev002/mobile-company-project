package pdp.uz.mobilecompanyspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.mobilecompanyspringbootproject.entity.Branches;
import pdp.uz.mobilecompanyspringbootproject.entity.Employee;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.BranchesDto;
import pdp.uz.mobilecompanyspringbootproject.payload.EmployeeDto;
import pdp.uz.mobilecompanyspringbootproject.repository.BranchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    public List<Branches> getBranch() {
        List<Branches> branches = branchRepository.findAll();
        return branches;
    }

    public ApiResponse addBranch(BranchesDto branchesDto) {

        //BRANCHNI SAQLADIK
        Branches branches = new Branches();
        branches.setAddress(branchesDto.getAddress());
        branches.setManagerDirector(branchesDto.getManagerDirector());

        //EMPLOYEENI SAQLAYMIZ
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeDto employeeDto : branchesDto.getEmployeeDtoList()) {
            Employee employee = new Employee(
                    employeeDto.getName(),
                    employeeDto.getPhoneNumber(),
                    branches
            );
            employeeList.add(employee);
        }
        branches.setEmployees(employeeList);
        branchRepository.save(branches);
        return new ApiResponse("Saqlandi",true);
    }

    public ApiResponse editBranch(Integer id, BranchesDto branchesDto) {
        boolean existsByManagerDirectorAndIdNot = branchRepository.existsByManagerDirectorAndIdNot(branchesDto.getManagerDirector(), id);
        if (existsByManagerDirectorAndIdNot){
            return new ApiResponse("Bunday idlik branch mavjud",false);
        }
        Optional<Branches> optionalBranches = branchRepository.findById(id);
        if (optionalBranches.isPresent()){

            //BRANCHES SAQLAYMIZ
            Branches branches = optionalBranches.get();
            branches.setAddress(branchesDto.getAddress());
            branches.setManagerDirector(branchesDto.getManagerDirector());

            //EMPLOYEENI SAQLAYMIZ
            List<Employee> employeeList = new ArrayList<>();
            for (EmployeeDto employeeDto : branchesDto.getEmployeeDtoList()) {
                Employee employee = new Employee(
                        employeeDto.getName(),
                        employeeDto.getPhoneNumber(),
                        branches
                );
                employeeList.add(employee);
            }
            branches.setEmployees(employeeList);
            branchRepository.save(branches);
            return new ApiResponse("Branch tahrirlandi",true);
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse deleteBranch(Integer id) {
        branchRepository.deleteById(id);
        return new ApiResponse("Branch Ochirildi",true);
    }
}
