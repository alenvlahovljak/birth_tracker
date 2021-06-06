# Roles scripts

INSERT INTO roles (name)
VALUES ('admin');

INSERT INTO roles (name)
VALUES ('manager');

INSERT INTO roles (name)
VALUES ('user');

# Admin scripts

INSERT INTO admin (username, password, role)
VALUES ('alen', 'alen', 1);

select *
from `admin`
where username = 'admin';

# Manager scripts

INSERT INTO manager (username, password, first_name, last_name, avatar_url, role)
VALUES ('demo', 'demo', 'Adam', 'Smith',
        'https://images.unsplash.com/photo-1547425260-76bcadfb4f2c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80',
        2);

INSERT INTO manager (username, first_name, last_name, avatar_url, role)
VALUES ('Jack@#', 'Jack', 'Stone',
        'https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80',
        2);

INSERT INTO manager (username, first_name, last_name, avatar_url, role)
VALUES ('an__A', 'Anna', 'Johnson',
        'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80',
        2);

# User scripts

INSERT INTO user (username, password, first_name, last_name, avatar_url)
    VALUE ('user', 'user', 'User', 'User', '');

# Organization scripts

INSERT INTO organization (name, abbreviation, description, thumbnail_url, rating, manager_id)
VALUES ('Smith Brothers Ltd.', 'S&B',
        'Smith Brothers Ltd. brings fun and value to Events while helping customers Throw a great party..',
        'https://images.unsplash.com/photo-1551150441-3f3828204ef0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80',
        3.2, 11);

INSERT INTO organization (name, abbreviation, description, thumbnail_url, rating, manager_id)
VALUES ('Birthday SURPRISE', 'B_Surprise',
        'Birthday SURPRISE brings reliable and unforgettable organization for Birthday parties while helping customers. Our motto is: ''Don''t waste your time on event management, call us!.''',
        'https://images.unsplash.com/photo-1557053964-937650b63311?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1159&q=80',
        4.3, 5);

INSERT INTO organization (name, abbreviation, description, thumbnail_url, rating, manager_id)
VALUES ('ThrowIT', 'THROW',
        'ThrowIT brings dignified and reliable events while helping customers we''re trying to mark unforgettable moments.',
        'https://images.unsplash.com/photo-1551150441-3f3828204ef0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80',
        4.8, 7);

SELECT * FROM organization where manager_id=13;
# Party scripts

ALTER TABLE party
    ADD CONSTRAINT min_num_of_participants CHECK (num_of_participants BETWEEN 0 AND 100);

INSERT INTO party (name, description, thumbnail_url, num_of_participants, max_participants, price, organization_id)
VALUES ('John''s party',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'https://images.unsplash.com/photo-1492684223066-81342ee5ff30?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80',
        24, 25, 80, 20);

INSERT INTO party (name, description, thumbnail_url, num_of_participants, max_participants, organization_id)
VALUES ('Amy''s magic night',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'https://images.unsplash.com/photo-1496843916299-590492c751f4?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80',
        10, 28, 3);


INSERT INTO party (name, description, thumbnail_url, num_of_participants, max_participants, organization_id)
VALUES ('Smallest party ever',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
        'https://images.unsplash.com/photo-1492684223066-81342ee5ff30?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80',
        0, 4, 7);

SELECT *
FROM party;


SELECT p.*, org.abbreviation as organization_abbreviation, org.manager_id, o.user_id, o.id as order_id, o.rating, o.has_discount
FROM party AS p
         LEFT JOIN organization as org ON (p.organization_id = org.id)
         LEFT JOIN `order` as o ON (p.id = o.party_id)
WHERE p.id = 45;

UPDATE party
SET num_of_participants = num_of_participants + 1
WHERE id = 41;

UPDATE party
SET num_of_participants = num_of_participants - 1
WHERE id = 41;

# Order scripts

INSERT INTO `order` (user_id, party_id)
    VALUE (3, 41);

SELECT *
FROM `order`;

SELECT COUNT(user_id) * 2.8 as discount
FROM `order`
WHERE user_id = 5;


# Triggers

CREATE TRIGGER setFreeSpots
    BEFORE INSERT
    ON party
    FOR EACH ROW
BEGIN
    IF NEW.num_of_participants = NEW.max_participants THEN
        SET NEW.has_free_spots = false;
end if;
end;

