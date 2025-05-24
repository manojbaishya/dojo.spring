package org.dojo.spring.department;

import java.io.Serializable;

public class DepartmentProjectionDto implements DepartmentProjection, Serializable {

    private Long id;
    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    private String name;
    @Override
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    private String code;
    @Override
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    private String address;
    @Override
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Constructors
    public DepartmentProjectionDto() { }

    public DepartmentProjectionDto(Long id, String name, String code, String address) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.address = address;
    }
}
