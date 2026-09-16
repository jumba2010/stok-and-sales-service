CREATE TABLE client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    picture VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    sucursal_id BIGINT
);

CREATE TABLE sucursal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    taxId VARCHAR(255),
    address VARCHAR(255),
    contact VARCHAR(255),
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    sucursal_id BIGINT
);

CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255),
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    lang VARCHAR(255) NOT NULL,
    parent_id BIGINT,
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    sucursal_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES category(id)
);

CREATE TABLE supplier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    sucursal_id BIGINT
);

CREATE TABLE tax (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    description VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    value DOUBLE NOT NULL,
    sucursal_id BIGINT NOT NULL,
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE unit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    lang VARCHAR(255) NOT NULL,
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    sucursal_id BIGINT
);

CREATE TABLE promotion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    percentage INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    sucursal_id BIGINT NOT NULL,
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    can_be_sold BOOLEAN NOT NULL,
    alert_quantity INT,
    available_quantity INT NOT NULL,
    package_count INT NOT NULL,
    purchase_price DECIMAL(19,4) NOT NULL,
    sale_price DECIMAL(19,4) NOT NULL,
    current_price DECIMAL(19,4) NOT NULL,
    promotional_price DOUBLE,
    sucursal_id BIGINT NOT NULL,
    unit_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    supplier_id BIGINT,
    promotion_id BIGINT,
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id),
    FOREIGN KEY (unit_id) REFERENCES unit(id),
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (promotion_id) REFERENCES promotion(id)
);

CREATE TABLE stock_entry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    warehouse_id BIGINT NOT NULL,
    supplier_id BIGINT,
    reference VARCHAR(255),
    received_date DATETIME NOT NULL,
    notes TEXT,
    product_id BIGINT NOT NULL,
    received_quantity INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE stock_entry_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    stock_entry_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    purchase_price DOUBLE NOT NULL,
    new_sale_price DOUBLE,
    FOREIGN KEY (stock_entry_id) REFERENCES stock_entry(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sucursal_id BIGINT NOT NULL,
    warehouse_id BIGINT NOT NULL,
    status INT NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE inventory_adjustment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventory_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    system_quantity INT NOT NULL,
    physical_quantity INT NOT NULL,
    discrepancy INT NOT NULL,
    reason TEXT,
    FOREIGN KEY (inventory_id) REFERENCES inventory(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    available_quantity INT,
    quantity INT NOT NULL,
    stock_type VARCHAR(255) NOT NULL,
    purchase_price DECIMAL(10,2),
    sell_price DECIMAL(10,2),
    sucursal_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    created_by BIGINT NOT NULL,
    updated_by BIGINT,
    activated_by BIGINT NOT NULL,
    state INT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    activated_at DATETIME NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);


CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    picture VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    profile_id BIGINT NOT NULL,
    sucursal_id BIGINT NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE login_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    duration INT,
    ip VARCHAR(255),
    device VARCHAR(255) NOT NULL,
    device_type VARCHAR(255) NOT NULL,
    location TEXT,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE profile_transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    transaction_id BIGINT NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    FOREIGN KEY (transaction_id) REFERENCES transaction(id)
);


CREATE TABLE sale (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total_amount DECIMAL(19,4),
    total_items INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    end_date TIMESTAMP,
    sucursal_id BIGINT NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE sale_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sale_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sale(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
