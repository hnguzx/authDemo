INSERT INTO sys_user(id, username, password, account_expired, account_locked, credentials_expired,
                     enabled)
VALUES (1, 'admin', '$2a$10$R3J7Z9e7vvbx1rAvN08dKezFSf3sZli0xc81rvzuAa10rf28JDSgq', b'1', b'1',
        b'1', b'1');
INSERT INTO sys_user(id, username, password, account_expired, account_locked, credentials_expired,
                     enabled)
VALUES (2, 'user', '$2a$10$ulkiGnJtG50ma2Xdi11HZOiuBzCFHyhPiMg9J/.N/uahXJelf33fK', b'1', b'1', b'1',
        b'1');

INSERT INTO sys_role(id, name)
VALUES (1, 'ADMIN');
INSERT INTO sys_role(id, name)
VALUES (2, 'USER');

INSERT INTO sys_authority(id, code, url)
VALUES (1, '100000', '/admin/demo');
INSERT INTO sys_authority(id, code, url)
VALUES (2, '200000', '/user/demo');
INSERT INTO sys_authority(id, code, url)
VALUES (3, '200001', '/user/query');
INSERT INTO sys_authority(id, code, url)
VALUES (4, '100001', '/admin/query');
INSERT INTO sys_authority(id, code, url)
VALUES (5, '300000', '/private/demo');
INSERT INTO sys_authority(id, code, url)
VALUES (6, '300001', '/private/query');


INSERT INTO sys_user_role(id, user_id, role_id)
VALUES (1, 1, 1);
INSERT INTO sys_user_role(id, user_id, role_id)
VALUES (2, 2, 2);


INSERT INTO sys_role_authority(id, role_id, authority_id)
VALUES (1, 1, 1);
INSERT INTO sys_role_authority(id, role_id, authority_id)
VALUES (2, 1, 4);
INSERT INTO sys_role_authority(id, role_id, authority_id)
VALUES (3, 2, 2);
INSERT INTO sys_role_authority(id, role_id, authority_id)
VALUES (4, 2, 3);


INSERT INTO sys_user_authority(id, user_id, authority_id)
VALUES (2, 2, 6);



