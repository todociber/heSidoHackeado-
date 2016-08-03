package com.todociber.hesidohackeado.bd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.todociber.hesidohackeado.bd.Hackeado;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table HACKEADO.
*/
public class HackeadoDao extends AbstractDao<Hackeado, Long> {

    public static final String TABLENAME = "HACKEADO";

    /**
     * Properties of entity Hackeado.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Correo = new Property(1, String.class, "correo", false, "CORREO");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Author = new Property(3, String.class, "author", false, "AUTHOR");
        public final static Property Is_vrf = new Property(4, String.class, "is_vrf", false, "IS_VRF");
        public final static Property Date_created = new Property(5, String.class, "date_created", false, "DATE_CREATED");
        public final static Property Date_leaked = new Property(6, String.class, "date_leaked", false, "DATE_LEAKED");
        public final static Property Emails_count = new Property(7, String.class, "emails_count", false, "EMAILS_COUNT");
        public final static Property Details = new Property(8, String.class, "details", false, "DETAILS");
        public final static Property Source_id = new Property(9, String.class, "source_id", false, "SOURCE_ID");
        public final static Property Source_url = new Property(10, String.class, "source_url", false, "SOURCE_URL");
        public final static Property Source_lines = new Property(11, String.class, "source_lines", false, "SOURCE_LINES");
        public final static Property Source_size = new Property(12, String.class, "source_size", false, "SOURCE_SIZE");
        public final static Property Source_network = new Property(13, String.class, "source_network", false, "SOURCE_NETWORK");
        public final static Property Source_provider = new Property(14, String.class, "source_provider", false, "SOURCE_PROVIDER");
    };


    public HackeadoDao(DaoConfig config) {
        super(config);
    }
    
    public HackeadoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'HACKEADO' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'CORREO' TEXT," + // 1: correo
                "'TITLE' TEXT," + // 2: title
                "'AUTHOR' TEXT," + // 3: author
                "'IS_VRF' TEXT," + // 4: is_vrf
                "'DATE_CREATED' TEXT," + // 5: date_created
                "'DATE_LEAKED' TEXT," + // 6: date_leaked
                "'EMAILS_COUNT' TEXT," + // 7: emails_count
                "'DETAILS' TEXT," + // 8: details
                "'SOURCE_ID' TEXT," + // 9: source_id
                "'SOURCE_URL' TEXT," + // 10: source_url
                "'SOURCE_LINES' TEXT," + // 11: source_lines
                "'SOURCE_SIZE' TEXT," + // 12: source_size
                "'SOURCE_NETWORK' TEXT," + // 13: source_network
                "'SOURCE_PROVIDER' TEXT);"); // 14: source_provider
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'HACKEADO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Hackeado entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String correo = entity.getCorreo();
        if (correo != null) {
            stmt.bindString(2, correo);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
 
        String is_vrf = entity.getIs_vrf();
        if (is_vrf != null) {
            stmt.bindString(5, is_vrf);
        }
 
        String date_created = entity.getDate_created();
        if (date_created != null) {
            stmt.bindString(6, date_created);
        }
 
        String date_leaked = entity.getDate_leaked();
        if (date_leaked != null) {
            stmt.bindString(7, date_leaked);
        }
 
        String emails_count = entity.getEmails_count();
        if (emails_count != null) {
            stmt.bindString(8, emails_count);
        }
 
        String details = entity.getDetails();
        if (details != null) {
            stmt.bindString(9, details);
        }
 
        String source_id = entity.getSource_id();
        if (source_id != null) {
            stmt.bindString(10, source_id);
        }
 
        String source_url = entity.getSource_url();
        if (source_url != null) {
            stmt.bindString(11, source_url);
        }
 
        String source_lines = entity.getSource_lines();
        if (source_lines != null) {
            stmt.bindString(12, source_lines);
        }
 
        String source_size = entity.getSource_size();
        if (source_size != null) {
            stmt.bindString(13, source_size);
        }
 
        String source_network = entity.getSource_network();
        if (source_network != null) {
            stmt.bindString(14, source_network);
        }
 
        String source_provider = entity.getSource_provider();
        if (source_provider != null) {
            stmt.bindString(15, source_provider);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Hackeado readEntity(Cursor cursor, int offset) {
        Hackeado entity = new Hackeado( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // correo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // is_vrf
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // date_created
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // date_leaked
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // emails_count
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // details
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // source_id
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // source_url
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // source_lines
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // source_size
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // source_network
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14) // source_provider
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Hackeado entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCorreo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAuthor(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIs_vrf(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDate_created(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDate_leaked(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setEmails_count(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDetails(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSource_id(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSource_url(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSource_lines(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSource_size(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setSource_network(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setSource_provider(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Hackeado entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Hackeado entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
