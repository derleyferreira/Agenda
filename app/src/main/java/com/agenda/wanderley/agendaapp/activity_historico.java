package com.agenda.wanderley.agendaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.vipulasri.timelineview.TimelineView;

public class activity_historico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {
        public TimelineView mTimelineView;
        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
        }
    }

    //@Override
    //public int getItemViewType(int position) {
    //    return TimelineView.getTimeLineViewType(position, getItemCount());
    //}

    //@Override
    //public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //    View view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
    //    return new TimeLineViewHolder(view, viewType);
    //} -->
}
