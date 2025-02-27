import React, { useLayoutEffect, useState } from "react";

const Products = () => {
    const [products, setProducts] = useState([]);
    const [vendors, setVendors] = useState([]);

    useLayoutEffect(() => {
        const getProducts = async () => {
            const res = await fetch('api/products');
            const products = await res.json();
            setProducts(products);
        }
        const getVendors = async () => {
            const res = await fetch('api/vendors');
            const vendors = await res.json();
            setVendors(vendors);
        }
        getProducts().catch(e => {
            console.log("error fetching products: " + e);
        })
        getVendors().catch(e => {
            console.log("error fetching vendors: " + e)
        })
    })

    return (
        <table>
            <thead>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Vendor ID</th>
                <th>Vendor Name</th>
            </thead>
            <tbody>
                {products.map(product => {
                    const {
                        productId,
                        name,
                        price,
                        vendorId
                    } = product;

                    var vendorName = "";
                    vendors.forEach(vendor => {
                        if (vendor.vendorId === vendorId) {
                            vendorName = vendor.name;
                        }
                    })

                    return (
                        <tr key={productId}>
                            <td>{productId}</td>
                            <td>{name}</td>
                            <td>{price}</td>
                            <td>{vendorId}</td>
                            <td>{vendorName}</td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    )
}

export default Products