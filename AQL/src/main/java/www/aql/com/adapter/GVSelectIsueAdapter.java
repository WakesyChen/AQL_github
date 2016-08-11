package www.aql.com.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import www.aql.com.R;
import www.aql.com.base.MyBaseAdapter;
import www.aql.com.entity.response.Issue;
import www.aql.com.utils.DisplayHelper;

/**
 * Created by Jason on 2016/7/19.
 */
public class GVSelectIsueAdapter extends MyBaseAdapter {
    public GVSelectIsueAdapter(Context context, List<Issue> list) {
        super(context);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        int padding = DisplayHelper.dp2px(context, 10);
        tv.setPadding(padding, padding, padding, padding);
        Issue issue = (Issue) list.get(position);
        tv.setText(issue.issuesname);
        tv.setBackgroundResource(R.drawable.shape_gv_issue_normal);

        return tv;
    }
}
