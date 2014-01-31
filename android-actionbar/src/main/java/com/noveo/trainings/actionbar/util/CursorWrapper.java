package com.noveo.trainings.actionbar.util;


import android.database.Cursor;

public class CursorWrapper extends android.database.CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        if ("_id".equals(columnName)) {
            return -1;
        }
        return super.getColumnIndexOrThrow(columnName);
    }
}
