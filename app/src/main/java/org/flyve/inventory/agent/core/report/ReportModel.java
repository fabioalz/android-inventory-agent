/*
 * Copyright Teclib. All rights reserved.
 *
 * Flyve MDM is a mobile device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @copyright Copyright Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/android-mdm-agent
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */

package org.flyve.inventory.agent.core.report;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import org.flyve.inventory.InventoryTask;
import org.flyve.inventory.agent.R;
import org.flyve.inventory.agent.utils.FlyveLog;
import org.flyve.inventory.agent.utils.Helpers;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ReportModel implements Report.Model {

    private Report.Presenter presenter;

    public ReportModel(Report.Presenter presenter) {
        this.presenter = presenter;
    }

    public void generateReport(final Activity activity) {
        final InventoryTask inventoryTask = new InventoryTask(activity, Helpers.getAgentDescription(activity), true);
        inventoryTask.getJSON(new InventoryTask.OnTaskCompleted() {
            @Override
            public void onTaskSuccess(String s) {
                presenter.sendInventory(s, load(activity, s));
                inventoryTask.getXMLSyn();
            }

            @Override
            public void onTaskError(Throwable throwable) {
                presenter.showError(throwable.getMessage());
            }
        });
    }

    private ArrayList<String> load(Activity activity, String jsonStr) {
        ProgressDialog progressBar = ProgressDialog.show(activity, "Creating inventory", activity.getResources().getString(R.string.loading));
        ArrayList<String> data = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(jsonStr);
            JSONObject jsonrequest = json.getJSONObject("request");
            JSONObject jsonContent = jsonrequest.getJSONObject("content");

            Iterator<?> keys = jsonContent.keys();

            while( keys.hasNext() ) {
                String key = (String)keys.next();

                if ( jsonContent.get(key) instanceof JSONArray) {
                    // add header
                    data.add(key.toUpperCase());
                }
            }
            progressBar.dismiss();
            return data;
        } catch (Exception ex) {
            progressBar.dismiss();
            presenter.showError(ex.getMessage());
            FlyveLog.e(ex.getMessage());
        }
        return data;
    }

    public void showDialogShare(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_share_title);

        final int[] type = new int[1];

        //list of items
        String[] items = context.getResources().getStringArray(R.array.export_list);
        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        type[0] = which;
                    }
                });

        String positiveText = context.getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        Helpers.share( context, "Inventory Agent File", type[0] );
                    }
                });

        String negativeText = context.getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

}
