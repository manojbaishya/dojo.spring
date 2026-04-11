import configuration from './configuration.json' with { type: 'json' };

import { createDepartments } from "./createDepartments.js";
import { getAllDepartments } from "./getAllDepartments.js";
import { addTransactions } from "./addTransactions.js";

import logger from './logger.js';

logger.info('Creating departments..');
await createDepartments(configuration);
logger.info('Get all departments..');
const departments = await getAllDepartments(configuration);
console.log(departments);

logger.info('Add transactions to departments..');
await addTransactions(configuration);
