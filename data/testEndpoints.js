export async function testEndpoints() {
    const url = "http://localhost:5000/departments";
    try {
        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        console.log(`Response completed with status code ${response.status}`);
        const responseData = await response.json();
        console.log(responseData);
    } catch (error) {
        console.error("Error occurred:", error);
    }
}
