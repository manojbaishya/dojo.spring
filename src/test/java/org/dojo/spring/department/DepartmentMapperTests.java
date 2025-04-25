package org.dojo.spring.department;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DepartmentMapperTests {
    private final String expectedName = "SampleName";
    private final String expectedAddress = "SampleAddress";
    private final String expectedCode = "SampleCode";

    @Test
    void testDeserialize() {
        var actualDto = new DepartmentDto(expectedName, expectedAddress, expectedCode);
        var actualEntity = DepartmentMapper.INSTANCE.deserialize(actualDto);

        assertThat(actualEntity.getDepartmentName()).isEqualTo(expectedName);
        assertThat(actualEntity.getDepartmentAddress()).isEqualTo(expectedAddress);
        assertThat(actualEntity.getDepartmentCode()).isEqualTo(expectedCode);
    }

    @Test
    void testSerialize() {
        var actualEntity = new Department(529051L, expectedName, expectedAddress, expectedCode);
        var actualDto = DepartmentMapper.INSTANCE.serialize(actualEntity);

        assertThat(actualDto.departmentName()).isEqualTo(expectedName);
        assertThat(actualDto.departmentAddress()).isEqualTo(expectedAddress);
        assertThat(actualDto.departmentCode()).isEqualTo(expectedCode);

    }
}
