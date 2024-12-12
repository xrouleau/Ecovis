INSERT IGNORE INTO nouvelles
VALUES
    (1,  'La biodiversité est essentielle à l’équilibre des écosystèmes. Elle regroupe l’ensemble des espèces animales, végétales et microbiennes ainsi que leurs interactions. Chaque espèce joue un rôle crucial dans le fonctionnement des écosystèmes, qu’il s’agisse de pollinisation, de régulation des sols ou de purification de l’air. Pourtant, elle est menacée par l’activité humaine : déforestation, pollution, surpêche ou encore changement climatique. Protéger la biodiversité, c’est protéger notre propre avenir, car elle est à la base de notre alimentation et de notre bien-être. Il est urgent de limiter nos impacts en adoptant des comportements responsables, comme la réduction des déchets et la protection des habitats naturels.',  '2018-11-21 12:12:12.123456', '1', 'L’importance de préserver la biodiversité'),
    (2,  'Chaque année, des millions de tonnes de déchets finissent dans les océans ou les décharges. Cette accumulation engendre des conséquences désastreuses pour la faune et la flore. Adopter des gestes simples comme le tri, le compostage ou la réduction de l’utilisation du plastique peut faire une grande différence. De plus, privilégier les objets réutilisables, acheter en vrac ou réparer au lieu de jeter sont autant de solutions accessibles à tous. Ces petits changements quotidiens contribuent à préserver notre planète et à limiter notre empreinte écologique.',  '2024-07-12 12:12:12.123456', '0', 'Réduire les déchets : un geste quotidien'),
    (3,  'Face à l’épuisement des ressources fossiles et à l’urgence climatique, les énergies renouvelables apparaissent comme une solution prometteuse. Le solaire, l’éolien, l’hydraulique ou encore la géothermie sont des sources d’énergie propres et inépuisables. En investissant dans ces technologies, nous pouvons réduire nos émissions de gaz à effet de serre et diminuer notre dépendance aux énergies polluantes. Chaque citoyen peut aussi s’engager, par exemple, en installant des panneaux solaires ou en choisissant un fournisseur d’électricité verte.',  '2019-08-05 12:12:12.123456', '1', 'Les énergies renouvelables : un avenir durable'),
    (4,  'Nos choix alimentaires ont un impact direct sur l’environnement. L’élevage intensif, par exemple, est responsable d’importantes émissions de gaz à effet de serre et d’une déforestation massive. Adopter une alimentation plus respectueuse de la planète passe par la réduction de la consommation de viande, le choix de produits locaux et de saison, ainsi que le soutien à l’agriculture biologique. En changeant nos habitudes, nous contribuons à la préservation des ressources naturelles et à la lutte contre le changement climatique.',  '2023-01-04 12:12:12.123456', '0', 'L’impact environnemental de notre alimentation'),
    (5,  'Les forêts jouent un rôle crucial dans la régulation du climat. Elles absorbent le dioxyde de carbone, stockent le carbone et produisent de l’oxygène. Pourtant, la déforestation continue à un rythme alarmant, détruisant ces "poumons de la Terre". Protéger les forêts, replanter des arbres et soutenir les projets de reforestation sont des actions indispensables pour freiner le réchauffement climatique et protéger la biodiversité.',  '2022-06-21 12:12:12.123456', '1', 'Le rôle des forêts dans la lutte contre le réchauffement climatique'),
    (6,  'Le secteur des transports est l’un des principaux émetteurs de gaz à effet de serre. Adopter des modes de transport durables comme le vélo, les transports en commun ou le covoiturage permet de réduire notre empreinte carbone. Les véhicules électriques, bien qu’imparfaits, représentent également une alternative intéressante à condition qu’ils soient alimentés par des énergies renouvelables. Changer nos habitudes de déplacement est un pas important vers un avenir plus respectueux de l’environnement.',  '2013-09-01 12:12:12.123456', '1', 'La mobilité durable : un enjeu essentiel'),
    (7,  'Les océans, qui couvrent plus de 70 % de la surface de la Terre, sont vitaux pour la régulation du climat et la survie de nombreuses espèces. Pourtant, ils sont gravement menacés par la pollution, notamment plastique, et par l’acidification due au changement climatique. Réduire l’utilisation du plastique, organiser des campagnes de nettoyage et soutenir les initiatives pour la protection des milieux marins sont des actions essentielles pour préserver ces écosystèmes fragiles.',  '2021-03-24 12:12:12.123456', '1', 'La pollution des océans : un défi mondial'),
    (8,  'Le mouvement zéro déchet gagne en popularité, et pour cause : il permet de réduire considérablement notre impact environnemental. En adoptant des pratiques comme le compostage, l’achat en vrac ou la création de produits ménagers maison, nous limitons la production de déchets non recyclables. Ce mode de vie minimaliste favorise également les économies financières et encourage une consommation plus consciente et respectueuse de la planète.',  '2022-02-15 12:12:12.123456', '1', 'Les bénéfices du zéro déchet'),
    (9,  'Sensibiliser les jeunes générations à l’écologie est essentiel pour garantir un futur durable. Les écoles, les familles et les médias jouent un rôle clé dans la transmission des valeurs environnementales. En apprenant dès le plus jeune âge l’importance de protéger la nature et en découvrant des gestes simples pour réduire son empreinte écologique, les enfants deviennent des acteurs du changement. Investir dans l’éducation environnementale est un pari sur l’avenir.',  '2002-02-07 12:12:12.123456', '0', 'L’éducation à l’écologie : un levier pour l’avenir'),
    (10,  'Contrairement au modèle économique linéaire basé sur "produire, consommer, jeter", l’économie circulaire vise à minimiser les déchets en réutilisant et en recyclant les matériaux. Ce système favorise la durabilité et l’innovation, tout en réduisant la pression sur les ressources naturelles. En adoptant ce modèle, entreprises et citoyens peuvent contribuer à une transition écologique bénéfique pour tous.',  '2020-12-12 12:12:12.123456', '1', 'L’économie circulaire : un modèle pour demain');

