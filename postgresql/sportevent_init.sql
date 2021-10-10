SELECT 'CREATE DATABASE nomosnow_event_service'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'nomosnow_event_service' )\gexec
CREATE TABLE IF NOT EXISTS public.sportevents
(
    sportevent_id text COLLATE pg_catalog."default" NOT NULL,
    organization_id text COLLATE pg_catalog."default",
    event_name text COLLATE pg_catalog."default",
    comment text COLLATE pg_catalog."default",
    CONSTRAINT sportevents_pkey PRIMARY KEY (sportevent_id)
)

TABLESPACE pg_default;


