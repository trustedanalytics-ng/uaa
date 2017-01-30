-- Human readable user id: change user_id to varchar(255)
ALTER TABLE USERS ALTER COLUMN id VARCHAR(255);
ALTER TABLE GROUP_MEMBERSHIP ALTER COLUMN member_id VARCHAR(255);
ALTER TABLE authz_approvals ALTER COLUMN user_id VARCHAR(255);
ALTER TABLE oauth_code ALTER COLUMN user_id VARCHAR(255);
ALTER TABLE revocable_tokens ALTER COLUMN user_id VARCHAR(255);