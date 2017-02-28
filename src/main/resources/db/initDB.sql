DROP TABLE IF EXISTS public.cars CASCADE;
DROP TABLE IF EXISTS public.service_stations CASCADE;
DROP TABLE IF EXISTS public.service_stations_clients CASCADE;
DROP TABLE IF EXISTS public.mechanics CASCADE;
DROP SEQUENCE IF EXISTS public.car_seq;
DROP SEQUENCE IF EXISTS public.mechanic_seq;
DROP SEQUENCE IF EXISTS public.s_station_seq;
DROP SEQUENCE IF EXISTS public.service_car_seq;

--cars
CREATE SEQUENCE public.car_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.car_seq
  OWNER TO "user";

CREATE TABLE public.cars
(
  id integer NOT NULL DEFAULT nextval('car_seq'::regclass),
  model character varying NOT NULL,
  maker character varying NOT NULL,
  engine_id integer NOT NULL,
  make_date date NOT NULL,
  price integer,
  CONSTRAINT pk_car_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cars
  OWNER TO "user";

--stations
CREATE SEQUENCE public.s_station_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
  ALTER TABLE public.s_station_seq
    OWNER TO "user";

    CREATE TABLE public.service_stations
    (
      id integer NOT NULL DEFAULT nextval('s_station_seq'::regclass),
      address character varying NOT NULL,
      CONSTRAINT pk_service_station_id PRIMARY KEY (id)
    )
    WITH (
      OIDS=FALSE
    );
    ALTER TABLE public.service_stations
      OWNER TO "user";

--mechanics
CREATE SEQUENCE public.mechanic_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.mechanic_seq
  OWNER TO "user";

CREATE TABLE public.mechanics
(
  id integer NOT NULL DEFAULT nextval('mechanic_seq'::regclass),
  first_name character varying NOT NULL,
  last_name character varying NOT NULL,
  service_station_id integer,
  CONSTRAINT pk_mechanic_id PRIMARY KEY (id),
  CONSTRAINT fk_service_station_id FOREIGN KEY (service_station_id)
      REFERENCES public.service_stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.mechanics
  OWNER TO "user";

--services and cars
CREATE SEQUENCE public.service_car_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.service_car_seq
  OWNER TO "user";

  CREATE TABLE public.service_stations_clients
  (
    id integer NOT NULL DEFAULT nextval('service_car_seq'::regclass),
    car_id integer NOT NULL,
    service_station_id integer NOT NULL,
    CONSTRAINT pk_service_sheet_id PRIMARY KEY (id),
    CONSTRAINT fk_car_id FOREIGN KEY (car_id)
        REFERENCES public.cars (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_service_stations_id FOREIGN KEY (service_station_id)
        REFERENCES public.service_stations (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT unique_car_service_station_id UNIQUE (car_id, service_station_id)
  )
  WITH (
    OIDS=FALSE
  );
  ALTER TABLE public.service_stations_clients
    OWNER TO "user";

  CREATE INDEX fki_car_id
    ON public.service_stations_clients
    USING btree
    (car_id);

  CREATE INDEX fki_service_stations_id
    ON public.service_stations_clients
    USING btree
    (service_station_id);
