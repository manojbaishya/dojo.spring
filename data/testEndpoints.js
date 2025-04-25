export async function testEndpoints() {
    const url = "http://localhost:5000/department";
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                departmentName: "dummy",
                departmentAddress: "dummy",
                departmentCode: "dummy"
            })
        });

        console.log(`Response completed with status code ${response.status}`);

        const responseData = await response.json();
        console.log(responseData);
    } catch (error) {
        console.error("Error occurred:", error);
    }
}
