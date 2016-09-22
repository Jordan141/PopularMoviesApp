package uk.co.keybound.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.keybound.popularmovies.model.Movie;

/**
 * Created by Admin on 22/09/2016.
 */

public class DetailActivityFragment extends Fragment {

    public static final String TAG = DetailActivityFragment.class.getSimpleName();

    static final String DETAIL_MOVIE = "DETAIL_MOVIE";

    private Movie mMovie;


    @BindView(R.id.detail_title)  TextView mTitleView;
    @BindView(R.id.detail_overview)  TextView mOverviewView;
    @BindView(R.id.detail_date)  TextView mDateView;
    @BindView(R.id.detail_vote_average)  TextView mVoteAverageView;
    @BindView(R.id.detail_image)  ImageView mImageView;
     @BindView(R.id.detail_layout)  ScrollView mDetailLayout;
    private Unbinder unbinder;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this,rootView);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mMovie = arguments.getParcelable(DetailActivityFragment.DETAIL_MOVIE);
        }
        if (mMovie != null) {
            mDetailLayout.setVisibility(View.VISIBLE);
        } else {
            mDetailLayout.setVisibility(View.INVISIBLE);
        }

        /*
        mImageView = (ImageView) rootView.findViewById(R.id.detail_image);

        mTitleView = (TextView) rootView.findViewById(R.id.detail_title);
        mOverviewView = (TextView) rootView.findViewById(R.id.detail_overview);
        mDateView = (TextView) rootView.findViewById(R.id.detail_date);
        mVoteAverageView = (TextView) rootView.findViewById(R.id.detail_vote_average);
        */

        if (mMovie != null) {

            String image_url = Utility.buildImageUrl(342, mMovie.getImage2());

            Glide.with(this).load(image_url).into(mImageView);

            mTitleView.setText(mMovie.getTitle());
            mOverviewView.setText(mMovie.getOverview());

            String movie_date = mMovie.getDate();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String date = DateUtils.formatDateTime(getActivity(),
                        formatter.parse(movie_date).getTime(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
                mDateView.setText(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            mVoteAverageView.setText(Integer.toString(mMovie.getRating()));
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
