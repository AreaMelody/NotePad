# NotePad
功能如下：
* 显示时间戳
* 笔记查询功能
* 美化界面
* 编辑笔记时更换背景


## 一、显示时间戳
### 1、在Noteslist_item中添加一个TextView用来显示时间
```java
<?xml version="1.0" encoding="utf-8"?><!-- Copyright (C) 2010 The Android Open Source Project

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at
  
          http://www.apache.org/licenses/LICENSE-2.0
  
     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="?android:attr/listPreferredItemHeight"
    android:background="#460ba0d6">

    <ImageView
        android:layout_width="48dp"
        android:layout_height="48dp"
        android:layout_marginLeft="5dp"
        android:layout_marginTop="5dp"
        android:background="@drawable/app_notes"/>

    <TextView
        android:id="@android:id/text1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:gravity="center_vertical"
        android:paddingLeft="5dip"
        android:singleLine="true"
        android:textAppearance="?android:attr/textAppearanceLarge" />


    <TextView
        android:id="@android:id/text2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:gravity="center_vertical"
        android:paddingLeft="5dip"
        android:textSize="14dp"
        android:singleLine="true"
        android:textAppearance="?android:attr/textAppearanceLarge" />
</LinearLayout>
```
### 2、继承SimpleCursorAdapter函数重写bindView（）使显示时间时将时间类型转化为data类型。
```java
@Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        String text1 = cursor.getString(1);
        String name=String.format("题目:"+text1);
        if (text1 == null) {
            text1 = "";
        }
        View v1 = view.findViewById(android.R.id.text1);
        if (v1 instanceof TextView) {
            setViewText((TextView) v1, name);
        }

        String text2 = cursor.getString(2);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.parseLong(text2);
        String dateString2 = format2.format(time);
        if (text2 == null) {
            text2 = "";
        }
        View v2 = view.findViewById(android.R.id.text2);
        if (v2 instanceof TextView) {
            setViewText((TextView) v2, dateString2);
        }
    }
```
## 二、笔记查询功能
### 1、在布局文件中加入一个searchview控件.
      ```java
      <SearchView
        android:layout_width="match_parent"
        android:layout_height="30dp" />
      ```
### 2、在activity中写searchview的一些函数
      ```java
      SearchView mSearchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String string) {
                searchNote(string);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String string) {
                searchNote(string);
                return true;
            }
        });
         public void searchNote(String string){
        Cursor cursor = managedQuery(
                getIntent().getData(),            // Use the default content URI for the provider.
                PROJECTION,                       // Return the note ID and title for each note.
                "`" + NotePad.Notes.COLUMN_NAME_TITLE + "` like '%" + string + "%'",                             // No where clause, return all records.
                null,                             // No where clause, therefore no where column values.
                NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
        );

        // The names of the cursor columns to display in the view, initialized to the title column
        String[] dataColumns = {NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE};

        // The view IDs that will display the cursor columns, initialized to the TextView in
        // noteslist_item.xml
        int[] viewIDs = {android.R.id.text1, android.R.id.text2};

        // Creates the backing adapter for the ListView.
        SimpleCursorAdapter adapter
                = new MySimpleCursorAdapter(
                NotesList.this,                             // The Context for the ListView
                R.layout.noteslist_item,          // Points to the XML for a list item
                cursor,                           // The cursor to get items from
                dataColumns,
                viewIDs
        );

        // Sets the ListView's adapter to be the cursor adapter that was just created.
        setListAdapter(adapter);
    }

## 三、美化界面
### 1、在主界面布局中添加图片和更改背景颜色
```java
      <?xml version="1.0" encoding="utf-8"?><!-- Copyright (C) 2010 The Android Open Source Project
     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at
  
          http://www.apache.org/licenses/LICENSE-2.0
  
     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="?android:attr/listPreferredItemHeight"
    android:background="#460ba0d6">

    <ImageView
        android:layout_width="48dp"
        android:layout_height="48dp"
        android:layout_marginLeft="5dp"
        android:layout_marginTop="5dp"
        android:background="@drawable/app_notes"/>

    <TextView
        android:id="@android:id/text1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:gravity="center_vertical"
        android:paddingLeft="5dip"
        android:singleLine="true"
        android:textAppearance="?android:attr/textAppearanceLarge" />


    <TextView
        android:id="@android:id/text2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:gravity="center_vertical"
        android:paddingLeft="5dip"
        android:textSize="14dp"
        android:singleLine="true"
        android:textAppearance="?android:attr/textAppearanceLarge" />
</LinearLayout>
```
## 四、编辑笔记时更换背景
### 1、在编辑界面布局的menu中添加一个选项change,让其点击可变换背景颜色。
```java
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/menu_save"
          android:icon="@drawable/ic_menu_save"
          android:alphabeticShortcut='s'
          android:title="@string/menu_save"
          android:showAsAction="ifRoom|withText" />
    <item android:id="@+id/menu_revert"
          android:icon="@drawable/ic_menu_revert"
          android:title="@string/menu_revert" />
    <item android:id="@+id/menu_delete"
          android:icon="@drawable/ic_menu_delete"
          android:title="@string/menu_delete"
          android:showAsAction="ifRoom|withText" />


    <item android:id="@+id/menu_change"
          android:showAsAction="ifRoom|withText"
          android:title="change"
          />
</menu>
```
### 2、在对应的activity中添加函数。
```java
 @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle all of the possible menu actions.
        switch (item.getItemId()) {

            case R.id.menu_change:
                Log.i("dd","d");
                int[] colors={Color.BLUE,Color.GREEN,Color.GRAY,Color.RED,Color.YELLOW};
                mText.setBackgroundColor(colors[(int) (Math.random()*5)]);
                break;

        case R.id.menu_save:
            String text = mText.getText().toString();
            updateNote(text, null);
            finish();
            break;
        case R.id.menu_delete:
            deleteNote();
            finish();
            break;
        case R.id.menu_revert:
            cancelNote();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
```
## 五、程序截图.

 ![Alt text](/app/src/main/res/drawable/p1.png)
 ![Alt text](/app/src/main/res/drawable/p2.png)
 ![Alt text](/app/src/main/res/drawable/p3.png)
 ![Alt text](/app/src/main/res/drawable/p4.png)



