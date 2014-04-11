use CDB;

INSERT INTO `CDB`.`users`
 (`USER_ID`,
 `USERNAME`,
 `PASSWORD`,
 `ACTIVE`
 )
 VALUES
 ('1',
 'dik81',
 '035645',
 '1'
 );

 INSERT INTO `CDB`.`users`
  (`USER_ID`,
  `USERNAME`,
  `PASSWORD`,
  `ACTIVE`
  )
  VALUES
  ('2',
  'dik82',
  '035645',
  '2'
  );

INSERT INTO `CDB`.`user_roles`
 (`USER_ROLE_ID`,
 `USER_ID`,
 `AUTHORITY`
 )
 VALUES
 ('1',
 '1',
 'ROLE_ADMIN'
 );

 INSERT INTO `CDB`.`user_roles`
  (`USER_ROLE_ID`,
  `USER_ID`,
  `AUTHORITY`
  )
  VALUES
  ('2',
  '2',
  'ROLE_USER'
  );


