async function loadProducts() {

    const response = await fetch("http://localhost:8080/api/products");

    const products = await response.json();

    const container = document.getElementById("products");

    products.forEach(product => {

        container.innerHTML += `
            <div class="card">
                <h2>${product.name}</h2>
                <p>₹${product.price}</p>
            </div>
        `;
    });
}

loadProducts();