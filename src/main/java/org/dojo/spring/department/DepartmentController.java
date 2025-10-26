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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService _departmentService;
    
    public DepartmentController(DepartmentService departmentService) { this._departmentService = departmentService; }

    @PostMapping(value = DepartmentEndpoints.CREATE_DEPARTMENT)
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        logger.info("Creating Department object.");
        var department = DepartmentMapper.INSTANCE.deserialize(departmentDto);
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(_departmentService.addDepartment(department)));
    }

    @GetMapping(value = DepartmentEndpoints.GET_DEPARTMENTS)
    public ResponseEntity<List<DepartmentProjection>> getDepartments() {
        logger.info("Getting all Department objects.");
        return ResponseEntity.ok(_departmentService.getAllDepartments());
    }

    @GetMapping(value = "/departments", params = {"transactions"})
    public ResponseEntity<List<DepartmentDto>> getDepartments(@RequestParam Boolean transactions) {
        logger.info("Getting all Department objects with Transactions included.");
        if (transactions) {
            List<Department> departments = _departmentService.getAllDepartmentsWithTransactions();
            return ResponseEntity.ok(departments.stream().map(DepartmentMapper.INSTANCE::serialize).toList());
        } else {
            List<DepartmentProjection> departments = _departmentService.getAllDepartments();
            return ResponseEntity.ok(departments.stream().map(DepartmentMapper.INSTANCE::serialize).toList());
        }
    }

    @GetMapping(value = DepartmentEndpoints.GET_DEPARTMENT, params = { "id" })
    public ResponseEntity<DepartmentDto> getDepartmentById(@RequestParam(name = "id") Long departmentId) throws ResourceNotFoundException {
        logger.info("Getting Department object by id.");
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(_departmentService.getDepartmentById(departmentId)));
    }

    @GetMapping(value = DepartmentEndpoints.GET_DEPARTMENT, params = { "name", "getTransactions" })
    public ResponseEntity<DepartmentDto> getDepartmentByName(@RequestParam(name = "name") String departmentName, @RequestParam boolean getTransactions) throws ResourceNotFoundException {
        logger.info("Getting Department object by name.");
        if (!getTransactions) return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(_departmentService.getDepartmentByNameWithoutTransactions(departmentName)));
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(_departmentService.getDepartmentByName(departmentName)));
    }

    @PutMapping(value = DepartmentEndpoints.UPDATE_DEPARTMENT)
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody @Valid DepartmentDto departmentDto) throws ResourceNotFoundException {
        logger.info("Updating Department object.");
        var department = DepartmentMapper.INSTANCE.deserialize(departmentDto);
        return ResponseEntity.ok(DepartmentMapper.INSTANCE.serialize(_departmentService.updateDepartment(departmentId, department)));
    }

    @DeleteMapping(value = DepartmentEndpoints.DELETE_DEPARTMENT)
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) throws ResourceNotFoundException {
        logger.info("Deleting Department object.");
        if (_departmentService.deleteDepartmentById(departmentId)) return ResponseEntity.ok().body(String.format("Department Id #%d deleted!", departmentId));
        else throw new ResourceNotFoundException(String.format("Department Id #%d is not deleted!", departmentId));
    }

    @PostMapping(DepartmentEndpoints.ADD_TRANSACTION)
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody @Valid TransactionDto transactionDto) throws ResourceNotFoundException {
        logger.info("Adding Transaction to Department object.");
        Department department = _departmentService.getDepartmentByName(transactionDto.departmentName());
        Transaction transaction = TransactionMapper.INSTANCE.deserialize(transactionDto);
        Transaction txn = _departmentService.addTransaction(department, transaction);
        return ResponseEntity.ok(TransactionMapper.INSTANCE.serialize(txn));
    }

    @GetMapping(value = DepartmentEndpoints.GET_TRANSACTIONS, params = { "departmentName" })
    public ResponseEntity<List<TransactionDto>> getTransactions(@RequestParam String departmentName) throws ResourceNotFoundException {
        logger.info("Getting all Transactions for this Department.");
        Department department = _departmentService.getDepartmentByName(departmentName);
        List<Transaction> transactions = department.getTransactions();
        return ResponseEntity.ok(transactions.stream().map(TransactionMapper.INSTANCE::serialize).toList());
    }
}
