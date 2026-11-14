CREATE TABLE member (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    email VARCHAR(254) NOT NULL,
    display_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_member PRIMARY KEY (id)
);

CREATE UNIQUE INDEX uq_member_email_lower ON member (lower(email));