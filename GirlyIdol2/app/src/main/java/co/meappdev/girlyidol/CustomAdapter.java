package co.meappdev.girlyidol;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<Result> mResult;
    private ViewHolder mViewHolder;
    private Bitmap mBitmap;

    public CustomAdapter(Context context, List<Result> results) {
        mContext = context;
        mResult = results;
    }

    public int getCount() {
        return mResult.size();
    }

    public Object getItem(int position) {
        return mResult.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
        {
            convertView = mInflater.inflate(R.layout.gridlist, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.model_pic = (ImageView)convertView.findViewById(R.id.imageModel);
            mViewHolder.model_name = (TextView)convertView.findViewById(R.id.textModel);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        final int _position = position;
        if (mResult.get(position).getUrl() != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        URL url = new URL(mResult.get(_position).getUrl());
                        mBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    } catch (MalformedURLException e) {

                    } catch (IOException e) {

                    }
                    return null;
                }
            }.execute();
            mViewHolder.model_pic.setImageBitmap(mBitmap);
        }
        mViewHolder.model_name.setText(mResult.get(position).getWho());

        return convertView;
    }

    private static class ViewHolder {
        ImageView model_pic;
        TextView model_name;
    }
}
