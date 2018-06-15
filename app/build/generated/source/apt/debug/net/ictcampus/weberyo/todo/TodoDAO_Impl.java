package net.ictcampus.weberyo.todo;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class TodoDAO_Impl implements TodoDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTodo;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTodo;

  public TodoDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTodo = new EntityInsertionAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `todo`(`ID_Todo`,`title`,`description`,`priority`,`date`,`theme`,`privacy`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getID_Todo());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getPriority());
        if (value.getDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDate());
        }
        if (value.getTheme() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTheme());
        }
        final int _tmp;
        _tmp = value.isPrivacy() ? 1 : 0;
        stmt.bindLong(7, _tmp);
      }
    };
    this.__deletionAdapterOfTodo = new EntityDeletionOrUpdateAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `todo` WHERE `ID_Todo` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getID_Todo());
      }
    };
  }

  @Override
  public void insertTodo(Todo... todos) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTodo.insert(todos);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTodo(Todo todo) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTodo.handle(todo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Todo> getTodoByDay(String date) {
    final String _sql = "SELECT * FROM todo WHERE date = ? ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIDTodo = _cursor.getColumnIndexOrThrow("ID_Todo");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfTheme = _cursor.getColumnIndexOrThrow("theme");
      final int _cursorIndexOfPrivacy = _cursor.getColumnIndexOrThrow("privacy");
      final List<Todo> _result = new ArrayList<Todo>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Todo _item;
        _item = new Todo();
        final int _tmpID_Todo;
        _tmpID_Todo = _cursor.getInt(_cursorIndexOfIDTodo);
        _item.setID_Todo(_tmpID_Todo);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final int _tmpPriority;
        _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
        _item.setPriority(_tmpPriority);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _item.setDate(_tmpDate);
        final String _tmpTheme;
        _tmpTheme = _cursor.getString(_cursorIndexOfTheme);
        _item.setTheme(_tmpTheme);
        final boolean _tmpPrivacy;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfPrivacy);
        _tmpPrivacy = _tmp != 0;
        _item.setPrivacy(_tmpPrivacy);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Todo> getTodoByDayLimi4(String date) {
    final String _sql = "SELECT * FROM todo WHERE date = ? ORDER BY priority DESC LIMIT 4 ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIDTodo = _cursor.getColumnIndexOrThrow("ID_Todo");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfTheme = _cursor.getColumnIndexOrThrow("theme");
      final int _cursorIndexOfPrivacy = _cursor.getColumnIndexOrThrow("privacy");
      final List<Todo> _result = new ArrayList<Todo>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Todo _item;
        _item = new Todo();
        final int _tmpID_Todo;
        _tmpID_Todo = _cursor.getInt(_cursorIndexOfIDTodo);
        _item.setID_Todo(_tmpID_Todo);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final int _tmpPriority;
        _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
        _item.setPriority(_tmpPriority);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _item.setDate(_tmpDate);
        final String _tmpTheme;
        _tmpTheme = _cursor.getString(_cursorIndexOfTheme);
        _item.setTheme(_tmpTheme);
        final boolean _tmpPrivacy;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfPrivacy);
        _tmpPrivacy = _tmp != 0;
        _item.setPrivacy(_tmpPrivacy);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Todo getToDoByTitle(String date, String title) {
    final String _sql = "SELECT * FROM todo WHERE date = ? AND title = ? ORDER BY title DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (title == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, title);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIDTodo = _cursor.getColumnIndexOrThrow("ID_Todo");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfTheme = _cursor.getColumnIndexOrThrow("theme");
      final int _cursorIndexOfPrivacy = _cursor.getColumnIndexOrThrow("privacy");
      final Todo _result;
      if(_cursor.moveToFirst()) {
        _result = new Todo();
        final int _tmpID_Todo;
        _tmpID_Todo = _cursor.getInt(_cursorIndexOfIDTodo);
        _result.setID_Todo(_tmpID_Todo);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _result.setTitle(_tmpTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result.setDescription(_tmpDescription);
        final int _tmpPriority;
        _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
        _result.setPriority(_tmpPriority);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _result.setDate(_tmpDate);
        final String _tmpTheme;
        _tmpTheme = _cursor.getString(_cursorIndexOfTheme);
        _result.setTheme(_tmpTheme);
        final boolean _tmpPrivacy;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfPrivacy);
        _tmpPrivacy = _tmp != 0;
        _result.setPrivacy(_tmpPrivacy);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Todo getToDoById(int id) {
    final String _sql = "SELECT * FROM todo WHERE ID_Todo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIDTodo = _cursor.getColumnIndexOrThrow("ID_Todo");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfPriority = _cursor.getColumnIndexOrThrow("priority");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfTheme = _cursor.getColumnIndexOrThrow("theme");
      final int _cursorIndexOfPrivacy = _cursor.getColumnIndexOrThrow("privacy");
      final Todo _result;
      if(_cursor.moveToFirst()) {
        _result = new Todo();
        final int _tmpID_Todo;
        _tmpID_Todo = _cursor.getInt(_cursorIndexOfIDTodo);
        _result.setID_Todo(_tmpID_Todo);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _result.setTitle(_tmpTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result.setDescription(_tmpDescription);
        final int _tmpPriority;
        _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
        _result.setPriority(_tmpPriority);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _result.setDate(_tmpDate);
        final String _tmpTheme;
        _tmpTheme = _cursor.getString(_cursorIndexOfTheme);
        _result.setTheme(_tmpTheme);
        final boolean _tmpPrivacy;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfPrivacy);
        _tmpPrivacy = _tmp != 0;
        _result.setPrivacy(_tmpPrivacy);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
