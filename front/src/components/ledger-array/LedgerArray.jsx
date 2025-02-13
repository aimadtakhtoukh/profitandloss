import './LedgerArray.css'
import { useState, useEffect } from "react";

function LedgerArray() {
    const baseUrl = process.env.REACT_APP_BASE_URL || "http://localhost:8080"

    const [ledgers, setLedgers] = useState([])

    useEffect(() => {
        fetch(baseUrl + "/profitAndLoss")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erreur lors de la récupération des profitAndLoss")
                }
                return response.json()
            })
            .then(data => {
                setLedgers(data)
            })
            .catch(error => console.error("Erreur : ", error))
    }, []);

    return (
        <>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Balance</th>
                        <th>Crédit</th>
                        <th>Débit</th>
                    </tr>
                </thead>
                <tbody>
                {
                    ledgers.map((ledger, index) => (
                        <tr key={index}>
                            <td> {ledger.month} </td>
                            <td> {ledger.balance} </td>
                            <td> {ledger.credit} </td>
                            <td> {ledger.debit} </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </>
    )


}

export default LedgerArray