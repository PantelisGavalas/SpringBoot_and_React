import React, { useLayoutEffect, useState } from "react";

const Vendors = () => {
    const [vendors, setVendors] = useState([]);

    useLayoutEffect(() => {
        const getVendors = async () => {
            const res = await fetch('api/vendors');
            const vendors = await res.json();
            setVendors(vendors);
        }
        getVendors().catch(e => {
            console.log("error fetching vendors: " + e);
        })
    })

    return (
        <table>
            <thead>
                <th>ID</th>
                <th>Name</th>
                <th>Contact</th>
                <th>Phone Number</th>
                <th>Email Address</th>
                <th>Address</th>
            </thead>
            <tbody>
                {vendors.map(vendor => {
                    const {
                        vendorId,
                        name,
                        contact,
                        phoneNumber,
                        emailAddress,
                        address
                    } = vendor;

                    return (
                        <tr key={vendorId}>
                            <td>{vendorId}</td>
                            <td>{name}</td>
                            <td>{contact}</td>
                            <td>{phoneNumber}</td>
                            <td>{emailAddress}</td>
                            <td>{address}</td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}

export default Vendors;