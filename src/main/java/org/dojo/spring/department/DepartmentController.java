package org.dojo.spring.department;

import jakarta.validation.Valid;
import org.dojo.spring.department.billing.Transaction;
import org.dojo.spring.department.billing.TransactionDto;
import org.dojo.spring.department.billing.TransactionMapper;
import org.dojo.spring.shared.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) { this.departmentService = departmentService; }

    @PostMapping("/department")
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        logger.info("Creating Department object.");
        var department = DepartmentMapper.INSTANCE.deserialize(departmentDto);
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(departmentService.addDepartment(department)));
    }

    @GetMapping(value = "/departments")
    public ResponseEntity<List<DepartmentProjection>> getDepartments() {
        logger.info("Getting all Department objects.");
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping(value = "/departments", params = {"transactions"})
    public ResponseEntity<List<DepartmentDto>> getDepartments(@RequestParam(name = "transactions") Boolean transactions) {
        logger.info("Getting all Department objects with Transactions included.");
        if (transactions) {
            List<Department> departments = departmentService.getAllDepartmentsWithTransactions();
            return ResponseEntity.ok(departments.stream().map(DepartmentMapper.INSTANCE::serialize).toList());
        } else {
            List<DepartmentProjection> departments = departmentService.getAllDepartments();
            return ResponseEntity.ok(departments.stream().map(DepartmentMapper.INSTANCE::serialize).toList());
        }
    }

    @GetMapping(value = "/department", params = { "id" })
    public ResponseEntity<DepartmentDto> getDepartmentById(@RequestParam(name = "id") Long departmentId) throws ResourceNotFoundException {
        logger.info("Getting Department object by id.");
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(departmentService.getDepartmentById(departmentId)));
    }

    @GetMapping(value = "/department", params = { "name" })
    public ResponseEntity<DepartmentDto> getDepartmentByName(@RequestParam(name = "name") String departmentName) throws ResourceNotFoundException {
        logger.info("Getting Department object by name.");
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(departmentService.getDepartmentByName(departmentName)));
    }

    @PutMapping(value = "/department/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody @Valid DepartmentDto departmentDto) throws ResourceNotFoundException {
        logger.info("Updating Department object.");
        var department = DepartmentMapper.INSTANCE.deserialize(departmentDto);
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(departmentService.updateDepartment(departmentId, department)));
    }

    @DeleteMapping(value = "/department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) throws ResourceNotFoundException {
        logger.info("Deleting Department object.");
        if (departmentService.deleteDepartmentById(departmentId)) return ResponseEntity.ok().body(String.format("Department Id #%d deleted!", departmentId));
        else throw new ResourceNotFoundException(String.format("Department Id #%d is not deleted!", departmentId));
    }

    @PostMapping("/department/transaction")
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody @Valid TransactionDto transactionDto) throws ResourceNotFoundException {
        logger.info("Adding Transaction to Department object.");
        Department department = departmentService.getDepartmentByName(transactionDto.departmentName());
        Transaction transaction = TransactionMapper.INSTANCE.deserialize(transactionDto);
        Transaction txn = departmentService.addTransaction(department, transaction);
        return ResponseEntity.ok(TransactionMapper.INSTANCE.serialize(txn));
    }

    @GetMapping(value = "/transactions", params = { "departmentName" })
    public ResponseEntity<List<TransactionDto>> getTransactions(@RequestParam(name = "departmentName") String departmentName) throws ResourceNotFoundException {
        logger.info("Getting all Transactions for this Department.");
        Department department = departmentService.getDepartmentByName(departmentName);
        List<Transaction> transactions = department.getTransactions();
        return ResponseEntity.ok(transactions.stream().map(TransactionMapper.INSTANCE::serialize).toList());
    }
}
