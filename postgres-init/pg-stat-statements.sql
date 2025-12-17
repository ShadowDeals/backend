CREATE EXTENSION IF NOT EXISTS pg_stat_statements;

ALTER SYSTEM SET pg_stat_statements.track = 'all';
ALTER SYSTEM SET pg_stat_statements.max = 10000;
SELECT pg_reload_conf();