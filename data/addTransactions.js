import { getAllDepartments } from "./getAllDepartments.js";

export async function addTransactions(cfg) {
    const url = `${cfg.server.protocol}://${cfg.server.hostname}${cfg.server.path}/${cfg.paths.addTransaction.name}`;

    const departments = await getAllDepartments(cfg);
    console.log(departments);

    const departmentIterations = 100;
    for (let i = 0; i < departmentIterations; i++) {
        const dept = departments[Math.floor(Math.random() * departments.length)];
        console.log(`Department is ${dept.name}`);
        const transactionIterations = 2000;
        for (let j = 0; j < transactionIterations; j++) {
            const min = -2000;
            const max = 2000;
            const amount = Math.floor(Math.random() * (max - min + 1)) + min;

            let transactionObject = {
                amount: amount,
                departmentName: dept.name
            };
            console.log(`Transaction amount: ${transactionObject.amount}`);

            try {
                const response = await fetch(url, {
                    method: `${cfg.paths.addTransaction.method}`,
                    headers: {
                        "Content-Type": 'application/json',
                    },
                    body: JSON.stringify(transactionObject),
                });

                console.log(`Response status code: ${response.status}`);
                const responseData = await response.json();
                console.log(responseData);
            } catch (error) {
                console.error("Error:", error.message);
            }
        }

    }
}