INSERT IGNORE INTO roles
VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_COMM'),
    (3, 'ROLE_USER');

INSERT IGNORE INTO utilisateurs
VALUES
    (1, 'jburton@gmail.com', 'Burton', '$2a$10$l5rbv0eQQAW9OyEGaPKeG.QOoSKLHZ9BOihzEZlKQKm7p5SY1.lRW', 'Jack', 'jburton'),
    (2, 'xrouleau@gmail.com', 'Rouleau', '$2a$10$l5rbv0eQQAW9OyEGaPKeG.QOoSKLHZ9BOihzEZlKQKm7p5SY1.lRW', 'Xavier','xrouleau'),
    (3, 'bdube@gmail.com', 'Dubé', '$2a$10$l5rbv0eQQAW9OyEGaPKeG.QOoSKLHZ9BOihzEZlKQKm7p5SY1.lRW', 'Béatrice', 'bdube'),
    (4, 'sproulx@gmail.com', 'Proulx', '$2a$10$l5rbv0eQQAW9OyEGaPKeG.QOoSKLHZ9BOihzEZlKQKm7p5SY1.lRW', 'Shawn', 'sproulx'),
    (5, 'fregimbald@gmail.com', 'Régimbald', '$2a$10$l5rbv0eQQAW9OyEGaPKeG.QOoSKLHZ9BOihzEZlKQKm7p5SY1.lRW', 'France', 'fregimbald');

INSERT IGNORE INTO utilisateurs_nouvelles
VALUES
    (2, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 5),
    (2, 6),
    (2, 7),
    (2, 8),
    (2, 9),
    (2, 10),
    (3, 1),
    (3, 4),
    (3, 7),
    (3, 2),
    (3, 5),
    (5, 1),
    (5, 6),
    (5, 8),
    (5, 10),
    (5, 2);

INSERT IGNORE INTO utilisateurs_roles
VALUES
    (2, 1),
    (2, 2),
    (2, 3),
    (1, 3),
    (3, 2),
    (3, 3),
    (4, 3),
    (5, 2),
    (5, 3);
