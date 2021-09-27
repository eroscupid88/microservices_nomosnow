CREATE TABLE IF NOT EXISTS public.sportevents
(
    sportevent_id text COLLATE pg_catalog."default" NOT NULL,
    organization_id text COLLATE pg_catalog."default" NOT NULL,
    event_name text COLLATE pg_catalog."default" NOT NULL,
    comment text COLLATE pg_catalog."default",
    CONSTRAINT sportevent_id_pkey PRIMARY KEY (sportevent_id),
    CONSTRAINT sport_organization_id_fkey FOREIGN KEY (organization_id)
        REFERENCES public.organizations (organization_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.sportevents
    OWNER to postgres;