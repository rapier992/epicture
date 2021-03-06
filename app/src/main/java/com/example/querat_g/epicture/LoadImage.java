package com.example.querat_g.epicture;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/***
 * Asynchronously fetch the image gallery of user of connected API
 */

public class LoadImage extends AsyncTask<Void, Integer, ArrayList<ApiImages>>  {
    public ArrayList<ApiImages> img = new ArrayList<ApiImages>();
    private ProgressDialog  dialog;
    private MainActivity    activity;
    private int             usage;

    public LoadImage(MainActivity activity, int usage){
        this.activity = activity;
        dialog = new ProgressDialog(activity);
        this.usage = usage;
    }

    protected void onPreExecute(){
        super.onPreExecute();
        dialog.setMessage("Loading gallery");
        dialog.show();
    }

    /***
     * Retrieve images uploaded by user on website
     * @param params default parameters
     * @return list of images on website
     */
    protected ArrayList<ApiImages> doInBackground(Void... params) {
        switch (usage){
            case 1 :
                try {
                    img.add(new ApiImages(1,"IMG 1", new URL("http://i.onionstatic.com/onion/7954/original/800.jpg")));
                    img.add(new ApiImages(2,"IMG 2", new URL("http://dreamatico.com/data_images/kitten/kitten-1.jpg")));
                    img.add(new ApiImages(3,"IMG 3", new URL("http://r.ddmcdn.com/s_f/o_1/w_1024/h_682/APL/uploads/2012/10/TooCute-KittenCamHighlights.jpg")));
                    img.add(new ApiImages(4,"IMG 4", new URL("https://www.royalcanin.com/~/media/Royal-Canin/Callouts/Rail%20Callouts/rail_callout_Kitten-Grooming.ashx")));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case 2 :
                //TODO FLICKR
                break;
            default:
                break;
        }
        return img;
    }

    protected void onProgressUpdate(Integer ... values){
    }

    protected void onPostExecute(ArrayList<ApiImages> img){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        this.activity.setList(img);
        this.activity.manageGallery();
    }

}
