CREATE DATABASE product_shop;

USE product_shop;

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE enhancements (
    enhancement_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_enhancements (
    product_id INT,
    enhancement_id INT,
    PRIMARY KEY (product_id, enhancement_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (enhancement_id) REFERENCES enhancements(enhancement_id)
);

CREATE TABLE product_categories (
    product_id INT,
    category_id INT,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- Inserts

-- Insert categories
INSERT INTO categories (name, description) VALUES
('Elektronika', 'Veškerá elektronická zařízení pro domácnost i kancelář'),
('Oblečení', 'Pánské a dámské oblečení všech stylů'),
('Nábytek', 'Vybavení do domácnosti'),
('Sportovní vybavení', 'Vše pro aktivní životní styl'),
('Zahrada', 'Produkty pro vaši zahradu'),
('Potraviny', 'Kvalitní potraviny od lokálních dodavatelů');

-- Insert products
INSERT INTO products (name, description, price) VALUES
('Notebook Dell XPS 13', 'Výkonný notebook s procesorem Intel i7, 16GB RAM a 512GB SSD', 36990.00),
('Chytrý telefon Samsung Galaxy S23', 'Nejnovější model s 6.2" AMOLED displejem a 256GB paměti', 23490.00),
('Mikina Nike Performance', 'Pánská sportovní mikina s kapucí, velikost L', 1290.00),
('Dámské běžecké boty Adidas', 'Pohodlné boty vhodné pro běh na dlouhé vzdálenosti', 2490.00),
('Konferenční stolek IKEA LACK', 'Moderní stolek do obývacího pokoje, barva bílá', 990.00),
('Pohovka IKEA KIVIK', 'Třímístná pohovka s potahem z odolné látky', 12990.00),
('Horské kolo Author Impulse', 'Kvalitní horské kolo s hliníkovým rámem a 27 převody', 18990.00),
('Elektrická sekačka Gardena', 'Výkonná sekačka na trávu s košem o objemu 40l', 5990.00),
('Bio mléko Olma', 'Čerstvé bio mléko z regionálních farem, 1l', 39.90),
('Domácí chléb', 'Čerstvě upečený kváskový chléb z lokální pekárny', 89.00);

-- Insert enhancements
INSERT INTO enhancements (name, description, price) VALUES
('Prodloužená záruka', 'Prodloužení záruky o 24 měsíců', 1990.00),
('Expresní doručení', 'Doručení do 24 hodin kamkoliv v ČR', 299.00),
('Dárkové balení', 'Elegantní balení vhodné jako dárek', 99.00),
('Montáž', 'Odborná montáž zakoupeného produktu', 499.00),
('Pojištění proti rozbití', 'Pojištění proti náhodnému poškození na 12 měsíců', 990.00),
('Gravírování', 'Možnost vyrýt personalizovaný text nebo obrázek', 399.00),
('Příslušenství navíc', 'Dodatečné příslušenství k vybranému produktu', 799.00),
('VIP servis', 'Přednostní vyřízení reklamací a servisních úkonů', 599.00);

-- Link products to categories
INSERT INTO product_categories (product_id, category_id) VALUES
(1, 1), -- Notebook Dell XPS 13 -> Elektronika
(2, 1), -- Samsung Galaxy S23 -> Elektronika
(3, 2), -- Mikina Nike -> Oblečení
(4, 2), -- Boty Adidas -> Oblečení
(4, 4), -- Boty Adidas -> Sportovní vybavení
(5, 3), -- Stolek IKEA -> Nábytek
(6, 3), -- Pohovka IKEA -> Nábytek
(7, 4), -- Horské kolo -> Sportovní vybavení
(8, 5), -- Sekačka -> Zahrada
(9, 6), -- Bio mléko -> Potraviny
(10, 6); -- Chléb -> Potraviny

-- Link products to enhancements
INSERT INTO product_enhancements (product_id, enhancement_id) VALUES
(1, 1), -- Notebook Dell XPS 13 -> Prodloužená záruka
(1, 2), -- Notebook Dell XPS 13 -> Expresní doručení
(1, 5), -- Notebook Dell XPS 13 -> Pojištění proti rozbití
(2, 1), -- Samsung Galaxy S23 -> Prodloužená záruka
(2, 3), -- Samsung Galaxy S23 -> Dárkové balení
(2, 5), -- Samsung Galaxy S23 -> Pojištění proti rozbití
(2, 6), -- Samsung Galaxy S23 -> Gravírování
(3, 3), -- Mikina Nike -> Dárkové balení
(4, 3), -- Boty Adidas -> Dárkové balení
(5, 2), -- Stolek IKEA -> Expresní doručení
(5, 4), -- Stolek IKEA -> Montáž
(6, 2), -- Pohovka IKEA -> Expresní doručení
(6, 4), -- Pohovka IKEA -> Montáž
(7, 1), -- Horské kolo -> Prodloužená záruka
(7, 7), -- Horské kolo -> Příslušenství navíc
(8, 1), -- Sekačka -> Prodloužená záruka
(8, 4), -- Sekačka -> Montáž
(9, 2), -- Bio mléko -> Expresní doručení
(10, 2); -- Chléb -> Expresní doručení