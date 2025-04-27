import { faker } from '@faker-js/faker';

const iterations = 1000;

export async function createDepartmentData() {

    const departments = ["Electrical", "Maintenance", "Production", "Accounts", "Marketing", "Operations"];

    const url = "http://localhost:5000/department";
    for (let i = 0; i < iterations; i++) {
        const payload = {
            departmentName: departments[Math.floor(Math.random() * departments.length)],
            departmentAddress: faker.location.streetAddress(),
            departmentCode: faker.string.alpha(6),
        };

        try {
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": 'application/json',
                },
                body: JSON.stringify(payload),
            });

            console.log(`Response status code: ${response.status}`);
            const responseData = await response.json();
            console.log(responseData);
        } catch (error) {
            console.error("Error:", error.message);
        }
    }
}
