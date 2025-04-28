import { faker } from '@faker-js/faker';
import staticData from './staticData.json' with { type: 'json' };

export async function createDepartments(cfg) {

    const departments = staticData.departments.map(dept => ({
        name: dept.name,
        code: `${dept.code}-${faker.string.alpha(6)}`,
        address: faker.location.streetAddress()
    }));

    const url = `${cfg.server.protocol}://${cfg.server.hostname}${cfg.server.path}/${cfg.paths.createDepartments.name}`;
    for (const dept of departments) {
        try {
            const response = await fetch(url, {
                method: `${cfg.paths.createDepartments.method}`,
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
