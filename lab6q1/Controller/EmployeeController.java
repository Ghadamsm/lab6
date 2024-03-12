package com.example.lab6q1.Controller;


import com.example.lab6q1.Model.EmployeeModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    ArrayList<EmployeeModel> employeeModels = new ArrayList<>();



    @GetMapping("/get")
    public ArrayList<EmployeeModel> getEmployee(){
        return employeeModels;
    }


    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeModel employeeModel , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        employeeModels.add(employeeModel);
        return ResponseEntity.status(200).body("added");
    }


    @PutMapping("update/{id}")
    public ResponseEntity updateEmployee(@PathVariable int id , @RequestBody @Valid EmployeeModel employeeModel , Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        for (EmployeeModel employeeModel1 : employeeModels){
            if (employeeModel1.getID().equals(id)){
                employeeModels.set(id , employeeModel);
                return ResponseEntity.status(200).body("updated");
            }
        }

        return ResponseEntity.status(400).body("Invalid ID");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){

        for (EmployeeModel employeeModel : employeeModels) {
            if (employeeModel.getID().equals(id)){
                employeeModels.remove(id);
                return ResponseEntity.status(200).body("deleted");
            }
        }
        return ResponseEntity.status(400).body("Invalid ID");
    }



    @GetMapping("/searchPosition/{Position}")
    public ResponseEntity searchEmployee(@PathVariable String Position){

        ArrayList<EmployeeModel> positionSearch = new ArrayList<>();
        for (EmployeeModel employeeModel : employeeModels){
            if (employeeModel.getPosition().equals(Position)){
                positionSearch.add(employeeModel);
                return ResponseEntity.status(200).body("find position : " + positionSearch);
            }

        }
        return ResponseEntity.status(400).body("Invalid position");

    }


    @GetMapping("/searchAge/{minAge}/{maxAge}")
    public ResponseEntity searchAge(@PathVariable int minAge , @PathVariable int maxAge ){
        ArrayList<EmployeeModel> searchAge = new ArrayList<>();

        for (EmployeeModel employeeModel : employeeModels){
            if (employeeModel.getAge() >= minAge && employeeModel.getAge() <= maxAge){
                searchAge.add(employeeModel);
                return ResponseEntity.status(200).body("find employee by age : " + searchAge);
            }
        }
        return ResponseEntity.status(400).body("Invalid Age");
    }



    @GetMapping("/applyAnnual/{id}")
    public ResponseEntity applyAnnual(@PathVariable int id ){

        for (EmployeeModel employeeModel : employeeModels){
            if (employeeModel.getID().equals(id)){
                if (employeeModel.getOnLeave().equals(false)){
                    if (employeeModel.getAnnualLeave() >= 1 ){
                        employeeModel.setOnLeave(true);
                        employeeModel.setAnnualLeave(employeeModel.getAnnualLeave() - 1);
                        return ResponseEntity.status(200).body("take care!");
                    }
                }
            }
        }
        return ResponseEntity.status(400).body("Invalid");
    }




    @GetMapping("/noAnnual")
    public ResponseEntity noAnnual(){
        ArrayList<EmployeeModel> noAnnualLeave = new ArrayList<>();

        for (EmployeeModel employeeModel : employeeModels){
            if (employeeModel.getAnnualLeave() == 0){
                noAnnualLeave.add(employeeModel);
                return ResponseEntity.status(200).body("All the Employee no have Annual Leave : " + noAnnualLeave);
            }
        }

        return ResponseEntity.status(400).body("there is no employee with 0 Annual Leave ");
    }




    @GetMapping("/get/{IDSuper}/{IDem}")
    public ResponseEntity promoteEmployee(@PathVariable int IDSuper , @PathVariable int IDem){

        for (EmployeeModel employeeModel : employeeModels){
            if (employeeModel.getID().equals(IDSuper) && employeeModel.getPosition().equals("supervisor")){
                if (employeeModel.getID().equals(IDem) && employeeModel.getAge() >= 30){
                    if (employeeModel.getAnnualLeave().equals(false)){
                        employeeModel.setPosition("supervisor");
                        return ResponseEntity.status(200).body("congratulated!");
                    }
                }
            }
        }
        return ResponseEntity.status(400).body("You cannot do any change");
    }

}

