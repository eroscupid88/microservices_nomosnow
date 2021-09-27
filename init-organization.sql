CREATE TABLE IF NOT EXISTS public.sporteventorganizations
(
    organization_id text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    phone text COLLATE pg_catalog."default",
    comment text COLLATE pg_catalog."default",
    CONSTRAINT organizations_pkey PRIMARY KEY (organization_id)
)
TABLESPACE pg_default;

ALTER TABLE public.organizations
    OWNER to postgres;
