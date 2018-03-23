package com.prashant.RoomDemo.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "_7csB2B.db";
    public static final String USER_TABLE = "user";
    public static final String GOLD_FILTER_OPTIONS_TABLE = "gold_filter_options";
    public static final String DIAMOND_FILTER_OPTIONS_TABLE = "dia_filter_options";
    public static final String DESIGN_TABLE = "design_table";
    public static final String PROFORMA_TABLE = "gold_proforma_table";
    public static final String ORDERS_HISTORY_TABLE = "order_history";
    public static final String CURRENCY_TABLE = "currency";
    public static final String COLLECTION_TABLE = "collection_table";


    public static DatabaseHelper helperInstance;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you don't accidentally leak an Activity's context.
        if (helperInstance == null) {
            helperInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return helperInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

   /* @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String USER_TABLE_QUERY =
                "CREATE TABLE " + USER_TABLE + " (" +
                        USER_ID + " TEXT PRIMARY KEY," +
                        PASSWORD + " TEXT," +
                        SELECTED_DESIGN_CATEGORY + " INTEGER," +
                        USER_ROLE_ID + " INTEGER," +
                        DEFAULT_KARAT + " TEXT," +
                        SHOW_PRICE + " INTEGER," +
                        FIRST_NAME + " TEXT," +
                        LAST_NAME + " TEXT," +
                        TITLE + " TEXT," +
                        COMPANY_NAME + " TEXT," +
                        MOBILE + " INTEGER," +
                        EMAIL + " TEXT," +
                        ADDRESS_1 + " TEXT," +
                        ADDRESS_2 + " TEXT," +
                        ZIP_CODE + " TEXT," +
                        COUNTRY + " TEXT," +
                        CITY + " TEXT," +
                        STATE + " TEXT )";

        final String DESIGN_TABLE_QUERY =
                "CREATE TABLE " + DESIGN_TABLE + " (" +
                        ID + " INTEGER," +
                        DESIGN_CODE + " TEXT PRIMARY KEY," +
                        SELECTED_DESIGN_CATEGORY + " INTEGER," +
                        DESIGN_TYPE + " TEXT," +
                        REMARKS + " TEXT," +
                        BRAND + " TEXT," +
                        GRADE + " TEXT," +
                        METAL_WT + " REAL," +
                        WL_QTY + " INTEGER," +
                        WL_REMARKS + " TEXT," +
                        DIA_QTY + " INTEGER," +
                        DIA_WT + " REAL," +
                        COL_QTY + " INTEGER," +
                        COL_WT + " REAL," +
                        GR_WT + " REAL," +
                        CURR_SYMBOL + " TEXT," +
                        PRICE + " REAL," +
                        GENDER + " TEXT," +
                        UOM + " TEXT," +
                        WORK_TYPE + " TEXT," +
                        MATERIAL + " TEXT," +
                        CATEGORY + " TEXT," +
                        SUB_CATEGORY + " TEXT," +
                        METAL + " TEXT," +
                        KARAT_VALUES + " TEXT," +
                        COLOR_VALUES + " TEXT," +
                        ENAMEL_VALUES + " TEXT," +
                        RHODIUM_VALUES + " TEXT," +
                        SIZE_VALUES + " TEXT," +
                        REGION + " TEXT," +
                        COLLECTION + " TEXT," +
                        COLLECTION_ID + " INTEGER," +
                        LEAD_TIME + " TEXT," +
                        IS_SKETCH + " INTEGER," +
                        CREATED_ON + " INTEGER," +
                        VM_IMAGE_RIGHT + " TEXT," +
                        VM_IMAGE_RIGHT_STORAGE_PATH + " TEXT," +
                        VM_IMAGE_LEFT + " TEXT," +
                        VM_IMAGE_LEFT_STORAGE_PATH + " TEXT," +
                        VM_IMAGE_URL + " TEXT," +
                        VM_IMAGE_STORAGE_PATH + " TEXT," +
                        IMAGE_URL + " TEXT," +
                        IMAGE_STORAGE_PATH + " TEXT )";

        final String GOLD_FILTER_OPTIONS_QUERY =
                "CREATE TABLE " + GOLD_FILTER_OPTIONS_TABLE + " (" +
                        FILTER_OPTION + " TEXT PRIMARY KEY," +
                        FILTER_OPTION_VALUE + " TEXT )";

        final String DIAMOND_FILTER_OPTIONS_QUERY =
                "CREATE TABLE " + DIAMOND_FILTER_OPTIONS_TABLE + " (" +
                        FILTER_OPTION + " TEXT PRIMARY KEY," +
                        FILTER_OPTION_VALUE + " TEXT )";

        final String PROFORMA_TABLE_QUERY =
                "CREATE TABLE " + PROFORMA_TABLE + " (" +
                        PROFORMA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        USER_ID + " TEXT," +
                        SELECTED_DESIGN_CATEGORY + " INTEGER," +
                        DESIGN_CODE + " TEXT," +
                        KARAT + " INTEGER," +
                        COLOR + " TEXT," +
                        RHODIUM + " TEXT," +
                        ENAMEL + " TEXT," +
                        REMARK + " TEXT," +
                        SPECIAL_INSTRUCTIONS + " TEXT," +
                        SIZE + " TEXT," +
                        SIZE_OPTIONS + " TEXT," +
                        CLARITY + " TEXT," +
                        QTY + " INTEGER," +
                        WEIGHT + " REAL," +
                        MIN_METAL_WT + " REAL," +
                        MAX_METAL_WT + " REAL," +
                        IS_GROUPED + " INTEGER DEFAULT 0," +
                        PROFORMA_GROSS_WT + " REAL," +
                        PROFORMA_NET_WT + " REAL," +
                        PROFORMA_DIA_WT + " REAL," +
                        PROFORMA_COL_WT + " REAL," +
                        TOTAL_WEIGHT + " REAL )";

        final String ORDER_HISTORY_TABLE_QUERY =
                "CREATE TABLE " + ORDERS_HISTORY_TABLE + " (" +
                        ORDER_ID + " TEXT PRIMARY KEY," +
                        TAB_ORDER_ID + " TEXT," +
                        USER_ID + " TEXT," +
                        SELECTED_DESIGN_CATEGORY + " INTEGER," +
                        DESIGN_CODE + " TEXT," +
                        ORDER_CREATE_DATE + " INTEGER," +
                        ORDER_FIRST_NAME + " TEXT," +
                        ORDER_LAST_NAME + " TEXT," +
                        ORDER_STREET + " TEXT," +
                        ORDER_ADDRESS_1 + " TEXT," +
                        ORDER_ADDRESS_2 + " TEXT," +
                        ORDER_CITY + " TEXT," +
                        ORDER_COUNTRY + " TEXT," +
                        ORDER_REGION + " TEXT," +
                        ORDER_POSTCODE + " TEXT," +
                        ORDER_PHONE + " TEXT," +
                        ORDER_EMAIL + " TEXT," +
                        ORDER_COMPANY + " TEXT," +
                        ORDER_STATE + " TEXT," +
                        ORDER_FAX + " TEXT," +
                        ORDER_QTY + " INTEGER," +
                        ORDER_WEIGHT + " TEXT," +
                        IS_ORDER_PENDING + " INTEGER," +
                        ORDER_ITEMS_JSON + " TEXT," +
                        IPAD_ORDER_ITEMS_JSON + " TEXT )";

        final String CURRENCY_TABLE_QUERY =
                "CREATE TABLE " + CURRENCY_TABLE + " (" +
                        ID + " INTEGER PRIMARY KEY," +
                        CURRENCY_NAME + " TEXT," +
                        CURRENCY_CODE + " TEXT," +
                        CURRENCY_FLAG + " TEXT," +
                        CURRENCY_FACTOR + " REAL," +
                        CURRENCY_SYMBOL + " TEXT )";

        final String COLLECTION_TABLE_QUERY =
                "CREATE TABLE " + COLLECTION_TABLE + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLLECTION_ENTITY_ID + " TEXT," +
                        COLLECTION_NAME + " TEXT," +
                        COLLECTION_DESCRIPTION + " TEXT," +
                        COLLECTION_IMAGE + " TEXT," +
                        COLLECTION_THUMBNAIL + " TEXT," +
                        COLLECTION_URL + " TEXT," +
                        THUMBNAIL_IMAGE_STORAGE_PATH + " TEXT," +
                        DETAIL_IMAGE_STORAGE_PATH + " TEXT," +
                        COLLECTION_TYPE + " INTEGER )";

        sqLiteDatabase.execSQL(USER_TABLE_QUERY);
        sqLiteDatabase.execSQL(DESIGN_TABLE_QUERY);
        sqLiteDatabase.execSQL(GOLD_FILTER_OPTIONS_QUERY);
        sqLiteDatabase.execSQL(DIAMOND_FILTER_OPTIONS_QUERY);
        sqLiteDatabase.execSQL(PROFORMA_TABLE_QUERY);
        sqLiteDatabase.execSQL(ORDER_HISTORY_TABLE_QUERY);
        sqLiteDatabase.execSQL(COLLECTION_TABLE_QUERY);
        //sqLiteDatabase.execSQL(CURRENCY_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

       *//* sqLiteDatabase.execSQL("DROP TABLE " + PROFORMA_TABLE);
        sqLiteDatabase.execSQL(PROFORMA_TABLE_QUERY);*//*

    }

    public long insertRecord(ContentValues cv, String tableName) {
        return getWritableDatabase().insertWithOnConflict(tableName, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void updateRecordFromDesignId(String tableName, String id, String fieldName, String value) {
        ContentValues values = new ContentValues();
        values.put(fieldName, value);
        int recordsCount = getWritableDatabase().updateWithOnConflict(tableName, values, DESIGN_CODE + "=?", new String[]{id}, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("Design Record update", recordsCount + "");
    }

    public void updateRecordFromId(String tableName, String id, ContentValues cv) {

        int recordsCount = getWritableDatabase().updateWithOnConflict(tableName, cv, ID + "=?", new String[]{id}, SQLiteDatabase.CONFLICT_IGNORE);
        Log.d("Design Record update", recordsCount + "");
        //("UPDATE " + tableName + " SET " + fieldName + "='" +value+ "' WHERE " + ID + "=?", new String[]{id});
    }

    public void updateRecord(String tableName, String whereParamField, String whereParamValue, ContentValues cv) {
        int recordsCount = getWritableDatabase().updateWithOnConflict(tableName, cv, whereParamField + "=?", new String[]{whereParamValue}, SQLiteDatabase.CONFLICT_REPLACE);
        Log.d("Design Record update", recordsCount + "");
    }

    public void deleteRecords(String tableName, String fieldName, String value) {
        String whereClause = null;
        String[] values = null;
        if (fieldName != null) {
            whereClause = fieldName + "=?";
            values = new String[]{value};
        }
        int recordsCount = getWritableDatabase().delete(tableName, whereClause, values);
        Log.d(tableName + " deleted rows", recordsCount + "");
    }

    public HashMap<String, String> getUserDataFromId(String userId) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USER_ID + "=?", new String[]{userId});
        return parseSingleRecord(cursor);
    }

    public HashMap<String, String> validateUserForOfflineLogin(String username, String password) {

        try {
            Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USER_ID + "=? and " + PASSWORD + "=?", new String[]{username, password});
            return parseSingleRecord(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getDistinctValues(String selectFieldName, String selectedCategory) {

        String GROUP_BY_CLAUSE = "GROUP BY " + selectFieldName;

        String whereClause = SELECTED_DESIGN_CATEGORY + "=? ";

        String query = "SELECT DISTINCT " + selectFieldName + " FROM " + DESIGN_TABLE
                + " WHERE " + whereClause + GROUP_BY_CLAUSE + " ORDER BY " + selectFieldName;

        Cursor cursor = getReadableDatabase().rawQuery(query, new String[]{selectedCategory});
        ArrayList<String> arrayList = new ArrayList<>();

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        if (selectFieldName.equals(KARAT_VALUES)) {

                            String value = cursor.getString(cursor.getColumnIndex(KARAT_VALUES));
                            String[] values = value.split("or");
                            for (int i = 0; i < values.length; i++) {
                                if (!arrayList.contains(values[i]) && !value.trim().isEmpty())
                                    arrayList.add(values[i]);
                            }
                        } else {
                            String value = cursor.getString(cursor.getColumnIndex(selectFieldName));

                            if (value == null || value.isEmpty() || value.equals("null"))
                                continue;

                            if (!arrayList.contains(value) && !value.trim().isEmpty())
                                arrayList.add(value);
                        }
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        Collections.sort(arrayList);
        return arrayList;
    }

    public ArrayList<String> getDistinctFilterValues(String selectFieldName, String fieldName, String fieldValue, String selectedCategory) {

        String GROUP_BY_CLAUSE = "GROUP BY " + selectFieldName;

        String whereClause = fieldName + "=? and " + SELECTED_DESIGN_CATEGORY + "=?";

        String query = "SELECT DISTINCT " + selectFieldName + " FROM " + DESIGN_TABLE
                + " WHERE " + whereClause + " " + GROUP_BY_CLAUSE;

        Cursor cursor = getReadableDatabase().rawQuery(query, new String[]{fieldValue, selectedCategory});
        ArrayList<String> arrayList = new ArrayList<>();

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        if (selectFieldName.equals(KARAT_VALUES)) {

                            String value = cursor.getString(cursor.getColumnIndex(KARAT_VALUES));
                            String[] values = value.split("or");
                            for (int i = 0; i < values.length; i++) {
                                if (!arrayList.contains(values[i]) && !value.trim().isEmpty())
                                    arrayList.add(values[i]);
                            }
                        } else {
                            String value = cursor.getString(cursor.getColumnIndex(selectFieldName));
                            if (!arrayList.contains(value) && !value.trim().isEmpty())
                                arrayList.add(value);
                        }
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        Collections.sort(arrayList);
        return arrayList;
    }

    public HashMap<String, ArrayList<String>> getDistinctFilterValues(ContentValues cv) {

        String GROUP_BY_CLAUSE = "GROUP BY "
                + KARAT_VALUES + ","
                + WORK_TYPE + ","
                + CATEGORY + ","
                + SUB_CATEGORY + ","
                + GENDER + ","
                + LEAD_TIME + ","
                + REGION;

        String whereClause = "";
        String values[] = new String[cv.size()];
        int index = 0;

        for (String key :
                cv.keySet()) {

            values[index++] = cv.getAsString(key);

            if (whereClause.isEmpty())
                whereClause = key + "=?";
            else
                whereClause += " and " + key + "=?";
        }
        String query = "SELECT DISTINCT * FROM " + DESIGN_TABLE
                + " WHERE " + whereClause + " " + GROUP_BY_CLAUSE;

        Cursor cursor = getReadableDatabase().rawQuery(query, values);
        ArrayList<String> category = new ArrayList<>();
        ArrayList<String> subCategory = new ArrayList<>();
        ArrayList<String> karat = new ArrayList<>();
        ArrayList<String> workType = new ArrayList<>();
        ArrayList<String> gender = new ArrayList<>();
        ArrayList<String> region = new ArrayList<>();
        ArrayList<String> leadTime = new ArrayList<>();

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        String value = cursor.getString(cursor.getColumnIndex(CATEGORY));
                        if (!category.contains(value) && !value.trim().isEmpty())
                            category.add(value);

                        value = cursor.getString(cursor.getColumnIndex(SUB_CATEGORY));
                        if (!subCategory.contains(value) && !value.trim().isEmpty())
                            subCategory.add(value);

                        value = cursor.getString(cursor.getColumnIndex(KARAT_VALUES));
                        String[] karatValues = value.split("or");
                        for (int i = 0; i < karatValues.length; i++) {
                            if (!karat.contains(karatValues[i]) && !value.trim().isEmpty())
                                karat.add(karatValues[i]);
                        }

                        value = cursor.getString(cursor.getColumnIndex(WORK_TYPE));
                        if (!workType.contains(value) && !value.trim().isEmpty())
                            workType.add(value);

                        value = cursor.getString(cursor.getColumnIndex(GENDER));
                        if (!gender.contains(value) && !value.trim().isEmpty()) gender.add(value);

                        value = cursor.getString(cursor.getColumnIndex(REGION));
                        if (!region.contains(value) && !value.trim().isEmpty()) region.add(value);

                        value = cursor.getString(cursor.getColumnIndex(LEAD_TIME));
                        if (!leadTime.contains(value) && !value.trim().isEmpty())
                            leadTime.add(value);

                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        Collections.sort(category);
        Collections.sort(subCategory);
        Collections.sort(karat);
        Collections.sort(workType);
        Collections.sort(gender);
        Collections.sort(region);
        Collections.sort(leadTime);

        HashMap<String, ArrayList<String>> valuesMap = new HashMap<>();
        valuesMap.put(CATEGORY, category);
        valuesMap.put(SUB_CATEGORY, subCategory);
        valuesMap.put(KARAT_VALUES, karat);
        valuesMap.put(WORK_TYPE, workType);
        valuesMap.put(GENDER, gender);
        valuesMap.put(REGION, region);
        valuesMap.put(LEAD_TIME, leadTime);

        return valuesMap;
    }

    public String[] getFilterOptionValues(String tableName, String filterOption) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT " + FILTER_OPTION_VALUE + " FROM " + tableName + " WHERE " + FILTER_OPTION + "=?", new String[]{filterOption});

        String[] values = new String[]{};
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String value = cursor.getString(cursor.getColumnIndex(FILTER_OPTION_VALUE));

                    if (tableName.equals(DIAMOND_FILTER_OPTIONS_TABLE)) {
                        try {
                            value = "[" + value + "]";
                            JSONArray filterValuesArr = new JSONArray(value);
                            values = new String[filterValuesArr.length()];

                            for (int i = 0; i < filterValuesArr.length(); i++) {
                                JSONObject valuesObj = filterValuesArr.getJSONObject(i);
                                if (valuesObj.has("name"))
                                    values[i] = valuesObj.getString("name");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        values = value.split(",");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        Arrays.sort(values);
        return values;
    }

    public Cursor getDesignImageData(int selectedDesignCategory) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT " + ID + "," + DESIGN_CODE + "," + VM_IMAGE_RIGHT + "," + VM_IMAGE_LEFT + ","
                        + VM_IMAGE_URL + "," + IMAGE_URL + " FROM " + DESIGN_TABLE
                        + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + IMAGE_STORAGE_PATH + "=?",
                new String[]{selectedDesignCategory + "", ""});
        return cursor;
    }

    public Cursor getDesignImageData(int selectedDesignCategory, int offset, int limit) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT " + ID + "," + DESIGN_CODE + "," + VM_IMAGE_RIGHT + "," + VM_IMAGE_LEFT + ","
                        + VM_IMAGE_URL + "," + IMAGE_URL + " FROM " + DESIGN_TABLE
                        + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + IMAGE_STORAGE_PATH + "=? LIMIT ?,?",
                new String[]{selectedDesignCategory + "", "", offset + "", limit + ""});
        return cursor;
    }

    public int getDesignImageDataCount(int selectedDesignCategory) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + DESIGN_TABLE
                        + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + IMAGE_STORAGE_PATH + "=?",
                new String[]{selectedDesignCategory + "", ""});

        int count = 0;
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            } catch (Exception e) {
                count = 0;
                e.printStackTrace();
            }
        }
        return count;
    }

    public ArrayList<HashMap<String, String>> getAllDesignData(int selectedDesignCategory) {

        Cursor cursor;
        if (selectedDesignCategory == CATEGORY_DIAMOND_JEWELLERY) {
            String query = "SELECT * FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + IS_SKETCH + "<>? ORDER BY " + COLLECTION;
            cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + "", "1"});

        } else {
            String query = "SELECT * FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=?";
            cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + ""});
        }
        return parseData(cursor);
    }

    public ArrayList<HashMap<String, String>> getDesignData(int selectedDesignCategory, int recordIndex, int limit) {

        Cursor cursor;
        if (selectedDesignCategory == CATEGORY_DIAMOND_JEWELLERY) {
            String query = "SELECT * FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + IS_SKETCH + "<>? ORDER BY " + COLLECTION + " LIMIT ?, ?";
            cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + "", "1", recordIndex + "", limit + ""});
        } else {
            String query = "SELECT * FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? ORDER BY " + CREATED_ON + " DESC LIMIT ?, ?";
            cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + "", recordIndex + "", limit + ""});
        }
        return parseData(cursor);
    }

    public HashMap<String, String> getDesignFromId(String selectFields, String productId, int selectedDesignCategory) {

        String query = "SELECT " + selectFields + " FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and "
                + ID + "=?";

        Cursor cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + "", productId});
        return parseSingleRecord(cursor);
    }

    public boolean isDesignExist(String designCode, int selectedDesignCategory) {

        String query = "SELECT COUNT(*) FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and "
                + DESIGN_CODE + "=?";

        Cursor cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + "", designCode});

        int count = 0;
        if (null != cursor)
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        cursor.close();

        return count > 0 ? true : false;
    }

    public boolean isDesignDataAvailable(int selectedDesignCategory) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=?", new String[]{selectedDesignCategory + ""});
        int count = 0;
        if (null != cursor)
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        cursor.close();

        return count > 0 ? true : false;

    }

    public ArrayList<HashMap<String, String>> getNewArrivalData(String tableName, int selectedCategory, String[] columns, String[] values) {

        long currentDate = 0, twoMonthsBackDate = 0;

        Calendar calendar = Calendar.getInstance();
        currentDate = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, -2);
        twoMonthsBackDate = calendar.getTimeInMillis();

        String orderBy = "";
        if (selectedCategory == CATEGORY_GOLD_JEWELLERY)
            orderBy = " ORDER BY " + CREATED_ON + " DESC";

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + tableName + " WHERE "
                        + CREATED_ON + ">=" + twoMonthsBackDate + " AND " + CREATED_ON + "<=" + currentDate
                        + " AND " + prepareWhereClause(columns) + orderBy,
                values);
        return parseData(cursor);
    }

    public ArrayList<HashMap<String, String>> getFilteredData(String tableName, String[] columns, String[] values) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + tableName + " WHERE " + prepareWhereClause(columns), values);
        return parseData(cursor);
    }

    public ArrayList<HashMap<String, String>> getFilteredData(
            String tableName, int category, String[] columns, String[] values, int recordIndex, int limit) {

        String orderBy = "";
        if (category == CATEGORY_GOLD_JEWELLERY)
            orderBy = " ORDER BY " + CREATED_ON + " DESC";

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + tableName + " WHERE " + prepareWhereClause(columns)
                + orderBy + " LIMIT " + recordIndex + "," + limit, values);
        return parseData(cursor);
    }

    public long getFilterRecordsCount(String tableName, String[] columns, String[] values) {

        long count = 0;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + tableName + " WHERE "
                + prepareWhereClause(columns), values);

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                count = cursor.getLong(0);
            } catch (Exception e) {
                count = 0;
                e.printStackTrace();
            }
        }
        return count;
    }

    public ArrayList<HashMap<String, String>> getSimilarDesigns(int selectedDesignCategory, String designCode) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + DESIGN_TABLE + " WHERE " + DESIGN_CODE + " LIKE ? and " + SELECTED_DESIGN_CATEGORY + "=?", new String[]{designCode + "%", selectedDesignCategory + ""});
        return parseData(cursor);
    }

    public ArrayList<HashMap<String, String>> getAllOrdersData(String userId, int selectedDesignCategory) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + ORDERS_HISTORY_TABLE
                        + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + USER_ID + "=? ORDER BY " + ORDER_CREATE_DATE + " DESC",
                new String[]{selectedDesignCategory + "", userId});
        return parseData(cursor);
    }

    public ArrayList<HashMap<String, String>> getAllPendingOrdersData(String userId, int selectedDesignCategory) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + ORDERS_HISTORY_TABLE
                        + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + USER_ID + "=? and " + IS_ORDER_PENDING,
                new String[]{selectedDesignCategory + "", userId});
        return parseData(cursor);
    }

    public ArrayList<HashMap<String, String>> getAllRecords(String tableName, ContentValues whereClauseCv) {

        String columns[] = new String[whereClauseCv.size()];
        String values[] = new String[whereClauseCv.size()];

        Iterator<String> cvIterator = whereClauseCv.keySet().iterator();
        int index = 0;
        while (cvIterator.hasNext()) {
            String key = cvIterator.next();
            columns[index] = key;
            values[index] = whereClauseCv.getAsString(key);
            index++;
        }
        //Cursor cursor = getReadableDatabase().rawQuery("SELECT " + QTY + "," + ID + " FROM " + PROFORMA_TABLE + " WHERE " + prepareWhereClause(columns), values);
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + tableName + " WHERE " + prepareWhereClause(columns), values);
        return parseData(cursor);
    }

    public ArrayList<HashMap<String, String>> getProformaList(int selectedDesignCategory, String userId) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + PROFORMA_TABLE + " pt," + DESIGN_TABLE + " dt WHERE pt." + SELECTED_DESIGN_CATEGORY + "=? and pt." + USER_ID + "=? and dt." + DESIGN_CODE + "=pt." + DESIGN_CODE, new String[]{selectedDesignCategory + "", userId});
        return parseData(cursor);
    }

    public HashMap<String, String> getProductFromProforma(int selectedDesignCategory, String userId, String designCode) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + PROFORMA_TABLE + " WHERE " + DESIGN_CODE + "=? and " + SELECTED_DESIGN_CATEGORY + "=? and " + USER_ID + "=?",
                new String[]{designCode, selectedDesignCategory + "", userId});
        return parseSingleRecord(cursor);
    }

    public int clearProformaData(int selectedDesignCategory, String userId) {
        //getWritableDatabase().rawQuery("DELETE FROM " + PROFORMA_TABLE + " WHERE " + USER_ID + "=? and " + SELECTED_DESIGN_CATEGORY + "=?", new String[]{userId, selectedDesignCategory + ""});
        return getWritableDatabase().delete(PROFORMA_TABLE, USER_ID + "=? and " + SELECTED_DESIGN_CATEGORY + "=?", new String[]{userId, selectedDesignCategory + ""});
    }

    public ArrayList<CollectionData> getCollections(int collectionType) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + COLLECTION_TABLE
                        + " WHERE " + COLLECTION_TYPE + "=?",
                new String[]{collectionType + ""});

        ArrayList<HashMap<String, String>> dataAl = parseData(cursor);
        ArrayList<CollectionData> collectionList = new ArrayList<>();

        for (int i = 0; i < dataAl.size(); i++) {

            CollectionData collectionData = new CollectionData();
            collectionData.setEntityId(Utility.getIntegerValueFromString(dataAl.get(i).get(COLLECTION_ENTITY_ID), -1));
            collectionData.setName(dataAl.get(i).get(COLLECTION_NAME));
            collectionData.setUrl(dataAl.get(i).get(COLLECTION_URL));
            collectionData.setDescription(dataAl.get(i).get(COLLECTION_DESCRIPTION));
            collectionData.setThumbnail(dataAl.get(i).get(COLLECTION_THUMBNAIL));
            collectionData.setImage(dataAl.get(i).get(COLLECTION_IMAGE));
            collectionData.setThubnailImagePath(dataAl.get(i).get(THUMBNAIL_IMAGE_STORAGE_PATH));
            collectionData.setDetailImagePath(dataAl.get(i).get(DETAIL_IMAGE_STORAGE_PATH));
            collectionList.add(collectionData);
        }
        return collectionList;
    }

    public ArrayList<CollectionData> getCollectionsImagesData() {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + COLLECTION_TABLE
                        + " WHERE " + DETAIL_IMAGE_STORAGE_PATH + "=? OR " + THUMBNAIL_IMAGE_STORAGE_PATH + "=?",
                new String[]{"", ""});

        ArrayList<HashMap<String, String>> dataAl = parseData(cursor);
        ArrayList<CollectionData> collectionList = new ArrayList<>();

        for (int i = 0; i < dataAl.size(); i++) {

            CollectionData collectionData = new CollectionData();
            collectionData.setDbId(Utility.getIntegerValueFromString(dataAl.get(i).get(ID), -1));
            collectionData.setEntityId(Utility.getIntegerValueFromString(dataAl.get(i).get(COLLECTION_ENTITY_ID), -1));
            collectionData.setName(dataAl.get(i).get(COLLECTION_NAME));
            collectionData.setUrl(dataAl.get(i).get(COLLECTION_URL));
            collectionData.setDescription(dataAl.get(i).get(COLLECTION_DESCRIPTION));
            collectionData.setThumbnail(dataAl.get(i).get(COLLECTION_THUMBNAIL));
            collectionData.setImage(dataAl.get(i).get(COLLECTION_IMAGE));
            collectionData.setCollectionType(Integer.parseInt(dataAl.get(i).get(COLLECTION_TYPE)));
            collectionList.add(collectionData);
        }
        return collectionList;
    }


    public ArrayList<HashMap<String, String>> parseData(Cursor cursor) {

        ArrayList<HashMap<String, String>> dataAl = new ArrayList<HashMap<String, String>>();
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    String coulmns[] = cursor.getColumnNames();
                    do {
                        HashMap<String, String> userMap = new HashMap<String, String>();
                        for (String column : coulmns
                                ) {
                            userMap.put(column, cursor.getString(cursor.getColumnIndex(column)));
                        }
                        dataAl.add(userMap);
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return dataAl;
    }


    public HashMap<String, String> parseSingleRecord(Cursor cursor) {

        HashMap<String, String> userMap = new HashMap<String, String>();
        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String coulmns[] = cursor.getColumnNames();
                    *//*do {*//*
                    for (String column : coulmns
                            ) {
                        userMap.put(column, cursor.getString(cursor.getColumnIndex(column)));
                    }
                    *//*} while (cursor.moveToNext());*//*
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMap;
    }

    private String prepareWhereClause(String[] columns) {
        String whereClause = "";
        String operator;
        String columnName;
        for (int i = 0; i < columns.length; i++) {

            columnName = columns[i];

            if (columns[i].equals(MIN_METAL_WT)) {
                operator = ">=?";
                columnName = METAL_WT;
            } else if (columns[i].equals(MAX_METAL_WT)) {
                operator = "<=?";
                columnName = METAL_WT;
            } else if (columns[i].equals(MIN_DIA_WT)) {
                operator = ">=?";
                columnName = DIA_WT;
            } else if (columns[i].equals(MAX_DIA_WT)) {
                operator = "<=?";
                columnName = DIA_WT;
            } else if (columns[i].equals(MIN_GOLD_WT)) {
                operator = ">=?";
                columnName = METAL_WT;
            } else if (columns[i].equals(MAX_GOLD_WT)) {
                operator = "<=?";
                columnName = METAL_WT;
            } else if (columns[i].equals(MIN_PRICE)) {
                operator = ">=?";
                columnName = PRICE;
            } else if (columns[i].equals(MAX_PRICE)) {
                operator = "<=?";
                columnName = PRICE;
            } else if (columns[i].equals(ORDER_FROM_DATE)) {
                operator = ">=?";
                columnName = ORDER_CREATE_DATE;
            } else if (columns[i].equals(ORDER_TO_DATE)) {
                operator = "<=?";
                columnName = ORDER_CREATE_DATE;
            } else if (columns[i].equals(ORDER_QTY)) {
                operator = "=?";
            } else if (columns[i].equals(ORDER_WEIGHT)) {
                operator = "=?";
            } else
                operator = " LIKE ?";

            if (whereClause.isEmpty())
                whereClause = columns[i] + operator;
            else
                whereClause = whereClause + " and " + columnName + operator;

        }

        return whereClause;
    }

    public long getRecordsCount(int selectedDesignCategory) {

        long count = 0;
        Cursor cursor;
        if (selectedDesignCategory == CATEGORY_DIAMOND_JEWELLERY) {
            String query = "SELECT COUNT(*) FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=? and " + IS_SKETCH + "<>? ORDER BY " + COLLECTION;
            cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + "", "1"});
        } else {
            String query = "SELECT COUNT(*) FROM " + DESIGN_TABLE + " WHERE " + SELECTED_DESIGN_CATEGORY + "=?";
            cursor = getReadableDatabase().rawQuery(query, new String[]{selectedDesignCategory + ""});
        }

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                count = cursor.getLong(0);
            } catch (Exception e) {
                count = 0;
                e.printStackTrace();
            }
        }
        return count;
    }*/
}
