
    alter table Agency 
        drop constraint FK_pcymgj5spcmlbkvjv5sq42f3j

    alter table Banker 
        drop constraint FK_1te0bkahutp17d9p3f4qkeqq8

    alter table Client 
        drop constraint FK_opdvps3jxvojk6jriu66iw7ao

    alter table Client 
        drop constraint FK_h505vwedsk89uiwbn01flqglv

    alter table Client_ExternalAccount 
        drop constraint FK_buywud696owc334c6xecu8nhc

    alter table Client_ExternalAccount 
        drop constraint FK_e8ttw557e8penbb3af90ra3xo

    alter table Client_InternalAccount 
        drop constraint FK_5178metlie93qn4hr0gptdlf8

    alter table Client_InternalAccount 
        drop constraint FK_gu2uicqbiq2kvca948ob6ow4c

    alter table Operation 
        drop constraint FK_qgodr8od8rlj6w0xcig8p5y8s

    drop table Address if exists

    drop table Agency if exists

    drop table Banker if exists

    drop table Client if exists

    drop table Client_ExternalAccount if exists

    drop table Client_InternalAccount if exists

    drop table ExternalAccount if exists

    drop table InternalAccount if exists

    drop table Operation if exists

    drop sequence hibernate_sequence

    create table Address (
        id bigint not null,
        city varchar(255),
        postalCode varchar(255),
        street varchar(255),
        primary key (id)
    )

    create table Agency (
        id bigint not null,
        fk_address bigint,
        primary key (id)
    )

    create table Banker (
        id bigint not null,
        email varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        fk_agency bigint,
        primary key (id)
    )

    create table Client (
        id bigint not null,
        description varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        fk_address bigint,
        fk_agency bigint,
        primary key (id)
    )

    create table Client_ExternalAccount (
        Client_id bigint not null,
        externalAccounts_id bigint not null
    )

    create table Client_InternalAccount (
        clients_id bigint not null,
        internalAccounts_id bigint not null
    )

    create table ExternalAccount (
        id bigint not null,
        description varchar(255),
        socialNumber varchar(255),
        primary key (id)
    )

    create table InternalAccount (
        id bigint not null,
        description varchar(255),
        number varchar(255),
        saldo float not null,
        primary key (id)
    )

    create table Operation (
        id bigint not null,
        amount bigint not null,
        date timestamp,
        description varchar(255),
        fk_internalaccount bigint,
        primary key (id)
    )

    alter table Client_ExternalAccount 
        add constraint UK_buywud696owc334c6xecu8nhc  unique (externalAccounts_id)

    alter table Agency 
        add constraint FK_pcymgj5spcmlbkvjv5sq42f3j 
        foreign key (fk_address) 
        references Address

    alter table Banker 
        add constraint FK_1te0bkahutp17d9p3f4qkeqq8 
        foreign key (fk_agency) 
        references Agency

    alter table Client 
        add constraint FK_opdvps3jxvojk6jriu66iw7ao 
        foreign key (fk_address) 
        references Address

    alter table Client 
        add constraint FK_h505vwedsk89uiwbn01flqglv 
        foreign key (fk_agency) 
        references Agency

    alter table Client_ExternalAccount 
        add constraint FK_buywud696owc334c6xecu8nhc 
        foreign key (externalAccounts_id) 
        references ExternalAccount

    alter table Client_ExternalAccount 
        add constraint FK_e8ttw557e8penbb3af90ra3xo 
        foreign key (Client_id) 
        references Client

    alter table Client_InternalAccount 
        add constraint FK_5178metlie93qn4hr0gptdlf8 
        foreign key (internalAccounts_id) 
        references InternalAccount

    alter table Client_InternalAccount 
        add constraint FK_gu2uicqbiq2kvca948ob6ow4c 
        foreign key (clients_id) 
        references Client

    alter table Operation 
        add constraint FK_qgodr8od8rlj6w0xcig8p5y8s 
        foreign key (fk_internalaccount) 
        references InternalAccount

    create sequence hibernate_sequence start with 1
