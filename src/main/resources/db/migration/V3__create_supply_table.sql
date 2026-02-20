CREATE TABLE supply (
    product_id BIGINT NOT NULL,
    raw_material_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (product_id, raw_material_id),
    CONSTRAINT fk_supply_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_supply_raw_material FOREIGN KEY (raw_material_id) REFERENCES raw_material(id)
);
