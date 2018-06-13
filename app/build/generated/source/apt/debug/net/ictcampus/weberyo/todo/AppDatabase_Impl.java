package net.ictcampus.weberyo.todo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile TodoDAO _todoDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `todo` (`ID_Todo` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `description` TEXT, `priority` INTEGER NOT NULL, `date` TEXT, `theme` TEXT, `privacy` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"bd61911a39670bdfa850c8c6fe544122\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `todo`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTodo = new HashMap<String, TableInfo.Column>(7);
        _columnsTodo.put("ID_Todo", new TableInfo.Column("ID_Todo", "INTEGER", true, 1));
        _columnsTodo.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsTodo.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsTodo.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0));
        _columnsTodo.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsTodo.put("theme", new TableInfo.Column("theme", "TEXT", false, 0));
        _columnsTodo.put("privacy", new TableInfo.Column("privacy", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTodo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTodo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTodo = new TableInfo("todo", _columnsTodo, _foreignKeysTodo, _indicesTodo);
        final TableInfo _existingTodo = TableInfo.read(_db, "todo");
        if (! _infoTodo.equals(_existingTodo)) {
          throw new IllegalStateException("Migration didn't properly handle todo(net.ictcampus.weberyo.todo.Todo).\n"
                  + " Expected:\n" + _infoTodo + "\n"
                  + " Found:\n" + _existingTodo);
        }
      }
    }, "bd61911a39670bdfa850c8c6fe544122", "1652e0e4f476a3a700bcbed025dacf17");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "todo");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `todo`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TodoDAO todoDAO() {
    if (_todoDAO != null) {
      return _todoDAO;
    } else {
      synchronized(this) {
        if(_todoDAO == null) {
          _todoDAO = new TodoDAO_Impl(this);
        }
        return _todoDAO;
      }
    }
  }
}
