-- Kindergarten groups
INSERT INTO KINDERGARTEN_GROUP(NAME) VALUES ('Rote Gruppe');
INSERT INTO KINDERGARTEN_GROUP(NAME) VALUES ('Grüne Gruppe');
INSERT INTO KINDERGARTEN_GROUP(NAME) VALUES ('Blaue Gruppe');

INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Shizuko', 'Salem', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Lisa', 'Lapan', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Katia', 'Klapp', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Marilee', 'Mento', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Laronda', 'Lockman', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Karen', 'Kratzer', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Celena', 'Chisolm', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Hubert', 'Hardwick', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Delinda', 'Devitt', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Keila', 'Knappenberger', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Rote Gruppe'));

INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Edythe', 'Ericson', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Debra', 'Dawkins', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Broderick', 'Bohland', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Jin', 'Junkin', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Celia', 'Cefalu', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Dulcie', 'Delariva', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Christel', 'Cullinan', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Laurie', 'Loudon', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Madelyn', 'Montalbano', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));
INSERT INTO KINDERGARTEN_CHILD(FIRST_NAME, LAST_NAME, KIGA_START, KIGA_END, BREAKFAST, LUNCH, GROUP_ID) VALUES ('Kristyn', 'Krone', TO_DATE('01-09-2017', 'DD-MM-YYYY'), TO_DATE('30-06-2020', 'DD-MM-YYYY'), True, True, (SELECT ID FROM KINDERGARTEN_GROUP WHERE name = 'Grüne Gruppe'));

-- Child attributes
INSERT INTO CHILD_ATTRIBUTE(CLASSIFICATION, NAME) VALUES ('Unverträglichkeit', 'Laktoseintoleranz');
INSERT INTO CHILD_ATTRIBUTE(CLASSIFICATION, NAME) VALUES ('Ernährungsweise', 'Vegetarier');
INSERT INTO CHILD_ATTRIBUTE(CLASSIFICATION, NAME) VALUES ('Unverträglichkeit', 'Nußallergie');

INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Shizuko' AND LAST_NAME = 'Salem', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Laktoseintoleranz');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Lisa' AND LAST_NAME = 'Lapan', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Laktoseintoleranz');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Lisa' AND LAST_NAME = 'Lapan', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Vegetarier');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Edythe' AND LAST_NAME = 'Ericson', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Laktoseintoleranz');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Edythe' AND LAST_NAME = 'Ericson', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Vegetarier');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Edythe' AND LAST_NAME = 'Ericson', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Nußallergie');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Debra' AND LAST_NAME = 'Dawkins', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Laktoseintoleranz');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Debra' AND LAST_NAME = 'Dawkins', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Vegetarier');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Broderick' AND LAST_NAME = 'Bohland', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Laktoseintoleranz');
INSERT INTO KINDERGARTEN_CHILD_ATTRIBUTES(KINDERGARTEN_CHILD_ID, ATTRIBUTES_ID) VALUES (SELECT ID FROM KINDERGARTEN_CHILD WHERE FIRST_NAME = 'Broderick' AND LAST_NAME = 'Bohland', SELECT ID FROM CHILD_ATTRIBUTE WHERE NAME = 'Vegetarier');
