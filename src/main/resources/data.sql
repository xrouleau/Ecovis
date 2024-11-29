INSERT IGNORE INTO nouvelles
VALUES
    (1,  'Bla bla bla',  '2024-06-21 12:12:12.123456', 'Réchauffement climatique'),
    (2,  'Plein de mots',  '2024-06-21 12:12:12.123456', 'Politique écologique'),
    (3,  'Un gros speech',  '2024-06-21 12:12:12.123456', 'Papier de toilette réutilisable'),
    (4,  'Bla bla bla',  '2024-06-21 12:12:12.123456', 'Réchauffement climatique'),
    (5,  'Plein de mots',  '2024-06-21 12:12:12.123456', 'Politique écologique'),
    (6,  'Un gros speech',  '2024-06-21 12:12:12.123456', 'Papier de toilette réutilisable');

INSERT IGNORE INTO roles
VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_COMM'),
    (3, 'ROLE_USER');

INSERT IGNORE INTO utilisateurs
VALUES
    (1, 'jburton@gmail.com', 'Burton', 'test', 'Jack', 3),
    (2, 'xrouleau@gmail.com', 'Rouleau', 'test', 'Xavier', 1),
    (3, 'bdube@gmail.com', 'Dubé', 'test', 'Béatrice', 2);

INSERT IGNORE INTO utilisateurs_nouvelles
VALUES
    (3, 2),
    (3, 1);

