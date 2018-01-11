#test input data to pull onto jsp
use SuperSightings;
INSERT INTO `Address` Values
(1, '526 S Main St #609', 'Akron', 'OH', '44311'),
(2, '504 Rentschler Street', 'Akron', 'OH', '44311'),
(3, '44 University Ave', 'Akron', 'OH', '44308'),
(4, '60 S High St', 'Akron', 'OH', '44326'),
(5, '302 E Buchtel Ave ', 'Akron', 'OH', '44325');
INSERT INTO `Location` Values 
(1, "The Software Guild", "In 2013, Eric Ward and Eric Wise came together to create The Software Craftsmanship Guild in Akron, Ohio. The Software Craftsmanship Guild was modeled after medieval guilds, where skilled masters passed on their training to apprentices in a rigorous, hands-on process that produced more master craftsmen. Academic program management company The Learning House, Inc. acquired The Software Craftsmanship Guild in 2015 and brought Rachel McGalliard on to manage operations, allowing them to expand their reach and provide services to people in other parts of the country, like Louisville, Kentucky, and Minneapolis, Minnesota. Now, they are pleased to offer multiple languages in multiple locations and a part-time online program.", "41.07193", "-81.5291377", 1),
(2, "Somewhere else in Akron...", "A swell place", "41.0695166", "-81.518073", 2),
(3, "St. Bernard's Church", "St. Bernard's Church is a historic stone masonry church at 44 University Avenue in Akron, Ohio. The original church was built in 1861, on the current site of the National Inventor's Hall of Fame S.T.E.M. school. The current church building was begun in 1902, and opened in 1905. It features a German Baroque Romanesque style. St. Bernard Church was added to the National Register of Historic Places in 1989. The church was designed by noted Akron-born architect William P. Ginther, whose legacy of prominent Catholic church buildings is particularly robust in his native Ohio and neighboring states.", "41.0782864", "-81.5208227", 3),
(4, "Akron-Summit Library", "In January 1874, the Akron City Council passed an ordinance to create a free public library for the city. On February 27, 1874, the Board of Trustees of the newly-formed Akron Public Library met for the first time, with John R. Buchtel serving as the first Board President. The Akron Public Library officially opened to the public in a building at the corner of Mill and South Howard streets on March 1, 1874. A century later, in 1974, the Library became the Akron-Summit County Public Library, incorporating suburban branch libraries into its system. Today, the Akron-Summit County Public Library's official service area includes the City of Akron and all of Summit County except for Barberton, Cuyahoga Falls, Hudson, Peninsula, Stow-Munroe Falls, and Twinsburg, all of which are served by independent libraries. Anyone living, working, or attending school in Ohio is eligible to register for a free Akron-Summit County Public Library card.", "41.083293", "-81.5212574", 4),
(5, "University of Akron", "The University of Akron is a public research university in Akron, Ohio, United States. The university is part of the University System of Ohio. As a STEM-focused institution, it focuses on industries such as polymers, advanced materials, and engineering.", "41.0750078", "-81.5197955", 5);
INSERT INTO `Sighting` Values
(1, '20151129', 3, NULL),
(2, '20170515', 1, 'things got turnt'),
(3, '20170601', 5, 'foiled a bank robbery'),
(4, '20170720', 4, NULL),
(5, '20170825', 2, 'death and destruction'),
(6, '20160101', 1, 'feels bad'),
(7, '20160303', 2, 'big fire'),
(8, '20160404', 3, 'flooding'),
(9, '20160505', 4, 'assassination attempt'), 
(10, '20160606', 5, 'helped an old lady cross the street'),
(11, '20160707', 1, 'road rage');
INSERT INTO `Power` VALUES
(1, 'Super Strength'),
(2, 'Heat Vision'),
(3, 'Animal Magnetism'),
(4, 'Hypnosight'),
(5, 'Immaculate Taste');
INSERT INTO `SuperPerson` VALUES
(1, "The Punisher", "A 4-year Vietnam veteran, Frank Castle became a vigilante after seeing his wife and children gunned down for accidentally observing a Mafia hit. Since then, he has devoted his life to the task of destroying organized crime wherever he finds it. Although he aids many heroes in their quests, they often come into conflict over his way of dealing with crime.", 1), 
(2, "Wolverine", "With a past shrouded in mystery, Wolverine's memories are full of government secrets, traumatic events, and death. Remembering only escaping Weapon X and later joining Alpha Flight, the man known as Logan was approached with a chance to change the world, by Professor Charles Xavier. After joining Professor X's X-Men, Wolverine has been using his mutant powers, to heal at an accelerated rate, enhanced physical condition, animal-like instincts, and razor sharp bone claws that protrude from between his knuckles laced with the indestructible metal Adamantium, to fight for the peaceful coexistence of mutants and humans most of his life.", 1), 
(3, "Spiderman", "Peter Benjamin Parker is a high school student and a superhero with spider-like abilities, fighting crime as his alter ego Spider-Man. After receiving his abilities from a spider's bite, Parker chose to protect Queens from crime with his powers, juggling all of his superhero duties and the demands of his high school life. Despite his best efforts to keep his identity secret from the world, he was found and recruited by Tony Stark in the Avengers Civil War, while gaining a new suit and technology in exchange for his help. Following the Clash of the Avengers, Stark allowed Parker to keep the suit for his time as Spider-Man. Parker forged a reputation for himself in New York City and became a well-known local hero. He later stumbled into a group of criminals led by the Vulture, who were creating advanced weapons from stolen Chitauri technology, and went on a quest to defeat their own leader in order to prove to Stark that he was worthy of being an Avenger. Only after defeating Vulture did Parker discover the true responsibilities of a hero, so he decided to decline Stark's offer of membership in the Avengers in order to continue helping the little guy however he could.", 1), 
(4, "Spawn", "Albert Francis Simmons was born in Detroit, Michigan as the second of three brothers (Marc, Al and Richard) to Esther and Bernard Simmons. Simmons was a very gifted officer of the United States Marine Corps, where he attained the rank of Lieutenant Colonel. He later joined the United States Secret Service, which led to his transfer to the Central Intelligence Agency. In the CIA, Simmons was recruited by Director Jason Wynn into a special top-secret covert ops division known as the U.S. Security Group, an elite task force with jurisdiction in all domestic and foreign situations. Jason Wynn (the director of the CIA) and Simmons began to butt heads more often and Simmons planned to resign. Simmons was murdered during a mission in Botswana for the USSG's Operation Knightstrike by fellow mercenary Bruce Stinson (aka Chapel) at the order of Director Wynn. Simmons was sent to Hell because of his life as an assassin. After arranging a deal with the being known as Malebolgia, Simmons agrees to become a Hellspawn in exchange for being allowed to see his wife Wanda Blake one last time.", null), 
(5, "Donald Trumpus", "Donald J. Trumpus builds walls. He is the very definition of the American success story, continually setting the standards of excellence while expanding his interests in real estate, sports, and entertainment. He is the archetypal businessman – a deal maker without peer. Mr. Trump started his business career in an office he shared with his father in Sheepshead Bay, Brooklyn, New York. He worked with his father for five years, where they were busy making deals together. Mr. Trump has been quoted as saying, “My father was my mentor, and I learned a tremendous amount about every aspect of the construction industry from him.” Likewise, Fred C. Trump often stated that “some of my best deals were made by my son, Donald...everything he touches seems to turn to gold.” Mr. Trump then entered the very different world of Manhattan real estate. In New York City and around the world, the Trump signature is synonymous with the most prestigious of addresses. Among them are the world-renowned Fifth Avenue skyscraper, Trump Tower, and the luxury residential buildings, Trump Parc, Trump Palace, Trump Plaza, 610 Park Avenue, The Trump World Tower (the tallest building on the East Side of Manhattan), and Trump Park Avenue. Mr. Trump was also responsible for the designation and construction of the Jacob Javits Convention Center on land controlled by him, known as the West 34th Street Railroad Yards, and the total exterior restoration of the Grand Central Terminal as part of his conversion of the neighboring Commodore Hotel into the Grand Hyatt Hotel. The development is considered one of the most successful restorations in the City and earned Mr. Trump an award from Manhattan’s Community Board Five for the “tasteful and creative recycling of a distinguished hotel.” Over the years, Mr. Trump has owned and sold many great buildings in New York including the Plaza Hotel (which he renovated and brought back to its original grandeur, as heralded by the New York Times Magazine), the St. Moritz Hotel (three times…and now called the Ritz Carlton on Central Park South) and until 2002, the land under the Empire State Building (which allowed the land and lease to be merged together for the first time in over 50 years). Additionally, the NikeTown store is owned by Mr. Trump, on East 57th Street and adjacent to Tiffany’s. In early 2008, Gucci opened their largest store in the world in Trump Tower.", 0),
(6, "Dogwelder", "Dogwelder was a member of Section Eight. During the reformation of the team Dogwelder was trapping and killing dogs in alleyways. He helped fight the Mawzir's henchmen during Hitman:Smoking Aces. In Hitman: Super Guy Dogwelder and Section Eight fought The Many Angled Ones. He was vaporized and killed by The Many Angled Ones.", 0),
(7, "The Defenestrator", "Defenestrator was a member of Section Eight. During the reformation of the team he was in Arkham Asylum for attacking a police officer. He helped fight the Mawzir's henchmen during Hitman:Smoking Aces. In Hitman: Super Guy Defenestrator and Section Eight fought The Many Angled Ones. He was fatally injured by Friendly Fire and died shortly after.", 0),
(8, "Powdered Toastman", "Powdered Toastman is an extremely dramatic and oblivious vigilante and spokesperson for Powdered Toast, the breakfast treat that tastes just like sawdust. Oddly, Powdered Toast doesn't taste right unless Powdered Toast Man farts on it before it is consumed. He possesses traditional superpowers like flying and some non-traditional ones (e.g. super-flatulence, the ability to scrape toast shavings from his head). He can fly by launching himself from a toaster, or dispensing a healthy amount of flatulence. He can fly backwards, or at an angle. His catch phrase is ‘Leave everything to me!’ Powdered Toast Man hides his true identity behind the guise of Pastor Toastman, a cool youth deacon. His call to action is the shouting of his name, with significant stress on the ‘man.’ ", 1),
(9, "Dirty Bubble", "Dirty Bubble is a huge, transparent, brown, dirty, bubble. He has black eyebrows and has a black eye color. He can float and fly in the air. He also has the ability to trap his enemies inside of his body & his weakness is that his enemy can pop him.", 0),
(10, "Space Ghost", "Space Ghost is an intergalactic crime fighter from the Ghost Planet. He has the ability to be invisible (via his belt), fly, and shoot various rays from the powerbands on his wrists. Space Ghost fights such recurring supervillains as Moltar, Zorak, Black Widow, Metallus, Brak and Creature King with the help of his sidekicks: Jan, Jayce, and their pet monkey, Blip.", 1),
(11, "Man Ray", "Man Ray is the greatest arch-nemesis of Mermaid Man and Barnacle Boy, the other being the Dirty Bubble. Man Ray is an anthropomorphized Manta Ray with a blue helmet shaped like the head of a manta ray and red skin. His gloves, boots, and Speedo are a deep blue color. He has no head under his helmet. He is a great threat to Bikini Bottom.", 0);
INSERT INTO `Power` Values
(1, 'ISIS', 'ISIS is a powerful terrorist militant group that has seized control of large areas of the Middle East. Infamous for its brutal violence and murderous assaults on civilians, this self-described caliphate has claimed responsibility for hundreds of terrorist attacks around the world, in addition to destroying priceless monuments, ancient temples and other buildings, and works of art from antiquity.', 2169739182, 0, 1),
(2, 'X-Men', 'They are children of the atom, homo superior, the next link in the chain of evolution. Each was born with a unique genetic mutation, which at puberty manifested itself in extraordinary powers. In a world filled with hate and prejudice, they are feared by those who cannot accept their differences. Led by Xavier the X-Men fight to protect a world that fears them. They are locked in a battle with former colleague and friend, Magneto who believes humans and mutants should never co-exist.', 2330989182, 1, 4),
(3, 'The Illuminati', 'the Illuminati is a secret orginazation of the most powerful and influential elite in the world.They go back for centuries and maintain the same bloodlines.They set up the council on forign relations,the bilderberg group and the tri-laterial commission.those 3 groups all meet to plan the fate of the world.They consists of international bankers,top government officials,leaders in the energy cartel and media monopoly owners and have control over the U.N. and unesco.their subdivisions reach into everyones daily life without most sheeple even being aware of it. They also have ties to the freemasons,skull and bones, and the knights templar.their ultimate goal is for a one world government which they will control,also a one world currency,and they want control and ownership of all land,property,resources and people.Also they manipulate political parties,and the legal and illigal drug trade and federal agencies related to all matters listed above.', 2169739182, 1, 2),
(4, 'Men in Black', 'They are the best-kept secret in the universe. Working for a highly funded yet unofficial government agency, Kay (Tommy Lee Jones) and Jay (Will Smith) are the Men in Black, providers of immigration services and regulators of all things alien on Earth. While investigating a series of unregistered close encounters, the MIB agents uncover the deadly plot of an intergalactic terrorist who is on a mission to assassinate two ambassadors from opposing galaxies currently in residence in New York City.', 2169739182, 1, 3),
(5, 'Al Quaeda', 'Their attacks have often been against citizens of these non-Islamic countries. Al-qaeda is a terrorist group that was founded by Osama bin Laden in the late 1980s. It began as a logistical network to support Muslims in Afghanistan fighting against what was then the Soviet Union during the Afghan War.', 2169739182, 0, 5);
INSERT INTO `SuperPerson_Power` VALUES
(1, 2, 5),
(2, 3, 1),
(3, 5, 1),
(4, 4, 2),
(5, 3, 3),
(6, 5, 4),
(7, 1, 4),
(8, 2, 1),
(9, 1, 2),
(10, 5, 5),
(11, 6, 1),
(12, 7, 2),
(13, 8, 3),
(14, 9, 4),
(15, 10, 5),
(16, 11, 1),
(17, 11, 3),
(18, 10, 3),
(19, 9, 2),
(20, 8 , 5),
(21, 7, 1),
(22, 6, 2);
INSERT INTO `SuperPerson_Power` VALUES
(1, 2, 5),
(2, 3, 1),
(3, 5, 1),
(4, 4, 2),
(5, 3, 3),
(6, 5, 4),
(7, 1, 4),
(8, 2, 1),
(9, 1, 2),
(10, 5, 5),
(11, 6, 1),
(12, 7, 2),
(13, 8, 3),
(14, 9, 4),
(15, 9, 1),
(16, 10, 5),
(17, 10, 3),
(18, 11, 2);
INSERT INTO `SuperPerson_Sighting` VALUES
(1, 2, 5),
(2, 3, 1),
(3, 5, 1),
(4, 4, 2),
(5, 3, 3),
(6, 5, 4),
(7, 1, 4),
(8, 2, 1),
(9, 1, 2),
(10, 5, 5),
(11, 6, 6),
(12, 7, 7),
(13, 8, 8),
(14, 9, 9),
(15, 10, 10),
(16, 11, 11);

use SuperSightings;
select count(*) from SuperPerson;

use SuperSightings;
SELECT `SuperPerson`.* FROM `SuperPerson`
inner join `SuperPerson_Power` on `SuperPerson`.`SuperPersonId` = `SuperPerson_Power`.`SuperPersonId`
inner join `Power` on `SuperPerson_Power`.`PowerId` = `Power`.`PowerId`
where `Power`.`PowerId` = 1 ORDER BY `SuperPerson`.`Name` LIMIT 0,10;