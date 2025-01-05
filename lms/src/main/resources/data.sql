-- Clear existing data while maintaining referential integrity
TRUNCATE TABLE calls CASCADE;
TRUNCATE TABLE purchased_products CASCADE;
TRUNCATE TABLE orders CASCADE;
TRUNCATE TABLE contacts CASCADE;
TRUNCATE TABLE restaurants CASCADE;
TRUNCATE TABLE products CASCADE;

-- Insert Products
INSERT INTO products (id, name, description, inventory, unit_price) VALUES
('FOOD001', 'Premium Rice', 'High-quality Basmati rice, 25kg bag', 500, 4999),
('FOOD002', 'Cooking Oil', 'Refined vegetable oil, 15L container', 300, 3999),
('FOOD003', 'Mixed Spices', 'Restaurant grade spice mix, 1kg pack', 1000, 1999),
('FOOD004', 'Wheat Flour', 'All-purpose flour, 50kg bag', 400, 5999),
('EQUIP001', 'Commercial Mixer', 'Heavy duty stand mixer, 20L capacity', 50, 89999),
('EQUIP002', 'Deep Fryer', 'Double basket deep fryer, 16L', 30, 149999);

-- Insert Restaurants
INSERT INTO restaurants (id, kam_id, name, address, stars_rating, status, frequency, last_call_time) VALUES
('REST001', 'KAM001', 'Golden Dragon', '123 Main St, City Center', 4, 'ACTIVE', 'WEEKLY', '2025-01-01T10:00:00Z'),
('REST002', 'KAM001', 'Spice Route', '456 Market Ave, Downtown', 5, 'ACTIVE', 'MONTHLY', '2025-01-02T11:30:00Z'),
('REST003', 'KAM002', 'Blue Ocean', '789 Harbor Rd, Seaside', 3, 'CONTACTED', 'WEEKLY', '2025-01-03T14:15:00Z'),
('REST004', 'KAM002', 'Green Garden', '321 Park Lane, Uptown', 4, 'ORDER_PLACED', 'DAILY', '2025-01-04T19:45:00Z'),
('REST005', 'KAM003', 'Royal Feast', '654 Queen St, Downtown', 5, 'ACTIVE', 'MONTHLY', '2025-01-05T16:20:00Z');

-- Insert Contacts
INSERT INTO contacts (id, name, role, phone, email, rest_id) VALUES
('CONT001', 'John Smith', 'MANAGER', '+1234567890', 'john@goldendragon.com', 'REST001'),
('CONT002', 'Mary Johnson', 'ADMIN', '+1234567891', 'mary@spiceroute.com', 'REST002'),
('CONT003', 'David Lee', 'MANAGER', '+1234567892', 'david@blueocean.com', 'REST003'),
('CONT004', 'Sarah Wilson', 'ADMIN', '+1234567893', 'sarah@greengarden.com', 'REST004'),
('CONT005', 'James Brown', 'MANAGER', '+1234567894', 'james@royalfeast.com', 'REST005');

-- Insert Orders
INSERT INTO orders (id, kam_id, rest_buyer_id, total_price, order_date, restaurant_id) VALUES
('ORD001', 'KAM001', 'CONT001', 149985, '2025-01-01T12:00:00Z', 'REST001'),
('ORD002', 'KAM001', 'CONT002', 89997, '2025-01-02T13:30:00Z', 'REST002'),
('ORD003', 'KAM002', 'CONT003', 199980, '2025-01-03T15:45:00Z', 'REST003'),
('ORD004', 'KAM002', 'CONT004', 299970, '2025-01-04T10:15:00Z', 'REST004'),
('ORD005', 'KAM003', 'CONT005', 449955, '2025-01-05T17:00:00Z', 'REST005');

-- Insert Purchased Products
INSERT INTO purchased_products (id, order_id, product_id, quantity) VALUES
('PP001', 'ORD001', 'FOOD001', 30),
('PP002', 'ORD001', 'FOOD002', 20),
('PP003', 'ORD002', 'FOOD003', 45),
('PP004', 'ORD003', 'FOOD004', 40),
('PP005', 'ORD004', 'EQUIP001', 2),
('PP006', 'ORD005', 'EQUIP002', 3);

-- Insert Calls
INSERT INTO calls (id, restaurant_id, kam_id, contact_id, order_id, call_time) VALUES
('CALL001', 'REST001', 'KAM001', 'CONT001', 'ORD001', '2025-01-01T10:00:00Z'),
('CALL002', 'REST002', 'KAM001', 'CONT002', 'ORD002', '2025-01-02T11:30:00Z'),
('CALL003', 'REST003', 'KAM002', 'CONT003', 'ORD003', '2025-01-03T14:15:00Z'),
('CALL004', 'REST004', 'KAM002', 'CONT004', 'ORD004', '2025-01-04T09:45:00Z'),
('CALL005', 'REST005', 'KAM003', 'CONT005', 'ORD005', '2025-01-05T16:20:00Z');