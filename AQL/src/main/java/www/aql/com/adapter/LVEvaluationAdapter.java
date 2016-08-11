package www.aql.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.aql.com.R;
import www.aql.com.base.MyBaseAdapter;
import www.aql.com.entity.response.Evaluation;

/**
 * Created by Jason on 2016/7/20.
 */
public class LVEvaluationAdapter extends MyBaseAdapter<Evaluation> {

    public LVEvaluationAdapter(Context context, List<Evaluation> list) {
        super(context);
        this.list = list;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lv_usercomments, viewGroup, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Evaluation evaluation = list.get(i);
        String nickname = evaluation.reviewuser.nickname;
        if (nickname.equals("") || nickname == null)
            vh.tvUsername.setText("匿名用户");
        else
            vh.tvUsername.setText(nickname);
        vh.tvComment.setText(evaluation.reviewcontent + "");
        vh.tvCommentTime.setText(evaluation.reviewtime + "");

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_commentTime)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment)
        TextView tvComment;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
