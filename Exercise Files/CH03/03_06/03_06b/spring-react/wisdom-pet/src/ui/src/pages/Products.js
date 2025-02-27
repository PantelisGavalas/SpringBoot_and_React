import React, { useLayoutEffect, useState } from "react";
import { getCurrency } from "../Utils";

const Products = () => {
    const [products, setProducts] = useState([]);
    const [vendors, setVendors] = useState(new Map());
    // function to add key-value pair to the vendors map
    const add = (key, value) => {
        setVendors(prev => new Map([...prev, [key, value]]));
    }

    useLayoutEffect(() => {
        const getProducts = async () => {
            const res = await fetch('api/products');
            const products = await res.json();
            setProducts(products);
        }
        const getVendors = async () => {
            const res = await fetch('api/vendors');
            const vendors = await res.json();
            // for each vendor, add to the map key:id value:vendor
            // that way by the vendor id we can access the vendor and their name
            vendors.map(vendor => {
                const {
                    vendorId, 
                    name,
                    contact,
                    emailAddress,
                    phoneNumber,
                    adress,
                } = vendor;
                add(vendorId, vendor);
            })
        }
        getProducts().catch(e => {
            console.log("error fetching products: " + e);
        })
        getVendors().catch(e => {
            console.log("error fetching vendors: " + e)
        })
    }, [])

    return (
        <table>
            <thead>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Vendor</th>
            </thead>
            <tbody>
                {products.map(product => {
                    const {
                        productId,
                        name,
                        price,
                        vendorId
                    } = product;

                    return (
                        <tr key={productId}>
                            <td>{productId}</td>
                            <td>{name}</td>
                            <td>{getCurrency(price)}</td>
                            <td>{vendors.get(vendorId).name}</td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}

export default Products