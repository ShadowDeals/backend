insert into sd_band (id, region_id)
values ('17347e02-4263-43ad-810a-855b9ea464eb', '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('72c0abe0-3bed-4d79-85c9-4c99ca42dbd9', 'mail1@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, '9a2fe9c7-06d8-4fe0-b380-c38b1acb5e5b', NULL, '17347e02-4263-43ad-810a-855b9ea464eb', '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('28ee0ba2-b653-4ee8-8c0b-6cbf158d8007', true, '72c0abe0-3bed-4d79-85c9-4c99ca42dbd9');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('58eec8a8-faa5-42f8-b968-03934452d510', '8bb3d1ef-2658-4de0-b9c9-93e0b1dff1dc', '72c0abe0-3bed-4d79-85c9-4c99ca42dbd9', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('21f3e0ea-9eff-42a1-820e-c924d0ad4526', 'mail2@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('d9206b05-2df2-448b-aba1-e31913b8fd9e', true, '21f3e0ea-9eff-42a1-820e-c924d0ad4526');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('d0f3a4da-68bb-47e0-9755-9a5181529d35', '933e1e39-4d23-4371-8b5d-c8e97e6a6df2', '21f3e0ea-9eff-42a1-820e-c924d0ad4526', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('490c77f3-aeb7-4e77-a51c-f5db3963b11e', 'mail3@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('09aec949-00d2-4d77-b815-ed930e730b9b', true, '490c77f3-aeb7-4e77-a51c-f5db3963b11e');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('47b9ce3c-dcff-4432-bc2f-942c4f01d8d9', '9a0b3461-721a-4e6b-8477-b6519cbc9756', '490c77f3-aeb7-4e77-a51c-f5db3963b11e', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('62444fc4-985a-47df-b6a3-b1a102c689fc', 'mail4@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('94bd6de8-94b9-417a-9275-f29e1e71e237', true, '62444fc4-985a-47df-b6a3-b1a102c689fc');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('12f6f748-c1f5-4d9c-8b28-64c15ea6ef11', 'ac4e3983-9228-4827-b951-c6260e277f61', '62444fc4-985a-47df-b6a3-b1a102c689fc', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('8213c1ef-cf75-478d-9063-dcddaaac2c72', 'mail5@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('f10c5554-5d95-4942-bc76-0f9a140143ab', true, '8213c1ef-cf75-478d-9063-dcddaaac2c72');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('eb4dddbb-364c-407c-9483-cfa427c877ec', '60ec1aff-bd0c-4fbf-88bc-fdf39fcfdc60', '8213c1ef-cf75-478d-9063-dcddaaac2c72', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('f13fab32-1498-4bee-9b28-e4fc39036e60', 'mail6@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('6bd35e30-edf3-4c65-8b2b-e81165182515', true, 'f13fab32-1498-4bee-9b28-e4fc39036e60');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('8243b897-d56f-41eb-b8a7-9eeeb3bd15f3', 'b47ff8e4-afc6-4e75-af30-0c5177fb20a4', 'f13fab32-1498-4bee-9b28-e4fc39036e60', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('04535b9b-2052-4add-981b-571821efdac6', 'mail7@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('bf753bba-c7bb-4460-94ee-d22e5803693f', true, '04535b9b-2052-4add-981b-571821efdac6');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('64deaaa3-010d-495a-9e5e-f3d01d3c1514', '0cd0db9d-c1df-42f0-a32a-ec49676526b0', '04535b9b-2052-4add-981b-571821efdac6', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('c20ef864-4efc-48a0-a3a0-98bae77920e6', 'mail8@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('f6665eb1-78bc-4980-8b5f-87f347bf9d15', true, 'c20ef864-4efc-48a0-a3a0-98bae77920e6');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('d1d86ae4-3f4d-4134-a0af-c8c111ff5071', 'f63bcc26-e4cb-49ae-9f40-dfc9235fb57e', 'c20ef864-4efc-48a0-a3a0-98bae77920e6', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('447f68ed-84f9-4020-87da-fb1fde0ebd53', 'mail9@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('5f0fa7f1-0b32-42b2-9076-ada485734cf9', true, '447f68ed-84f9-4020-87da-fb1fde0ebd53');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('1cddde04-39e9-4d00-9b0a-16d179e7a6d1', '83fa2fe9-96af-4e8b-8978-30f832c74759', '447f68ed-84f9-4020-87da-fb1fde0ebd53', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('8f316533-5eaf-4164-9724-865a7076ea31', 'mail10@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('fd58503d-08dd-4dbe-93ee-a30680f9a65c', true, '8f316533-5eaf-4164-9724-865a7076ea31');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('242eaab2-fb80-4f89-959f-505bec0e1336', 'b4f6ca62-dba8-41a6-a80e-08a78fe60e15', '8f316533-5eaf-4164-9724-865a7076ea31', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('0473c721-1bc6-418b-b5c7-c4d706677159', 'mail11@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('44ea2e50-cfbb-4230-bec9-8541ff6a22db', true, '0473c721-1bc6-418b-b5c7-c4d706677159');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('80d572f1-0087-4be9-9f50-3086b0ba3139', '3e5054cb-e9bd-4757-ba58-907984f93301', '0473c721-1bc6-418b-b5c7-c4d706677159', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('ea60f214-630e-47dc-8976-25a7fcefd9eb', 'mail12@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('9b5e136e-34ad-44e3-9159-3b311958b072', true, 'ea60f214-630e-47dc-8976-25a7fcefd9eb');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('f7f4ca9f-bc06-493f-9e88-92290ad5e0ad', '0fd16257-d8f6-4cb0-9323-c388dc4947ab', 'ea60f214-630e-47dc-8976-25a7fcefd9eb', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('b27f7369-9223-4603-bf12-9495e40abdc5', 'mail13@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('c881204b-a632-42a0-be66-f757e273b011', true, 'b27f7369-9223-4603-bf12-9495e40abdc5');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('666f9b69-a53c-4991-ba77-06794707a513', '4d378deb-30a5-4b12-a243-ab4beb16618e', 'b27f7369-9223-4603-bf12-9495e40abdc5', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('20f05ccd-b228-404d-8dec-55006c2213e3', 'mail14@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('87394d21-e00a-489a-94c5-4ddac5f74f1f', true, '20f05ccd-b228-404d-8dec-55006c2213e3');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('6693ca88-1e73-4f5d-98cc-741c88356f5f', '80617ae2-e342-4063-a949-eb37c23522ce', '20f05ccd-b228-404d-8dec-55006c2213e3', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('3fff88b1-f175-4a64-982f-a5570bf3dd02', 'mail15@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('bec847ed-9d75-473d-a5f0-a49c09f5ac53', true, '3fff88b1-f175-4a64-982f-a5570bf3dd02');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('e84b26ea-0a26-4808-b3a2-d04d1b85831a', '7184b804-c83d-4a1b-ac1d-7798bb17508e', '3fff88b1-f175-4a64-982f-a5570bf3dd02', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('0ffeaa21-da10-43d8-ac1f-37678daada1d', 'mail16@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('91262bce-88ac-43cf-aa99-4ffeccc4561f', true, '0ffeaa21-da10-43d8-ac1f-37678daada1d');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('e2648aa2-b49a-44ef-ac7f-2bd7567df729', 'a5c3c17d-8f21-4c70-a029-474db70d05a4', '0ffeaa21-da10-43d8-ac1f-37678daada1d', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('f270f9a8-0b0a-4d5c-a54f-7f2a5cac38e1', 'mail17@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('82e000d7-1957-4bf4-a1e4-30594bd9a287', true, 'f270f9a8-0b0a-4d5c-a54f-7f2a5cac38e1');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('08bd4636-3851-4b4d-8a0f-16edfd274db9', 'c154d337-d2cb-4f40-84e6-df4414949249', 'f270f9a8-0b0a-4d5c-a54f-7f2a5cac38e1', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('8fef7dea-5eff-40e0-8172-86e1242fa4fb', 'mail18@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('8f2ef234-1f96-4b5b-8c01-b259dce1e812', true, '8fef7dea-5eff-40e0-8172-86e1242fa4fb');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('3ed7ab00-53ae-45d4-9f0e-7d787f5a3db6', '7a693f7e-4e3a-4567-8ae4-ad2e2911073d', '8fef7dea-5eff-40e0-8172-86e1242fa4fb', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('63d3f1b6-e3ec-451e-aecb-fbb520b2678a', 'mail19@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('7d021446-e6a0-4bd0-a5e0-1c777118c28f', true, '63d3f1b6-e3ec-451e-aecb-fbb520b2678a');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('3496ba95-48b5-4cd2-823b-cdb0f6118f50', '80b7e635-5897-41a6-a84a-d906abe45cf6', '63d3f1b6-e3ec-451e-aecb-fbb520b2678a', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('8ab6dfde-5d99-4984-a179-1b3d8dc94a19', 'mail20@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('79947778-2668-44dc-8d83-41bd02f37ff7', true, '8ab6dfde-5d99-4984-a179-1b3d8dc94a19');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('99c459d6-3c5a-4edc-857c-13f612b5b451', 'acd8cb15-45f3-41a1-ac89-05b187fbaba6', '8ab6dfde-5d99-4984-a179-1b3d8dc94a19', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('3e1b7e66-e5b6-41dc-ad62-48d1e9b5eb55', 'mail21@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('87fc8df8-9e0b-443f-b174-13f8c71b5c65', true, '3e1b7e66-e5b6-41dc-ad62-48d1e9b5eb55');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('66754d3f-46ae-412a-aba9-009456979b2a', 'fe3bdffa-496e-441e-bd35-c9a3095b40af', '3e1b7e66-e5b6-41dc-ad62-48d1e9b5eb55', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('70f3a706-49c6-4ca8-9f98-47a88875653c', 'mail22@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('a9ecc489-9b6d-4f7e-bd24-d01418e0e047', true, '70f3a706-49c6-4ca8-9f98-47a88875653c');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('4f70d95e-5c57-4d19-8aa6-90448a8d5a1c', '0e8e2ec7-9e56-4ed8-9020-993427728cd1', '70f3a706-49c6-4ca8-9f98-47a88875653c', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('fa3ee11b-9d7f-4179-a5e7-b15d07f61e4f', 'mail23@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('72eb9041-8ed4-441a-b55e-1c1bcddd6842', true, 'fa3ee11b-9d7f-4179-a5e7-b15d07f61e4f');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('0402984a-3eca-4223-911d-c181cf0540b1', '5077dfd8-ffa4-466d-aad1-2e2de00cb9e1', 'fa3ee11b-9d7f-4179-a5e7-b15d07f61e4f', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('93ba6d9d-c6a6-448c-b806-836aacb42551', 'mail24@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('a4988318-711a-4b57-8711-7a7643243dc4', true, '93ba6d9d-c6a6-448c-b806-836aacb42551');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('f860c1f8-5b18-4667-b0e3-0253ee3e0bf7', '92e299a9-8f65-4cb3-8df1-b18361fa06cb', '93ba6d9d-c6a6-448c-b806-836aacb42551', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('0a10322b-f31f-4745-9569-19177e04ab5b', 'mail25@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('d8e479a9-edc1-4f40-b1ba-e8056467f899', true, '0a10322b-f31f-4745-9569-19177e04ab5b');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('312e109e-ae01-4578-a7ae-04240fc72757', '2eee193f-165a-4e03-8a2e-261d0f757725', '0a10322b-f31f-4745-9569-19177e04ab5b', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('52af6a20-44f6-448c-9ec0-a44aef89bdd9', 'mail26@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('28fbc1a7-507e-410f-a133-47bd7b197e5d', true, '52af6a20-44f6-448c-9ec0-a44aef89bdd9');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('e12035d4-cf45-40f3-a8e8-5581f4c3a5aa', 'dcda87dc-8845-45e6-a852-5d65e9b2d184', '52af6a20-44f6-448c-9ec0-a44aef89bdd9', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('32b7bc54-6cdd-4cce-8634-3660fe8cfa96', 'mail27@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('6ea34df8-09e2-475f-a04b-6e651c9819ee', true, '32b7bc54-6cdd-4cce-8634-3660fe8cfa96');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('9aed461d-47a7-4b10-8a82-74954ed955c8', 'b3a5df24-d624-4796-a144-39d9ca028fa5', '32b7bc54-6cdd-4cce-8634-3660fe8cfa96', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('01a2d525-3461-4c41-91f4-1928015dc9b7', 'mail28@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('08b38d38-1979-4f91-8fdf-4186e2062497', true, '01a2d525-3461-4c41-91f4-1928015dc9b7');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('65fa68d4-c3d0-41b8-b348-d6d22e56c4a5', 'a3b552fd-8757-425a-a1a6-a68bfe445705', '01a2d525-3461-4c41-91f4-1928015dc9b7', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('f0d51b6d-f09c-476c-8570-467a64457ff0', 'mail29@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('95a15a55-1cf8-4e5d-adb4-b86e978b37db', true, 'f0d51b6d-f09c-476c-8570-467a64457ff0');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('ed6f281e-5914-470f-ae5a-98755c511aa2', 'c3bab6c6-5819-4694-a60c-6ab60d56ca77', 'f0d51b6d-f09c-476c-8570-467a64457ff0', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('f5401bfc-445c-40c6-bdc8-b7836e9ead50', 'mail30@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('3775015b-dc1e-4261-a587-c4446ebd76e1', true, 'f5401bfc-445c-40c6-bdc8-b7836e9ead50');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('d6268d08-434e-45fc-8bbc-bfd9d2d685ee', 'b26ef6fb-2189-42cd-80b0-98703c37a5ed', 'f5401bfc-445c-40c6-bdc8-b7836e9ead50', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('baeb9020-6076-4cd4-8413-978cc17cb93b', 'mail31@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', 'b9ce21f8-6230-473b-921d-4a489e89562b', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('ce88ea18-03fd-48ac-b03e-b5f94953c02d', true, 'baeb9020-6076-4cd4-8413-978cc17cb93b');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('79341985-a578-42c5-a70c-50fcd3e901e9', 'e71a3b72-118a-4e84-b39a-703ca78b2e70', 'baeb9020-6076-4cd4-8413-978cc17cb93b', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('e5700060-bf4c-439a-914a-ca3a54058acf', 'mail32@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('2681a004-9580-4de3-8bbb-1d4e5c8e4fd1', true, 'e5700060-bf4c-439a-914a-ca3a54058acf');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('1cfeb328-ca41-468e-88ae-65790b7c6edd', 'a056cee7-ebed-41b3-9ae5-c7852ac45663', 'e5700060-bf4c-439a-914a-ca3a54058acf', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('ba7c7cdb-edc4-4a19-a416-b54c1cbf16b2', 'mail33@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('6a15e94c-9145-43c3-bb36-84fc3e9c1b7b', true, 'ba7c7cdb-edc4-4a19-a416-b54c1cbf16b2');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('d232de72-85a3-4920-9ee2-b9ddc9b5ef74', '894c6f2c-6bc7-4b5b-b390-eb1197ba520e', 'ba7c7cdb-edc4-4a19-a416-b54c1cbf16b2', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('b868d7ae-d4c5-4c68-95a6-6967b7e32160', 'mail34@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('a2eab31a-6183-4223-ae17-24fb98796a74', true, 'b868d7ae-d4c5-4c68-95a6-6967b7e32160');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('a1863ee0-d879-4b51-87a7-3e95c2acb5aa', '5d977dda-eba5-4e7c-83b4-c9c3ee45d7f2', 'b868d7ae-d4c5-4c68-95a6-6967b7e32160', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('4a4b62da-293c-4033-b3ef-2369829b077b', 'mail35@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('e141c157-fc85-40b5-bd39-78ebda68a5d1', true, '4a4b62da-293c-4033-b3ef-2369829b077b');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('96e5b8cb-7f6b-4471-843f-cc3da93a9b9c', '379ff00e-7898-4db2-98de-0047356bd833', '4a4b62da-293c-4033-b3ef-2369829b077b', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('40cd6f19-b117-4b75-9e23-d6e8dcb65898', 'mail36@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('289e73f4-a659-42d7-bc27-a13a2a15fb61', true, '40cd6f19-b117-4b75-9e23-d6e8dcb65898');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('9a52b8d9-bd14-426f-85ae-203be2d3d294', '9f528381-2d9a-416f-8f81-4b93d8abb519', '40cd6f19-b117-4b75-9e23-d6e8dcb65898', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('d948c107-3db9-4175-be6a-a05481c94441', 'mail37@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('777cda0d-bbfa-4322-8e77-8c460e3df92c', true, 'd948c107-3db9-4175-be6a-a05481c94441');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('64cc461c-be26-4336-b743-91f601b93d3b', 'dcad6972-dc09-4c67-b846-05c22eeebc7a', 'd948c107-3db9-4175-be6a-a05481c94441', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('c240f252-1c02-42f0-bfd9-a192f40f6091', 'mail38@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('abb968e7-1442-4940-8fd0-e842e0ec9109', true, 'c240f252-1c02-42f0-bfd9-a192f40f6091');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('16c86ab7-cffd-4475-8cfe-2802962252aa', '988d18fa-5800-4d28-8f69-fd814e53a416', 'c240f252-1c02-42f0-bfd9-a192f40f6091', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('4f4f28e6-0289-44c4-b93b-f8a4898a5a10', 'mail39@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('42ee4398-24dd-4431-a26c-1660f4a640df', true, '4f4f28e6-0289-44c4-b93b-f8a4898a5a10');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('c478eeef-0ac0-411a-b84c-4325875f2828', '134e29d7-84da-4dd9-b063-5ca922137ec7', '4f4f28e6-0289-44c4-b93b-f8a4898a5a10', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('dec65676-2a60-4bb3-9c19-07d32f32462e', 'mail40@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('2347b288-09b5-46bf-b880-ae2f4219058a', true, 'dec65676-2a60-4bb3-9c19-07d32f32462e');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('02eeb968-dd4f-4957-83ad-e86498a3f7d8', 'f218e434-1b00-4964-a978-af8b7b35419e', 'dec65676-2a60-4bb3-9c19-07d32f32462e', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('a15a4ceb-60d2-4110-9c76-4ee9fab649fb', 'mail41@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('0cbe23da-8ffc-44f5-810f-30e740c852c0', true, 'a15a4ceb-60d2-4110-9c76-4ee9fab649fb');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('cc15bd66-8f16-46f6-9802-13ce234c0e95', '0b28fd84-df5a-4a86-b1c2-10580b5b34d5', 'a15a4ceb-60d2-4110-9c76-4ee9fab649fb', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('b4cd8c95-ed2d-4008-9c0e-bc3e9e7d423d', 'mail42@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('11e88a4b-f616-4983-9b9e-95e7584658f3', true, 'b4cd8c95-ed2d-4008-9c0e-bc3e9e7d423d');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('d0006b87-5364-4935-8aba-a58c5c46b63b', '132d96ec-5dc2-4b1a-b874-db4d4cbd9f21', 'b4cd8c95-ed2d-4008-9c0e-bc3e9e7d423d', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('cb737141-e562-4feb-b726-879238754ff8', 'mail43@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('f9197c29-868c-4195-b38f-ec58b5ebc7bf', true, 'cb737141-e562-4feb-b726-879238754ff8');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('b534e8f2-a4e8-4722-b17a-d813b2ba4495', 'ce52ecf1-4673-470d-b832-a2ab7ef427ca', 'cb737141-e562-4feb-b726-879238754ff8', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('f3dcb415-6089-413c-8832-de97b1dbf554', 'mail44@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('dc053898-88cd-408f-a60b-5a5c2bb15a25', true, 'f3dcb415-6089-413c-8832-de97b1dbf554');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('be1cfefc-7ba8-4dec-a144-c1aade28dfca', '83751565-09c3-4b86-ae4d-ff4cf5aaf605', 'f3dcb415-6089-413c-8832-de97b1dbf554', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('77f277b1-9ed8-46a4-8f23-92c980619b60', 'mail45@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('fa5e64fb-2475-4196-8c92-2270820acd0b', true, '77f277b1-9ed8-46a4-8f23-92c980619b60');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('cc5f7938-79a9-496c-ad5c-6e816c33eef0', '42a707c5-b128-436a-8b3a-1017e22bc5cc', '77f277b1-9ed8-46a4-8f23-92c980619b60', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('7ccec226-4d66-49be-946c-aed1fddaf6fb', 'mail46@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('168d6e48-052a-4540-a197-55c1318b2e62', true, '7ccec226-4d66-49be-946c-aed1fddaf6fb');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('f92ea778-c581-4ede-8a19-a6369bd11c91', 'ed1f128c-2291-434a-90d0-96c314b740df', '7ccec226-4d66-49be-946c-aed1fddaf6fb', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('aab61c0c-e4cb-4280-a006-797da63a28dd', 'mail47@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('a3033db1-66cd-43d4-9b09-c426f8d2e5f0', true, 'aab61c0c-e4cb-4280-a006-797da63a28dd');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('2fdc3e18-b5d5-4716-867e-cda6e928e702', '6a62b039-18dc-4a94-bfeb-8eb0e98f22f2', 'aab61c0c-e4cb-4280-a006-797da63a28dd', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('1e21ec2d-4266-4487-95e2-c5681b422034', 'mail48@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('f614f37d-c719-4332-a800-b4d14656a131', true, '1e21ec2d-4266-4487-95e2-c5681b422034');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('53c882da-9c8d-4ddd-8158-449918a71da4', 'c50f7c42-d4a6-45ce-a35a-772b04250689', '1e21ec2d-4266-4487-95e2-c5681b422034', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('7e59d6f9-9b1f-485c-9805-37928e5fe573', 'mail49@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('1b6255fc-6781-48c5-9dc8-97e42a666757', true, '7e59d6f9-9b1f-485c-9805-37928e5fe573');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('3aedde53-4f82-4296-a4ee-63586eb9de2d', '125c1ccd-5c86-4969-addb-c93a02ebe2df', '7e59d6f9-9b1f-485c-9805-37928e5fe573', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('c893a977-7245-4262-8e6c-bb6ecb7e839f', 'mail50@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('34389eaf-af0e-497a-8fcd-3e224843b69a', true, 'c893a977-7245-4262-8e6c-bb6ecb7e839f');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('3601830d-d742-43fa-93cc-027622ffcaa9', 'e6f8e159-857c-4190-a70f-0dd0a5616253', 'c893a977-7245-4262-8e6c-bb6ecb7e839f', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('ab00777b-56fd-4a9e-8a68-e6bec3780a22', 'mail51@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('96ed0c02-045e-4190-be25-1504d5f0c319', true, 'ab00777b-56fd-4a9e-8a68-e6bec3780a22');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('6e0978e6-eee8-46ab-984b-093a3f8423ea', 'f4c35992-a0dd-40c4-b7a0-2c7176969df0', 'ab00777b-56fd-4a9e-8a68-e6bec3780a22', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('7d33b2b9-fdd0-433b-81cc-b2125f1c8924', 'mail52@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('b66fc28f-0613-487c-8730-5914931e2baf', true, '7d33b2b9-fdd0-433b-81cc-b2125f1c8924');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('ca147c7b-099d-4334-b8a6-9ed10594eed5', '2f1377d2-cdca-479b-a7b9-f12384777eb2', '7d33b2b9-fdd0-433b-81cc-b2125f1c8924', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('1fcb3c82-1544-4783-974c-68ffa3c8dabf', 'mail53@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('08cf547f-94a8-4961-a662-cebfaa628063', true, '1fcb3c82-1544-4783-974c-68ffa3c8dabf');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('a64b09a7-58d6-4792-84a5-26e69e5d09b4', '4835a289-8b87-42d3-b688-47600b10c8a5', '1fcb3c82-1544-4783-974c-68ffa3c8dabf', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('72b1b52e-a539-47b9-85c2-943fc1e9a981', 'mail54@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('7dd42b18-51dc-4163-92bf-07d5b6945f51', true, '72b1b52e-a539-47b9-85c2-943fc1e9a981');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('df4507f9-1aa3-4743-8efe-7dfbc20a6838', 'b4bd78bd-fb62-4038-bb87-71022b870eb4', '72b1b52e-a539-47b9-85c2-943fc1e9a981', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('7771b705-f256-4ce8-8700-e7e0ef97773e', 'mail55@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('5c0ff17d-2340-43f0-abb8-dcfe3fa17452', true, '7771b705-f256-4ce8-8700-e7e0ef97773e');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('4a916c78-28fa-44e7-b7b5-ade169f080d9', '604ecf9b-0449-49ea-b689-a90c0b7cd5a6', '7771b705-f256-4ce8-8700-e7e0ef97773e', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('4de010ba-4974-4c31-ab97-c76c21fabe59', 'mail56@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('414a5b4b-a6ea-47bb-8b25-974650695a05', true, '4de010ba-4974-4c31-ab97-c76c21fabe59');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('565cecf5-2007-4642-9887-bbaf432d9da8', '567f4027-0a8d-4c0a-8f3a-110c210b1a12', '4de010ba-4974-4c31-ab97-c76c21fabe59', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('74858a12-e6b9-439f-90ab-58e336fa89d0', 'mail57@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('fffa234e-651e-42ad-8fcb-95a8417fe0e5', true, '74858a12-e6b9-439f-90ab-58e336fa89d0');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('5cf3cfad-6901-472b-b728-003c53e1f496', '47fdb15a-cddc-4798-91ea-037da283e2d2', '74858a12-e6b9-439f-90ab-58e336fa89d0', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('ccc2d94c-951b-4277-a8ec-ddd84c950447', 'mail58@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('761e90a2-b7ca-4d9c-b488-0e9a04b8dba5', true, 'ccc2d94c-951b-4277-a8ec-ddd84c950447');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('f7ad6362-68c6-4480-9b4c-02898bc87933', '7738acdf-b463-4144-9469-1f5e160fee4d', 'ccc2d94c-951b-4277-a8ec-ddd84c950447', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('c65aeeae-1814-48b9-88bb-3664091903fe', 'mail59@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('2dd96445-36e2-483c-b760-52d6f6eaa59d', true, 'c65aeeae-1814-48b9-88bb-3664091903fe');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('c0e90c86-31a7-4729-88a8-b20c1c50e6b4', '9af1017f-c1dc-4b95-bd1b-316cbbd5e755', 'c65aeeae-1814-48b9-88bb-3664091903fe', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('f50cb1b8-63d0-4f94-9d51-5373e75515cc', 'mail60@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('ad4b6229-cef8-4c96-bcf1-8b0a30a09e68', true, 'f50cb1b8-63d0-4f94-9d51-5373e75515cc');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('652da6ff-fe9c-4b61-830e-615f0f76b891', '64048c00-1461-409c-82b5-fe26ddd720d1', 'f50cb1b8-63d0-4f94-9d51-5373e75515cc', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('8b320de8-3494-4dd3-8a7a-0d0076399352', 'mail61@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', '17347e02-4263-43ad-810a-855b9ea464eb', '9c7900fa-89e9-4422-9bc9-d2063ce60453', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('9f6dfbcb-b9a6-45ae-be3b-6348a447a5e7', true, '8b320de8-3494-4dd3-8a7a-0d0076399352');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('12a503d2-6234-47aa-ab88-7f1b25201bfc', 'f64d3c4d-4f5d-4c2e-8110-e33ec7d5b4c4', '8b320de8-3494-4dd3-8a7a-0d0076399352', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('3f8e78d9-b6fc-4fe7-b12b-8982d9963d93', 'mail62@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('751f83b8-092e-4302-9ade-02496cd4b9f6', true, '3f8e78d9-b6fc-4fe7-b12b-8982d9963d93');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('5e79e3f9-f5f5-45da-bbff-77ccaf68c1c6', '25d573eb-576d-45fa-a86c-12ad480a2ca8', '3f8e78d9-b6fc-4fe7-b12b-8982d9963d93', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('21b796a2-3dfb-461b-8cc5-40064f3e21a0', 'mail63@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('19ac5beb-356e-49b8-9e59-05410ed98935', true, '21b796a2-3dfb-461b-8cc5-40064f3e21a0');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('576dd0fa-619f-47c3-8c4d-83bec2fa1995', '4c3a3072-1ceb-4f17-9e77-a7542cb6a201', '21b796a2-3dfb-461b-8cc5-40064f3e21a0', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('9ba9df93-ac54-4337-ad3c-aa322a326b39', 'mail64@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('b567bd62-7876-41d9-b448-f04a8e259a05', true, '9ba9df93-ac54-4337-ad3c-aa322a326b39');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('79f9a532-fe6f-48f3-b3ee-5f3c04d048c8', '5bae5f8b-c9a5-4b4f-8e2f-bcae5692bd29', '9ba9df93-ac54-4337-ad3c-aa322a326b39', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('32a4684c-ebb2-4bf7-9175-0cd48198fd0a', 'mail65@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('64d76076-e3e4-45a7-8a15-5bdc3badb7dc', true, '32a4684c-ebb2-4bf7-9175-0cd48198fd0a');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('7611817f-f7c8-4d4c-bbc6-919b2eebef20', '17889d61-d683-42a4-a4ad-4b91efcf2a5c', '32a4684c-ebb2-4bf7-9175-0cd48198fd0a', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('c15d92d5-8f3c-4e4e-a321-c19fd60ad9e6', 'mail66@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('9db40452-f5f4-43b3-8f81-853be9c38da2', true, 'c15d92d5-8f3c-4e4e-a321-c19fd60ad9e6');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('3347bef9-367b-4290-9cbd-8ec8f35852c0', '2c13b874-ee87-46f8-984c-cd5cbcecec98', 'c15d92d5-8f3c-4e4e-a321-c19fd60ad9e6', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('43788690-9fb1-46c8-a852-6ea1d0d965bb', 'mail67@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('c46748b0-a046-4aba-94f6-14b98088bc4b', true, '43788690-9fb1-46c8-a852-6ea1d0d965bb');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('c82063c7-014a-4dfc-a031-90ead67c5521', 'eb7bcfdb-8f5b-45cd-b06c-cf8edb0dd90d', '43788690-9fb1-46c8-a852-6ea1d0d965bb', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('cf9c7207-5a77-45a4-a582-6e266c4227ab', 'mail68@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('a4be32e2-f999-450f-ae98-5bfcb09c9296', true, 'cf9c7207-5a77-45a4-a582-6e266c4227ab');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('d99bc86d-c3d8-4dd3-9c6b-259344288556', 'e19365ec-9ca7-4433-b875-0b8890dea169', 'cf9c7207-5a77-45a4-a582-6e266c4227ab', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('8094e3d2-f78a-4b7d-b545-3ae9e72fab21', 'mail69@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('bd98723f-1bad-462f-92ca-c8ab1c280356', true, '8094e3d2-f78a-4b7d-b545-3ae9e72fab21');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('e7013b30-d975-47d5-9ae2-d7e8c1f40805', '7acd0266-f2b7-433a-a09b-dca4e026329b', '8094e3d2-f78a-4b7d-b545-3ae9e72fab21', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('078ec4fe-f7e7-47fe-beec-4a25311f8d31', 'mail70@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('4e7f6ba6-4c4f-423e-9cc5-bc2a34a11e6d', true, '078ec4fe-f7e7-47fe-beec-4a25311f8d31');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('9e443731-d831-4df5-8704-0a57a560f6e5', 'fba575ce-85f6-488b-afbe-99ca612b520b', '078ec4fe-f7e7-47fe-beec-4a25311f8d31', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('78c4ec45-4569-4808-aee4-6d3b4c8a3be1', 'mail71@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('e48bbe83-60c9-4965-a145-1cf3e7548d16', true, '78c4ec45-4569-4808-aee4-6d3b4c8a3be1');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('b7bcde7c-823a-49ff-933b-a0436dd4997f', '9fa206d7-bcd1-48e5-9d15-6648d5451a59', '78c4ec45-4569-4808-aee4-6d3b4c8a3be1', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('7aefaa2e-ff48-44c9-b2a7-f58185235ed3', 'mail72@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('6d7b64e7-45c2-4778-bc98-e28c4d151937', true, '7aefaa2e-ff48-44c9-b2a7-f58185235ed3');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('5fe684e1-4eee-427b-b19c-73f014739dd0', '2ac6026b-2813-492d-8b37-7ec40dd177cd', '7aefaa2e-ff48-44c9-b2a7-f58185235ed3', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('2afe4b99-f74d-4cf4-bf2e-fe0865c90db0', 'mail73@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('d2389c65-4e70-4038-81e4-868ace8db7eb', true, '2afe4b99-f74d-4cf4-bf2e-fe0865c90db0');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('486f0eec-613d-4f24-93c1-119748565bff', '7a6dd679-a4c6-4c72-9bd3-11932f3f4926', '2afe4b99-f74d-4cf4-bf2e-fe0865c90db0', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('3de044f1-7253-4022-940d-23b46c4fb37f', 'mail74@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('3f460ed2-efdd-404d-ac47-1d0ff3f00948', true, '3de044f1-7253-4022-940d-23b46c4fb37f');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('7ac2be24-ec19-42be-bccf-bd4ce3723273', 'd78b6267-a79d-4e9f-a568-bd931dc2905e', '3de044f1-7253-4022-940d-23b46c4fb37f', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('ffecee3d-8bd0-48f4-b465-096e55830fcd', 'mail75@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('fb44e6a9-61b3-48a8-afa3-b1207550564a', true, 'ffecee3d-8bd0-48f4-b465-096e55830fcd');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('436ed37f-e776-4cae-99ae-a1b746ba4566', 'db350267-2a7f-4480-8c37-1631aedd4c14', 'ffecee3d-8bd0-48f4-b465-096e55830fcd', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('7f1c74fa-a282-4a92-bcf7-b5188d568e34', 'mail76@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('cfb4b71e-4ff2-4a28-b565-858ecfa94d07', true, '7f1c74fa-a282-4a92-bcf7-b5188d568e34');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('8b656af3-1c50-4615-a15a-378215807f56', 'c7c9b9e9-6060-4362-947d-2a16ae25575a', '7f1c74fa-a282-4a92-bcf7-b5188d568e34', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('beb85232-4d85-4690-8972-9fea5d2fbc6f', 'mail77@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('7df4429f-d631-4329-a4c4-a625c2696c25', true, 'beb85232-4d85-4690-8972-9fea5d2fbc6f');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('16d14812-db0c-4bda-b497-57ca70c785cd', '3bf876e7-1547-4549-9a27-97a7e1e47a4b', 'beb85232-4d85-4690-8972-9fea5d2fbc6f', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('9949d315-3816-43f1-803a-b5a1c1963910', 'mail78@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('e38b08cd-44ad-4174-81f5-43d6c82d894b', true, '9949d315-3816-43f1-803a-b5a1c1963910');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('e679da46-991b-4c6f-a729-618ee58046f7', 'fa62710d-1573-40a5-a00a-e41326932ff7', '9949d315-3816-43f1-803a-b5a1c1963910', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('a267ee4a-c01b-4f89-94b0-20da724562c1', 'mail79@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('302b840c-6e01-40f0-bcbb-935e4c081527', true, 'a267ee4a-c01b-4f89-94b0-20da724562c1');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('66785bca-d310-4c87-8375-1008c380f131', 'c00f1da0-6546-4a9c-ab9d-e1294ce42648', 'a267ee4a-c01b-4f89-94b0-20da724562c1', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('db29128b-e376-4350-a745-ed973d27c1f9', 'mail80@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('036700e2-6f98-4993-82b2-a3cba69b811a', true, 'db29128b-e376-4350-a745-ed973d27c1f9');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('1d47ccb8-11fa-4d77-9a0b-c00e0efd16d9', 'c96997fc-572a-4387-a18e-503900a19aea', 'db29128b-e376-4350-a745-ed973d27c1f9', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('129a9456-6e0b-4f88-ae5a-778f9905a07b', 'mail81@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('b71a9e6f-f690-449b-8495-7b7b76322073', true, '129a9456-6e0b-4f88-ae5a-778f9905a07b');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('263b212a-9ab1-45f2-975c-ecd44185fe93', '7a245da9-0e38-4422-999d-0ae30836ee0c', '129a9456-6e0b-4f88-ae5a-778f9905a07b', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('0aa944d4-bc43-430b-a0a7-19ebe597100e', 'mail82@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('c3700f28-ca3d-455f-838d-18fc810ed86f', true, '0aa944d4-bc43-430b-a0a7-19ebe597100e');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('d2757c5d-0ba2-4125-a76b-1e2d0f406149', 'be4703a6-cca5-42dd-a982-fe5a1e7634cc', '0aa944d4-bc43-430b-a0a7-19ebe597100e', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('0efa2142-b693-4132-b82f-3d5eb5903b7a', 'mail83@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('95a55c13-d9db-4751-a180-b8f8f1b76fb0', true, '0efa2142-b693-4132-b82f-3d5eb5903b7a');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('056f2026-d8f6-4f28-872d-9695584b3811', '5202d480-1ed4-4e69-95bf-08a05ab6964a', '0efa2142-b693-4132-b82f-3d5eb5903b7a', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('ae0db2f5-9397-4fed-9d33-68c605162075', 'mail84@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('eb96f488-672f-47ea-8911-efbe691771e8', true, 'ae0db2f5-9397-4fed-9d33-68c605162075');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('85d55f58-eb82-4bcb-8279-ea8da469acd3', '63b2a18e-f24e-4b79-8fd9-d556c097a6cc', 'ae0db2f5-9397-4fed-9d33-68c605162075', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('43391e48-f676-45aa-b359-03c552beef9c', 'mail85@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('fb8845f7-e94f-4f94-853a-aed3234abf23', true, '43391e48-f676-45aa-b359-03c552beef9c');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('65f269c2-a89c-4a67-8fa3-cd67818a9ca0', 'fa9be93c-3d47-47f6-931d-a8a23fdbabe1', '43391e48-f676-45aa-b359-03c552beef9c', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('8deee97a-a798-4c7e-a864-deb2fa233ab2', 'mail86@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('ed9461bb-fcb6-43dd-863d-3f8b13012612', true, '8deee97a-a798-4c7e-a864-deb2fa233ab2');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('67e04cdc-b009-431a-8662-3e5f960ee1db', '4d7d52fb-c1e2-448e-9660-d0d7074593fd', '8deee97a-a798-4c7e-a864-deb2fa233ab2', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('b2eed8bc-3014-4976-abfb-767759602e7d', 'mail87@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('739f2ab4-819e-4a8a-9fbf-68c1cbbeb132', true, 'b2eed8bc-3014-4976-abfb-767759602e7d');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('48b2a4fa-fa56-4057-bb78-c65779b8f090', '6addcbfa-c1ae-46df-98b4-38bce8a5fee8', 'b2eed8bc-3014-4976-abfb-767759602e7d', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('2450350f-7970-4008-b660-28116883cd7e', 'mail88@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('54c64c1f-4bda-4f34-8e8b-f38d30e139e4', true, '2450350f-7970-4008-b660-28116883cd7e');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('edbab6ef-7479-479e-aa5f-2f61bb154c73', '5c8d03ca-9b74-4803-81e1-fccb9e9b7f38', '2450350f-7970-4008-b660-28116883cd7e', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('73818017-b3b1-46ce-9ec8-8c98b7fd7300', 'mail89@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('11b0c7e8-9f5c-4403-b268-1d6d084e3f1b', true, '73818017-b3b1-46ce-9ec8-8c98b7fd7300');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('7d6ef027-dc23-4686-a4c4-8b7ba0648c9a', 'b6c6575c-b2df-4267-b6f0-6184076d7e5b', '73818017-b3b1-46ce-9ec8-8c98b7fd7300', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('a00efbaa-105e-4950-91f9-41542253f811', 'mail90@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('0bd56cae-b1e8-4cb4-bf5e-a316af9a7b7a', true, 'a00efbaa-105e-4950-91f9-41542253f811');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('fed473be-6a28-4f52-b3c0-1bf8bcc0ab6d', '6da209a2-9811-49dc-8e28-f9eece1200d1', 'a00efbaa-105e-4950-91f9-41542253f811', now());

insert into sd_user (id, email, password, first_name, last_name, nickname, band_id, role_id, task_id, own_band_id, region_id)
values ('6fe5bb61-1faa-4bba-ad79-b53ea60ae4aa', 'mail91@mail.ru', '$2a$10$o9Gfzjd/NZUvZ/AvuQ1t6OPAenheXBpzdgEbIO18goplPjNcfoYC.', 'Кирилл', 'Симовин', 'Tamada', NULL, 'fc55ec84-5c0a-4890-9752-cba4c5fa6fa0', NULL, NULL, '170f5f8f-bf1b-4d1b-ab21-7714a83880f1');
insert into sd_activation_code (id, is_activated, sd_user_id)
values ('6cea56e8-c6fb-4496-83b6-9d5af401a89b', true, '6fe5bb61-1faa-4bba-ad79-b53ea60ae4aa');
insert into sd_refresh_token (id, refresh_token, user_id, date_created)
values ('e82e3768-77df-401f-982a-5e2f33b20f9b', '6e670889-c820-48dd-816d-4fa137a84805', '6fe5bb61-1faa-4bba-ad79-b53ea60ae4aa', now());

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('62e9b2b5-cff7-43f9-9946-256d9f5c08c5', 'Какой-то адрес', 'Сделайте красиво', 'a00efbaa-105e-4950-91f9-41542253f811', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), 'b2eed8bc-3014-4976-abfb-767759602e7d', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('e0136e74-1efc-4914-a92d-0a8345d6e6c9', 'Какой-то адрес', 'Сделайте красиво', '73818017-b3b1-46ce-9ec8-8c98b7fd7300', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '73818017-b3b1-46ce-9ec8-8c98b7fd7300', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('b278cc13-9329-4019-899a-4ce7f875e23a', 'Какой-то адрес', 'Сделайте красиво', '43391e48-f676-45aa-b359-03c552beef9c', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('37ccb80e-ac2f-4398-a51e-146863ccc615', 'Какой-то адрес', 'Сделайте красиво', '2450350f-7970-4008-b660-28116883cd7e', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '8deee97a-a798-4c7e-a864-deb2fa233ab2', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('7b3ff0e8-517a-4c15-9598-0add046a947a', 'Какой-то адрес', 'Сделайте красиво', '43391e48-f676-45aa-b359-03c552beef9c', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '43391e48-f676-45aa-b359-03c552beef9c', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('e82dd543-ee18-4e03-bf5c-a6b5ceedff5b', 'Какой-то адрес', 'Сделайте красиво', '2450350f-7970-4008-b660-28116883cd7e', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('bad9960b-bf9e-4f84-bf3f-0ada415769f7', 'Какой-то адрес', 'Сделайте красиво', '2450350f-7970-4008-b660-28116883cd7e', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), 'a00efbaa-105e-4950-91f9-41542253f811', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('384a99c3-602e-41cb-a221-df4503c33f52', 'Какой-то адрес', 'Сделайте красиво', '73818017-b3b1-46ce-9ec8-8c98b7fd7300', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), 'b2eed8bc-3014-4976-abfb-767759602e7d', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('6b30251b-1e06-4cad-9868-77e47e53a99e', 'Какой-то адрес', 'Сделайте красиво', '73818017-b3b1-46ce-9ec8-8c98b7fd7300', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), 'a00efbaa-105e-4950-91f9-41542253f811', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('e0633e6f-6673-48bc-81c0-f1cdf896810e', 'Какой-то адрес', 'Сделайте красиво', '8deee97a-a798-4c7e-a864-deb2fa233ab2', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('23975116-6f2d-4c4e-a102-682a5c086be9', 'Какой-то адрес', 'Сделайте красиво', '43391e48-f676-45aa-b359-03c552beef9c', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), 'b2eed8bc-3014-4976-abfb-767759602e7d', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('fded5e5c-adc5-4087-aac5-e06d20758ea1', 'Какой-то адрес', 'Сделайте красиво', '2450350f-7970-4008-b660-28116883cd7e', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('a5cb27c0-d638-4fc9-b4e1-d195611d5302', 'Какой-то адрес', 'Сделайте красиво', 'b2eed8bc-3014-4976-abfb-767759602e7d', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('1dd04a91-4b50-4251-b4d5-c0679c50dadf', 'Какой-то адрес', 'Сделайте красиво', '8deee97a-a798-4c7e-a864-deb2fa233ab2', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '43391e48-f676-45aa-b359-03c552beef9c', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('dcd0b43b-97d6-488a-83b2-43fdf26afb53', 'Какой-то адрес', 'Сделайте красиво', 'a00efbaa-105e-4950-91f9-41542253f811', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('a29cd6c7-0661-4a94-9a27-c3407eef93b9', 'Какой-то адрес', 'Сделайте красиво', '43391e48-f676-45aa-b359-03c552beef9c', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '43391e48-f676-45aa-b359-03c552beef9c', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('08845c3a-3acf-4fcb-90ca-0e34c4fc1f40', 'Какой-то адрес', 'Сделайте красиво', '73818017-b3b1-46ce-9ec8-8c98b7fd7300', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('fcc623e7-7dce-493e-b3bc-58bf483ce64c', 'Какой-то адрес', 'Сделайте красиво', '2450350f-7970-4008-b660-28116883cd7e', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), 'b2eed8bc-3014-4976-abfb-767759602e7d', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('ad32ad1a-7a6d-4342-bc09-fa0cd9299b15', 'Какой-то адрес', 'Сделайте красиво', 'a00efbaa-105e-4950-91f9-41542253f811', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '73818017-b3b1-46ce-9ec8-8c98b7fd7300', 150000);

insert into sd_task (id, address, description, customer_id, type_id, status_id, date_created, officer_id, price)
values ('984f2d36-fd26-42b7-94de-8fb303c31272', 'Какой-то адрес', 'Сделайте красиво', '2450350f-7970-4008-b660-28116883cd7e', 'afa1f1b4-6758-4a0a-a862-570c7fddd3a0', '041c64ff-e1bc-412a-8738-4a3349aa5a65', now(), '2450350f-7970-4008-b660-28116883cd7e', 150000);

insert into sd_band_task (id, band_id, task_id)
values ('987754d2-621b-4aa0-a534-74c858c53c08', '17347e02-4263-43ad-810a-855b9ea464eb', '62e9b2b5-cff7-43f9-9946-256d9f5c08c5');

insert into sd_band_task (id, band_id, task_id)
values ('2f4bd8a6-c80e-4788-9111-4b50c8165563', '17347e02-4263-43ad-810a-855b9ea464eb', 'e0136e74-1efc-4914-a92d-0a8345d6e6c9');

insert into sd_band_task (id, band_id, task_id)
values ('6b17bc9a-95a6-4edd-8fdc-95d7c9d1fdb5', '17347e02-4263-43ad-810a-855b9ea464eb', 'b278cc13-9329-4019-899a-4ce7f875e23a');

insert into sd_band_task (id, band_id, task_id)
values ('53930a83-48e6-4d96-bb56-8a029d43252f', '17347e02-4263-43ad-810a-855b9ea464eb', '37ccb80e-ac2f-4398-a51e-146863ccc615');

insert into sd_band_task (id, band_id, task_id)
values ('18f81dd0-cb7b-457c-a82b-eef74dffc7de', '17347e02-4263-43ad-810a-855b9ea464eb', '7b3ff0e8-517a-4c15-9598-0add046a947a');

insert into sd_band_task (id, band_id, task_id)
values ('82eaff26-0009-4e98-a928-388cf297db91', '17347e02-4263-43ad-810a-855b9ea464eb', 'e82dd543-ee18-4e03-bf5c-a6b5ceedff5b');

insert into sd_band_task (id, band_id, task_id)
values ('0c4c6fea-e405-42fe-b36b-b4ea473200b2', '17347e02-4263-43ad-810a-855b9ea464eb', 'bad9960b-bf9e-4f84-bf3f-0ada415769f7');

insert into sd_band_task (id, band_id, task_id)
values ('f7b7391d-9c21-4951-a135-59be11a178e2', '17347e02-4263-43ad-810a-855b9ea464eb', '384a99c3-602e-41cb-a221-df4503c33f52');

insert into sd_band_task (id, band_id, task_id)
values ('8b679849-b059-44e7-a696-ebafe0bb42a9', '17347e02-4263-43ad-810a-855b9ea464eb', '6b30251b-1e06-4cad-9868-77e47e53a99e');

insert into sd_band_task (id, band_id, task_id)
values ('cb79fbf9-624c-4d35-a5a7-6728779c75d2', '17347e02-4263-43ad-810a-855b9ea464eb', 'e0633e6f-6673-48bc-81c0-f1cdf896810e');

insert into sd_band_task (id, band_id, task_id)
values ('c43c3c5d-fd4d-4e4a-bad6-178bd7656b43', '17347e02-4263-43ad-810a-855b9ea464eb', '23975116-6f2d-4c4e-a102-682a5c086be9');

insert into sd_band_task (id, band_id, task_id)
values ('824f1c41-1524-41a8-a449-be9ea22644fc', '17347e02-4263-43ad-810a-855b9ea464eb', 'fded5e5c-adc5-4087-aac5-e06d20758ea1');

insert into sd_band_task (id, band_id, task_id)
values ('ace651f7-ee27-4085-aaf6-b0a5ebec1d30', '17347e02-4263-43ad-810a-855b9ea464eb', 'a5cb27c0-d638-4fc9-b4e1-d195611d5302');

insert into sd_band_task (id, band_id, task_id)
values ('ab5bef90-ff58-457d-b0f1-fd7931fdfeb9', '17347e02-4263-43ad-810a-855b9ea464eb', '1dd04a91-4b50-4251-b4d5-c0679c50dadf');

insert into sd_band_task (id, band_id, task_id)
values ('9dd0e8d4-6514-4653-a18e-2c83b9a86e12', '17347e02-4263-43ad-810a-855b9ea464eb', 'dcd0b43b-97d6-488a-83b2-43fdf26afb53');

insert into sd_band_task (id, band_id, task_id)
values ('6dc02a4a-a91f-45d5-a82b-4ee0181fbae9', '17347e02-4263-43ad-810a-855b9ea464eb', 'a29cd6c7-0661-4a94-9a27-c3407eef93b9');

insert into sd_band_task (id, band_id, task_id)
values ('8e6c7d84-46d1-4a5e-b584-632081de23b7', '17347e02-4263-43ad-810a-855b9ea464eb', '08845c3a-3acf-4fcb-90ca-0e34c4fc1f40');

insert into sd_band_task (id, band_id, task_id)
values ('e7458d99-9444-4cec-bb6b-ee225e39290a', '17347e02-4263-43ad-810a-855b9ea464eb', 'fcc623e7-7dce-493e-b3bc-58bf483ce64c');

insert into sd_band_task (id, band_id, task_id)
values ('65930332-bf0b-4217-8784-a0edffdd06e1', '17347e02-4263-43ad-810a-855b9ea464eb', 'ad32ad1a-7a6d-4342-bc09-fa0cd9299b15');

insert into sd_band_task (id, band_id, task_id)
values ('ed480e87-9668-4508-b3bd-4fc7f8d23439', '17347e02-4263-43ad-810a-855b9ea464eb', '984f2d36-fd26-42b7-94de-8fb303c31272');
