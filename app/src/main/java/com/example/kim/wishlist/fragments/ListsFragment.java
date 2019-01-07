package com.example.kim.wishlist.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.kim.wishlist.R;
import com.example.kim.wishlist.activities.AddListActivity;
import com.example.kim.wishlist.activities.MainActivity;
import com.example.kim.wishlist.adapters.WishlistAdapter;
import com.example.kim.wishlist.data.AppDatabase;
import com.example.kim.wishlist.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class ListsFragment extends Fragment {
    private static final String TAG = "Lists";

    // Variables for the recyclerview
    List<Wishlist> wishlists = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private WishlistAdapter wishlistAdapter;
    private LinearLayoutManager linearLayoutManager;

    // Constants used when calling 'AddListActivity'
    public static final String NEW_ITEM = "Item";
    public static final int REQUESTCODE = 1234;
    public static final int EDIT_REQUESTCODE = 4321;
    public static int mModifyPosition;

    // Variables for the room database
    public static AppDatabase db;

    public final static int TASK_GET_ALL_WISHLISTS = 0;
    public final static int TASK_DELETE_WISHLISTS = 1;
    public final static int TASK_UPDATE_WISHLISTS = 2;
    public final static int TASK_INSERT_WISHLISTS = 3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        setHasOptionsMenu(true);

        mRecyclerView = view.findViewById(R.id.listsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        db = AppDatabase.getInstance(getContext());

        new WishlistAsyncTask(TASK_GET_ALL_WISHLISTS).execute();

        // ItemTouchHelper to remove Wishlists
        ItemTouchHelper.SimpleCallback simpleItemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = (viewHolder.getAdapterPosition());
                new WishlistAsyncTask(TASK_DELETE_WISHLISTS).execute(wishlists.get(position));
                wishlists.remove(position);
                updateUI();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelper);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        return view;
    }

    // Adds options to the toolbar menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_lists, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Makes options in toolbar menu clickable
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(getActivity(), AddListActivity.class);
                startActivityForResult(intent, REQUESTCODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Sets adapter to the RecyclerView and updates the UI
    private void updateUI(){
        if(wishlistAdapter == null){
            wishlistAdapter = new WishlistAdapter(getActivity(), this, wishlists);
            mRecyclerView.setAdapter(wishlistAdapter);
        } else {
            wishlistAdapter.swapList(wishlists);
        }
    }

    // Adds item to the list after coming back from AddListActivity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE){
            if (resultCode == AddListActivity.RESULT_OK) {
                Wishlist newList = data.getParcelableExtra(NEW_ITEM);
                new WishlistAsyncTask(TASK_INSERT_WISHLISTS).execute(newList);
            }
        } else if (requestCode == EDIT_REQUESTCODE){
            if (resultCode == AddListActivity.RESULT_OK){
                Wishlist updatedList = data.getParcelableExtra(NEW_ITEM);
                new WishlistAsyncTask(TASK_UPDATE_WISHLISTS).execute(updatedList);
            }
        }
    }

    // Asynctask
    public class WishlistAsyncTask extends AsyncTask<Wishlist, Void, List>{
        private int taskCode;
        public WishlistAsyncTask(int taskCode){
            this.taskCode = taskCode;
        }

        @Override
        protected List doInBackground(Wishlist... wishlists) {
            switch (taskCode){
                case TASK_DELETE_WISHLISTS:
                    db.wishlistDao().deleteWishlists(wishlists[0]);
                    break;
                case TASK_UPDATE_WISHLISTS:
                    db.wishlistDao().updateWishlists(wishlists[0]);
                    break;
                case TASK_INSERT_WISHLISTS:
                    db.wishlistDao().insertWishlists(wishlists[0]);
                    break;
            }
            return db.wishlistDao().getAllWishlists();
        }

        @Override
        protected void onPostExecute(List list){
            super.onPostExecute(list);
            onWishlistDbUpdated(list);
        }
    }

    //remove later
    public void onWishlistDbUpdated(List list){
        wishlists = list;
        updateUI();
    }
}
