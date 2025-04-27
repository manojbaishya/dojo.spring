import { createDepartments } from "./createDepartments.js";
import { testEndpoints } from "./testEndpoints.js";

await createDepartments();
await testEndpoints();
