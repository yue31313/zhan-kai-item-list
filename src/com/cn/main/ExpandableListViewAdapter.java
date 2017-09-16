package com.cn.main;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    public String[] group = { "����", "��Ц", "ԭ��", "��Ѷ", "����", "��Ϸ", "����", "����" };
    public String[][] gridViewChild = { { "���ǰ���", "���ˇ���", "�������", "�Ƽ���Ѷ", "��Ů����", "��Ϸ����", "��Ů��ģ", "�����" },
            { "�����", "��Ц����", "ԭ����Ц", "��Ц����", "���ˇ���" }, { "ԭ���ȵ�", "ԭ��Ӱ��", "���ֶ���", "���Ǹ�Ц", "У԰��Ʒ", "�������", "�Ŀ�" },
            { "�����Ѷ", "������Ѷ", "������Ѷ", "�Ƹ���Ѷ", "�Ƽ���Ѷ" }, { "�������", "��������", "�ۺ�����", "�����˶�", "����ˤ��", "��Ů����" },
            { "������Ϸ", "���Ӿ���", "��������", "��Ϸ����", "����ս��" }, { "�³��ٵ�", "�����Ƽ�", "��װ����", "�������", "���ⱨ��", "��Ů��ģ" }, { "���ǰ���", "Ӱ����Ѷ" } };
//    String[] group = { "����", "С˵", "����" };
//    String[][] gridViewChild = { { "����1", "����2" }, { "�����˲�" }, { "���ɷ���", "�δ�" } };
    String[][] child = { { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" } };
//    String[] gridViewChild = { "����1", "����2", "���ɷ���", "�δ�" };
    LayoutInflater mInflater;
    Context context;

    public ExpandableListViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return child[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview_item, null);
            mViewChild.gridView = (GridView) convertView.findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(context, setGridViewData(gridViewChild[groupPosition]), R.layout.channel_gridview_item,
                new String[] { "channel_gridview_item" }, new int[] { R.id.channel_gridview_item });
        mViewChild.gridView.setAdapter(mSimpleAdapter);
        setGridViewListener(mViewChild.gridView);
        mViewChild.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

//        hashMap.put(groupPosition + "", mViewChild.gridView);
        return convertView;
    }

    /**
     * ����gridview����¼�����
     * 
     * @param gridView
     */
    private void setGridViewListener(final GridView gridView) {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(view instanceof TextView){
                    //�����Ҫ��ȡ����һ�У����Զ���gridview��adapter��item����2��textviewһ����������id����ʾ��һ��
                    TextView tv = (TextView) view ;
                    Toast.makeText(context, "position=" + position+"||"+tv.getText(), Toast.LENGTH_SHORT).show();
                    Log.e("hefeng", "gridView listaner position=" + position + "||text="+tv.getText());
                }
            }
        });
    }


    /**
     * ����gridview����
     * 
     * @param data
     * @return
     */
    private ArrayList<HashMap<String, Object>> setGridViewData(String[] data) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < data.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("channel_gridview_item", data[i]);
            gridItem.add(hashMap);
        }
        return gridItem;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return child[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return group[groupPosition];
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return group.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview, null);
            mViewChild.textView = (TextView) convertView.findViewById(R.id.channel_group_name);
            mViewChild.imageView = (ImageView) convertView.findViewById(R.id.channel_imageview_orientation);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        if (isExpanded) {
            mViewChild.imageView.setImageResource(R.drawable.channel_expandablelistview_top_icon);
        } else {
            mViewChild.imageView.setImageResource(R.drawable.channel_expandablelistview_bottom_icon);
        }
        mViewChild.textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    ViewChild mViewChild;

    static class ViewChild {
        ImageView imageView;
        TextView textView;
        GridView gridView;
    }
}
