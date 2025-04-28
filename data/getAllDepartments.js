export async function getAllDepartments(cfg) {
    const url = `${cfg.server.protocol}://${cfg.server.hostname}${cfg.server.path}/${cfg.paths.getAllDepartments.name}`;
    try {
        const response = await fetch(url, {
            method: `${cfg.paths.getAllDepartments.method}`,
            headers: {
                "Content-Type": "application/json"
            }
        });

        console.log(`Response completed with status code ${response.status}`);
        return response.json();
    } catch (error) {
        console.error("Error occurred:", error);
        return null;
    }
}
