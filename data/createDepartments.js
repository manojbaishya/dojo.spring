import { faker } from '@faker-js/faker';

export async function createDepartments() {

    const departments = [
        {
            name: "Electrical",
            code: `EL-${faker.string.alpha(6)}`,
            address: faker.location.streetAddress()
        },
        {
            name: "Maintenance",
            code: `MN-${faker.string.alpha(6)}`,
            address: faker.location.streetAddress()
        },
        {
            name: "Production",
            code: `PD-${faker.string.alpha(6)}`,
            address: faker.location.streetAddress()
        },
        {
            name: "Accounts",
            code: `AC-${faker.string.alpha(6)}`,
            address: faker.location.streetAddress()
        },
        {
            name: "Marketing",
            code: `MK-${faker.string.alpha(6)}`,
            address: faker.location.streetAddress()
        },
        {
            name: "Operations",
            code: `OP-${faker.string.alpha(6)}`,
            address: faker.location.streetAddress()
        }
    ];

    const url = "http://localhost:5000/department";
    for (const dept of departments) {

        try {
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": 'application/json',
                },
                body: JSON.stringify(dept),
            });

            console.log(`Response status code: ${response.status}`);
            const responseData = await response.json();
            console.log(responseData);
        } catch (error) {
            console.error("Error:", error.message);
        }
    }
}
