import configuration from './configuration.json' with { type: 'json' };

import { createDepartments } from "./createDepartments.js";
import { getAllDepartments } from "./getAllDepartments.js";
import { addTransactions } from "./addTransactions.js";

// await createDepartments(configuration);
const departments = await getAllDepartments(configuration);
console.log(departments);
// await addTransactions(configuration);
