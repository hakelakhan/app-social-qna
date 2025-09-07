-- Step 1: Create the database
CREATE DATABASE "social-qna-app";

-- Step 2: Create the application user
CREATE USER "social-qna-user" WITH PASSWORD 'social-qna-pass';

-- Step 3: Grant all privileges on the database to the app user
GRANT ALL PRIVILEGES ON DATABASE "social-qna-app" TO "social-qna-user";

-- Step 4: Create an admin user
CREATE USER "social-qna-admin" WITH PASSWORD 'social-qna-admin-pass';

-- Step 5: Grant all privileges on the database to the admin user
GRANT ALL PRIVILEGES ON DATABASE "social-qna-app" TO "social-qna-admin";

GRANT ALL ON SCHEMA public TO "social-qna-user";
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO "social-qna-user";
