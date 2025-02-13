import { useState, useEffect } from "react";

function Balance() {
    const baseUrl = process.env.REACT_APP_BASE_URL || "http://localhost:8080"

    const [balance, setBalance] = useState(0.0)

    useEffect(() => {
        fetch(baseUrl + "/balance")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erreur lors de la récupération de la balance")
                }
                return response.json()
            })
            .then(data => {
                setBalance(data)
            })
            .catch(error => console.error("Erreur : ", error))
    })

    return (
        <>
            <h1>Balance : { balance }</h1>
        </>
    )

}

export default Balance