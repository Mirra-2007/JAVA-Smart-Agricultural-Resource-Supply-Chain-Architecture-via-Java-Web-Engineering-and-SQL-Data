const FARM_API_BASE = (function () {
    let base = "http://localhost:8080";
    if (typeof window !== "undefined" && typeof window.FARM_API_BASE_URL === "string") {
        base = window.FARM_API_BASE_URL;
    }
    return base.replace(/\/+$/, "");
})();

async function farmApiRequest(path) {
    let response;
    try {
        response = await fetch(FARM_API_BASE + path, {
            method: "GET",
            headers: { "Accept": "application/json" }
        });
    } catch (networkError) {
        throw new Error(
            "Cannot reach the Farm Map server at " + FARM_API_BASE +
            ". Please make sure FarmMapServer is running, then try again."
        );
    }

    if (!response.ok) {
        let serverMessage = "";
        try {
            const errorBody = await response.json();
            if (errorBody && errorBody.error) {
                serverMessage = errorBody.error;
            }
        } catch (ignored) { }

        if (response.status === 404) {
            throw new Error(serverMessage || "No farm map data is available for this farmer yet.");
        }
        throw new Error(
            serverMessage || "Failed to load farm map data (HTTP " + response.status + "). Please try again."
        );
    }

    try {
        return await response.json();
    } catch (parseError) {
        throw new Error("Received an invalid response from the Farm Map server. Please try again.");
    }
}

async function getFarmMap(farmerId) {
    return farmApiRequest("/api/farms/" + encodeURIComponent(farmerId) + "/map");
}

async function getFarmDetails(farmerId) {
    return farmApiRequest("/api/farms/" + encodeURIComponent(farmerId));
}

async function getCanals(farmerId) {
    return farmApiRequest("/api/farms/" + encodeURIComponent(farmerId) + "/canals");
}

async function listFarms() {
    return farmApiRequest("/api/farms");
}

function farmerNameToId(farmerName) {
    if (!farmerName) {
        return "";
    }
    return String(farmerName).trim().toUpperCase().replace(/\s+/g, "_");
}
