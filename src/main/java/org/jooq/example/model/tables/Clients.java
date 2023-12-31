/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.model.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.example.model.Keys;
import org.jooq.example.model.Public;
import org.jooq.example.model.tables.records.ClientsRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Clients extends TableImpl<ClientsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.clients</code>
     */
    public static final Clients CLIENTS = new Clients();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ClientsRecord> getRecordType() {
        return ClientsRecord.class;
    }

    /**
     * The column <code>public.clients.id</code>.
     */
    public final TableField<ClientsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.clients.name</code>.
     */
    public final TableField<ClientsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.clients.address_id</code>.
     */
    public final TableField<ClientsRecord, Long> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.BIGINT, this, "");

    private Clients(Name alias, Table<ClientsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Clients(Name alias, Table<ClientsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.clients</code> table reference
     */
    public Clients(String alias) {
        this(DSL.name(alias), CLIENTS);
    }

    /**
     * Create an aliased <code>public.clients</code> table reference
     */
    public Clients(Name alias) {
        this(alias, CLIENTS);
    }

    /**
     * Create a <code>public.clients</code> table reference
     */
    public Clients() {
        this(DSL.name("clients"), null);
    }

    public <O extends Record> Clients(Table<O> child, ForeignKey<O, ClientsRecord> key) {
        super(child, key, CLIENTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<ClientsRecord, Long> getIdentity() {
        return (Identity<ClientsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ClientsRecord> getPrimaryKey() {
        return Keys.CLIENTS_PKEY;
    }

    @Override
    public List<ForeignKey<ClientsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CLIENTS__CLIENTS_ADDRESS_ID_FKEY);
    }

    private transient Addresses _addresses;

    /**
     * Get the implicit join path to the <code>public.addresses</code> table.
     */
    public Addresses addresses() {
        if (_addresses == null)
            _addresses = new Addresses(this, Keys.CLIENTS__CLIENTS_ADDRESS_ID_FKEY);

        return _addresses;
    }

    @Override
    public Clients as(String alias) {
        return new Clients(DSL.name(alias), this);
    }

    @Override
    public Clients as(Name alias) {
        return new Clients(alias, this);
    }

    @Override
    public Clients as(Table<?> alias) {
        return new Clients(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Clients rename(String name) {
        return new Clients(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Clients rename(Name name) {
        return new Clients(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Clients rename(Table<?> name) {
        return new Clients(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
