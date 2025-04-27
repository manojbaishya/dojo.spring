package org.dojo.spring.department;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DepartmentMapperTests {
    private final String expectedName = "SampleName";
    private final String expectedAddress = "SampleAddress";
    private final String expectedCode = "SampleCode";

    @Test
    void testDeserialize() {
        var actualDto = new DepartmentDto(expectedName, expectedAddress, expectedCode, null);
        var actualEntity = DepartmentMapper.INSTANCE.deserialize(actualDto);

        assertThat(actualEntity.getName()).isEqualTo(expectedName);
        assertThat(actualEntity.getAddress()).isEqualTo(expectedAddress);
        assertThat(actualEntity.getCode()).isEqualTo(expectedCode);
    }

    @Test
    void testSerialize() {
        var actualEntity = new Department(529051L, expectedName, expectedAddress, expectedCode);
        var actualDto = DepartmentMapper.INSTANCE.serialize(actualEntity);

        assertThat(actualDto.name()).isEqualTo(expectedName);
        assertThat(actualDto.address()).isEqualTo(expectedAddress);
        assertThat(actualDto.code()).isEqualTo(expectedCode);

    }
}
