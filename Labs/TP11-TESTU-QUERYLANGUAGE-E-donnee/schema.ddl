
    alter table Banker 
        drop constraint FK_8lyxc7gxydl4kp3yy5vodwaqu

    alter table Banker 
        drop constraint FK_qwjbsl0nh50ijgdro9xwv7p21

    alter table Client 
        drop constraint FK_p0n8e1o1tcj9mnnyyrsc3ywjl

    alter table Client 
        drop constraint FK_ry5y27p5iwfa43hripgl2n1y6

    alter table ExternalAccount 
        drop constraint FK_rw6dnqe5uy0ey4awjuanngsr1

    alter table ExternalAccount 
        drop constraint FK_fjkfj3git0w550dkll3c7n4iv

    alter table InternalAccount 
        drop constraint FK_ekamabbtx3hcrwkmq8orq6ej1

    alter table InternalAccount_Client 
        drop constraint FK_e1u4o0enfjvktbfxf0yw1xhrl

    alter table InternalAccount_Client 
        drop constraint FK_fdjtqarc6a0o9g1d7lxhb7wce

    alter table Operation 
        drop constraint FK_ats4u62f84r9hwqnbg5ic5v8v

    drop table Account if exists

    drop table Agency if exists

    drop table Banker if exists

    drop table Client if exists

    drop table ExternalAccount if exists

    drop table InternalAccount if exists

    drop table InternalAccount_Client if exists

    drop table Operation if exists

    drop table Person if exists

    drop sequence hibernate_sequence

    create table Account (
        id bigint not null,
        description varchar(255),
        primary key (id)
    )

    create table Agency (
        id bigint not null,
        city varchar(255),
        postalCode varchar(255),
        street varchar(255),
        primary key (id)
    )

    create table Banker (
        email varchar(255),
        id bigint not null,
        agency_id bigint,
        primary key (id)
    )

    create table Client (
        city varchar(255),
        postalCode varchar(255),
        street varchar(255),
        description varchar(255),
        id bigint not null,
        agency_id bigint,
        primary key (id)
    )

    create table ExternalAccount (
        socialNumber varchar(255),
        id bigint not null,
        FK_CLIENT bigint,
        primary key (id)
    )

    create table InternalAccount (
        number varchar(255),
        saldo float not null,
        id bigint not null,
        primary key (id)
    )

    create table InternalAccount_Client (
        internalAccounts_id bigint not null,
        owners_id bigint not null,
        primary key (internalAccounts_id, owners_id)
    )

    create table Operation (
        id bigint not null,
        amount bigint not null,
        date timestamp,
        description varchar(255),
        FK_INTERNALACCOUNT bigint,
        primary key (id)
    )

    create table Person (
        id bigint not null,
        firstname varchar(255),
        lastname varchar(255),
        primary key (id)
    )

    alter table Banker 
        add constraint FK_8lyxc7gxydl4kp3yy5vodwaqu 
        foreign key (agency_id) 
        references Agency

    alter table Banker 
        add constraint FK_qwjbsl0nh50ijgdro9xwv7p21 
        foreign key (id) 
        references Person

    alter table Client 
        add constraint FK_p0n8e1o1tcj9mnnyyrsc3ywjl 
        foreign key (agency_id) 
        references Agency

    alter table Client 
        add constraint FK_ry5y27p5iwfa43hripgl2n1y6 
        foreign key (id) 
        references Person

    alter table ExternalAccount 
        add constraint FK_rw6dnqe5uy0ey4awjuanngsr1 
        foreign key (id) 
        references Account

    alter table ExternalAccount 
        add constraint FK_fjkfj3git0w550dkll3c7n4iv 
        foreign key (FK_CLIENT) 
        references Client

    alter table InternalAccount 
        add constraint FK_ekamabbtx3hcrwkmq8orq6ej1 
        foreign key (id) 
        references Account

    alter table InternalAccount_Client 
        add constraint FK_e1u4o0enfjvktbfxf0yw1xhrl 
        foreign key (owners_id) 
        references Client

    alter table InternalAccount_Client 
        add constraint FK_fdjtqarc6a0o9g1d7lxhb7wce 
        foreign key (internalAccounts_id) 
        references InternalAccount

    alter table Operation 
        add constraint FK_ats4u62f84r9hwqnbg5ic5v8v 
        foreign key (FK_INTERNALACCOUNT) 
        references InternalAccount

    create sequence hibernate_sequence start with 1
