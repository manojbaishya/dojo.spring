import { createDepartmentData } from "./createDepartmentData.js";
import { testEndpoints } from "./testEndpoints.js";

await createDepartmentData();
await testEndpoints();
